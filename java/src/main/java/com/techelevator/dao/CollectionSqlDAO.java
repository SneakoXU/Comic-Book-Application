package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Collection;
import com.techelevator.model.marvel.fields.Field;

@Component
public class CollectionSqlDAO implements CollectionDAO {

	private ComicDAO comicDAO;
    private JdbcTemplate jdbcTemplate;
	
	public CollectionSqlDAO(JdbcTemplate jdbcTemplate, ComicDAO comicDAO) {
		this.jdbcTemplate = jdbcTemplate;
		this.comicDAO = comicDAO;
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
	public void addComic(Field newComic, int collectionId) {
		String sql = "insert into comic (comic_id, title, description, thumbnail_url) values (?, ?, ?, ?) on conflict do nothing;";
        jdbcTemplate.update(sql, newComic.getId(), newComic.getTitle(), newComic.getDescription(), newComic.getThumbnail().getPath()+newComic.getThumbnail().getExtension());
        sql = "insert into collection_comic (comic_id, collection_id) values (?, ?);";
        jdbcTemplate.update(sql, newComic.getId(), collectionId);
	}
	
	@Override
	public void deleteComic(int collectionId, int comicId) {
		String sql = "delete from collection_comic where comic_id = ? and collection_id = ?;";
        jdbcTemplate.update(sql, comicId, collectionId);
	}

	@Override
	public Collection removeCollection(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Collection> getCollectionByCharacter(long characterID) {
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
	public List<Collection> getCollections(boolean isPublic) {
		return getCollections("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = ?",
				new Object[] {isPublic},
				new int[] {java.sql.Types.BOOLEAN});
	}

	@Override
	public List<Collection> getCollectionsByUser(long userID, boolean isPublic) {
		return getCollections("select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = ? and creator_id = ?",
				new Object[] {isPublic, userID},
				new int[] {java.sql.Types.BOOLEAN, java.sql.Types.INTEGER});
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
		return new Collection(comicDAO.getComicsByCollection(rs.getLong("collection_id")), rs.getLong("creator_id"), rs.getBoolean("publicstatus"), rs.getDate("datecreated"), rs.getString("collectionname"));
	}

	

}
