package alloc;

import java.util.ArrayList;

public class Register {
	public int registerNumber;
	public int offset;
	public boolean isAvailable;
	public int firstUse;
//	public int nextUse;			//do I need this?? Can I calc this??
	public int lastUse;
	public int frequency;
	public int life;
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
	
	public Register(Register r) {
		this.registerNumber = r.registerNumber;
		this.offset = r.offset;
		this.isAvailable = r.isAvailable;
		this.firstUse = r.firstUse;
		this.lastUse = r.lastUse;
		this.frequency = r.frequency;
		this.liveRange = new int[2];
		this.liveRange[0] = r.liveRange[0];
		this.liveRange[1] = r.liveRange[1];
		this.life = r.life;
	}
	
	public Register(int regNum) {
		this.registerNumber = regNum;
		this.offset = (regNum * (-4));
//		this.isAvailable = true;
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
		if (instruction.sources[0].contains("r")) {	
			tmp = instruction.sources[0].split("r");
			rNum = Integer.valueOf(tmp[1]);
			addRegister(rNum);
			instruction.sourceRegisters.add(Allocator.blockRegisters[rNum]);
		}
		if (instruction.sources.length == 2) {
			if (instruction.sources[1].contains("r")) {
				tmp = instruction.sources[1].split("r");
				rNum = Integer.valueOf(tmp[1]);
				addRegister(rNum);
				instruction.sourceRegisters.add(Allocator.blockRegisters[rNum]);
			}
		}
		tmp = null;
		if (instruction.targets != null) {
			if (instruction.targets[0].contains("r")) {
				tmp = instruction.targets[0].split("r");
				rNum = Integer.valueOf(tmp[1]);
				addRegister(rNum);
				instruction.targetRegisters.add(Allocator.blockRegisters[rNum]);
			}
			if (instruction.targets.length == 2) {
				if (instruction.targets[1].contains("r")) {
					tmp = instruction.targets[1].split("r");
					rNum = Integer.valueOf(tmp[1]);
					addRegister(rNum);
					instruction.targetRegisters.add(Allocator.blockRegisters[rNum]);
				}
			}
		}
		return;
	}
	
	public static void addRegister(int rNum) {
		if (Allocator.blockRegisters[rNum] != null) {
			Allocator.blockRegisters[rNum].lastUse = Allocator.instructionNumber;
			Allocator.blockRegisters[rNum].liveRange[1] = Allocator.instructionNumber;
			Allocator.blockRegisters[rNum].life = Allocator.blockRegisters[rNum].liveRange[1] -
				Allocator.blockRegisters[rNum].liveRange[0];
			Allocator.blockRegisters[rNum].frequency++;
		}
		else {
			Allocator.blockRegisters[rNum] = new Register(rNum, Allocator.instructionNumber);
		}
		return;
	}
	
	public static String[] getString(ArrayList<Register> list) {
		if (list != null) {
			if (list.size() == 1) {
				String[] tmp = {String.valueOf("r" + list.get(0).registerNumber)};
				return tmp;
			}
			else if (list.size() == 2) {
				String[] tmp = {String.valueOf("r" + list.get(0).registerNumber)
						, String.valueOf("r" + list.get(1).registerNumber)};
				return tmp;
			}
		}
		return null;
	}
	
	public void printRegister() {
		System.out.print("regNum: " + this.registerNumber);
		System.out.print(", offset: " + this.offset);
		System.out.print(", avail: " + this.isAvailable);
		System.out.print(", first: " + this.firstUse);
		System.out.print(", last: " + this.lastUse);
		System.out.print(", freq: " + this.frequency);
		if (this.liveRange != null) {
			if (this.liveRange[0] != 0 && this.liveRange[1] != 0) {
				System.out.print(", LR: {" + this.liveRange[0] + ", " + this.liveRange[1] + "}");
			}
		}
		return;
	}
	
	public static void printRegisterList() {
		System.out.println("Register list: ");
		System.out.println();
		System.out.println();
		for (int i = 0; i < Allocator.blockRegisters.length; i++) {
			if (Allocator.blockRegisters[i] != null) {
				Allocator.blockRegisters[i].printRegister();
				System.out.println();
			}
		}
	}
	
}
