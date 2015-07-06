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
public class LoopsTest {

	private final String input;
	private final String expected;

	public LoopsTest(String input, String expected) {
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
		testcases.add(new Object[] { lines("variable countStep:=0;", "FOR (countStep < 7)", "countStep:=countStep+1;", "countStep;", "END"), "7" });
		testcases.add(new Object[] { lines("variable countStep:=0;", "WHILE (countStep < 7)", "countStep:=countStep+1;", "countStep;", "END"), "7" });
		return testcases;
	}

}
