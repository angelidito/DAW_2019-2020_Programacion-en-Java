package angelidito.laruleta;

import java.util.ArrayList;

import angelidito.aux.Escaner;
import angelidito.aux.Pair;
import angelidito.vistas.listados.ListadoEstatisticas;
import angelidito.vistas.listados.ListadoJugadores;
import angelidito.vistas.menus.MenuAdministrarJugadores;
import angelidito.vistas.menus.MenuCasino;
import angelidito.vistas.preguntas.PreguntasLanzamientos;

/**
 * 
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class Casino {

	private static final ArrayList<Crupier> crupiers = new ArrayList<Crupier>(1);

	public static void main(String[] args) throws Exception {

		// TODO: para un futuro se podria arreglar esta clase para que mejase distintos
		// crupiers. Por el momento en el array list solo habrá 1.
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
		crupiers.add(new Crupier(new ListaJugadores()));
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
				escojerCrupier(crupiers);
				break;

			case 2:
				// Lista de jugadores
				menuAdministrarJugadores();
				break;

			case 3:
				new ListadoEstatisticas(Ruleta.getEstadisticas(), Crupier.getGananciasBanca());
				break;

			case 8:
				borrarDatos(vistaCasino);
				break;

			case 9: // Guarda pero no sale del bucle
			case 0: // Gurada y sí se sale del bucle
				guardarDatos(vistaCasino, crupiers.get(0).getJugadores());
				break;
			default:
				opcionIncorrecta = true;

			}

		} while (opcion != 0);

		vistaCasino.fin();
	}

	/**
	 * De entre todos los crupiers da uno a elegir y pregunta cuantos lanzamientos
	 * hacer. Ahora que sólo hay uno, no da a elegir crupier.
	 * 
	 * @param crupiers Lista de crupiers.
	 */
	private static void escojerCrupier(ArrayList<Crupier> crupiers) {

		preguntarNumeroLanzamientos(crupiers.get(0));

	}

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
				menuListarJugadores();
				
				new ListadoJugadores(crupiers.getlistaJugadores);
				
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

			case 9:
				for (Jugador jugador : jugadoresEnMesa) {
					System.out.println(jugador.informacion());
				}
				break;
			case 0:
				// NO TIENE QUE HACER NADA
				break;

			default:
				opcionIncorrecta = true;
			}

		} while (opcion != 0);
	}

	private static void borrarDatos(MenuCasino vistaCasino) {
		if (vistaCasino.borrarDatos()) {

			GestionCSV.borrarDatos();
			vistaCasino.datosBorrados(true);
		} else
			vistaCasino.datosBorrados(false);
	}

	/**
	 * Guarda los datos del programa. Tanto las listas de jugadores, como el
	 * histórico de la ruleta.
	 * 
	 * @param vista
	 * 
	 * @param jugadores Lista de jugadores a guardar.
	 */
	private static void guardarDatos(MenuCasino v, ListaJugadores jugadores) {

		v.guardandoJugadores();

		jugadores.guardarJugadores();

		v.jugadoresGuardadosGuardandoEstadisticas();

		Ruleta.guardarHisorico();

		v.estadisticaGuardadas();
	}

}
