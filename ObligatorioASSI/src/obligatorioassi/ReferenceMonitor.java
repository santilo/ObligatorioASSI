
package obligatorioassi;

public class ReferenceMonitor {
    public ReferenceMonitor() {
        // Se agregan Hal y Lyle al readManager
        ObjectManager.obtenerLecturas().put("hal", 0);
        ObjectManager.obtenerLecturas().put("lyle", 0);
	}

	// Genero nuevo objeto en el objectManager
	public void AltaObjeto(String name, SecurityLevel secLev) {
		ObjectManager.AltaObjeto(name.toLowerCase(), secLev);
	}

	// Reviso si la instruccion write es "segura" y envio la ejecucion al objectManager
	static void Escribir(InstructionObject instruccion) {
		String subj = instruccion.obtenerSujeto();
		String obj = instruccion.obtenerObjeto();

		SecurityLevel sujetoSeguridad = SecureSystem.obtenerSujetos().get(subj);
		int nivelSeguridadSujeto = sujetoSeguridad.obtenerDominancia();

		SecurityLevel objetoSeguridad = ObjectManager.obtenerObjetos().get(obj);
		int nivelSeguridadObjeto = objetoSeguridad.obtenerDominancia();
                
                //SI EL SUJETO ES MEJOR O IGUAL QUE EL OBJETO, ENTONCES PUEDE ESCRIBIR.
		if (nivelSeguridadSujeto <= nivelSeguridadObjeto) {
			ObjectManager.Escribir(instruccion);
		}
	}

	// Reviso si la instruccion write es "segura" y envio la ejecucion al objectManager
	static void Leer(InstructionObject instruccion) {
		String sujeto = instruccion.obtenerSujeto();
		String objeto = instruccion.obtenerObjeto();

		SecurityLevel SujetoSeguridad = SecureSystem.obtenerSujetos().get(sujeto);
		int nivelSeguridadSujeto = SujetoSeguridad.obtenerDominancia();

		SecurityLevel objetoSeguridad = ObjectManager.obtenerObjetos().get(objeto);
		int nivelSeguridadObjeto = objetoSeguridad.obtenerDominancia();

                //SI EL SUJETO ES MAYOR O IGUAL QUE EL OBJETO, ENTONCES PUEDE LEER.
                
		if (nivelSeguridadSujeto >= nivelSeguridadObjeto) {
			ObjectManager.Leer(instruccion);
		} else {
			ObjectManager.InstruccionMal(instruccion);
		}
	}
}
