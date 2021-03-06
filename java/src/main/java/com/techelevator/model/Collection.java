package com.techelevator.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.marvel.fields.Field;

public class Collection extends Field{
	
	private List<Field> comicBooks;
	private long userID;
	private boolean isPublic;
	private Date dateCreated;
	private String name;
	
	public Collection() {}
	
	public Collection(int id, List<Field> comicBooks, long userID, boolean isPublic,
	        Date dateCreated, String name) {
		super();
		super.setId(id);
		this.comicBooks = comicBooks;
		this.userID = userID;
		this.isPublic = isPublic;
		this.dateCreated = dateCreated;
		this.name = name;
	}

	public List<Field> getComicBookIDs() {
		return comicBooks;
	}

	public void setComicBookIDs(List<Long> comicBookIDs) {
		this.comicBooks = comicBooks;
	}

	public long getUserID() {
		return userID;
	}

	public Collection setUserID(long userID) {
		this.userID = userID;
		return this;
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

	public Collection setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection addComics(List<Field> comicBooks)
	{
		this.comicBooks.addAll(comicBooks);
		return this;
	}
	
	public Collection addFriends(Field comicBook)
	   {
		   this.comicBooks.add(comicBook);
		   return this;
	   }
	
	public Collection removeComics(List<Field> comicBooks)
	{
		for(int i = 0; i < this.comicBooks.size(); i++)
			if(comicBooks.contains(this.comicBooks.get(i)))
				this.comicBooks.remove(i--);
		return this;
	}
	
	public Collection removeComics(Field comicBookID)
	{
		try 
	   	{
			comicBooks.remove(comicBooks.indexOf(comicBookID));
	   	} 
	   	catch (Exception e) 
	   	{
			System.out.println("Could not remove comic book " + comicBookID);
	   	}

		return this;
	}
	
}
