package alloc;
import java.util.*;
import java.io.*;


@SuppressWarnings("unused")
public class Allocator {

	public static int instructionNumber;
	public static int numRegisters;
	public static int registersRemaining;
	public static int MAX_LIVE;
	public static int feasibleRegisters = 2;
	public static char allocatorType;
	public static String filename;
	public static ArrayList<Instruction> block;
	public static ArrayList<Instruction> spilledBlock;
	public static ArrayList<Instruction> allocated;
	public static Register[] blockRegisters;
	public static Register[] physicalRegisters;
	public static ArrayList<Register> spilledRegisters;
	public static ArrayList<Register> allocatedRegisters;
	
	//need to finish refactor of physicalRegisters (also make sure we're checking for null values (maybe method??))
	public static void bottomUp() {
		allocated.add(block.get(0));
		//check for end of life
		for (int i = 1; i < block.size(); i++) {
			Instruction tmpInstr = block.get(i);
			System.out.println();
			tmpInstr.printInstruction();
//			System.out.println();
			
			//load proper registers whose live ranges have ended
			for (int j = 0; j < allocatedRegisters.size(); j++) {
				Register tmpReg = allocatedRegisters.get(j);
				if (tmpInstr.sourceRegisters.contains(tmpReg)) {
					String src = "r" + String.valueOf(tmpReg.physicalIndex(physicalRegisters) + 1);
//					System.out.println("i: " + i + ", j: " + j + ", src: " + src + ", num: " + tmpReg.registerNumber);
					tmpInstr.sources[tmpInstr.sourceRegisters.indexOf(tmpReg)] = src;
				}
				if (tmpInstr.opcode.contains("store") && tmpInstr.targetRegisters.contains(tmpReg)) {
					String tgt = "r" + String.valueOf(tmpReg.physicalIndex(physicalRegisters) + 1);
//					System.out.println("i: " + i + ", j: " + j + ", tgt: " + tgt + ", num: " + tmpReg.registerNumber);
					tmpInstr.targets[0] = tgt;
				}
			}
			//remove registers whose live ranges have ended
			for (int j = 0; j < physicalRegisters.length; j++) {
				if (physicalRegisters[j] != null) {
					if (i >= physicalRegisters[j].liveRange[1]) {
						System.out.println("Removing: " + physicalRegisters[j].registerNumber);
						allocatedRegisters.remove(physicalRegisters[j]);
						physicalRegisters[j] = null;
					}
				}
			}
			//determine number of registers available to allocate this pass (increment # if register is a target of store,
				//in this case, it will be counted as a target register even though it's already been 
			int numAvailableRegs = Register.numAvailable(physicalRegisters);
			if (tmpInstr.opcode.contains("store")) {
//				numAvailableRegs++;
			}
			else {
				for (int j = 0; j < tmpInstr.targetRegisters.size(); j++) {
					if (tmpInstr.targetRegisters.get(j).isContainedIn(physicalRegisters)) {
						numAvailableRegs++;
					}
				}
			}
			
			if (numAvailableRegs - tmpInstr.targetRegisters.size() < 0) {
				int numToFree = Register.numAvailable(physicalRegisters) + tmpInstr.targetRegisters.size();
				System.out.println("We need to spill some registers, boys: " + numToFree);
				//do stuff
//				while (numToFree > 0) {
					for (int j = 0; j < physicalRegisters.length; j++) {
						if (physicalRegisters[j] != null) {
							physicalRegisters[j].calculateNextUse(block, i);
						}
					}
					
					for (int j = 0; j < physicalRegisters.length; j++) {
						
					}
//				}
					
			}
			
			else {
				//allocate reg
				if (tmpInstr.targetRegisters.size() != 0) {
					for (int j = 0; j < tmpInstr.targetRegisters.size(); j++) {
						if (j > 0) {
							System.out.print("j: " + j + ", ");
						}
						if ((!tmpInstr.targetRegisters.get(j).isContainedIn(physicalRegisters)) && (!tmpInstr.opcode.contains("store"))) {
							tmpInstr.targetRegisters.get(j).addTo(physicalRegisters);
							allocatedRegisters.add(tmpInstr.targetRegisters.get(j));
							String tgt = "r" + String.valueOf(tmpInstr.targetRegisters.get(j).physicalIndex(physicalRegisters) + 1);
//							System.out.print("i: " + i + ", r: " + tmpInstr.targetRegisters.get(j).registerNumber
//									+ ", String: " + tgt);
							tmpInstr.targets[j] = tgt;
//							System.out.println(", Targets: " + tgt);
						}
					}
				}
			}
			Register.printThisRegList(physicalRegisters);
			allocated.add(tmpInstr);
		}
//		//free registers to meet needs of next instruction
//		for (int i = 0; i < numRegisters; i++) {
//			//if 
//		}
//		//move inside while?
		
		//do some register freeing stuff
//		while (registersRemaining < tmpInstr.numRegistersNeeded) {
//			//register.inUse?	
//			
//		}
		
		//insert spill code
		
		
		//allocate physical registers
		
		
		
//		allocated.add(0, tmpInstr);
//		Instruction.printInstructionList(allocated);
		Instruction.printILOC(allocated);
		Instruction.printILOCtoFile(allocated);
		return;
	}

	public static void simpleTopDown() {
		
		return;
	}
	
	public static void topDown() {
//		Instruction.printILOC(block);
//		Instruction.printILOCtoFile(block);
		determineSpilledRegsTD();
		spillRegistersTD();
		loadSpilledRegsTD();
//		Instruction.printInstructionList(spilledBlock);
		allocateRegisters();
//		Instruction.printInstructionList(spilledBlock);
//		Instruction.printILOC(spilledBlock);
		Instruction.printILOCtoFile(spilledBlock);
		return;
	}
	
	/*public static void loadSpilledRegsBU(ArrayList<Instruction> block, int start) {
		for (int i = 0; i < spilledRegisters.size(); i++) {
			Register tmpReg = spilledRegisters.get(i);
			for (int j = start; j < block.size(); j++) {
				Instruction tmpInstr = block.get(j);
				if (true) {
					
				}
			}
		}
		return;
	}*/
	
	public static void determineSpilledRegsTD() {
		Register[] tmpArr = new Register[256];
		for (int i = 0; i < 256; i++) {
			if (blockRegisters[i] != null) {
				tmpArr[i] = new Register(blockRegisters[i]);
			}
		}
		for (int i = 0; i < block.size(); i++) {
			Instruction tmpInstr = block.get(i);
			if (tmpInstr.maxLive > (numRegisters - feasibleRegisters)){
				Register regToSpill = tmpInstr.liveRegisters.get(0);
				if (tmpInstr.liveRegisters.size() > 1) {
					for (int j = 1; j < tmpInstr.liveRegisters.size(); j++) {
						if ((tmpInstr.liveRegisters.get(j).frequency < regToSpill.frequency)
						|| ((tmpInstr.liveRegisters.get(j).frequency == regToSpill.frequency)
						&& (tmpInstr.liveRegisters.get(j).life > regToSpill.life))){
							regToSpill = tmpInstr.liveRegisters.get(j);
						}
					}
				}
				spilledRegisters.add(blockRegisters[regToSpill.registerNumber]);
				tmpArr[regToSpill.registerNumber] = null;
				Instruction.calculateMaxLive(block, tmpArr);
			}
		}
		return;
	}
	
	public static void spillRegistersTD() {
		for (int j = 0; j < block.size(); j++) {
			Instruction tmpInstr = block.get(j);
			for (int i = 0; i < spilledRegisters.size(); i++) {
				Register regToSpill = spilledRegisters.get(i);
				if (tmpInstr.targetRegisters != null) {
					if (tmpInstr.targetRegisters.contains(regToSpill)) {
						int address = (1024 + regToSpill.offset);
						String[] f = {String.valueOf(address)};
						String[] f1 = {"f1"};
						String[] f2 = {"f2"};
						Instruction spill1 = new Instruction(-1, "loadI", f, f2);
						Instruction spill2 = new Instruction(-1, "store", f1, f2);
						tmpInstr.targets = f1;
						tmpInstr.liveRegisters.remove(regToSpill);
						spilledBlock.add(tmpInstr);
						spilledBlock.add(spill1);
						spilledBlock.add(spill2);
						break;
					}
				}
			}
			if (!spilledBlock.contains(tmpInstr)) {
				spilledBlock.add(tmpInstr);
			}
		}
		return;
	}
	
	public static void loadSpilledRegsTD() {
		for (int i = 0; i < spilledRegisters.size(); i++) {
			for (int j = 0; j < spilledBlock.size(); j++) {
				if (spilledBlock.get(j).instructionNumber < 0){
					continue;
				}
				int address = (1024 + spilledRegisters.get(i).offset);
				String[] f = {String.valueOf(address)};
				String[] f1 = {"f1"};
				String[] f2 = {"f2"};
				Instruction load1 = new Instruction(-2, "loadI", f, f1);
				Instruction load2 = new Instruction(-2, "load", f1, f1);
				Instruction load3 = new Instruction(-3, "loadI", f, f2);
				Instruction load4 = new Instruction(-3, "load", f2, f2);
				if (spilledBlock.get(j).sourceRegisters != null) {
					int index = spilledBlock.get(j).sourceRegisters.indexOf(spilledRegisters.get(i));
					if (spilledBlock.get(j).sourceRegisters.contains(spilledRegisters.get(i))){
						if (index == 0) {
							spilledBlock.get(j).sources[0] = "f2";
							spilledBlock.add(j, load3);
							j++;
							spilledBlock.add(j, load4);
							j++;
						}
						if (index == 1) {
							spilledBlock.get(j).sources[1] = "f1";
							spilledBlock.add(j, load1);
							j++;
							spilledBlock.add(j, load2);
							j++;
						}
					}
				}
				if (spilledBlock.get(j).targetRegisters != null) {
					int index = spilledBlock.get(j).targetRegisters.indexOf(spilledRegisters.get(i));
					if (spilledBlock.get(j).targetRegisters.contains(spilledRegisters.get(i))) {
						if (spilledBlock.get(j).instructionNumber > spilledRegisters.get(i).liveRange[0]
								&& (spilledBlock.get(j).opcode.contains("store"))){
							spilledBlock.get(j).targets[index] = "f1";
							spilledBlock.add(j, load1);
							j++;
							spilledBlock.add(j, load2);
							j++;
						}
					}
				}
			}
		}
		return;
	}
	
	public static void allocateRegisters() {
		for (int i = 1; i < spilledBlock.size(); i++) {
			Instruction tmp = spilledBlock.get(i);
			String ra = "r254";
			String rb = "r255";
			if (tmp.targets != null) {
				if (tmp.targets[0].contains("f1")) {
					tmp.targets[0] = ra;
				}
				if (tmp.targets[0].contains("f2")) {
					tmp.targets[0] = rb;
				}
				if (tmp.targets.length == 2) {
					System.out.println("why are we here");
					if (tmp.targets[1].contains("f1")) {
						tmp.targets[1] = ra;
					}
					if (tmp.targets[1].contains("f2")) {
						tmp.targets[1] = rb;
					}
				}
			}
			if (tmp.sources != null){
				if (tmp.sources[0].contains("f1")) {
					tmp.sources[0] = ra;
				}
				if (tmp.sources[0].contains("f2")) {
					tmp.sources[0] = rb;
				}
				if (tmp.sources.length == 2) {
					if (tmp.sources[1].contains("f1")) {
						tmp.sources[1] = ra;
					}
					if (tmp.sources[1].contains("f2")) {
						tmp.sources[1] = rb;
					}
				}
			}
		}
		return;
	}
	
	public static void parseBlock() {
		File blockFile = new File(filename);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(blockFile));
			String instruction = null;
			while ((instruction = br.readLine()) != null) {
				Instruction.addInstruction(instruction);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error, file not found.");
		} catch (IOException e) {
			System.out.println("Error, IOException.");
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Error, IOException closing br");
			}
		}
		return;
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Error, wrong number of arguments.");
		}
		if (args[1].length() != 1) {
			System.out.println("Error, please enter a single character for the allocator type.");
		}
		numRegisters = Integer.valueOf(args[0]);
		registersRemaining = numRegisters;
		allocatorType = args[1].toLowerCase().charAt(0);
		filename = args[2];
		physicalRegisters = new Register[numRegisters];
		for (int i = 0; i < physicalRegisters.length; i++) {
			physicalRegisters[i] = null;
		}
		block = new ArrayList<Instruction>();
		spilledBlock = new ArrayList<Instruction>();
		blockRegisters = new Register[256];
		parseBlock();
		Instruction.calculateMaxLive(block);
		spilledRegisters = new ArrayList<Register>();
		allocatedRegisters = new ArrayList<Register>();
		allocated = new ArrayList<Instruction>();
		
		switch (allocatorType) {
			case 'b':
				bottomUp();
				break;
			case 's':
				simpleTopDown();
				break;
			case 't':
				registersRemaining = numRegisters - 2;
				topDown();
				break;
			case 'o':
				break;
			default:
				System.out.println("Error, please enter a valid allocator type.");
				break;
		}
		return;
	}
}
