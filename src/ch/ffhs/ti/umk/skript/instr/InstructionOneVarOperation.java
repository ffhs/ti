package ch.ffhs.ti.umk.skript.instr;

public class InstructionOneVarOperation  extends Instruction{
	final BinaryOperator operator;

	final Instruction operand;


	public InstructionOneVarOperation(BinaryOperator operator, Instruction operand) {
		this.operator = operator;
		this.operand = operand;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitOneVarOperation(this);
	}
}
