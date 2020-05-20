package angelidito.vistas.listados;

import java.util.ArrayList;

import angelidito.laruleta.Jugador;
import angelidito.laruleta.ListaJugadores;

public class ListadoJugadores extends Listado {

	private ListaJugadores listaJugadores;

	/**
	 * Lista los jugadores.
	 */
	public ListadoJugadores(ListaJugadores listaJugadores) {

		this.listaJugadores = listaJugadores;

	}

	/**
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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