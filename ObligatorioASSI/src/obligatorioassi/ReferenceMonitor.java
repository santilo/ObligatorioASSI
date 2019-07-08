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
public class ReferenceMonitor {
    public ReferenceMonitor() {
		// Initially add a blank Hal and Lyle to the read manager
		ObjectManager.getReadManager().put("Hal", 0);
		ObjectManager.getReadManager().put("Lyle", 0);
	}

	// Create a new object within the object manager
	public void createNewObject(String name, SecurityLevel secLev) {
		ObjectManager.createNewObject(name, secLev);
	}

	// Check to see if the passed write instruction is safe based on security
	// principles. If so, send to object manager for execution
	static void writeExecute(InstructionObject instr) {
		String subj = instr.getSubject();
		String obj = instr.getObject();

		SecurityLevel subjSec = SecureSystem.getSubjectManager().get(subj);
		int subjSecLevel = subjSec.getDomination();

		SecurityLevel objSec = ObjectManager.getObjectManager().get(obj);
		int objSecLevel = objSec.getDomination();
                
                //SI EL SUJETO ES MEJOR O IGUAL QUE EL OBJETO, ENTONCES PUEDE ESCRIBIR.
                
		if (subjSecLevel <= objSecLevel) {
			ObjectManager.writeExecute(instr);
		}
	}

	// Check to see if the passed read instruction is safe based on security
	// principles. If so, send to object manager for execution
	static void readExecute(InstructionObject instr) {
		String subj = instr.getSubject();
		String obj = instr.getObject();

		SecurityLevel subjSec = SecureSystem.getSubjectManager().get(subj);
		int subjSecLevel = subjSec.getDomination();

		SecurityLevel objSec = ObjectManager.getObjectManager().get(obj);
		int objSecLevel = objSec.getDomination();

                //SI EL SUJETO ES MAYOR O IGUAL QUE EL OBJETO, ENTONCES PUEDE LEER.
                
		if (subjSecLevel >= objSecLevel) {
			ObjectManager.readExecute(instr);
		} else {
			ObjectManager.badReadExecute(instr);
		}
	}
}
