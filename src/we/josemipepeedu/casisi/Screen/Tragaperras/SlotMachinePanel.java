package we.josemipepeedu.casisi.Screen.Tragaperras;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.RenderableObject;
import we.josemipepeedu.casisi.Utils.Utils;

public class SlotMachinePanel extends Canvas {
	private Tragaperras tragaperras;
	private HashMap<String, RenderableObject> renderables = new HashMap<String, RenderableObject>();
	private BufferedImage lightOn;
	private boolean otherLightAnimation = false;
	private String message = "";
	private int messageAlpha = 255;
	private long messageTime = 0;
	private Font font = new Font("Aria", Font.PLAIN, 50);
	private int apuesta = 50;
	public SlotMachinePanel(Tragaperras tragaperras) {
		this.tragaperras = tragaperras;
		setBounds(0, 0, 951, 756);
		try {
			lightOn = ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/lightOn.png"));
			addRenderable(new RenderableObject("slot_machine", 0, 0, 951, 756, ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/slotMachine.png"))));
			addRenderable(new RenderableObject("slot_panel", 0, 0, 951, 756, ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/slot_panel.png"))));
			addRenderable(new RenderableObject("button1", 0, 0, 951, 756, ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1.png"))));
			addRenderable(new RenderableObject("button2", 0, 0, 951, 756, ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2.png"))));
			addRenderable(new RenderableObject("roundButton", 0, 0, 951, 756, ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/roundButton.png"))));
			int[][] pos = new int[44][2];
			for (int i = 0; i < 12; i++) {
				pos[i] = new int[] {(int) (164 + (i*49.5)), 249};
			}
			for (int i = 0; i < 4; i++) {
				pos[i+12] = new int[] {706, (int) (299 + i*56)};
			}
			for (int i = 0; i < 12; i++) {
				pos[i + 16] = new int[] {(int) (164 + ((11*49.5) - (i*49.5))), 523};
			}
			for (int i = 0; i < 4; i++) {
				pos[i+28] = new int[] {164, (int) (299 + ((3*56) - (i*56)))};
			}
			for (int i = 0; i < pos.length; i++) {
				if (pos[i][0] != 0) {
					addRenderable(new RenderableObject("light_"+i, pos[i][0], pos[i][1], 60, 41, lightOn));
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addRenderable(RenderableObject renderableObject) {
		renderables.put(renderableObject.getID(), renderableObject);
	}
	public RenderableObject getRenderableObject(String id) {
		return renderables.get(id);
	}
	public void removeRenderable(RenderableObject renderableObject) {
		renderables.remove(renderableObject.getID());
	}
	@Override
	public void paint(Graphics g) {
		paintt();
	}
	private void paintt() {
		try {
			BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = img.getGraphics();
			for (int x = 0; x <= (getWidth() / 200); x++) {
				for (int y = 0; y <= (getHeight() / 200); y++) {
					g.drawImage(Utils.fondoOscuro, 200*x, 200*y, 200, 200, null);
				}
			}
			g.fillRect(350, 300, 450, 200);
			String[] entries = renderables.keySet().toArray(new String[] {});
			for (String entry : entries) {
				if (renderables.containsKey(entry)) {
					if (entry.contains("slot_") && !entry.equals("slot_machine")) {
						renderables.get(entry).paint(g, 120);
					}
				}
			}
			renderables.get("slot_machine").paint(g, 120);
			renderables.get("slot_panel").paint(g, 120);
			for (String entry : entries) {
				if (renderables.containsKey(entry)) {
					if (!entry.contains("slot_")) {
						renderables.get(entry).paint(g, 120);
					}
				}
			}
			if (messageAlpha > 0) {
				g.setColor(new Color (255, 255, 255, messageAlpha));
				g.setFont(font);
			    FontMetrics metrics = g.getFontMetrics(font);
				g.drawString(message, 580 - (metrics.stringWidth(message) / 2), 210);
				if (messageTime - System.currentTimeMillis() < 0) {
					messageAlpha -= 25;
				}
			}
			Font font = new Font("Times New Roman", Font.BOLD, 40);
			FontMetrics metrics = g.getFontMetrics(font);
			int stringWidth = metrics.stringWidth("Saldo: " + Casisi.getInstance().getBankSystem().getMoney());
			g.setColor(new Color(195, 195, 195, 200));
			g.fillRect(1150 - (stringWidth) - 10, 20, stringWidth + 20, 32);
			g.setFont(font);
			g.setColor(new Color(255, 207, 27));
			g.drawString("Saldo: " + Casisi.getInstance().getBankSystem().getMoney(), 1150 - (stringWidth), 50);
			getGraphics().drawImage(img, 0, 0, getWidth(), getHeight(), null);
		} catch (Exception ex) {}
	}
	public void paintThread() {
		new Thread() {
			@Override
			public void run() {
				while (tragaperras.isOpen()) {
					try {
						sleep(10);
						paintt();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	public void lightsThread() {
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 44; i++) {
					if (renderables.containsKey("light_" + i)) {
						try {
							sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						RenderableObject renderable = renderables.get("light_" + i);
						if (renderable.getTexture() != null) {
							renderable.setTexture(null);
						} else {
							renderable.setTexture(lightOn);
						}
					}
				}
				if (tragaperras.isOpen()) {
					lightsThread2();
				}
			}
		}.start();
	}
	private void lightsThread2() {
		new Thread() {
			@Override
			public void run() {
				for (int i = 0; i < 44; i++) {
					if (i % 2 == 0) {
						if (renderables.containsKey("light_" + i)) {
							try {
								sleep(20);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							RenderableObject renderable = renderables.get("light_" + i);
							if (renderable.getTexture() != null) {
								renderable.setTexture(null);
							} else {
								renderable.setTexture(lightOn);
							}
						}
					}
				}
				for (int i = 0; i < 44; i++) {
					if (i % 2 != 0) {
						if (renderables.containsKey("light_" + i)) {
							try {
								sleep(20);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							RenderableObject renderable = renderables.get("light_" + i);
							if (renderable.getTexture() != null) {
								renderable.setTexture(null);
							} else {
								renderable.setTexture(lightOn);
							}
						}
					}
				}
				if (tragaperras.isOpen()) {
					lightsThread();
				}
			}
		}.start();
	}
	public int getApuesta() {
		return apuesta;
	}
	public void setApuesta(int apuesta) {
		if (apuesta < 25) {
			apuesta = 25;
		}
		if (apuesta > 500) {
			apuesta = 500;
		}
		this.apuesta = apuesta;
		setMessage("Apuesta: " + apuesta + "€", 2500);
	}
	public void setMessage(String msg, long time) {
		this.messageAlpha = 255;
		this.message = msg;
		this.messageTime = System.currentTimeMillis() + time;
	}
}
