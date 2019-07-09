package obligatorioassi;

import java.util.HashMap;

public class ObjectManager {
    static HashMap<String, SecurityLevel> objectManager = new HashMap<String, SecurityLevel>();
	static HashMap<String, Integer> valueManager = new HashMap<String, Integer>();
	static HashMap<String, Integer> readManager = new HashMap<String, Integer>();

	public static HashMap<String, SecurityLevel> getObjectManager() {
		return objectManager;
	}

	public static HashMap<String, Integer> getValueManager() {
		return valueManager;
	}

	public static HashMap<String, Integer> getReadManager() {
		return readManager;
	}
         //Crea un nuevo objeto a traves del object manager
	static void createNewObject(String name, SecurityLevel secLev) {
		objectManager.put(name, secLev);
		valueManager.put(name, 0);
	}
        //Cuando el referenceMonitor valida la instruccion, ejecuta el write instruccion
	static void writeExecute(InstructionObject instr) {
		int val = instr.getValue();
		String obj = instr.getObject();

		valueManager.put(obj, val);
	}
        //Cuando el referenceMonitor valida la instruccion, ejecuta el read instruccion
	static void readExecute(InstructionObject instr) {
		String subj = instr.getSubject();
		String obj = instr.getObject();

		readManager.put(subj, valueManager.get(obj));

	}

	//Si la instruccion es invalida, fija lo ultimo leido como 0
	static void badReadExecute(InstructionObject instr) {
		String subj = instr.getSubject();

		readManager.put(subj, 0);

	}
}
