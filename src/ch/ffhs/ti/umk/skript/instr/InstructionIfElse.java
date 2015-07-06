package ch.ffhs.ti.umk.skript.instr;

import java.util.List;

public class InstructionIfElse extends Instruction {
	final Object condition;
	final Instruction instruction1;
	final Instruction instruction2;

	public InstructionIfElse(List<Instruction> instruction1, List<Instruction> instruction2, Object condition) {
		this.instruction1 = new InstructionListWrapper(instruction1);
		this.instruction2 = new InstructionListWrapper(instruction2);
		this.condition = condition;
	}

	public InstructionIfElse(List<Instruction> instruction, Object condition) {
		this.instruction1 = new InstructionListWrapper(instruction);
		this.instruction2 = null;
		this.condition = condition;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		boolean conditionResult = visitor.visitBooleanCondition(condition);

		if (conditionResult) {
			if (instruction1 == null) {
				return null;
			}

			return instruction1.acceptVisitor(visitor);
		}
		if (instruction2 != null) {
			return instruction2.acceptVisitor(visitor);
		}
		return null;
	}
}
