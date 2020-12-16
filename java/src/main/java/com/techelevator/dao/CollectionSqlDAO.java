package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Collection;
import com.techelevator.model.Comment;
import com.techelevator.model.marvel.fields.DataWrapper;
import com.techelevator.model.marvel.fields.Field;
import com.techelevator.model.marvel.fields.Summary;

@Component
public class CollectionSqlDAO implements CollectionDAO {

	private ComicDAO comicDAO;
	private UserDAO userDAO;
    private JdbcTemplate jdbcTemplate;
	
	public CollectionSqlDAO(JdbcTemplate jdbcTemplate, ComicDAO comicDAO, UserDAO userDAO) {
		this.jdbcTemplate = jdbcTemplate;
		this.comicDAO = comicDAO;
		this.userDAO = userDAO;
	}
	
	
	@Override
	public List<Collection> getCollections(String name, int limit, int page, int userId)
	{
		List<Collection> collections = new ArrayList<Collection>();
		String sql = "select c.collection_id, c.collectionname, c.creator_id, c.publicstatus, c.datecreated from collections as c where c.collectionname ilike ? and (c.publicstatus = true or (c.creator_id = ? or c.creator_id in (select user_id from user_friend where friend_id = ?) or c.creator_id in (select friend_id from user_friend where user_id = ?))) group by c.collection_id order by c.collection_id asc limit ? offset ?;"; 
		System.out.println(name);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + name + "%", userId, userId, userId, limit, page*limit);
        while (results.next())
        {
        	collections.add(mapRowToCollection(results));
        }
        return collections;
	}
	
	@Override
	public List<Collection> getCollections(String name, int limit, int page)
	{
		List<Collection> collections = new ArrayList<Collection>();
		String sql = "select c.collection_id, c.collectionname, c.creator_id, c.publicstatus, c.datecreated from collections as c where c.collectionname ilike ? and c.publicstatus = true group by c.collection_id order by c.collection_id asc limit ? offset ?;"; 
		System.out.println(name);
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + name + "%", limit, page*limit);
        while (results.next())
        {
        	collections.add(mapRowToCollection(results));
        }
        return collections;
	}
	
	@Override
	public Collection getCollectionByID(long id, boolean isPublic) {
		if(isPublic)
			return getCollection("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = true and collection_id = ?",
				new Object[] {id},
				new int[] {java.sql.Types.INTEGER});
		else 
			return getCollection("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where collection_id = ?",
					new Object[] {id},
					new int[] {java.sql.Types.INTEGER});
		
	}

	@Override
	public void addCollection(Collection newCollection) {
		String sql = "insert into collections (collectionname, creator_id, publicstatus, datecreated) values (?, ?, ?, ?) on conflict do nothing;";
        jdbcTemplate.update(sql, newCollection.getName(),  newCollection.getUserID(), newCollection.isPublic(),newCollection.getDateCreated());
	}
	
	@Override
	public boolean hasComic(int comicId, int collectionId)
	{
		String sql = "select collection_id from collection_comic where collection_id = ? and comic_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId, comicId);
        return results.next();
	}
	
	@Override
	public void addComic(Field newComic, int collectionId) {
		String sql = "insert into comic (comic_id, title, description, thumbnail_url) values (?, ?, ?, ?) on conflict do nothing;";
        jdbcTemplate.update(sql, newComic.getId(), newComic.getTitle(), newComic.getDescription(), newComic.getThumbnail().getPath()+"."+newComic.getThumbnail().getExtension());
        sql = "insert into collection_comic (comic_id, collection_id) values (?, ?) on conflict do nothing;";
        jdbcTemplate.update(sql, newComic.getId(), collectionId);
        for(Summary creator : newComic.getCreators().getItems())
        {
        	if(creator.getResourceURI() != null)
        	{
        		int id = 0;
        		try 
        		{
        			id = Integer.parseInt( creator.getResourceURI().substring(creator.getResourceURI().lastIndexOf('/') + 1));
				} 
        		catch (Exception e) 
        		{
					System.out.println(creator.getResourceURI().substring(creator.getResourceURI().lastIndexOf('/') + 1));
				}
        		sql = "insert into author (author_id, firstname, lastname, description, thumbnail_url) values (?, ?, ?, ?, ?) on conflict do nothing;";
        		String firstName = "";
        		String lastName = "";
        		try {
        		firstName = creator.getName().substring(0, creator.getName().indexOf(' '));
        		}
        		catch (Exception e) {
					// TODO: handle exception
				}
        		try {
        			lastName = creator.getName().substring(creator.getName().lastIndexOf(' '));
				} catch (Exception e) {
					// TODO: handle exception
				}
        		
        		jdbcTemplate.update(sql, id, firstName, lastName, creator.getRole(), "");
        		if(!comicDAO.hasAuthor(newComic.getId(), id))
        		{
        			sql = "insert into comic_author (comic_id, author_id) values (?, ?) on conflict do nothing;";
        			jdbcTemplate.update(sql, newComic.getId(), id);
        		}
        	}
        }
	}
	
	@Override
	public void deleteComic(int collectionId, int comicId) {
		String sql = "delete from collection_comic where comic_id = ? and collection_id = ?;";
        jdbcTemplate.update(sql, comicId, collectionId);
	}
	
	@Override 
	public void addComment(Comment comment)
	{
		System.out.println(comment.getCommenter_id());
		String sql = "insert into comment (commenter_id, collection_id, text) values (?, ?, ?)";
        jdbcTemplate.update(sql, comment.getCommenter_id(), comment.getCollection_id(), comment.getText());
	}
	
	@Override 
	public void deleteComment(int commentId)
	{
		String sql = "delete from comment where comment_id = ?";
        jdbcTemplate.update(sql, commentId);
	}
	
	@Override 
	public List<Comment> getComments(int collectionId)
	{
		String sql = "Select * from comment where collection_id = ?";
		List<Comment> comments = new ArrayList<>();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
		while(results.next()) {
			comments.add(mapRowToComment(results));
		}
		return  comments;
	}

	@Override
	public void removeCollection(long id) 
	{
		jdbcTemplate.update("delete from comment where collection_id = ?;", id);
		jdbcTemplate.update("delete from collection_comic where collection_id = ?;", id);
		jdbcTemplate.update("delete from subscription where collection_id = ?;", id);
		jdbcTemplate.update("delete from collections where collection_id = ?;", id);
	}

	@Override
	public List<Collection> getCollectionByCharacter(long characterID) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collection> getPublicCollectionsByCreator(boolean isPublic, long creatorID) {
		return getCollections("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = ? and creator_id = ?",
				new Object[] {isPublic, creatorID},
				new int[] {java.sql.Types.BOOLEAN, java.sql.Types.INTEGER});
	}
	
	@Override
	public List<Collection> getPublicCollectionsBySubscription(boolean isPublic, long userId) {
		if(isPublic)
		{
			return getCollections("select c.collection_id, c.collectionname, c.creator_id, c.publicstatus, c.datecreated from collections as c inner join subscription as s on c.collection_id = s.collection_id where publicstatus = true and s.user_id = ?;",
					new Object[] {userId},
					new int[] {java.sql.Types.INTEGER});
		}
		return getCollections("select c.collection_id, c.collectionname, c.creator_id, c.publicstatus, c.datecreated from collections as c inner join subscription as s on c.collection_id = s.collection_id inner join user_friend as uf on (uf.user_id = ? or uf.friend_id = ?) where (publicstatus = true or ((uf.user_id = ? and uf.friend_id = c.creator_id) or (uf.friend_id = ? and uf.user_id = c.creator_id))) and s.user_id = ? group by c.collection_id;",
				new Object[] {userId,userId,userId,userId,userId},
				new int[] {java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.INTEGER,java.sql.Types.INTEGER});

		
	}
	
	@Override
	public List<Collection> getCollections(boolean isPublic) {
		return getCollections("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = ?",
				new Object[] {isPublic},
				new int[] {java.sql.Types.BOOLEAN});
	}

	@Override
	public List<Collection> getCollectionsByUser(long userID, boolean isPublic)
	{
		if(isPublic)
		{
			return getCollections("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = true and creator_id = ?",
				new Object[] {userID},
				new int[] {java.sql.Types.INTEGER});
		}
		return getCollections("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where creator_id = ?",
				new Object[] {userID},
				new int[] {java.sql.Types.INTEGER});
		
	}
	
	@Override
	public String getThumbnail(int collectionId) {
		String sql = "select c.thumbnail_url from comic as c inner join collection_comic as cc on c.comic_id = cc.comic_id where cc.collection_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while(results.next()) {
            return results.getString("thumbnail_url");
        }
        return "";
	}
	
	@Override
	public int getCollectionOwner(int collectionId)
	{
		String sql = "select creator_id from collections where collection_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collectionId);
        while(results.next()) {
            return  results.getInt("creator_id");
        }
		return -1;

	}

	@Override
	public List<Collection> getCollectionByComic(long comicBookID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collection> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<Collection> getCollections(String sql, Object[] args, int[] types)
	{
		List<Collection> collections = new ArrayList<Collection>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, args, types);
        while(results.next()) {
            collections.add( mapRowToCollection(results));
        }
        return collections;
	}
	
	private Collection getCollection(String sql, Object[] args, int[] types)
	{
		Collection collections = null;

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, args, types);
        while(results.next()) {
            collections = mapRowToCollection(results);
        }
        return collections;
	}
	
	
	private Collection mapRowToCollection(SqlRowSet rs)
	{
		return new Collection(rs.getInt("collection_id"), comicDAO.getComicsByCollection(rs.getLong("collection_id")), rs.getLong("creator_id"), rs.getBoolean("publicstatus"), rs.getDate("datecreated"), rs.getString("collectionname"));
	}
	
	private Comment mapRowToComment(SqlRowSet rs)
	{
		return new Comment(rs.getInt("comment_id"), rs.getInt("commenter_id"), userDAO.getUsername(rs.getInt("commenter_id")), rs.getInt("collection_id"), rs.getInt("likes"), rs.getString("text"));
	}

	

}
