package we.josemipepeedu.casisi.Screen.Tragaperras;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Utils.RenderableObject;

public class SlotMachinePanel extends Canvas {
	private HashMap<String, RenderableObject> renderables = new HashMap<String, RenderableObject>();
	private BufferedImage lightOn;
	private boolean otherLightAnimation = false;
	public SlotMachinePanel() {
		setBounds(0, 0, 951, 756);
		try {
			lightOn = ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/lightOn.png"));
			addRenderable(new RenderableObject("slot_machine", 0, 0, 951, 756, ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/slotMachine.png"))));
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
			paintThread();
			lightsThread();			
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
			img.getGraphics().setColor(new Color(0, 0, 0, 0));
			img.getGraphics().fillRect(0, 0, getWidth(), getHeight());
			String[] entries = renderables.keySet().toArray(new String[] {});
			for (String entry : entries) {
				if (renderables.containsKey(entry)) {
					if (entry.contains("slot_") && !entry.equals("slot_machine")) {
						renderables.get(entry).paint(img.getGraphics());
					}
				}
			}
			img.getGraphics().drawImage(renderables.get("slot_machine").getTexture(), renderables.get("slot_machine").getX(), renderables.get("slot_machine").getY(), renderables.get("slot_machine").getWith(), renderables.get("slot_machine").getHeight(), null);
			for (String entry : entries) {
				if (renderables.containsKey(entry)) {
					if (!entry.contains("slot_")) {
						renderables.get(entry).paint(img.getGraphics());
					}
				}
			}
			getGraphics().drawImage(img, 0, 0, getWidth(), getHeight(), null);
		} catch (Exception ex) {}
	}
	private void paintThread() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
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
	private void lightsThread() {
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
				lightsThread2();
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
				lightsThread();
			}
		}.start();
	}
}
