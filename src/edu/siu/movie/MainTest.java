package edu.siu.movie;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class MainTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		final int ARRAYSIZE = 500;
		MovieObjectBuilder[] array = new MovieObjectBuilder[ARRAYSIZE];
		MovieObjectBuilder movie = new MovieObjectBuilder("Nightmare On Elm Street", "1985");
		array[0] = movie;
		assertEquals("1985", array[0].getReleaseDate());
	}

}
