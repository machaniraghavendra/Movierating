package com.movierating.entity;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

	@Id
	private String Id;
	private String movieName;
	private String movieGenre;
	private float movieRating;
	private int likes;
	
}
