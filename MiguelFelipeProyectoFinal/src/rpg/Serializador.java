/**
 * 
 */
package rpg;

/**
 * @author Angel Miguel
 * @version 1.0.0
 * 
 * CLASE SERIALIZADOR
 * 
 * Clase que alberga todos los métodos relacionados con guardar o cargar datos en archivos.
 * Incluye el String archivo.
 * 
 */ 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializador {

	private static String archivo = "datos.dat";



	/**
	 * Serializar -> Método para guardar la partida
	 * @param c el Jugador que se quiere guardar
	 */
	public static void serializar(Player c) {

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
			out.writeObject(c);
		} 
		catch (IOException e) {
			e.printStackTrace(); 
		}
	}


	/**
	 * Deserializar -> Método que carga la partida guardada
	 * @return Player el jugador guardado en el archivo.
	 */
	public static Player deserializar() {

		File f = new File(archivo);

		if (!f.exists() || f.length() == 0) {
			System.out.println("El archivo no existe o está vacío.");
			return null; 
		}

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(archivo))) {
			return (Player) in.readObject();
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Método para eliminar el archivo creado.
	 */
	public static void eliminarArchivo() {
		File f = new File(archivo);
		if (f.exists()) {
			f.delete();
		}
	}

}