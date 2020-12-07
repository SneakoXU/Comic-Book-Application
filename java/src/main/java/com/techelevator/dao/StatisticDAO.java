package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Collection;
import com.techelevator.model.Statistics;

public interface StatisticDAO 
{
	long getCollectionsByCreator(long id);//for getting number of collections a user has made
	long getCollectionsByAuthor(long id);//for getting number of collections with an author
	long getCollectionsByCharacter(long id);//for getting number of collections with a character
	long getCollectionsByComic(long id); //for the most popular comic
	
	long getUsersByCollection(long id); //for the most popular collection
	
	long getComicsByCreator(long id);//for getting number of comics a user has made. For comic ownership leaderboards
	long getComicsByAuthor(long id);//for getting number of comics with an author. For author popularity
	long getComicsByCharacter(long id);//gets the number of comics featuring a character. For character popularity
}	
