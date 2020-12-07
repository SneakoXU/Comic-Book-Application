package com.techelevator.model.marvel.fields;

import com.techelevator.model.marvel.lists.CharacterList;
import com.techelevator.model.marvel.lists.ComicList;
import com.techelevator.model.marvel.lists.CreatorList;
import com.techelevator.model.marvel.lists.EventList;
import com.techelevator.model.marvel.lists.StoryList;
import com.techelevator.model.marvel.summaries.SeriesSummary;

public class Series {
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private URL[] urls;
	private int startYear;
	private int endYear;
	private String rating;
	private Date modified;
	private Image thumbnail;
	private ComicList comics;
	private StoryList stories;
	private EventList events;
	private CharacterList characters;
	private CreatorList creators;
	private SeriesSummary next;
	private SeriesSummary previous;
	public Series() {
	}
	public Series(int id, String title, String description, String resourceURI,
	        URL[] urls, int startYear, int endYear, String rating,
	        Date modified, Image thumbnail, ComicList comics, StoryList stories,
	        EventList events, CharacterList characters, CreatorList creators,
	        SeriesSummary next, SeriesSummary previous) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.resourceURI = resourceURI;
		this.urls = urls;
		this.startYear = startYear;
		this.endYear = endYear;
		this.rating = rating;
		this.modified = modified;
		this.thumbnail = thumbnail;
		this.comics = comics;
		this.stories = stories;
		this.events = events;
		this.characters = characters;
		this.creators = creators;
		this.next = next;
		this.previous = previous;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Image getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}
	public ComicList getComics() {
		return comics;
	}
	public void setComics(ComicList comics) {
		this.comics = comics;
	}
	public StoryList getStories() {
		return stories;
	}
	public void setStories(StoryList stories) {
		this.stories = stories;
	}
	public EventList getEvents() {
		return events;
	}
	public void setEvents(EventList events) {
		this.events = events;
	}
	public CharacterList getCharacters() {
		return characters;
	}
	public void setCharacters(CharacterList characters) {
		this.characters = characters;
	}
	public CreatorList getCreators() {
		return creators;
	}
	public void setCreators(CreatorList creators) {
		this.creators = creators;
	}
	public SeriesSummary getNext() {
		return next;
	}
	public void setNext(SeriesSummary next) {
		this.next = next;
	}
	public SeriesSummary getPrevious() {
		return previous;
	}
	public void setPrevious(SeriesSummary previous) {
		this.previous = previous;
	}
	
	
	
	
}
