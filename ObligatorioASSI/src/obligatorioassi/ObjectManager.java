package obligatorioassi;

import java.util.HashMap;

public class ObjectManager {
    static HashMap<String, SecurityLevel> objetos = new HashMap<String, SecurityLevel>();
	static HashMap<String, Integer> valores = new HashMap<String, Integer>();
	static HashMap<String, Integer> lecturas = new HashMap<String, Integer>();

	public static HashMap<String, SecurityLevel> obtenerObjetos() {
		return objetos;
	}
 
	public static HashMap<String, Integer> obtenerValores() {
		return valores;
	}

	public static HashMap<String, Integer> obtenerLecturas() {
		return lecturas;
	}
         //Crea un nuevo objeto a traves del object manager
	static void AltaObjeto(String nombre, SecurityLevel secLev) {
		objetos.put(nombre.toLowerCase(), secLev);
		valores.put(nombre.toLowerCase(), 0);
	}
        //Cuando el referenceMonitor valida la instruccion...
	static void Escribir(InstructionObject instruccion) {
		int val = instruccion.obtenerValor();
		String objeto = instruccion.obtenerObjeto();

		valores.put(objeto, val);
	}
        
        //Cuando el referenceMonitor valida la instruccion...
	static void Leer(InstructionObject instruccion) {
		String sujeto = instruccion.obtenerSujeto().toLowerCase();
		String objeto = instruccion.obtenerObjeto().toLowerCase();

		lecturas.put(sujeto, valores.get(objeto));

	}

        // Elimina un objeto del sistema
	static void Destruir(InstructionObject instruccion) {
            String objeto = instruccion.obtenerObjeto();
            obtenerObjetos().remove(objeto);
	}
           
	//Si la instruccion es invalida, fija lo ultimo leido como 0
	static void InstruccionMal(InstructionObject instr) {
		String sujeto = instr.obtenerSujeto().toLowerCase();

		lecturas.put(sujeto, 0);

	}
}
