package angelidito.laruleta.excepciones;

/**
 * Excepcion lanzable cuando la cantidad de campo del CSV es incorrecta. Esto se
 * hace en las clases que manejan el guardado y recuperación de datos. Subclase
 * de {@code BadCSVException}.
 * 
 * @author angel
 *
 */
public class CantidadCamposCSVException extends BadCSVException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 608652755453108416L;

	/**
	 * 
	 */
	public CantidadCamposCSVException() {
	}

	/**
	 * @param message Mensaje de la excepción.
	 */
	public CantidadCamposCSVException(String message) {
		super(message);
	}

	/**
	 * @param cause Causa.
	 */
	public CantidadCamposCSVException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message Mensaje de la excepción.
	 * @param cause Causa.
	 */
	public CantidadCamposCSVException(String message, Throwable cause) {
		super(message, cause);
	}

//	/**
//	 * @param message
//	 * @param cause
//	 * @param enableSuppression
//	 * @param writableStackTrace
//	 */
//	public CantidadCamposCSVException(String message, Throwable cause, boolean enableSuppression,
//			boolean writableStackTrace) {
//		super(message, cause, enableSuppression, writableStackTrace);
//	}
}
