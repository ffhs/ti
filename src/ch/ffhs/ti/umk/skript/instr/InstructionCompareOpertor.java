package ch.ffhs.ti.umk.skript.instr;

public class InstructionCompareOpertor extends Instruction {

	final BinaryOperator operator;

	final Instruction leftOperand;

	final Instruction rightOperand;

	public InstructionCompareOpertor(BinaryOperator operator, Instruction leftOperand, Instruction rightOperand) {
		this.operator = operator;
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return null;
	}
}
