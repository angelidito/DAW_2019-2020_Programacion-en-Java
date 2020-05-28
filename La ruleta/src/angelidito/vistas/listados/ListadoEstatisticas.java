/**
 * 
 */
package angelidito.vistas.listados;

/**
 * Listado de las estadisticas de la ruleta.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class ListadoEstatisticas extends Listado {

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

		if (1 < ganancias)
			System.out.printf("Hoy la casa ha ganado %d créditos.%n", ganancias);
		else if (ganancias < -1)
			System.out.printf("Hoy la casa ha perdido %d créditos.%n", -1 * ganancias);
		else if (ganancias == 1)
			System.out.printf("Hoy la casa ha ganado 1 crédito.%n");
		else if (ganancias == 0)
			System.out.printf("Hoy la casa no ha ganado nada.%n");
		else if (ganancias == -1)
			System.out.printf("Hoy la casa ha perdido 1 crédito.%n");

		System.out.println("");

		waitForEnter();

		System.out.println("");

	}

}
