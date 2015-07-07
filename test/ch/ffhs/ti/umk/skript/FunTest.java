package ch.ffhs.ti.umk.skript;

import static ch.ffhs.ti.umk.skript.TestHelper.write;
import static ch.ffhs.ti.umk.skript.TestHelper.UNEXP_RESULT;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FunTest {

	private final String input;
	private final String expected;

	public FunTest(String input, String expected) {
		super();
		this.input = input;
		this.expected = expected;
	}

	@Test
	public void execTest() throws Exception {
		String actual = ScannerCompiler.evaluateInput(input);
		assertEquals(UNEXP_RESULT + input, expected, actual);
	}

	@Parameters
	public static Collection<Object[]> createTestcases() {
		Collection<Object[]> tests = new ArrayList<>();

		tests.add(new Object[] { write("var i:=10;", "fun blub() {", "100;", "}", "i:=blub();", "i;"), "100" });
		tests.add(new Object[] { write("var i:=10;", "fun calc() {", "i:=i+100*3;", "i;", "}", "i:=calc();", "i;"), "310" });

		tests.add(new Object[] { write("var i:=10;", "fun blub() {", "var t:=100;", "t:=gett();", "t;", "}", "fun gett() {", "2*2;", "}", "i:=blub();", "i;"), "4" });

		return tests;
	}
}
