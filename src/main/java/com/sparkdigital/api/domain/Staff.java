package com.sparkdigital.api.domain;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * I putted staff, because you can have actor, actress, director, anyone...
 * I just want this entity to represent a physical person that is related to the
 * movie development and production.
 */
@NodeEntity
public class Staff {

    @Id
    @GeneratedValue
	private Long id;
	private String name;
	private Integer born;

	@Relationship(type = "ACTED_IN")
	private List<Movie> movies = new ArrayList<>();

	public Staff() {
	}

	public Staff(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getBorn() {
		return born;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBorn(Integer born) {
		this.born = born;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}