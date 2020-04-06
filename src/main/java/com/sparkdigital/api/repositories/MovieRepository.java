package com.sparkdigital.api.repositories;

import com.sparkdigital.api.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * This class handles the data queries
 */
@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends Neo4jRepository<Movie, Long> {

	Movie findByTitle(@Param("title") String title);

	Collection<Movie> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Movie) RETURN m LIMIT {limit}")
	Collection<Movie> getAllMovies(@Param("limit") int limit); // limit goes just for the matter of pagination...

	@Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Staff) WHERE a.name = {name}  RETURN m,r,a")
    Collection<Movie> findAllMoviesByActor(@Param("name") String title);
}