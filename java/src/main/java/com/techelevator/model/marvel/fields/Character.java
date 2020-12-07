package com.techelevator.model.marvel.fields;

import com.techelevator.model.marvel.lists.ComicList;
import com.techelevator.model.marvel.lists.EventList;
import com.techelevator.model.marvel.lists.SeriesList;
import com.techelevator.model.marvel.lists.StoryList;
import com.techelevator.model.marvel.wrappers.CharacterDataWrapper;
import com.techelevator.model.marvel.wrappers.ComicDataWrapper;

public class Character extends Field{
	private int id;
	private String name;
	private String description;
	private Date modified;
	private String resourceURI;
	private URL[] urls;
	private Image thumbnail;
	private ComicList comics;
	private StoryList stories;
	private EventList events;
	private SeriesList series;
	
	public Character() {
	}
	public Character(int id, String name, String description, Date modified,
	        String resourceURI, URL[] urls, Image thumbnail, ComicList comics,
	        StoryList stories, EventList events, SeriesList series) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.modified = modified;
		this.resourceURI = resourceURI;
		this.urls = urls;
		this.thumbnail = thumbnail;
		this.comics = comics;
		this.stories = stories;
		this.events = events;
		this.series = series;
	}
	
	public static CharacterDataWrapper getCharacter(long id) 
	{
		return getDataWrapper("public/characters/"+id, CharacterDataWrapper.class);
	}
	
	public static CharacterDataWrapper getCharacter() 
	{
		return getDataWrapper("public/characters", CharacterDataWrapper.class);
	}
	
	public static CharacterDataWrapper getCharactersByName(String name) 
	{
		return getDataWrapper("public/characters?titleStartsWith="+name, CharacterDataWrapper.class);
	}
	
	public static CharacterDataWrapper getCharactersByComic(int id) 
	{
		return getDataWrapper("public/characters?comics="+id, CharacterDataWrapper.class);
	}
	public static CharacterDataWrapper getComicsBySeries(int id) 
	{
		return getDataWrapper("public/comics?series="+id, CharacterDataWrapper.class);
	}
	public static String getThumbnailURL(int charactersID)
	{
		Image comicImage = getCharacter(charactersID).getData().getResults()[0].getThumbnail();
		try
		{
			return comicImage.getPath()+"." + comicImage.getExtension();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return "err";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public SeriesList getSeries() {
		return series;
	}
	public void setSeries(SeriesList series) {
		this.series = series;
	}
	
	
	
	

}
