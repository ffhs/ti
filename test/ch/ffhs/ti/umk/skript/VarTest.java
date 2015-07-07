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
public class VarTest {

	private final String input;
	private final String expected;

	public VarTest(String input, String expected) {
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
		
		tests.add(new Object[] { write("var i:=100;", "i+i;"), "200" });
		tests.add(new Object[] { write("var i:=100;", "i-i;"), "0" });
		tests.add(new Object[] { write("var i:=100;", "i*i;"), "10000" });
		tests.add(new Object[] { write("var i:=10;", "i/i;"), "1" });
		tests.add(new Object[] { write("var i:=4;", "i^i;"), "256" });
		
		tests.add(new Object[] { write("var i:=100;", "var x:=10;", "i+x;"), "110" });
		tests.add(new Object[] { write("var i:=100;", "var x:=10;", "i-x;"), "90" });
		tests.add(new Object[] { write("var i:=100;", "var x:=10;", "x-i;"), "-90" });
		tests.add(new Object[] { write("var i:=100;", "var x:=10;", "i*x;"), "1000" });
		tests.add(new Object[] { write("var i:=100;", "var x:=10;", "i/x;"), "10" });
		tests.add(new Object[] { write("var i:=100;", "var x:=2;", "i#x;"), "10" });

		tests.add(new Object[] { write("var i:=100;", "i+i;"), "200" });
		tests.add(new Object[] { write("var i:=0;", "i:=100*2;", "i+i;"), "400" });

		return tests;
	}
}
