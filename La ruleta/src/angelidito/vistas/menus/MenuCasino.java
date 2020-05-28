/**
 * 
 */
package angelidito.vistas.menus;

/**
 * Menú principal del programa.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuCasino extends Menu {

	private final static String CABECERA = "¡ESTO ES LA RULETA!\n\n  Escoja una opción:";
	private final static String[] OPCIONES = { "1 - ¡Juguemos!", "2 - Administrar jugadores", "3 - Estadísticas",
			"8 - Borrar datos", "9 - Guardar datos", "0 - Guardar y salir" };
	@SuppressWarnings("unused")
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 8, 9, 0 };

	/**
	 * Crea la vista.
	 */
	public MenuCasino() {
		this(CABECERA, OPCIONES);
	}

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera Cabecera del menú.
	 * @param opciones Vector con las opciones imprimir.
	 */
	private MenuCasino(String cabecera, String[] opciones) {
		super(cabecera, opciones);
	}

	/**
	 * Imprime un mensaje informativo.
	 */
	public void guardandoJugadores() {

		System.out.println("Guardando jugadores:");
	}

	/**
	 * Imprime un mensaje informativo.
	 */
	public void jugadoresGuardadosGuardandoEstadisticas() {

		System.out.println(" ¡Jugadores guardados!");
		System.out.println("");
		System.out.println("");
		System.out.println("Guardando estadisticas:");

	}

	/**
	 * Imprime un mensaje informativo.
	 */
	public void estadisticaGuardadas() {
		System.out.println(" ¡Estadísticas guardadas!");

		System.out.println("");
	}

	/**
	 * Imprime un mensaje informativo.
	 */
	public void fin() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Programa finalizado.");
	}

	/**
	 * Imprime un mensaje de bienvenida.
	 */
	public static void bienvenida() {
		System.out.println("Bienvenido al casino Ruleta Afortunada");
		System.out.println("");
	}
}
