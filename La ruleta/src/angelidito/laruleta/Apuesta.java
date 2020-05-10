package angelidito.laruleta;

import java.util.Arrays;
import java.util.Iterator;

import angelidito.escaner.Escaner;
import angelidito.escaner.TipoEntero;
import angelidito.laruleta.excepciones.BadCSVException;
import angelidito.laruleta.excepciones.BadProgramingRTException;
import angelidito.laruleta.excepciones.CantidadCamposCSVException;
import angelidito.laruleta.excepciones.ContenidoNullCSVException;

/**
 * 
 * 
 * @author <a href="twitter.com/angelidito">Ángel M. D.</a>
 */
public class Apuesta {

	// *************************************************
	// ************* Atributos de la clase *************
	// *************************************************

	/**
	 * Vector de tamaño 37 con las apuestas los números. La posición es el número al
	 * que se apuesta, y el valor de la posición la cantidad apostada.
	 */
	private NumeroApuesta[] numeros = new NumeroApuesta[37];

	/**
	 * Vector de tamaño 2 con las apuestas a par e impar. El valor de cada posición
	 * es la cantidad apostada a par e impar, respectivamente.
	 */
	private int[] parImpar = new int[2];

	/**
	 * Vector de tamaño 2 con las apuestas a rojo y negro. El valor de cada posición
	 * es la cantidad apostada a rojo y negro, respectivamente.
	 */
	private int[] rojoNegro = new int[2];

	/**
	 * Vector de tamaño 2 con las apuestas a la primera y segunda mitad. El valor de
	 * cada posición es la cantidad apostada a la primera y segunda mitad,
	 * respectivamente.
	 */
	private int[] mitades = new int[2];

	/**
	 * Vector de tamaño 3 con las apuestas a las docenas. El valor de cada posición
	 * es la cantidad apostada a la primera, segunda y tercera docena,
	 * respectivamente.
	 */
	private int[] docenas = new int[3];

	/**
	 * Vector de tamaño 3 con las apuestas a las filas 2 a 1. El valor de cada
	 * posición es la cantidad apostada a las filas de 2 a 1 con los números con
	 * módulo 3 igual a 1, 2 y 0, respectivamente.
	 */
	private int[] modulos = new int[3];

	/**
	 * Contador de objetos Apuesta creados. No tiene utilidad por el momento.
	 */
	@SuppressWarnings("unused")
	private static int numApuestas;

	static {
		numApuestas = 0;
	}

	// *************************************************
	// ********** Constructores y toString() **********
	// *************************************************

	/**
	 * Se inicia con todas las apuestas a cero. Aumenta el contador numApuestas.
	 */
	public Apuesta() {
		setApuestasTo0();
		numApuestas++;
	}

	/**
	 * Crea el objeto con los parametros introducidos. Cuando alguno de estos no
	 * tenga la dimensión acertada, fijará a cero esa parte de la apuesta. Si en
	 * algún vector alguna cantidad a apostar es negativa, se interpretará como 0.
	 * Aumenta el contador numApuestas.
	 * 
	 * @param numeros   Vector de tamaño 37 con las apuestas los números. La
	 *                  posición es el número al que se apuesta, y el valor de la
	 *                  posición la cantidad apostada.
	 * @param parImpar  Vector de tamaño 2 con las apuestas a par e impar. El valor
	 *                  de cada posición es la cantidad apostada a par e impar,
	 *                  respectivamente.
	 * @param rojoNegro Vector de tamaño 2 con las apuestas a rojo y negro. El valor
	 *                  de cada posición es la cantidad apostada a rojo y negro,
	 *                  respectivamente.
	 * @param mitades   Vector de tamaño 2 con las apuestas a la primera y segunda
	 *                  mitad. El valor de cada posición es la cantidad apostada a
	 *                  la primera y segunda mitad, respectivamente.
	 * @param docenas   Vector de tamaño 3 con las apuestas a las filas 2 a 1. El
	 *                  valor de cada posición es la cantidad apostada a las filas 2
	 *                  a 1 con modulo 3 igual a 1, 2 y 0, respectivamente.
	 * @param modulos   Vector de tamaño 3 con las apuestas a las filas 2 a 1. El
	 *                  valor de cada posición es la cantidad apostada a las filas 2
	 *                  a 1 con modulo 3 igual a 1, 2 y 0, respectivamente.
	 */
	public Apuesta(int[] numeros, int[] parImpar, int[] rojoNegro, int[] mitades, int[] docenas, int[] modulos) {

		this.setNumeros(numeros);

		this.setParImpar(parImpar);

		this.setRojoNegro(rojoNegro);

		this.setMitades(mitades);

		this.setDocenas(docenas);

		this.setModulos(modulos);

		numApuestas++;

	}

	/**
	 * Recupera la Apuesta del CSV. Crea el objeto con los vectores que sacará del
	 * parámetro. Cuando alguno estos no tenga la dimensión acertada, fijará a cero
	 * esa parte de la apuesta. Si en algún vector alguna cantidad a apostar es
	 * negativa, esta se interpretará como 0. Si al dividir el parámetro por el
	 * signo almohadilla, "#", el tamaño del vector resultante no es de longitud 6,
	 * la apuesta será iniciada como el constructor por defecto: a cero. Aumenta el
	 * contador numApuestas.
	 * 
	 * @param ultimaColumnaDelCSV La úlima columna del CSV que debería contener el
	 *                            texto creado por el método toFormatoCSV().
	 * @throws CantidadCamposCSVException Cuando la cantidad de campos
	 */
	public Apuesta(String ultimaColumnaDelCSV) throws BadCSVException {

		// Esto o deberia pasar por cómo está programado el método que lee las lineas
		// de los ficheros, pero por si acaso
		if (ultimaColumnaDelCSV == null)
			throw new ContenidoNullCSVException("Contenido nulo al recuperar la acpuesta:");

		String[] vectores = ultimaColumnaDelCSV.split("#");
		// Si coincide la longitud, bien; si no, todo a 0.
		if (vectores.length != 6)
			throw new CantidadCamposCSVException("No hay campos suficientes para recuperar la puesta:");

		// Vectores contiene:
		// numeros, parImpar, rojoNegro,
		// mitades, decenas y modulos; en ese orden,
		String[] cantidadesANumerosStr = vectores[0].split(":");
		String[] parImparStr = vectores[1].split(":");
		String[] rojoNegroStr = vectores[2].split(":");
		String[] mitadesStr = vectores[3].split(":");
		String[] docenasStr = vectores[4].split(":");
		String[] modulosStr = vectores[5].split(":");

		// Numeros
		// Si coincide la longitud, bien; si no, todo a 0.
		// Esta estructura se repite en los siguientes if-else
		if (this.numeros.length != cantidadesANumerosStr.length)
			this.setNumerosTo0();
		else
			this.setNumeros(transformarVector(cantidadesANumerosStr));

		// ParImpar
		if (this.parImpar.length != parImparStr.length)
			this.setParImparTo0();
		else
			this.setParImpar(transformarVector(parImparStr));

		// RojoNegro
		if (this.rojoNegro.length != rojoNegroStr.length)
			this.setRojoNegroTo0();
		else
			this.setRojoNegro(transformarVector(rojoNegroStr));

		// Mitades
		if (this.mitades.length != mitadesStr.length)
			this.setMitadesTo0();
		else
			this.setMitades(transformarVector(mitadesStr));

		// Docenas
		if (this.docenas.length != docenasStr.length)
			this.setDocenasTo0();
		else
			this.setDocenas(transformarVector(docenasStr));

		// Modulos
		if (this.modulos.length != modulosStr.length)
			this.setModulosTo0();
		else
			this.setModulos(transformarVector(modulosStr));

		numApuestas++;

	}

	// *************************************************
	// ************** Métodos de la clase **************
	// *************************************************

	/**
	 * Transforma un vector de tipo String a uno de enteros. El contenido del vector
	 * debe de ser analizable como números enteros, en caso de no ser así, devuelve
	 * un vector del mismo tamaño inicializado a 0.
	 * 
	 * @param vectorDeNumeros
	 * @return
	 */
	private int[] transformarVector(String[] vectorDeNumeros) {

		int[] vectorRecuperado = new int[vectorDeNumeros.length];

		try {
			for (int i = 0; i < vectorDeNumeros.length; i++)
				vectorRecuperado[i] = Integer.parseInt(vectorDeNumeros[i]);
		} catch (NumberFormatException e) {
			vectorRecuperado = new int[vectorDeNumeros.length];
		}

		return vectorRecuperado;
	}

	/**
	 * 
	 * @return Vector de tamaño 37 con las cantidades apostadas a cada número.
	 */
	private int[] numerosToCantidades() {
		int[] cantidadesApostadas = new int[this.numeros.length];

		for (int i = 0; i < cantidadesApostadas.length; i++) {
			cantidadesApostadas[i] = this.numeros[i].getCantidadApostada();
		}
		return cantidadesApostadas;
	}

	/**
	 * Clona la apuesta.
	 * 
	 * @return Una Apuesta similar
	 */
	public Apuesta clonar() {
		Apuesta apuesta;

		apuesta = new Apuesta(numerosToCantidades(), this.parImpar, this.rojoNegro, this.mitades, this.docenas,
				this.modulos);

		return apuesta;
	}

	/**
	 * Devuelve la apuesta en formato para CSV. Para poder recuperarla al iniciar de
	 * nuevo el programa. En el CSV irá en una misma celda. Guarda los vectores
	 * numeros, parImpar, rojoNegro, mitades, decenas y modulos; en ese orden,
	 * separados por el singo de almohadilla, "#". Las posiciones de cada vector se
	 * separarán por el signo de dos puntos, ":".
	 * 
	 * @return La apuesta en texto con formato CSV.
	 */
	@Override
	public String toString() {

		String enFormato = "";

		enFormato += numeros[0];
		for (int i = 1; i < numeros.length; i++)
			enFormato += ":" + numeros[i].getCantidadApostada();

		enFormato += "#";

		enFormato += parImpar[0];
		for (int i = 1; i < parImpar.length; i++)
			enFormato += ":" + parImpar[i];

		enFormato += "#";

		enFormato += rojoNegro[0];
		for (int i = 1; i < rojoNegro.length; i++)
			enFormato += ":" + rojoNegro[i];

		enFormato += "#";

		enFormato += mitades[0];
		for (int i = 1; i < mitades.length; i++)
			enFormato += ":" + mitades[i];

		enFormato += "#";

		enFormato += docenas[0];
		for (int i = 1; i < docenas.length; i++)
			enFormato += ":" + docenas[i];

		enFormato += "#";

		enFormato += modulos[0];
		for (int i = 1; i < modulos.length; i++)
			enFormato += ":" + modulos[i];

		return enFormato;
	}

	/**
	 * Devuelve la informacion de lo apostado. Si no hay apostado nada, devuelve in
	 * texto informando de ello.
	 * 
	 * @return La información de lo apostado.
	 */
	public String info() {
		String info = "";
		int totalAnotado = 0;

		for (NumeroApuesta numero : this.numeros)
			if (numero.getCantidadApostada() > 0) {
				info += String.format("%d al nº%d. ", numero.getCantidadApostada(), numero.getN());
				totalAnotado += numero.getCantidadApostada();
			}

		if (totalAnotado < this.totalApostado())
			if (this.parImpar[0] > 0) {
				info += String.format("%d a pares. ", this.parImpar[0]);
				totalAnotado += this.parImpar[0];
			}

		if (totalAnotado < this.totalApostado())
			if (this.parImpar[1] > 0) {
				info += String.format("%d a impares. ", this.parImpar[1]);
				totalAnotado += this.parImpar[1];
			}

		if (totalAnotado < this.totalApostado())
			if (this.rojoNegro[0] > 0) {
				info += String.format("%d al rojo. ", this.rojoNegro[0]);
				totalAnotado += this.rojoNegro[0];
			}

		if (totalAnotado < this.totalApostado())
			if (this.rojoNegro[1] > 0) {
				info += String.format("%d al negro. ", this.rojoNegro[1]);
				totalAnotado += this.rojoNegro[1];
			}

		if (totalAnotado < this.totalApostado())
			if (this.mitades[0] > 0) {
				info += String.format("%d a la primera mitad. ", this.mitades[0]);
				totalAnotado += this.mitades[0];
			}

		if (totalAnotado < this.totalApostado())
			if (this.mitades[1] > 0) {
				info += String.format("%d a la segunda mitad. ", this.mitades[1]);

			}

		if (totalAnotado < this.totalApostado())
			if (this.docenas[0] > 0) {
				info += String.format("%d a la primera docena. ", this.docenas[0]);
				totalAnotado += this.docenas[0];
			}

		if (totalAnotado < this.totalApostado())
			if (this.docenas[1] > 0) {
				info += String.format("%d a la segunda docena. ", this.docenas[1]);
				totalAnotado += this.docenas[1];
			}

		if (totalAnotado < this.totalApostado())
			if (this.docenas[2] > 0) {
				info += String.format("%d a la tercera docena. ", this.docenas[2]);
				totalAnotado += this.docenas[2];
			}

		if (totalAnotado < this.totalApostado())
			if (this.modulos[1] > 0) {
				info += String.format("%d a la primera fila 2 a 1. ", this.modulos[1]);
				totalAnotado += this.modulos[1];
			}

		if (totalAnotado < this.totalApostado())
			if (this.modulos[2] > 0) {
				info += String.format("%d a la segunda fila 2 a 1. ", this.modulos[2]);
				totalAnotado += this.modulos[2];
			}

		if (totalAnotado < this.totalApostado())
			if (this.modulos[0] > 0) {
				info += String.format("%d a la tercera fila 2 a 1. ", this.modulos[0]);
				totalAnotado += this.modulos[0];
			}

		if (totalAnotado == 0)
			info = "ninguna";

		if (totalAnotado != this.totalApostado())
			throw new BadProgramingRTException("ERROR DE CONTEO EN Apuesta.info()");

		return info;
	}

	/**
	 * Devuelve la informacion completa de la puesta. Va por arrays. Método más útil
	 * para pruebas y desarrollo qu epara la apliación final. Se recomienda usar el
	 * método {@code info()}.
	 * 
	 * @return Toda la información de la apuesta.
	 */
	public String informacion() {
		int[] cantidades = new int[numeros.length];

		for (int i = 0; i < cantidades.length; i++) {
			cantidades[i] = numeros[i].getCantidadApostada();
		}

		String informacion = "***    numeros   = " + Arrays.toString(cantidades) + "\n***    parImpar  = "
				+ Arrays.toString(parImpar) + "\n***    rojoNegro = " + Arrays.toString(rojoNegro) + "\n***    mitades   = "
				+ Arrays.toString(mitades) + "\n***    docenas   = " + Arrays.toString(docenas) + "\n***    modulos   = "
				+ Arrays.toString(modulos);
		return informacion;
	}

	// ****** Métodos para ajustar las apuestas ********
	// ************ sin andar con arrays ***************

	/**
	 * Devuelve la suma total del crédito apostado.
	 * 
	 * @return El crédito total apostado
	 */
	public int totalApostado() {
		int total = 0;
		for (NumeroApuesta apuesta : numeros)
			total += apuesta.getCantidadApostada();
		for (int apuesta : parImpar)
			total += apuesta;
		for (int apuesta : rojoNegro)
			total += apuesta;
		for (int apuesta : mitades)
			total += apuesta;
		for (int apuesta : docenas)
			total += apuesta;
		for (int apuesta : modulos)
			total += apuesta;
		return total;
	}

	/**
	 * Apuesta a un número. Si la cantidad a apostar es negativa, no se apostará
	 * nada.
	 * 
	 * @param numero   Número al que se apuesta.
	 * @param cantidad Cantidad que se apuesta.
	 */
	public void apostarNumero(int numero, int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);

		numeros[numero].setCantidadApostada(cantidad);
	}

	/**
	 * Apuesta a par.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarPares(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		parImpar[0] = cantidad;
	}

	/**
	 * Apuesta a impar. Si la cantidad a apostar es negativa, no se apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarImpares(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		parImpar[1] = cantidad;
	}

	/**
	 * Apuesta al rojo. Si la cantidad a apostar es negativa, no se apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarRojo(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		rojoNegro[0] = cantidad;
	}

	/**
	 * Apuesta a negro. Si la cantidad a apostar es negativa, no se apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarNegro(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		rojoNegro[1] = cantidad;
	}

	/**
	 * Apuesta a la primera mitad. Si la cantidad a apostar es negativa, no se
	 * apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarPrimeraMitad(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		mitades[0] = cantidad;
	}

	/**
	 * Apuesta a a la segunda mitad. Si la cantidad a apostar es negativa, no se
	 * apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarSegundaMitad(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		mitades[1] = cantidad;
	}

	/**
	 * Apuesta a la primera docena. Si la cantidad a apostar es negativa, no se
	 * apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarPrimeraDocena(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		docenas[0] = cantidad;
	}

	/**
	 * Apuesta a la segunda docena. Si la cantidad a apostar es negativa, no se
	 * apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarSegundaDocena(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		docenas[1] = cantidad;
	}

	/**
	 * Apuesta a la tercera docena. Si la cantidad a apostar es negativa, no se
	 * apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarTerceraDocena(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		docenas[2] = cantidad;
	}

	/**
	 * Apuesta a la fila 2 a 1 que tiene los numero cuyo mólulo 3 es 1. Si la
	 * cantidad a apostar es negativa, no se apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarModulo1(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		modulos[0] = cantidad;
	}

	/**
	 * Apuesta a la fila 2 a 1 que tiene los numero cuyo mólulo 3 es 2. Si la
	 * cantidad a apostar es negativa, no se apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarModulo2(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		modulos[1] = cantidad;
	}

	/**
	 * Apuesta a la fila 2 a 1 que tiene los numero cuyo mólulo 3 es 0. Si la
	 * cantidad a apostar es negativa, no se apostará nada.
	 * 
	 * @param cantidad Cantidad a apostar.
	 */
	public void apostarModulo0(int cantidad) {
		cantidad = asegurarCantidadNoNegativa(cantidad);
		modulos[2] = cantidad;
	}

	// ****** Métodos para eliminar las apuestas *******

	/**
	 * Elimina todas las apuestas.
	 */
	public void setApuestasTo0() {
		setNumerosTo0();
		setParImparTo0();
		setRojoNegroTo0();
		setMitadesTo0();
		setDocenasTo0();
		setModulosTo0();
	}

	/**
	 * Elimina las apuestas a los números.
	 */
	public void setNumerosTo0() {

		for (int i = 0; i < numeros.length; i++) {
			if (numeros[i] == null || numeros[i].getN() != i)
				numeros[i] = new NumeroApuesta(i);
			else
				numeros[i].setCantidadApostada(0);
		}
	}

	/**
	 * Elimina las apuestas a pares e impares.
	 */
	public void setParImparTo0() {
		for (@SuppressWarnings("unused")
		int elem : parImpar) {
			elem = 0;
		}
	}

	/**
	 * Elimina las apuestas a rojo y negro.
	 */
	public void setRojoNegroTo0() {
		for (@SuppressWarnings("unused")
		int elem : rojoNegro) {
			elem = 0;
		}
	}

	/**
	 * Elimina las apuestas a las mitades.
	 */
	public void setMitadesTo0() {
		for (@SuppressWarnings("unused")
		int elem : mitades) {
			elem = 0;
		}
	}

	/**
	 * Elimina las apuestas a las docenas.
	 */
	public void setDocenasTo0() {
		for (@SuppressWarnings("unused")
		int elem : docenas) {
			elem = 0;
		}
	}

	/**
	 * Elimina las apuestas a los módulos.
	 */
	public void setModulosTo0() {
		for (@SuppressWarnings("unused")
		int elem : modulos) {
			elem = 0;
		}
	}

	// *************************************************
	// *************** Getters & Setters ***************
	// *************************************************

	/**
	 * Duleve las a puestas a los numeros.
	 * 
	 * @return El vector numeros.
	 */
	public NumeroApuesta[] getNumeros() {
		return numeros;
	}

	/**
	 * Fija el vector de las apuestas a los números. Si y sólo si este tiene el
	 * tamaño acertado: 37; en caso de no tener este tamaño fija estas apuestas a 0.
	 * Si en el vector alguna cantidad a apostar es negativa, se interpretará como
	 * 0.
	 * 
	 * @param cantidades Vector con la apuesta a cada número.
	 */
	public void setNumeros(int[] cantidades) {

		if (cantidades.length == this.numeros.length) {
			cantidades = asegurarCantidadNoNegativa(cantidades);

			for (int i = 0; i < numeros.length; i++) {
				if (this.numeros[i] == null || numeros[i].getN() != i)
					this.numeros[i] = new NumeroApuesta(i, cantidades[i]);
				else
					this.numeros[i].setCantidadApostada(cantidades[i]);
			}

		} else
			this.setNumerosTo0();
	}

	/**
	 * Duleve las a puestas a pares e impares.
	 * 
	 * @return El vector parImpar.
	 */
	public int[] getParImpar() {
		return parImpar;
	}

	/**
	 * Fija el vector de las apuestas a par e impar. Si y sólo si este tiene el
	 * tamaño acertado: 2; en caso de no tener este tamaño fija estas apuestas a 0.
	 * Si en el vector alguna cantidad a apostar es negativa, se interpretará como
	 * 0.
	 * 
	 * @param parImpar el vector parImpar a fijar.
	 */
	public void setParImpar(int[] parImpar) {
		if (parImpar.length == this.parImpar.length) {

			this.parImpar = asegurarCantidadNoNegativa(parImpar);

		} else
			this.setParImparTo0();
	}

	/**
	 * Devuelve las apuestas a rojo y negro.
	 * 
	 * @return El vector rojoNegro.
	 */
	public int[] getRojoNegro() {
		return rojoNegro;
	}

	/**
	 * Fija el vector de las apuestas a rojo y negro. Si y sólo si este tiene el
	 * tamaño acertado: 2; en caso de no tener este tamaño fija estas apuestas a 0.
	 * Si en el vector alguna cantidad a apostar es negativa, se interpretará como
	 * 0.
	 * 
	 * @param rojoNegro el vector rojoNegro a fijar.
	 */
	public void setRojoNegro(int[] rojoNegro) {
		if (rojoNegro.length == this.rojoNegro.length) {

			this.rojoNegro = asegurarCantidadNoNegativa(rojoNegro);

		} else
			this.setRojoNegroTo0();
	}

	/**
	 * Devuelve las apuestas a las mitades.
	 * 
	 * @return El vector mitades.
	 */
	public int[] getMitades() {
		return mitades;
	}

	/**
	 * Fija el vector de las apuestas a los mitades. Si y sólo si este tiene el
	 * tamaño. acertado: 2; en caso de no tener este tamaño fija estas apuestas a 0.
	 * Si en el vector alguna cantidad a apostar es negativa, se interpretará como
	 * 0.
	 * 
	 * @param mitades el vector mitades a fijar.
	 */
	public void setMitades(int[] mitades) {
		if (mitades.length == this.mitades.length) {

			this.mitades = asegurarCantidadNoNegativa(mitades);

		} else
			this.setMitadesTo0();
	}

	/**
	 * Devuelve las apuestas a las docenas.
	 * 
	 * @return El vector docenas.
	 */
	public int[] getDocenas() {
		return docenas;
	}

	/**
	 * Fija el vector de las apuestas a las docenas. Si y sólo si este tiene el
	 * tamaño acertado: 3; en caso de no tener este tamaño fija estas apuestas a 0.
	 * Si en el vector alguna cantidad a apostar es negativa, se interpretará como
	 * 0.
	 * 
	 * @param docenas el vector docenas a fijar.
	 */
	public void setDocenas(int[] docenas) {
		if (docenas.length == this.docenas.length) {

			this.docenas = asegurarCantidadNoNegativa(docenas);

		} else
			this.setDocenasTo0();
	}

	/**
	 * Devuelve las apuestas a los módulos.
	 * 
	 * @return El vector modulos.
	 */
	public int[] getModulos() {
		return modulos;
	}

	/**
	 * Fija el vector de las apuestas a los módulos. Si y sólo si este tiene el
	 * tamaño acertado: 3; en caso de no tener este tamaño fija estas apuestas a 0.
	 * Si en el vector alguna cantidad a apostar es negativa, se interpretará como
	 * 0.
	 * 
	 * @param modulos el vector módulos a fijar.
	 */
	public void setModulos(int[] modulos) {
		if (modulos.length == this.modulos.length) {

			this.modulos = asegurarCantidadNoNegativa(modulos);

		} else
			this.setModulosTo0();
	}

	// ****** Métodos privados *******

	/**
	 * Asegura que la cantidad a apostar no sea negativa. Si el valor es negativo,
	 * devuelve 0; en caso contrario no altera el valor.
	 * 
	 * @param cantidad Valor que no se desea que sea menor que 0.
	 * @return El mismo valor del parámetro cantidad si era mayor que 0, en caso
	 *         contrario devuelve 0.
	 */
	private int asegurarCantidadNoNegativa(int cantidad) {
		if (cantidad < 0)
			cantidad = 0;
		return cantidad;
	}

	/**
	 * Asegura que las cantidades a apostar no sean negativa. Si algun valor del
	 * vector es negativo, lo vuelve 0; en caso contrario no lo altera.
	 * 
	 * 
	 * @param cantidades Vector de valores que no se desea que sean menores que 0.
	 * @return Un vector con que contendrá en cada posicion el valor que tenía si
	 *         este era mayor que 0 y si era menor, contendrá el valor 0.
	 */
	private int[] asegurarCantidadNoNegativa(int[] cantidades) {
		for (int cantidad : cantidades)
			cantidad = this.asegurarCantidadNoNegativa(cantidad);
		return cantidades;
	}

	/**
	 * Modifica la apuesta por teclado. El total apostado ({@code totalApostado()})
	 * no puede ser mayor que {code creditoDisponible}
	 * 
	 * @param creditoDisponible Credito máximo que se puede apostar
	 * @param op                1, 2, 3, 4, 5 o 6, según lo que se quiera editar.
	 */
	public void editarApuesta(int creditoDisponible, int op) {

		int opcion = 0;
		int cantidad = 0;

		switch (op) {

		case 1:

			System.out.println("  Indique a que numero deséa apostar: ");
			int numero = Escaner.entero(0, 36);

			cantidad = pedirCantidad();

			if ((this.totalApostado() + cantidad) > creditoDisponible)
				System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

			else
				this.apostarNumero(numero, cantidad);

			break;

		case 2:

			do {

				System.out.println("  Indique a qué desea apostar:");
				System.out.println("1 - Pares");
				System.out.println("2 - Impares");
				opcion = Escaner.entero(1, 2);

				switch (opcion) {
				case 1:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarPares(cantidad);

					break;

				case 2:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarImpares(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 3:

			do {

				System.out.println("  Indique a qué desea apostar:");
				System.out.println("1 - Rojo");
				System.out.println("2 - Negro");
				opcion = Escaner.entero(1, 2);

				switch (opcion) {
				case 1:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarRojo(cantidad);

					break;

				case 2:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarNegro(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 4:

			do {

				System.out.println("  Indique a qué mitad desea apostar:");
				System.out.println("1 - Del 1 al 18");
				System.out.println("2 - Del 19 al 36");
				opcion = Escaner.entero(1, 2);

				switch (opcion) {
				case 1:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarPrimeraMitad(cantidad);

					break;

				case 2:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarSegundaMitad(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 5:

			do {

				System.out.println("  Indique a qué docena desea apostar:");
				System.out.println("1 - Primera");
				System.out.println("2 - Segunda");
				System.out.println("3 - Tercera");
				opcion = Escaner.entero(1, 3);

				switch (opcion) {
				case 1:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarPrimeraDocena(cantidad);

					break;

				case 2:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarSegundaDocena(cantidad);

					break;

				case 3:

					cantidad = this.pedirCantidad();

					if ((this.totalApostado() + cantidad) > creditoDisponible)
						System.out.println("El jugador no dispone de suficiente crédito para realizar esta apuesta.");

					else
						this.apostarTerceraDocena(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 6:

			do {

				System.out.println("  Indique a qué fila 2 a 1 desea apostar:");
				System.out.println("1 - La fila del 1");
				System.out.println("2 - La fila del 2");
				System.out.println("3 - La fila del 3");
				opcion = Escaner.entero(1, 3);

				switch (opcion) {
				case 1:

					cantidad = this.pedirCantidad();
					this.apostarModulo1(cantidad);

					break;

				case 2:

					cantidad = this.pedirCantidad();
					this.apostarModulo2(cantidad);

					break;

				case 3:

					cantidad = this.pedirCantidad();
					this.apostarModulo0(cantidad);

					break;

				default:
					Escaner.avisoOpcionIncorrecta();
				}
			} while (opcion == 0);

			break;

		case 7:

			this.setApuestasTo0();

			break;

		case 0:
			// Salir
			break;

		default:
			Escaner.avisoOpcionIncorrecta();
		}
	}

	/**
	 * Pregunta y escanea la cantidad a apostar. Sólo admite enteros positivos.
	 * 
	 * @returns La cantidad introducida.
	 */
	private int pedirCantidad() {
		TipoEntero positivo = TipoEntero.POSITIVO;

		System.out.println("¿Qúe cantidad desea apostar?");
		int cantidad = Escaner.entero(positivo);

		return cantidad;

	}
}
