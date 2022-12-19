package we.josemipepeedu.casisi.Blackjack;

import java.io.IOException;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Utils.Utils;

public enum Cartas {
	PICAS("cartas/pica{id}.png"),
	TREBOL("cartas/trebol{id}.png"),
	CORAZONES("cartas/corazon{id}.png"),
	ROMBO("cartas/rombo{id}.png");
	
	private String url;
	
	Cartas(String url) {
		this.url = url;
	}
	public String getURL() {
		return url;
	}
	public Carta getCarta(int id) throws IOException {
		return new Carta(this, id, ImageIO.read(getClass().getClassLoader().getResource(getURL().replace("{id}", id + "")))); // devuelve el objeto carta con el tipo, la id, y la textura
	}
	public static Carta getRandomCarta() throws IOException {
		Cartas randomCarta = Cartas.values()[Utils.random.nextInt(Cartas.values().length)]; // coge un tipo de carta aleatoria
		return randomCarta.getCarta(Utils.random.nextInt(12) + 1);
	}
}
