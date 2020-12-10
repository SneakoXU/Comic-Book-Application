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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.techelevator.dao.CollectionDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Collection;
import com.techelevator.model.CollectionStatistics;

@RestController
@RequestMapping("/collections")
@PreAuthorize("isAuthenticated()")
public class CollectionController {

	private CollectionDAO collectionDAO;
	private UserDAO userDAO;
	
	public CollectionController(CollectionDAO collectionDAO, UserDAO userDAO) {
		this.collectionDAO = collectionDAO;
		this.userDAO = userDAO;
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/public", method = RequestMethod.GET)
    public List<Collection> getPublicCollections(Principal principal) {
        List<Collection> collections = collectionDAO.getCollections(true);
        System.out.println(principal.getName());
        return collections;
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{collectionId}", method = RequestMethod.GET)
    public Collection getPublicCollectionById(@PathVariable int collectionId, Principal principal){
        Collection collection = collectionDAO.getCollectionByID(collectionId, true);
        if(collection == null)
        	throw new ResponseStatusException(
        	          HttpStatus.NOT_FOUND, "Collection Not Found");
        if((principal == null && collection.isPublic()) || verifyUser(principal, collectionId))
        	return collection;
        throw new ResponseStatusException(
  	          HttpStatus.UNAUTHORIZED, "This collection is private");
    }
	
	@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{collectionId}/add/{comicId}", method = RequestMethod.POST)
    public void addComic(@PathVariable int comicId, @PathVariable int collectionId, Principal principal) {
		if (collectionDAO.hasComic(comicId, collectionId)) 
			throw new ResponseStatusException(
	      	          HttpStatus.CONFLICT, "You cannot add duplicate comics to a collection");
		if(verifyUser(principal, collectionId))
			collectionDAO.addComic(MarvelAPIController.Comic.getComic(comicId).getData().getResults()[0], collectionId);
		else 
			throw new ResponseStatusException(
	      	          HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
    }
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{collectionId}/remove/{comicId}", method = RequestMethod.POST)
	public void deleteComic(@PathVariable int comicId, @PathVariable int collectionId, Principal principal) {
		if(verifyUser(principal, collectionId))
			collectionDAO.deleteComic(collectionId, comicId);
		else 
			throw new ResponseStatusException(
		      	          HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
	}
		
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/thumbnail/{collectionId}", method = RequestMethod.GET)
	public String getThumbnail(@PathVariable int collectionId, Principal principal) {
		//if(verifyUser(principal, collectionId))
			return collectionDAO.getThumbnail(collectionId);
		//else 
			//throw new ResponseStatusException(
		      	          //HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/delete/{collectionId}", method = RequestMethod.POST)
	public void removeCollection(@PathVariable int collectionId, Principal principal) {
		if(verifyUser(principal, collectionId))
			collectionDAO.removeCollection(collectionId);
		else 
			throw new ResponseStatusException(
	      	          HttpStatus.UNAUTHORIZED, "You cannot delete others' collections");
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void addCollection(@RequestBody Collection collection, Principal principal) {

		if(collection == null || collection.getName() == null)
			throw new ResponseStatusException(
      	          HttpStatus.BAD_REQUEST, "Empty Request");
		else
			collectionDAO.addCollection(collection.setDateCreated(Date.valueOf( LocalDate.now())).setUserID(userDAO.findIdByUsername(principal.getName())));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/createraw", method = RequestMethod.POST)
	public void addCollectionRaw(Collection collection, Principal principal) {

		if(collection == null || collection.getName() == null)
			throw new ResponseStatusException(
      	          HttpStatus.BAD_REQUEST, "Empty Request");
		else
			collectionDAO.addCollection(collection.setDateCreated(Date.valueOf( LocalDate.now())).setUserID(userDAO.findIdByUsername(principal.getName())));
	}
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/stat/{collectionId}", method = RequestMethod.GET)
	public CollectionStatistics addCollection(@PathVariable int collectionId, Principal principal) {
		CollectionStatistics stats = new CollectionStatistics();
		Collection collection = collectionDAO.getCollectionByID(collectionId, false);
        if(collection == null)
        	throw new ResponseStatusException(
        	          HttpStatus.NOT_FOUND, "Collection Not Found");
        if((principal == null && collection.isPublic()) || (principal != null && verifyUser(principal, collectionId)))
        	return stats.setNumberOfComics(collection.getComicBookIDs().size());
        throw new ResponseStatusException(
  	          HttpStatus.UNAUTHORIZED, "This collection is private");
	}
	
	private boolean verifyUser(Principal principal, int collectionId)
	{
		System.out.println(principal);
		return userDAO.getUserById( collectionDAO.getCollectionOwner(collectionId)).getUsername().equals(principal.getName());
	}
}
