package ch.ffhs.ti.umk.skript;

import static ch.ffhs.ti.umk.skript.TestHelper.lines;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MethodTest {

	private final String input;
	private final String expected;

	public MethodTest(String input, String expected) {
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
		// empty declaration only
		testcases.add(new Object[] { lines("function foo() {", "}"), "" });
		testcases.add(new Object[] { lines("function foo(input) {", "}"), "" });
		testcases.add(new Object[] { lines("function foo(a, b) {", "}"), "" });

		// simple
		testcases.add(new Object[] { lines("variable a:=0;", "function foo() {", "42;", "}", "a:=foo();", "a;"), "42" });
		testcases.add(new Object[] { lines("variable a:=5;", "function foo() {", "a:=a+36;", "a;", "}", "a:=foo();", "a;"), "41" });

		// method calling method
		testcases.add(new Object[] { lines("variable a:=0;", "function foo() {", "variable b:=0;", "b:=bar();", "b;", "}", "function bar() {", "13;", "}", "a:=foo();", "a;"), "13" });

		return testcases;
	}
}
