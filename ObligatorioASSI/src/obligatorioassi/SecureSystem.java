package obligatorioassi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SecureSystem {
	ReferenceMonitor refMon = new ReferenceMonitor();
	static InstructionObject instrobj;
	static HashMap<String, SecurityLevel> subjectManager = new HashMap<>();
        static File input_file;
	public static void main(String[] args) throws FileNotFoundException {

		// Crea un securitySystem y pasa el txt en el filePath
		SecureSystem sys = new SecureSystem("C:\\pruebaArchivo.txt");
		
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
		Scanner scan = new Scanner(input_file);
		while (scan.hasNextLine()) {
			String curLine = scan.nextLine();
			instrobj = new InstructionObject(curLine);
			printState();
		}
		scan.close();
	}

	// Constructor secureSystem
	public SecureSystem(String fileName) throws FileNotFoundException {
		input_file = new File(fileName);
		System.out.println("Leyendo desde: " + input_file);
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
			System.out.println("Instrucción mal formada");
			System.out.println("Estado Actual: ");
			System.out.println("   LObj tiene el valor: " + ObjectManager.getValueManager().get("LObj"));
			System.out.println("   HObj tiene el valor: " + ObjectManager.getValueManager().get("HObj"));
			System.out.println("   Lyle ha leído: " + ObjectManager.getReadManager().get("Lyle"));
			System.out.println("   Hal ha leído: " + ObjectManager.getReadManager().get("Hal"));
			System.out.println();
		} else if (instrobj.getInstruction().equals("READ")) {
			System.out.println(instrobj.getSubject() + " lee el valor" + instrobj.getObject());
			System.out.println("Estado Actual: ");
			System.out.println("   LObj tiene el valor: " + ObjectManager.getValueManager().get("LObj"));
			System.out.println("   HObj tiene el valor: " + ObjectManager.getValueManager().get("HObj"));
			System.out.println("   Lyle ha leído: " + ObjectManager.getReadManager().get("Lyle"));
			System.out.println("   Hal ha leído: " + ObjectManager.getReadManager().get("Hal"));
			System.out.println();
		} else if (instrobj.getInstruction().equals("WRITE")) {
			System.out.println(instrobj.getSubject() + " escribe el valor "  + instrobj.getValue() + " a " + instrobj.getObject());
			System.out.println("Estado Actual: ");
			System.out.println("   LObj tiene el valor: " + ObjectManager.getValueManager().get("LObj"));
			System.out.println("   HObj tiene el valor: " + ObjectManager.getValueManager().get("HObj"));
			System.out.println("   Lyle ha leído: " + ObjectManager.getReadManager().get("Lyle"));
			System.out.println("   Hal ha leído: " + ObjectManager.getReadManager().get("Hal"));
			System.out.println();
		}
	}
}