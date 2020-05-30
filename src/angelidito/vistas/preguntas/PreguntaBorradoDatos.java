/**
 * 
 */
package angelidito.vistas.preguntas;

import angelidito.vistas.Escaner;

/**
 * Pregunta sobre el borrado de datos.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class PreguntaBorradoDatos extends Preguntas {

	/**
	 * 
	 * Pregunta si de desean borrar los datos. Imprime por pantalla si la acción se
	 * va o no a llevar a cabo.
	 * 
	 * @return {@code true} si e quieren borrar, {@code false} si no.
	 */
	public boolean borrarDatos() {

		System.out.println("Está a punto de borrar los archivos del programa.");
		System.out.print("Esta acción no puede deshacerse. ¿Esta seguro?");

		boolean borrar = Escaner.yesNoQuestionRecursivo();

		datosBorrados(borrar);

		return borrar;
	}

	/**
	 * Imprime por pantalla si el borrado se va a llevar a cabo o no. Dependiendo de
	 * si el parámetro es true o false.
	 * 
	 * @param borrado Confirmación o no del borrado.
	 */
	private void datosBorrados(boolean borrado) {

		if (borrado)
			System.out.println("Borrando datos.");

		else
			System.out.println("No re realiza ningun borrado.");

		println();

	}
}
