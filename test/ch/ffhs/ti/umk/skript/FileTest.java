package ch.ffhs.ti.umk.skript;

import static org.junit.Assert.assertEquals;
import static ch.ffhs.ti.umk.skript.TestHelper.evalFile;



import org.junit.Test;

public class FileTest {

	@Test
	public void testSimple() throws Exception {
		String filename = "testsimple.txt";
		String actual = evalFile(filename);

		assertEquals("220", actual);
	}

	
	@Test
	public void testAll() throws Exception {
		String filename = "testall.txt";
		String actual = evalFile(filename);
		assertEquals("11", actual);
	}
	
	
	
	/**
	 * REMOVE
	 * 
	 
	
	@Test
	public void testA() throws Exception {
		String filename = "testA.txt";
		String actual = evalFile(filename);

		assertEquals("6", actual);
	}

	@Test
	public void testB() throws Exception {
		String filename = "testB.txt";
		String actual = evalFile(filename);

		assertEquals("42", actual);
	}
	
	@Test
	public void testFeatureOverview() throws Exception {
		String filename = "feature_overview.txt";
		String actual = evalFile(filename);
		
		assertEquals("11", actual);
	}

	@Test
	public void testFibanacci_11() throws Exception {
		String filename = "fibonacci_11.txt";
		String actual = evalFile(filename);

		assertEquals("89", actual);
	}

	@Test
	public void testFibanacci_13() throws Exception {
		String filename = "fibonacci_13.txt";
		String actual = evalFile(filename);

		assertEquals("233", actual);
	}
*/
	
}
