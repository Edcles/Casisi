package we.josemipepeedu.casisi.Screen.Blackjack;

import java.awt.image.BufferedImage;

public class Carta {
	private Cartas type;
	private int id;
	private BufferedImage texture;
	public Carta(Cartas type, int id, BufferedImage texture) {
		this.type = type;
		this.id = id;
		this.texture = texture;
	}
	public Cartas getType() {
		return type;
	}
	public int getId() {
		return id;
	}
	public BufferedImage getTexture() {
		return texture;
	}
}
