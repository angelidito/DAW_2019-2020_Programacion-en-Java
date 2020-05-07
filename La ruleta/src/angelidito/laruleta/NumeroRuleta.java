package angelidito.laruleta;

public class NumeroRuleta extends NumeroTablero {

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
		return "Nº" + getN() + ": " + ocurrencias + " ocurrencias";
	}

}
