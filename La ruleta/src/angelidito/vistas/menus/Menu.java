/**
 * 
 */
package angelidito.vistas.menus;

import angelidito.vistas.Vista;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class Menu extends Vista {

	private String cabecera;
	private String[] opciones;

	/**
	 * Crea la vista.
	 * 
	 * @param cabecera Cabecera del menú.
	 * @param opciones Vector con las opciones imprimir.
	 */
	public Menu(String cabecera, String[] opciones) {

		this.cabecera = cabecera;
		this.opciones = opciones;

	}

	/**
	 * @param cabecera the cabecera to set
	 */
	protected void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * @param opciones the opciones to set
	 */
	protected void setOpciones(String[] opciones) {
		this.opciones = opciones;
	}

	/**
	 * @return the opciones
	 */
	protected String[] getOpciones() {
		return opciones;
	}

	/**
	 * Imprime el menú. Cabecera y opciones.
	 */
	public void printMenu() {
		System.out.println(cabecera);
		for (String opcion : opciones) {
			System.out.println(opcion.toString());
		}
		println();

	}

	/**
	 * Imprime el menú. Cabecera y opciones.
	 * 
	 * @param opcionIncorrecta Si es positivo imprime la un mensaje.
	 */
	public void printMenu(boolean opcionIncorrecta) {

		System.out.println(cabecera);

		for (String op : opciones)
			System.out.println(op);

//		for (int i = 0; i < opciones.length; i++) 
//			System.out.println(opciones[i]);

		if (opcionIncorrecta)
			System.out.println("Asegúrese de que introduce una opción de entre las disponibles.");
		else
			println();

	}

}
