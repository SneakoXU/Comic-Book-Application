package com.techelevator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.techelevator.controller.MarvelAPIController.*;
import com.techelevator.model.marvel.fields.Field;


@SpringBootApplication
public class Application {

    public static void main(String[] args) 
    {
    	//for(Field c : Comic.getComicsByName("Spi").getData().getResults())
    	//	System.out.println(c.getTitle() + "\t\t" + c.getDescription() + "\t\t" + c.getThumbnail().getPath() + "." + c.getThumbnail().getExtension());

    	//System.exit(0);
    	
        SpringApplication.run(Application.class, args);
    }

}
