package com.sparkdigital.api.builders;

import com.sparkdigital.api.domain.Movie;


/**
 * In this design we overload the add new movie, so we can add new attributes
 * maintaining the backward and forward compatibility.
 *
 * This is a typical builder pattern.
 *
 */
public class MovieBuilder {
    private Movie movie;

    public Movie buildRawMovieInstance(String name) {

        movie = new Movie(name);

        return movie;
    }

    public Movie buildRawMovieInstance(String name, Integer released) {

        movie = new Movie(name);
        movie.setReleased(released);

        return movie;
    }

    public Movie buildRawMovieInstance(String name, Integer released, String tagline) {
        movie = new Movie(name);

        movie.setReleased(released);
        movie.setTagline(tagline);

        return movie;
    }

    /**
     * This is just to allow the node to be updated;
     * @param oldMovie
     * @param newMovie
     * @return
     */
    public Movie mergedMovie(Movie oldMovie, Movie newMovie) {

        oldMovie.setTagline(newMovie.getTagline() != null?newMovie.getTagline():oldMovie.getTagline());
        oldMovie.setReleased(newMovie.getReleased() != null?newMovie.getReleased():oldMovie.getReleased());
        oldMovie.setRoles(newMovie.getRoles() != null?newMovie.getRoles():oldMovie.getRoles());
        oldMovie.setTitle(newMovie.getTitle() != null?newMovie.getTitle():oldMovie.getTitle());

        return oldMovie; //I return the oldMovie, so we preserve the ID.
    }
}
