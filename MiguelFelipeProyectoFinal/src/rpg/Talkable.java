package rpg;

/**
 * @author Angel Miguel
 * @version 1.0.0
 * 
 * INTERFAZ HABLABLE
 * 
 * Determina si un personaje u objeto puede hablar e implementa el método para hablar
 * 
 */ 
public abstract interface Talkable {
	
	/**
	 * @param p Jugador al que se habla (para tomar los datos del player actualizados)
	 * @return String lo que le dirá al jugador
	 */
	public abstract String talk(Player p);

}
