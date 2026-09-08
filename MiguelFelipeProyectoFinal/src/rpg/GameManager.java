package rpg;

import java.util.Scanner;
/**
 * @version 1.0.0
 * 
 * CLASE GAME MANAGER
 * 
 * Es la clase más importante con diferencia de un juego.
 * Incorpora todos los métodos y menús para controlar el estado del juego de una manera ordenada mediante métodos.
 * 
 * En esta clase se agregan el jugador,la orda y el escaner que usaremos durante todas las partidas
 * Se crea el boolean que usaremos como llave para salir al siguiente menu.
 * 
 * @author Angel
 */
public class GameManager {

	private Player p1 = new Player("", 2000, 100, 50);
	private Wave w = new Wave(p1);
	private boolean exit = false;
	private Scanner s = new Scanner(System.in);
	

	/**
	 * Al crearse el game manager se lanza el inicio del juego
	 */
	public GameManager() {
		inicio();
	}

	/**
	 * @return the p1
	 */
	public Player getP1() {
		return p1;
	}

	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(Player p1) {
		this.p1 = p1;
	}



	/**
	 * Principio de una nueva partida (no sale al cargar partida) nombramos a nuestro personaje.
	 */
	public void startGame() {

		p1 = new Player(null, 2000, 100, 50);

		System.out.println("                                   BIENVENIDO A TEXTPG VIAJERO"
				+ "\n Cual es tu nombre?");

		p1.setName(s.next());

		System.out.println("Bienvenido "+ p1.getName() + " te encuentras en una cueva" );

		gameLoop();
	}
	/**
	 * Este es el menú de inicio del juego
	 * Elegimos si creamos una nueva partida o la cargamos o salimos.
	 */
	public void inicio(){

		exit = false;

		while (exit != true)
		{
			System.out.println("TEXTPG\n\n[1] Nueva Partida\n[2] Cargar Partida\n[3] Settings\n[4] Exit");
			String input = s.next();
			switch (input)
			{

			case "1":
				System.out.println("THE GAME IS ON");
				startGame();
				break;

			case "2":
				System.out.println("YOU LOADED GAME X");
				p1 = Serializador.deserializar();
				gameLoop();
				break;
			case "3":
				System.out.println("NO HAY OPCIONES );");
				break;
			case "4":
				System.out.println("ADIOS :)");
				exit = true;
				break;
			default:
				System.out.println("thats not a valid input");
				break;
			}
		}
	}
	/**
	 * Es el ciclo de juego principal.
	 * 1.Crea una nueva orda
	 * 2.Decidimos si combatimos la siguiente orda, hablamos o salimos y guardamos.
	 */
	public void gameLoop(){

		exit = false;


		while (exit != true)
		{
			if(w.getOrda().getLast().isMuerto()==true) {
				w = new Wave(p1);}

			System.out.println("\n\n[1] COMBATIR\n[2] HABLAR\n[3] SALIR Y GUARDAR\n");
			String input = s.next();
			switch (input)
			{

			case "1":
				combate();
				break;

			case "2":
				talk();
				break;
			case "3":
				Serializador.serializar(p1);
				exit = true;
				break;
			default:
				System.out.println("thats not a valid input");
				break;
			}
		}
	}
	/**
	 * Menu principal del combate diseñado para que las cajas se adapten al tamaño de las variables
	 */
	public void combate() {

		for(Enemy e : w.getOrda()) {

			while(e.isMuerto()!=true&&exit!=true) {

				System.out.println("                                                                        \n\n\n\n\n\n" +
						" ________________________¬                                        _________________________¬\n" +
						"| Player "+String.format("%-16.16s", p1.getName())+"|                                       | ENEMY "+String.format("%-16.16s", e.getName())+"  |\n" +
						"|                        |                                       |                         |\n" +
						"| HP: "+String.format("%-10.10s", p1.getHp())+"         |                                       | HP: "+String.format("%-16.16s", e.getHp())+"    |\n" +
						"| Level: "+String.format("%-16.16s", p1.getLvl())+"|                                       |                         |\n" +
						"|________________________|                                       |_________________________|\n" +
						"                                                                                            \n" +
						" ________________________¬                                                                  \n" +
						"|                        |                                                                  \n" +
						"| [1] Attack             |                                                                  \n" +
						"| [2] Heal               |          "+e.accion()+"                                                        \n" +
						"| [3] Defend             |                                                                  \n" +
						"| [4] Exit & Save        |                                                                  \n" +
						"|________________________|                                      \n\n\n\n");

				String input = s.next();
				switch (input)
				{
				case "1":
					e.daniado(p1.getAttack());
					p1.daniado(e.getAttack());
					if(e.isMuerto() == true) {
						p1.lvlUp(e.getRareza()*100);}
					
					try {
						gameOver();
					} catch (GameOverException e1) {
						e1.printStackTrace();
						System.exit(0);
					}
					break;
					
				case "2":
					p1.setHp(p1.getMaxHp());
					break;
				case "3":
					p1.setDefense(100);
					break;
				case "4":
					exit = true;
					Serializador.serializar(p1);
					inicio();
					break;
				default:
					System.out.println("not a valid input");
					break;
				}
			}

		}
	}
 	/**
 	 * Menu de datos de personajes (usa el NPC para ordenarlos)
 	 */
 	public void talk() {


		System.out.println(new NPC(null, 0, 0, 0).talk(p1));

	}
 	/**
 	 * @throws GameOverException
 	 * 
 	 * Determina cuando se ha ganado o perdido y termina el juego con una excepción.
 	 * Tambien elimina el archivo de datos guardados para obligar a crear una nueva partida.
 	 */
 	public void gameOver() throws GameOverException{
 		
 		if(p1.getLvl() >= 10) {
 			
 			Serializador.eliminarArchivo();
 			throw new GameOverException("HAS GANADO!!!");
 		}
 		else if(p1.getHp() <= 0) {
 			
 			Serializador.eliminarArchivo();
 			throw new GameOverException("HAS PERDIDO );");
 			
 		}
 		
 	}
}
