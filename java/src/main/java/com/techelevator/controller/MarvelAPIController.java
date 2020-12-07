package com.techelevator.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Stack;

import org.apache.tomcat.jni.Time;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.marvel.Comic;
import com.techelevator.model.marvel.ComicDataContainer;
import com.techelevator.model.marvel.Image;
import com.techelevator.model.marvel.CharacterDataWrapper;

public class MarvelAPIController 
{
	private static final String API_BASE_URL = "https://gateway.marvel.com/v1/";
	private static final String API_PUBLIC_HASH = "7e740b39912d2877c3f183a21d4d53b1";
	private static final String API_PRIVATE_HASH = "03aba47b1aa53fece1a0c748f1af03004ed6622a";

	private RestTemplate restTemplate = new RestTemplate();
	private ObjectMapper mapper = new ObjectMapper();
	
	public static String generateURL(String uri)
	{
		long time =System.currentTimeMillis();

		try
		{
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest((time + API_PRIVATE_HASH + API_PUBLIC_HASH).getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) 
	        {
	        	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	        }
	        return API_BASE_URL+uri+"ts="+time+"&apikey="+API_PUBLIC_HASH+"&hash="+sb.toString();
	    } 
		catch (java.security.NoSuchAlgorithmException e) 
		{}
	    return null;
	}
	
	private CharacterDataWrapper returnCharacterDataWrapper(String s)
	{
		ResponseEntity response = restTemplate.getForEntity(generateURL(s + (s.indexOf('?')==-1?'?':'&')), String.class);
		try 
		{
			return mapper.readValue(mapper.createParser((String)response.getBody()) , CharacterDataWrapper.class);
		}
		catch (IOException e) 
		{
			System.out.println("Could not parse JSON");
			e.printStackTrace();
		}
		return null;
	}
	
	public CharacterDataWrapper getComic(long id) 
	{
		return returnCharacterDataWrapper("public/comics/"+id);
	}
	
	public CharacterDataWrapper getComics() 
	{
		return returnCharacterDataWrapper("public/comics");
	}
	
	public CharacterDataWrapper getComicsByName(String name) 
	{
		return returnCharacterDataWrapper("public/comics?titleStartsWith="+name);
	}
	
	public CharacterDataWrapper getComicsByCharacter(int id) 
	{
		return returnCharacterDataWrapper("public/comics?characters="+id);
	}
	public CharacterDataWrapper getComicsBySeries(int id) 
	{
		return returnCharacterDataWrapper("public/comics?series="+id);
	}
	public String getThumbnailURL(int comicID)
	{
		Image comicImage =getComic(comicID).getData().getResults()[0].getThumbnail();
		try
		{
			return comicImage.getPath()+"." + comicImage.getExtension();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "err";
		
		
	}
	
	
}
