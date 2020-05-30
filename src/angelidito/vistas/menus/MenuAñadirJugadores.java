/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.laruleta.Jugador;
import angelidito.vistas.Escaner;

/**
 * Menú para añadir jugadores.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuAñadirJugadores extends Menu {

	private final static String CABECERA = "Añadido de  jugadores\n\n  Escoja una opción:";
	private final static String[] OPCIONES = {
			"1 - Jugador estándar:\n\tnombre por defecto\n\tcrédito = 10\n\trondas máximas = 10",
			"2 - Jugador estándar con credito personalizado", "3 - Jugador con parámetros personalizados",
			"0 - Volver atrás" };
	@SuppressWarnings("unused")
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 0 };

	/**
	 * Crea la vista.
	 */
	public MenuAñadirJugadores() {
		this(CABECERA, OPCIONES);
	}

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera Cabecera del menú.
	 * @param opciones Vector con las opciones del menú.
	 */
	private MenuAñadirJugadores(String cabecera, String[] opciones) {
		super(cabecera, opciones);
	}

	/**
	 * Pide el crédito por teclado.
	 * 
	 * @return el crédito introducido.
	 */
	public int pedirCredito() {

		System.out.println("Indique el cédito del nuevo jugador:");

		return Escaner.entero(1, Jugador.MAX_INT_VALUE);
	}

	/**
	 * Permite crear un jugador con los parametros desados. Pide por teclado los
	 * valores necesarios. Devuelve el jugador creado.
	 * 
	 * @return El jugador resultante.
	 */
	public Jugador crearJugadorConParametrosPorTeclado() {

		String nombre;
		int credito;
		int creditoObjetivo;
		int rondasMaximas;

		System.out.println("  Introduzca el nombre del jugador");
		nombre = Escaner.texto();

		System.out.println("  Introduzca el crédito:");
		credito = Escaner.entero(1, Jugador.MAX_INT_VALUE);

		System.out.println("  Introduzca el crédito objetivo:");
		creditoObjetivo = Escaner.entero(credito + 1, Jugador.MAX_INT_VALUE);

		System.out.println("  Introduzca el las rondas maximas:");
		rondasMaximas = Escaner.entero(1, Jugador.MAX_INT_VALUE);

		// Creamos el jugador con la apuesta a 0
		Jugador jugador = new Jugador(nombre, credito, creditoObjetivo, rondasMaximas);

		System.out.print("  ¿Desea añadirle ahora la apuesta? [s/n]");

		// TODO quizá mas adelante ofrecer copiar la apuesta a otro jugador.
		if (Escaner.yesNoQuestion()) {

			int op;
			boolean opcionIncorrecta = false;
			int[] opciones = MenuEditarApuesta.getNumOpciones();
			do {

				MenuEditarApuesta edicionApuesta = new MenuEditarApuesta(jugador);

				edicionApuesta.printMenu(opcionIncorrecta);

				op = Escaner.entero();

				opcionIncorrecta = true;

				for (int i = 0; opcionIncorrecta && i < opciones.length; i++) {
					if (opciones[i] == op)
						opcionIncorrecta = false;
				}

				if (!opcionIncorrecta)
					edicionApuesta.editarApuesta(op);

			} while (op != 0);

		}

		return jugador;
	}

}
