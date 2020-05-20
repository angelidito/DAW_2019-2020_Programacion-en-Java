/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.aux.Escaner;
import angelidito.laruleta.Jugador;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuAñadirJugador extends Menu {

	private final static String CABECERA = " Va a añadir un jugador\n\n  Escoja una opción:";
	private final static String[] OPCIONES = {
			"1 - Jugador estándar:\\n\\tnombre por defecto\\n\\tcrédito = 10\\n\\trondas máximas = 10",
			"Jugador estándar con credito personalizado", "3 - Jugador con parámetros personalizados",
			"0 - Volver atrás" };
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 0 };

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 */
	public MenuAñadirJugador() {
		this(CABECERA, OPCIONES);
	}

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 * @param numerosOpciones Vector que contiene los numeros que son aceptados.
	 */
	private MenuAñadirJugador(String cabecera, String[] opciones) {
		super(cabecera, opciones);
	}
	
	public int pedirCredito () {

		System.out.println("Indique el cédito del nuevo jugador:");
	
		return Escaner.entero(1, Jugador.MAX_INT_VALUE);
	}
	
	
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

		System.out.print("  ¿Desea añadirle ahora la apuesta? [s/n");
		// TODO quizá mas adelante ofrecer copiar la apuesta a otro jugador.
		if (Escaner.yesNoQuestion()) {
			MenuEditarApuesta edicionApuesta = new MenuEditarApuesta(jugador);
			
			edicionApuesta.editarApuesta();
		}

		return jugador;
	}

}
