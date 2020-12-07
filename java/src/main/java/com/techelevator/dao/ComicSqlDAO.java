package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.model.marvel.Comic;

@Component
public class ComicSqlDAO implements ComicDAO{

	private JdbcTemplate jdbcTemplate;
	
	public ComicSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Comic getComicByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comic addComic(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comic removeComic(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comic> getComicByCharacter(long characterID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comic> getComicByAuthor(long authorID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comic> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
