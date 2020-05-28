/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.laruleta.Jugador;
import angelidito.laruleta.ListaJugadores;
import angelidito.vistas.Escaner;

/**
 * Menú con las opciones para editar a un jugador. Permite la edición de los
 * jugadores en mesa. Permite editar nombres, crédito, crédito objetivo, rondas
 * máximas y apuestas.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuEditarJugadores extends Menu {

	private final static String CABECERA = "Retirada de jugadores\n  Seleccione un jugador:";

	/**
	 * Lista de jugadores que editar.
	 */
	private ListaJugadores listaJugadores;

	/**
	 * Crea la vista.
	 * 
	 * @param listaJugadores Lista de jugadores que editar.
	 */
	public MenuEditarJugadores(ListaJugadores listaJugadores) {
		this(CABECERA, listaJugadores.listadoJugadoresEnMesa(), listaJugadores);

	}

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera       Cabecera del menú.
	 * @param opciones       Vector con las opciones del menú.
	 * @param listaJugadores Lista de jugadores que editar.
	 */
	private MenuEditarJugadores(String cabecera, String[] opciones, ListaJugadores listaJugadores) {
		super(cabecera, opciones);
		this.listaJugadores = listaJugadores;
	}

	/**
	 * Permite seleccionar y editar a un jugador.
	 */
	public void editarJugador() {
		int opcion;
		boolean retirar;
		Jugador jugadorParaEditar = selecionarJugador();

		do {

			System.out.printf("Ha slecionado a %s%n%n", jugadorParaEditar.info());

			System.out.println("  Escoja una opción:");
			System.out.println("1 - Cambiar nombre");
			System.out.println("2 - Variar crédito");
			System.out.println("3 - Cambiar crédito objetivo");
			System.out.println("4 - Cambiar rondas máximas");
			System.out.println("5 - Cambiar apuesta");
			System.out.println("0 - Guardar cambios");
			System.out.println("");

			opcion = Escaner.entero(0, 5);

			System.out.println();

			retirar = editarJugador(opcion, jugadorParaEditar);

			System.out.println();

		} while (opcion != 0);

		if (retirar) {
			String mensajeRetirada;
			mensajeRetirada = listaJugadores.retirarJugador(jugadorParaEditar);
			System.out.println(mensajeRetirada);
		}

	}

	/**
	 * Edita el jugador según la opcion introducida por teclado. Opciones: 1,
	 * nombre; 2, crédito; 3, crédito objetivo;, 4, rondas máximas; 5, apuesta; 0,
	 * nada.
	 * 
	 * @param opcion            1, para cambiar el nombre; 2, para variar el
	 *                          crédito; 3, para cambiar el crédito objetivo; 4,
	 *                          para cambiar el máximo número de rondas; 5, para
	 *                          editar la apuesta; 0, no hace nada; en cualquier
	 *                          otro caso, informa que la opción es incorrecta.
	 * @param jugadorParaEditar Jugador a editar.
	 * @return {@code true} si el jugador ha alcanzado el crédito objetivo o las
	 *         rondas máximas, o si no tiene crédito; de otra manera, {@code false}
	 */
	private boolean editarJugador(int opcion, Jugador jugadorParaEditar) {

		boolean retirar = false;

		switch (opcion) {

		case 1:
			System.out.println("Introduzca el nuevo nombre.");

			jugadorParaEditar.setNombre(Escaner.texto());

			break;

		case 2:
			System.out.println("Inserte la cantidad a sumar (restará si es negativa).");

			jugadorParaEditar.variarCredito(Escaner.entero(-1 * Jugador.MAX_INT_VALUE + jugadorParaEditar.getCredito(),
					Jugador.MAX_INT_VALUE - jugadorParaEditar.getCredito()));

			if (jugadorParaEditar.getCredito() < 1) {
				retirar = true;
				System.err.println("Sin credito. El jugador se retirará tras la edición");
			}

			break;

		case 3:
			System.out.println("Inserte el nuevo crédito objetivo.");
			System.out.printf("El actual es: %d.%n", jugadorParaEditar.getCreditoObjetivo());

			int nuevoCreditoObjetivo = Escaner.entero(0, Jugador.MAX_INT_VALUE);
			jugadorParaEditar.setCreditoObjetivo(nuevoCreditoObjetivo);

			if (jugadorParaEditar.getCreditoObjetivo() <= jugadorParaEditar.getCredito()) {
				retirar = true;
				System.err.println("Crédito objetivo superado. El jugador se retirará tras la edición");
			}

			break;

		case 4:
			System.out.println("Inserte el nuevo máximo de rondas.");
			System.out.printf("El actual es: %d.%n", jugadorParaEditar.getRondasMaximas());

			int nuevasRondasMaximas = Escaner.entero(1, Jugador.MAX_INT_VALUE);
			jugadorParaEditar.setCreditoObjetivo(nuevasRondasMaximas);

			if (jugadorParaEditar.getRondasMaximas() <= jugadorParaEditar.getRondas()) {
				retirar = true;
				System.err.println("Rondas máximas superadas. El jugador se retirará tras la edición");
			}

			break;

		case 5:

			int op;
			boolean opcionIncorrecta = false;
			int[] opciones = MenuEditarApuesta.getNumOpciones();
			do {

				MenuEditarApuesta edicionApuesta = new MenuEditarApuesta(jugadorParaEditar);

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

			break;

		case 0:
			// NO TIENE QUE HACER NADA.
			// Solo salir.
			break;

		}
		return retirar;
	}

	/**
	 * Pide selecionar por teclado un jugador de la lista de jugadores en mesa.
	 * 
	 * @return El Jugador selecionado.
	 */
	private Jugador selecionarJugador() {

		Jugador jugadorSelecionado = null;

		do {

			int numeroJugador = Escaner.entero(1, super.getOpciones().length) - 1;

			jugadorSelecionado = listaJugadores.getJugadoresEnMesa().get(numeroJugador);

		} while (jugadorSelecionado == null);

		return jugadorSelecionado;
	}

}
