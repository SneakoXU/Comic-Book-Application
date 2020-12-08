package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Collection;

public interface CollectionDAO {

	Collection getCollectionByID(long id);
	
	Collection addCollection(Collection newCollection);
	
	Collection removeCollection(long id);
	
	List<Collection> getCollectionByCharacter(long characterID);
		
	List<Collection> getCollectionByUser(long userID);
	
	List<Collection> getCollectionByComic(long comicBookID);
	
	List<Collection> findAll();
	
	List<Long> getComicIdsByCollection(long collection_id);

	List<Collection> getPublicCollectionsByCreator(long creatorID);

	List<Collection> getPublicCollections();
	
}
