/**
 * 
 */
package rpg;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Angel Miguel Felipe
 * @version 1.0.0
 * 
 * CLASE NPC
 * 
 * Es la clase hija de Character, la unica no dañable pero si Hablable
 * 
 * Incorpora una lista de los enemigos para posteriormente ordenarlas junto con el estado actual del jugador
 * 
 */
public class NPC extends Character implements Talkable {

	private static final long serialVersionUID = 1L;

	private ArrayList<Character> datos; 

	/**
	 * @param name
	 * @param hp
	 * @param attack
	 * @param defense
	 */
	
	public NPC(String name, int hp, int attack, int defense) {
		super(name, hp, attack, defense);
		datos = new ArrayList<Character>();
		datos.add(new Boss(name, defense, defense, defense, defense));
		datos.add(new Goblin(name, hp, attack, defense, defense));
		datos.add(new Orco(name, hp, attack, defense, defense));
	}

	/**
	 * talk
	 *@param p Jugador para incluirlo en los datos del juego
	 *@return String datos ordenados de todos los personajes del juego te incluye a ti.
	 */
	@Override
	public String talk(Player p) {
		
		datos.add(p);
		Collections.sort(datos, new ComparadorAtaque());
		
		String mensaje = "BUENOS DIAS JUGADOR ESTOS SON LOS PERSONAJES ORDENADOS POR ATAQUE:";
		
		for(Character c : datos) {
			
			mensaje += "\n---------------------------------------------------------------------------------------\n"+c.toString();
			
		}
		
		return mensaje;
	}

}
