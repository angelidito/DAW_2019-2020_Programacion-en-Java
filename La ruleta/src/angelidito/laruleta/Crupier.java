package angelidito.laruleta;

import angelidito.escaner.Escaner;

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
	 * TODO
	 */
	public void preguntarNumeroLanzamientos() {
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

		int gananciasBanca = lanzar(lanzamientos);

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

		gananciasBanca = lanzarRecursivo(lanzamientos, gananciasBanca);

		return gananciasBanca;

	}
	
	private int lanzarRecursivo(int lanzamientos, int gananciasBanca) {
		int n;
		n = ruleta.lanzar();
		NumeroRuleta numero = ruleta.getNumeroRuleta(n);

		if (numero.getN() != n)
			throw new BadProgramingRTException("Esto no deberia haber pasado nunca.\n"
					+ "Hay un error en Ruleta.java al crear el array de NumeroRuleta.\n"
					+ "El índice de la posición no coincide con la N del NumeroRuleta.");

		// Aquí cobramos o pagamos a los jugadores
		// Se cumple que: numero.getN() == n
		for (Jugador jugador : listaJugadores.getJugadoresEnMesa()) {
			if (numero.getN() == 0)
				gananciasBanca += this.ganaLaBanca(jugador);
			else
				gananciasBanca += this.administrarJugador(jugador, numero);
		}

		if (lanzamientos == 1)
			return gananciasBanca;
		else
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

		if (listaJugadores.comprobarRetidadaJugador(jugador))
			listaJugadores.retirarJugador(jugador);

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
		if (listaJugadores.comprobarRetidadaJugador(jugador))
			listaJugadores.retirarJugador(jugador);

		return -balance;
	}

}
