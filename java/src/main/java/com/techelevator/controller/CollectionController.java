package com.techelevator.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping(value="", method = RequestMethod.GET)
    public List<Collection> getpublicCollections(Principal principal) {
		System.out.println("RANNNNN");
        List<Collection> collections = collectionDAO.getPublicCollections();
        return collections;
    }

	
}
