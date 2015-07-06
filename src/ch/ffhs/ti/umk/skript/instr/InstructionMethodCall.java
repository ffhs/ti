package ch.ffhs.ti.umk.skript.instr;

import java.util.List;

public class InstructionMethodCall extends Instruction {

	final String name;
	final List<Instruction> arguments;

	public InstructionMethodCall(String name, List<Instruction> arguments) {
		super();
		this.name = name;
		this.arguments = arguments;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitMethodCall(this);
	}

}
