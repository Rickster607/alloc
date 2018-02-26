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
	public static ArrayList<Instruction> finalSpilledBlock;
	public static ArrayList<Instruction> allocated;
	public static Register[] blockRegisters;
	public static Register[] physicalRegisters;
	public static ArrayList<Register> spilledRegisters;
	
	public static void bottomUp() {
		Instruction tmpInstr = block.get(0);
		if (tmpInstr.targets != null) {
			if (tmpInstr.targets.length > 0) {				//need to change if more than one alloc pass
				if (tmpInstr.targets[0].contains("r0")) {
//					System.out.println("found r0");
					allocated.add(0, tmpInstr);
					block.remove(0);
					return;
				}
			}
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
		
		
		
		allocated.add(0, tmpInstr);
		block.remove(0);
		return;
	}

	public static void simpleTopDown() {
		
		return;
	}
	
	public static void topDown() {
		determineSpilledRegsTD();
//		Instruction.printInstructionList(spilledBlock);
		System.out.println("spilled Regs: " + spilledRegisters.toString());
		for (int i = 0; i < spilledRegisters.size(); i++) {
			System.out.print("spilled Regs: " + spilledRegisters.get(i).registerNumber + ", ");
		}
		System.out.println();
		spillRegisters();
		System.out.println();
		loadSpilledRegs();
		System.out.println();
		Instruction.printInstructionList(spilledBlock);
		return;
	}
	
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
				Instruction.calculateMaxLive(block, tmpArr, tmpInstr.instructionNumber);
//				blockRegisters[regToSpill.registerNumber] = null;
//				Instruction.calculateMaxLive(block, tmpInstr.instructionNumber);
				
				
//				System.out.println("spilling #: " + tmpInstr.instructionNumber + ", reg#: "
//						+ regToSpill.registerNumber);
/*				int address = (1024 + regToSpill.offset);
				String[] f = {String.valueOf(address)};
				String[] f1 = {"f1"};
				String[] f2 = {"f2"};
				Instruction spill1 = new Instruction(-1, "loadI", f, f2);
				Instruction spill2 = new Instruction(-1, "store", f1, f2);
				tmpInstr.targets = f1;
				tmpInstr.targetRegisters = null;
				spilledBlock.add(tmpInstr);
				spilledBlock.add(spill1);
				spilledBlock.add(spill2);
				blockRegisters[regToSpill.registerNumber] = null;
//				Register tmp = new Register(regToSpill.registerNumber);
				spilledRegisters.add(regToSpill);
				Instruction.calculateMaxLive(block, tmpInstr.instructionNumber);
*/			}
//			else {
//				spilledBlock.add(tmpInstr);
//			}
		}
		return;
	}
	
	public static void spillRegisters() {
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
						tmpInstr.targetRegisters = null;
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
	
	public static void loadSpilledRegs() {
		for (int i = 0; i < spilledRegisters.size(); i++) {
			for (int j = 0; j < spilledBlock.size(); j++) {
				if (spilledBlock.get(j).instructionNumber < 0){
					continue;
				}
				if (spilledBlock.get(j).sourceRegisters != null) {
					if (spilledBlock.get(j).sourceRegisters.contains(spilledRegisters.get(i))){
						System.out.println("I found one!!!: "
						+ spilledRegisters.get(i).registerNumber
						+ ", i:" + spilledBlock.get(j).instructionNumber);
						int address = (1024 + spilledRegisters.get(i).offset);
						String[] f = {String.valueOf(address)};
						String[] f1 = {"f1"};
						Instruction load1 = new Instruction(-2, "loadI", f, f1);
						Instruction load2 = new Instruction(-2, "load", f1, f1);
						spilledBlock.get(j).sources[spilledBlock.get(j).sourceRegisters
						                            .indexOf(spilledRegisters.get(i))] = "f1";
						spilledBlock.add(j, load1);
						j++;
						spilledBlock.add(j, load2);
						j++;
						
					}
				}
			}
		}
		return;
	}
	
	public static void allocateRegister() {
		
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
		block = new ArrayList<Instruction>();
		spilledBlock = new ArrayList<Instruction>();
		finalSpilledBlock = new ArrayList<Instruction>();
		blockRegisters = new Register[256];
		parseBlock();
		Instruction.calculateMaxLive(block);
		spilledRegisters = new ArrayList<Register>();
		allocated = new ArrayList<Instruction>();
		Instruction.printInstructionList(block);
		System.out.println();
		System.out.println();
		Register.printRegisterList();
		
		switch (allocatorType) {
			case 'b':
				while (block.size() > 0) {
					bottomUp();
				}
				break;
			case 's':
				simpleTopDown();
				break;
			case 't':
//				physicalRegisters[(numRegisters - 1)].isAvailable = false;
//				registersRemaining--;
//				physicalRegisters[(numRegisters - 2)].isAvailable = false;
//				registersRemaining--;
				topDown();
				break;
			case 'o':
				break;
			default:
				System.out.println("Error, please enter a valid allocator type.");
				break;
		}
		System.out.println("finished");
		return;
	}
}
