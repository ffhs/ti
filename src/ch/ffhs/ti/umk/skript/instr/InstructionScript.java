package ch.ffhs.ti.umk.skript.instr;

import java.util.ArrayList;
import java.util.List;

public class InstructionScript extends Instruction {
	final List<Instruction> assignments;
	final List<Instruction> methods;
	final List<Instruction> instructions;

	public InstructionScript(Instruction instruction) {
		this.assignments = new ArrayList<>();
		this.methods = new ArrayList<>();
		this.instructions = new ArrayList<>();
		instructions.add(instruction);
	}

	public InstructionScript(List<Instruction> assignments, List<Instruction> instructions) {
		this.assignments = assignments;
		this.methods = new ArrayList<>();
		this.instructions = instructions;
	}

	public InstructionScript(List<Instruction> instructions) {
		this.assignments = new ArrayList<>();
		this.methods = new ArrayList<>();
		this.instructions = instructions;
	}

	public InstructionScript(List<Instruction> assignments, List<Instruction> methods, List<Instruction> instructions) {
		this.assignments = assignments;
		this.methods = methods;
		this.instructions = instructions;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitScript(this);
	}

}
