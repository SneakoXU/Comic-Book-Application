package com.techelevator.model.marvel.fields;

public class List 
{
	private int available;
	private int returned;
	public String name;
	private String collectionURI;
	private String resourceURI;
	private Summary[] items;
	public List() {
	}

	public List(int available, int returned, String name, String collectionURI,
	        String resourceURI, Summary[] items) {
		super();
		this.available = available;
		this.returned = returned;
		this.name = name;
		this.collectionURI = collectionURI;
		this.resourceURI = resourceURI;
		this.items = items;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String getResourceURI() {
		return resourceURI;
	}
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	public Summary[] getItems() {
		return items;
	}
	public void setItems(Summary[] items) {
		this.items = items;
	}
	
	
}
