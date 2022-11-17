package com.movierating.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MovieEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int movieId;
	private String movieName;
	private String movieGenre;
	private float movieRating;
	private int likes;
	
	public MovieEntity() {
		super();
	}

	public MovieEntity(int movieId, String movieName, String movieGenre, float movieRating, int likes) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieGenre = movieGenre;
		this.movieRating = movieRating;
		this.likes = likes;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public float getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(float movieRating) {
		this.movieRating = movieRating;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "MovieEntity [movieId=" + movieId + ", movieName=" + movieName + ", movieGenre=" + movieGenre
				+ ", movieRating=" + movieRating + ", likes=" + likes + "]";
	}

	
}
