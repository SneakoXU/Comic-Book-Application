package com.techelevator.model;

import java.util.Map;

public class CollectionStatistics 
{
	private Map<Integer, Integer> characterCounts;
	private int numberOfComics;
	
	public CollectionStatistics(){}
	
	public CollectionStatistics(Map<Integer, Integer> characterCounts,
	        int numberOfComics) {
		super();
		this.characterCounts = characterCounts;
		this.numberOfComics = numberOfComics;
	}

	

	public Map<Integer, Integer> getCharacterCounts() {
		return characterCounts;
	}

	public void setCharacterCounts(Map<Integer, Integer> characterCounts) {
		this.characterCounts = characterCounts;
	}

	public int getNumberOfComics() {
		return numberOfComics;
	}

	public CollectionStatistics setNumberOfComics(int numberOfComics) {
		this.numberOfComics = numberOfComics;
		return this;
	}
}
