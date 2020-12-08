package com.techelevator.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.CollectionDAO;
import com.techelevator.model.Collection;

@RestController
@RequestMapping("/collections")
@PreAuthorize("isAuthenticated()")
public class CollectionController {

	private CollectionDAO collectionDAO;
	
	public CollectionController(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="public", method = RequestMethod.GET)
    public List<Collection> getPublicCollections(Principal principal) {
        List<Collection> collections = collectionDAO.getCollections(true);
        return collections;
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Collection getPublicCollectionById(@PathVariable int id, Principal principal){
        Collection collections = collectionDAO.getCollectionByID(id, true);
        if(collections == null)
        	throw new ResponseStatusException(
        	          HttpStatus.NOT_FOUND, "Collection Not Found");
        return collections;
    }
	
	//TODO: make edit perms so that only the collection owner can do this
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{collectionId}/add/{comicId}", method = RequestMethod.POST)
    public void addComic(@PathVariable int comicId, @PathVariable int collectionId, Principal principal) {
		collectionDAO.addComic(MarvelAPIController.Comic.getComic(comicId).getData().getResults()[0], collectionId);
    }
	
	//TODO: make edit perms so that only the collection owner can do this
		@PreAuthorize("permitAll()")
		@ResponseStatus(HttpStatus.OK)
	    @RequestMapping(value = "/{collectionId}/remove/{comicId}", method = RequestMethod.POST)
	    public void deleteComic(@PathVariable int comicId, @PathVariable int collectionId, Principal principal) {
			collectionDAO.deleteComic(collectionId, comicId);
	    }
	
	//TODO: make edit perms so that only the collection owner can do this
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/delete/{collectionId}", method = RequestMethod.POST)
	public void removeCollection(@PathVariable int collectionId, Principal principal) {
		collectionDAO.removeCollection(collectionId);
	}
	
	//TODO: make edit perms so that only the collection owner can do this
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void addCollection( Collection collection, Principal principal) {
		if(collection == null || collection.getName() == null)
			throw new ResponseStatusException(
      	          HttpStatus.BAD_REQUEST, "Empty Request");
		else
			collectionDAO.addCollection(collection.setDateCreated(Date.valueOf( LocalDate.now())).setUserID(1));
	}
	
	

	
}
