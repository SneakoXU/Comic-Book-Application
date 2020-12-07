package com.techelevator.model.marvel.lists;

import com.techelevator.model.marvel.summaries.CharacterSummary;

public class CharacterList {
	
	private int available;
	private int returned;
	private String collectionURI;
	private CharacterSummary[] items;
	public CharacterList() {
		
	}
	public CharacterList(int available, int returned, String collectionURI,
	        CharacterSummary[] items) {
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
	public CharacterSummary[] getItems() {
		return items;
	}
	public void setItems(CharacterSummary[] items) {
		this.items = items;
	}
	

}
