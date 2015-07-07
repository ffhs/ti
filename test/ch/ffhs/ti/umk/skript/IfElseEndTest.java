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

import ch.ffhs.ti.umk.skript.ScannerCompiler;

@RunWith(Parameterized.class)
public class IfElseEndTest {

	private final String input;
	private final String expected;

	public IfElseEndTest(String input, String expected) {
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

		tests.add(new Object[] { write("IF (false)", "100;", "END"), "" });
		tests.add(new Object[] { write("IF (true)", "100;", "END"), "100" });
		tests.add(new Object[] { write("IF (4>6)", "100;", "END"), "" });
		tests.add(new Object[] { write("IF (4<6)", "100;", "END"), "100" });
		
		tests.add(new Object[] { write("IF (false)", "100;", "ELSE", "200;", "END"), "200" });
		tests.add(new Object[] { write("IF (true)", "100;", "ELSE", "200;", "END"), "100" });
		tests.add(new Object[] { write("IF (4>6)", "100;", "ELSE", "200;", "END"), "200" });
		tests.add(new Object[] { write("IF (4<6)", "100;", "ELSE", "200;", "END"), "100" });

		tests.add(new Object[] { write("IF (false)", "100;", "ELSE", "200;", "END", "20*2+2;"), "42" });

		tests.add(new Object[] { write("IF (false OR false)", "100;", "END"), "" });
		tests.add(new Object[] { write("IF (false OR true)", "100;", "END"), "100" });
		tests.add(new Object[] { write("IF (true OR false)", "100;", "END"), "100" });
		tests.add(new Object[] { write("IF (true OR true)", "100;", "END"), "100" });
		tests.add(new Object[] { write("IF (4<6 OR 4>6)", "100;", "END"), "100" });

		tests.add(new Object[] { write("IF (false AND false)", "100;", "END"), "" });
		tests.add(new Object[] { write("IF (false AND true)", "100;", "END"), "" });
		tests.add(new Object[] { write("IF (true AND false)", "100;", "END"), "" });
		tests.add(new Object[] { write("IF (true AND true)", "100;", "END"), "100" });
		
		return tests;
	}

}
