package com.sparkdigital.api.controllers;

import com.sparkdigital.api.builders.StaffBuilder;
import com.sparkdigital.api.domain.Movie;
import com.sparkdigital.api.domain.Staff;
import com.sparkdigital.api.services.StaffService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	 * NOTE: we can decide to add more services here overloading the number of attributes.
	 * I putted that here just as an example of what could be done in this architecture.
	 */
	@GetMapping("/addNewStaff")
	public Staff addNewStaff(@RequestParam(value = "name",required = false) String name) {
		StaffBuilder movieBuilder = new StaffBuilder();
		Staff staff = staffService.addStaff(movieBuilder.buildRawStaffInstance(name));

		return staff;
	}

}
