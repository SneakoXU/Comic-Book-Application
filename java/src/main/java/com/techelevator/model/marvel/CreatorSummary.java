package com.techelevator.model.marvel;

public class CreatorSummary {
	
	private String resourceURI;
	private String name;
	private String role;
	public CreatorSummary() {
	}
	public CreatorSummary(String resourceURI, String name, String role) {
		super();
		this.resourceURI = resourceURI;
		this.name = name;
		this.role = role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
