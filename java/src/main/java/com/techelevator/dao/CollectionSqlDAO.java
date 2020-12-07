package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.model.Collection;

@Component
public class CollectionSqlDAO implements CollectionDAO {

    private JdbcTemplate jdbcTemplate;
	
	public CollectionSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
	public List<Collection> getCollectionByCreator(long creatorID) {
		// TODO Auto-generated method stub
		return null;
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

}
