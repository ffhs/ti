package ch.ffhs.ti.umk.skript;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class FilebasedTest {

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

	private String evalFile(String filename) throws URISyntaxException, IOException, Exception {
		URI uri = FilebasedTest.class.getClassLoader().getResource(filename).toURI();
		BufferedReader reader = Files.newBufferedReader(Paths.get(uri), Charset.forName("UTF-8"));
		return ScannerCompiler.evaluateInput(reader);
	}

}
