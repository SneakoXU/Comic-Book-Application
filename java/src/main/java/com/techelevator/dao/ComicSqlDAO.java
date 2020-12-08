package com.techelevator.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.techelevator.model.marvel.fields.Field;

@Component
public class ComicSqlDAO implements ComicDAO{

	private JdbcTemplate jdbcTemplate;
	
	public ComicSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Field getComicByID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field addComic(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Field removeComic(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Field> getComicByCharacter(long characterID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Field> getComicByAuthor(long authorID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Field> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
