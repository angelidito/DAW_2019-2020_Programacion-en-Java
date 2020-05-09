/**
 * 
 */
package angelidito.laruleta;

/**
 * 
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class NumeroTablero extends Number implements Comparable<NumeroTablero> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3233488705262267331L;

	/**
	 * Numero y valor al que se corresponde el objeto.
	 */
	private final int n;
	/**
	 * 0 si par o cero, 1 si impar
	 */
	private int paridad;
	/**
	 * -1 si cero, 0 si rojo, 1 si negro
	 */
	private int color;
	/**
	 * -1, 0 o 1, para el cero, la primera o segunda mitad, respectivamente.
	 */
	private int mitad;
	/**
	 * -1, 0, 1 o 2, para el cero, la primera, segunda o tercera docena,
	 * respectivamente.
	 */
	private int docena;
	/**
	 * -1, 0, 1 o 2, para el cero, la fila del 1, del 2 y del 3, respectivamente.
	 */
	private int fila;

	/**
	 * Encaja los atributos de la clase en los valores que les corresponden en un
	 * tablero de ruleta francesa. Si el número no existe en el tablero o es 0, los
	 * valores de todos los atributos salvo de {@code n} seran iguales -1.
	 * 
	 * @param n El numero al que corresponde el objeto.
	 */
	public NumeroTablero(int numero) {

		this.n = numero;

		// Para 0 o los numeros que no pertenecen al tablero
		if (n < 1 || 36 < n) {
			paridad = -1;
			color = -1;
			mitad = -1;
			docena = -1;
			fila = -1;
		}

		// Para los numeros del tablero sin contara el 0
		else {
			paridad = n % 2;

			if ((0 < n && n < 10 && n % 2 == 1) || (11 < n && n < 19 && n % 2 == 0) || (18 < n && n < 28 && n % 2 == 1)
					|| (29 < n && n < 37 && n % 2 == 0))
				color = 1;
			else
				color = 2;

			if (n < 19)
				mitad = 1;
			else
				mitad = 2;

			if (n < 13)
				docena = 1;
			else if (n < 25)
				docena = 2;
			else
				docena = 3;

			if (n % 3 == 1)
				fila = 1;
			else if (n % 3 == 2)
				fila = 2;
			else
				fila = 3;
		}
	}

	/**
	 * @return El número al que corresponde.
	 */
	public int getN() {
		return n;
	}

	/**
	 * @return 0 si par, 1 si impar; -1 si cero
	 */
	public int getParidad() {
		return paridad;
	}

	/**
	 * @return 0 si rojo, 1 si negro; -1 si cero.
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @return 0 o 1, para la primera o segunda mitad, respectivamente; -1 si cero.
	 */
	public int getMitad() {
		return mitad;
	}

	/**
	 * @return 0, 1 o 2, para la primera, segunda o tercera docena,
	 *         respectivamente; -1 si cero.
	 */
	public int getDocena() {
		return docena;
	}

	/**
	 * @return 0, 1 o 2, para la fila del 3, del 1 y del 2,
	 *         respectivamente; -1 si cero.
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * Compara este Numero con el especificado en el parámetro. Devuelve un entero
	 * negativo, cero, o un entero positivo si el Numero es menor, igual o mayor que
	 * el Numero del parámetro.
	 * 
	 * @param numero El Numero a comparar.
	 * @returns un entero negativo, cero, o un entero positivo si el objeto es
	 *          menor, igual o mayor que el objeto del parámetro.
	 */
	@Override
	public int compareTo(NumeroTablero numero) {

		int diferencia;

		diferencia = this.n - numero.getN();

		return diferencia;
	}

	@Override
	public String toString() {
		return String.format("%d", n);
	}

	@Override
	public int intValue() {
		return n;
	}

	@Override
	public long longValue() {
		return (long) n;
	}

	@Override
	public float floatValue() {
		return (float) n;
	}

	@Override
	public double doubleValue() {
		return (double) n;
	}

}
