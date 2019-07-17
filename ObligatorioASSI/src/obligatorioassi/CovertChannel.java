package obligatorioassi;

import java.io.*;
import java.util.*;

public class CovertChannel {

	static long numOfBits = 0;
	static boolean generoLog;
        static FileOutputStream logger;    


	public static void main(String[] args) throws IOException {
		File file2;
		if (args[0].equals("v")) {
			System.out.println("Genero Log");
			file2 = new File(args[1]);
			generoLog = true;
                        String nombreLog = "log.txt";
                        logger = new FileOutputStream(nombreLog);
		} else {
			file2 = new File(args[0]);
			generoLog = false;
		}
                SecureSystem sys = new SecureSystem(args[0]);        

		//Creo LOW and HIGH niveles de seguridad
		SecurityLevel low = SecurityLevel.LOW;
		SecurityLevel high = SecurityLevel.HIGH;

		//Creo LYLE y HAL
		sys.createSubject("lyle", low);
		sys.createSubject("hal", high);

                // Defino el nombre del archivo que voy a crear como salida
		Scanner scan = new Scanner(file2);
		String nombreArchivo = file2.getName() + ".out";
		
		byte[] nuevaLinea = System.getProperty("line.separator").getBytes();
		FileOutputStream fos = new FileOutputStream(nombreArchivo);
		
		final long tiempoInicio = System.currentTimeMillis();

		// While file has next line
		while (scan.hasNextLine()) {
			String lineaActual = scan.nextLine();
			byte[] buf = lineaActual.getBytes();

			ByteArrayInputStream input = new ByteArrayInputStream(buf);
			int numeroDeBytes = input.available();

                        //Mientras tengamos bytes en el archivo
			while (numeroDeBytes > 0) {
				int inputByte = input.read();
				String inputBit = Integer.toBinaryString(inputByte);
				int inputLength = inputBit.length();
				numOfBits += 8;
                                // Me fijo que el largo de los bytes no sea distinto de 8
				if (inputLength != 8) {
					while (inputLength != 8) {
						String zero = "0";
						inputBit = zero.concat(inputBit);
						inputLength = inputBit.length();
					}
				}

				// Recorro los bits y me fijo si es 0 o 1, dependiendo de cual sea, según que ejecuto
				for (int i = 0; i < inputLength; i++) {
					if (inputBit.charAt(i) == '0') 
                                        {
                                            String[] instr = 
                                            { "CREATE HAL OBJ", "CREATE LYLE OBJ",
                                                "WRITE LYLE OBJ 1", "READ LYLE OBJ",
                                                "DESTROY LYLE OBJ", "RUN LYLE" 
                                            };
                                            //Si generoLog está en true, escribo en el log
                                            if (generoLog) 
                                            {
                                                String log = Arrays.toString(instr);
                                                byte[] resultadoLog = log.getBytes();
                                                logger.write(resultadoLog);
                                                logger.write(nuevaLinea);
                                            }
                                            SecureSystem.passInstructions(instr);
					} 
                                        else 
                                        {
                                            String[] instr = 
                                                { 
                                                    "CREATE LYLE OBJ",
                                                    "WRITE LYLE OBJ 1", "READ LYLE OBJ",
                                                    "DESTROY LYLE OBJ", "RUN LYLE" 
                                                };
                                            //Si generoLog está en false, no escribo en log
                                            if (generoLog) 
                                            {
                                                String log = Arrays.toString(instr);
                                                byte[] resultadoLog = log.getBytes();
                                                logger.write(resultadoLog);
                                                logger.write(nuevaLinea);
                                            }
                                            SecureSystem.passInstructions(instr);
					}
				}
                                // Escribo en el archivo de salida
				numeroDeBytes--;
				String resultado = ReferenceMonitor.obtenerEjecucion();
				byte[] resultArray = resultado.getBytes();
				fos.write(resultArray);
				ReferenceMonitor.obtenerEjecuciones().put("lyle", "temp");
			}
			fos.write(nuevaLinea);
		}
		final long tiempoFin = System.currentTimeMillis();

		// Resumen de ejecución
		double bytes = file2.length();
		long bandwidth = numOfBits / (tiempoFin - tiempoInicio);
		System.out.println("Document: " + file2.getName());
		System.out.println("Size: " + bytes + " bytes");
		System.out.println("Bandwidth: " + bandwidth + " bits/ms");
		scan.close();
		fos.close();
                
		if (generoLog){
                    logger.close();
                }
                
	}

}