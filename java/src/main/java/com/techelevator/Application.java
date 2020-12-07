package com.techelevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.techelevator.controller.MarvelAPIController;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
    	MarvelAPIController marvelAPI = new MarvelAPIController();
    	String response = marvelAPI.getThumbnailURL(21324);
    	System.out.println(response);
    	System.out.println(MarvelAPIController.generateURL("public/comics"));
    	System.exit(0);
    	
        SpringApplication.run(Application.class, args);
    }

}
