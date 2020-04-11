package com.swiftacad.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swiftacad.entity.Movie;
import com.swiftacad.repository.MovieRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;

	@RequestMapping(value="movie", method = RequestMethod.POST)
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
		System.out.println(movieRepository);
		movieRepository.save(movie);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value="movie", method = RequestMethod.PUT)
	public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie) {
		movieRepository.save(movie);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "movie/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Movie> findById(@PathVariable("id") Long id) {
		Optional<Movie> optional = movieRepository.findById(id);
		
		if( optional.isPresent()) {
			return new ResponseEntity<>(optional.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "movies/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByName(@PathVariable("name") String name){
		List<Movie> movies = movieRepository.findByName(name);
		
		if(movies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "movies/producerName/{producerName}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByProducerName(@PathVariable("producerName") String producerName){
		List<Movie> movies = movieRepository.findByProducerName(producerName);
		
		if(movies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "movies/genre/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findByGenre(@PathVariable("genre") String genre){
		List<Movie> movies = movieRepository.findByGenre(genre);
		
		if(movies.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "movies", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findAll() {
		List<Movie> movies = new ArrayList<>();
		movieRepository.findAll().forEach( m -> movies.add(m));
		
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	

	
	@RequestMapping(value="movie/id/{id}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id ) {
		movieRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	
 
	@RequestMapping(value="movie/name/{name}", method = RequestMethod.DELETE) 
	public ResponseEntity<?> deleteByName(@PathVariable("name") String name ) {
		movieRepository.deleteByName(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	  @RequestMapping(value = "movies", method = RequestMethod.DELETE)
	  public ResponseEntity<?>deleteAll(){
		  movieRepository.deleteAll();
		  return new ResponseEntity<>(HttpStatus.OK);
	  }
	  
}