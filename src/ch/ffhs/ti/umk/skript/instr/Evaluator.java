package ch.ffhs.ti.umk.skript.instr;

import java.util.HashMap;
import java.util.Map;
import java.math.*;


/**
 * Ein Evaluator zum Auswerten von Instructions. Die Klasse ist eine Visitorklasse und wird wie folgt verwendet: instruction.acceptVisitor(evaluator);
 * 
 * @author urs-martin
 */
public class Evaluator implements InstructionVisitor<BigInteger> {
	/** Eine Map mit Namen-Wert Paaren für Variable */

	private Context context;
	private final Map<String, InstructionMethod> contextMethods;

	/**
	 * Erzeugt einen Evaluator mit leerem Context und leerer FunktionsLibrary.
	 */
	public Evaluator() {
		this(new Context(), new HashMap<String, InstructionMethod>());
	}

	/**
	 * Erzeugt einen Evaluator
	 * 
	 * @param context
	 *            Vordefinierte Variablenwerte
	 */
	public Evaluator(Context context, Map<String, InstructionMethod> methods) {
		this.context = context;
		this.contextMethods = methods;
	}

	// Ohne weitere Kommentare: Auswertungsmethoden für alle Instruktionstypen.

	@Override
	public BigInteger visitBinaryOperation(InstructionBinaryOperation instructionBinOp) {
		BigInteger left = instructionBinOp.leftOperand.acceptVisitor(this);
		BigInteger right = instructionBinOp.rightOperand.acceptVisitor(this);
		
		switch (instructionBinOp.operator) {
		case PLUS:
			return left.add(right);
		case MINUS:
			return left.subtract(right) ;
		case TIMES:
			return left.multiply(right);
		case DIV:
			return left.divide(right);
		case MOD:
			return left.mod(right);
		case POW:
			return left.pow(right.intValue());
		case ROO:
			return BigInteger.valueOf((long) Math.pow(left.doubleValue(), 1/right.doubleValue()));
		default:
			assert false;
			return null;
		}
	}

	@Override
	public Boolean visitBooleanCondition(Object condition) {
		if (condition instanceof InstructionCompareOpertor) {
			InstructionCompareOpertor instructionCompare = (InstructionCompareOpertor) condition;

			BigInteger left = instructionCompare.leftOperand.acceptVisitor(this);
			BigInteger right = instructionCompare.rightOperand.acceptVisitor(this);
			BinaryOperator compareOperator = instructionCompare.operator;

			int leftValue = left.intValue();
			int rightValue = right.intValue();
			switch (compareOperator) {
			case EQUAL:
				return leftValue == rightValue;

			case NOTEQUAL:
				return leftValue != rightValue;

			case LESS:
				return leftValue < rightValue;

			case BIGGER:
				return leftValue > rightValue;

			default:
				throw new RuntimeException("Unexpected type of variable 'condition'.");

			}

		} else if (condition instanceof InstructionOr) {
			InstructionOr or = (InstructionOr) condition;
			Boolean left = visitBooleanCondition(or.left);
			Boolean right = visitBooleanCondition(or.right);
			return left || right;
		} else if (condition instanceof InstructionAnd) {
			InstructionAnd or = (InstructionAnd) condition;
			Boolean left = visitBooleanCondition(or.left);
			Boolean right = visitBooleanCondition(or.right);
			return left && right;
		} else {
			return (Boolean) condition;
		}
	}

	@Override
	public BigInteger visitConstant(InstructionConstant instructionConstant) {
		return (BigInteger) instructionConstant.value;
	}

	@Override
	public BigInteger visitGetVariable(InstructionGetVariable instructionGetVariable) {
		String name = instructionGetVariable.name;
		BigInteger value = this.context.getValue(name);
		return value;
	}

	@Override
	public BigInteger visitNegation(InstructionNegate instructionNegate) {
		BigInteger operand = instructionNegate.operand.acceptVisitor(this);
		return operand.negate();
	}

	@Override
	public BigInteger visitScript(InstructionScript instructionScript) {
		for (Instruction instr : instructionScript.assignments) {
			instr.acceptVisitor(this);
		}

		if (instructionScript.methods != null) {
			for (Instruction method : instructionScript.methods) {
				method.acceptVisitor(this);
			}
		}

		BigInteger result = null;
		for (Instruction instr : instructionScript.instructions) {
			if (instr != null) {
				result = instr.acceptVisitor(this);
			}
		}

		return result;
	}

	@Override
	public BigInteger visitSetVariable(InstructionSetVariable instructionSetVariable) {
		BigInteger evaluatedValue = instructionSetVariable.value.acceptVisitor(this);
		context.changeValue(instructionSetVariable.name, evaluatedValue);
		return null;
	}

	@Override
	public BigInteger visitInitVariable(InstructionInitVariable instructionSetVariable) {
		BigInteger evaluatedValue = instructionSetVariable.value.acceptVisitor(this);
		context.setValue(instructionSetVariable.name, evaluatedValue);
		return null;
	}

	@Override
	public BigInteger visitMethod(InstructionMethod method) {
		this.contextMethods.put(method.name, method);
		return null;
	}

	@Override
	public BigInteger visitMethodCall(InstructionMethodCall methodCall) {
		String name = methodCall.name;
		if (!contextMethods.containsKey(name)) {
			throw new RuntimeException("Method " + name + " not initialized.");
		}
		Context prevContext = context;
		// erstelle neuen context
		this.context = new Context(context);

		InstructionMethod method = contextMethods.get(name);
		// init context mit parameter
		for (int i = 0; i < method.parameterNames.size(); i++) {
			Instruction paramInstruction = methodCall.arguments.get(i);
			BigInteger paramValue = paramInstruction.acceptVisitor(this);
			context.setValue(method.parameterNames.get(i), paramValue);
		}

		BigInteger blockValue = method.block.acceptVisitor(this);

		// setze contexte zurueck
		context = prevContext;

		return blockValue;
	}

}
