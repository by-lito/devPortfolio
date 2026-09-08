/**
 * 
 */
package rpg;

/**
 * @author Angel Miguel Felipe
 * @version 1.0.0
 * 
 * CLASE GOBLIN
 * 
 * Es la clase hija de Enemigo que representará a nuestro enemigo Goblin, al igual que el resto puede pedir parámetros
 * por hacer más simple el ejercicio he establecido los valores al crearse
 * 
 */
public class Goblin extends Enemy {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param name nombre
	 * @param hp vida
	 * @param attack ataque
	 * @param defense defensa
	 * @param rareza
	 */
	
	public Goblin(String name, int hp, int attack, int defense, int rareza) {
		super( name = "GOBLIN", hp = 100, attack = 50, defense = 0, rareza = 1);
		setRareza(1);
	}

	/**
	 * Accion específica del GOBLIN
	 * */
	@Override
	public String accion() {
		
		return "El goblin te ha robado!";
	}
	
	
	
}
