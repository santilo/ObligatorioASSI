package obligatorioassi;

public class InstructionObject {
        private String instruccion;
	private String sujeto;
	private String objeto;
	private int valor;

	public InstructionObject(String instr) {
            this.instruccion	= " ";
            String[] linea = instr.split(" ");
            linea[1] = linea[1].toLowerCase();
            linea[2] = linea[2].toLowerCase();
            //si llega instruccion de Run, creo una nueva instancia de InstrucionObject
             if (linea[0].toUpperCase().equals("RUN")) {
                this.instruccion = "RUN";
                this.sujeto = linea[1];
                ReferenceMonitor.Ejecutar(obtenerInstructionObject());
           
            //Reviso si en la primera posicion de la instruccion llega un comando valido
            } else if (!linea[0].toUpperCase().equals("READ") && !linea[0].toUpperCase().equals("WRITE")&& linea[0].toUpperCase().equals("CREATE") && !linea[0].toUpperCase().equals("DESTROY") && !linea[0].toUpperCase().equals("RUN")) {
		this.instruccion = "BAD";
           
            // Reviso si la instruccion es del largo apropiado acorde a la operacion a ejecutar
            }else if ((linea.length != 4 && linea[0].toUpperCase().toUpperCase().equals("WRITE")) || (linea.length != 3 && linea[0].toUpperCase().toUpperCase().equals("READ")) || (linea.length != 3 && linea[0].toUpperCase().equals("CREATE"))
                || (linea.length != 3 && linea[0].toUpperCase().equals("DESTROY")) || (linea.length != 2 && linea[0].toUpperCase().equals("RUN"))) {
                this.instruccion = "BAD";
              
            // Reviso si el sujeto y objeto existen
            } else if (!SecureSystem.obtenerSujetos().containsKey(linea[1]) ) {
                this.instruccion = "BAD";
                
                //Si voy a escribir, el valor tiene que ser numerico
            } else if (linea[0].toUpperCase().equals("WRITE") && !esNumerico(linea[3])) {
                this.instruccion = "BAD";
             
            //tiene que existir el objeto a eliminar    
            } else if (linea[0].toUpperCase().equals("DESTROY") && !ObjectManager.obtenerObjetos().containsKey(linea[2])) {
                   this.instruccion = "BAD";
      
            //si ya existe el objeto a crear esta mal
            } else if (linea[0].toUpperCase().equals("CREATE") && ObjectManager.obtenerObjetos().containsKey(linea[2])) {
                this.instruccion = "BAD";
            }
             
            if(this.instruccion != "BAD"){
               switch(linea[0].toUpperCase()) {
                    case "READ":
                        this.instruccion = "READ";
                        this.sujeto = linea[1];
                        this.objeto = linea[2];
                        ReferenceMonitor.Leer(obtenerInstructionObject());
                        break;
                    case "WRITE":
                        this.instruccion = "WRITE";
                        this.sujeto = linea[1];
                        this.objeto = linea[2];
                        this.valor = Integer.parseInt(linea[3]);
                        ReferenceMonitor.Escribir(obtenerInstructionObject());
                    break;
                    case "CREATE":
                        this.instruccion = "CREATE";
                        this.sujeto = linea[1];
                        this.objeto = linea[2];
                        ReferenceMonitor.Crear(obtenerInstructionObject());
                    case "DESTROY":
                        this.instruccion = "DESTROY";
                        this.sujeto = linea[1];
                        this.objeto = linea[2];
                        ReferenceMonitor.Destruir(obtenerInstructionObject());  
                        break;
                }
            }
	}
         
        
	// Reviso si mi valor es un numero entero o no
	public static boolean esNumerico(String valor) {
		try {
                    Integer.parseInt(valor);
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
