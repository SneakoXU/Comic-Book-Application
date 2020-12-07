package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.marvel.fields.Comic;

public interface ComicDAO {
	
	Comic getComicByID(long id);
	
	Comic addComic(long id);
	
	Comic removeComic(long id);
	
	List<Comic> getComicByCharacter(long characterID);
	
	List<Comic> getComicByAuthor(long authorID);
	
	List<Comic> findAll();

}
