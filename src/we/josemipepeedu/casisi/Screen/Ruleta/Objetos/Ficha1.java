package we.josemipepeedu.casisi.Screen.Ruleta.Objetos;

import java.awt.image.BufferedImage;

public class Ficha1 extends Ficha {
	private int valor;
	public Ficha1(String id, int x, int y, int with, int height, BufferedImage bufferedImage, int valor, Boolean selected) {
		super(id, x, y, with, height, bufferedImage, valor, selected);
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
