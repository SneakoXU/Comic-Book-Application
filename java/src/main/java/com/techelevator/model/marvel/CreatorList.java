package com.techelevator.model.marvel;

public class CreatorList {
	
	private int available;
	private int returned;
	private String collectionURI;
	private CreatorSummary[] items;
	public CreatorList() {
	}
	public CreatorList(int available, int returned, String collectionURI,
	        CreatorSummary[] items) {
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
	public CreatorSummary[] getItems() {
		return items;
	}
	public void setItems(CreatorSummary[] items) {
		this.items = items;
	}
	
	

}
