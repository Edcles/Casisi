package we.josemipepeedu.casisi.Screen.Tragaperras;

import javax.swing.JPanel;

import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.Callback;
import we.josemipepeedu.casisi.Utils.GameAudio;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.Utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Tragaperras extends JPanel {
	private List<Spin> spinners = new ArrayList<Spin>();
	private SlotMachinePanel slotMachine;
	private HashMap<Integer, Boolean> buttons = new HashMap<Integer, Boolean>();
	public Tragaperras() throws IOException {
		setSize(new Dimension(1200, 800));
		setLayout(null);
		buttons.put(1, false);
		buttons.put(2, false);
		buttons.put(3, false);
		slotMachine = new SlotMachinePanel();
		slotMachine.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point = e.getPoint();
				if (!Utils.isTouch(point, 250, 563, 111, 60)) { // boton1
					try {
						buttons.put(1, false);
						slotMachine.getRenderableObject("button1").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (!Utils.isTouch(point, 377, 563, 111, 60)) { // boton2
					try {
						buttons.put(2, false);
						slotMachine.getRenderableObject("button2").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (!Utils.isTouch(point, 530, 563, 142, 60)) { // boton3
					try {
						buttons.put(3, false);
						slotMachine.getRenderableObject("roundButton").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/roundButton.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		slotMachine.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				if (Utils.isTouch(point, 250, 563, 111, 60)) { // boton1
					try {
						buttons.put(1, true);
						slotMachine.getRenderableObject("button1").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1_pulsed.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 377, 563, 111, 60)) { // boton2
					try {
						buttons.put(2, true);
						slotMachine.getRenderableObject("button2").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2_pulsed.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 530, 563, 142, 60)) { // boton3
					try {
						buttons.put(3, true);
						slotMachine.getRenderableObject("roundButton").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/roundButton_pulsed.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Point point = e.getPoint();
				if (Utils.isTouch(point, 250, 563, 111, 60)) { // boton1
					try {
						if (buttons.get(1)) {
							
						}
						buttons.put(1, false);
						slotMachine.getRenderableObject("button1").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 377, 563, 111, 60)) { // boton2
					try {
						if (buttons.get(2)) {
							
						}
						buttons.put(2, false);
						slotMachine.getRenderableObject("button2").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 530, 563, 142, 60)) { // boton3
					try {
						if (buttons.get(3)) {
							spin();
						}
						buttons.put(3, false);
						slotMachine.getRenderableObject("roundButton").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/roundButton.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		slotMachine.setBackground(new Color(0, 0, 0, 0));
		slotMachine.setBounds(105, 0, 951, 756);
		add(slotMachine);
		/*
		 * 
		ImagePanel light1 = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/lightOn.png")));
		light1.setBounds(706, 299, 60, 41);
		light1.setBackgroundType(BackgroundType.PANEL);
		light1.setBackground(new Color(0, 0, 0, 0));
		slotMachine.add(light1);
		 */
		//registerLights(slotMachine);
		generateSpinners();
	}
	private void generateSpinners() {
		for (int x : new int[] {320, 440, 575}) {
			spinners.add(new Spin(slotMachine, x, new Callback<Boolean>() {
					@Override
					public void done(Boolean result, Exception exception) {
						boolean oneActive = false;
						for (Spin spin: spinners) {
							if (spin.isSpinning()) {
								oneActive = true;
							}
						}
						if (!oneActive) {
							HashMap<SlotItem, Integer> counter = new HashMap<SlotItem, Integer>();
							for (Spin spinner : spinners) {
								if (!counter.containsKey(spinner.getWinner())) {
									counter.put(spinner.getWinner(), 0);
								}
								counter.put(spinner.getWinner(), counter.get(spinner.getWinner()) + 1);
							}
							for (Entry<SlotItem, Integer> winner : counter.entrySet()) {
								if (winner.getValue() == 2) {
									new GameAudio("tragaperras/sounds/spinnerwin.wav") {
										@Override
										public void onFinish() {
										}							
									};
								} else if (winner.getValue() == 3) {
									new GameAudio("tragaperras/sounds/spinnerbigwin.wav") {
										@Override
										public void onFinish() {
										}							
									};
								}
							}
						}
					}					
				}));
		}
	}
	private void spin() {
		boolean oneActive = false;
		boolean oneToStop = false;
		for (Spin spin: spinners) {
			if (spin.isSpinning()) {
				oneActive = true;
				if (!oneToStop && !spin.isToStop()) {
					spin.toStop();
					oneToStop = true;
				}
			}
		}
		if (!oneActive) {
			for (Spin spin : spinners) {
				spin.toStart();
			}
		}
	}
}
