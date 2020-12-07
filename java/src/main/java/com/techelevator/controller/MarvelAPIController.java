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
import com.techelevator.model.marvel.containers.ComicDataContainer;
import com.techelevator.model.marvel.fields.Comic;
import com.techelevator.model.marvel.fields.Image;
import com.techelevator.model.marvel.wrappers.ComicDataWrapper;

public class MarvelAPIController 
{
	private static final String API_BASE_URL = "https://gateway.marvel.com/v1/";
	private static final String API_PUBLIC_HASH = "7e740b39912d2877c3f183a21d4d53b1";
	private static final String API_PRIVATE_HASH = "03aba47b1aa53fece1a0c748f1af03004ed6622a";

	public static RestTemplate restTemplate = new RestTemplate();
	public static ObjectMapper mapper = new ObjectMapper();
	
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
	
	
	
	
}
