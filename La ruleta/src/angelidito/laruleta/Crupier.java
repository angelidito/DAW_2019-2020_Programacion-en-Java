package angelidito.laruleta;

/**
 * Es la clase encargada de manejar la ruleta, además de cobrar y pagar a los jugadores segun lo que salga enn la ruleta.
 * 
 * @author <a href="twitter.com/angelidito">Ángel M. D.</a>
 */
public class Crupier {

	/**
	 * Lista de jugadores que maneja el Crupier
	 */
	private ListaJugadores jugadores;

	/**
	 * Identificador del Crupier
	 */
	private int id;
	private static int numCrupiers;

	static {
		numCrupiers = 0;
	}

	/**
	 * Crea el crupier que manejará una lista de jugadores.
	 * 
	 * @param jugadores Lista de de jugadores.
	 */
	public Crupier(ListaJugadores jugadores) {
		super();
		this.jugadores = jugadores;
		this.id = Crupier.numCrupiers;
		Crupier.numCrupiers++;
	}

	/**
	 * @return El id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the jugadores
	 */
	public ListaJugadores getJugadores() {
		return jugadores;
	}

}
