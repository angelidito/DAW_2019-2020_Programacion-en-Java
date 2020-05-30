/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.laruleta.Jugador;
import angelidito.laruleta.ListaJugadores;
import angelidito.vistas.Escaner;

/**
 * Menú con las opciones para retirar a un jugador.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuRetirarJugadores extends Menu {

	private final static String CABECERA = "Retirada de jugadores\n  Seleccione un jugador:";

	/**
	 * Lista de jugadores de la que retirar jugadores.
	 */
	private ListaJugadores listaJugadores;

	/**
	 * Crea la vista.
	 * 
	 * @param listaJugadores Lista de jugadores de la que retirar jugadores.
	 */
	public MenuRetirarJugadores(ListaJugadores listaJugadores) {
		this(CABECERA, listaJugadores.listadoJugadoresEnMesa(), listaJugadores);

	}

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera       Cabecera del menú.
	 * @param opciones       Vector con las opciones del menú.
	 * @param listaJugadores Lista de jugadores de la que retirar jugadores.
	 */
	private MenuRetirarJugadores(String cabecera, String[] opciones, ListaJugadores listaJugadores) {
		super(cabecera, opciones);
		this.listaJugadores = listaJugadores;
	}

	/**
	 * Permite seleccionar y retirar a un jugador.
	 */
	public void retirarJugador() {

		Jugador jugadorParaRetirar = selecionarJugador();

		System.out.printf("Va a retirar al siguiente jugador: %s%n", jugadorParaRetirar.info());
		System.out.println("Esta acción no se puede deshacer. ¿Está seguro? [s/n]");

		if (Escaner.yesNoQuestion()) {
			String mensajeRetirada = listaJugadores.retirarJugador(jugadorParaRetirar);
			println();
			System.out.println(mensajeRetirada);
		} else {
			println();
			System.out.println("El jugador no ha sido retirado");
		}

		println();

	};

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
