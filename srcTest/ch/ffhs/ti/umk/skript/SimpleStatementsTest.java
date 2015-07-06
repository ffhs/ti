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
public class SimpleStatementsTest {

	private final String input;
	private final String expected;

	public SimpleStatementsTest(String input, String expected) {
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
		testcases.add(new Object[] { lines(""), "" });
		testcases.add(new Object[] { lines("6;"), "6" });
		testcases.add(new Object[] { lines("variable a:=5;", "a;"), "5" });
		return testcases;
	}

}
