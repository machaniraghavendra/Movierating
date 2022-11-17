package com.movierating.service;

import java.util.List;

import com.movierating.entity.MovieEntity;
import com.movierating.exception.MovieAlreadyExistsException;
import com.movierating.exception.MovieNotExistsException;

public interface MovieService {
	
	String save(MovieEntity movieEntity) throws MovieAlreadyExistsException;
	String saveall(List<MovieEntity> movieEntity) throws MovieAlreadyExistsException; 
	String update(MovieEntity movieEntity) throws MovieNotExistsException;
	List<MovieEntity> findall();
	List<String> find(String movieName) throws MovieNotExistsException; //Return string output
	List<MovieEntity> findByName(String movieName) throws MovieNotExistsException;// Returns Entity as output
	String deleteById(int movieId) throws MovieNotExistsException;
	String deleteAll();
}
