package angelidito.laruleta.excepciones;

/**
 * Excepción lanzable cuando no se encuentra un archivoCSV. Esto lo hacen las
 * clases que manejan el guardado y recuperación de datos. Subclase de
 * {@code BadCSVException}.
 * 
 * @author angel
 *
 */
public class NoCSVException extends BadCSVException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 608652755453108416L;

	/**
	 * 
	 */
	public NoCSVException() {
	}

	/**
	 * @param message Mensaje de la excepción.
	 */
	public NoCSVException(String message) {
		super(message);
	}

	/**
	 * @param cause Causa.
	 */
	public NoCSVException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message Mensaje de la excepción.
	 * @param cause Causa.
	 */
	public NoCSVException(String message, Throwable cause) {
		super(message, cause);
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public NoCSVException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
}
