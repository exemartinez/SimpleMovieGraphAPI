package com.sparkdigital.api.domain;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an edge, actor -> movie.
 */
@RelationshipEntity(type = "ACTED_IN")
public class Role {

    @Id
    @GeneratedValue
	private Long id;
	private List<String> roles = new ArrayList<>();

	@StartNode
	private Staff staff;

	@EndNode
	private Movie movie;

	public Role(Movie movie, Staff staff) {
		this.movie = movie;
		this.staff = staff;
	}

	public Role(Movie movie, Staff staff, String staffRole) {
		this(movie, staff);
		this.addRoleName(staffRole);
	}

	public Long getId() {
	    return id;
	}

	public List<String> getRoles() {
	    return roles;
	}

	public Staff getStaff() {
	    return staff;
	}

	public Movie getMovie() {
	    return movie;
	}

    public void addRoleName(String name) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(name);
    }
}