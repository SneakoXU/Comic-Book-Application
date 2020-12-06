package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StatisticsSqlDAO implements StatisticDAO{

	private JdbcTemplate jdbcTemplate;
	
	public StatisticsSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public long getCollectionsByCreator(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionsByAuthor(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionsByCharacter(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCollectionsByComic(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getUsersByCollection(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getComicsByCreator(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getComicsByAuthor(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getComicsByCharacter(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
