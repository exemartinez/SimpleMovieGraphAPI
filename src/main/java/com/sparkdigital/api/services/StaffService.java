package com.sparkdigital.api.services;


import com.sparkdigital.api.builders.MovieBuilder;
import com.sparkdigital.api.builders.StaffBuilder;
import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.domain.Staff;
import com.sparkdigital.api.repositories.MovieRepository;
import com.sparkdigital.api.repositories.StaffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * This is the service that will handle the Staff transactions and queries.
 *
 * NOTE: We are not adding the "update" node, for reasons of time. But it should be implemented as well
 */
@Service
public class StaffService {

    private final static Logger LOG = LoggerFactory.getLogger(StaffService.class);

	private final StaffRepository staffRepository;
	public StaffService(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}

	/**
	 * It will return all movies by actor.
	 *
	 */
	@Transactional(readOnly = true)
	public Collection<Movie> getAllMoviesByActor(Staff actor) {
		Collection<Movie> result = staffRepository.getAllMoviesByActor(actor.getName());
		return result;
	}

	/**
	 * It will return all movies by actor's Name.
	 *
	 */
	@Transactional(readOnly = true)
	public Collection<Movie> getAllMoviesByActor(String actorName) {
		Collection<Movie> result = staffRepository.getAllMoviesByActor(actorName);
		return result;
	}

	/**
	 * Saves the staff node as is.
	 * NOTE: just for the sake of simplicity, here we do it
	 * checking that the name is unique, before we add it.
	 * This is different approach to what we showed in Movies;
	 * I'm reluctant to do it because it implies that the names are the node identifiers, which is not true.
	 * We should instruct the API to operate with a rigurous ID that must be provided by the consumer before it sends a request.
	 *
	 */
	@Transactional
	public Staff addStaff(Staff staff){
		Staff foundStaff = staffRepository.findByName(staff.getName());

		if (foundStaff==null){
			return staffRepository.save(staff);
		}else{
			return null;
		}

	}


}
