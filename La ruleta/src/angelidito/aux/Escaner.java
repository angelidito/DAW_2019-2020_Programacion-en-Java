/**
 * 
 */
package angelidito.aux;

import java.io.Closeable;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase intermediaria de la clase Scanner. Maneja las excepciones para no
 * lanzar ninguna y guía al usuario a introdocir lo que tiene que introducir.
 * 
 * @author <a href="twitter.com/angelidito">Ángel M. D.</a>
 */
public final class Escaner implements Closeable {

	static Scanner escaner = new Scanner(System.in);

	/**
	 * Escanea un número entero positivo por teclado. El método guía al usuario si
	 * se empeña en no intruducir un número analizable hasta que lo hace.
	 * 
	 * @return El número dentro del rango introducido por el usuario.
	 */
	public static int entero() {

		String textoEscaneado;
		int numeroIntroducido = 0;
		boolean textoEsNumero = false;

		do {

			textoEscaneado = escaner.next();
			System.out.println();

			try {
				numeroIntroducido = Integer.parseInt(textoEscaneado);
				textoEsNumero = true;

			} catch (NumberFormatException e) {

				System.out.println("Error. Introduzca un número.");
				textoEsNumero = false;

			}

		} while (!textoEsNumero);

		// escaner.close();

		return numeroIntroducido;

	}

	/**
	 * Escanea un número entero por teclado. Mayor/menor o igual que 0 según el
	 * parámetro. El método guía al usuario si se empeña en no intruducir un número
	 * analizable hasta que lo hace.
	 * 
	 * @param tipoEntero Tipo de entero que se quiere escanear. POSITIVO, 0 o mayor;
	 *                   NEGATIVO, 0 o menor.
	 * @return El número dentro del rango introducido por el usuario.
	 */
	public static int entero(TipoEntero tipoEntero) {

		String textoEscaneado;
		int numeroIntroducido = 0;
		boolean textoEsNumero = false;

		do {

			textoEscaneado = escaner.next();
			System.out.println();

			try {
				numeroIntroducido = Integer.parseInt(textoEscaneado);
				textoEsNumero = true;

			} catch (NumberFormatException e) {

				System.out.println("Error. Introduzca un número.");
				textoEsNumero = false;

			}

		} while (!textoEsNumero);

		// escaner.close();
		return numeroIntroducido;

	}

	/**
	 * Escanea el número entero introducido por teclado. Sólo aceptará un número
	 * entero que se encuentre entre los parámetros min y max, ambos inclusive. El
	 * método guía al usuario si se empeña en no intruducir un número analizable
	 * hasta que lo hace. Si min es mayor que max o max es mayor que min, ambos se
	 * fijaran en el mayor de los dos.
	 * 
	 * @param min Valor mínimo der rango
	 * @param max Valor máximo der rango
	 * @return El número escaneado dentro del rango.
	 */
	public static int entero(int min, int max) {

		if (min > max || max < min) {
			min = Math.max(max, min);
			max = min;
		}

		String textoEscaneado = "0x80000000";
		int numeroIntroducido = -999999;
		boolean textoEsNumero = false;

		do {

			textoEscaneado = escaner.next();
			System.out.println();

			try {
				numeroIntroducido = Integer.parseInt(textoEscaneado);
				textoEsNumero = true;

				if (!(min <= numeroIntroducido && numeroIntroducido <= max))
					System.out.printf("El número debe estar entre %d y %d, ambos inclusive.%n", min, max);

			} catch (NumberFormatException e) {

				System.out.println("Error. Introduzca de nuevo el número.");
				textoEsNumero = false;

			}

		} while (!textoEsNumero || !(min <= numeroIntroducido && numeroIntroducido <= max));

		// escaner.close();
		return numeroIntroducido;

	}

	/**
	 * Escanea el dígito introducido por teclado y lo devuelve como un carácter.
	 * Sólo aceptará un dígito que se encuentre entre los parámetros min y max,
	 * ambos inclusive. El método guía al usuario si se empeña en no intruducir lo
	 * que debe hasta que lo hace. Tanto el mínimo como el máximo deben estar entre
	 * 0 y 9, ambos inclusive. Si min es mayor que max o max es mayor que min, ambos
	 * se fijaran en el mayor de los dos.
	 * 
	 * @param min Mínimo valor del dígito
	 * @param max Máximo valor del dijito
	 * @return El carácter correspondiente al número dentro del rango introducido
	 *         por el usuario.
	 */
	public static char caracterNumericoEnRango(int min, int max) {
		if (min < 0)
			min = 0;
		if (max > 9)
			max = 9;
		if (min > max || max < min) {
			min = Math.max(max, min);
			max = min;
		}

		String textoEscaneado;

		textoEscaneado = escaner.nextLine();
		System.out.println();

		while (textoEscaneado.length() != 1 || !Character.isDigit(textoEscaneado.charAt(0))
				|| !(min <= Integer.parseInt(textoEscaneado.substring(0, 1))
						&& Integer.parseInt(textoEscaneado.substring(0, 1)) <= max)) {

			if (textoEscaneado.length() != 1) {
				System.out.println("Debe introducir un único número.");
			} else {
				System.out.println("Por favor, escriba un solo número.");
				System.out.printf("Debe de estar entre %d y %d.%n", min, max);
			}

			textoEscaneado = escaner.nextLine();
			System.out.println();

		}

		return textoEscaneado.charAt(0);
	}

	/**
	 * Escanea el texto introducido por teclado.
	 * 
	 * @return El texto escaneado.
	 */
	public static String texto() {

		String textoEscaneado = null;
		do {
			try {
				textoEscaneado = escaner.nextLine();
			} catch (NoSuchElementException e) {
				System.err.println("No ha introducido nada.");
				System.out.println("Vuelva a intentarlo.");
			}

		} while (textoEscaneado == null);

		// escaner.close();
		return textoEscaneado;
	}

	/**
	 * Espera a que el usuario pulse enter.
	 * 
	 * @return Un salto de linea.
	 */
	public static String Enter() {
		escaner.nextLine();
		return "\n";
	}

	/**
	 * Pide un si o un no y devuelve en consecuencia {@code true} o {@code false}.
	 * Escanea el texto introducido por teclado hasta que se introduzca una de las
	 * siguiente opciones: s, si, sí, n, no. No atiende a mayúsculas o minúsculas.
	 * 
	 * @return {@code true} o {@code false}, dependiendo si la respusta es si o no.
	 */
	public static boolean yesNoQuestion() {

		String texto = "";
		boolean textoAdecuado = false;
		boolean yesNo = false;
		do {
			try {
				texto = escaner.next();
			} catch (Exception e) {
				texto = "";
			}
			if (texto.compareToIgnoreCase("s") == 0 || texto.compareToIgnoreCase("sí") == 0
					|| texto.compareToIgnoreCase("si") == 0) {

				textoAdecuado = true;
				yesNo = true;

			} else if (texto.compareToIgnoreCase("n") == 0 || texto.compareToIgnoreCase("no") == 0) {
				textoAdecuado = true;
				yesNo = false;

			}

		} while (!textoAdecuado);

		return yesNo;
	}

	// METODO CON RECURSIVIDAD
	/**
	 * Pide un si o un no y devuelve en consecuencia {@code true} o {@code false}.
	 * Escanea el texto introducido por teclado hasta que se introduzca una de las
	 * siguiente opciones: s, si, sí, n, no. No atiende a mayúsculas o minúsculas.
	 * METODO CON RECURSIVIDAD. No deberia atascar la máquina si está bien escrito.
	 * 
	 * @return {@code true} o {@code false}, dependiendo si la respusta es si o no.
	 */
	public static boolean yesNoQuestionRecursivo() {

		String texto = "";
		boolean yesNo;

		System.out.println(" [s/n]");
		try {
			texto = escaner.next();
		} catch (Exception e) {

		}
		if (texto.compareToIgnoreCase("s") == 0 || texto.compareToIgnoreCase("sí") == 0
				|| texto.compareToIgnoreCase("si") == 0) {

			yesNo = true;

		} else if (texto.compareToIgnoreCase("n") == 0 || texto.compareToIgnoreCase("no") == 0) {

			yesNo = false;

		} else {
			yesNo = yesNoQuestionRecursivo();
		}

		return yesNo;
	}

	// METODO CON RECURSIVIDAD
	/**
	 * Imprime por pantalla una pregunta tipo si/no. Además de un salto de linea.
	 * Escanea el texto introducido por teclado hasta que se introduzca una de las
	 * siguiente opciones: s, si, sí, n, no. No atiende a mayúsculas o minúsculas.
	 * METODO CON RECURSIVIDAD. No deberia atascar la máquina si está bien escrito.
	 * 
	 * @param pregunta Pregunta a imprimir por pantalla.
	 * @return {@code true} o {@code false}, dependiendo si la respusta es si o no.
	 */
	public static boolean yesNoQuestionRecursivo(String pregunta) {

		String texto = "";
		boolean yesNo;

		System.out.println(pregunta + " [s/n]");
		try {
			texto = escaner.next();
		} catch (Exception e) {

		}
		if (texto.compareToIgnoreCase("s") == 0 || texto.compareToIgnoreCase("sí") == 0
				|| texto.compareToIgnoreCase("si") == 0) {

			yesNo = true;

		} else if (texto.compareToIgnoreCase("n") == 0 || texto.compareToIgnoreCase("no") == 0) {

			yesNo = false;

		} else {

			System.out.println("Opción no valida, pruebe de nuevo.");

			yesNo = yesNoQuestionRecursivo();
		}

		// escaner.close();
		return yesNo;
	}

	/**
	 * Imprime por pantalla un aviso. Este dice: Asegúrese de que introduce una
	 * opción de las disponibles. Ademas, dos saltos de línea.
	 */
	public static void avisoOpcionIncorrecta() {
		System.out.println("Asegúrese de que introduce una opción de las disponibles.");
		System.out.println();
	}

	@Override
	public void close() throws IOException {

		escaner.close();

	}
}
