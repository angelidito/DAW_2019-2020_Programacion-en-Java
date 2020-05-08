package angelidito.laruleta;

import angelidito.escaner.Escaner;
import angelidito.escaner.TipoEntero;

/**
 * Es la clase encargada de manejar la ruleta y a los jugadors, así como
 * cobrarlos y pagarlos según lo que salga en la ruleta.
 * 
 * @author <a href="twitter.com/angelidito">Ángel M. D.</a>
 */
public class Crupier {

	/**
	 * Lista de jugadores que maneja el Crupier.
	 */
	private ListaJugadores listaJugadores;

	/**
	 * Ruleta que aneja el Crupier.
	 */
	Ruleta ruleta;

	/**
	 * Identificador del Crupier.
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
		this.listaJugadores = jugadores;
		this.id = Crupier.numCrupiers;
		Crupier.numCrupiers++;
		ruleta = new Ruleta();
	}

	/**
	 * @return El id del crupier.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return La Lista de Jugadores.
	 */
	public ListaJugadores getJugadores() {
		return listaJugadores;
	}

	/**
	 * Realiza una tirada en la ruleta y cobra o paga a los jugadores. Devuelve una
	 * cantidad entera que representa el crédito que ha perdido la banca, si es
	 * negativo es que lo ha ganado.
	 * 
	 * @return Crédito que ha perdido la banca, si es negatico es que lo gana.
	 */
	public int lanzar() {
		int perdidasBanca = 0;
		int n;
		int lanzamientos = 1;
		int opcion = 0;

		while (opcion == 0) {
			System.out.println("  ¿Desa realizar uno o más lanzamientos?");
			System.out.println("1 - Uno");
			System.out.println("2 - Dos o más");
			opcion = Escaner.entero(1, 2);
		}

		System.out.println();

		if (opcion == 2) {
			System.out.print("  ¿Cuantos lanzamientos desea realizar?\nDe 2 a 1000: ");
			lanzamientos = Escaner.entero(2, 1000);
			System.out.println();
		}

		for (int i = 0; i < lanzamientos; i++) {

			n = ruleta.lanzar();
			NumeroRuleta numero = ruleta.getNumeroRuleta(n);

			if (numero.getN() != n)
				throw new RuntimeException("Esto no deberia haber pasado nunca.\n"
						+ "Hay un error en Ruleta.java al crear el array de NumeroRuleta.\n"
						+ "El índice de la posición no coincide con la N del NumeroRuleta.");

			// Aquí cobramos o pagamos a los jugadores
			// Se cumple que: numero.getN() == n
			for (Jugador jugador : listaJugadores.getJugadoresEnMesa()) {
				if (numero.getN() == 0)
					perdidasBanca -= this.ganaLaBanca(jugador);
				else
					perdidasBanca += administrarJugador(jugador, numero);
			}

		}

		return perdidasBanca;

	}

	/**
	 * Resta al jugador el credito apostado y lo devuelve. Si el jugador no tiene
	 * suficiente crédito para, lo retira.
	 * 
	 * @param jugador Jugador al que hay que administrar.
	 * 
	 * @return el dinero que ha ganado la banca del jugador.
	 */
	private int ganaLaBanca(Jugador jugador) {

		int totalPerdido = (-1) * jugador.totalApostado();

		jugador.variarCredito(totalPerdido);

		listaJugadores.retirarJugador(jugador);

		return totalPerdido;

	}

	/**
	 * Suma o resta al jugador el credito apostado y devuelve su balance final. Si
	 * el jugador no tiene suficiente crédito para mantener la apuesta, lo retira.
	 * 
	 * @param jugador Jugador al que hay que administrar.
	 * @param num     Número que ha salido en la ruleta.
	 * @return el dinero que ha ganado la banca del jugador.
	 */
	public int administrarJugador(Jugador jugador, NumeroRuleta num) {
		Apuesta apuesta = jugador.getApuesta();
		int totalApostado = apuesta.totalApostado();
		// variable auxiliar par ano hacer operaciones innecesarias
		int totalAnotado = 0;
		int balanceJugador;
		int ganado = 0;
		int perdido = 0;
		// variable auxiliar
		int cantidadApostada;

		for (NumeroApuesta numeroApostado : apuesta.getNumeros()) {
			cantidadApostada = numeroApostado.getCantidadApostada();
			if (cantidadApostada > 0) {

				totalAnotado += cantidadApostada;

				if (num.compareTo(numeroApostado) == 0)
					ganado += cantidadApostada * 35;
				else
					perdido += cantidadApostada;

			}
		}

		for (int i = 0; i < apuesta.getRojoNegro().length; i++) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = apuesta.getRojoNegro()[i];
				if (cantidadApostada > 0) {
					if (num.getColor() == i)
						ganado += cantidadApostada;
					else
						perdido += cantidadApostada;
				}
			}
		}

		for (int i = 0; i < apuesta.getParImpar().length; i++) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = apuesta.getParImpar()[i];
				if (cantidadApostada > 0) {
					if (num.getParidad() == i)
						ganado += cantidadApostada;
					else
						perdido += cantidadApostada;
				}
			}
		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getParImpar()[1] > 0) {
//				String.format("%d a impares. ", jugador.getApuesta().getParImpar()[1]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getRojoNegro()[0] > 0) {
//				String.format("%d al rojo. ", jugador.getApuesta().getRojoNegro()[0]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getRojoNegro()[1] > 0) {
//				String.format("%d al negro. ", jugador.getApuesta().getRojoNegro()[1]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getMitades()[0] > 0) {
//				String.format("%d a la primera mitad. ", jugador.getApuesta().getMitades()[0]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getMitades()[1] > 0) {
//				String.format("%d a la segunda mitad. ", jugador.getApuesta().getMitades()[1]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getDocenas()[0] > 0) {
//				String.format("%d a la primera docena. ", jugador.getApuesta().getDocenas()[0]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getDocenas()[1] > 0) {
//				String.format("%d a la segunda docena. ", jugador.getApuesta().getDocenas()[1]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getDocenas()[2] > 0) {
//				String.format("%d a la tercera docena. ", jugador.getApuesta().getDocenas()[2]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getModulos()[1] > 0) {
//				String.format("%d a la primera fila 2 a 1. ", jugador.getApuesta().getModulos()[1]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getModulos()[2] > 0) {
//				String.format("%d a la segunda fila 2 a 1. ", jugador.getApuesta().getModulos()[2]);
//
//			}
//		}
//		if (totalAnotado < totalApostado) {
//			cantidadApostada = 0;
//			if (jugador.getApuesta().getModulos()[0] > 0) {
//				String.format("%d a la tercera fila 2 a 1. ", jugador.getApuesta().getModulos()[0]);
//			}
//		}

		balanceJugador = ganado - perdido;

		return balanceJugador;
	}

}
