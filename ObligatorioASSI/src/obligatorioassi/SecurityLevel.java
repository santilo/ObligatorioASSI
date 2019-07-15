package obligatorioassi;

public class SecurityLevel {
	public static SecurityLevel HIGH = new SecurityLevel(1);
	public static SecurityLevel LOW = new SecurityLevel(0);
        int dominacion;

	public SecurityLevel(int nivel) {
		dominacion = nivel;
	}

	// Retorna el valor de dominacion para saber quien domina
	int obtenerDominancia() {
		return dominacion;
	}
}
