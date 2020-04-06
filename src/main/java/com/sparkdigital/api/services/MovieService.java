package com.sparkdigital.api.services;


import com.sparkdigital.api.builders.MovieBuilder;
import com.sparkdigital.api.builders.StaffBuilder;
import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.domain.Role;
import com.sparkdigital.api.domain.Staff;
import com.sparkdigital.api.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * This is the service that handles the movies.
 * This behaves like a business controller for the "front end", which is the API.
 * NOTE: We are not adding the "update" node, for reasons of time. But it should be implemented as well
 */
@Service
public class MovieService {

    private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

	private final MovieRepository movieRepository;
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}


    @Transactional(readOnly = true)
    public Movie findByTitle(String title) {
        Movie result = movieRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<Movie> findByTitleLike(String title) {
        Collection<Movie> result = movieRepository.findByTitleLike(title);
        return result;
    }

	@Transactional(readOnly = true)
	public Collection<Movie> getAllMovies(int limit) {
		Collection<Movie> result = movieRepository.getAllMovies(limit);
		return result;
	}

	/**
	 * Saves the NEW movie to the appropiate repository. (in this case Neo4j, but it could be anything else)
	 */
	@Transactional
	public Movie addMovie(Movie movie){

		Movie foundMovie = movieRepository.findByTitle(movie.getTitle());

		if (foundMovie==null){
			return movieRepository.save(movie);
		}else{
			return null;
		}

	}

	/**
	 * Adds an EDGE between a movie and a given staff.
	 * NOTE: For simplicity, we do not check if the staff already exists or not.
	 * But we shall validate it in a complete version!
	 */
	@Transactional
	public Movie addRole(Movie movie, Staff staff, String staffRole) {
		Role role = new Role(movie, staff, staffRole);
		movie.addRole(role);

		return movieRepository.save(movie);
	}

    @Transactional(readOnly = true)
	public Movie findById(Long id) {
		return  movieRepository.findById(id).get();
	}

    public Collection<Movie> findAllMoviesByActor(String actorName) {
        return movieRepository.findAllMoviesByActor(actorName);
    }

	/**
	 * Handles the business logic for "what to do" when we need to update a movie data.
	 * @return
	 */
	public Movie updateMovie(Movie movieUpdate) {

		if (!movieRepository.existsById(movieUpdate.getId())){
			return null;
		}else{
			Optional<Movie> movieOld = movieRepository.findById(movieUpdate.getId());
			Movie newMovie = (new MovieBuilder()).mergedMovie(movieOld.get(),movieUpdate);
			return movieRepository.save(newMovie);
		}

	}
}
