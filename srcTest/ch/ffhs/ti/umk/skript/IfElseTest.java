package ch.ffhs.ti.umk.skript;

import static ch.ffhs.ti.umk.skript.TestHelper.lines;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.ffhs.ti.umk.skript.ScannerCompiler;

@RunWith(Parameterized.class)
public class IfElseTest {

	private final String input;
	private final String expected;

	public IfElseTest(String input, String expected) {
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
		// if
		testcases.add(new Object[] { lines("IF (true)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (false)", "19;", "END"), "" });
		testcases.add(new Object[] { lines("IF (1<2)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (1>2)", "19;", "END"), "" });
		// if else
		testcases.add(new Object[] { lines("IF (true)", "19;", "ELSE", "42;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (false)", "19;", "ELSE", "42;", "END"), "42" });
		testcases.add(new Object[] { lines("IF (1<2)", "19;", "ELSE", "42;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (1>2)", "19;", "ELSE", "42;", "END"), "42" });
		// embedded
		testcases.add(new Object[] { lines("6+7;", "IF (true)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (false)", "19;", "END", "6+7;"), "13" });
		testcases.add(new Object[] { lines("6+7;", "IF (true)", "19;", "ELSE", "42;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (false)", "19;", "ELSE", "42;", "END", "6+7;"), "13" });

		// OR
		testcases.add(new Object[] { lines("IF (true OR true)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (true OR false)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (false OR true)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (false OR false)", "19;", "END"), "" });
		testcases.add(new Object[] { lines("IF (1<2 OR 3<4)", "19;", "END"), "19" });
		// AND
		testcases.add(new Object[] { lines("IF (true AND true)", "19;", "END"), "19" });
		testcases.add(new Object[] { lines("IF (true AND false)", "19;", "END"), "" });
		testcases.add(new Object[] { lines("IF (false AND true)", "19;", "END"), "" });
		testcases.add(new Object[] { lines("IF (false AND false)", "19;", "END"), "" });
		
		return testcases;
	}

}
