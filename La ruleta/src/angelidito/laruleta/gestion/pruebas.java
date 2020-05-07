package angelidito.laruleta.gestion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import angelidito.laruleta.Jugador;

public class pruebas {

	public static void main(String[] args) {
		File fichero = new File("jugadoresEnMesa0.csv");
	}

	public static void main2(String[] args) {

		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

		jugadores.add(new Jugador());
		jugadores.add(new Jugador());
		jugadores.add(new Jugador());
		jugadores.add(new Jugador());
		jugadores.add(new Jugador());

		System.out.printf("Jugadores en lista:%n");
		for (Jugador jugador : jugadores) {
			System.out.printf("%s%n", jugador);
		}
		String nombreFichero = "jugadoresEnMesa0.csv";
		escribirCSV(nombreFichero, jugadores);

	}

	
	public static <E> void escribirCSV(String nombreFichero, List<E> lista) {
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(nombreFichero, true);
			pw = new PrintWriter(fichero);

			System.out.printf("Escribiendo en el fichero %s%n", nombreFichero);

			System.out.printf("Escribiendo linea: %d%n", lista.size());
			pw.println(lista.size());

			for (E elem : lista) {
				System.out.printf("Escribiendo linea: %s%n", elem);
				pw.println(elem);
			}

		} catch (IOException e) {
			System.err.println("ERROR.\n" + GestionCSV.class);
			e.printStackTrace();
		} finally {
			try {
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
