package com.techelevator.model.marvel.fields;

import com.techelevator.model.marvel.lists.ComicList;
import com.techelevator.model.marvel.lists.EventList;
import com.techelevator.model.marvel.lists.SeriesList;
import com.techelevator.model.marvel.lists.StoryList;

public class Creator {
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffix;
	private String fullName;
	private Date modified;
	private String resourceURI;
	private URL[] urls;
	private Image thumbnail;
	private SeriesList series;
	private StoryList stories;
	private ComicList comics;
	private EventList events;
	public Creator() {
	}
	public Creator(int id, String firstName, String middleName, String lastName,
	        String suffix, String fullName, Date modified, String resourceURI,
	        URL[] urls, Image thumbnail, SeriesList series, StoryList stories,
	        ComicList comics, EventList events) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.fullName = fullName;
		this.modified = modified;
		this.resourceURI = resourceURI;
		this.urls = urls;
		this.thumbnail = thumbnail;
		this.series = series;
		this.stories = stories;
		this.comics = comics;
		this.events = events;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public String getResourceURI() {
		return resourceURI;
	}
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}
	public URL[] getUrls() {
		return urls;
	}
	public void setUrls(URL[] urls) {
		this.urls = urls;
	}
	public Image getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}
	public SeriesList getSeries() {
		return series;
	}
	public void setSeries(SeriesList series) {
		this.series = series;
	}
	public StoryList getStories() {
		return stories;
	}
	public void setStories(StoryList stories) {
		this.stories = stories;
	}
	public ComicList getComics() {
		return comics;
	}
	public void setComics(ComicList comics) {
		this.comics = comics;
	}
	public EventList getEvents() {
		return events;
	}
	public void setEvents(EventList events) {
		this.events = events;
	}
	
	
	
	

}
