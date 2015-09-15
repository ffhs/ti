package ch.ffhs.ti.umk.skript.instr;

public class InstructionSymbolOperation  extends Instruction{
	final BinaryOperator operator;

	public InstructionSymbolOperation(BinaryOperator operator) {
		this.operator = operator;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitSymbolOperation(this);
	}

}
