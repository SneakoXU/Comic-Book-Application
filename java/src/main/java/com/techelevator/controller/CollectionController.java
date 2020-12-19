package com.techelevator.controller;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.techelevator.model.Comment;
import com.techelevator.model.marvel.fields.Container;
import com.techelevator.model.marvel.fields.DataWrapper;
import com.techelevator.model.marvel.fields.Field;


@RestController
@CrossOrigin
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
	@RequestMapping(value="/{name}/{number}/{page}", method = RequestMethod.GET)
    public DataWrapper getCollectionsNameNumber(@PathVariable String name, @PathVariable int number, @PathVariable int page, Principal principal) {
		List<Collection> collections;
		System.out.println("Getting page " + page + " of collections by name " + name + " limited to " + number);
		if(principal == null)
		{
			System.out.println("Logged out");
			collections = collectionDAO.getCollections(name, number, page);
		}
		else 
		{
			System.out.println("Logged in");
			collections = collectionDAO.getCollections(name, number, page, userDAO.findIdByUsername(principal.getName()));
		}
		System.out.println("Returning " + collections.size() + " collections");
		return new DataWrapper().setData(new Container().setResultsList(collections).setCount(collections.size()).setTotal(collectionDAO.getCollections("", Integer.MAX_VALUE, 0).size()));
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{number}/{page}", method = RequestMethod.GET)
    public DataWrapper getCollections(@PathVariable int number, @PathVariable int page, Principal principal) {
		List<Collection> collections;
		System.out.println("Getting page " + page + " of collections limited to " + number);
		if(principal == null)
		{
			System.out.println("Logged out");
			collections = collectionDAO.getCollections("", number, page);
		}
		else 
		{
			System.out.println("Logged in");
			collections = collectionDAO.getCollections("", number, page, userDAO.findIdByUsername(principal.getName()));
		}
		System.out.println("Returning " + collections.size() + " collections");
		return new DataWrapper().setData(new Container().setResultsList(collections).setCount(collections.size()).setTotal(collectionDAO.getCollections("", Integer.MAX_VALUE, 0).size()));
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/owner/{username}", method = RequestMethod.GET)
    public DataWrapper getCollectionsByUsername(@PathVariable String username, Principal principal) {
		List<Collection> collections;
		System.out.println("Getting " + username + "'s collections");
		if(principal == null || (!userDAO.friendExists(userDAO.findIdByUsername(username), userDAO.findIdByUsername(principal.getName())) && !principal.getName().equals(username)))
		{
			System.out.println("Logged out");
			collections = collectionDAO.getCollectionsByUser(userDAO.findIdByUsername(username), true);
		}
		else
		{
			System.out.println("Logged in");
			collections = collectionDAO.getCollectionsByUser(userDAO.findIdByUsername(username), false);
		}
		System.out.println("Returning " + collections.size() + " collections");
		return new DataWrapper().setData(new Container().setResultsList(collections).setCount(collections.size()).setTotal(collections.size()));
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/mycollections/{username}", method = RequestMethod.GET)
    public DataWrapper getCollectionsByUsernameOrSubscribed(@PathVariable String username, Principal principal) {
		List<Collection> collections;
		System.out.println("Getting " + username + "'s collections");
		if(principal == null)
		{
			System.out.println("Logged out");
			collections = collectionDAO.getCollectionsByUser(userDAO.findIdByUsername(username), true);
			collections.addAll(collectionDAO.getPublicCollectionsBySubscription(true, userDAO.findIdByUsername(username)));
		}
		else 
		{
			System.out.println("Logged in");
			System.out.println("Subscribed to " + collectionDAO.getPublicCollectionsBySubscription(false, userDAO.findIdByUsername(username)).size() + " collections");
			collections = collectionDAO.getCollectionsByUser(userDAO.findIdByUsername(username), false);
			collections.addAll(collectionDAO.getPublicCollectionsBySubscription(false, userDAO.findIdByUsername(username)));
		}
		System.out.println("Returning " + collections.size() + " collections");
		return new DataWrapper().setData(new Container().setResultsList(collections).setCount(collections.size()).setTotal(collections.size()));
    }
	
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/public", method = RequestMethod.GET)
    public List<Collection> getPublicCollections(Principal principal) {
        List<Collection> collections = collectionDAO.getCollections(true);
        //System.out.println(principal.getName());
        return collections;
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/public/{limit}", method = RequestMethod.GET)
    public List<Collection> getPublicCollectionsLimited(@PathVariable int limit, Principal principal) {
        List<Collection> collections = collectionDAO.getCollections(true);
        if(collections.size() > limit)
        {
        	Collections.shuffle(collections);
        	collections = collections .subList(0,4);
        }
        return collections;
    }
	
	@CrossOrigin
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/{collectionId}", method = RequestMethod.GET)
    public Collection getPublicCollectionById(@PathVariable int collectionId, Principal principal){
        Collection collection = collectionDAO.getCollectionByID(collectionId, false);
        if(collection == null)
        	throw new ResponseStatusException(
        	          HttpStatus.NOT_FOUND, "Collection Not Found");
        if(collection.isPublic() || (principal == null && collection.isPublic()) || ( principal != null && (verifyUser(principal, collectionId) || userDAO.friendExists((int)collectionDAO.getCollectionByID(collectionId, false).getUserID(), userDAO.findIdByUsername(principal.getName())))))
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
		{
			Field comic = MarvelAPIController.Comic.getComic(comicId).getData().getResults()[0];
			System.out.println("Adding comic " + comic.getTitle() + " to Collection " + collectionDAO.getCollectionByID(collectionId, false).getName());
			collectionDAO.addComic(comic, collectionId);
		}
		else 
			throw new ResponseStatusException(
	      	          HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
    }
	
	
	@ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public void addComic(@RequestBody Comment comment, Principal principal) {
		collectionDAO.addComment(comment);
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value = "/{collectionId}/comments", method = RequestMethod.GET)
	public List<Comment> getComments(@PathVariable int collectionId)
	{
		return collectionDAO.getComments(collectionId);
	}	
	
	@RequestMapping(value = "/comment/delete/{commentId}", method = RequestMethod.POST)
	public void deleteComment(@PathVariable int commentId)
	{
		 collectionDAO.deleteComment(commentId);
	}	
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{collectionId}/remove/{comicId}", method = RequestMethod.POST)
	public void deleteComic(@PathVariable int comicId, @PathVariable int collectionId, Principal principal) {
		if(verifyUser(principal, collectionId))
		{
			System.out.println("Deleting comic by id " + comicId + " from Collections by id " + collectionId);
			collectionDAO.deleteComic(collectionId, comicId);
		}
		else 
			throw new ResponseStatusException(
		      	          HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
	}
		
	
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/thumbnail/{collectionId}", method = RequestMethod.GET)
	public String getThumbnail(@PathVariable int collectionId, Principal principal) {
		//if(verifyUser(principal, collectionId))
		String thumbnail = collectionDAO.getThumbnail(collectionId);
		System.out.println("Getting thumbnail URL from collection by id " + collectionId + ". Returning thumbnail " + thumbnail);
		return thumbnail;
		//else 
			//throw new ResponseStatusException(
		      	          //HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/subscribe/{collectionId}", method = RequestMethod.POST)
	public void subscribe(@PathVariable int collectionId, Principal principal) 
	{
		System.out.println("Subscribing user " + principal.getName() + " to collection by id " + collectionId);
		userDAO.subscribe(userDAO.findIdByUsername(principal.getName()), collectionId);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/unsubscribe/{collectionId}", method = RequestMethod.POST)
	public void unSubscribe(@PathVariable int collectionId, Principal principal) 
	{
		System.out.println("Unsubscribing user " + principal.getName() + " from collection by id " + collectionId);
		userDAO.unSubscribe(userDAO.findIdByUsername(principal.getName()), collectionId);
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/delete/{collectionId}", method = RequestMethod.POST)
	public void removeCollection(@PathVariable int collectionId, Principal principal) {
		if(verifyUser(principal, collectionId))
		{
			System.out.println("Deleting collection by id " + collectionId);
			collectionDAO.removeCollection(collectionId);
		}
		else 
			throw new ResponseStatusException(
	      	          HttpStatus.UNAUTHORIZED, "You cannot delete others' collections");
	}
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void addCollection(@RequestBody Collection collection, Principal principal) {
		if(collection != null)
			System.out.println("Attempting to create collection " + collection.getName() + " with values:\nuserId: " + collection.getUserID() + "\nisPublic: " + collection.isPublic());
		else 
			System.out.println("Collection posted is null");
		
		if(collection == null || collection.getName() == null)
			throw new ResponseStatusException(
      	          HttpStatus.BAD_REQUEST, "Empty Request");
		else
		{
			System.out.println("Creating collection " + collection.getName() + " as user " + principal.getName());
			collectionDAO.addCollection(collection.setDateCreated(Date.valueOf( LocalDate.now())).setUserID(userDAO.findIdByUsername(principal.getName())));
		}
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
        if(collection.isPublic() || (principal == null && collection.isPublic()) || (principal != null && verifyUser(principal, collectionId)))
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
