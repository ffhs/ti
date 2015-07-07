package ch.ffhs.ti.umk.skript;

import static org.junit.Assert.assertEquals;
import static ch.ffhs.ti.umk.skript.TestHelper.write;
import static ch.ffhs.ti.umk.skript.TestHelper.UNEXP_RESULT;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import ch.ffhs.ti.umk.skript.ScannerCompiler;

@RunWith(Parameterized.class)
public class ForWhileTest {

	private final String input;
	private final String expected;

	public ForWhileTest(String input, String expected) {
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
		
		tests.add(new Object[] { write("var i:=0;", "FOR (i < 10)", "i:=i+1;", "i;", "END"), "10" });
		
		tests.add(new Object[] { write("var i:=0;", "var b:=0;", "WHILE (i < 10)", "i:=i+1;", "b:=i+10;","b;", "END"), "20" });
		
		return tests;
	}
}
