package com.techelevator.model.marvel.fields;

import com.techelevator.model.marvel.lists.CharacterList;
import com.techelevator.model.marvel.lists.ComicList;
import com.techelevator.model.marvel.lists.CreatorList;
import com.techelevator.model.marvel.lists.EventList;
import com.techelevator.model.marvel.lists.SeriesList;
import com.techelevator.model.marvel.summaries.ComicSummary;

public class Story {
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private String type;
	private Date modified;
	private Image thumbnail;
	private ComicList comics;
	private SeriesList series;
	private EventList events;
	private CharacterList characters;
	private CreatorList creators;
	private ComicSummary originalIssue;
	
	public Story() {
	}

	public Story(int id, String title, String description, String resourceURI,
	        String type, Date modified, Image thumbnail, ComicList comics,
	        SeriesList series, EventList events, CharacterList characters,
	        CreatorList creators, ComicSummary originalIssue) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.resourceURI = resourceURI;
		this.type = type;
		this.modified = modified;
		this.thumbnail = thumbnail;
		this.comics = comics;
		this.series = series;
		this.events = events;
		this.characters = characters;
		this.creators = creators;
		this.originalIssue = originalIssue;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public SeriesList getSeries() {
		return series;
	}

	public void setSeries(SeriesList series) {
		this.series = series;
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

	public ComicSummary getOriginalIssue() {
		return originalIssue;
	}

	public void setOriginalIssue(ComicSummary originalIssue) {
		this.originalIssue = originalIssue;
	}
	
}
