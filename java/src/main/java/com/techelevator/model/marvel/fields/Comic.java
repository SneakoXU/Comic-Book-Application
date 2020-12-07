package com.techelevator.model.marvel.fields;

import com.techelevator.model.marvel.lists.CharacterList;
import com.techelevator.model.marvel.lists.CreatorList;
import com.techelevator.model.marvel.lists.EventList;
import com.techelevator.model.marvel.lists.StoryList;
import com.techelevator.model.marvel.summaries.ComicSummary;
import com.techelevator.model.marvel.summaries.SeriesSummary;
import com.techelevator.model.marvel.wrappers.ComicDataWrapper;

public class Comic extends Field {

	private long id;
	private long digitalId;
	private String title;
	private int issueNumber;
	private String variantDescription;
	private String description;
	private String modified;
	private String isbn;
	private String upc;
	private String diamondCode;
	private String ean;
	private String issn;
	private String format;
	private int pageCount;
	private TextObject[] textObjects;
	private String resourceURI;
	private URL[] urls;
	private SeriesSummary series;
	private ComicSummary[] variants;
	private ComicSummary[] collections;
	private ComicSummary[] collectedIssues;
	private Date[] dates;
	private Price[] prices;
	private Image thumbnail;
	private Image[] images;
	private CreatorList creators;
	private CharacterList characters;
	private StoryList stories;
	private EventList events;
	public Comic() {
	}
	
	public Comic(long id, long digitalId, String title, int issueNumber,
	        String variantDescription, String description, String modified,
	        String isbn, String upc, String diamondCode, String ean,
	        String issn, String format, int pageCount, TextObject[] textObjects,
	        String resourceURI, URL[] urls, SeriesSummary series,
	        ComicSummary[] variants, ComicSummary[] collections,
	        ComicSummary[] collectedIssues, Date[] dates, Price[] prices,
	        Image thumbnail, Image[] images, CreatorList creators,
	        CharacterList characters, StoryList stories, EventList events) {

		this.id = id;
		this.digitalId = digitalId;
		this.title = title;
		this.issueNumber = issueNumber;
		this.variantDescription = variantDescription;
		this.description = description;
		this.modified = modified;
		this.isbn = isbn;
		this.upc = upc;
		this.diamondCode = diamondCode;
		this.ean = ean;
		this.issn = issn;
		this.format = format;
		this.pageCount = pageCount;
		this.textObjects = textObjects;
		this.resourceURI = resourceURI;
		this.urls = urls;
		this.series = series;
		this.variants = variants;
		this.collections = collections;
		this.collectedIssues = collectedIssues;
		this.dates = dates;
		this.prices = prices;
		this.thumbnail = thumbnail;
		this.images = images;
		this.creators = creators;
		this.characters = characters;
		this.stories = stories;
		this.events = events;
	}
	
	public static ComicDataWrapper getComic(long id) 
	{
		return getDataWrapper("public/comics/"+id, ComicDataWrapper.class);
	}
	
	public static ComicDataWrapper getComics() 
	{
		return getDataWrapper("public/comics", ComicDataWrapper.class);
	}
	
	public static ComicDataWrapper getComicsByName(String name) 
	{
		return getDataWrapper("public/comics?titleStartsWith="+name, ComicDataWrapper.class);
	}
	
	public static ComicDataWrapper getComicsByCharacter(int id) 
	{
		return getDataWrapper("public/comics?characters="+id, ComicDataWrapper.class);
	}
	public static ComicDataWrapper getComicsBySeries(int id) 
	{
		return getDataWrapper("public/comics?series="+id, ComicDataWrapper.class);
	}
	public static String getThumbnailURL(int comicID)
	{
		Image comicImage =getComic(comicID).getData().getResults()[0].getThumbnail();
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
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
	public SeriesSummary getSeries() {
		return series;
	}
	public void setSeries(SeriesSummary series) {
		this.series = series;
	}
	public ComicSummary[] getVariants() {
		return variants;
	}
	public void setVariants(ComicSummary[] variants) {
		this.variants = variants;
	}
	public ComicSummary[] getCollections() {
		return collections;
	}
	public void setCollections(ComicSummary[] collections) {
		this.collections = collections;
	}
	public ComicSummary[] getCollectedIssues() {
		return collectedIssues;
	}
	public void setCollectedIssues(ComicSummary[] collectedIssues) {
		this.collectedIssues = collectedIssues;
	}
	public Date[] getDates() {
		return dates;
	}
	public void setDates(Date[] dates) {
		this.dates = dates;
	}
	public Price[] getPrices() {
		return prices;
	}
	public void setPrices(Price[] prices) {
		this.prices = prices;
	}
	public Image getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Image thumbnail) {
		this.thumbnail = thumbnail;
	}
	public Image[] getImages() {
		return images;
	}
	public void setImages(Image[] images) {
		this.images = images;
	}
	public CreatorList getCreators() {
		return creators;
	}
	public void setCreators(CreatorList creators) {
		this.creators = creators;
	}
	public CharacterList getCharacters() {
		return characters;
	}
	public void setCharacters(CharacterList characters) {
		this.characters = characters;
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
}

