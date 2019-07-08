/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obligatorioassi;

/**
 *
 * @author santi
 */
public class SecurityLevel {
        int domination;
	public static SecurityLevel HIGH = new SecurityLevel(1);
	public static SecurityLevel LOW = new SecurityLevel(0);

	// Creates a security level
	public SecurityLevel(int level) {
		domination = level;
	}

	// Returns the domination for checking security levels later for who
	// dominates
	int getDomination() {
		return domination;
	}
}
