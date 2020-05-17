/**
 * 
 */
package angelidito.vistas.preguntas;

import java.util.Scanner;

import angelidito.aux.Escaner;

/**
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class PreguntasLanzamientos extends Preguntas {

	/**
	 * Pregunta y devuelve el nº de lanzamientos a realizar.
	 * 
	 * @param minLanzamientos Lanzamientos mínimos posibles
	 * @param maxLanzamientos Lanzamientos máximos posibles
	 * @return La cantidad introducida por teclado.
	 */
	public int numLanzamientos(int minLanzamientos, int maxLanzamientos) {

		System.out.printf("¿Cuantos lanzamientos desea realizar?%n" + "De %d a %d: ", minLanzamientos, maxLanzamientos);

		int lanzamientos = Escaner.entero(minLanzamientos, maxLanzamientos);

		println();

		return lanzamientos;
	}

	/**
	 * Pregunta si desea lanzar de nuevo. Devuelve {@code true} o {@code false} en
	 * consecuencia.
	 * 
	 * @return {@code true} o {@code false}, si se desa o no lanzar.
	 */
	public boolean lazarDeNuevo() {
		boolean yesNo;

		System.out.println("¿Desea lanzar de nuevo? [s/n]");
		yesNo = Escaner.yesNoQuestion();

		println();
		println();

		return yesNo;
	}

}
