package angelidito.laruleta;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import angelidito.laruleta.gestion.GestionCSV;

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
	// private static Integer[] historico;
	private static NumeroRuleta[] historico;

	/**
	 * No implementado: TODO top diez numeros mas probables
	 */
	@SuppressWarnings("unused")
	private static NumeroRuleta[] topTen;

	private static int totalTiradas = 0;

	/**
	 * Objeto con el que se generán los números.
	 */
	private static Random random;

	static {
		random = new Random();
		historico = new NumeroRuleta[37];
		recuperarHistorico();
		topTen = new NumeroRuleta[10];

		for (NumeroRuleta numero : historico)
			totalTiradas += numero.getOcurrencias();

	}

	/**
	 * Fija el histórico a partir del Fichero dado. Si no tiene el formato deseado
	 * lo inicializa a 0. Si algun valor es negativo se toma como 0.
	 * 
	 * @param historico Vector con ocurrencias cada número.
	 */
	private static void recuperarHistorico() {
		setHistorico(GestionCSV.obtenerHistoricoOcurrencias());
	}

	/**
	 * Fija las ocurrencias del histórico a partir del vector dado. Si no tiene el
	 * tamaño deseado las inicializa a 0. Si algun valor es negativo se toma como 0.
	 * 
	 * @param ocurrencias Vector con ocurrencias de cada número.
	 */
	private static void setHistorico(Integer[] ocurrencias) {
		if (Ruleta.historico.length != ocurrencias.length)
			for (int i = 0; i < Ruleta.historico.length; i++)
				Ruleta.historico[i] = new NumeroRuleta(i);
		else
			for (int i = 0; i < Ruleta.historico.length; i++)
				if (ocurrencias[i] < 0)
					Ruleta.historico[i] = new NumeroRuleta(i, ocurrencias[i]);
				else
					Ruleta.historico[i] = new NumeroRuleta(i);

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

		Ruleta.historico[ultimoNumero].añadirOcurrencia();
		++Ruleta.totalTiradas;

		return ultimoNumero;
	}

	public static String estadisticas() {

		String estadisticas = "";
		for (NumeroRuleta numero : historico) {
			estadisticas += String.format("Nº%d: %d ocurrencias; %.4f‰.%n", numero.getN(), numero.getOcurrencias(),
					(float) numero.getOcurrencias() * 1000 / Ruleta.totalTiradas);
		}

		return estadisticas;
	}

	/**
	 * Devuelve el historial de ocurrencias.
	 * 
	 * @return El histórico
	 */
	public static NumeroRuleta[] getHistorico() {
		return historico;
	}

	public NumeroRuleta getNumeroRuleta(int n) {

		return Ruleta.historico[n];

	}

	/**
	 * Devuelve el total de tiradas.
	 * 
	 * @return El total de tiradas.
	 */
	public static int getTotalTiradas() {
		return totalTiradas;
	}

	/**
	 * Guarda el historico en un CSV. Guarda en orden las ocurrencias de cada nº. La
	 * primera fila será 37 (el nº de filas que vendrán despues), las siguientes
	 * contendrán las ocurrencias.
	 */
	public static void guardarHisorico() {

		String nombreFichero = "historico.csv";

		Integer[] ocurrencias = new Integer[Ruleta.historico.length];
		for (int i = 0; i < ocurrencias.length; i++) {
			ocurrencias[i] = Ruleta.historico[i].getOcurrencias();
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

	// Cambio de planes. Codigo CADUCADO. en vez de todo en una linea,
	// irá cada cada ocurrencia de historico en una fila.
//	/**
//	 * Devuelve en formato CSV el histórico de la ruleta.
//	 * 
//	 * @return Un texto con formato CSV con el histórico.
//	 */
//	private static String historicoToFormatoCSV() {
//
//		String historicoEnFormatoCSV = "";
//
//		historicoEnFormatoCSV += historico[0];
//
//		for (int i = 1; i < historico.length; i++)
//			historicoEnFormatoCSV += ";" + historico[i];
//
//		return historicoEnFormatoCSV;
//	}

}