package ch.ffhs.ti.umk.skript.instr;

public interface InstructionVisitor<R> {
	public R visitScript(InstructionScript instructionScript);

	public R visitBinaryOperation(InstructionBinaryOperation instructionBinOperation);
	
	public R visitOneVarOperation(InstructionOneVarOperation instructionOneVarOperation);
	
	public R visitSymbolOperation(InstructionSymbolOperation instructionSymbolOperation);

	public R visitNegation(InstructionNegate instructionUnaryOperation);

	public R visitConstant(InstructionConstant instructionConstant);

	public R visitGetVariable(InstructionGetVariable instructionGetVariable);

	public R visitSetVariable(InstructionSetVariable instructionSetVariable);
	
	public R visitInitVariable(InstructionInitVariable instructionSetVariable);

	public Boolean visitBooleanCondition(Object condition);

	R visitMethod(InstructionMethod method);

	R visitMethodCall(InstructionMethodCall methodCall);
}
