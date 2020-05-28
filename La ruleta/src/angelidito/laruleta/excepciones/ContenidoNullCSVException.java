package angelidito.laruleta.excepciones;

/**
 * Excepcion lanzable por las clases que manejan el guardado y recuperación de
 * datos. Se lanza cuando alguna linea del CSV es nula. Subclase de {@code BadCSVException}.
 * 
 * @author angel
 *
 */
public class ContenidoNullCSVException extends BadCSVException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 608652755453108416L;

	/**
	 * 
	 */
	public ContenidoNullCSVException() {
	}

	/**
	 * @param message Mensaje de la excepción.
	 */
	public ContenidoNullCSVException(String message) {
		super(message);
	}

	/**
	 * @param cause Causa.
	 */
	public ContenidoNullCSVException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message Mensaje de la excepción.
	 * @param cause Causa.
	 */
	public ContenidoNullCSVException(String message, Throwable cause) {
		super(message, cause);
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public ContenidoNullCSVException(String message, Throwable cause, boolean enableSuppression,
//			boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
}
