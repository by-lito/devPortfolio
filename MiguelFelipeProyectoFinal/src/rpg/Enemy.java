/**
 * 
 */
package rpg;

/**
 * @version 1.0.0
 * 
 * CLASE ENEMY
 * 
 * Es la clase hija de Character y padre de los enemigos que representará a nuestro enemigo plantilla.
 * incorpora isMuerto boolean que determina si este enemigo está muerto o no
 * incorpora rareza que utilizaremos para determinar la importancia y los porcentajes en los que nos encontramos con el enemigo.
 * 
 * @author Angel
 */
public abstract class Enemy extends Character implements Daniable,Comparable<Enemy> {

	private static final long serialVersionUID = 1L;
	
	private int rareza;
	private boolean muerto = false;
	
	/**
	 * @param name
	 * @param hp
	 * @param attack
	 * @param defense
	 * @param rareza
	 */
	public Enemy(String name, int hp, int attack, int defense,int rareza) {
		super(name, hp, attack, defense);
	}

	/**
	 * daniado
	 *@param dmg int daño enemigo
	 *
	 *Se restará el daño de x fuente a la vida de este enemigo y si la vida llega a 0 morirá
	 */
	@Override
	public void daniado(int dmg) {

		this.setHp(this.getHp()-(dmg-this.getDefense()));

		if(this.getHp()<=0) {
			
			muerto = true;

		}
	}
	
	/**
	 * @return rareza
	 */
	public int getRareza() {
		return rareza;
	}

	/**
	 * @param rareza
	 */
	public void setRareza(int rareza) {
		this.rareza = rareza;
	}

	/**
	 * @return si está muerto
	 */
	public boolean isMuerto() {
		return muerto;
	}

	/**
	 * @param muerto
	 */
	public void setMuerto(boolean muerto) {
		this.muerto = muerto;
	}

	/**
	 * @return String accion específica del Enemigo.
	 */
	public abstract String accion();
	
	/**
	 * CompareTo
	 *@return int comparacion de la rareza de los enemigos.
	 */
	@Override
	public int compareTo(Enemy e) {
		
		return this.rareza - e.getRareza();
		
	}
	
}
