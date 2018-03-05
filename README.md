#CS415 - Compilers
#Project1 - Register Allocation
#Richard Stoltzfus - netID: ras480
#Tested on vi.cs.rutgers.edu

===================================================================================

This register allocator was written in Java using Java 1.8. Instructions on how
 to compile, create an executable (jar), and run are in the following section.

===================================================================================

To compile sources:
	1) from project root directory, run:
		javac -d bin/ src/alloc/*.java

To create jar:
	1) from project root directory, run:
		jar cfe alloc.jar alloc.Allocator -C bin/ .

To run jar:
	1) from project root directory (or wherever you put the jar):
		java -jar alloc.jar {args}
	For example:
		java -jar alloc.jar 5 b ~/workspace/alloc/Report/report1.i
	Allocates report1.i with 5 physical registers using a BottomUp allocator.

===================================================================================

A short outline of my source code:

My source code consists of 3 Java classes: Allocator, Instruction, and Register.
The main method is in the Allocator class and all methods in this class are static.
When the allocator is invoked, I use the main method to parse the input arguments,
 initialize my arrays and other objects, parse the specified block of ILOC code and 
 store it into "block, then call the appropriate method for the allocator type 
 specified in the input arguments.

The block of ILOC code is originally parsed and objects are created for each 
 instruction (of type Instruction) and each register (of type Register) used in the 
 original ILOC code. The Instruction objects contain fields for the opcode, sources 
 and targets of the instruction, and the Register objects contain a for the 
 register number that is used in the original instruction. Methods are then called 
 to initialize some additional fields in each object, including the number of live 
 registers for each Instruction, and the live range of each Register.

My BottomUp allocator, when called, loops through the "block" of original 
 Instructions and does 5 main things (in order):
	1) Determines whether the Registers used in the current Instruction have 
	 been "allocated" already (placed in the physicalRegisters array), and if 
	 so sets the String[] values of "sources" and "targets" to the value of 
	 the *physical* Register that will be used for the operation ("targets" 
	 is only set if the instruction is a "store" operation).
	2) Determines the number of physicalRegisters needed for the current 
	 Instruction and if any Registers need to be spilled to meet that 
	 requirement. If so, the nextUse of each physicalRegister is calculated, 
	 the proper Registers are spilled, and the Registers that are spilled are 
	 then added to the spilledRegisters list.
	3) Loads any spilledRegisters that are needed for the current instruction.
	4) Allocates the targetRegisters for non-"store" operations.
	5) Frees physicalRegisters whose live ranges have ended.

	All spill code is added to the "allocated" block of Instructions when 
	 created, and at the end of each iteration of the "block", the updated
	 Instruction is added to the allocated block.
	
My SimpleTopDown allocator is, as the name suggests, very simple. When called it 
 calls 4 static methods in the Allocator class that do the following:
	1) Creates a copy of the array of virtual registers used in the original 
	 Instruction block and sorts it to determine the most frequently used 
	 Registers. These Registers are then added to the physicalRegisters array 
	 and the remaining Registers are added to the spilledRegisters list.
	2) Loops through the "block" of Instructions updating the "sources" and 
	 "targets" of the Instructions whose Registers need to be spilled, and
	 adding the updated Instructions, and spill code as necessary, to the 
	 spilledBlock.
	3) Loops through the spilledBlock and inserts code to load spilled 
	 Registers as needed, updating the spilledBlock Instructions to reflect 
	 the proper sources and targets.
	4) Loops through the spilledBlock one last time to update the Instructions 
	 whose "sources" and "targets" reference Registers that have been 
	 physically allocated.

My TopDown allocator works the same way as my SimpleTopDown allocator except for 
 one main difference. In the SimpleTopDown allocator, the physicalRegisters are 
 determined in the very beginning and never change, even after their live ranges 
 have expired. In the TopDown allocator, those Registers will be removed from the 
 physicalRegisters array and new Registers will take their place (based on the 
 given spilling heuristic).
Additionally, my TopDown allocator (which was the first allocator I wrote) does 
 not reassign the register numbers in the Instructions to reflect r1, r2, ..., rn 
 where (n+2) is the number of physical registers to allocate, and the two feasible 
 registers used are not r(n) and r(n-1), but instead r255 and r254. This does not 
 affect the intended result of the program; there are no more than n registers 
 "live" at any point in the ILOC code output to stdout, so the desired operation 
 is achieved.
