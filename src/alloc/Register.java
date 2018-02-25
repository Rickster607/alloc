package alloc;

public class Register {
	public int registerNumber;
	public int offset;
	public boolean isAvailable;
	public int firstUse;
	public int lastUse;
	public int frequency;
	public int[] liveRange;
	
	public Register(int regNum, int firstUse) {
		this.registerNumber = regNum;
		this.offset = (regNum * (-4));
		this.isAvailable = true;
		this.firstUse = firstUse;
		this.lastUse = 0;
		this.frequency = 1;
		this.liveRange = new int[2];
		this.liveRange[0] = firstUse;
		this.liveRange[1] = 0;
	}
	
	public Register(int regNum) {
		this.registerNumber = regNum;
		this.offset = (regNum * (-4));
		this.isAvailable = true;
	}
	
	public static int availableRegisters() {
		int tmp = 0;
		for (int i = 0; i < Allocator.numRegisters; i++) {
			if (Allocator.physicalRegisters[i].isAvailable) {
				tmp++;
			}
		}
		return tmp;
	}
	
	public static void buildRegisterArray(Instruction instruction) {
		String[] tmp = null;
		int rNum = 0;
//		Instruction tmpInstr = null;
		if (instruction.sources[0].contains("r")) {	
			tmp = instruction.sources[0].split("r");
			rNum = Integer.valueOf(tmp[1]);
			addRegister(rNum);
//			tmpInstr = Allocator.block.get(instruction.instructionNumber);
//			if (!tmpInstr.registersNeeded.contains(Allocator.blockRegisters[rNum])) {
//				tmpInstr.registersNeeded.add(Allocator.blockRegisters[rNum]);
//			}
		}
		if (instruction.sources.length == 2) {
			if (instruction.sources[1].contains("r")) {
				tmp = instruction.sources[1].split("r");
				rNum = Integer.valueOf(tmp[1]);
				addRegister(rNum);
			}
		}
		tmp = null;
		if (instruction.targets != null) {
			if (instruction.targets[0].contains("r")) {
				tmp = instruction.targets[0].split("r");
				rNum = Integer.valueOf(tmp[1]);
				addRegister(rNum);
			}
			if (instruction.targets.length == 2) {
				if (instruction.targets[1].contains("r")) {
					tmp = instruction.targets[1].split("r");
					rNum = Integer.valueOf(tmp[1]);
					addRegister(rNum);
				}
			}
		}
		return;
	}
	
	public static void addRegister(int rNum) {
		if (Allocator.blockRegisters[rNum] != null) {
			Allocator.blockRegisters[rNum].lastUse = Allocator.instructionNumber;
			Allocator.blockRegisters[rNum].liveRange[1] = Allocator.instructionNumber;
			Allocator.blockRegisters[rNum].frequency++;
		}
		else {
			Allocator.blockRegisters[rNum] = new Register(rNum, Allocator.instructionNumber);
		}
		return;
	}
	
	public void printRegister() {
		System.out.print(this.registerNumber);
		System.out.print(", " + this.offset);
		System.out.print(", " + this.isAvailable);
		System.out.print(", " + this.firstUse);
		System.out.print(", " + this.lastUse);
		System.out.print(", " + this.frequency);
		if (this.liveRange != null) {
			if (this.liveRange[0] != 0 && this.liveRange[1] != 0) {
				System.out.print(", {" + this.liveRange[0] + ", " + this.liveRange[1] + "}");
			}
		}
		System.out.println();
		return;
	}
	
	public static void printRegisterList() {
		System.out.println("Register list: ");
		System.out.println();
		System.out.println();
		for (int i = 0; i < Allocator.blockRegisters.length; i++) {
			if (Allocator.blockRegisters[i] != null) {
				Allocator.blockRegisters[i].printRegister();
			}
		}
	}
	
	/*	
	public void updateRegister() {
		
		return;
	}
	*/
	/*
	public static void GenerateRegisterArray() {
		
		return;
	}
	*/
}