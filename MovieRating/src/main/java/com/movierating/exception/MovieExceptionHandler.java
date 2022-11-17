package com.movierating.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class MovieExceptionHandler{

	@ExceptionHandler(value=MovieAlreadyExistsException.class)
	public ResponseEntity<String> movieAlreadyExistsException(MovieAlreadyExistsException e){
		return new ResponseEntity<String> ("Movie already exists",HttpStatus.ACCEPTED);
	}
	
	@ExceptionHandler(value=MovieNotExistsException.class)
	public  ResponseEntity<String> movieNotFoundException(MovieNotExistsException e){
		return new ResponseEntity<String>("Movie not exists",HttpStatus.ACCEPTED);
	}
}
