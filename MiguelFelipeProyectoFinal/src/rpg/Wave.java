/**
 * 
 */
package rpg;

import java.util.ArrayList;
import java.util.Collections;

/**
 * CLASE ORDA
 * 
 * Esta Clase genera una lista de enemigos ordenada de menos a más raro para luchar contra ellos
 * 
 * @author Angel
 * @version 1.0.0
 */
public class Wave {
	
	private ArrayList <Enemy> orda;

	/**
	 * @param p para la creacion de la orda en base al nivel actual del jugador
	 */
	public Wave(Player p) {
		
		this.orda = new ArrayList<Enemy>();
		crearOrda(p);
		Collections.sort(this.orda);
		
	}
	
	
	
	
	/**
	 * @return orda
	 */
	public ArrayList<Enemy> getOrda() {
		return orda;
	}

	/**
	 * @param orda
	 */
	public void setOrda(ArrayList<Enemy> orda) {
		this.orda = orda;
	}
	
	
	/**
	 * @param p Jugador
	 * Se crea la nueva orda en base al nivel del jugador para así balancear el nivel creciente de este
	 * Se aplica un sistema de creacion de enemigos por probabilidad para que todas las ordas sean distintas
	 */
	public void crearOrda(Player p) {
		
		double prob;
		
		for(int i = p.getLvl();i>=0;i--) {
			
			prob = Math.random();
			
			if(prob <= 0.6) {
				this.orda.add(new Goblin(null, i, i, i, i));		
			}
			else if (prob > 0.6 && prob < 0.9) {
				this.orda.add(new Orco(null, i, i, i, i));
			}
			else {this.orda.add(new Boss(null, i, i, i, i));}
			
		}
		
		
		
	}

}
