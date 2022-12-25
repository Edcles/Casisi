package we.josemipepeedu.casisi.Utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/*
 * Clase que se encarga de hacer la rotación de la imagen de la ruleta
 */
public class RotatePanel extends JPanel {
	private BufferedImage img;
	
	private int x = 0;
	private int y = 0;
	private int num;
	
	public RotatePanel(BufferedImage img, int num) {
		this.img = img;
		this.num = num;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(x,  y);
		g2d.rotate(Math.toRadians(num));
		g2d.drawImage(img, x, y, (int) img.getWidth(), (int) img.getHeight(), null);
		g2d.dispose();
	}
}
