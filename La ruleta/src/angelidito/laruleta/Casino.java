package angelidito.laruleta;

import angelidito.vistas.Escaner;
import angelidito.vistas.listados.ListadoEstatisticas;
import angelidito.vistas.listados.ListadoJugadores;
import angelidito.vistas.menus.MenuAdministrarJugadores;
import angelidito.vistas.menus.MenuAñadirJugadores;
import angelidito.vistas.menus.MenuCasino;
import angelidito.vistas.menus.MenuEditarJugadores;
import angelidito.vistas.menus.MenuRetirarJugadores;
import angelidito.vistas.preguntas.PreguntaBorradoDatos;
import angelidito.vistas.preguntas.PreguntasLanzamientos;

/**
 * Clase controladora del programa.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class Casino {

	private static final Crupier crupier = new Crupier(new ListaJugadores());

	public static void main(String[] args) throws Exception {

		// TO DO: para un futuro se podria arreglar esta clase para que mejase distintos
		// crupiers. Con un array list.
		/*
		 * Habria que contar los archivos que hay que se llamen jugadoresEnMesaXX.csv,
		 * siendo XX el id de la lista de jugadores.
		 * 
		 * Después habrá que añadir la opción de si jugar con un crupier o con todos a
		 * la vez. Para lo cual vendría genial la concurrencia.
		 * 
		 * Debería añadirse otro menú para añadir crupiers, que contendría el menú de
		 * las listas de jugadores. Cada crupier el suyo, está claro.
		 */

		Escaner escaner = new Escaner();

		MenuCasino vistaCasino;
		MenuCasino.bienvenida();

		int opcion;
		boolean opcionIncorrecta = false;
		do {

			vistaCasino = new MenuCasino();
			vistaCasino.printMenu(opcionIncorrecta);

			opcionIncorrecta = false;

			opcion = Escaner.entero();

			vistaCasino.println();

			switch (opcion) {

			case 1:
//				escojerCrupier(crupiers);
				preguntarNumeroLanzamientos(crupier);
				break;

			case 2:
				// Lista de jugadores
				menuAdministrarJugadores();
				break;

			case 3:
				new ListadoEstatisticas(Ruleta.getEstadisticas(), Crupier.getGananciasBanca());
				break;

			case 8:
				PreguntaBorradoDatos borrado;
				borrado = new PreguntaBorradoDatos();

				if (borrado.borrarDatos())
					GestionCSV.borrarDatos();
				break;

			case 9: // Guarda pero no sale del bucle
			case 0: // Gurada y sí se sale del bucle
				guardarDatos(vistaCasino, crupier.getListaJugadores());
				break;
			default:
				opcionIncorrecta = true;

			}

		} while (opcion != 0);

		escaner.close();
		vistaCasino.fin();
	}

//	/**
//	 * De entre todos los crupiers da uno a elegir y pregunta cuantos lanzamientos
//	 * hacer. Ahora que sólo hay uno, no da a elegir crupier.
//	 * 
//	 * @param crupiers Lista de crupiers.
//	 */
//	private static void escojerCrupier(ArrayList<Crupier> crupiers) {
//
//		
//
//	}

	/**
	 * Pregunta el nº de lanzamientos a realizar y los realiza.
	 * 
	 * @param crupier Crupier que lanza su ruleta.
	 */
	private static void preguntarNumeroLanzamientos(Crupier crupier) {
		PreguntasLanzamientos lanzamiento = new PreguntasLanzamientos();

		int minLanzamientos = 1;
		int maxLanzamientos = 1000000;
		int lanzamientos;
		String mensajeJugadoresRetirados;

		lanzamientos = lanzamiento.numLanzamientos(minLanzamientos, maxLanzamientos);

		mensajeJugadoresRetirados = crupier.lanzar(lanzamientos);

		lanzamiento.mostrar(mensajeJugadoresRetirados);

		if (lanzamiento.lazarDeNuevo())
			preguntarNumeroLanzamientos(crupier);

	}

	/**
	 * Menu de jugadores. Permite listar, añadir, retirar y editar jugadores.
	 * 
	 */
	private static void menuAdministrarJugadores() {

		int opcion = 0;
		boolean opcionIncorrecta = false;
		do {

			MenuAdministrarJugadores adminJugadores = new MenuAdministrarJugadores();

			adminJugadores.printMenu(opcionIncorrecta);

			opcionIncorrecta = false;

			opcion = Escaner.entero();

			adminJugadores.println();

			switch (opcion) {

			case 1:

				ListadoJugadores listadoJugadores = new ListadoJugadores(crupier.getListaJugadores());

				listadoJugadores.infoJugadores();

				break;

			case 2:
				menuAñadirJugadores();
				break;

			case 3:
				menuRetirarJugadores();
				break;

			case 4:
				menuEditarJugadores();
				break;

			case 9:

				listadoJugadores = new ListadoJugadores(crupier.getListaJugadores());

				listadoJugadores.informacionJugadores();
				break;
			case 0:
				// NO TIENE QUE HACER NADA
				break;

			default:
				opcionIncorrecta = true;
			}

		} while (opcion != 0);
	}

	/**
	 * Permite editar a los jugadores.
	 */
	private static void menuEditarJugadores() {

		MenuEditarJugadores editar;
		editar = new MenuEditarJugadores(crupier.getListaJugadores());

		editar.printMenu();

		editar.editarJugador();

	}

	/**
	 * Permite retirar jugadores.
	 */
	private static void menuRetirarJugadores() {

		MenuRetirarJugadores retirar;
		retirar = new MenuRetirarJugadores(crupier.getListaJugadores());

		retirar.printMenu();

		retirar.retirarJugador();

	}

	/**
	 * Permite añadir un jugador. Estandar o personalizado.
	 */
	private static void menuAñadirJugadores() {

		int opcion;
		MenuAñadirJugadores menuAñadirJugador;
		ListaJugadores listaJugadores = crupier.getListaJugadores();

		boolean opcionIncorrecta = false;
		do {
			menuAñadirJugador = new MenuAñadirJugadores();

			menuAñadirJugador.printMenu(opcionIncorrecta);
			opcionIncorrecta = false;

			opcion = Escaner.entero();

			menuAñadirJugador.println();

			switch (opcion) {

			case 1:
				listaJugadores.getJugadoresEnMesa().add(new Jugador());
				break;

			case 2:
				int credito = menuAñadirJugador.pedirCredito();
				listaJugadores.getJugadoresEnMesa().add(new Jugador(credito));
				break;

			case 3:

				Jugador jugador;

				jugador = menuAñadirJugador.crearJugadorConParametrosPorTeclado();

				listaJugadores.getJugadoresEnMesa().add(jugador);
				break;

			case 0:
				// NO TIENE QUE HACER NADA
				break;

			default:
				opcionIncorrecta = true;
			}

		} while (opcion != 0);

	}

	/**
	 * Guarda los datos del programa. Tanto las listas de jugadores, como el
	 * histórico de la ruleta.
	 * 
	 * @param v         Vista que emplea.
	 * 
	 * @param jugadores Lista de jugadores a guardar.
	 */
	private static void guardarDatos(MenuCasino v, ListaJugadores jugadores) {

		v.guardandoJugadores();

		jugadores.guardarJugadores();

		v.jugadoresGuardadosGuardandoEstadisticas();

		Ruleta.guardarHisorico();

		v.estadisticaGuardadas();

		v.println();
		v.waitForEnter();
	}

}
