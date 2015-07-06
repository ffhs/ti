package ch.ffhs.ti.umk.skript.instr;

/**
 * Basisklasse f�r alle Instruktionen.
 * 
 * @author urs-martin
 */
public abstract class Instruction {
	public abstract <R> R acceptVisitor(InstructionVisitor<R> visitor);
}
