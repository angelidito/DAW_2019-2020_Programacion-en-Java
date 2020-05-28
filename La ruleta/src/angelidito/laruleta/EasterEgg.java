package angelidito.laruleta;

import java.util.Random;

/**
 * Nadie sabe lo que hace esta clase y ni falta que hace.
 * 
 * @author <a href="https://twitter.com/angelidito">Ángel M. D.</a>
 *
 */
public class EasterEgg {

	public static void huevoDePascua(String nombreDelJugador) {
		Random r = new Random();

		String[] eggs = { "%s pagará lo que debe con sangre... O sea, sudor quiero decir: fregará el suelo...",
				"A %s le acaban de partir las piernas.", "%s está apagado o fuera de cobertura.",
				"El casino ha enviado a %s a un lugar mejor.", "El funeral de %s será  mañana a las 12:00.",
				"%s ya no está entre nosotros...", "%s ha sido acompañado a la salida. De la vida.",
				"Parece que %s ha apostado el cuello. Y lo ha perdido...",
				"Hoy es un buen día para morir, ¿verdad, %s?",
				"Los perros del inferno han arrastrado a %s hasta sus llamas.",
				"%s, esto se te ha ido de las manos.%nAhora tus manos se van a ir de ti. *sonido de cuchillo de carnicero cortando hueso*",
				"No debiste pedir prestado a la mafia, %s.", "Ahora %s tiene un dedo menos. En cada mano y pie.",
				"La policia está investigando la desaparición de %s.", "%s no vio venir la deuda. Tampoco la bala.",
				"Míralo por el lado bueno, %s, ahora puedes aparcar en minusvalidos.",
				"Saludos de mi parte para San Pedro, %s.", "Deuda + casino = paliza para %s. :D",
				"%s ha sido retirado. Se le ha agotado la vida.", "%s ha sido retirado. Se le ha agotado la vida.",
				"%s ha sido retirado. Se le ha agotado la vida.", "%s ha sido retirado. Se le ha agotado la vida.",
				"%s ha sido retirado. Se le ha agotado la vida." };

		int egg = r.nextInt(eggs.length);
		
		System.err.printf("Parece que alguien ha contraido una deuda...%n" + eggs[egg] + "%n%n", nombreDelJugador);

	}

}
