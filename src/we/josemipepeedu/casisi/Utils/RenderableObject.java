package we.josemipepeedu.casisi.Utils;

import java.awt.Color;
import java.awt.Graphics;
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
		paint(g, 0);
	}
	public void paint(Graphics g, int plusX) {
		if (background != null) {
			g.setColor(background);
			g.fillRect(x + plusX, y, with, height);
		}
		if (bufferedImage != null) {
			g.drawImage(bufferedImage, x + plusX, y, with, height, null);
		}
	}
}
