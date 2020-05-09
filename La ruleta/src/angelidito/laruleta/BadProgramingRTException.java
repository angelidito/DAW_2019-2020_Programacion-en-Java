/**
 * 
 */
package angelidito.laruleta;

/**
 * Excepcion a lanzar cuando un se ocurre una situación que no debería ocurrir
 * de estar bien diseñado el programa. No se debería tener que capturar, ya que
 * habría que solucionarlo corrigiendo o añadiendo código.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class BadProgramingRTException extends RuntimeException {

	/**
	 * 
	 */
	public BadProgramingRTException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public BadProgramingRTException(String message) {
		super(message);

		System.err.println(message);
	}

	/**
	 * @param cause
	 */
	public BadProgramingRTException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BadProgramingRTException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BadProgramingRTException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
