package com.techelevator.model.marvel.lists;

import com.techelevator.model.marvel.summaries.StorySummary;

public class StoryList {
	
	private int available;
	private int returned;
	private String collectionURI;
	private StorySummary[] items;
	public StoryList() {
	}
	public StoryList(int available, int returned, String collectionURI,
	        StorySummary[] items) {
		super();
		this.available = available;
		this.returned = returned;
		this.collectionURI = collectionURI;
		this.items = items;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getReturned() {
		return returned;
	}
	public void setReturned(int returned) {
		this.returned = returned;
	}
	public String getCollectionURI() {
		return collectionURI;
	}
	public void setCollectionURI(String collectionURI) {
		this.collectionURI = collectionURI;
	}
	public StorySummary[] getItems() {
		return items;
	}
	public void setItems(StorySummary[] items) {
		this.items = items;
	}
	
	

}
