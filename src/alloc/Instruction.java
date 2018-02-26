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
	
	public Instruction(int n, String opcode, String[] sources, String[] targets) {
		this.opcode = opcode;
		this.sources = sources;
		this.targets = targets;
//		this.liveRegisters = new ArrayList<Register>();
//		this.sourceRegisters = new ArrayList<Register>();
//		this.targetRegisters = new ArrayList<Register>();
		this.instructionNumber = n;
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
	
	public static void calculateMaxLive(ArrayList<Instruction> block) {
		Allocator.MAX_LIVE = 0;
		for (int i = 0; i < block.size(); i++) {
			block.get(i).liveRegisters.clear();
			block.get(i).maxLive = 0;
			for (int j = 1; j < Allocator.blockRegisters.length; j++) {
				if (Allocator.blockRegisters[j] != null) {
					if (i >= Allocator.blockRegisters[j].liveRange[0]
						&& i < Allocator.blockRegisters[j].liveRange[1]) {
						block.get(i).liveRegisters.add(Allocator.blockRegisters[j]);
						block.get(i).maxLive++;
					}
				}
			}
			if (block.get(i).maxLive > Allocator.MAX_LIVE) {
				Allocator.MAX_LIVE = block.get(i).maxLive;
			}
		}
	return;
	}

	public static void calculateMaxLive(ArrayList<Instruction> block, int start) {
		Allocator.MAX_LIVE = 0;
		for (int i = start; i < block.size(); i++) {
			block.get(i).liveRegisters.clear();
			block.get(i).maxLive = 0;
			for (int j = 1; j < Allocator.blockRegisters.length; j++) {
				if (Allocator.blockRegisters[j] != null) {
					if (i >= Allocator.blockRegisters[j].liveRange[0]
						&& i < Allocator.blockRegisters[j].liveRange[1]) {
						block.get(i).liveRegisters.add(Allocator.blockRegisters[j]);
						block.get(i).maxLive++;
					}
				}
			}
			if (block.get(i).maxLive > Allocator.MAX_LIVE) {
				Allocator.MAX_LIVE = block.get(i).maxLive;
			}
		}
	return;
	}
	
	public static void calculateMaxLive(ArrayList<Instruction> block, Register[] rArr, int start) {
		Allocator.MAX_LIVE = 0;
		for (int i = start; i < block.size(); i++) {
			block.get(i).liveRegisters.clear();
			block.get(i).maxLive = 0;
			for (int j = 1; j < rArr.length; j++) {
				if (rArr[j] != null) {
					if (i >= rArr[j].liveRange[0]
						&& i < rArr[j].liveRange[1]) {
						block.get(i).liveRegisters.add(rArr[j]);
						block.get(i).maxLive++;
					}
				}
			}
			if (block.get(i).maxLive > Allocator.MAX_LIVE) {
				Allocator.MAX_LIVE = block.get(i).maxLive;
			}
		}
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
		if (this.instructionNumber != -1) {
			System.out.print(", maxLive: " + this.maxLive);
		}
		if (this.liveRegisters != null) {
			System.out.print(", live: " + this.liveRegisters.toString());
		}
		System.out.println();
		return;
	}
	
	public static void printInstructionList(ArrayList<Instruction> block) {
		System.out.println("Instruction list: ");
		System.out.println();
		System.out.println();
		for (int i = 0; i < block.size(); i++) {
			block.get(i).printInstruction();
		}
	}
	
	public static void printInstructionList(ArrayList<Instruction> block, int n) {
		System.out.println("Instruction list: ");
		System.out.println();
		System.out.println();
		for (int i = 0; i < n; i++) {
			block.get(i).printInstruction();
		}
	}
	
}
