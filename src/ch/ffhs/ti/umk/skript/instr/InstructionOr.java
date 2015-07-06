package ch.ffhs.ti.umk.skript.instr;

public class InstructionOr extends Instruction {

	Object left;
	Object right;

	public InstructionOr(Object left, Object right) {
		super();
		this.left = left;
		this.right = right;
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return null;
	}

}
