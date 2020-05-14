/**
 * 
 */
package angelidito.vistas.menus;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class Menu {

	private String cabecera;

	private String[] opciones;

	private int[] numerosOpciones;

	/**
	 * Crea la vista e imprime por pantalla el menú. Cabecera y opciones.
	 * 
	 * @param cabecera        Cabecera del menú.
	 * @param opciones        Vector con las opciones imprimir.
	 * @param numerosOpciones Vector que contiene los numeros que son aceptados.
	 */
	public Menu(String cabecera, String[] opciones, int[] numerosOpciones) {

		this.cabecera = cabecera;
		this.opciones = opciones;
		this.numerosOpciones = numerosOpciones;

		printMenu();
	}

	/**
	 * @param cabecera the cabecera to set
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @param opciones the opciones to set
	 */
	public void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	/**
	 * @param numerosOpciones the numerosOpciones to set
	 */
	public void setNumerosOpciones(int[] numerosOpciones) {
		this.numerosOpciones = numerosOpciones;
	}

	public void printMenu() {
		System.out.println(cabecera);
		for (String opcion : opciones) {
			System.out.println(opcion);
		}
		println();

	}

	public void println() {
		System.out.println();
	}

}
