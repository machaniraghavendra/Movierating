package com.movierating.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.movierating.entity.MovieEntity;
import com.movierating.exception.MovieAlreadyExistsException;
import com.movierating.exception.MovieNotExistsException;
import com.movierating.service.MovieServiceImpl;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/movie")
@Api(value = "/movierating" , tags = "Movie Rating and its methods")
public class MovieController {
	@Autowired
	MovieServiceImpl movieServ;
	
	@PostMapping("/")
	public ResponseEntity<String> movieSave(@RequestBody MovieEntity movieEntity) throws MovieAlreadyExistsException{
		String token=UUID.randomUUID().toString();
		MovieEntity movie=new MovieEntity(token, movieEntity.getMovieName(),movieEntity.getMovieGenre(),
				movieEntity.getMovieRating(),movieEntity.getLikes());
		return new ResponseEntity<String>(movieServ.save(movie),HttpStatus.OK);
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> movieSaveAll(@RequestBody List<MovieEntity> movieEntity) throws MovieAlreadyExistsException{
		return new ResponseEntity<String>(movieServ.saveall(movieEntity),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieEntity>> getMovieList(){
		return new ResponseEntity<List<MovieEntity>>(movieServ.findall(),HttpStatus.OK);
	}
	
	@GetMapping("/string/{name}")
	public ResponseEntity<List<String>> getMovieasString(@PathVariable String name ) throws MovieNotExistsException{
		return new ResponseEntity<List<String>>(movieServ.find(name),HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity< List<MovieEntity>> getMovieasMovieEntity(@PathVariable String name ) throws MovieNotExistsException{
		return new ResponseEntity< List<MovieEntity>>(movieServ.findByName(name),HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<String> updateMovie(@RequestBody MovieEntity movieEntity) throws MovieNotExistsException  {
		return new ResponseEntity<String>(movieServ.update(movieEntity),HttpStatus.OK);
	}
	
	@DeleteMapping("/{movieName}")
	public ResponseEntity<String> deleteMovieById(@PathVariable String movieName) throws MovieNotExistsException  {
		return new ResponseEntity<String>(movieServ.deleteById(movieName),HttpStatus.OK);
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<String> deleteAllData()   {
		return new ResponseEntity<String>(movieServ.deleteAll(),HttpStatus.OK);
	}
}
