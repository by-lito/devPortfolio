/**
 * 
 */
package rpg;

/**
 * @author Angel Miguel Felipe
 * @version 1.0.0
 * 
 * CLASE GameOverException
 * 
 * Esta clase hija de Exception incorpora un metodo para lanzar mensajes en el caso de que el juego deba terminar.
 * 
 */
public class GameOverException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Excepcion que determinará si se ha terminado el juego
	 */
	public GameOverException(String mensaje) {
		super("\n\n\n\n                      GAME OVER "+mensaje+"                         \n\n");
	}
	
	 

}
