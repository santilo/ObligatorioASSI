package obligatorioassi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SecureSystem {
	ReferenceMonitor refMon = new ReferenceMonitor();
	static InstructionObject instrobj;
	static HashMap<String, SecurityLevel> sujetos = new HashMap<String, SecurityLevel>();
        static  File file;
	
        public static void main(String[] args) throws FileNotFoundException {
            // Crea securitySystem 
            SecureSystem sys = new SecureSystem(args[0]);

            // Crea ambos niveles de seguridaad
            SecurityLevel low = SecurityLevel.LOW;
            SecurityLevel high = SecurityLevel.HIGH;

            // Creo los sujetos lyle y hal
            sys.createSubject("lyle", low);
            sys.createSubject("hal", high);

            // Creo los objs lobj y hobj
            sys.obtenerReferenceMonitor().AltaObjeto("lobj", low);
            sys.obtenerReferenceMonitor().AltaObjeto("hobj", high);

            // Recorro e imprimo el txt revisando el estado de cada instruccion
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                instrobj = new InstructionObject(linea);
                printState();
            }
            scanner.close();
	}

	// Constructor secureSystem
	public SecureSystem(String fileName) throws FileNotFoundException {
		file = new File(fileName);
		System.out.println("Las instrucciones estan siendo leídas desde: " + file);
		System.out.println();
  
	}

	// Constructor del subjManager
	void createSubject(String name, SecurityLevel secLev) {
		sujetos.put(name.toLowerCase(), secLev);
	}
        
	public static HashMap<String, SecurityLevel> obtenerSujetos() {
		return sujetos;
	}

	public ReferenceMonitor obtenerReferenceMonitor() {
		return refMon;
	}
     
        // Imprimo estado de todos los sujetos/objetos del txt
	static void printState() {
            switch(instrobj.obtenerInstruccion()) {
            case "BAD":
                System.out.println("Instrucción mal formada");
                System.out.println("El estado actual del sistema es: ");
                System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                System.out.println("   HObj tiene el valor " + ObjectManager.obtenerValores().get("hobj"));
                System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                System.out.println();
                break;
           
            case "READ":
                System.out.println(instrobj.obtenerSujeto() + " lee " + instrobj.obtenerObjeto());
                System.out.println("El estado actual del sistema es: ");
                System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                System.out.println("   HObj tiene el valor " + ObjectManager.obtenerValores().get("hobj"));
                System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                System.out.println();
                break;

            case "WRITE":
                System.out.println(instrobj.obtenerSujeto() + " escribe el valor " + instrobj.obtenerValor() + " a " + instrobj.obtenerObjeto());
                System.out.println("El estado actual del sistema es: ");
                System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                System.out.println("   HObj tiene el valor " + ObjectManager.obtenerValores().get("hobj"));
                System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                System.out.println();
                break;
          }
	}
	
}