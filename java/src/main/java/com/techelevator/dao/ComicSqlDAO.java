package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.marvel.fields.Field;
import com.techelevator.model.marvel.fields.Image;

@Component
public class ComicSqlDAO implements ComicDAO{

	private JdbcTemplate jdbcTemplate;
	
	public ComicSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Field getComicByID(long id) {
		Field  comic = null;
        String sql = "select comic_id, title, description, thumbnail_url from comic where comic_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next() ) {
            comic = mapRowToComic(results);
        }
        return  comic;
	}
	
	@Override
	public String getThumbnail(long id) {
        String sql = "select thumbnail_url from comic where comic_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,id);
        if(results.next() ) {
            return results.getString("thumbnail_url");
        }
        return "";
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
	
	@Override
	public List<Field> getComicsByCollection(long collection_id) {
		List<Field> comics = new ArrayList<Field>();
        String sql = "select c.comic_id, c.title, c.description, c.thumbnail_url from collection_comic as cc inner join comic as c on c.comic_id = cc.comic_id where cc.collection_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, collection_id);
        while(results.next()) {
        	comics.add(mapRowToComic(results));
        }
        return comics;
	}

	public static Field mapRowToComic(SqlRowSet rs)
	{
		Field field = new Field();
		field.setId(rs.getInt("comic_id"));
		field.setTitle(rs.getString("title"));
		field.setDescription(rs.getString("description"));
		field.setThumbnail(new Image(rs.getString("thumbnail_url"), ""));
		return field;
	}

}
