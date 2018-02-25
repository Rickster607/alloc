package alloc;
import java.util.ArrayList;

public class Instruction {
	String opcode;
	String[] sources;
	String[] targets;
	ArrayList<Register> registersNeeded;
	int instructionNumber;
	int numRegistersNeeded;
	
	public Instruction(String opcode, String[] sources, String[] targets, int iNum, int nr) {
		this.opcode = opcode;
		this.sources = sources;
		this.targets = targets;
		this.registersNeeded = new ArrayList<Register>();
		this.instructionNumber = iNum;
		this.numRegistersNeeded = nr;
	}
	
	public static void addInstruction(String instruction) {
		if (!(instruction.startsWith("//") || instruction.startsWith("#")
				|| (instruction == null) || instruction.isEmpty())) {
			Instruction tmp = null;
			tmp = parseInstruction(instruction);
			Register.buildRegisterArray(tmp);
			Allocator.block.add(tmp);
		}
		return;
	}
	
	public static Instruction parseInstruction(String instruction) {
		String[] out = instruction.split("\t");
		String oc = null;
		String[] sc = {null, null};
		String[] tg = {null, null};
		oc = out[1];
		sc = out[2].split(", ");
		if (out.length == 4)
			tg = out[3].substring(out[3].indexOf('>') + 2).split(", ");
		else
			tg = null;
		int in = Allocator.instructionNumber++;
		int nr = calculateNumRegistersNeeded(sc, tg);
		return new Instruction(oc, sc, tg, in, nr);
	}
	
	/*public static void formatInstructions() {
		for (int i = 0; i < Allocator.block.size(); i ++) {
			Allocator.block.get(i).formatInstruction();
		}
		return;
	}*/
	
	/*public void formatInstruction() {
		String[] tmp = null;
		if (this.sources[0].contains("r")) {	
			tmp = this.sources[0].split("r");
			this.sources[0] = tmp[1];
		}
		if (this.sources.length == 2) {
			if (this.sources[1].contains("r")) {
				tmp = this.sources[1].split("r");
				this.sources[1] = tmp[1];
			}
		}
		tmp = null;
		if (this.targets != null) {
			if (this.targets[0].contains("r")) {
				tmp = this.targets[0].split("r");
				this.targets[0]= tmp[1];
			}
			if (this.targets.length == 2) {
				if (this.targets[1].contains("r")) {
					tmp = this.targets[1].split("r");
					this.targets[1] = tmp[1];
				}
			}
		}
		return;
	}*/

	/*public void calculateRegistersNeeded() {
	String[] tmp = null;
	int rNum = 0;
	if (this.sources[0].contains("r")) {	
		tmp = this.sources[0].split("r");
		rNum = Integer.valueOf(tmp[1]);
	}
	if (this.sources.length == 2) {
		if (this.sources[1].contains("r")) {
			tmp = this.sources[1].split("r");
			this.sources[1] = tmp[1];
		}
	}
	tmp = null;
	if (this.targets != null) {
		if (this.targets[0].contains("r")) {
			tmp = this.targets[0].split("r");
			this.targets[0]= tmp[1];
		}
		if (this.targets.length == 2) {
			if (this.targets[1].contains("r")) {
				tmp = this.targets[1].split("r");
				this.targets[1] = tmp[1];
			}
		}
	}
	return;
}*/
	
	/*public void calculateRegistersNeeded2() {
		int tmp = 0;
		if (this.sources[0].contains("r")) {
			tmp++;
			if (this.sources.length == 2) {
				if (this.sources[1].contains("r")) {
					tmp++;
				}
			}
		}
		if (this.targets != null) {
			if (this.targets[0].contains("r")) {
				tmp++;
			}
			if (this.targets.length == 2) {
				if (this.targets[1].contains("r")) {
					tmp++;
				}
			}
		}
		return;
	}*/
	
	public static int calculateNumRegistersNeeded(String[] sources, String[] targets) {
		int tmp = 0;
		if (sources[0].contains("r")) {
			tmp++;
			if (sources.length == 2) {
				if (sources[1].contains("r")) {
					tmp++;
				}
			}
		}
		if (targets != null) {
			if (targets[0].contains("r")) {
				tmp++;
			}
			if (targets.length == 2) {
				if (targets[1].contains("r")) {
					tmp++;
				}
			}
		}
		return tmp;
	}
	
	public static void calculateMaxLive() {
		for (int i = 0; i < Allocator.block.size(); i++) {
			for (int j = 0; j < Allocator.blockRegisters.length; j++) {
				if (Allocator.blockRegisters[j] != null) {
					//if (Allocator.blockRegisters[j].liveRange[0])
				}
			}
		}
	return;
	}
	
	public void printInstruction() {
		System.out.print(opcode);
		System.out.print(", " + sources[0]);
		if (this.sources.length == 2)
			System.out.print(", " + sources[1]);
		if (this.targets != null) {
			if (this.targets.length > 0) {
				System.out.print(", " + targets[0]);
				if (this.targets.length == 2) {
					System.out.print(", " + targets[1]);
				}
			}
		}
		System.out.print(", " + this.instructionNumber);
		System.out.print(", " + this.numRegistersNeeded);
		System.out.println();
		return;
	}
	
	public static void printInstructionList() {
		System.out.println("Instruction list: ");
		System.out.println();
		System.out.println();
		for (int i = 0; i < Allocator.block.size(); i++) {
			Allocator.block.get(i).printInstruction();
		}
	}
	
}
