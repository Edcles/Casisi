package we.josemipepeedu.casisi.Utils;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Utils {
	public static Random random = new Random();
	public static BufferedImage selectedFondo;
	public static BufferedImage fondoClaro;
	public static BufferedImage fondoOscuro;
	public static boolean isTouch(Point mouse, int x, int y, int dimX, int dimY) {
		if (mouse.getX() >= x && mouse.getX() <= x + dimX && mouse.getY() >= y && mouse.getY() <= y + dimY) {
			return true;
		}	
		return false;
	}
}
