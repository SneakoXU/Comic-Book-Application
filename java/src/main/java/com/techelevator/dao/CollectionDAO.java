package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Collection;
import com.techelevator.model.marvel.fields.Field;

public interface CollectionDAO {

	Collection getCollectionByID(long id, boolean isPublic);
	
	void addCollection(Collection newCollection);
	
	Collection removeCollection(long id);
	
	List<Collection> getCollectionByCharacter(long characterID);
		
	List<Collection> getCollectionsByUser(long userID, boolean isPublic);
	
	List<Collection> getCollectionByComic(long comicBookID);
	
	List<Collection> findAll();
	
	List<Collection> getPublicCollectionsByCreator(boolean isPublic, long creatorID);

	List<Collection> getCollections(boolean isPublic);

	void addComic(Field newComic, int collectionId);

	void deleteComic(int collectionId, int comicId);
	
}
