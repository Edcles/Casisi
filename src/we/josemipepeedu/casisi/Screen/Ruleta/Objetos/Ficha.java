package we.josemipepeedu.casisi.Screen.Ruleta.Objetos;

import java.awt.image.BufferedImage;

import we.josemipepeedu.casisi.Utils.RenderableObject;

public abstract class Ficha extends RenderableObject{
	public Ficha(String id, int x, int y, int with, int height, BufferedImage bufferedImage) {
		super(id, x, y, with, height, bufferedImage);
	}

}
