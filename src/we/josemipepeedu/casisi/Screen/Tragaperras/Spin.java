<<<<<<< HEAD:src/we/josemipepeedu/casisi/Screen/Tragaperras/Spin.java
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
=======
package we.josemipepeedu.casisi.Tragaperras;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Utils.Callback;
import we.josemipepeedu.casisi.Utils.GameAudio;
import we.josemipepeedu.casisi.Utils.RenderableObject;

public class Spin extends Thread {
	private SlotMachinePanel slotMachine;
	private int x = 0;
	private List<SlotItemObject> items = new ArrayList<SlotItemObject>();
	private int speed = 0;
	private int ySpin = 505;
	private boolean toStop = false;
	private long lastSpeedChanged = 0;
	private int changeSpeedDifference = 200;
	private Callback<Boolean> callback;
	private int internalID = 0;
	private SlotItem winner;
	public Spin(SlotMachinePanel slotMachine, int x, Callback<Boolean> callback) {
		this.slotMachine = slotMachine;
		this.x = x;
		this.callback = callback;
	}
	public boolean isSpinning() {
		return speed != 0;
	}
	public boolean isToStop() {
		return toStop;
	}
	public void toStart() {
		for (RenderableObject object : items) {
			object.setBackground(null);
		}
		winner = null;
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
				sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (speed != 0) {
				boolean needCreateOther = items.isEmpty();
				int lastY = 0;
				Iterator<SlotItemObject> iterator = items.iterator();
				while (iterator.hasNext()) {
					RenderableObject object = iterator.next();
					object.setY(object.getY() - speed);
					if (object.getY() < ySpin - 300) {
						slotMachine.removeRenderable(object);
						iterator.remove();
					}
					if (!iterator.hasNext()) {
						needCreateOther = object.getY() < ySpin - 50;
						if (needCreateOther) {
							lastY = (int) object.getY();
						}
					}
				}
				if (needCreateOther) {
					SlotItem slot = SlotItem.getRandom();
					try {
						SlotItemObject object = new SlotItemObject("slot_" + getId() + "_" + internalID++, slot, x, lastY + 50);
						slotMachine.addRenderable(object);
						items.add(object);
						/*new GameAudio("tragaperras/sounds/wheel.wav") {
							@Override
							public void onFinish() {
							}							
						};*/
					} catch (Exception e) {
 						e.printStackTrace();
					}
				}
				if (toStop && (lastSpeedChanged + changeSpeedDifference) - System.currentTimeMillis() <= 0) {
					speed--;
					lastSpeedChanged = System.currentTimeMillis();
					if (speed == 0) {
						selectWinner();
					}
				}
			}
		}
	}
	public SlotItem getWinner() {
		return winner;
	}
	private void selectWinner() {
		for (SlotItemObject object : items) {
			int y = (int) object.getY();
			int height = (int) object.getHeight();
			if (ySpin - 150 <= y && ySpin - 150 >= y - height) {
				winner = object.getSlotItem();
				callback.done(true, null);
				new Thread() {
					@Override
					public void run() {
						for (int i = 0; i < 10; i++) {
							try {
								object.setBackground(new Color(255, 255, 0, 150));
								sleep(100);
								object.setBackground(new Color(0, 0, 0, 0));
								sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						object.setBackground(new Color(255, 230, 0));
					}
				}.start();
				break; // para que si otro está tambien en el rango no gane
			}
		}
	}
}
>>>>>>> origin/pepe:src/we/josemipepeedu/casisi/Tragaperras/Spin.java
