package angelidito.laruleta;

import java.util.ArrayList;

/**
 * Contiene, recupera y administra a los jugadores.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class ListaJugadores {

	/**
	 * Lista de jugadores en activo.
	 */
	private ArrayList<Jugador> jugadoresEnMesa;

	/**
	 * Lista de jugadores retirados.
	 */
	private static ArrayList<Jugador> jugadoresRetirados;

	/**
	 * Identificador del objeto y los CSV que crea. El propósito se este parámetro
	 * es evitar que se sobre escriban los ficheros en caso de crear más de un
	 * objeto de esta clase, ya sea por error o por futuras versiones del programa.
	 */
	private int id;

	private static int numListasJugadores;

	static {
		numListasJugadores = -1;
		jugadoresRetirados = null;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return jugadoresEnMesa
	 */
	public ArrayList<Jugador> getJugadoresEnMesa() {
		return jugadoresEnMesa;
	}

	/**
	 * @return jugadoresRetirados
	 */
	public ArrayList<Jugador> getJugadoresRetirados() {
		return jugadoresRetirados;
	}

	/**
	 * Recupera los datos de jugadoresEnMesa y jugadoresRetirados. En caso de no
	 * existir jugadoresEnMesa.csv y jugadoresRetirados.csv, informa de no
	 * encontrarlos.
	 */
	public ListaJugadores() {

		ListaJugadores.numListasJugadores++;

		this.id = ListaJugadores.numListasJugadores;

		this.jugadoresEnMesa = new ArrayList<Jugador>();
		this.cargarJugadoresEnMesa();

		if (ListaJugadores.jugadoresRetirados == null) {
			ListaJugadores.jugadoresRetirados = new ArrayList<Jugador>();
			this.cargarJugadoresRetirados();
		}

	}

	/**
	 * Carga los jugadores del fichero jugadoresEnMesa.csv. Los guarda en los
	 * jugadoresEnMesa. En caso de no existir jugadoresEnMesa.csv, informa de no
	 * encontrarlo.
	 */
	private void cargarJugadoresEnMesa() {

		String nombreFichero = String.format("jugadoresEnMesa%d.csv", id);

		this.jugadoresEnMesa = GestionCSV.obtenerJugadores(nombreFichero);

	}

	/**
	 * Carga los jugadores del fichero jugadoresEnMesa.csv. Los guarda en los
	 * jugadoresEnMesa. En caso de no existir y jugadoresRetirados.csv, informa de
	 * no encontrarlo.
	 */
	private void cargarJugadoresRetirados() {

		String nombreFichero = "jugadoresRetirados.csv";

		ListaJugadores.jugadoresRetirados = GestionCSV.obtenerJugadores(nombreFichero);

	}

	/**
	 * Guarda los jugadores en los CSV. Para cada ArrayList guarda los jugadores en
	 * un CSV.
	 */
	public void guardarJugadores() {

		String ficheroEnMesa = String.format("jugadoresEnMesa%d.csv", id);
		String ficheroRetirados = "jugadoresRetirados.csv";

		GestionCSV.escribirCSV(ficheroEnMesa, jugadoresEnMesa);
		GestionCSV.escribirCSV(ficheroRetirados, jugadoresRetirados);

	}

	/**
	 * Comprueba si hay que retirar al jugador. Esto es cuando no le queda crédito,
	 * ha alcanzado el credito objetivo o ha alcanzado las rondas máximas.
	 * 
	 * @param jugadorParaComprobar jugador qu ehay que comprobar la retirada
	 * @return {@code true} si hay que retirarlo; {@code false} si no.
	 */
	public boolean comprobarRetidadaJugador(Jugador jugadorParaComprobar) {
		boolean hayQue = false;

		if (jugadorParaComprobar.getCredito() < 1
				|| jugadorParaComprobar.getCreditoObjetivo() <= jugadorParaComprobar.getCredito()
				|| jugadorParaComprobar.getRondas() >= jugadorParaComprobar.getRondasMaximas())
			hayQue = true;

		return hayQue;
	}

	/**
	 * Retira a un jugador. Lo quita de jugadores en mesa y lo pasa a jugadores
	 * retirados.
	 * 
	 * @param jugadorParaRetirar Jugador a retirar.
	 * @return Mensaje de retirada o no del jugador.
	 */
	public String retirarJugador(Jugador jugadorParaRetirar) {

		boolean retirado = false;

		for (int i = 0; !retirado && i < this.jugadoresEnMesa.size(); i++) {
			if (this.jugadoresEnMesa.get(i).getId() == jugadorParaRetirar.getId()) {

				ListaJugadores.jugadoresRetirados.add(jugadorParaRetirar);

				this.jugadoresEnMesa.remove(i);
				i--;
				retirado = true;

			}
		}

		String mensajeRetirada;
		if (retirado) {
			mensajeRetirada = "%s ha sido retirado.%s";
			if (jugadorParaRetirar.getCredito() < 0)
				EasterEgg.huevoDePascua(jugadorParaRetirar.getNombre());

			else {

				if (jugadorParaRetirar.getCredito() == 0)
					mensajeRetirada = String.format(mensajeRetirada, jugadorParaRetirar.getNombre(),
							" Se le ha agotado el crédito.");

				else if (jugadorParaRetirar.getCredito() >= jugadorParaRetirar.getCreditoObjetivo())
					mensajeRetirada = String.format(mensajeRetirada, jugadorParaRetirar.getNombre(),
							" Ha alcanzado su crédito objetivo");

				else if (jugadorParaRetirar.getRondas() >= jugadorParaRetirar.getRondasMaximas())
					mensajeRetirada = String.format(mensajeRetirada, jugadorParaRetirar.getNombre(),
							" Ha alcanzado sus rondas máximas");

				else
					mensajeRetirada = String.format(mensajeRetirada, jugadorParaRetirar.getNombre(), "");

			}

		} else {
			mensajeRetirada = "No se ha podido retirar al jugador.";
		}

		return mensajeRetirada;

	}

	/**
	 * Devuelve un vector con la lista de jugadores en mesa
	 * 
	 * @return Vector con la lista numerada de jugadores.
	 */
	public String[] listadoJugadoresEnMesa() {

		String[] listado = new String[this.jugadoresEnMesa.size()];

		for (int i = 0; i < this.jugadoresEnMesa.size(); i++)
			listado[i] = String.format("%d - %s", (i + 1), this.jugadoresEnMesa.get(i).info());

		return listado;
	}

	/**
	 * Borra el contenido de las listas de jugadores.
	 */
	public void borrarListas() {

		this.jugadoresEnMesa = new ArrayList<Jugador>();
		ListaJugadores.jugadoresRetirados = new ArrayList<Jugador>();

	}

}
