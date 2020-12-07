package com.techelevator.model.marvel;

public class Image {
	
	/**
	 * 50x75
	 */
	public static final String PORTRAIT_SMALL = "portrait_small";
	
	/**
	 * 100x150px
	 */
	public static final String PORTRAIT_MEDIUM = "portrait_medium";
		
	/**
	 * 150x225px
	 */
	public static final String PORTRAIT_LARGE = "portrait_xlarge";
		
	/**
	 * 168x252px
	 */
	public static final String PORTRAIT_FANTASTIC = "portrait_fantastic";
	
	/**
	 * 300x450px
	 */
	public static final String PORTRAIT_UNCANNY = "portrait_uncanny";
		
	/**
	 * 216x324px
	 */
	public static final String PORTRAIT_INCREDIBLE = "portrait_incredible";
	
	private String path;
	private String extension;
	public Image() {
	}
	public Image(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	

}
