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
	private int angle = 0;
	private boolean selected;
	public RenderableObject(String id, int x, int y, int with, int height, BufferedImage bufferedImage) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.with = with;
		this.height = height;
		this.bufferedImage = bufferedImage;
	}
	public RenderableObject(String id, int x, int y, int with, int height, BufferedImage bufferedImage, boolean selected) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.with = with;
		this.height = height;
		this.bufferedImage = bufferedImage;
		this.selected = selected;
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
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public BufferedImage rotateImageByDegrees(int angle) {
		double rads = Math.toRadians(angle);
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();

        BufferedImage rotated = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate(0, 0);

        int x = w / 2;
        int y = h / 2;
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(bufferedImage, 0, 0, null);
        g2d.dispose();
        return rotated;
	}
	public void paint(Graphics g) {
		paint(g, 0);
	}
	public void paint(Graphics g, int plusX) {
		if (background != null) {
			g.setColor(background);
			g.fillRect(x + plusX, y, with, height);
		}
		if (bufferedImage != null) {
			if (angle != 0) {
				g.drawImage(rotateImageByDegrees(angle), x + plusX, y, with, height, null);
			} else {
				g.drawImage(bufferedImage, x + plusX, y, with, height, null);
			}
		}
	}
}
