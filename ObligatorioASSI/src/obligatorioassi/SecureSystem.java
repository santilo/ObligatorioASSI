package obligatorioassi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import obligatorioassi.InstructionObject;
import obligatorioassi.ObjectManager;
import obligatorioassi.ReferenceMonitor;
import obligatorioassi.SecurityLevel;

public class SecureSystem {
	ReferenceMonitor refMon = new ReferenceMonitor();
	static InstructionObject instrobj;
	static HashMap<String, SecurityLevel> subjectManager = new HashMap<String, SecurityLevel>();

	public static void main(String[] args) throws FileNotFoundException {

		// Crea un securitySystem y pasa el txt en el filePath
		SecureSystem sys = new SecureSystem("C:\\Users\\santi\\OneDrive\\Escritorio\\Santiago\\ORT\\ASSI\\ObligatorioASSI\\pruebaArchivo.txt");
		File file2 = new File("C:\\Users\\santi\\OneDrive\\Escritorio\\Santiago\\ORT\\ASSI\\ObligatorioASSI\\pruebaArchivo.txt");

		// Crea ambos niveles de seguridaad
		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		// Creo los sujetos Lyle y Hal
		sys.createSubject("Lyle", low);
		sys.createSubject("Hal", high);

		// Creo los objs LObj y HObj
		sys.getReferenceMonitor().createNewObject("LObj", low);
		sys.getReferenceMonitor().createNewObject("HObj", high);

		// Recorro e imprimo el txt revisando el estado
		Scanner scan = new Scanner(file2);
		while (scan.hasNextLine()) {
			String curLine = scan.nextLine();
			instrobj = new InstructionObject(curLine);
			printState();
		}
		scan.close();
	}

	// Constructor secureSystem
	public SecureSystem(String fileName) throws FileNotFoundException {
		File file1 = new File(fileName);
		System.out.println("Reading from file: " + file1);
		System.out.println();
	}

	// Constructor del subjManager
	void createSubject(String name, SecurityLevel secLev) {
		subjectManager.put(name, secLev);
	}
        
	public static HashMap<String, SecurityLevel> getSubjectManager() {
		return subjectManager;
	}

	public ReferenceMonitor getReferenceMonitor() {
		return refMon;
	}

	// Imprimo estado de todos los sujetos/objetos del txt
	static void printState() {
		if (instrobj.getInstruction().equals("BAD")) {
			System.out.println("Bad Instruction");
			System.out.println("The current state is: ");
			System.out.println("   LObj has value: "
					+ ObjectManager.getValueManager().get("LObj"));
			System.out.println("   HObj has value: "
					+ ObjectManager.getValueManager().get("HObj"));
			System.out.println("   Lyle has recently read: "
					+ ObjectManager.getReadManager().get("Lyle"));
			System.out.println("   Hal has recently read: "
					+ ObjectManager.getReadManager().get("Hal"));
			System.out.println();
		} else if (instrobj.getInstruction().equals("READ")) {
			System.out.println(instrobj.getSubject() + " reads "
					+ instrobj.getObject());
			System.out.println("The current state is: ");
			System.out.println("   LObj has value: "
					+ ObjectManager.getValueManager().get("LObj"));
			System.out.println("   HObj has value: "
					+ ObjectManager.getValueManager().get("HObj"));
			System.out.println("   Lyle has recently read: "
					+ ObjectManager.getReadManager().get("Lyle"));
			System.out.println("   Hal has recently read: "
					+ ObjectManager.getReadManager().get("Hal"));
			System.out.println();
		} else if (instrobj.getInstruction().equals("WRITE")) {
			System.out.println(instrobj.getSubject() + " writes value "
					+ instrobj.getValue() + " to " + instrobj.getObject());
			System.out.println("The current state is: ");
			System.out.println("   LObj has value: "
					+ ObjectManager.getValueManager().get("LObj"));
			System.out.println("   HObj has value: "
					+ ObjectManager.getValueManager().get("HObj"));
			System.out.println("   Lyle has recently read: "
					+ ObjectManager.getReadManager().get("Lyle"));
			System.out.println("   Hal has recently read: "
					+ ObjectManager.getReadManager().get("Hal"));
			System.out.println();
		}
	}
}