package we.josemipepeedu.casisi.Screen.Tragaperras;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.Utils;

public class Spin extends Thread {
	private Tragaperras tragaperras;
	private int x = 0;
	private List<ImagePanel> items = new ArrayList<ImagePanel>();
	private int speed = 0;
	private int ySpin = 600;
	private boolean toStop = false;
	private long lastSpeedChanged = 0;
	private int changeSpeedDifference = 200;
	public Spin(Tragaperras tragaperras, int x) {
		this.tragaperras = tragaperras;
		this.x = x;
	}
	public boolean isSpinning() {
		return speed != 0;
	}
	public boolean isToStop() {
		return toStop;
	}
	public void toStart() {
		speed = 10;
		toStop = false;
		if (!isAlive()) {
			start();
		}
	}
	public void toStop() {
		toStop = true;
		lastSpeedChanged = System.currentTimeMillis();
	}
	@Override
	public void run() {
		while (true) {
			try {
				sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (speed != 0) {
				boolean needCreateOther = items.isEmpty();
				int lastY = 0;
				Iterator<ImagePanel> iterator = items.iterator();
				while (iterator.hasNext()) {
					JPanel panel = iterator.next();
					panel.setBounds(panel.getBounds().x, panel.getBounds().y - speed, panel.getBounds().width, panel.getBounds().height);
					panel.repaint();
					if (panel.getBounds().getY() < ySpin - 300) {
						panel.setVisible(false);
						iterator.remove();
					}
					if (!iterator.hasNext()) {
						needCreateOther = panel.getBounds().getY() < ySpin - 50;
						if (needCreateOther) {
							lastY = (int) panel.getBounds().getY();
						}
					}
				}
				if (needCreateOther) {
					int randomID = Utils.random.nextInt(14) + 1;
					try {
						ImagePanel imagePanel = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/" + randomID + ".png")));
						//imagePanel.setBackground(new Color(Utils.random.nextInt(255), Utils.random.nextInt(255), Utils.random.nextInt(255)));
						imagePanel.setBackgroundType(BackgroundType.PANEL);
						imagePanel.setBounds(x, lastY + 50, 50, 50);
						items.add(imagePanel);
						tragaperras.add(imagePanel);
					} catch (Exception e) {
						System.out.println(randomID + " dont exists?");
						e.printStackTrace();
					}
				}
				if (toStop && (lastSpeedChanged + changeSpeedDifference) - System.currentTimeMillis() <= 0) {
					speed--;
					lastSpeedChanged = System.currentTimeMillis();
				}
			}
		}
	}
}
