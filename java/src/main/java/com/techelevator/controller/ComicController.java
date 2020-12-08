package com.techelevator.controller;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.marvel.fields.Field;
import com.techelevator.model.marvel.fields.Image;

@RestController
@RequestMapping("/transfers")
@PreAuthorize("isAuthenticated()")
public class ComicController 
{

	
}
