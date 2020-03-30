package com.sparkdigital.api.controllers;

import com.sparkdigital.api.builders.MovieBuilder;
import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.services.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This is the REST service that we will publish for the Movies CRUD.
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * Just returns the full list of movies.
	 * @param limit
	 * @return
	 */
    @GetMapping(value="/getAllMovies", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Movie> getAllMovies(@RequestParam(value = "limit",required = false) Integer limit) {
		return movieService.getAllMovies(limit == null ? 100 : limit);
	}

	/**
	 * Adds a new movie with the minimal mandatory data
	 * NOTE: we can decide to add more services here overloading the number of attributes.
	 * I putted that here just as an example of what could be done in this architecture.
	 * @param name
	 * @return
	 */
	@PostMapping(value="/addNewMovie", produces = MediaType.APPLICATION_JSON_VALUE)
	public Movie addNewMovie(@RequestParam(value = "name",required = false) String name) {
		MovieBuilder movieBuilder = new MovieBuilder();
		Movie movie = movieService.addMovie(movieBuilder.buildRawMovieInstance(name));

		return movie;
	}

	/**
	 * Just returns the full list of movies in which a given actor has been involved.
	 *
	 * @return
	 */
	@GetMapping(value="/getAllMoviesByActor", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Movie> getAllMoviesByActor(@RequestParam(value = "name",required = true) String actorName) {
		return movieService.findAllMoviesByActor(actorName);
	}

}
