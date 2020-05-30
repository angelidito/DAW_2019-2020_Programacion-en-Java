package angelidito.vistas.listados;

import angelidito.laruleta.Jugador;
import angelidito.laruleta.ListaJugadores;

/**
 * Lista la información de los jugadores.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class ListadoJugadores extends Listado {

	private ListaJugadores listaJugadores;

	/**
	 * Lista los jugadores.
	 * 
	 * @param listaJugadores Donde están las listas de jugadores.
	 */
	public ListadoJugadores(ListaJugadores listaJugadores) {

		this.listaJugadores = listaJugadores;

	}

	/**
	 * Lista la información abreviada e los jugadores.
	 */
	public void infoJugadores() {
		this.infoJugadoresEnMesa();

		System.out.println("");

		this.infoJugadoresRetirados();

		System.out.println("");
		System.out.println("");

		waitForEnter();

		println();
	}

	/**
	 * Lista la información abreviada e los jugadores en mesa.
	 */
	private void infoJugadoresEnMesa() {
		System.out.println("Jugadores en mesa:");

		if (listaJugadores.getJugadoresEnMesa().size() == 0)
			System.out.println("\tNingun jugador");
		else
			for (Jugador j : listaJugadores.getJugadoresEnMesa())
				System.out.println("\t" + j.info());
	}

	 /** 
	 * Lista la información abreviada e los jugadores retirados.
	 */
	private void infoJugadoresRetirados() {
		System.out.println("Jugadores retirados:");

		if (listaJugadores.getJugadoresRetirados().size() == 0)
			System.out.println("\tNingun jugador");
		else
			for (Jugador j : listaJugadores.getJugadoresRetirados())
				System.out.println("\t" + j.info());
	}

	/**
	 * 
	 * Lista la información extensa de los jugadores en mesa.
	 */
	public void informacionJugadores() {
		this.informacionJugadoresEnMesa();

		System.out.println("");

		this.informacionJugadoresRetirados();

		System.out.println("");
		System.out.println("");

		waitForEnter();

		println();
	}

	/**
	 * Lista la información extensa e los jugadores en mesa.
	 */
	private void informacionJugadoresEnMesa() {
		System.out.println("Jugadores en mesa:");

		if (listaJugadores.getJugadoresEnMesa().size() == 0)
			System.out.println("\tNingun jugador");
		else
			for (Jugador j : listaJugadores.getJugadoresEnMesa())
				System.out.println(j.informacion());
	}

	/**
	 * Lista la información extensa e los jugadores retirados.
	 */
	private void informacionJugadoresRetirados() {
		System.out.println("Jugadores retirados:");

		if (listaJugadores.getJugadoresRetirados().size() == 0)
			System.out.println("\tNingun jugador");
		else
			for (Jugador j : listaJugadores.getJugadoresRetirados())
				System.out.println(j.informacion());
	}

}