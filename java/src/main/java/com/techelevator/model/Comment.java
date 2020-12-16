package com.techelevator.model;

public class Comment 
{
	private int id;
	private int commenter_id;
	private String commenter_name;
	private int collection_id;
	private int likes;
	private String text;
	
	public Comment() {}

	public Comment(int id, int commenter_id, String commenter_name, int collection_id, int likes,
	        String text) {
		super();
		this.id = id;
		this.commenter_id = commenter_id;
		this.commenter_name = commenter_name;
		this.collection_id = collection_id;
		this.likes = likes;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommenter_id() {
		return commenter_id;
	}

	public void setCommenter_id(int commenter_id) {
		this.commenter_id = commenter_id;
	}

	public int getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getCommenter_name()
	{
		return commenter_name;
	}
	
	public void setCommenter_name(String name)
	{
		this.commenter_name = name;
	}
	
	

}
