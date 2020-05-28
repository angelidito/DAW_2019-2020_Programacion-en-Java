/**
 * 
 */
package angelidito.vistas.menus;

/**
 * 
 * Menú de administración de jugadores.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuAdministrarJugadores extends Menu {

	private final static String CABECERA = "Administración de jugadores\n\n  Escoja una opción:";
	private final static String[] OPCIONES = { "1 - Listar jugadores", "2 - Añadir nuevos jugadores",
			"3 - Retirar jugadores", "4 - Editar jugadores", "9 - Información detallada (Aviso: muy extensa)",
			"0 - Volver al menú principal" };
	@SuppressWarnings("unused")
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 9, 0 };

	/**
	 * Crea la vista.
	 */
	public MenuAdministrarJugadores() {
		this(CABECERA, OPCIONES);
	}

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 */
	private MenuAdministrarJugadores(String cabecera, String[] opciones) {
		super(cabecera, opciones);
	}

}
