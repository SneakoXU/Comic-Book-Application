package com.techelevator.model.marvel.fields;

import java.util.List;

import com.techelevator.model.Collection;
import com.techelevator.model.User;

public class Container {
	private int offset;
	private int limit;
	private int total;
	private int count;
	private Field[] results;
	public Container() {
	}
	public Container(int offset, int limit, int total, int count,
	        Field[] results) {
		super();
		this.offset = offset;
		this.limit = limit;
		this.total = total;
		this.count = count;
		this.results = results;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getTotal() {
		return total;
	}
	public Container setTotal(int total) {
		this.total = total;
		return this;
	}
	public int getCount() {
		return count;
	}
	public Container setCount(int count) {
		this.count = count;
		return this;
	}
	public Field[] getResults() {
		return results;
	}
	public Container setResults(Field[] results) {
		this.results = results;
		return this;
	}
	
	public Container setResultsList(List<?> results) {
		Field[] ret = new Field[results.size()];
		for(int i = 0; i< results.size();i++)
			ret[i] = (Field) results.get(i);
		this.results = ret;
		return this;
	}

}
