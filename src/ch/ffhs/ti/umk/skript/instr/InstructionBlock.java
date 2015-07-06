package ch.ffhs.ti.umk.skript.instr;

import java.util.List;

public class InstructionBlock extends Instruction {

	Instruction script;

	public InstructionBlock(List<Instruction> assignList, List<Instruction> statementList) {
		super();
		this.script = new InstructionScript(assignList, statementList);
	}

	@Override
	public <R> R acceptVisitor(InstructionVisitor<R> visitor) {
		return visitor.visitScript((InstructionScript) script);
	}

}
