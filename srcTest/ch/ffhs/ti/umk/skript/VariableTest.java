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
public class VariableTest {

	private final String input;
	private final String expected;

	public VariableTest(String input, String expected) {
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
		// basics (eine variable)
		testcases.add(new Object[] { lines("var a:=5;", "a+a;"), "10" });
		testcases.add(new Object[] { lines("var a:=5;", "a-a;"), "0" });
		testcases.add(new Object[] { lines("var a:=5;", "a*a;"), "25" });
		testcases.add(new Object[] { lines("var a:=10;", "a/a;"), "1" });
		testcases.add(new Object[] { lines("var a:=2;", "a%a;"), "0" });
		testcases.add(new Object[] { lines("var a:=2;", "a^a;"), "4" });
		// basics (2 variablen)
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "a+b;"), "7" });
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "a-b;"), "3" });
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "a*b;"), "10" });
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "a/b;"), "2" });
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "a%b;"), "1" });
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "a^b;"), "25" });

		// schreibend
		testcases.add(new Object[] { lines("var b:=0;", "b:=5;", "b+b;"), "10" });
		testcases.add(new Object[] { lines("var b:=2;", "b:=2+3;", "b+b;"), "10" });
		testcases.add(new Object[] { lines("var a:=5;", "var b:=2;", "b:=a+b;", "a+b;"), "12" });

		return testcases;
	}


}
