package obligatorioassi;

public class InstructionObject {
        private String instruccion;
	private String sujeto;
	private String objeto;
	private int valor;

	public InstructionObject(String instr) {
            this.instruccion	= " ";
            String[] instruccion = instr.split(" ");
            // Reviso si la instruccion es del largo apropiado acorde a la operacion a ejecutar
            if ((instruccion.length != 4 && instruccion[0].toUpperCase().equals("WRITE")) || (instruccion.length != 3 && instruccion[0].toUpperCase().equals("READ"))) {
                this.instruccion = "BAD";
                // Reviso si la primer palabra es read/write
            } else if (!instruccion[0].toUpperCase().equals("READ") && !instruccion[0].toUpperCase().equals("WRITE")) {
                this.instruccion = "BAD";
                // Reviso si el sujeto y objeto existen
            } else if (!SecureSystem.obtenerSujetos().containsKey(instruccion[1].toLowerCase()) || !ObjectManager.obtenerObjetos().containsKey(instruccion[2].toLowerCase()) ) {
                this.instruccion = "BAD";
                //Si voy a escribir, el valor tiene que ser numerico
            } else if (instruccion[0].toUpperCase().equals("WRITE") && !esNumerico(instruccion[3])) {
                this.instruccion = "BAD";
            }
            if(this.instruccion != "BAD"){
                if (instruccion[0].toUpperCase().equals("READ")) {
                    this.instruccion = "READ";
                    this.sujeto = instruccion[1].toLowerCase();
                    this.objeto = instruccion[2].toLowerCase();
                    ReferenceMonitor.Leer(obtenerInstructionObject());
                } else if (instruccion[0].toUpperCase().equals("WRITE")) {
                    this.instruccion = "WRITE";
                    this.sujeto = instruccion[1].toLowerCase();
                    this.objeto = instruccion[2].toLowerCase();
                    this.valor = Integer.parseInt(instruccion[3]);
                    ReferenceMonitor.Escribir(obtenerInstructionObject());
                }
            }
	}
        
        
	// Reviso si mi valor es un numero entero o no
	public static boolean esNumerico(String s) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
	}

	public InstructionObject obtenerInstructionObject() {
		return this;
	}

	public String obtenerInstruccion() {
		return this.instruccion;
	}

        public int obtenerValor() {
		return this.valor;
	}
        
	public String obtenerSujeto() {
		return this.sujeto;
	}

	public String obtenerObjeto() {
		return this.objeto;
	}
}
