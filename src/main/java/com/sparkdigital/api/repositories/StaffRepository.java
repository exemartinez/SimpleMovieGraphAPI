package com.sparkdigital.api.repositories;

import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.domain.Staff;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 *
 */
public interface StaffRepository extends Neo4jRepository<Staff, Long> {

    Staff findByName(String name);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Staff) RETURN m,r,a WHERE a = {actor}")
    Collection<Movie> getAllMoviesByActor(@Param("actor") String actor);
}