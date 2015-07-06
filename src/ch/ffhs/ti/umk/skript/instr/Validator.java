package ch.ffhs.ti.umk.skript.instr;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Ein Validator, der kontrolliert, ob verwendete Variablen deklariet sind und ob eine zugewiesene Variable auch verwendet wird. Die Klasse ist eine Visitorklasse; der Rückgabewert der visit-Methoden ist immer null (ist nur hier, da das
 * Visitor-Interface das verlangt.) Verwendung: instruction.acceptVisitor(validator);
 * 
 * @author urs-martin
 */
public class Validator implements InstructionVisitor<Object> {
	/** Ein Set mit den definierten Variablen. */
	private Set<String> definedVariables = new HashSet<String>();

	/** Ein Set mit den nicht definierten Variablen. */
	private Set<String> undefinedVariables = new HashSet<String>();

	/** Ein Set mit den definierten, aber nicht verwendeten Variablen. */
	private Set<String> unusedVariables = new HashSet<String>();

	/** Ein Set mit den definierten Methoden. */
	private Set<String> definedMethods = new HashSet<String>();

	/** Ein Set mit den definierten, aber nicht verwendeten Methoden. */
	private Set<String> unusedMethods = new HashSet<String>();

	/** Ein Set mit den nicht definierten Methoden. */
	private Set<String> undefinedMethod = new HashSet<String>();

	/**
	 * Liefert alle Variablen, die verwendet werden, aber zum Zeitpunkt der Verwendung nicht definiert sind. Muss leer sein, sonst läuft Skript nicht.
	 * 
	 * @return Menge der nicht definierten Variablen.
	 */
	public Set<String> getUndefinedVariables() {
		return undefinedVariables;
	}

	/**
	 * Liefert alle Variablen, die nach einer Zuordnung nicht mehr verwendet werden. Sollte leer sein.
	 * 
	 * @return Menge der nicht verwendeten Variablen.
	 */
	public Set<String> getUnusedVariables() {
		return unusedVariables;
	}

	// Ohne weitere Kommentare: Auswertungsmethoden f�r alle Instruktionstypen.

	@Override
	public Object visitScript(InstructionScript instructionScript) {
		for (Instruction instr : instructionScript.assignments) {
			instr.acceptVisitor(this);
		}

		for (Instruction instr : instructionScript.instructions) {
			// ForWhile & IfElse muessen besonders validiert werden und duerfen
			// nicht "normal" aufgerufen werden. Denn die instructions sollen
			// unabhaengig von der Condition validiert werden
			if (instr instanceof InstructionForWhile) {
				InstructionForWhile forWhile = (InstructionForWhile) instr;
				visitBooleanCondition(forWhile.condition);
				List<Instruction> subInstructions = forWhile.instructions;
				for (Instruction subInstr : subInstructions) {
					subInstr.acceptVisitor(this);
				}
			}
			if (instr instanceof InstructionIfElse) {
				InstructionIfElse ifElseIf = (InstructionIfElse) instr;
				visitBooleanCondition(ifElseIf.condition);

				if (ifElseIf.instruction1 != null) {
					ifElseIf.instruction1.acceptVisitor(this);
				}
				if (ifElseIf.instruction2 != null) {
					ifElseIf.instruction2.acceptVisitor(this);
				}
			} else if (instr != null) {
				instr.acceptVisitor(this);
			}
		}

		return null;
	}

	@Override
	public Object visitConstant(InstructionConstant instructionConstant) {
		return null;
	}

	@Override
	public Object visitGetVariable(InstructionGetVariable instructionGetVariable) {

		if (definedVariables.contains(instructionGetVariable.name)) {
			unusedVariables.remove(instructionGetVariable.name);
		} else {
			undefinedVariables.add(instructionGetVariable.name);
		}
		return null;
	}

	@Override
	public Object visitSetVariable(InstructionSetVariable instructionSetVariable) {
		instructionSetVariable.value.acceptVisitor(this);
		return null;
	}

	@Override
	public Object visitInitVariable(InstructionInitVariable instructionSetVariable) {
		definedVariables.add(instructionSetVariable.name);
		unusedVariables.add(instructionSetVariable.name);
		return null;
	}

	@Override
	public Object visitBinaryOperation(InstructionBinaryOperation instructionBinOp) {
		instructionBinOp.leftOperand.acceptVisitor(this);
		instructionBinOp.rightOperand.acceptVisitor(this);
		return null;
	}

	@Override
	public Object visitNegation(InstructionNegate instructionNegate) {
		instructionNegate.operand.acceptVisitor(this);
		return null;
	}

	@Override
	public Boolean visitBooleanCondition(Object condition) {
		if (condition instanceof InstructionCompareOpertor) {
			((InstructionCompareOpertor) condition).leftOperand.acceptVisitor(this);
			((InstructionCompareOpertor) condition).rightOperand.acceptVisitor(this);
		}
		return false;
	}

	@Override
	public Object visitMethod(InstructionMethod method) {
		definedMethods.add(method.name);
		unusedMethods.add(method.name);
		definedVariables.addAll(method.parameterNames);

		if (method.block instanceof InstructionBlock) {
			visitScript((InstructionScript) ((InstructionBlock) method.block).script);
		}
		return null;
	}

	@Override
	public Object visitMethodCall(InstructionMethodCall methodCall) {
		if (definedMethods.contains(methodCall.name)) {
			unusedMethods.remove(methodCall.name);
		} else {
			undefinedMethod.add(methodCall.name);
		}
		return null;
	}
}
