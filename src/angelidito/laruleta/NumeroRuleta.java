package angelidito.laruleta;

/**
 * Representa los números de la ruleta. Hereda de {@code NumeroTablero} y guarda además
 * las ocurrencias de cada número.
 *
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class NumeroRuleta extends NumeroTablero {
	private static final long serialVersionUID = -6284727951773680634L;

	/**
	 * Ocurrencias del número {@code n}.
	 */
	private int ocurrencias;

	/**
	 * @return Las ocurrencias del número.
	 */
	public int getOcurrencias() {
		return ocurrencias;
	}

	/**
	 * @param ocurrencias Las ocurrencias a guardar.
	 */
	public void setOcurrencias(int ocurrencias) {
		this.ocurrencias = ocurrencias;
	}

	/**
	 * Añadie una ocurrencia al número.
	 */
	public void añadirOcurrencia() {
		ocurrencias++;

	}

	public NumeroRuleta(int numero) {
		super(numero);
		this.ocurrencias = 0;
	}

	public NumeroRuleta(int numero, int ocurrencias) {
		super(numero);
		this.ocurrencias = ocurrencias;

	}

	@Override
	public String toString() {

		return String.format("Nº%02d - %d ocurrencias", this.getN(), this.ocurrencias);
	}

}
