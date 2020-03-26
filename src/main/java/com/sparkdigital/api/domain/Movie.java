package com.sparkdigital.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

/**
 * The movie node that needs to be enriched.
 */
@NodeEntity
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	private String title;

	private Integer released;
	private String tagline;

	@JsonIgnoreProperties("movie")
	@Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
	private List<Role> roles;

	public Movie() {
	}

	/**
	 * We'll just keep one constructor with its
	 * mandatory parameter that we know will not change
	 * even if the API evolves.
	 *
	 * @param title
	 *
	 */
	public Movie(String title) {
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getReleased() {
		return released;
	}

	public String getTagline() {
		return tagline;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setReleased(Integer released) {
		this.released = released;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}