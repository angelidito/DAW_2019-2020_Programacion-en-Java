package angelidito.laruleta;

import java.util.ArrayList;

import angelidito.escaner.Escaner;
import angelidito.vistas.listados.ListadoEstatisticas;
import angelidito.vistas.menus.MenuCasino;

/**
 * 
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class Casino {

	private static int ganancias = 0;

	private static ArrayList<Crupier> crupiers = new ArrayList<Crupier>(1);

	/**
	 * Suma una cantidad a las ganancias.
	 * 
	 * @param cantidad Cantidad a sumar
	 */
	public static void ganado(int cantidad) {
		Casino.ganancias += cantidad;
	}

	public static void main(String[] args) {

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

			opcion = Escaner.entero();

			if(opcionIncorrecta) {
				opcionIncorrecta=false;
				Escaner.avisoOpcionIncorrecta();
			}
			else
			vistaCasino.println();
		vistaCasino.println();

			switch (opcion) {

			case 1:
				jugar(crupiers);
				break;

			case 2:
				// Lista de jugadores
				crupiers.get(0).getJugadores().menu();
				break;

			case 3:
				new ListadoEstatisticas(Ruleta.getEstadisticas(), ganancias);
				break;

			case 8:
				borrarDatos(vistaCasino);
				break;

			case 9:
				guardarDatos(vistaCasino, crupiers.get(0).getJugadores());
				break;

			case 0:
				// Salir y guardar
				break;
			default:
				opcionIncorrecta = true;

			}

		} while (opcion != 0);

		guardarDatos(vistaCasino, crupiers.get(0).getJugadores());
		vistaCasino.fin();
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

	private static void borrarDatos(MenuCasino vistaCasino) {
		if (vistaCasino.borrarDatos()) {

			GestionCSV.borrarDatos();
			vistaCasino.datosBorrados(true);
		} else
			vistaCasino.datosBorrados(false);
	}

	/**
	 * De entre todos los crupiers da uno a elegir y pregunta cuantos lanzamientos
	 * hacer. Ahora que sólo hay uno, no da a elegir crupier.
	 * 
	 * @param crupiers Lista de crupiers.
	 */
	private static void jugar(ArrayList<Crupier> crupiers) {

		crupiers.get(0).preguntarNumeroLanzamientos();

	}

}
