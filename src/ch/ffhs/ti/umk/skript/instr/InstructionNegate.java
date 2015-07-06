package ch.ffhs.ti.umk.skript.instr;

public class InstructionNegate extends Instruction {
	final Instruction operand;

	public InstructionNegate(Instruction operand) {
		this.operand = operand;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitNegation(this);
	}

}
