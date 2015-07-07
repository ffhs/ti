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
		assertEquals("8", actual);
	}
}
