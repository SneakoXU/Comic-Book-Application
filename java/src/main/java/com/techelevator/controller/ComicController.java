package com.techelevator.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.marvel.fields.DataWrapper;

@RestController
@RequestMapping("/comics")
@PreAuthorize("isAuthenticated()")
public class ComicController 
{
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
}
