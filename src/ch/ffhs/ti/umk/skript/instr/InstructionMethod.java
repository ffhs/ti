package ch.ffhs.ti.umk.skript.instr;

import java.util.List;

public class InstructionMethod extends Instruction {

	final String name;
	final List<String> parameterNames;
	final Instruction block;

	public InstructionMethod(String name, List<String> parameterNames, Instruction block) {
		super();
		this.name = name;
		this.parameterNames = parameterNames;
		this.block = block;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitMethod(this);
	}

}
