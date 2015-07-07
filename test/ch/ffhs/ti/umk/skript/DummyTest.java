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
public class DummyTest {

	private final String input;
	private final String expected;

	public DummyTest(String input, String expected) {
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
	public static Collection<Object[]> createtests() {
		Collection<Object[]> tests = new ArrayList<>();
		
		tests.add(new Object[] { write(""), "" });
		tests.add(new Object[] { write("100;"), "100" });
		tests.add(new Object[] { write("var i:=100;", "i;"), "100" });
		
		return tests;
	}

}
