package angelidito.laruleta;

public class NumeroRuleta extends NumeroTablero {
	private static final long serialVersionUID = -6284727951773680634L;

	/**
	 * Ocurrencias del número {@code n}.
	 */
	private int ocurrencias = 0;

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
		String str = String.format("Nº%02d - %d ocurrencias", this.getN(), this.ocurrencias);
		
		return str;
	}

}
