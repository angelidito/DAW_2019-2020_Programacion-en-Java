package angelidito.laruleta.gestion;

/**
 * Excepcion lanzable por las clases que manejan el guardado y recuperación de
 * datos. Se lanza cuando alguna linea del CSV es nula y no se esperaba. Subclase de {@code BadCSVException}.
 * 
 * @author angel
 *
 */
public class ContenidoCorruptoCSVException extends BadCSVException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 608652755453108416L;

	/**
	 * 
	 */
	public ContenidoCorruptoCSVException() {
	}

	/**
	 * @param message Mensaje de la excepción.
	 */
	public ContenidoCorruptoCSVException(String message) {
		super(message);
	}

	/**
	 * @param cause Causa.
	 */
	public ContenidoCorruptoCSVException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message Mensaje de la excepción.
	 * @param cause Causa.
	 */
	public ContenidoCorruptoCSVException(String message, Throwable cause) {
		super(message, cause);
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public ContenidoCorruptoCSVException(String message, Throwable cause, boolean enableSuppression,
//			boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
}
