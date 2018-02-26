package alloc;
import java.util.*;
import java.io.*;


@SuppressWarnings("unused")
public class Allocator {
	
	public static int numRegisters;
	public static int registersRemaining;
	public static char allocatorType;
	public static String filename;
	public static Register[] physicalRegisters;
	public static ArrayList<Register> spilledRegisters;
	public static Register[] blockRegisters;	//do i need this??
	public static ArrayList<Instruction> block;
	public static ArrayList<Instruction> allocated;
	public static int offset = -4;
	public static int instructionNumber;
	
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
		int MAX_LIVE;
		
		return;
	}
	
	/*public static void allocateRegister() {
		
		return;
	}*/
	
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
	
	public static int nextOffset() {
		offset = offset - 4;
		return offset;
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
		for (int i = 0; i < numRegisters; i++) {
			physicalRegisters[i] = new Register(i + 1);
		}
		block = new ArrayList<Instruction>();
		blockRegisters = new Register[256];
		parseBlock();
		for (int i = 1; i < block.size(); i++) {
			block.get(i).calculateMaxLive();
		}
		spilledRegisters = new ArrayList<Register>();
		allocated = new ArrayList<Instruction>();
		Instruction.printInstructionList();
		System.out.println();
		System.out.println();
		Register.printRegisterList();
		
		//Instruction.formatInstructions();
		//Instruction.printInstructionList();
		//System.out.println("Size of block: " + block.size());
		
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
