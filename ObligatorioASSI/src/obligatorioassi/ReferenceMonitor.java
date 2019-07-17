package obligatorioassi;

import java.util.HashMap;

public class ReferenceMonitor {

    static HashMap<String, String> ejecuciones = new HashMap<String, String>();
    static String ejecucion_instruccion;

    public ReferenceMonitor() {
        // Se agregan Hal y Lyle al readManager
        ObjectManager.obtenerLecturas().put("hal", 0);
        ObjectManager.obtenerLecturas().put("lyle", 0);
        ejecuciones.put("hal", "temp");
        ejecuciones.put("lyle", "temp");
    }

    public static String obtenerEjecucion() {
        return ejecucion_instruccion;
    }

    public static HashMap<String, String> obtenerEjecuciones() {
        return ejecuciones;
    }

    // Genero nuevo objeto en el objectManager
    static public void AltaObjeto(String name, SecurityLevel secLev) {
        ObjectManager.AltaObjeto(name ,secLev);
    }

    // Reviso si la instruccion write es "segura" y envio la ejecucion al objectManager
    static void Escribir(InstructionObject instruccion) {
        String subj = instruccion.obtenerSujeto();
        String obj = instruccion.obtenerObjeto();

        SecurityLevel SujetoSeguridad = SecureSystem.obtenerSujetos().get(subj);
        int NivelSeguridadSujeto = SujetoSeguridad.obtenerDominancia();

        SecurityLevel objetoSeguridad = ObjectManager.obtenerObjetos().get(obj);
        int NivelSeguridadObjeto = objetoSeguridad.obtenerDominancia();

        //SI EL SUJETO ES MEJOR O IGUAL QUE EL OBJETO, ENTONCES PUEDE ESCRIBIR.
        if (NivelSeguridadSujeto <= NivelSeguridadObjeto) {
            ObjectManager.Escribir(instruccion);
        }
    }

    // Reviso si la instruccion write es "segura" y envio la ejecucion al objectManager
    static void Leer(InstructionObject instruccion) {
        String sujeto = instruccion.obtenerSujeto();
        String objeto = instruccion.obtenerObjeto();

        SecurityLevel SujetoSeguridad = SecureSystem.obtenerSujetos().get(sujeto);
        int NivelSeguridadSujeto = SujetoSeguridad.obtenerDominancia();

        SecurityLevel objetoSeguridad = ObjectManager.obtenerObjetos().get(objeto);
        int NivelSeguridadObjeto = objetoSeguridad.obtenerDominancia();

                //SI EL SUJETO ES MAYOR O IGUAL QUE EL OBJETO, ENTONCES PUEDE LEER.
        if (NivelSeguridadSujeto >= NivelSeguridadObjeto) {
            ObjectManager.Leer(instruccion);
        } else {
            ObjectManager.InstruccionMal(instruccion);
        }
    }

    //Ejecuta la operacion DESTROY  si el nivel de seguridad es el adecuado
    static void Destruir(InstructionObject instruccion) {
        String sujeto = instruccion.obtenerSujeto();
        String objeto = instruccion.obtenerObjeto();

        SecurityLevel sujetoSeguridad = SecureSystem.obtenerSujetos().get(sujeto);
        int NivelSeguridadSujeto = sujetoSeguridad.obtenerDominancia();

        SecurityLevel objetoSeguridad = ObjectManager.obtenerObjetos().get(objeto);
        int NivelSeguridadObjeto = objetoSeguridad.obtenerDominancia();

        if (NivelSeguridadSujeto <= NivelSeguridadObjeto) {
            ObjectManager.Destruir(instruccion);
        } else {
            System.out.println("No se puede ejecutar la operación DESTROY, los permisos no son los adecuados.");
        }
    }

    //Ejecuta la sentencia CREATE
    static void Crear(InstructionObject instruccion) {
        String sujeto = instruccion.obtenerSujeto();
        SecurityLevel sujetoSeguirdad = SecureSystem.obtenerSujetos().get(sujeto);
        AltaObjeto(instruccion.obtenerObjeto(), sujetoSeguirdad);
    }

    // Execute the RUN call.
    static void Ejecutar(InstructionObject instruccion) {
        int tmp = ObjectManager.obtenerLecturas().get(instruccion.obtenerSujeto());
        String ejecucion = ejecuciones.get(instruccion.obtenerSujeto());
        //primera ejecucion
        if (ejecucion.equals("temp")) {
            if (tmp != 0) {
                ejecucion = "1";
                ejecuciones.put(instruccion.obtenerSujeto(), ejecucion);
            } else {
                ejecucion = "0";
                ejecuciones.put(instruccion.obtenerSujeto(), ejecucion);
            }
        } else if (ejecucion.length() < 8) {
            if (tmp != 0) {
                ejecucion = ejecucion.concat("1");
                ejecuciones.put(instruccion.obtenerSujeto(), ejecucion);
            } else {
                ejecucion = ejecucion.concat("0");
                ejecuciones.put(instruccion.obtenerSujeto(), ejecucion);
            }
        }

        if (ejecucion.length() == 8) {
            ejecucion_instruccion = ejecucion;
            int charTemp = Integer.parseInt(ejecucion_instruccion, 2);
            ejecucion_instruccion = new Character((char) charTemp).toString();
        }

    }

}
