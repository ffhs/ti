package ch.ffhs.ti.umk.skript.instr;

public class InstructionAnd extends Instruction {

	Object left;
	Object right;

	public InstructionAnd(Object left, Object right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return null;
	}

}
