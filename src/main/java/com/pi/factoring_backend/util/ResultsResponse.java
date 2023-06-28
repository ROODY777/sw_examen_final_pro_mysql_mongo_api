package com.pi.factoring_backend.util;

import java.io.Serializable;

public class ResultsResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String successMessage;
	private T results;

	public ResultsResponse(T results) {
		this.results = results;
	}

	public ResultsResponse(String successMessage) {
		this.successMessage = successMessage;
	}

	public ResultsResponse(String successMessage, T results) {
		this.successMessage = successMessage;
		this.results = results;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public T getResults() {
		return results;
	}

	public void setResults(T results) {
		this.results = results;
	}

}
