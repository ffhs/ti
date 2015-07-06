package ch.ffhs.ti.umk.skript.instr;

import java.util.ArrayList;

public class InstructionForWhile extends Instruction {
	final Object condition;
	ArrayList<Instruction> instructions;

	public InstructionForWhile(ArrayList<Instruction> instructions, Object condition) {
		this.instructions = instructions;
		this.condition = condition;
	}

	public InstructionForWhile(Instruction instruction, Object condition) {
		this.instructions = new ArrayList<Instruction>();
		this.instructions.add(instruction);
		this.condition = condition;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		R result = null;
		while (visitor.visitBooleanCondition(condition)) {
			for (Instruction instr : instructions) {
				result = instr.acceptVisitor(visitor);
				// TODO: Return only the result from the last call. That's
				// wrong.
			}
		}

		return result;
	}
}
