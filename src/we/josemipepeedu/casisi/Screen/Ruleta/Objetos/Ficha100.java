package we.josemipepeedu.casisi.Screen.Ruleta.Objetos;

import java.awt.image.BufferedImage;

public class Ficha100 extends Ficha {
	private int valor;
	
	public Ficha100(String id, int x, int y, int with, int height, BufferedImage bufferedImage, int valor, Boolean selected) {
		super(id, x, y, with, height, bufferedImage, valor, selected);
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
