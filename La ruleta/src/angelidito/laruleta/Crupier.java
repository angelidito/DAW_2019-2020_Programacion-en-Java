package angelidito.laruleta;

import java.util.ArrayList;

import angelidito.escaner.Escaner;
import angelidito.laruleta.excepciones.BadProgramingRTException;

/**
 * Clase encargada de manejar la ruleta y a los jugadores. Así como cobrarlos y
 * pagarlos según el número que salga en la ruleta.
 * 
 * @author <a href="twitter.com/angelidito">Ángel M. D.</a>
 */
public class Crupier {

	/**
	 * Lista de jugadores que maneja el Crupier.
	 */
	private ListaJugadores listaJugadores;
	private ArrayList<Jugador> jugadoresParaRetirar;

	/**
	 * Ruleta que aneja el Crupier.
	 */
	private Ruleta ruleta;

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
	 * Pregunta el nº de lanzamientos a realizar y los realiza.
	 */
	public void preguntarNumeroLanzamientos() {
		int gananciasBanca = 0;
		int lanzamientos = 1;
		int minLanzamientos = 1;
		int maxLanzamientos = 1000000;

		System.out.printf("¿Cuantos lanzamientos desea realizar?%n" + "De %d a %d: ", minLanzamientos, maxLanzamientos);
		lanzamientos = Escaner.entero(minLanzamientos, maxLanzamientos);
		System.out.println();

		if (lanzamientos > 0)
			gananciasBanca = lanzar(lanzamientos);

		Casino.ganado(gananciasBanca);

	}

	/**
	 * Realiza lanzamientos en la ruleta y cobra o paga a los jugadores. Devuelve
	 * una cantidad entera que representa el crédito que ha ganado la banca.
	 * 
	 * @param lanzamientos Lanzamientos a realizar.
	 * 
	 * @return Crédito que ha ganado la banca.
	 */
	private int lanzar(int lanzamientos) {
		int gananciasBanca = 0;
		int jugadoresRetirados = 0;

		for (int l = 0; l < lanzamientos; l++) {

			int n;
			n = ruleta.lanzar();
			NumeroRuleta numero = ruleta.getNumeroRuleta(n);

			if (numero.getN() != n)
				throw new BadProgramingRTException("Esto no deberia haber pasado nunca.\n"
						+ "Hay un error en Ruleta.java al crear el array de NumeroRuleta.\n"
						+ "El índice de la posición no coincide con la N del NumeroRuleta.");

			// Aquí cobramos o pagamos a los jugadores
			// Se cumple que: numero.getN() == n
			for (int i = 0; i < listaJugadores.getJugadoresEnMesa().size(); i++) {

				if (numero.getN() == 0)
					gananciasBanca += this.ganaLaBanca(listaJugadores.getJugadoresEnMesa().get(i));
				else
					gananciasBanca += this.administrarJugador(listaJugadores.getJugadoresEnMesa().get(i), numero);

				//

				if (listaJugadores.comprobarRetidadaJugador(listaJugadores.getJugadoresEnMesa().get(i))) {
					System.out.printf("Lanzamiento nº%d:%n", lanzamientos);
					listaJugadores.retirarJugador(listaJugadores.getJugadoresEnMesa().get(i));
					i--;
					jugadoresRetirados++;
				}
			}
//			 Hay que retirarlo después del bucle, porque si no lanza excepcion.
//			 Si fuese un bucle
//			this.retirarJugadoresParaRetirar();

		}
		if (jugadoresRetirados == 0)
			System.out.println("Ningun jugador ha sido retirado esta vez, ¡qué suerte!");
		System.out.println();
		System.out.println();

		return gananciasBanca;

	}

	/**
	 * Realiza lanzamientos en la ruleta y cobra o paga a los jugadores. Devuelve
	 * una cantidad entera que representa el crédito que ha ganado la banca.
	 * 
	 * @param lanzamientos Lanzamientos a realizar.
	 * 
	 * @return Crédito que ha ganado la banca.
	 */
	private int lanzarRec(int lanzamientos) {
		int gananciasBanca = 0;

		gananciasBanca = lanzarRecursivo(lanzamientos, gananciasBanca);

		return gananciasBanca;

	}

	private int lanzarRecursivo(int lanzamientos, int gananciasBanca) {
		if (lanzamientos == 0)
			return gananciasBanca = 0;

		int n;
		n = ruleta.lanzar();
		NumeroRuleta numero = ruleta.getNumeroRuleta(n);

		if (numero.getN() != n)
			throw new BadProgramingRTException("Esto no deberia haber pasado nunca.\n"
					+ "Hay un error en Ruleta.java al crear el array de NumeroRuleta.\n"
					+ "El índice de la posición no coincide con la N del NumeroRuleta.");

		// Aquí cobramos o pagamos a los jugadores
		// Se cumple que: numero.getN() == n

		for (int i = 0; i < listaJugadores.getJugadoresEnMesa().size(); i++) {
			if (numero.getN() == 0)
				gananciasBanca += this.ganaLaBanca(listaJugadores.getJugadoresEnMesa().get(i));
			else
				gananciasBanca += this.administrarJugador(listaJugadores.getJugadoresEnMesa().get(i), numero);

			if (listaJugadores.comprobarRetidadaJugador(listaJugadores.getJugadoresEnMesa().get(i))) {

				listaJugadores.retirarJugador(listaJugadores.getJugadoresEnMesa().get(i));
				i--;
			}
		}

//		 Hay que retirarlo después del bucle, porque si no lanza excepcion.
//		 Si fuese un bucle
//		this.retirarJugadoresParaRetirar();

		return lanzarRecursivo((--lanzamientos), gananciasBanca);

	}

	/**
	 * Resta al jugador el credito apostado y lo devuelve. Si el jugador no tiene
	 * suficiente crédito para, lo retira.
	 * 
	 * @param jugador Jugador al que hay que administrar.
	 * 
	 * @return El dinero que ha ganado la banca del jugador.
	 */
	private int ganaLaBanca(Jugador jugador) {

		int gananciasBanca = jugador.totalApostado();

		jugador.variarCredito(-gananciasBanca);

		return gananciasBanca;

	}

	/**
	 * Suma o resta al jugador el credito apostado y devuelve su balance final. Si
	 * el jugador no tiene suficiente crédito para mantener la apuesta, lo retira.
	 * 
	 * @param jugador Jugador al que hay que administrar.
	 * @param num     Número que ha salido en la ruleta.
	 * 
	 * @return El dinero que ha ganado la banca del jugador.
	 */
	private int administrarJugador(Jugador jugador, NumeroRuleta num) {
		Apuesta apuesta = jugador.getApuesta();
		int totalApostado = apuesta.totalApostado();
		// variable auxiliar para evitar operaciones innecesarias
		int totalAnotado = 0;
		int ganado = 0;
		int perdido = 0;
		int balance;
		// variable auxiliar
		int cantidadApostada;

		for (int i = 0; i < apuesta.getRojoNegro().length; i++) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = apuesta.getRojoNegro()[i];
				if (cantidadApostada > 0) {
					totalAnotado += cantidadApostada;
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
					totalAnotado += cantidadApostada;
					if (num.getParidad() == i)
						ganado += cantidadApostada;
					else
						perdido += cantidadApostada;
				}
			}
		}

		for (int i = 0; i < apuesta.getModulos().length; i++) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = apuesta.getModulos()[i];
				if (cantidadApostada > 0) {
					totalAnotado += cantidadApostada;
					if (num.getFila() == i)
						ganado += cantidadApostada;
					else
						perdido += cantidadApostada;
				}
			}
		}

		for (int i = 0; i < apuesta.getMitades().length; i++) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = apuesta.getMitades()[i];
				if (cantidadApostada > 0) {
					totalAnotado += cantidadApostada;
					if (num.getMitad() == i)
						ganado += cantidadApostada;
					else
						perdido += cantidadApostada;
				}
			}
		}

		for (int i = 0; i < apuesta.getDocenas().length; i++) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = apuesta.getDocenas()[i];
				if (cantidadApostada > 0) {
					totalAnotado += cantidadApostada;
					if (num.getDocena() == i)
						ganado += cantidadApostada;
					else
						perdido += cantidadApostada;
				}
			}
		}

		for (NumeroApuesta numeroApostado : apuesta.getNumeros()) {
			if (totalAnotado < totalApostado) {
				cantidadApostada = numeroApostado.getCantidadApostada();
				if (cantidadApostada > 0) {
					totalAnotado += cantidadApostada;
					if (num.compareTo(numeroApostado) == 0)
						ganado += cantidadApostada * 35;
					else
						perdido += cantidadApostada;
				}
			}
		}

		balance = ganado - perdido;

		jugador.variarCredito(balance);

		return -balance;
	}

	/**
	 * @param jugador Jugador a comprobar
	 */
	private void comprobarRetiradaJugador(Jugador jugador) {
		if (listaJugadores.comprobarRetidadaJugador(jugador)) {
			jugadoresParaRetirar.add(jugador);
		}
	}

	/**
	 */
	private void retirarJugadoresParaRetirar() {
		for (Jugador jugador : jugadoresParaRetirar) {
			listaJugadores.retirarJugador(jugador);
		}
		jugadoresParaRetirar.clear();
	}

}
