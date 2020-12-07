package com.techelevator.model.marvel;

public class URL {
	
	private String type;
	private String url;
	public URL() {
	}
	public URL(String type, String url) {
		super();
		this.type = type;
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
