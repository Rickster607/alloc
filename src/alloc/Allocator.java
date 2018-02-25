package alloc;
import java.util.*;
import java.io.*;


@SuppressWarnings("unused")
public class Allocator {
	
	public static int numRegisters;
	public static char allocatorType;
	public static String filename;
	public static Register[] registers;
	public static ArrayList<Instruction> block;
	
	public static void bottomUp() {
		
		return;
	}

	public static void simpleTopDown() {
		
		return;
	}
	
	public static void topDown() {
		int MAX_LIVE;
		
		return;
	}
	
	public static int findMaxLive() {
		return 0;
	}
	
	public static void parseBlock() {
		File blockFile = new File(filename);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(blockFile));
			String instruction = null;
			while ((instruction = br.readLine()) != null) {
				block.add(Instruction.parseInstruction(instruction));
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
		allocatorType = args[1].toLowerCase().charAt(0);
		filename = args[2];
		registers = new Register[numRegisters];
		block = new ArrayList<Instruction>();
		/*int i;
		for (i = 0; i < numRegisters; i++) {
			registers[i].value = i + 1;
		}*/
		
		
		switch (allocatorType) {
			case 'b':
				bottomUp();
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
		return;
	}
}
