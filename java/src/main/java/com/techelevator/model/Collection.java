package com.techelevator.model;

import java.util.List;

public class Collection {
	
	private List<Long> comicBookIDs;
	private long userID;
	
	public Collection(long userID, List<Long> comicBookIDs)
	{
		this.userID = userID;
		this.comicBookIDs = comicBookIDs;
	}

	public List<Long> getComicBookIDs() {
		return comicBookIDs;
	}

	public void setComicBookIDs(List<Long> comicBookIDs) {
		this.comicBookIDs = comicBookIDs;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}
	
	public Collection addComics(List<Long> comicBookIDs)
	{
		this.comicBookIDs.addAll(comicBookIDs);
		return this;
	}
	
	public Collection addFriends(long comicBookID)
	   {
		   this.comicBookIDs.add(comicBookID);
		   return this;
	   }
	
	public Collection removeComics(List<Long> comicBookIDs)
	{
		for(int i = 0; i < this.comicBookIDs.size(); i++)
			if(comicBookIDs.contains(this.comicBookIDs.get(i)))
				this.comicBookIDs.remove(i--);
		return this;
	}
	
	public Collection removeComics(long comicBookID)
	{
		try 
	   	{
			comicBookIDs.remove(comicBookIDs.indexOf(comicBookID));
	   	} 
	   	catch (Exception e) 
	   	{
			System.out.println("Could not remove comic book " + comicBookID);
	   	}

		return this;
	}
	
}
