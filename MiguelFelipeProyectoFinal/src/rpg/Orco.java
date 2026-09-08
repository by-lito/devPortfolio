/**
 * 
 */
package rpg;

/**
 * @author Angel Miguel Felipe
 * @version 1.0.0
 * 
 * CLASE ORCO
 * 
 * Es la clase hija de Enemigo que representará a nuestro enemigo Orco, al igual que el resto puede pedir parámetros
 * por hacer más simple el ejercicio he establecido los valores al crearse
 * 
 */
public class Orco extends Enemy {

	private static final long serialVersionUID = 1L;

	/**
	 * @param name
	 * @param hp
	 * @param attack
	 * @param defense
	 */
	public Orco(String name, int hp, int attack, int defense, int rareza) {
		super(name = "ORCO", hp = 300, attack = 100, defense = 50, rareza = 2);
		setRareza(2);
	}
	/**
	 * Accion específica del ORCO
	 * */
	@Override
	public String accion() {
		return "Este Orco cree que es muy guapo, te das cuenta de que ahora se parece a Brad Pitt";
	}

}
