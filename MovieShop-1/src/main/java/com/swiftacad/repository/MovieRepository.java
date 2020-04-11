package com.swiftacad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.swiftacad.entity.Movie;

@Repository
	public interface MovieRepository extends CrudRepository<Movie, Long>{
	List<Movie> findByName(String name);

	List<Movie> findByProducerName(String producerName);

	List<Movie> findByGenre(String genre);
	
	void deleteByName(String name);
	


}
