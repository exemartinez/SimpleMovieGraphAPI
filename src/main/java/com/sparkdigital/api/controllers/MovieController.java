package com.sparkdigital.api.controllers;

import com.sparkdigital.api.builders.MovieBuilder;
import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.domain.Staff;
import com.sparkdigital.api.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

/**
 * This is the REST service that we will publish for the Movies CRUD.
 */
@RestController
@RequestMapping("/")
public class MovieController {

	private final MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * Just returns the full list of movies.
	 * @param limit limits the number of rows to be returned.
	 * @return
	 */
    @GetMapping(value="/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Movie> getAllMovies(@RequestParam(value = "limit",required = false) Integer limit) {
		try {
			return movieService.getAllMovies(limit == null ? 100 : limit);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	/**
	 * Adds a new movie
	 */
	@PostMapping(value="/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addNewMovie(@RequestBody Movie newMovie) {
		Movie movie = null;

		try {
			movie = movieService.addMovie(newMovie);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (movie==null){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * Updates an existing movie with the minimal mandatory data
	 */
	@PutMapping(value="/movies/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateMovie(@PathVariable(value = "id",required = true) Long id, @RequestBody Movie updatedMovie) {
		Movie movie=null;

		try {
			updatedMovie.setId(id);

			movie = movieService.updateMovie(updatedMovie);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (movie==null){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	/**
	 * Just returns the full list of movies in which a given actor has been involved.
	 * @return
	 */
	@GetMapping(value="/movies/actor", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Movie> getAllMoviesByActor(@RequestBody Staff staff) {
		try {
			return movieService.findAllMoviesByActor(staff.getName());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	/**
	 * Adds a role to a movie
	 */
	@PutMapping(value="/movies/{id}/role/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addRoleToMovie(@PathVariable(value = "id",required = true) Long id, @PathVariable(value = "name",required = true) String name, @RequestBody Staff staff) {
		Movie movie;

		try {

			movie = movieService.findById(id);
			movie = movieService.addRole(movie, staff, name);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (movie==null){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.OK);
	}
}
