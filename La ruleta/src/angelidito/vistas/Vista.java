/**
 * 
 */
package angelidito.vistas;

/**
 * 
 * Clase madre de las vistas. Inlcluye tres métodos: println, waitForEnter y
 * mostrar.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class Vista {

	/**
	 * Imprime por pantalla un salto de linea. Lo sé, muy inútil, era para ahorrarme
	 * algo de tiempo escribiendo saltos de linea.
	 */
	public void println() {
		System.out.println();
	}

	/**
	 * Espera a que el usuario pulse enter antes de continuar.
	 */
	public void waitForEnter() {
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
