/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.aux.Escaner;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuCasino extends Menu {

	private final static String CABECERA = "¡ESTO ES LA RULETA!\n\n  Escoja una opción:";
	private final static String[] OPCIONES = { "1 - ¡Juguemos!", "2 - Administrar jugadores", "3 - Estadísticas",
			"8 - Borrar datos", "9 - Guardar datos", "0 - Guardar y salir" };
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 8, 9, 0 };

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 */
	public MenuCasino() {
		this(CABECERA, OPCIONES);
	}

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 * @param numerosOpciones Vector que contiene los numeros que son aceptados.
	 */
	private MenuCasino(String cabecera, String[] opciones) {
		super(cabecera, opciones);
	}

	public boolean borrarDatos() {

		System.out.println("Está a punto de borrar los archivos del programa.");
		System.out.print("Esta acción no puede deshacerse. ¿Esta seguro?");
		return Escaner.yesNoQuestionRecursivo();

	}

	public void guardandoJugadores() {

		System.out.println("Guardando jugadores:");
	}

	public void jugadoresGuardadosGuardandoEstadisticas() {

		System.out.println(" ¡Jugadores guardados!");
		System.out.println("");
		System.out.println("");
		System.out.println("Guardando estadisticas:");

	}

	public void estadisticaGuardadas() {
		System.out.println(" ¡Estadísticas guardadas!");
	}

	public void datosBorrados(boolean borrado) {
		if (borrado) {
			System.out.println("Datos borrados.");

		} else
			System.out.println("Borrado no realizado.");

		println();

	}

	public void fin() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Programa finalizado.");
	}

	public static void bienvenida() {
		System.out.println("Bienvenido al casino Ruleta Afortunada");
		System.out.println("");
	}
}
