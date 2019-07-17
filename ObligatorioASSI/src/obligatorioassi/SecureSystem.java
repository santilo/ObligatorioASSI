package obligatorioassi;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class SecureSystem {
	static InstructionObject instructionObject;
	static HashMap<String, SecurityLevel> sujetos = new HashMap<String, SecurityLevel>();
        ReferenceMonitor referenceMonitor = new ReferenceMonitor();
        
	static void procesarIntruccion(String[] palabra) {
            for (String termino : palabra) {
                instructionObject = new InstructionObject(termino);
            }
	}
	//Constructor
	public SecureSystem(String fileName) throws FileNotFoundException {

	}
	//Constructro de manejador de sujetos
	void crearSujeto(String name, SecurityLevel secLev) {
		sujetos.put(name, secLev);
	}
	// Returns the subject manager
	public static HashMap<String, SecurityLevel> obtenerSujetos() {
		return sujetos;
	}
	//Devuelve una instancia de la clase Reference Monitor
	public ReferenceMonitor obtenerReferenceMonitor() {
		return referenceMonitor;
	}

        //Estado de la aplicacion...
	static void printState() {
            
             switch(instructionObject.obtenerInstruccion()) {
                case "BAD":
                    System.out.println("Instrucción mal formada");
                    System.out.println("El estado actual del sistema es: ");
                    System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                    System.out.println("   HObj tiene el valor: " + ObjectManager.obtenerValores().get("hobj"));
                    System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                    System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                    System.out.println();
                    break;
                case "READ":
                    System.out.println("Instrucción mal formada");
                    System.out.println("El estado actual del sistema es: ");
                    System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                    System.out.println("   HObj tiene el valor: " + ObjectManager.obtenerValores().get("hobj"));
                    System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                    System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                    System.out.println();
                    break;
                case "WRITE":    
                     System.out.println(instructionObject.obtenerSujeto()+ " escribe " + instructionObject.obtenerValor()+ " a " + instructionObject.obtenerObjeto());
                    System.out.println("El estado actual del sistema es: ");
                    System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                    System.out.println("   HObj tiene el valor: " + ObjectManager.obtenerValores().get("hobj"));
                    System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                    System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                    System.out.println();
                    break;
                case "CREATE":    
                    System.out.println(instructionObject.obtenerSujeto()+ " crea objeto " + instructionObject.obtenerObjeto());
                    System.out.println("El estado actual del sistema es: ");
                    System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                    System.out.println("   HObj tiene el valor: " + ObjectManager.obtenerValores().get("hobj"));
                    System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                    System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                    System.out.println();
                    System.out.println("El estado actual del sistema es: ");
                    for (String name : ObjectManager.obtenerObjetos().keySet()) {
                        int value = ObjectManager.obtenerObjetos().get(name).obtenerDominancia();
                        System.out.println(name + " " + value);
                    }
                    break;
                case "DESTROY":
                    System.out.println(instructionObject.obtenerSujeto()+ " destruye objeto " + instructionObject.obtenerObjeto());
                    System.out.println("El estado actual del sistema es: ");
                    System.out.println("   LObj tiene el valor: " + ObjectManager.obtenerValores().get("lobj"));
                    System.out.println("   HObj tiene el valor: " + ObjectManager.obtenerValores().get("hobj"));
                    System.out.println("   Lyle recientemente leyo: " + ObjectManager.obtenerLecturas().get("lyle"));
                    System.out.println("   Hal recientemente leyo: " + ObjectManager.obtenerLecturas().get("hal"));
                    System.out.println();
                    System.out.println("El manejador de objectos actual es: ");
                    for (String name : ObjectManager.obtenerObjetos().keySet()) {
                        int value = ObjectManager.obtenerObjetos().get(name).obtenerDominancia();
                        System.out.println(name + " " + value);
                    }
                    break;
             
             
            }
            System.out.println();
	}
}