package com.techelevator.model.marvel;

public class Series 
{
	private String resourceURI;
	private String name;
	public Series() {
	}
	public Series(String resourceURI, String name) {
		super();
		this.resourceURI = resourceURI;
		this.name = name;
	}
	public String getResourceURI() {
		return resourceURI;
	}
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
