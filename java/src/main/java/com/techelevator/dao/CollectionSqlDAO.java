package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.controller.ComicController;
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
	public Collection getCollectionByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection addCollection(Collection newCollection) {
		// TODO Auto-generated method stub
		return null;
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
	public List<Collection> getPublicCollectionsByCreator(long creatorID) {
		List<Collection> collections = new ArrayList<Collection>();
        String sql = "select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where creator_id = ? and publicstatus = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, creatorID);
        while(results.next()) {
            collections.add( mapRowToCollection(results));
        }
        return collections;
	}
	
	@Override
	public List<Collection> getPublicCollections() {
		List<Collection> collections = new ArrayList<Collection>();
        String sql = "select collection_id, collectionname, creator_id, publicstatus, datecreated from collections where publicstatus = true";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            collections.add( mapRowToCollection(results));
        }
        return collections;
	}

	@Override
	public List<Collection> getCollectionByUser(long userID) {
		// TODO Auto-generated method stub
		return null;
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
	
	
	
	private Collection mapRowToCollection(SqlRowSet rs)
	{
		return new Collection(comicDAO.getComicsByCollection(rs.getLong("collection_id")), rs.getLong("creator_id"), rs.getBoolean("publicstatus"), rs.getDate("datecreated"), rs.getString("collectionname"));
	}

	

}
