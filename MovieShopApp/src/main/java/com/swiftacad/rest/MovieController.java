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
import com.swiftacad.entity.Shop;
import com.swiftacad.repository.MovieRepository;
import com.swiftacad.repository.ShopRepository;
import com.swiftacad.resources.MovieResource;

import static java.util.stream.Collectors.toList;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
    private ShopRepository shopRepository;

	@RequestMapping(value = "movie", method = RequestMethod.POST)
	public ResponseEntity<Movie> addMovie(@RequestBody MovieResource movieResource) {
		Movie movie = createMovieFromResource(movieResource);
		movieRepository.save(movie);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

	@RequestMapping(value = "movie", method = RequestMethod.PUT)
	public ResponseEntity<Movie> updateMovie(@RequestBody MovieResource movieResource) {
		Movie movie = createMovieFromResource(movieResource);
		movieRepository.save(movie);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "movie/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<MovieResource> findById(@PathVariable("id") Long id) {
		Optional<Movie> optional = movieRepository.findById(id);

		 return optional.map(movie -> new ResponseEntity<>(createResourceFromMovie(movie), HttpStatus.OK))
              .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	

	
	 @RequestMapping(value = "movies/name/{name}", method = RequestMethod.GET)
	    public ResponseEntity<List<MovieResource>> findByName(@PathVariable("name") String name) {
	        List<Movie> movies = movieRepository.findByName(name);

	        if (movies.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(movies.stream()
	                .map(this::createResourceFromMovie)
	                .collect(toList()), HttpStatus.OK);
    }


	@RequestMapping(value = "movies/producerName/{producerName}", method = RequestMethod.GET)
	public ResponseEntity<List<MovieResource>> findByProducerName(@PathVariable("producerName") String producerName) {
		List<Movie> movies = movieRepository.findByProducerName(producerName);

		if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies.stream()
                .map(this::createResourceFromMovie)
                .collect(toList()), HttpStatus.OK);
	}

	@RequestMapping(value = "movies/genre/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<MovieResource>> findByGenre(@PathVariable("genre") String genre) {
		List<Movie> movies = movieRepository.findByGenre(genre);

		if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(movies.stream()
                .map(this::createResourceFromMovie)
                .collect(toList()), HttpStatus.OK);
	}

	@RequestMapping(value = "movies", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> findAll() {
		List<Movie> movies = new ArrayList<>();
		movieRepository.findAll().forEach(m -> movies.add(m));

		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
   
	@RequestMapping(value = "movie/id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		movieRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "movie/name/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteByName(@PathVariable("name") String name) {
		movieRepository.deleteByName(name);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private Movie createMovieFromResource(MovieResource movieResource) {
        Movie movie = new Movie();
        Shop shop = shopRepository.findById(movieResource.getShopId()).orElseThrow(IllegalArgumentException::new);
        movie.setShop(shop);
        movie.setDuration(movieResource.getDuration());
        movie.setName(movieResource.getName());
        movie.setGenre(movieResource.getGenre());
        movie.setProducerName((movie.getProducerName()));
        movie.setPrice(movieResource.getPrice());

        return movie;
    }

    private MovieResource createResourceFromMovie(Movie movie) {
        MovieResource movieResource = new MovieResource();
        movieResource.setDuration(movie.getDuration());
        movieResource.setShopId(movie.getShop().getId());
        movieResource.setGenre(movie.getGenre());
        movieResource.setPrice(movie.getPrice());
        movieResource.setName(movie.getName());
        movieResource.setProducerName(movie.getProducerName());

        return movieResource;
    }


}