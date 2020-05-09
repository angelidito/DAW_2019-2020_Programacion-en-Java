package angelidito.laruleta.gestion;

/**
 * Excepcion general lanzable por las clases que manejan el guardado y
 * recuperación de datos.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class BadCSVException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 608652755453108416L;

	/**
	 * 
	 */
	public BadCSVException() {
	}

	/**
	 * @param message Mensaje de la excepción.
	 */
	public BadCSVException(String message) {
		super(message);
	}

	/**
	 * @param cause Causa.
	 */
	public BadCSVException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message Mensaje de la excepción.
	 * @param cause Causa
	 */
	public BadCSVException(String message, Throwable cause) {
		super(message, cause);
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public BadCSVException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
}
