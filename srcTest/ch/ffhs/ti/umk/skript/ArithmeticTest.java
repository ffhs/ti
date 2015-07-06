package ch.ffhs.ti.umk.skript;

import static org.junit.Assert.assertEquals;
import static ch.ffhs.ti.umk.skript.TestHelper.lines;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.ffhs.ti.umk.skript.ScannerCompiler;

@RunWith(Parameterized.class)
public class ArithmeticTest {

	private final String input;
	private final String expected;

	public ArithmeticTest(String input, String expected) {
		super();
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void execTest() throws Exception {
		String actual = ScannerCompiler.evaluateInput(input);
		assertEquals("Unexpected Result for input " + input, expected, actual);
	}

	@Parameters
	public static Collection<Object[]> createTestcases() {
		Collection<Object[]> testcases = new ArrayList<>();
		// basics
		testcases.add(new Object[] { lines("4+2;"), "6" });
		testcases.add(new Object[] { lines("4-2;"), "2" });
		testcases.add(new Object[] { lines("2-4;"), "-2" });
		testcases.add(new Object[] { lines("4*2;"), "8" });
		testcases.add(new Object[] { lines("4/2;"), "2" });
		testcases.add(new Object[] { lines("2/4;"), "0" }); // Nur ganzzahl unterstuetzt
		testcases.add(new Object[] { lines("4%2;"), "0" });
		testcases.add(new Object[] { lines("4^2;"), "16" });
		// negiert
		testcases.add(new Object[] { lines("4*(-2);"), "-8" });
		testcases.add(new Object[] { lines("-4*(-2);"), "8" });
		// kombiniert
		testcases.add(new Object[] { lines("4+4+4+4;"), "16" });
		testcases.add(new Object[] { lines("4+(4-4);"), "4" });
		//multilines
		testcases.add(new Object[] { lines("4+2;","4-2;"), "2" }); // nur das letzte
		return testcases;
	}

}
