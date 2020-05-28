/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.laruleta.Apuesta;
import angelidito.laruleta.Jugador;
import angelidito.vistas.Escaner;
import angelidito.vistas.Escaner.TipoEntero;

/**
 * Menú que permite editar la apuesta.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuEditarApuesta extends Menu {

	/**
	 * Jugador que contiene la apuesta a editar.
	 */
	private Jugador jugador;
	/**
	 * Apuesta a editar extraida del jugador.
	 */
	private Apuesta apuestaParaEditar;

	private final static String[] OPCIONES = { "1 - Apuestas números", "2 - Apuestas a pares e impares",
			"3 - Apuestas a rojos y negros", "4 - Apuestas a mitades", "5 - Apuestas a docenas",
			"6 - Apuestas a filas 2 a 1", "7 - Retirar toda la apuesta", "0 - Guardar cambios" };
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 5, 6, 7, 0 };

	/**
	 * Crea la vista.
	 * 
	 * @param jugador Jugador cuya apuesta hay que editar.
	 */
	public MenuEditarApuesta(Jugador jugador) {
		this(String.format("Apuesta: %s%n%n", jugador.getApuesta().info())
				+ "¿Qué de la apuesta parte desea editar?\n  Escoja una opción:", OPCIONES, jugador);
	}

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera Cabecera del menú.
	 * @param opciones Vector con las opciones imprimir.
	 * @param jugador  Jugador cuya apuesta hay que editar.
	 */
	private MenuEditarApuesta(String cabecera, String[] opciones, Jugador jugador) {
		super(cabecera, opciones);
		this.jugador = jugador;
		this.apuestaParaEditar = jugador.getApuesta();
	}

	/**
	 * Modifica la apuesta por teclado. El total apostado no puede ser mayor que el
	 * credito disponible del jugador.
	 * 
	 * @param op 1, 2, 3, 4, 5 o 6, según lo que se quiera editar.
	 */
	public void editarApuesta(int op) {
		int creditoDisponible = jugador.getCredito();
		int opcion = 0;
		int cantidad = 0;

		switch (op) {

		case 1:

			System.out.println("  Indique a que numero deséa apostar: ");
			int numero = Escaner.entero(0, 36);

			cantidad = pedirCantidad();

			if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
				System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

			else
				apuestaParaEditar.apostarNumero(numero, cantidad);

			break;

		case 2:

			do {

				System.out.println("  Indique a qué desea apostar:");
				System.out.println("1 - Pares");
				System.out.println("2 - Impares");
				opcion = Escaner.entero(1, 2);

				switch (opcion) {
				case 1:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarPares(cantidad);

					break;

				case 2:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarImpares(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 3:

			do {

				System.out.println("  Indique a qué desea apostar:");
				System.out.println("1 - Rojo");
				System.out.println("2 - Negro");
				opcion = Escaner.entero(1, 2);

				switch (opcion) {
				case 1:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarRojo(cantidad);

					break;

				case 2:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarNegro(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 4:

			do {

				System.out.println("  Indique a qué mitad desea apostar:");
				System.out.println("1 - Del 1 al 18");
				System.out.println("2 - Del 19 al 36");
				opcion = Escaner.entero(1, 2);

				switch (opcion) {
				case 1:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarPrimeraMitad(cantidad);

					break;

				case 2:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarSegundaMitad(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 5:

			do {

				System.out.println("  Indique a qué docena desea apostar:");
				System.out.println("1 - Primera");
				System.out.println("2 - Segunda");
				System.out.println("3 - Tercera");
				opcion = Escaner.entero(1, 3);

				switch (opcion) {
				case 1:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarPrimeraDocena(cantidad);

					break;

				case 2:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarSegundaDocena(cantidad);

					break;

				case 3:

					cantidad = pedirCantidad();

					if ((apuestaParaEditar.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						apuestaParaEditar.apostarTerceraDocena(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 6:

			do {

				System.out.println("  Indique a qué fila 2 a 1 desea apostar:");
				System.out.println("1 - La fila del 1");
				System.out.println("2 - La fila del 2");
				System.out.println("3 - La fila del 3");
				opcion = Escaner.entero(1, 3);

				switch (opcion) {
				case 1:

					cantidad = pedirCantidad();
					apuestaParaEditar.apostarModulo1(cantidad);

					break;

				case 2:

					cantidad = pedirCantidad();
					apuestaParaEditar.apostarModulo2(cantidad);

					break;

				case 3:

					cantidad = pedirCantidad();
					apuestaParaEditar.apostarModulo0(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 7:

			apuestaParaEditar.setApuestasTo0();

			break;

		case 0:
			// Salir
			break;

		default:
			Escaner.avisoOpcionIncorrecta();
		}

	}

	/**
	 * Pregunta y escanea la cantidad a apostar. Sólo admite enteros positivos.
	 * 
	 * @return La cantidad introducida.
	 */
	private int pedirCantidad() {

		System.out.println("¿Qúe cantidad desea apostar?");
		int cantidad = Escaner.entero(TipoEntero.POSITIVO);

		return cantidad;

	}

	/**
	 * @return vector con las opciones disponibles
	 */
	public static int[] getNumOpciones() {
		return NUM_OPCIONES;
	}

}
