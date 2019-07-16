package obligatorioassi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SecureSystem {
	ReferenceMonitor refMon = new ReferenceMonitor();
	static InstructionObject instrobj;
	static HashMap<String, SecurityLevel> sujetos = new HashMap<String, SecurityLevel>();

	static void passInstructions(String[] instructions) {
		// Parse the passed txt file until end, while printing the state after
		// each line
		for (int i = 0; i < instructions.length; i++) {
			// System.out.println("Passing instruction: " + instructions[i]);
			instrobj = new InstructionObject(instructions[i]);
			// printState();
		}
	}

	// SecureSystem constructor
	public SecureSystem(String fileName) throws FileNotFoundException {

	}

	// Constructor for a subject manager
	void createSubject(String name, SecurityLevel secLev) {
		sujetos.put(name, secLev);
	}

	// Returns the subject manager
	public static HashMap<String, SecurityLevel> obtenerSujetos() {
		return sujetos;
	}

	// Returns the reference monitor
	public ReferenceMonitor obtenerReferenceMonitor() {
		return refMon;
	}

	// Prints the status of all the objects and subjects after each line of the
	// txt
	static void printState() {
		if (instrobj.obtenerInstruccion().equals("BAD")) {
			System.out.println("Instrucción mal formada");
			System.out.println("El estado actual del sistema es: ");
			System.out.println("   LObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("LObj"));
			System.out.println("   HObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("HObj"));
			System.out.println("   Lyle recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Lyle"));
			System.out.println("   Hal recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Hal"));
			System.out.println();
		} else if (instrobj.obtenerInstruccion().equals("READ")) {
			System.out.println(instrobj.obtenerSujeto()+ " reads "
					+ instrobj.obtenerSujeto());
			System.out.println("El estado actual del sistema es: ");
			System.out.println("   LObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("LObj"));
			System.out.println("   HObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("HObj"));
			System.out.println("   Lyle recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Lyle"));
			System.out.println("   Hal recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Hal"));
			System.out.println();
		} else if (instrobj.obtenerInstruccion().equals("WRITE")) {
			System.out.println(instrobj.obtenerSujeto()+ " writes value "
					+ instrobj.obtenerValor()+ " to " + instrobj.obtenerObjeto());
			System.out.println("El estado actual del sistema es: ");
			System.out.println("   LObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("LObj"));
			System.out.println("   HObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("HObj"));
			System.out.println("   Lyle recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Lyle"));
			System.out.println("   Hal recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Hal"));
			System.out.println();
		} else if (instrobj.obtenerInstruccion().equals("CREATE")) {
			System.out.println(instrobj.obtenerSujeto()+ " crea objeto "
					+ instrobj.obtenerObjeto());
			System.out.println("El estado actual del sistema es: ");
			System.out.println("   LObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("LObj"));
			System.out.println("   HObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("HObj"));
			System.out.println("   Lyle recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Lyle"));
			System.out.println("   Hal recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Hal"));
			System.out.println();
			System.out.println("The current ObjectManager is: ");
			for (String name : ObjectManager.obtenerObjetos().keySet()) {
				int value = ObjectManager.obtenerObjetos().get(name)
						.obtenerDominancia();
				System.out.println(name + " " + value);
			}
		} else if (instrobj.obtenerInstruccion().equals("DESTROY")) {
			System.out.println(instrobj.obtenerSujeto()+ " destruye objeto "
					+ instrobj.obtenerObjeto());
			System.out.println("El estado actual del sistema es: ");
			System.out.println("   LObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("LObj"));
			System.out.println("   HObj tiene el valor: "
					+ ObjectManager.obtenerValores().get("HObj"));
			System.out.println("   Lyle recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Lyle"));
			System.out.println("   Hal recientemente leyo: "
					+ ObjectManager.obtenerLecturas().get("Hal"));
			System.out.println();
			System.out.println("El manejador de objectos actual es: ");
			for (String name : ObjectManager.obtenerObjetos().keySet()) {
				int value = ObjectManager.obtenerObjetos().get(name)
						.obtenerDominancia();
				System.out.println(name + " " + value);
			}
		}
		System.out.println();
	}
}