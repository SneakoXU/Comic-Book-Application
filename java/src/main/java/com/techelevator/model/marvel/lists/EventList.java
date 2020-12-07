package com.techelevator.model.marvel.lists;

import com.techelevator.model.marvel.summaries.EventSummary;

public class EventList {

	private int available;
	private int returned;
	private String collectionURI;
	private EventSummary[] items;
	public EventList() {
	}
	public EventList(int available, int returned, String collectionURI,
	        EventSummary[] items) {
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
	public EventSummary[] getItems() {
		return items;
	}
	public void setItems(EventSummary[] items) {
		this.items = items;
	}
	
	
	
}
