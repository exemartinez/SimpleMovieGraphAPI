package com.sparkdigital.api.repositories;

import com.sparkdigital.api.builders.MovieBuilder;
import com.sparkdigital.api.builders.StaffBuilder;
import com.sparkdigital.api.domain.Role;
import com.sparkdigital.api.domain.Staff;
import com.sparkdigital.api.domain.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

import com.sparkdigital.api.services.MovieService;
import com.sparkdigital.api.services.StaffService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Just a general test for compliance of the basic REST service.
 *
 * NOTE: We test the "Service" classes only, because testing the due Controllers
 * is trivial. (However, I invite you to checkit out with postman)
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private StaffRepository staffRepository;

	@Before
	public void setUp() {

		Long movieId = addMovie("Star Wars: A new hope");

		addRoleActor(movieId, "Mark Hamill", "Actor");
		addRoleActor(movieId, "Carrie Fisher", "Actor");
		addRoleActor(movieId, "Alec Guinness", "Actor");
		addRoleActor(movieId, "Harrison Ford", "Actor");

		movieId = addMovie("Star Wars: The empire strikes back");

		addRoleActor(movieId, "Mark Hamill", "Actor");
		addRoleActor(movieId, "Carrie Fisher", "Actor");
		addRoleActor(movieId, "Harrison Ford", "Actor");
		addRoleActor(movieId, "Billy Dee Williams", "Actor");

	}

	private Long addMovie(String movieTitle) {

		MovieBuilder movieBuilder = new MovieBuilder();
		StaffBuilder staffBuilder = new StaffBuilder();
		MovieService movieService = new MovieService(movieRepository);
		StaffService staffService = new StaffService(staffRepository);

		//Creation of the moview node...
		Movie movie = movieBuilder.buildRawMovieInstance(movieTitle);

		return movieService.addMovie(movie).getId();

	}

	private void addRoleActor(Long movieId, String staffName, String staffRole) {

		StaffBuilder staffBuilder = new StaffBuilder();
		MovieService movieService = new MovieService(movieRepository);
		StaffService staffService = new StaffService(staffRepository);

		//Creation of the moview node...

		Movie movie = movieService.findById(movieId);

		//adding the 'actors', an actual "role" for any given staff involved with the movie.
		Staff staff = staffBuilder.buildRawStaffInstance(staffName);
		staffService.addStaff(staff);

		//Adding the roles
		movieService.addRole(movie,staff, staffRole);

	}

	/**
	 * Test of findByTitle method, of class MovieRepository.
	 */
	@Test
	public void testFindByTitle() {

		String title = "Star Wars: A new hope";

		//Test the repo
		MovieService movieService = new MovieService(movieRepository);
		Collection<Movie> result = movieService.findByTitleLike(title);

		List<Movie> list = new ArrayList<Movie>(result);
		assertNotNull(result);
		assertEquals("Mark Hamill", ((ArrayList<Role>)((ArrayList<Movie>) list).get(0).getRoles()).get(0).getStaff().getName());

	}

	/**
	 * Test of findByTitleContaining method, of class MovieRepository.
	 */
	@Test
	public void testFindByTitleContaining() {
		String title = "*Empire*";
		MovieService movieService = new MovieService(movieRepository);
		Collection<Movie> result = movieService.findByTitleLike(title);

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	/**
	 * Test of graph method, of class MovieRepository.
	 */
	@Test
	public void testGraph() {
		MovieService movieService = new MovieService(movieRepository);
		Collection<Movie> graph = movieService.getAllMovies(5);

		assertEquals(2, graph.size());

		Movie movie = graph.iterator().next();

		assertEquals(4, movie.getRoles().size());
		assertEquals( "Star Wars: A new hope", movie.getTitle());
		assertEquals("Mark Hamill", movie.getRoles().iterator().next().getStaff().getName());
	}


	/**
	 * Test that the movies can be retrieved by actor name.
	 */
	@Test
	public void testFindAllMoviewByOneActor() {
		MovieService movieService = new MovieService(movieRepository);
		Collection<Movie> result = movieService.findAllMoviesByActor("Mark Hamill");
		assertNotNull(result);
		assertEquals(2, result.size());

		result = movieService.findAllMoviesByActor("Alec Guinness");
		assertNotNull(result);
		assertEquals(1, result.size());

		result = movieService.findAllMoviesByActor("Billy Dee Williams");
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}