package com.techelevator.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.model.marvel.fields.DataWrapper;
import com.techelevator.model.marvel.fields.Image;

public class MarvelAPIController 
{
	private static final String API_BASE_URL = "https://gateway.marvel.com/v1/";
	private static final String API_PUBLIC_HASH = "7e740b39912d2877c3f183a21d4d53b1";
	private static final String API_PRIVATE_HASH = "03aba47b1aa53fece1a0c748f1af03004ed6622a";

	public static SortedMap<String, String> ILLEGAL_CHARACTERS = new TreeMap<String, String>();
	static 
	{

		//ILLEGAL_CHARACTERS.put("%" , "%25");
		ILLEGAL_CHARACTERS.put(" " , "+");
		ILLEGAL_CHARACTERS.put("!" , "%21");
		ILLEGAL_CHARACTERS.put("@" , "%40");
		ILLEGAL_CHARACTERS.put("#" , "%23");
		ILLEGAL_CHARACTERS.put("//$" , "%24");
		//ILLEGAL_CHARACTERS.put("^" , "%5E");
		ILLEGAL_CHARACTERS.put("&" , "%26");
		ILLEGAL_CHARACTERS.put("\"" , "%27");
		ILLEGAL_CHARACTERS.put("\\(" , "%28");
		ILLEGAL_CHARACTERS.put("\\)" , "%29");
		ILLEGAL_CHARACTERS.put("," , "%2C");
	}
	
	public static RestTemplate restTemplate = new RestTemplate();
	public static ObjectMapper mapper = new ObjectMapper();
	
	//black box
	public static String generateURL(String uri)
	{
		long time =System.currentTimeMillis();

		try
		{
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] array = md.digest((time + API_PRIVATE_HASH + API_PUBLIC_HASH).getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < array.length; ++i) 
	        {
	        	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	        }
	        System.out.println("Querying Marvel API using link: " + API_BASE_URL+uri+"ts="+time+"&apikey="+API_PUBLIC_HASH+"&hash="+sb.toString() + "\n");
	        return API_BASE_URL+uri+"ts="+time+"&apikey="+API_PUBLIC_HASH+"&hash="+sb.toString();
	    } 
		catch (NoSuchAlgorithmException e) 
		{}
	    return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static DataWrapper getDataWrapper(String s)
	{
		try 
		{
			ResponseEntity response = MarvelAPIController.restTemplate.getForEntity(MarvelAPIController.generateURL(s + (s.indexOf('?')==-1?'?':'&')), String.class);
			return MarvelAPIController.mapper.readValue(MarvelAPIController.mapper.createParser((String)response.getBody()) , DataWrapper.class);
		}
		catch (HttpClientErrorException e) {
			System.out.println("404- Not found");
		}
		catch (IOException e) 
		{
			System.out.println("Could not parse JSON");
			e.printStackTrace();
		}
		return null;
	}
	
	public static abstract class Character
	{
		public static DataWrapper getCharacter(long id) 
		{
			return getDataWrapper("public/characters/"+id);
		}
		
		public static DataWrapper getCharacter() 
		{
			return getDataWrapper("public/characters");
		}
		
		public static DataWrapper getCharactersByName(String name) 
		{
			return getDataWrapper("public/characters?nameStartsWith="+name);
		}
		
		public static DataWrapper getCharactersByComic(int id) 
		{
			return getDataWrapper("public/characters?comics="+id);
		}
		public static DataWrapper getComicsBySeries(int id) 
		{
			return getDataWrapper("public/comics?series="+id);
		}
		public static String getThumbnailURL(int characterID)
		{
			try
			{
				Image comicImage = getCharacter(characterID).getData().getResults()[0].getThumbnail();
				return comicImage.getPath()+"." + comicImage.getExtension();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return "err";
		}
	}
	
	public static abstract class Comic
	{
		public static DataWrapper getComic(long id) 
		{
			System.out.println("Getting comic by id " + id);
			return getDataWrapper("public/comics/"+id);
		}
		
		public static DataWrapper getAllComics() 
		{
			return getDataWrapper("public/comics");
		}
		
		public static DataWrapper getComicsByName(String name) 
		{

			name = name.replaceAll("%", "%25");
			for(Entry<String,String> entry : ILLEGAL_CHARACTERS.entrySet())
			{
				name = name.replaceAll(entry.getKey(), entry.getValue());
			}
			System.out.println("Getting 30 comics by name " + name);
			return getDataWrapper("public/comics?"+ (name.equals("0")?"": "titleStartsWith="+name + "&") + "limit=30");
		}
		
		public static DataWrapper getComicsByName(String name, int pageNumber) 
		{
			if(name.equals("0"))
				name="";
			name = name.replaceAll("%", "%25");
			for(Entry<String,String> entry : ILLEGAL_CHARACTERS.entrySet())
			{
				
				name = name.replaceAll(entry.getKey(), entry.getValue());
			}
			System.out.println("Getting page " + pageNumber + " of comics by name " + name);
			return getDataWrapper("public/comics?"+ (name.equals("0")?"": "titleStartsWith="+name + "&") + "limit=30&offset="+(pageNumber*30));
		}
		
		public static DataWrapper getComicsByCharacter(int id) 
		{
			return getDataWrapper("public/comics?characters="+id);
		}
		public static DataWrapper getComicsBySeries(int id) 
		{
			return getDataWrapper("public/comics?series="+id);
		}
		public static String getThumbnailURL(int comicID)
		{
			try
			{
				Image comicImage =getComic(comicID).getData().getResults()[0].getThumbnail();
				return comicImage.getPath()+"." + comicImage.getExtension();
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return "err";
		}
	}
	
	public static abstract class Creator
	{
		public DataWrapper getAuthorByComicId(int id)
		{
			return getDataWrapper("public/creators?comics="+id);
		}
	}
	
	public static abstract class Event
	{
		
	}
	
	public static abstract class Series
	{
		
	}
	
	public static abstract class Story
	{
		
	}
	
	
	
	
}
