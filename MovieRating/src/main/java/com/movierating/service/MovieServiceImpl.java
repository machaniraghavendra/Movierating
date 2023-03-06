package com.movierating.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.movierating.entity.MovieEntity;
import com.movierating.exception.MovieAlreadyExistsException;
import com.movierating.exception.MovieNotExistsException;
import com.movierating.repo.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	List<String> Value = new ArrayList<String>();
	List<MovieEntity> movies = new ArrayList<MovieEntity>();

	@Autowired
	MovieRepository movieRepo;

	@Override
	public String save(MovieEntity movieEntity) throws MovieAlreadyExistsException {
		try {
			if (!(movieRepo.existsById(movieEntity.getId())||(movieRepo.findAll().stream()
					.filter(a->a.getMovieName().toLowerCase().equalsIgnoreCase(movieEntity.getMovieName()))
					.collect(Collectors.toList())).size()>0)) 
			{
				movieRepo.save(movieEntity);
				return "The Movie " + movieEntity.getMovieName() + " is saved";
			} else {
				throw new MovieAlreadyExistsException("This movie '"+movieEntity.getMovieName()+"' already exists");
			}
		} catch (MovieAlreadyExistsException e) {
			e.printStackTrace();
		}
		return "This movie '"+movieEntity.getMovieName()+"' already exists";
	}

	@Override
	public String saveall(List<MovieEntity> movieEntity) throws MovieAlreadyExistsException {
		String movieId="";
		try {
			for (MovieEntity movie : movieEntity) {
				movieId = movie.getId();
				if (movieRepo.existsById(movie.getId())) {
					throw new MovieAlreadyExistsException("This movie already exists");
				} else {
					movieRepo.saveAll(movieEntity);
					return "Saved movies list";
				}
			}
		} catch (MovieAlreadyExistsException e) {
			e.printStackTrace();
		}
		return "This movie with id " + movieId + " already exists";
	}

	@Override
	public String update(MovieEntity movieEntity) throws MovieNotExistsException {
		List<MovieEntity> movieList=findByName(movieEntity.getMovieName()).stream().collect(Collectors.toList());
		String id="";
		try {
			for(MovieEntity movie:movieList) {
				if (movie.getMovieName()==movieEntity.getMovieName()||movie.getMovieGenre()==movieEntity.getMovieGenre()
						||movie.getMovieRating()==movieEntity.getMovieRating()||movie.getLikes()==movieEntity.getLikes()) {
					id=movie.getId();
					if (!movieRepo.existsById(id)) {
						throw new MovieNotExistsException("This movie with id " + id + " not exists");
					} else {
						movieRepo.save(movie);
						return "The Movie " + movie.getMovieName() + " is updated";
					}
				}
			
			}
		} catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return "This movie with id " + id+ " not exists";
	}

	@Override
	public List<MovieEntity> findall() {
		return movieRepo.findAll();
	}

	@Override
	public List<String> find(String movieName) throws MovieNotExistsException {
		try {
			List<String> fliterList = movieRepo.findAll().stream()
					.sorted((o1, o2) -> (((Integer) o2.getLikes()).compareTo((Integer) o1.getLikes())))
					//					.sorted(Comparator.comparingInt(MovieEntity::getLikes)
					//							.reversed()
					.filter(movie -> movie.getMovieName().toLowerCase().contains(movieName.toLowerCase()))
					.map(movie -> movie.getMovieName() + " with rating = " + movie.getMovieRating()
					+ "/5.0, is of Genre " + movie.getMovieGenre() + " and likes are " + movie.getLikes())
					.collect(Collectors.toList());

			if (!fliterList.isEmpty()) {
				return fliterList;
			} else {
				Value.clear();
				Value.add("There is no movie with name '" + movieName + "'");
				throw new MovieNotExistsException("Not found with name "+movieName);
			}
		} catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return Value;
	}

	@Override
	public List<MovieEntity> findByName(String movieName) throws MovieNotExistsException{
		List<MovieEntity> movieList=new ArrayList<MovieEntity>();
		try {
			 movieList=movieRepo.findAll().stream()
					.sorted(Comparator.comparing(MovieEntity::getLikes).reversed()
							.thenComparing(MovieEntity::getMovieName))
					.filter(movie->movie.getMovieName().toLowerCase().contains(movieName.toLowerCase()))
					.collect(Collectors.toList());
			if (!movieList.isEmpty()) {
				return movieList;
			}else {
				movies.clear();
				movies.add(new MovieEntity("null", "No movie with name '"+movieName+"'", "None", 0, 0));
				throw new MovieNotExistsException("Not found with name"+movieName);
			}
		}catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return movieList;
	}

	@Override
	public String deleteById(String movieName) throws MovieNotExistsException {
		try {
			List<MovieEntity> listMovies=movieRepo.findAll().stream()
					.filter(a->a.getMovieName().toLowerCase().contains(movieName.toLowerCase())).toList();
			if (listMovies.size()==1) {
				for(MovieEntity movie:listMovies) {
					movieRepo.deleteById(movie.getId());
					return "Deleted movie '"+movie.getMovieName()+"'";
				}
			}else {
				throw new  MovieNotExistsException("Movie name not exists '"+movieName+"'");
			}
		} catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return "Movie not matched with the data we have, try to give full name of movie";
	}

	@Override
	public String deleteAll() {
		movieRepo.deleteAll();
		return "Deleted all movie(s) data";
	}

}
