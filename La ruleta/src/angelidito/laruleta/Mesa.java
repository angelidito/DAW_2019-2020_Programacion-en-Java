package angelidito.laruleta;

import angelidito.escaner.Escaner;
import angelidito.laruleta.gestion.GestionCSV;

/**
 * 
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class Mesa {

	public static void main(String[] args) {

		ListaJugadores listaJugadores = new ListaJugadores();

		System.out.println("Bienvenido al casino");

		System.out.println("");
		int opcion;
		do {

			mostrarMenu();

			opcion = Escaner.entero();

			System.out.println();

			switch (opcion) {

			case 1:
				jugar();
				break;

			case 2:
				// Lista de jugadores
				listaJugadores.menu();
				break;

			case 3:
				mostrarEstadisticas();
				break;

			case 9:
				borrarDatos();
				break;

			case 0:
				// Salir y guardar
				break;

			default:
				Escaner.avisoOpcionIncorrecta();
			}

		} while (opcion != 0);

		guardarDatos(listaJugadores);
	}

	/**
	 * Muestra el menú. Con las opciones disponibles.
	 * 
	 */
	private static void mostrarMenu() {

		System.out.println("¡ESTO ES LA RULETA!");
//		System.out.println("");
		System.out.println("  Escoja una opción:");
		System.out.println("1 - ¡Juguemos!");
		System.out.println("2 - Lista de jugadores");
		System.out.println("3 - Estadísticas");
		System.out.println("9 - Borrar datos");
		System.out.println("0 - Guardar y salir.");
		System.out.println("");

	}

	private static void jugar() {
		// TODO Auto-generated method stub
		System.out.println("Mesa.Jugar()");
		System.out.println("No implementado todavia");
		System.out.println("No implementado todavia");
		System.out.println("No implementado todavia");
		System.out.println("No implementado todavia");
		System.out.println("");
		System.out.println("");
	}

	/**
	 * Muestra las estadísticas del historial de ocurrencias.
	 */
	private static void mostrarEstadisticas() {
		// TODO
		

		System.out.println(Ruleta.estadisticas());

		System.out.println("");

		System.out.println("");

	}

	private static void borrarDatos() {

		System.out.println("Está a punto de borrar los archivos del programa.");
		System.out.print("Esta acción no puede deshacerse. ¿Esta seguro?");
		if (Escaner.yesNoQuestionRecursivo()) {

			GestionCSV.borrarDatos();
			System.out.println("Datos borrados.");

		} else
			System.out.println("Borrado no realizado.");

	}

	/**
	 * Guarda los datos del programa. Tanto las listas de jugadores, como el
	 * histórico de la ruleta.
	 * 
	 * @param jugadores Lista de jugadores a guardar.
	 */
	private static void guardarDatos(ListaJugadores jugadores) {

		System.out.println("Guardando jugadores:");
		jugadores.guardarJugadores();
		System.out.println(" ¡Jugadores guardados!");

		System.out.println("");
		System.out.println("");

		System.out.println("Guardando estadisticas:");
		Ruleta.guardarHisorico();
		System.out.println(" ¡Estadisticas guardadas!");

		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Programa finalizado.");
	}

}
