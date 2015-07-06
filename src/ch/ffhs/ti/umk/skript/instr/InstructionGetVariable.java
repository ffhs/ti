package ch.ffhs.ti.umk.skript.instr;

public class InstructionGetVariable extends Instruction {
	final String name;

	public InstructionGetVariable(String name) {
		this.name = name;
	}

	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitGetVariable(this);
	}
}
