package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ua.kiev.makson.controller.ConvertPassword;

public class ConvertPasswordTest {
	ConvertPassword convert;

	@Before
	public void setUp() throws Exception {
		convert = new ConvertPassword();
	}

	@Test
	public void testPassNull() {
		char[] pass = null;
		String result = convert.getPassword(pass);
		assertNull(result);
	}

	@Test
	public void testPassLetters() {
		char[] pass = { 'a', 'b', 'c', 'd', 'e' };
		String result = convert.getPassword(pass);
		assertTrue(result.equals("abcde"));
	}

	@Test
	public void testPassLetter() {
		char[] pass = { 'a' };
		String result = convert.getPassword(pass);
		assertTrue(result.equals("a"));
	}

	@Test
	public void testPassDash() {
		char[] pass = { '-' };
		String result = convert.getPassword(pass);
		assertTrue(result.equals("-"));
	}

	@Test
	public void testPassSixDash() {
		char[] pass = { '-', '-', '-', '-', '-', '-' };
		String result = convert.getPassword(pass);
		assertTrue(result.equals("------"));
	}

	@Test
	public void testPassLettersDash() {
		char[] pass = { '-', 'w', '-', 'e', '-', 'a' };
		String result = convert.getPassword(pass);
		assertTrue(result.equals("-w-e-a"));
	}

	@Test
	public void testPassNumbersDash() {
		char[] pass = { '-', '1', '-', '2', '-', '3' };
		String result = convert.getPassword(pass);
		assertTrue(result.equals("-1-2-3"));
	}
}
