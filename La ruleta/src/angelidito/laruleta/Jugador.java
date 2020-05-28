package angelidito.laruleta;

import angelidito.laruleta.excepciones.BadCSVException;
import angelidito.laruleta.excepciones.BadProgramingRTException;
import angelidito.laruleta.excepciones.CantidadCamposCSVException;
import angelidito.laruleta.excepciones.ContenidoCorruptoCSVException;
import angelidito.laruleta.excepciones.ContenidoNullCSVException;

/**
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class Jugador {

	/**
	 * Nombre del jugador. Por defecto será SinNombreXX, siendo XX el número de
	 * jugadores creados.
	 */
	private String nombre;

	/**
	 * Crédito que está dispuesto a perder el jugador antes de parar.
	 */
	private int credito;

	/**
	 * Crédito al que quiere llegar el jugador antes de parar.
	 */
	private int creditoObjetivo;

	/**
	 * Número de rondas que va a jugar si no se queda antes sin dinero.
	 */
	private int rondasMaximas;

	/**
	 * Número de rondas que ha jugado.
	 */
	private int rondas;

	/**
	 * Crédito que ha obtenido de la mesa.
	 */
	private int ganado;

	/**
	 * Crédito que ha perdido en la mesa.
	 */
	private int perdido;

	/**
	 * Apuesta que realiza el Jugador.
	 */
	private Apuesta apuesta;

	/**
	 * Contadores internos del número de jugadores.
	 */
	private static int numJugadores, jugadoresSinNombre;

	public static final int MAX_INT_VALUE = 1000000000;

	/**
	 * Identificador único de cada jugador.
	 */
	private int id;

	static {
		jugadoresSinNombre = 0;
		numJugadores = 0;
	}

	/**
	 * Crea el Jugador con el crédito y las rondas máximas inicializadas a 10. El
	 * credito objetivo será de mil millones. Crea la apuesta del jugador sin
	 * apostar nada.
	 */
	public Jugador() {
		this.id = numJugadores++;
		this.nombre = "SinNombre" + String.format("%02d", ++jugadoresSinNombre);
		this.credito = 10;
		this.creditoObjetivo = Jugador.MAX_INT_VALUE;
		this.rondasMaximas = Jugador.MAX_INT_VALUE;
		this.rondas = 0;
		this.ganado = 0;
		this.perdido = 0;
		this.apuesta = new Apuesta();
	}

	/**
	 * Crea el Jugador con el crédito a pasado. Aunque se pase un crédito menor, el
	 * mínimo será de 10. Crea la apuesta del jugador sin apostar nada.
	 * 
	 * @param credito Crédito con el que comienza.
	 */
	public Jugador(int credito) {
		this.id = numJugadores++;
		this.nombre = "SinNombre" + String.format("%02d", ++jugadoresSinNombre);
		this.credito = credito > 10 ? credito : 10;
		this.creditoObjetivo = Jugador.MAX_INT_VALUE;
		this.rondasMaximas = Jugador.MAX_INT_VALUE;
		this.rondas = 0;
		this.ganado = 0;
		this.perdido = 0;
		this.apuesta = new Apuesta();
	}

	/**
	 * Crea el Jugador con el crédito y las rondas máximas pasados. Aunque se pase
	 * un crédito o un numero de rondas máximas menor, los mínimos serán de 10. Crea
	 * la apuesta del jugador sin apostar nada.
	 * 
	 * @param nombre          Nombre del jugador.
	 * @param credito         El crédito del jugador.
	 * @param creditoObjetivo Crédito con el que el jugador se retirará.
	 * @param rondasMaximas   Las rondas máximas a jugar.
	 */
	public Jugador(String nombre, int credito, int creditoObjetivo, int rondasMaximas) {
		this.id = numJugadores++;
		this.nombre = nombre;
		this.credito = credito > 10 ? credito : 10;
		this.creditoObjetivo = creditoObjetivo;
		this.rondasMaximas = rondasMaximas > 10 ? rondasMaximas : Jugador.MAX_INT_VALUE;
		this.rondas = 0;
		this.ganado = 0;
		this.perdido = 0;
		this.apuesta = new Apuesta();
	}

	/**
	 * Crea el Jugador con el crédito y las rondas máximas pasados. Aunque se pase
	 * un crédito o un numero de rondas máximas menor, los mínimos serán de 10. Crea
	 * la apuesta del jugador sin apostar nada.
	 * 
	 * @param credito         El crédito del jugador.
	 * @param creditoObjetivo Crédito con el que el jugador se retirará.
	 * @param rondasMaximas   Las rondas máximas a jugar.
	 */
	public Jugador(int credito, int creditoObjetivo, int rondasMaximas) {
		this.id = numJugadores++;
		this.nombre = "SinNombre" + String.format("%02d", ++jugadoresSinNombre);
		this.credito = credito > 10 ? credito : 10;
		this.creditoObjetivo = creditoObjetivo;
		this.rondasMaximas = rondasMaximas > 10 ? rondasMaximas : Jugador.MAX_INT_VALUE;
		this.rondas = 0;
		this.ganado = 0;
		this.perdido = 0;
		this.apuesta = new Apuesta();
	}

	/**
	 * Crea el Jugador con el crédito, las rondas máximas y la apuesta pasados.
	 * Aunque se pase un crédito o un numero de rondas másximas menor, los mínimos
	 * serán de 10. Fija la apuesta a la pasada por para, si la que recibe es null,
	 * si no, le fija la del parámetro.
	 * 
	 * @param credito         El crédito del jugador.
	 * @param creditoObjetivo Crédito con el que el jugador se retirará.
	 * @param rondasMaximas   Las rondas máximas a jugar.
	 * @param apuesta         La Apuesta a realizar.
	 */
	public Jugador(int credito, int creditoObjetivo, int rondasMaximas, Apuesta apuesta) {
		this.id = numJugadores++;
		this.nombre = "SinNombre" + String.format("%02d", ++jugadoresSinNombre);
		this.credito = credito > 0 ? credito : 10;
		this.creditoObjetivo = creditoObjetivo;
		this.rondasMaximas = rondasMaximas > 10 ? rondasMaximas : Jugador.MAX_INT_VALUE;
		this.rondas = 0;
		this.ganado = 0;
		this.perdido = 0;
		this.apuesta = apuesta != null ? apuesta : new Apuesta();
	}

	/**
	 * Crea el Jugador con el nombre, el crédito, las rondas máximas y la apuesta
	 * pasados. Aunque se pase un crédito o un numero de rondas másximas menor, los
	 * mínimos serán de 10. Fija la apuesta a la pasada por para, si la que recibe
	 * es null, si no, le fija la del parámetro.
	 * 
	 * @param nombre          Nombre del jugador.
	 * @param credito         El crédito del jugador.
	 * @param creditoObjetivo Crédito con el que el jugador se retirará.
	 * @param rondasMaximas   Las rondas máximas a jugar.
	 * @param apuesta         Apuesta a realizar.
	 */
	public Jugador(String nombre, int credito, int creditoObjetivo, int rondasMaximas, Apuesta apuesta) {
		this.id = numJugadores++;
		this.nombre = nombre != null ? nombre : "SinNombre" + String.format("%02d", id);
		this.credito = credito;
		this.creditoObjetivo = creditoObjetivo;
		this.rondasMaximas = rondasMaximas > 10 ? rondasMaximas : Jugador.MAX_INT_VALUE;
		this.apuesta = apuesta != null ? apuesta : new Apuesta();
	}

	/**
	 * Crea un jugador con la infomacion de una línea del CSV. Da valor a todos los
	 * atributos de la clase con dicha información, salvo si el CSV no contiene las
	 * 7 celdas que debería, en cuyo caso haría lo mismo que el contructor por
	 * defecto.
	 * 
	 * @param lineaDelCSV Línea del CSV que contiene toda la informarción de un
	 *                    jugador.
	 * @throws BadCSVException Cuando la linea del CSV es null, la cantidad de
	 *                         campos que contiene es menor que 8 o hay contenido no
	 *                         numérico donde no debería.
	 */
	public Jugador(String lineaDelCSV) throws BadCSVException {

		if (lineaDelCSV == null)
			throw new ContenidoNullCSVException("Información nula de jugador.");

		try {

			String[] columnas = lineaDelCSV.split(";");

			// Si coincide la longitud, bien; si no, excepción y constructor por defecto.
			if (columnas.length < 8)
				throw new CantidadCamposCSVException(
						"No hay campos suficientes para recuperar el jugador:" + lineaDelCSV);

			this.credito = Integer.parseInt(columnas[1]);
			this.creditoObjetivo = Integer.parseInt(columnas[2]) > 10 ? Integer.parseInt(columnas[2]) : 10;
			this.rondasMaximas = Integer.parseInt(columnas[3]) > 10 ? Integer.parseInt(columnas[2]) : 10;
			this.rondas = Integer.parseInt(columnas[4]);
			this.ganado = Integer.parseInt(columnas[5]);
			this.perdido = Integer.parseInt(columnas[6]);

			// Esta parte viene depues de lo anterior podría haber dado error lo anterior y
			// no queremos aumentar numJugadores
			this.id = numJugadores++;
			// Nombre
			if (nombre != null && columnas[0].length() >= 11) {

				if (columnas[0].substring(0, 9).compareTo("SinNombre") == 0)
					this.nombre = "SinNombre" + String.format("%02d", ++jugadoresSinNombre);
				else
					this.nombre = columnas[0];

			} else if (columnas[0] != null)
				this.nombre = columnas[0];
			else
				this.nombre = "SinNombre" + String.format("%02d", ++jugadoresSinNombre);

			// Y esto viene lo ultimo porque el catch usa el nombre del jugador.
			try {
				this.apuesta = new Apuesta(columnas[7]);
			} catch (CantidadCamposCSVException e) {
//				System.err.println(e.getMessage());
				System.err.printf("Apuesta de %s fijada a 0.%n", this.nombre);
				this.apuesta = new Apuesta();

			}

		} catch (NumberFormatException e) {
			throw new ContenidoCorruptoCSVException("No se ha podido cargar un Jugador del CSV: " + lineaDelCSV);
		}

	}

	/**
	 * Devuelve el identificador único del jugador.
	 * 
	 * @return El id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el nombre del Jugador.
	 * 
	 * @param nombre el nombre a fijar.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve el crédito actual del jugador.
	 * 
	 * @return El credito.
	 */
	public int getCredito() {
		return credito;
	}

	/**
	 * Cambia el crédito del jugador. Si el parámetro crédito es negativo se fijará
	 * en 0.
	 * 
	 * @param credito a crédito a fijar.
	 */
	public void setCredito(int credito) {
		if (credito < 0)
			this.credito = 0;
		else
			this.credito = credito;
	}

	/**
	 * Devuelve el crédito objetivo
	 * 
	 * @return El credito objetivo.
	 */
	public int getCreditoObjetivo() {
		return creditoObjetivo;
	}

	/**
	 * @param creditoObjetivo el crédito objetivo a fijar.
	 */
	public void setCreditoObjetivo(int creditoObjetivo) {
		this.creditoObjetivo = creditoObjetivo;
	}

	/**
	 * Varia el crédito del jugador. Suma la cantidad pasada al credito del jugador.
	 * Obviamente, si el parámetro es negativo, restará en vez de sumar. Si la
	 * cantidad es positiva, se suma a ganado. Si la cantidad es negativa, se suma a
	 * perdido.
	 * 
	 * @param cantidad de crédito a sumar.
	 */
	public void variarCredito(int cantidad) {

		this.credito += cantidad;

		if (cantidad > 0)
			ganado += cantidad;
		else
			ganado -= cantidad;

	}

	/**
	 * @return Las rondas máximas
	 */
	public int getRondasMaximas() {
		return rondasMaximas;
	}

	/**
	 * Cambia las rondas máximas.
	 * 
	 * @param rondasMaximas las rondas máximas a fijar.
	 */
	public void setRondasMaximas(int rondasMaximas) {
		this.rondasMaximas = rondasMaximas;
	}

	/**
	 * @return Las rondas que lleva el jugador
	 */
	public int getRondas() {
		return rondas;
	}

	/**
	 * @param rondas las rondas a fijar
	 */
	public void setRondas(int rondas) {
		this.rondas = rondas;
	}

	/**
	 * Devuelve la apuesta del jugador. Desde aquí se la puede modificar sin crear
	 * una nueva.
	 * 
	 * @return La Apuesta.
	 */
	public Apuesta getApuesta() {
		return apuesta;
	}

	/**
	 * Fija la Apuesta del jugador.
	 * 
	 * @param apuesta la Apuesta a fijar.
	 */
	public void setApuesta(Apuesta apuesta) {

		if (apuesta.totalApostado() > credito)
			throw new BadProgramingRTException();

		this.apuesta = apuesta;
	}

	// Esto no sé por qué lo he hecho. No tiene sentido.
//	/**
//	 * Cambia la Apuesta del jugador por otra.
//	 * 
//	 */
//	public void setApuesta() {
//
//		Apuesta apuesta = null;
//
//		this.apuesta = apuesta;
//	}

//	/**
//	 * Incluye información como el nombre, rondas, dinero y la apuesta.
//	 */
//	public String toString2() {
//		return nombre;
//
//	}

	/**
	 * Devuelve en formato CSV el jugador y su apuesta.
	 * 
	 * @return Un texto con formato CSV con el jugador y su apuesta.
	 */
	public String toString() {
		String jugador;
		jugador = nombre + ";" + credito + ";" + creditoObjetivo + ";" + rondasMaximas + ";" + rondas + ";" + ganado
				+ ";" + perdido + ";" + apuesta;
		return jugador;
	}

	/**
	 * Ofrece la información basica del jugador: nombre y credito. Es la versión
	 * corta del método {@code informacion()}. Incluye información del nombre,
	 * rondas, dinero y apuesta.
	 * 
	 * @return El nombre y el crédito en el siguiente formato: Nombre, crédito:
	 *         cantidad.
	 */
	public String info() {
		String jugador;

		String apuesta = "sin apuestas.";
		if (this.totalApostado() > 0)
			apuesta = String.format("total apostado: %d; apuestas: [ %s]", this.totalApostado(), this.apuesta.info());

		jugador = String.format("%s, crédito: %d; %s", this.nombre, this.credito, apuesta);
		return jugador;
	}

	/**
	 * Ofrece la información detallada del jugador. Es la versión extendida del
	 * método {@code info()}. Incluye información del nombre, rondas, dinero y
	 * apuesta.
	 * 
	 * @return La información detallada del jugador.
	 */
	public String informacion() {
		String jugador;

//						  "****************************************%n" 
//						+ "****************************************%n"
//						+ "*** Jugador: %s%n" 
//						+ "*** Rondas%n" 
//						+ "***    Máximas:   %d%n" 
//						+ "***    Actual:    %d%n"
//						+ "***    Restantes: %d%n" 
//						+ "*** Crédito%n" 
//						+ "***    Actual:    %d%n" 
//						+ "***    Objetivo:  %d%n"
//						+ "***    Ganado:    %d%n" 
//						+ "***    Perdido:   %d%n" 
//						+ "***    Balance:   %d%n"
//						+ "*** Apuesta: %s%n" 
//						+ "***   %s%n" 
//						+ "****************************************%n"
//						+ "****************************************%n",
		jugador = String.format(
				"****************************************%n" + "****************************************%n"
						+ "*** Jugador: %s%n" + "*** Rondas%n" + "***    Máximas:   %d%n" + "***    Actual:    %d%n"
						+ "***    Restantes: %d%n" + "*** Crédito%n" + "***    Actual:    %d%n"
						+ "***    Objetivo:  %d%n" + "***    Ganado:    %d%n" + "***    Perdido:   %d%n"
						+ "***    Balance:   %d%n" + "*** Apuesta: %n" + "%s%n"
						+ "****************************************%n" + "****************************************%n",
				nombre, rondasMaximas, rondas, rondasMaximas - rondas, credito, creditoObjetivo, ganado, perdido,
				getBalance(), apuesta.informacion());
		return jugador;
	}

	/**
	 * @return La diferencia entre ganado y perdido
	 */
	private int getBalance() {
		return ganado - perdido;
	}

	/**
	 * Devuelve la suma total del crédito apostado.
	 * 
	 * @return El crédito total apostado
	 */
	public int totalApostado() {
		return this.apuesta.totalApostado();
	}


	

}
