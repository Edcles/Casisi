package we.josemipepeedu.casisi.Utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -8547066102878856126L;
	private BufferedImage directedTexture;
	private int radious;
	private BackgroundType backgroundType = BackgroundType.IMAGE;
	/*
	 * Clase para poner un panel con una imagen de fondo
	 * Se puede poner una imagen directamente.
	 * Ademas tambien se puede redondear
	 * los bordes poniendole un radio especifico.
	 * 
	 * Tambien tiene varios modos de background BackgroundType
	 * los cuales se usan para lo siguiente:
	 * FILL: Rellenará el panel repitiendo la imagen infinitas veces.
	 * IMAGE: Dibujará la imagen con el tamaño de dicha imagen.
	 * PANEL: Dibujará la imagen redimensionada a el panel.
	 */
	public ImagePanel(BufferedImage texture) {
		this(texture, 0);
	}
	public ImagePanel(BufferedImage texture, int radious) {
		this.directedTexture = texture;
		this.radious = radious;
		if (radious != 0 && directedTexture != null) {
			directedTexture = makeRoundedCorner(directedTexture, radious);
		}
		setLayout(null);
	}
	public void setBackgroundType(BackgroundType backgroundType) {
		this.backgroundType = backgroundType;
	}
	public void setTexture(BufferedImage texture) {
		this.directedTexture = texture;
		if (radious != 0 && directedTexture != null) {
			directedTexture = makeRoundedCorner(directedTexture, radious);
		}
		repaint();
	}
	public void paintComponent(Graphics g) {
		if (getBackground().getAlpha() != 0) {
			System.out.println("background pintado");
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		if (directedTexture != null) {
			if (backgroundType.equals(BackgroundType.FILL)) {
				for (int x = 0; x <= (getWidth() / directedTexture.getWidth()); x++) {
					for (int y = 0; y <= (getHeight() / directedTexture.getHeight()); y++) {
						g.drawImage(radious != 0 ? makeRoundedCorner(directedTexture, radious) : directedTexture, directedTexture.getWidth()*x, directedTexture.getWidth()*y, null);
					}
				}	
			} else if (backgroundType.equals(BackgroundType.IMAGE) ) {
				g.drawImage(radious != 0 ? makeRoundedCorner(directedTexture, radious) : directedTexture, 0, 0, null);
			} else if (backgroundType.equals(BackgroundType.PANEL) ) {
				g.drawImage(radious != 0 ? makeRoundedCorner(directedTexture, radious) : directedTexture, 0, 0, getWidth(), getHeight(), null);
			}
		}
	}
	private BufferedImage makeRoundedCorner(BufferedImage image, int cornerRadius) {
	    int w = image.getWidth();
	    int h = image.getHeight();
	    BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = output.createGraphics();
	    g2.setComposite(AlphaComposite.Src);
	    paintBorder(g2);
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setColor(Color.WHITE);
	    g2.fill(new RoundRectangle2D.Float(0, 0, w, h, cornerRadius, cornerRadius));
	    g2.setComposite(AlphaComposite.SrcAtop);
	    g2.drawImage(image, 0, 0, null);	    
	    g2.dispose();	    
	    return output;
	}
}
