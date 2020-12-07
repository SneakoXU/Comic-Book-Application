package com.techelevator.model.marvel.fields;

import java.io.IOException;

import org.springframework.http.ResponseEntity;

import com.techelevator.controller.MarvelAPIController;

public class Field {
	
	public static <T> T getDataWrapper(String s, Class<T> type)
	{
		ResponseEntity response = MarvelAPIController.restTemplate.getForEntity(MarvelAPIController.generateURL(s + (s.indexOf('?')==-1?'?':'&')), String.class);
		try 
		{
			return MarvelAPIController.mapper.readValue(MarvelAPIController.mapper.createParser((String)response.getBody()) , type);
		}
		catch (IOException e) 
		{
			System.out.println("Could not parse JSON");
			e.printStackTrace();
		}
		return null;
	}
}
