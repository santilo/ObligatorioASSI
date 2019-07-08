/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorioassi;

/**
 *
 * @author santi
 */
public class InstructionObject {
    private String instruction;
	private String subject;
	private String object;
	private int value;

	public InstructionObject(String instr) {
		String[] myLine = instr.split(" ");
		// Checks to make sure instruction is the required length
		if ((myLine.length != 4 && myLine[0].equals("write"))
				|| (myLine.length != 3 && myLine[0].equals("read"))) {
			this.instruction = "BAD";
			// Checks to see if the first word is 'read' or 'write'
		} else if (myLine[0].equals("read") == false
				&& myLine[0].equals("write") == false) {
			this.instruction = "BAD";
			// Checks if the passed subject is in the subject manager
		} else if (!SecureSystem.getSubjectManager().containsKey(myLine[1])) {
			this.instruction = "BAD";
			// Checks if the passed object is in the object manager
		} else if (!ObjectManager.getObjectManager().containsKey(myLine[2])) {
			this.instruction = "BAD";
			// Checks that the last string in a write instruction is an int
		} else if (myLine[2].equals("write") && !isInteger(myLine[3])) {
			this.instruction = "BAD";
			// Otherwise, read is safe and sent to reference monitor
		} else if (myLine[0].equals("read")) {
			this.instruction = "READ";
			this.subject = myLine[1];
			this.object = myLine[2];
			ReferenceMonitor.readExecute(getInstructionObject());
			// Otherwise, write is safe and sent to reference monitor
		} else if (myLine[0].equals("write")) {
			this.instruction = "WRITE";
			this.subject = myLine[1];
			this.object = myLine[2];
			this.value = Integer.parseInt(myLine[3]);
			ReferenceMonitor.writeExecute(getInstructionObject());
		}
	}

	// Returns the instruction object
	public InstructionObject getInstructionObject() {
		return this;
	}

	// Returns the instruction
	public String getInstruction() {
		return this.instruction;
	}

	// Returns the subject
	public String getSubject() {
		return this.subject;
	}

	// Returns the object
	public String getObject() {
		return this.object;
	}

	// Returns the value
	public int getValue() {
		return this.value;
	}

	// Checks to see if a string is an int for a safety check with a write call
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
