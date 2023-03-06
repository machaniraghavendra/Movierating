//package com.movierating.controller;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.movierating.entity.MovieEntity;
//import com.movierating.exception.MovieAlreadyExistsException;
//import com.movierating.exception.MovieNotExistsException;
//import com.movierating.repo.MovieRepository;
//import com.movierating.service.MovieServiceImpl;
//
//@WebMvcTest
//class MovieControllerTest {
//
//	@MockBean
//	MovieServiceImpl servImpl;
//
//	@MockBean
//	MovieRepository repo;
//	
//	@Autowired
//	MockMvc mockMvc;
//
//	ObjectMapper objectMap=new ObjectMapper();
//	ObjectWriter objectWriter=objectMap.writer();
//
//	MovieEntity movie=new MovieEntity();
//	
//	@BeforeEach
//	void setUp() {
////		movie=new MovieEntity(1,"RRR", "Action", 4, 8);
//	}
//
//	@AfterEach
//	void tearDown() {
//		movie=new MovieEntity();
//	}
//
//	@Test
//	void testSaveMovieEntity() throws Exception,MovieAlreadyExistsException {
//		
//		//ServiceTest
//		when(servImpl.save(movie)).thenReturn("The Movie " + movie.getMovieName() + " is saved");
//		
//		//Controller test
//		mockMvc.perform(post("/movie/")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectWriter.writeValueAsString(movie)))
//		.andExpect(status().isOk());
//	}
//
//	@Test
//	void testSaveListOfMovieEntity() throws JsonProcessingException, Exception,MovieAlreadyExistsException {
//		List<MovieEntity> movie=new ArrayList<MovieEntity>();
//		movie.add(new MovieEntity(1,"RRR", "Action", 4, 8));
//		movie.add(new MovieEntity(2,"Raksha", "Thriller", 4, 8));
//
//		//Service Test
//			when(servImpl.saveall(movie)).thenReturn("Saved list of data");
//			movie.add(new MovieEntity(1,"RRR", "Action", 4, 8));
//			
//			//Exception test
//			when(servImpl.saveall(movie)).thenThrow(new MovieAlreadyExistsException(""));
//		
//		//Controller Test
//		mockMvc.perform(post("/movie/all")
//				.contentType(MediaType.APPLICATION_JSON).content(objectWriter.writeValueAsString(movie)))
//		.andExpect(status().isOk());
//	}
//
//	@Test
//	void testFindall() throws Exception {
//		
//		List<MovieEntity> movie=new ArrayList<MovieEntity>();
//		movie.add(new MovieEntity(1,"RRR", "Action", 4, 8));
//		movie.add(new MovieEntity(2,"Raksha", "Thriller", 4, 8));
//
//		//Service Test
//		when(servImpl.findall()).thenReturn(movie);
//		
//		//Controller Test
//		mockMvc.perform(get("/movie/all")).andExpect(status().isOk());
//	}
//
//	@Test
//	void testFind() throws Exception,MovieNotExistsException {
//		List<MovieEntity> movie=new ArrayList<MovieEntity>();
//		movie.add(new MovieEntity(1,"RRR", "Action", 4, 8));
//		movie.add(new MovieEntity(2,"Raksha", "Thriller", 4, 8));
//
//		//Service test
//		List<String> returnString=new ArrayList<String>();
//		returnString.add("RRR ");
//		when(servImpl.find(this.movie.getMovieName())).thenReturn(returnString);
//		//Exception test
//		returnString.clear();
//		when(servImpl.find("ttt")).thenThrow(new MovieNotExistsException(""));
//		
//		//Controller test
//		mockMvc.perform(get("/movie/string/RRR")).andExpect(status().isOk());
//	}
//
//	@Test
//	void testFindByName() throws Exception,MovieNotExistsException {
//		List<MovieEntity> movie=new ArrayList<MovieEntity>();
//		movie.add(new MovieEntity(1,"RRR", "Action", 4, 8));
//		movie.add(new MovieEntity(2,"Raksha", "Thriller", 4, 8));
//		//Service test
//		List<MovieEntity> returnList=new ArrayList<MovieEntity>();
//		returnList.add(this.movie);
//		when(servImpl.findByName("RRR")).thenReturn(returnList);
//		//Exception test
//		when(servImpl.findByName("rrrrrr")).thenThrow(new MovieNotExistsException(""));
//		
//		//Controller test
//		mockMvc.perform(get("/movie/RRR")).andExpect(status().isOk());
//	}
//
//	@Test
//	void testUpdate() throws JsonProcessingException, Exception,MovieNotExistsException {
//		
//		//Service test
//		when(servImpl.update(new MovieEntity(1,"bahubali", "Action", 3, 10))).thenReturn("");
//		//Exception test
//		when(servImpl.update(new MovieEntity(10,"bahubali", "Action", 3, 10)))
//				.thenThrow(new MovieNotExistsException(""));
//		
//		//Controller test
//		mockMvc.perform(put("/movie/")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(objectWriter.writeValueAsString(movie)))
//		.andExpect(status().isOk());
//	}
//
//		@Test
//		void testDelete() throws Exception,MovieNotExistsException {
//		
//			//Service test
//			when(servImpl.deleteById(1)).thenReturn("Deleted");
//			//Exception test
//			when(servImpl.deleteById(0)).thenThrow(new MovieNotExistsException(""));
//			
//			//Controller test
//			mockMvc.perform(delete("/movie/1")).andExpect(status().isOk());
//		}
//
//		@Test
//		void testDeleteAll() throws Exception {
//			//Service test
//			when(servImpl.deleteAll()).thenReturn("Deleted");
//			
//			//Controller test
//			mockMvc.perform(delete("/movie/all")).andExpect(status().isOk());
//		}
//
//}
