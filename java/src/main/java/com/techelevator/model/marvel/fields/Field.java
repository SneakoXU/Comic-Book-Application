package com.techelevator.model.marvel.fields;

import java.sql.Date;

public class Field {
	
	/**
	 * Used in All
	 */
	private int id;
	/**
	 * Used in Character
	 */
	private String name;
	/**
	 * Used in Character, Comic, Story, Event, Series
	 */
	private String description;
	/**
	 * Used in All
	 */
	private java.sql.Date modified;
	/**
	 * Used in All
	 */
	private String resourceURI;
	/**
	 * Used in Character, Comic, Event, Series, Creator
	 */
	private URL[] urls;
	/**
	 * Used in All
	 */
	private Image thumbnail;
	/**
	 * Used in Character, Story, Event, Series, Creator
	 */
	private List comics;
	/**
	 * Used in Character, Comic, Event, Series, Creator
	 */
	private List stories;
	/**
	 * Used in Character, Story, Comic, Series, Creator
	 */
	private List events;
	/**
	 * Used in Character, Story, Event, Comic, Creator
	 */
	private List series;
	/**
	 * Used in Comic
	 */
	private long digitalId;
	/**
	 * Used in Comic, Story, Event, Series
	 */
	private String title;
	/**
	 * Used in Comic
	 */
	private int issueNumber;
	/**
	 * Used in Comic
	 */
	private String variantDescription;
	/**
	 * Used in Comic
	 */
	private String isbn;
	/**
	 * Used in Comic
	 */
	private String upc;
	/**
	 * Used in Comic
	 */
	private String diamondCode;
	/**
	 * Used in Comic
	 */
	private String ean;
	/**
	 * Used in Comic
	 */
	private String issn;
	/**
	 * Used in Comic
	 */
	private String format;
	/**
	 * Used in Comic
	 */
	private int pageCount;
	/**
	 * Used in Comic
	 */
	private TextObject[] textObjects;
	/**
	 * Used in Comic
	 */
	private Summary[] variants;
	/**
	 * Used in Comic
	 */
	private Summary[] collections;
	/**
	 * Used in Comic
	 */
	private Summary[] collectedIssues;
	/**
	 * Used in Comic
	 */
	private ComicDate[] dates;
	/**
	 * Used in Comic
	 */
	private Price[] prices;
	/**
	 * Used in Comic
	 */
	private Image[] images;
	/**
	 * Used in Comic, Story, Event, Series
	 */
	private List creators;
	/**
	 * Used in Comic, Story, Event, Series
	 */
	private List characters;
	/**
	 * Used in Story
	 */
	private String type;
	/**
	 * Used in Event, Series
	 */
	private Summary next;
	/**
	 * Used in Event, Series
	 */
	private Summary previous;
	/**
	 * Used in Story
	 */
	private Summary originalIssue;
	/**
	 * Used in Series
	 */
	private int startYear;
	/**
	 * Used in Series
	 */
	private int endYear;
	/**
	 * Used in Series
	 */
	private String rating;
	/**
	 * Used in Creator
	 */
	private String firstName;
	/**
	 * Used in Creator
	 */
	private String middleName;
	/**
	 * Used in Creator
	 */
	private String lastName;
	/**
	 * Used in Creator
	 */
	private String suffix;
	/**
	 * Used in Creator
	 */
	private String fullName;

	public Field() {
	}

	
	public Field(int id, String name, String description, Date modified,
	        String resourceURI, URL[] urls, Image thumbnail, List comics,
	        List stories, List events, List series, long digitalId,
	        String title, int issueNumber, String variantDescription,
	        String isbn, String upc, String diamondCode, String ean,
	        String issn, String format, int pageCount, TextObject[] textObjects,
	        Summary[] variants, Summary[] collections,
	        Summary[] collectedIssues, ComicDate[] dates, Price[] prices,
	        Image[] images, List creators, List characters, String type,
	        Summary next, Summary previous, Summary originalIssue,
	        int startYear, int endYear, String rating, String firstName,
	        String middleName, String lastName, String suffix,
	        String fullName) {
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
		this.digitalId = digitalId;
		this.title = title;
		this.issueNumber = issueNumber;
		this.variantDescription = variantDescription;
		this.isbn = isbn;
		this.upc = upc;
		this.diamondCode = diamondCode;
		this.ean = ean;
		this.issn = issn;
		this.format = format;
		this.pageCount = pageCount;
		this.textObjects = textObjects;
		this.variants = variants;
		this.collections = collections;
		this.collectedIssues = collectedIssues;
		this.dates = dates;
		this.prices = prices;
		this.images = images;
		this.creators = creators;
		this.characters = characters;
		this.type = type;
		this.next = next;
		this.previous = previous;
		this.originalIssue = originalIssue;
		this.startYear = startYear;
		this.endYear = endYear;
		this.rating = rating;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.suffix = suffix;
		this.fullName = fullName;
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
		return (description == null || description.length() == 0)? "None": description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public java.sql.Date getModified() {
		return modified;
	}


	public void setModified(java.sql.Date modified) {
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


	public List getComics() {
		return comics;
	}


	public void setComics(List comics) {
		this.comics = comics;
	}


	public List getStories() {
		return stories;
	}


	public void setStories(List stories) {
		this.stories = stories;
	}


	public List getEvents() {
		return events;
	}


	public void setEvents(List events) {
		this.events = events;
	}


	public List getSeries() {
		return series;
	}


	public void setSeries(List series) {
		this.series = series;
	}


	public long getDigitalId() {
		return digitalId;
	}


	public void setDigitalId(long digitalId) {
		this.digitalId = digitalId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getIssueNumber() {
		return issueNumber;
	}


	public void setIssueNumber(int issueNumber) {
		this.issueNumber = issueNumber;
	}


	public String getVariantDescription() {
		return variantDescription;
	}


	public void setVariantDescription(String variantDescription) {
		this.variantDescription = variantDescription;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public String getUpc() {
		return upc;
	}


	public void setUpc(String upc) {
		this.upc = upc;
	}


	public String getDiamondCode() {
		return diamondCode;
	}


	public void setDiamondCode(String diamondCode) {
		this.diamondCode = diamondCode;
	}


	public String getEan() {
		return ean;
	}


	public void setEan(String ean) {
		this.ean = ean;
	}


	public String getIssn() {
		return issn;
	}


	public void setIssn(String issn) {
		this.issn = issn;
	}


	public String getFormat() {
		return format;
	}


	public void setFormat(String format) {
		this.format = format;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public TextObject[] getTextObjects() {
		return textObjects;
	}


	public void setTextObjects(TextObject[] textObjects) {
		this.textObjects = textObjects;
	}


	public Summary[] getVariants() {
		return variants;
	}


	public void setVariants(Summary[] variants) {
		this.variants = variants;
	}


	public Summary[] getCollections() {
		return collections;
	}


	public void setCollections(Summary[] collections) {
		this.collections = collections;
	}


	public Summary[] getCollectedIssues() {
		return collectedIssues;
	}


	public void setCollectedIssues(Summary[] collectedIssues) {
		this.collectedIssues = collectedIssues;
	}


	public ComicDate[] getDates() {
		return dates;
	}


	public void setDates(ComicDate[] dates) {
		this.dates = dates;
	}


	public Price[] getPrices() {
		return prices;
	}


	public void setPrices(Price[] prices) {
		this.prices = prices;
	}


	public Image[] getImages() {
		return images;
	}


	public void setImages(Image[] images) {
		this.images = images;
	}


	public List getCreators() {
		return creators;
	}


	public void setCreators(List creators) {
		this.creators = creators;
	}


	public List getCharacters() {
		return characters;
	}


	public void setCharacters(List characters) {
		this.characters = characters;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Summary getNext() {
		return next;
	}


	public void setNext(Summary next) {
		this.next = next;
	}


	public Summary getPrevious() {
		return previous;
	}


	public void setPrevious(Summary previous) {
		this.previous = previous;
	}


	public Summary getOriginalIssue() {
		return originalIssue;
	}


	public void setOriginalIssue(Summary originalIssue) {
		this.originalIssue = originalIssue;
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
	
	
}
