package we.josemipepeedu.casisi.Utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class RenderableObject {
	private String id;
	private int x;
	private int y;
	private int with;
	private int height;
	private BufferedImage bufferedImage;
	private Color background;
	public RenderableObject(String id, int x, int y, int with, int height, BufferedImage bufferedImage) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.with = with;
		this.height = height;
		this.bufferedImage = bufferedImage;
	}
	public String getID() {
		return id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWith() {
		return with;
	}
	public void setWith(int with) {
		this.with = with;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BufferedImage getTexture() {
		return bufferedImage;
	}
	public void setTexture(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	public Color getBackground() {
		return background;
	}
	public void setBackground(Color color) {
		this.background = color;
	}
	public void paint(Graphics g) {
		if (background != null) {
			g.setColor(background);
			g.fillRect(x, y, with, height);
		}
		if (bufferedImage != null) {
			g.drawImage(bufferedImage, x, y, with, height, null);
		}
	}
	public BufferedImage rotateImageByDegrees(BufferedImage img, int angle) {
		double rads = Math.toRadians(angle);
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate(0, 0);

        int x = w / 2;
        int y = h / 2;
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
	}
}
