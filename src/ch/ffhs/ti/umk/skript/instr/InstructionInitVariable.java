package ch.ffhs.ti.umk.skript.instr;

public class InstructionInitVariable extends Instruction {
	final String name;
	final Instruction value;

	public InstructionInitVariable(String name, Instruction value) {
		this.name = name;
		this.value = value;
	}

	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitInitVariable(this);
	}

}
