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
    static void AltaObjeto(String name, SecurityLevel secLev) {
            objetos.put(name.toLowerCase(), secLev);
            valores.put(name.toLowerCase(), 0);
    }
    //Cuando el referenceMonitor valida la instruccion...
    static void Escribir(InstructionObject instr) {
            int val = instr.obtenerValor();
            String objeto = instr.obtenerObjeto();

            valores.put(objeto, val);
    }
    //Cuando el referenceMonitor valida la instruccion...
    static void Leer(InstructionObject instr) {
            String sujeto = instr.obtenerSujeto().toLowerCase();
            String objeto = instr.obtenerObjeto().toLowerCase();

            lecturas.put(sujeto, valores.get(objeto));
    }

    //Si la instruccion es invalida, fija lo ultimo leido como 0
    static void InstruccionMal(InstructionObject instr) {
            String sujeto = instr.obtenerSujeto().toLowerCase();

            lecturas.put(sujeto, 0);

    }
}
