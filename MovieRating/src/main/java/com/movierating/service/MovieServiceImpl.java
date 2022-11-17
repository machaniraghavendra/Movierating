package com.movierating.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
			if (movieRepo.existsById(movieEntity.getMovieId())) {
				throw new MovieAlreadyExistsException("This movie already exists");
			} else {
				movieRepo.save(movieEntity);
				return "The Movie " + movieEntity.getMovieName() + " is saved";
			}
		} catch (MovieAlreadyExistsException e) {
			e.printStackTrace();
		}
		return "This movie already exists";
	}

	@Override
	public String saveall(List<MovieEntity> movieEntity) throws MovieAlreadyExistsException {
		int movieId = 0;
		try {
			for (MovieEntity movie : movieEntity) {
				if (movieRepo.existsById(movie.getMovieId())) {
					movieId = movie.getMovieId();
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
		try {
			if (!movieRepo.existsById(movieEntity.getMovieId())) {
				throw new MovieNotExistsException("This movie with id " + movieEntity.getMovieId() + " not exists");
			} else {
				movieRepo.save(movieEntity);
				return "The Movie " + movieRepo.findById(movieEntity.getMovieId()) + " is updated";
			}
		} catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return "This movie with id " + movieEntity.getMovieId() + " not exists";
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
		try {
			List<MovieEntity> movieList=movieRepo.findAll().stream()
					.sorted(Comparator.comparing(MovieEntity::getLikes).reversed()
							.thenComparing(MovieEntity::getMovieName))
					.filter(movie->movie.getMovieName().toLowerCase().contains(movieName.toLowerCase()))
					.toList();
			if (!movieList.isEmpty()) {
				return movieList;
			}else {
				movies.clear();
				movies.add(new MovieEntity(0, "No movie with name '"+movieName+"'", "None", 0, 0));
				throw new MovieNotExistsException("Not found with name"+movieName);
			}
		}catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public String deleteById(int movieId) throws MovieNotExistsException {
		try {
			if (movieRepo.existsById(movieId)) {
				movieRepo.deleteById(movieId);
				return "The movie is deleted";
			} else {
				throw new MovieNotExistsException("Movie not exists with id");
			}
		} catch (MovieNotExistsException e) {
			e.printStackTrace();
		}
		return "Movie not exists with id";
	}

	@Override
	public String deleteAll() {
		movieRepo.deleteAll();
		return "Deleted all movie(s) data";
	}

}
