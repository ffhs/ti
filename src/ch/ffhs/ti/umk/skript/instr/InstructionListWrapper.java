package ch.ffhs.ti.umk.skript.instr;

import java.util.List;

public class InstructionListWrapper extends Instruction {

	final List<Instruction> instructions;

	public InstructionListWrapper(List<Instruction> instructions) {
		super();
		this.instructions = instructions;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		R result = null;
		for (Instruction instruction : instructions) {
			if (instruction != null) {
				result = instruction.acceptVisitor(visitor);
			}
		}
		return result;
	}

}
