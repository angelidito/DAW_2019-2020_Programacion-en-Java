/**
 * 
 */
package angelidito.laruleta;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class NumeroApuesta extends NumeroTablero {
	private static final long serialVersionUID = -3117945003343960634L;

	/**
	 * Cantidad apostada al número {@code n}.
	 */
	private int cantidadApostada = 0;

	/**
	 * @return la cantidad apostada.
	 */
	public int getCantidadApostada() {
		return cantidadApostada;
	}

	/**
	 * @param cantidad La cantidad a apostar.
	 */
	public void setCantidadApostada(int cantidad) {
		this.cantidadApostada = cantidad;
	}

	/**
	 * No apuesta nada. A los demás atributos de la clase los encaja en los valores
	 * que les corresponden en un tablero de ruleta francesa. Si el número no existe
	 * en el tablero o es 0, los valores de todos los atributos salvo de {@code n} y
	 * {@code cantidadApostada} seran iguales -1.
	 * 
	 * 
	 * @param numero El numero al que corresponde el objeto.
	 */
	public NumeroApuesta(int numero) {
		super(numero);
		this.cantidadApostada = 0;
	}

	/**
	 * Apuesta la cantidad pasada al número {@code n}. A los demás atributos de la
	 * clase los encaja en los valores que les corresponden en un tablero de ruleta
	 * francesa. Si el número no existe en el tablero o es 0, los valores de todos
	 * los atributos salvo de {@code n} y {@code cantidadApostada} seran iguales -1.
	 * 
	 * @param numero   El numero al que corresponde el objeto.
	 * @param cantidad Cantidad a apostar.
	 */
	public NumeroApuesta(int numero, int cantidad) {
		super(numero);
		this.cantidadApostada = cantidad;
	}

	@Override
	public String toString() {
		return String.format("%d al nº%d", cantidadApostada, getN());
	}

}
