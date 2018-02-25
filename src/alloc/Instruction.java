package alloc;

public class Instruction {
	String opcode;
	String[] sources;
	String[] targets;
	
	public Instruction(String opcode, String[] sources, String[] targets) {
		this.opcode = opcode;
		this.sources = sources;
		this.targets = targets;
	}
	
	public static Instruction parseInstruction(String instruction) {
		
		return null;
	}
	
	
}
