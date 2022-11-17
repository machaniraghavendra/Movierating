package com.movierating.exception;

@SuppressWarnings("serial")
public class MovieNotExistsException extends Exception {
	public MovieNotExistsException(String message) {
		super(message);
	}
}
