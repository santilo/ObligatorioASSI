package obligatorioassi;

public class InstructionObject {
    private String instruction;
	private String subject;
	private String object;
	private int value;

	public InstructionObject(String instr) {
		String[] instruction = instr.split(" ");
		// Reviso si la instruccion es del largo apropiado
		if ((instruction.length != 4 && instruction[0].equals("write"))
				|| (instruction.length != 3 && instruction[0].equals("read"))) {
			this.instruction = "BAD";
			// Reviso si la primer palabra es read/write
		} else if (instruction[0].equals("read") == false
				&& instruction[0].equals("write") == false) {
			this.instruction = "BAD";
			// Reviso si sujeto existe
		} else if (!SecureSystem.getSubjectManager().containsKey(instruction[1])) {
			this.instruction = "BAD";
			// Reviso si obj existe
		} else if (!ObjectManager.getObjectManager().containsKey(instruction[2])) {
			this.instruction = "BAD";
			// Reviso si la 4ta palabra es un entero
		} else if (instruction[2].equals("write") && !isInteger(instruction[3])) {
			this.instruction = "BAD";
		} else if (instruction[0].equals("read")) {
			this.instruction = "READ";
			this.subject = instruction[1];
			this.object = instruction[2];
			ReferenceMonitor.readExecute(getInstructionObject());
		} else if (instruction[0].equals("write")) {
			this.instruction = "WRITE";
			this.subject = instruction[1];
			this.object = instruction[2];
			this.value = Integer.parseInt(instruction[3]);
			ReferenceMonitor.writeExecute(getInstructionObject());
		}
	}
        
        
	// Reviso si mi valor es un numero entero o no
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public InstructionObject getInstructionObject() {
		return this;
	}

	public String getInstruction() {
		return this.instruction;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getObject() {
		return this.object;
	}

	public int getValue() {
		return this.value;
	}

}
