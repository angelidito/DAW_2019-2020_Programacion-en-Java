/**
 * 
 */
package angelidito.vistas.menus;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuAdministrarJugadores extends Menu {

	private final static String CABECERA = "Administración de jugadores\n\n  Escoja una opción:";
	private final static String[] OPCIONES = { "1 - Listar jugadores", "2 - Añadir nuevos jugadores",
			"3 - Retirar jugadores", "4 - Editar jugadores", "9 - Información detallada (Aviso: muy extensa)",
			"0 - Volver al menú principal" };
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 9, 0 };

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 */
	public MenuAdministrarJugadores() {
		this(CABECERA, OPCIONES);
	}

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 * @param numerosOpciones Vector que contiene los numeros que son aceptados.
	 */
	private MenuAdministrarJugadores(String cabecera, String[] opciones) {
		super(cabecera, opciones);
	}

}
