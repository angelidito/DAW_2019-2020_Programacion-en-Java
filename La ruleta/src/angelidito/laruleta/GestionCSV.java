/**
 * 
 */
package angelidito.laruleta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import angelidito.laruleta.excepciones.BadCSVException;
import angelidito.laruleta.excepciones.CantidadCamposCSVException;
import angelidito.laruleta.excepciones.ContenidoCorruptoCSVException;
import angelidito.laruleta.excepciones.ContenidoNullCSVException;
import angelidito.laruleta.excepciones.NoCSVException;

/**
 * Clase que gestiona los archivos con extensión CSV. Los escribe y lee cuando
 * es necesario para las clases que lo requieren.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 */
public class GestionCSV {

	/**
	 * Constructor. La clase {@code GestionCSV} no es instanciable.
	 */
	private GestionCSV() {
	}

	/**
	 * Crea o sobre escribe un CSV con el nombre dado y el contenido de la lista. La
	 * primera fila contendrá el tamaño de las lista.
	 * 
	 * @param <E>           Clase contenida en la lista.
	 * 
	 * @param nombreFichero Nombre completo del fichero, inluidos el punto y la
	 *                      extensión. Ejemplo: nombre.ext
	 * @param lista         Contenido que tendrá el CSV. Cada elemento será una
	 *                      columna.
	 */
	public static <E> void escribirCSV(String nombreFichero, List<E> lista) {

		File fichero = new File(nombreFichero);
		fichero.delete();

		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		try {
			fileWriter = new FileWriter(nombreFichero, true);
			printWriter = new PrintWriter(fileWriter);

			System.out.printf("\t->Escribiendo en el fichero %s%n", nombreFichero);

			// Con esto se sobre escribe el fichero
//			System.out.printf("Escribiendo linea: %d%n", lista.size());

			printWriter.println(String.format("%d", lista.size()));

			for (E elem : lista) {
//				System.out.printf("Escribiendo linea: %s%n", elem);
				printWriter.println(elem);
			}

			System.out.println("   ->Tarea terminada.\n");
		} catch (IOException e) {
			System.err.println("ERROR.\n" + GestionCSV.class);
			e.printStackTrace();
		} finally {
			try {
				if (null != fileWriter)
					fileWriter.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
//	public static <E> void escribirCSV(String fichero, List<E> lista) {
//
//		FileWriter fileWriter = null;
//		PrintWriter printWriter = null;
//
//		try {
//			System.out.printf("Escribiendo en el fichero %s%n", fichero);
//			fileWriter = new FileWriter(fichero);
//			printWriter = new PrintWriter(fileWriter);
//
//			// Esto será la cantidad de lineas que escribirá despues.
//
//			System.out.printf("Escribiendo linea: %d%n", lista.size());
//			printWriter.println(lista.size());
//			// Info a recuperar
//			for (E elem : lista) {
//				System.out.printf("Escribiendo linea: %s%n", elem.toString());
//				printWriter.println(elem.toString());
//			}
//
//		} catch (IOException e) {
//			System.err.println("ERROR.\n" + GestionCSV.class);
//			e.printStackTrace();
//		}
//
//	}

//	public static boolean appendadas(int id) {
//		FileWriter fileWriter = null;
//		PrintWriter pw = null;
//		try {
//			fileWriter = new FileWriter("Clientes.csv", true);
//			pw = new PrintWriter(fileWriter);
//			pw.println("");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (null != fileWriter)
//					fileWriter.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		if (null != fileWriter) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	/**
	 * Recupera y devuelve el histórico a partir del fichero historico.csv. Si no
	 * tiene el formato deseado lo inicializa a 0. Si algun valor es negativo se
	 * toma como 0. Si no existe historico.csv, lo crea con los datos a 0.
	 * 
	 * @return El histórico a partir de historico.csv
	 */
	@SuppressWarnings("resource")
	public static Integer[] obtenerHistoricoOcurrencias() {

		Integer[] ocurrencias = new Integer[37];

		String nombreFichero = "historico.csv";

		File fichero = new File(nombreFichero);

		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {

			if (!fichero.exists())
				throw new NoCSVException(String.format(
						"No se ha encontrado el fichero %s"
								+ "%nPor favor, no se preocupe si es la primera vez que inicia el programa.",
						nombreFichero));

			fileReader = new FileReader(fichero);
			bufferedReader = new BufferedReader(fileReader);

			String linea = bufferedReader.readLine();
			int lineasLeidas = 0;
			while (lineasLeidas < ocurrencias.length && linea != null) {
				linea = bufferedReader.readLine();
				// Este método puede lanzar NumberFormatException, que se maneja en un catch
				int cantidad = Integer.parseInt(linea);

				ocurrencias[lineasLeidas] = cantidad < 0 ? 0 : cantidad;

				lineasLeidas++;
			}

			if (lineasLeidas < ocurrencias.length)
				throw new CantidadCamposCSVException("Cantidad de filas no adecuada en el fichero historico.csv");

		} catch (NoCSVException e) {

			System.err.println("No se ha podido cargar el historial de la Ruleta.");
//			System.err.println(e.getMessage());
			System.err.println();

			for (int i = 0; i < ocurrencias.length; i++)
				ocurrencias[i] = 0;

			GestionCSV.escribirCSV(nombreFichero, Arrays.asList(ocurrencias));

		} catch (BadCSVException e) {

			System.err.println("No se ha podido cargar el historial de la Ruleta.");
//			System.err.println(e.getMessage());
			System.err.println();

			for (int i = 0; i < ocurrencias.length; i++)
				ocurrencias[i] = 0;

		} catch (NumberFormatException e) {

			System.err.println("No se ha podido cargar el historial de la Ruleta.");
			System.err.println("Contenido no numérico en " + nombreFichero + ", se procede a formatear su contenido.");

			System.err.println();
			for (int i = 0; i < ocurrencias.length; i++)
				ocurrencias[i] = 0;

			GestionCSV.escribirCSV(nombreFichero, Arrays.asList(ocurrencias));

		} catch (IOException e) {

			System.err.println("ERROR INESPERADO");
			System.err.println("No se ha podido cargar el historial de la Ruleta.");

			System.err.println();
			e.printStackTrace();

			for (int i = 0; i < ocurrencias.length; i++)
				ocurrencias[i] = 0;

		}

//		finally {
//
//			try {
//
//				if (fileReader != null)
//					fileReader.close();
//
//			} catch (IOException e) {
//
//				System.err.println("ERROR INESPERADO");
//
//				System.err.println();
//				e.printStackTrace();
//
//			}
//		}

		return ocurrencias;
	}

	/**
	 * Recupera y devuelve la lista jugadores a partir de un fichero. Si alguna
	 * linea no contiene los datos necesarios para crear un jugador, no lo añade a
	 * la lista. Informa de cualquier error que pueda suceder.
	 * 
	 * @param nombreFichero nombre del fichero que almacena los jugadores.
	 * @return ArrayList con los jugadores que contenia el fichero.
	 */
	@SuppressWarnings("resource")
	public static ArrayList<Jugador> obtenerJugadores(String nombreFichero) {

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

		FileReader fr = null;
		BufferedReader br = null;

		try {

			try {
				// Esto lanza FileNotFoundException
				// que es subclase de IOException
				fr = new FileReader(nombreFichero);

			} catch (IOException e) {

				throw new NoCSVException(String.format(
						"No se ha encontrado el fichero %s"
								+ "%nPor favor, no se preocupe si es la primera vez que inicia el programa.",
						nombreFichero));
				// Respecto a lo de arriba->tmpco se precupe si hay + de 1 objeto ListaJugadores
			}
			br = new BufferedReader(fr);

			String lineaCSV;
			int lineasQueLeer;

			try {

				lineaCSV = br.readLine();

				if (lineaCSV == null)
					throw new ContenidoNullCSVException(String.format("Informacion irrecuperable en el fichero %s"
							+ "%nEn la primera fila y columna deberia estar la cantidad de jugadores "
							+ "que guarda el fichero, sin embargo el contenido es nulo.", nombreFichero));

				// La primera linea del CSV contiene la cantidad de filas del CSV a leer
				lineasQueLeer = Integer.parseInt(lineaCSV);

			} catch (NumberFormatException e) {
				throw new ContenidoCorruptoCSVException(String.format("Informacion irrecuperable en el fichero %s"
						+ "%nEn la primera fila y columna deberia estar la cantidad de jugadores que guarda el fichero."
						+ "%nSe recomienda correción manual.", nombreFichero));
			}

			for (int i = 0; i < lineasQueLeer; i++) {

				lineaCSV = br.readLine();

				try {
					jugadores.add(new Jugador(lineaCSV));
				} catch (BadCSVException e) {
//					System.err.println(e.getMessage());
					System.err.println();
				}

			} // Fin del for

		} catch (BadCSVException e) {

			System.err.println();
//			System.err.println(e.getMessage());
//			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

//		finally {
//			try {
//				if (fr != null)
//					fr.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}

		return jugadores;
	}

	/**
	 * Elimina, si existen, los csv existentes.
	 */
	public static void borrarDatos() {

		File fichero;

		String ficheroHistorico = "historico.csv";
		fichero = new File(ficheroHistorico);
		eliminarFichero(fichero);

		for (int i = 0; i < 99; i++) {

			String ficheroEnMesa = String.format("jugadoresEnMesa%d.csv", i);
			fichero = new File(ficheroEnMesa);
			eliminarFichero(fichero);

		}

		String ficheroRetirados = "jugadoresRetirados.csv";
		fichero = new File(ficheroRetirados);
		eliminarFichero(fichero);

	}

	/**
	 * Si existe, borra el fichero.
	 * 
	 * @param Fichero fichero a eliminar.
	 */
	private static void eliminarFichero(File fichero) {
		if (fichero.exists())
			fichero.delete();
	}

}
