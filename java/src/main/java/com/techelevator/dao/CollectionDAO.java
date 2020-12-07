package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Collection;
import com.techelevator.model.marvel.Comic;

public interface CollectionDAO {

	Collection getCollectionByID(long id);
	
	Collection addCollection(Collection newCollection);
	
	Collection removeCollection(long id);
	
	List<Collection> getCollectionByCharacter(long characterID);
	
	List<Collection> getCollectionByCreator(long creatorID);
	
	List<Collection> getCollectionByUser(long userID);
	
	List<Collection> getCollectionByComic(long comicBookID);
	
	List<Collection> findAll();
	
}
