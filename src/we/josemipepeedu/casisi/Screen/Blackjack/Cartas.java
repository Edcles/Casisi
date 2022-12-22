package we.josemipepeedu.casisi.Screen.Blackjack;

import java.io.IOException;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Utils.Utils;
//GUARDAMOS EN UN ENUM EL TIPO Y EL NUMERO
public enum Cartas {
	PICAS("pica{id}.png"),
	TREBOL("trebol{id}.png"),
	CORAZONES("corazon{id}.png"),
	ROMBO("rombo{id}.png");
	
	private String url;
		Cartas(String url) {
		this.url = url;
	}
	public String getURL() {
		return url;
	}
	//COGE DEL PAQUETE DE IMAGEN LA IMAGEN DE LA CARTA SEGUN SU ID Y SU TIPO
	public Carta getCarta(int id) throws IOException {
		return new Carta(this, id, ImageIO.read(getClass().getClassLoader().getResource(getURL().replace("{id}", id + "")))); // devuelve el objeto carta con el tipo, la id, y la textura
	}
	//ESCOGEMOS UNA CARTA ALEATORIA
	public static Carta getRandomCarta() throws IOException {
		Cartas randomCarta = Cartas.values()[Utils.random.nextInt(Cartas.values().length)]; // coge un tipo de carta aleatoria
		return randomCarta.getCarta(Utils.random.nextInt(12) + 1);
	}
}
