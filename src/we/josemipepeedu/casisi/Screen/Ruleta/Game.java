package we.josemipepeedu.casisi.Screen.Ruleta;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.HashMap;

public class Game extends Canvas {
	private static final long serialVersionUID = -6895564443654717344L;
	
	private int width; // Variable que contiene el ancho del panel
	private int height; // variable que contiene el alto del panel
	
	private static HashMap<String, String> objetos = new HashMap<String, String>(); // HashMap que contiene los objetos rendeables

	public Game(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paint(Graphics g) {
	}
}
