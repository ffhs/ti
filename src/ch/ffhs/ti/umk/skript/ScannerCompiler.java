package ch.ffhs.ti.umk.skript;

import java.io.Reader;
import java.io.StringReader;
import java.math.BigInteger;

import java_cup.runtime.Symbol;
import ch.ffhs.ti.umk.skript.instr.Evaluator;
import ch.ffhs.ti.umk.skript.instr.Instruction;
import ch.ffhs.ti.umk.skript.instr.Validator;
import ch.ffhs.ti.umk.skript.parser.Parser;
import ch.ffhs.ti.umk.skript.parser.Scanner;

/**
 * Interaktiver Interpreter als Konsonen-Applikation. Ein Skript-Teil wird
 * jeweils nach Eingabe einer Leerzeile ausgeführt.
 * 
 * @author urs-martin
 */
public class ScannerCompiler {

	// Is public for usage in JUnit-Tests
	public static String evaluateInput(String inputScript) throws Exception {
		StringReader reader = new StringReader(inputScript);
		return evaluateInput(reader);
	}

	public static String evaluateInput(Reader reader) throws Exception {
		Parser parse = new Parser(new Scanner(reader));
		Symbol symbol = parse.parse();
		Instruction instr = (Instruction) symbol.value;

		validate(instr);

		return evaluate(instr);
	}

	private static String evaluate(Instruction instr) {
		Evaluator evaluator = new Evaluator();
		BigInteger result = instr.acceptVisitor(evaluator);

		if (result == null) {
			return "";
		}

		return result.toString();
	}

	private static void validate(Instruction instr) {
		Validator validator = new Validator();
		instr.acceptVisitor(validator);
		if (!validator.getUndefinedVariables().isEmpty()) {
			System.out.println("Error: Undefined variables: " + validator.getUndefinedVariables());
		}
		if (!validator.getUnusedVariables().isEmpty()) {
			System.out.println("Warning: Unused variables: " + validator.getUnusedVariables());
		}
	}
}
