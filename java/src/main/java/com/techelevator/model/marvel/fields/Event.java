package com.techelevator.model.marvel.fields;

import com.techelevator.model.marvel.lists.CharacterList;
import com.techelevator.model.marvel.lists.ComicList;
import com.techelevator.model.marvel.lists.CreatorList;
import com.techelevator.model.marvel.lists.SeriesList;
import com.techelevator.model.marvel.lists.StoryList;
import com.techelevator.model.marvel.summaries.EventSummary;

public class Event {
	
	private int id;
	private String title;
	private String description;
	private String resourceURI;
	private URL[] urls;
	private Date modified;
	private Date start;
	private Date end;
	private Image thumbnail;
	private ComicList comics;
	private StoryList stories;
	private SeriesList series;
	private CharacterList characters;
	private CreatorList creators;
	private EventSummary next;
	private EventSummary previous;
	public Event() {
	}
	public Event(int id, String title, String description, String resourceURI,
	        URL[] urls, Date modified, Date start, Date end, Image thumbnail,
	        ComicList comics, StoryList stories, SeriesList series,
	        CharacterList characters, CreatorList creators, EventSummary next,
	        EventSummary previous) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.resourceURI = resourceURI;
		this.urls = urls;
		this.modified = modified;
		this.start = start;
		this.end = end;
		this.thumbnail = thumbnail;
		this.comics = comics;
		this.stories = stories;
		this.series = series;
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
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
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
	public SeriesList getSeries() {
		return series;
	}
	public void setSeries(SeriesList series) {
		this.series = series;
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
	public EventSummary getNext() {
		return next;
	}
	public void setNext(EventSummary next) {
		this.next = next;
	}
	public EventSummary getPrevious() {
		return previous;
	}
	public void setPrevious(EventSummary previous) {
		this.previous = previous;
	}
	
	
	
	

}
