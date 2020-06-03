package angelidito.laruleta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class Ruleta {

	/**
	 * El último número que ha salido en la Ruleta. Vale -1 al crear el objeto,
	 * salvo en este momento, el rango de valores posibles es de 0 a 36.
	 */
	private int ultimoNumero;

	/**
	 * Vector que almacena las ocurrencias de cada número. En cada posición, desde 0
	 * a 36, el valor de la posición indica el número de ocurrencias del número
	 * correspondiente a esa posición.
	 */
	// private static Integer[] histórico;
	private static final NumeroRuleta[] histórico;

//	/**
//	 * TODO: top diez numeros mas probables
//	 */
//	@SuppressWarnings("unused")
//	private static NumeroRuleta[] topTen;

	private static int totalTiradas = 0;

	/**
	 * Objeto con el que se generán los números.
	 */
	private static final Random random;

	static {
		random = new Random();
		histórico = new NumeroRuleta[37];
		recuperarHistorico();
//		topTen = new NumeroRuleta[10];

		for (NumeroRuleta numero : histórico)
			totalTiradas += numero.getOcurrencias();

	}

	/**
	 * Recupera el histórico.
	 * 
	 */
	private static void recuperarHistorico() {
		setHistórico(GestionCSV.obtenerHistoricoOcurrencias());
	}

	/**
	 * Fija las ocurrencias del histórico a partir del vector dado. Si no tiene el
	 * tamaño deseado las inicializa a 0. Si algun valor es negativo se toma como 0.
	 * 
	 * @param ocurrencias Vector con ocurrencias de cada número.
	 */
	private static void setHistórico(Integer[] ocurrencias) {
		if (Ruleta.histórico.length != ocurrencias.length) {
			for (int i = 0; i < Ruleta.histórico.length; i++)
				Ruleta.histórico[i] = new NumeroRuleta(i);
		}
		else
			for (int i = 0; i < Ruleta.histórico.length; i++)
				if (ocurrencias[i] > 0)
					Ruleta.histórico[i] = new NumeroRuleta(i, ocurrencias[i]);
				else
					Ruleta.histórico[i] = new NumeroRuleta(i);

	}

	// tirar seguidas? quizá eso sea mejor en Crupier Y AHORRAMOS LIOS
	/**
	 * Genera un número aleatório del 0 al 36. Lo devuelve y lo guarda. También lo
	 * anota en el histórico.
	 * 
	 * @return Un número al alzar entre 0 y 36, incluidos.
	 */
	public int lanzar() {

		this.ultimoNumero = Ruleta.random.nextInt(37);

		Ruleta.histórico[ultimoNumero].añadirOcurrencia();
		++Ruleta.totalTiradas;

		return ultimoNumero;
	}

	public static String getEstadisticas() {

		StringBuilder estadisticas = new StringBuilder();
		for (NumeroRuleta numero : histórico) {

			estadisticas.append(String.format("%s; %.3f", numero,
					(float) numero.getOcurrencias() * 100 / Ruleta.totalTiradas)).append("%\n");

		}

		return estadisticas.toString();
	}

	public NumeroRuleta getNumeroRuleta(int n) {

		return Ruleta.histórico[n];

	}

	/**
	 * Guarda el historico en un CSV. Guarda en orden las ocurrencias de cada nº. La
	 * primera fila será 37 (el nº de filas que vendrán despues), las siguientes
	 * contendrán las ocurrencias.
	 */
	public static void guardarHistórico() {

		String nombreFichero = "historico.csv";

		Integer[] ocurrencias = new Integer[Ruleta.histórico.length];
		for (int i = 0; i < ocurrencias.length; i++) {
			ocurrencias[i] = Ruleta.histórico[i].getOcurrencias();
		}

		List<Integer> listaHistorico = Arrays.asList(ocurrencias);

		GestionCSV.escribirCSV(nombreFichero, listaHistorico);

	}

	/**
	 * Crea la Ruleta y restaura el historial de números. Restaura el histórico si
	 * existe el fichero que guarda sus datos.
	 */
	public Ruleta() {

		this.ultimoNumero = 0;

	}
}