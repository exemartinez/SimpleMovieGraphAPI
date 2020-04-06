package com.sparkdigital.api.controllers;

import com.sparkdigital.api.builders.StaffBuilder;
import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.domain.Staff;
import com.sparkdigital.api.services.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * This is the REST service that we will publish for the Movies CRUD.
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

	private final StaffService staffService;

	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}


	/**
	 * Adds a new STAFF with the minimal mandatory data
	 */
	@PostMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity addNewStaff(@RequestBody Staff newStaff) {
		Staff staff=null;

		try {
			staff = staffService.addStaff(newStaff);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (staff==null){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

}
