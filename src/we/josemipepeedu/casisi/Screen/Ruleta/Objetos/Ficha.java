package we.josemipepeedu.casisi.Screen.Ruleta.Objetos;

import java.awt.image.BufferedImage;

import we.josemipepeedu.casisi.Utils.RenderableObject;

public class Ficha extends RenderableObject{
	private int valor;
	public Ficha(String id, int x, int y, int with, int height, BufferedImage bufferedImage, int valor, Boolean selected) {
		super(id, x, y, with, height, bufferedImage, selected);
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
