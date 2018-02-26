package alloc;
import java.util.ArrayList;

public class Instruction {
	String opcode;
	String[] sources;
	String[] targets;
	ArrayList<Register> liveRegisters;
	ArrayList<Register> sourceRegisters;
	ArrayList<Register> targetRegisters;
	int instructionNumber;
	int numRegistersNeeded;
	int maxLive;
	
	public Instruction(String opcode, String[] sources, String[] targets, int iNum, int nr) {
		this.opcode = opcode;
		this.sources = sources;
		this.targets = targets;
		this.liveRegisters = new ArrayList<Register>();
		this.sourceRegisters = new ArrayList<Register>();
		this.targetRegisters = new ArrayList<Register>();
		this.instructionNumber = iNum;
		this.numRegistersNeeded = nr;
	}
	
	public static void addInstruction(String instruction) {
		if (!(instruction.startsWith("//") || instruction.startsWith("#")
				|| (instruction == null) || instruction.isEmpty())) {
			Instruction tmp = parseInstruction(instruction);
			Allocator.block.add(tmp);
			Register.buildRegisterArray(tmp);
			Allocator.instructionNumber++;
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
		int in = Allocator.instructionNumber;
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
	
	public void calculateMaxLive() {
//		int life = 0;
		for (int j = 1; j < Allocator.blockRegisters.length; j++) {
			if (Allocator.blockRegisters[j] != null) {
				if (this.instructionNumber >= Allocator.blockRegisters[j].liveRange[0]
					&& this.instructionNumber < Allocator.blockRegisters[j].liveRange[1]) {
//					System.out.println("i: " + this.instructionNumber + ", j: " + j + ", LR: "
//							+ Allocator.blockRegisters[j].liveRange[0] + ", "
//							+ Allocator.blockRegisters[j].liveRange[1]);
//					life++;
					this.liveRegisters.add(Allocator.blockRegisters[j]);
					this.maxLive++;
				}
			}
		}
		if (this.maxLive > Allocator.MAX_LIVE) {
			Allocator.MAX_LIVE = this.maxLive;
		}
//		System.out.println("instr: " + this.instructionNumber + ", this: " + this.maxLive);
	return;
	}
	
	public void printInstruction() {
		System.out.print("instr#: " + this.instructionNumber);
		System.out.print(", oc: " + opcode);
		System.out.print(", srcs: " + sources[0]);
		if (this.sources.length == 2)
			System.out.print(", " + sources[1]);
		if (this.targets != null) {
			if (this.targets.length > 0) {
				System.out.print(", tgts: " + targets[0]);
				if (this.targets.length == 2) {
					System.out.print(", " + targets[1]);
				}
			}
		}
//		System.out.print(", #regs: " + this.numRegistersNeeded);
		System.out.print(", maxLive: " + this.maxLive);
		System.out.print(", live: " + this.liveRegisters.toString());
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
