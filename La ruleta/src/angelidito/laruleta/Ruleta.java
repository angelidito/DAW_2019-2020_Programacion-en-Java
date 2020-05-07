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
	 * Objeto con el que se generán los números.
	 */
	private static Random random;

	static {
		historico = new NumeroRuleta[37];
		recuperarHistorico();
		random = new Random();
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
	 * Fija las ocurrencias del histórico a partir del vector dado. Si no tiene el tamaño deseado las 
	 * inicializa a 0. Si algun valor es negativo se toma como 0.
	 * 
	 * @param ocurrencias Vector con ocurrencias de cada número.
	 */
	private static void setHistorico(Integer[] ocurrencias) {
		if (Ruleta.historico.length != ocurrencias.length)
			for (int i = 0; i < Ruleta.historico.length; i++)
				Ruleta.historico[i]= new NumeroRuleta(i);
		else
			for (int i = 0; i < Ruleta.historico.length; i++)
				if(ocurrencias[i] < 0)
					Ruleta.historico[i]= new NumeroRuleta(i, ocurrencias[i]);
				else
					Ruleta.historico[i]= new NumeroRuleta(i);
				
	}

	// tirar seguidas? quizá eso sea mejor en Crupier Y AHORRAMOS LIOS
	/**
	 * Genera un número aleatório del 0 al 36. Lo devuelve y lo guarda. También lo
	 * anota en el histórico.
	 * 
	 * @return Un número al alzar entre 0 y 36, incluidos.
	 */
	public int girar() {

		ultimoNumero = random.nextInt(37);

		historico[ultimoNumero].añadirOcurrencia();

		return ultimoNumero;
	}



	/**
	 * Devuelve el historial de ocurrencias.
	 * 
	 * @return El histórico
	 */
	public static NumeroRuleta[] getHistorico() {
		return historico;
	}

	/**
	 * Crea la Ruleta y restaura el historial de números. Restaura el histórico si
	 * existe el fichero que guarda sus datos.
	 */
	public Ruleta() {

		this.ultimoNumero = -1;

	}

	/**
	 * Guarda el historico en un CSV.
	 */
	public static void guardarHisorico() {

		String nombreFichero = "historico.csv";
		
		Integer[] ocurrencias = new Integer[Ruleta.historico.length];
		for (int i = 0; i < ocurrencias.length; i++) {
			ocurrencias[i]= Ruleta.historico[i].getOcurrencias();
		}

		List<Integer> listaHistorico = Arrays.asList(ocurrencias);

		GestionCSV.escribirCSV(nombreFichero, listaHistorico);

	}

	// Cambio de planes. Codigo CADUCADO. en vez de todo en una linea,
	// irá cada cada elemento de historico en una fila.
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