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
public class MathTest {

	private final String input;
	private final String expected;

	public MathTest(String input, String expected) {
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
		
		tests.add(new Object[] { write("6+8;"), "14" });
		
		tests.add(new Object[] { write("22-31;"), "-9" });
		tests.add(new Object[] { write("44-4;"), "40" });
		
		tests.add(new Object[] { write("9/3;"), "3" });
		tests.add(new Object[] { write("10/30;"), "0" }); 
		
		tests.add(new Object[] { write("10*2;"), "20" });
		tests.add(new Object[] { write("6%6;"), "0" });
		tests.add(new Object[] { write("6%8;"), "6" });
		
		tests.add(new Object[] { write("6^3;"), "216" });
		
		tests.add(new Object[] { write("64#2;"), "8" });
		tests.add(new Object[] { write("64#3;"), "4" });
		tests.add(new Object[] { write("64#4;"), "3" });

		tests.add(new Object[] { write("2*(-3);"), "-6" });
		tests.add(new Object[] { write("-2*(-8);"), "16" });
		

		
		tests.add(new Object[] { write("16#;"), "4" });
		tests.add(new Object[] { write("16#2;"), "4" });
		tests.add(new Object[] { write("256#3;"), "6" });
		
		tests.add(new Object[] { write("100§10;"), "2" });
		tests.add(new Object[] { write("100§;"), "4" });

		tests.add(new Object[] { write("2*10+20/5+2;"), "26" });
		tests.add(new Object[] { write("10/2+40-10+3*10-(-10);"), "75" });
		return tests;
	}
}
