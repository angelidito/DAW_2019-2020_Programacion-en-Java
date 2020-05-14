/**
 * 
 */
package angelidito.vistas.listados;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class ListadoEstatisticas {

	/**
	 * Metodo unico de la clase. Crea la vista y muestra las estadisticas de la
	 * rulta y las canancias del casino.
	 * 
	 * @param estadisticas Estadísticas de la ruleta.
	 * @param ganancias    Ganancias del día del casino.
	 */
	public ListadoEstatisticas(String estadisticas, int ganancias) {
		System.out.println(estadisticas);

		System.out.println("");

		System.out.printf("Hoy la casa ha ganado %d créditos.%n", ganancias);
		System.out.println("");

		System.out.println("");
	}

}
