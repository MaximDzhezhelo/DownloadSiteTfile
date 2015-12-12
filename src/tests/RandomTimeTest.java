package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ua.kiev.makson.timer.RandomTime;

public class RandomTimeTest {
	private RandomTime time;

	@Before
	public void setUpBefore() throws Exception {
		time = new RandomTime();
	}

	@Test
	public void testCheckOneRandomTimeAuthentication() {
		int t = time.getRandomTimeAuthentication();
		assertNotNull(t);
		assertTrue(t >= 0 && t <= 50);
		assertTrue(time.getX() == 1);
	}

	@Test
	public void testCheckSecondRandomTimeAuthentication() {
		time = new RandomTime();

		int t = time.getRandomTimeAuthentication();
		t = time.getRandomTimeAuthentication();
		t = time.getRandomTimeAuthentication();
		assertNotNull(t);
		assertTrue(time.getX() == 3);
	}

	@Test
	public void testRandomGetRequests() {
		time = new RandomTime();
		int t = time.getRandomGetRequests();
		assertNotNull(t);
		assertTrue(t == 2);
		t = time.getRandomGetRequests();
		assertTrue(t >= 7200 && t <= 8500);
		t = time.getRandomGetRequests();
		t = time.getRandomGetRequests();
		t = time.getRandomGetRequests();
		t = time.getRandomGetRequests();
		assertTrue(t >= 7200 && t <= 8500);
	}

	@Test
	public void testRandomDownload() {
		time = new RandomTime();
		int t = time.getRandomDownload();
		assertNotNull(t);
		assertTrue(t >= 1 && t <= 7);
	}
}
