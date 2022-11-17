package com.movierating.controller;

import java.util.List;

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

@Controller
@RequestMapping("/movie")
public class MovieController {
	@Autowired
	MovieServiceImpl movieServ;
	
	@PostMapping("/")
	public ResponseEntity<String> save(@RequestBody MovieEntity movieEntity) throws MovieAlreadyExistsException{
		return new ResponseEntity<String>(movieServ.save(movieEntity),HttpStatus.OK);
	}
	
	@PostMapping("/all")
	public ResponseEntity<String> save(@RequestBody List<MovieEntity> movieEntity) throws MovieAlreadyExistsException{
		return new ResponseEntity<String>(movieServ.saveall(movieEntity),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieEntity>> findall(){
		return new ResponseEntity<List<MovieEntity>>(movieServ.findall(),HttpStatus.OK);
	}
	
	@GetMapping("/string/{name}")
	public ResponseEntity<List<String>> find(@PathVariable String name ) throws MovieNotExistsException{
		return new ResponseEntity<List<String>>(movieServ.find(name),HttpStatus.OK);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<MovieEntity>> findByName(@PathVariable String name ) throws MovieNotExistsException{
		return new ResponseEntity<List<MovieEntity>>(movieServ.findByName(name),HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<String> update(@RequestBody MovieEntity movieEntity) throws MovieNotExistsException  {
		return new ResponseEntity<String>(movieServ.update(movieEntity),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) throws MovieNotExistsException  {
		return new ResponseEntity<String>(movieServ.deleteById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<String> deleteAll()   {
		return new ResponseEntity<String>(movieServ.deleteAll(),HttpStatus.OK);
	}
}
