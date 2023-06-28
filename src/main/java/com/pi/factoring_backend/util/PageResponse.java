package com.pi.factoring_backend.util;

import java.util.List;

import org.springframework.data.domain.Page;

public class PageResponse<T> extends ResultsResponse<List<T>> {
	private static final long serialVersionUID = 1L;
	private int number;
	private int numberOfElements;
	private int size;
	private long totalElements;
	private int totalPages;

	public PageResponse(Page<T> results) {
		super(results.getContent());
		setProperties(results);
	}
	
	public PageResponse(String successMessage, Page<T> results) {
		super(successMessage, results.getContent());
		setProperties(results);
	}
	
	private void setProperties(Page<T> results) {
		this.number = results.getNumber() + 1;
		this.numberOfElements = results.getNumberOfElements();
		this.size = results.getSize();
		this.totalElements = results.getTotalElements();
		this.totalPages = results.getTotalPages() + 1;
	}

	public int getNumber() {
		return number;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public int getSize() {
		return size;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

}
