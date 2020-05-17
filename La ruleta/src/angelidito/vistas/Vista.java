/**
 * 
 */
package angelidito.vistas;

import angelidito.aux.Escaner;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class Vista {

	public void println() {
		System.out.println();
	}

	public static void waitForEnter() {

		System.out.println("Pulsa Enter para continuar...");
		Escaner.Enter();
	}

	/**
	 * Realiza un System.out.println del parámetro.
	 * 
	 * @param <O> Tipo del objeto a mostrar.
	 * 
	 * @param str El objeto a imprimir por pantalla.
	 */
	public <O> void mostrar(O str) {

		System.out.println(str);
	}
}
