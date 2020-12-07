package com.techelevator.model.marvel.fields;

public class ComicDate {

	private String type;
	private String date;
	public ComicDate() {
	}
	public ComicDate(String type, String date) {
		super();
		this.type = type;
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
