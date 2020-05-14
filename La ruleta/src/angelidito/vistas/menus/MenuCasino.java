/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.escaner.Escaner;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuCasino extends Menu {

	private final static String cabecera = "¡ESTO ES LA RULETA!\n\n  Escoja una opción:";
	private final static String[] opciones = { "1 - ¡Juguemos!", "2 - Lista de jugadores", "3 - Estadísticas",
			"8 - Borrar datos", "9 - Guardar datos", "0 - Guardar y salir" };
	private final static int[] numerosOpciones = { 1, 2, 3, 8, 9, 0 };

	public MenuCasino() {
		this(cabecera, opciones, numerosOpciones);
	}

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 * @param numerosOpciones Vector que contiene los numeros que son aceptados.
	 */
	private MenuCasino(String cabecera, String[] opciones, int[] numerosOpciones) {
		super(cabecera, opciones, numerosOpciones);
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

		System.out.println("");
		System.out.println("");

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
