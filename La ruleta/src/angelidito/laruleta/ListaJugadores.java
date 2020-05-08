/**
 * 
 */
package angelidito.laruleta;

import java.util.ArrayList;

import angelidito.escaner.Escaner;
import angelidito.laruleta.gestion.GestionCSV;

/**
 * Contiene y recupera los jugadores.
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
	 * Menu de jugadores. Permite listar, añadir, retirar y editar jugadores.
	 * 
	 */
	public void menu() {

		int opcion = 0;
		do {

			System.out.println("Lista de jugadores");
			System.out.println();
			System.out.println("  Escoja una opción:");
			System.out.println("1 - Listar jugadores");
			System.out.println("2 - Añadir nuevos jugadores");
			System.out.println("3 - Retirar jugadores");
			System.out.println("4 - Editar jugadores");
			System.out.println("0 - Volver atrás");
			System.out.println("");

			opcion = Escaner.entero();

			System.out.println();

			switch (opcion) {
			case 1:
				this.menuListarJugadores();
				break;

			case 2:
				this.menuAñadirJugador();
				break;

			case 3:
				this.menuRetirarJugador();
				break;

			case 4:
				this.menuEditarJugador();
				break;

			case 0:
				// NO TIENE QUE HACER NADA
				break;

			default:
				Escaner.avisoOpcionIncorrecta();
			}

		} while (opcion != 0);
	}

	/**
	 * Lista los jugadores. NO ES UN MENU. NO da a escojer entre jugadores en mesa y
	 * jugadores retirados, pero podría ser una opción para más adelante.
	 * 
	 */
	private void menuListarJugadores() {

		listarJugadoresEnMesa();

		System.out.println("");

		listarJugadoresRetirados();

		System.out.println("");
		System.out.println("");

	}

	/**
	 * 
	 */
	private void listarJugadoresEnMesa() {
		System.out.println("Jugadores en mesa:");

		if (this.jugadoresEnMesa.size() == 0)
			System.out.println("\tNingun jugador");
		else
			for (Jugador j : this.jugadoresEnMesa)
				System.out.println("\t" + j.info());
	}

	/**
	 * 
	 */
	private void listarJugadoresRetirados() {
		System.out.println("Jugadores retirados:");

		if (ListaJugadores.jugadoresRetirados.size() == 0)
			System.out.println("\tNingun jugador");
		else
			for (Jugador j : ListaJugadores.jugadoresRetirados)
				System.out.println("\t" + j.info());
	}

	/**
	 * Permite añadir un jugador. Estandar o personalizado.
	 */
	private void menuAñadirJugador() {

		int opcion;
		do {

			System.out.println(" ¿Cómo desea añadir el jugador?");
			System.out.println("  Escoja una opción:");
			System.out.println("1 - Jugador estándar:\n\tnombre por defecto\n\tcrédito = 10\n\trondas máximas = 10");
			System.out.println("2 - Jugador estándar con credito personalizado");
			System.out.println("3 - Jugador con parámetros personalizados");
			System.out.println("0 - Volver atrás.");
			System.out.println("");

			opcion = Escaner.entero();

			System.out.println();

			switch (opcion) {

			case 1:
				this.jugadoresEnMesa.add(new Jugador());
				break;

			case 2:
				System.out.println("Indique el cédito del nuevo jugador:");
				int credito = Escaner.entero(1, Jugador.MAX_INT_VALUE);
				this.jugadoresEnMesa.add(new Jugador(credito));
				break;

			case 3:
				this.jugadoresEnMesa.add(crearJugadorConParametrosPorTeclado());
				break;

			case 0:
				// NO TIENE QUE HACER NADA
				break;

			default:
				Escaner.avisoOpcionIncorrecta();
			}

		} while (opcion != 0);

	}

	private Jugador crearJugadorConParametrosPorTeclado() {

		int credito;
		int creditoObjetivo;
		int rondasMaximas;

		System.out.println("  Introduzca el crédito:");
		credito = Escaner.entero(1, Jugador.MAX_INT_VALUE);

		System.out.println("  Introduzca el crédito objetivo:");
		creditoObjetivo = Escaner.entero(credito + 1, Jugador.MAX_INT_VALUE);

		System.out.println("  Introduzca el las rondas maximas:");
		rondasMaximas = Escaner.entero(1, Jugador.MAX_INT_VALUE);

		// Creamos el jugador con la apuesta a 0
		Jugador jugador = new Jugador(credito, creditoObjetivo, rondasMaximas);

		System.out.print("  ¿Desea añadirle ahora la apuesta? ");
		// TODO quizá mas adelante ofrecer copiar la apuesta a otro jugador.
		if (Escaner.yesNoQuestionRecursivo())
			jugador.menuEditarApuesta();

		return jugador;
	}

	/**
	 * Permite la edición de los jugadores en mesa. Permite editar nombres, crédito,
	 * crédito objetivo, rondas máximas y apuestas.
	 */
	private void menuEditarJugador() {
		int opcion;
		boolean retirar = false;

		System.out.println("Editando jugadores");
		System.out.println("");

		Jugador jugadorParaEditar = selecionarJugador();

		do {

			System.out.printf("Ha slecionado a %s.%n", jugadorParaEditar.info());

			System.out.println("  Escoja una opción:");
			System.out.println("1 - Cambiar nombre.");
			System.out.println("2 - Variar crédito.");
			System.out.println("3 - Cambiar crédito objetivo.");
			System.out.println("4 - Cambiar rondas máximas.");
			System.out.println("5 - Cambiar apuesta.");
			System.out.println("0 - Volver atrás.");
			System.out.println("");

			opcion = Escaner.entero();

			System.out.println();

			retirar = jugadorParaEditar.editarJugador(opcion) ? true : false;

			System.out.println();

		} while (opcion != 0);

		if (retirar)
			this.retirarJugador(jugadorParaEditar);

	}

	/**
	 * Menú con las opciones para retirar a un jugador.
	 */
	private void menuRetirarJugador() {

		System.out.println("Retirada de jugadores");
		System.out.println("");

		Jugador jugadorParaRetirar = this.selecionarJugador();

		System.out.printf("Va a retirar al siguiente jugador: %s%n", jugadorParaRetirar.info());
		System.out.print("Esta acción no se puede deshacer. ¿Está seguro?");

		if (Escaner.yesNoQuestionRecursivo()) {
			this.retirarJugador(jugadorParaRetirar);
		} else
			System.out.println("El jugador no ha sido retirado");

		System.out.println("");

	}

	/**
	 * NO IMPLEMENTADO: Comprueba si hay que retirar al jugador. Si es así lo retira. Esto sucede
	 * cuando no le queda crédito, ha alcanzado el credito objetivo o ha superado
	 * las rondas máximas.
	 * 
	 * @param jugadorParaComprobar
	 */
	private void comprobarRetidadaJugador(Jugador jugadorParaComprobar) {
		// TODO Auto-generated method stub

		System.out.println("ListaJugadores.comprobarRetidadaJugador(Jugador)");
		System.out.println("No implementado todavia");
		System.out.println("No implementado todavia");
		System.out.println("No implementado todavia");
		System.out.println("No implementado todavia");
		System.out.println("");
		System.out.println("");
	}

	private void retirarJugador(Jugador jugadorParaRetirar) {

		boolean retirado = false;

		for (int i = 0; i < this.jugadoresEnMesa.size() && !retirado; i++) {
			if (this.jugadoresEnMesa.get(i).getId() == jugadorParaRetirar.getId()) {
				
				ListaJugadores.jugadoresRetirados.add(jugadorParaRetirar);
				
				this.jugadoresEnMesa.remove(i);
				
				retirado = true;
				
			}
		}
		if (retirado)
			System.out.println("Jugador retirado.");
		else
			System.err.println("No se ha podido retirar al jugador.");
	}

	/**
	 * Pide selecionar por teclado un jugador de la lista de jugadores en mesa.
	 * 
	 * @return El Jugador selecionado.
	 */
	private Jugador selecionarJugador() {

		Jugador jugadorSelecionado = null;

		do {

			System.out.println(" Seleccione un jugador:");

			for (int i = 0; i < this.jugadoresEnMesa.size(); i++)
				System.out.printf("%d - %s%n", (i + 1), this.jugadoresEnMesa.get(i).info());

			int numeroJugador = Escaner.entero(1, this.jugadoresEnMesa.size()) - 1;

			jugadorSelecionado = this.jugadoresEnMesa.get(numeroJugador);

		} while (jugadorSelecionado == null);

		return jugadorSelecionado;
	}

	/**
	 * Borra el contenido de las listas de jugadores.
	 */
	public void borrarListas() {

		this.jugadoresEnMesa = new ArrayList<Jugador>();
		ListaJugadores.jugadoresRetirados = new ArrayList<Jugador>();

	}

}
