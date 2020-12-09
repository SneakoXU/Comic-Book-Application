package com.techelevator.dao;

import java.util.List;
import com.techelevator.model.marvel.fields.Field;

public interface ComicDAO {
	
	Field getComicByID(long id);
	
	Field addComic(long id);
	
	Field removeComic(long id);
	
	List<Field> getComicByCharacter(long characterID);
	
	List<Field> getComicByAuthor(long authorID);
	
	List<Field> findAll();

	List<Field> getComicsByCollection(long collection_id);

	String getThumbnail(long id);

}
