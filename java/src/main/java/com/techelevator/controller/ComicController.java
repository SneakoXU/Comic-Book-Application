package com.techelevator.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.CollectionDAO;
import com.techelevator.dao.ComicDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.marvel.fields.DataWrapper;

@RestController
@RequestMapping("/comics")
@PreAuthorize("isAuthenticated()")
public class ComicController 
{
	
	private UserDAO userDAO;
	private ComicDAO comicDAO;
	
	public ComicController(ComicDAO comicDAO, UserDAO userDAO) {
		this.comicDAO = comicDAO;
		this.userDAO = userDAO;
	}
	
	@CrossOrigin
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/query/title/{name}", method = RequestMethod.GET)
    public DataWrapper getTransfer(@PathVariable String name, Principal principal) 
	{
        return MarvelAPIController.Comic.getComicsByName(name);
    }
	
	@PreAuthorize("permitAll()")
	@RequestMapping(value="/query/id/{id}", method = RequestMethod.GET)
    public DataWrapper getTransfer(@PathVariable int id, Principal principal) 
	{
        return MarvelAPIController.Comic.getComic(id);
    }
	
	@PreAuthorize("permitAll()")
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/thumbnail/{comicId}", method = RequestMethod.GET)
	public String getThumbnail(@PathVariable int comicId, Principal principal) {
		//if(verifyUser(principal, collectionId))
			return comicDAO.getThumbnail(comicId);
		//else 
			//throw new ResponseStatusException(
		      	          //HttpStatus.UNAUTHORIZED, "You cannot edit others' collections");
	}
	
	
	
	
}
