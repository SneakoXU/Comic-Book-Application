package com.techelevator.model;

import java.sql.Date;
import java.util.List;

public class Collection {
	
	private List<Long> comicBookIDs;
	private long userID;
	private boolean isPublic;
	private Date dateCreated;
	private String name;
	


	public Collection(List<Long> comicBookIDs, long userID, boolean isPublic,
	        Date dateCreated, String name) {
		super();
		this.comicBookIDs = comicBookIDs;
		this.userID = userID;
		this.isPublic = isPublic;
		this.dateCreated = dateCreated;
		this.name = name;
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
	
	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
