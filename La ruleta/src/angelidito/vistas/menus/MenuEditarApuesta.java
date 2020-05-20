/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.aux.Escaner;
import angelidito.aux.TipoEntero;
import angelidito.laruleta.Apuesta;
import angelidito.laruleta.Jugador;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class MenuEditarApuesta extends Menu {

	private Jugador jugador;
	private Apuesta apuestaParaEditar;

	private final static String[] OPCIONES = { // TODO
			"1 - Jugador estándar:\\n\\tnombre por defecto\\n\\tcrédito = 10\\n\\trondas máximas = 10",
			"Jugador estándar con credito personalizado", "3 - Jugador con parámetros personalizados",
			"0 - Volver atrás" };
	private final static int[] NUM_OPCIONES = { 1, 2, 3, 0 };

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 */
	public MenuEditarApuesta(Jugador jugador) {
		this(String.format("Apuesta: %s%n%n", jugador.getApuesta().info())
				+ "¿Qué de la apuesta parte desea editar?\n  Escoja una opción:", OPCIONES, jugador);
	}

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 * @param numerosOpciones Vector que contiene los numeros que son aceptados.
	 */
	public MenuEditarApuesta(String cabecera, String[] opciones, Jugador jugador) {
		super(cabecera, opciones);
		this.jugador = jugador;
		this.apuestaParaEditar = jugador.getApuesta();
	}

	/**
	 * Menú de edicion de la Apuesta.
	 */
	public void editarApuesta() {
		int op;
		do {
			super.printMenu();
			// TODO
			System.out.println("1 - Apuestas números");
			System.out.println("2 - Apuestas a pares e impares");
			System.out.println("3 - Apuestas a rojos y negros");
			System.out.println("4 - Apuestas a mitades");
			System.out.println("5 - Apuestas a docenas");
			System.out.println("6 - Apuestas a filas 2 a 1");
			System.out.println("7 - Retirar toda la apuesta");
			System.out.println("0 - Guardar cambios");

			op = Escaner.entero(0, 7);

			System.out.println();

			apuestaParaEditar.editarApuesta(jugador.getCredito(), op);

			System.out.println();

		} while (op != 0);
	}

	public void editarOpcion(int creditoDisponible, int op) {

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

}
