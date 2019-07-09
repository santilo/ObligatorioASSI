
package obligatorioassi;

public class ReferenceMonitor {
    public ReferenceMonitor() {
		// Se agregan Hal y Lyle al readManager
		ObjectManager.getReadManager().put("Hal", 0);
		ObjectManager.getReadManager().put("Lyle", 0);
	}

	// Genero nuevo objeto en el objectManager
	public void createNewObject(String name, SecurityLevel secLev) {
		ObjectManager.createNewObject(name, secLev);
	}

	// Reviso si la instruccion write es "segura" y envio la ejecucion al objectManager
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

	// Reviso si la instruccion write es "segura" y envio la ejecucion al objectManager
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
