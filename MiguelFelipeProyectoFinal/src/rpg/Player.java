/**
 * 
 */
package rpg;

import java.io.Serializable;

/**
 * @author Angel Miguel Felipe
 * @version 1.0.0
 * 
 * CLASE PLAYER
 * 
 * Es la clase hija de Character y representará a nuestro Jugador plantilla.
 * 
 * incorpora Nivel int que usaremos para mejorar el resto de estadísticas de nuestro jugador
 * incorpora Experiencia int para determinar si subimos de nivel
 * incorpora SaludMaxima int que utilizaremos como variable resguardo para determinar a cuanto se puede establecer la vida actual.
 * 
 */
public class Player extends Character implements Daniable,Serializable {

	private static final long serialVersionUID = 1L;
	private int lvl;
	private int exp;
	private int maxHp;

	/**
	 * @param name
	 * @param hp
	 * @param attack
	 * @param defense
	 */
	public Player(String name, int hp, int attack, int defense) {
		super(name, hp, attack, defense);
		this.setLvl(1);
		this.setExp(0);
		this.setMaxHp(hp);
	}





	/**
	 * @return lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 */
	public void setLvl(int lvl) {
		if (lvl>0) {
		this.lvl = lvl;}
	}

	/**
	 * @return exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}
	/**
	 * @return the maxHp
	 */
	public int getMaxHp() {
		return maxHp;
	}
	/**
	 * @param maxHp the maxHp to set
	 */
	public void setMaxHp(int maxHp) {
		if(maxHp>0) {
		this.maxHp = maxHp;}
	}





	/**
	 * daniado
	 *
	 *Determinará cuanto vida nos quitan los enemigos y si acabamos con salud negativa la establecerá en 0
	 */
	@Override
	public void daniado(int dmg) {

		if(dmg-this.getDefense()<=0){
		}
		else {

			this.setHp(this.getHp()-(dmg-this.getDefense()));

			if(this.getHp()<=0) {

				this.setHp(0);
			}
		}

	}

	/**
	 * @param exp recibida por el enemigo
	 * 
	 * Este método determinará si subimos de nivel o no al recibir experiencia
	 * Mejorará las estadisticas base si subimos de nivel.
	 */
	public void lvlUp(int exp) 
	{
		this.exp += exp;
		while(this.exp>=(this.lvl*100)) {

			this.exp -= this.lvl*100;
			this.lvl++;
			this.setAttack(this.getAttack()+50);
			this.setDefense(this.getDefense()+10);
			this.setMaxHp(this.getMaxHp()+250);
			this.setHp(this.getMaxHp());

		}

	}

}
