package we.josemipepeedu.casisi.Screen.Tragaperras;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.Callback;
import we.josemipepeedu.casisi.Utils.GameAudio;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.Screen;
import we.josemipepeedu.casisi.Utils.Utils;

import java.awt.Color;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Tragaperras extends Screen {
	private List<Spin> spinners = new ArrayList<Spin>();
	private SlotMachinePanel slotMachine;
	private HashMap<Integer, Boolean> buttons = new HashMap<Integer, Boolean>();
	private boolean jackpot = false;
	private ImagePanel volver;
	public Tragaperras() throws IOException {
		super(null);
		setSize(new Dimension(1200, 800));
		setLayout(null);
		try {
			volver = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("volver.png")));
			volver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Casisi.getInstance().openScreen("game-inicio");
				}
			});
			volver.setBackgroundType(BackgroundType.PANEL);
			volver.setBackground(new Color(0, 0, 0, 0));
			volver.setBounds(0, 0, 80, 80);
			//volver.setBorder(new LineBorder(Color.BLUE));
			add(volver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		buttons.put(1, false);
		buttons.put(2, false);
		buttons.put(3, false);
		slotMachine = new SlotMachinePanel(this);
		slotMachine.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Point point = e.getPoint();
				if (!Utils.isTouch(point, 370, 563, 111, 60)) { // boton1
					try {
						buttons.put(1, false);
						slotMachine.getRenderableObject("button1").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (!Utils.isTouch(point, 497, 563, 111, 60)) { // boton2
					try {
						buttons.put(2, false);
						slotMachine.getRenderableObject("button2").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (!Utils.isTouch(point, 650, 563, 142, 60)) { // boton3
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
				if (Utils.isTouch(point, 370, 563, 111, 60)) { // boton1
					try {
						buttons.put(1, true);
						slotMachine.getRenderableObject("button1").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1_pulsed.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 497, 563, 111, 60)) { // boton2
					try {
						buttons.put(2, true);
						slotMachine.getRenderableObject("button2").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2_pulsed.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 650, 563, 142, 60)) { // boton3
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
				if (Utils.isTouch(point, 370, 563, 111, 60)) { // boton1
					try {
						slotMachine.setApuesta(slotMachine.getApuesta() - 25);
						removeJackpot();
						if (buttons.get(1)) {
							
						}
						buttons.put(1, false);
						slotMachine.getRenderableObject("button1").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button1.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 497, 563, 111, 60)) { // boton2
					try {
						slotMachine.setApuesta(slotMachine.getApuesta() + 25);
						removeJackpot();
						if (buttons.get(2)) {
							
						}
						buttons.put(2, false);
						slotMachine.getRenderableObject("button2").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/button2.png")));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (Utils.isTouch(point, 650, 563, 142, 60)) { // boton3
					try {
						removeJackpot();
						if (buttons.get(3)) {
							if (Casisi.getInstance().getBankSystem().getMoney() < slotMachine.getApuesta()) {
								JOptionPane.showMessageDialog(null, "No tienes fichas suficientes para hacer una apuesta de " + slotMachine.getApuesta());
							} else {
								spin();
							}
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
		slotMachine.setBounds(0, 0, 1200, 790);
		add(slotMachine);
		generateSpinners();
	}
	private void removeJackpot() throws IOException {
		if (jackpot) {
			slotMachine.getRenderableObject("slot_panel").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/slot_panel.png")));
		}
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
									int win = (int) (winner.getKey().getTwoReward() * slotMachine.getApuesta());
									slotMachine.setMessage("+ " + win, 2000);
									Casisi.getInstance().getBankSystem().addMoney(win);
									new GameAudio("tragaperras/sounds/spinnerwin.wav") {
										@Override
										public void onFinish() {
										}							
									};
								} else if (winner.getValue() == 3) {
									int win = (int) (winner.getKey().getAllReward() * slotMachine.getApuesta());
									slotMachine.setMessage("+ " + win, 1500);
									Casisi.getInstance().getBankSystem().addMoney(win);
									jackpot = true;
									new Thread() {
										@Override
										public void run() {
											try {
												sleep(2000);
												slotMachine.getRenderableObject("slot_panel").setTexture(ImageIO.read(getClass().getClassLoader().getResource("tragaperras/images/machine/slot_panel_jackpot.png")));
											} catch (Exception e) {
												e.printStackTrace();
											}
										}
									}.start();
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
			Casisi.getInstance().getBankSystem().removeMoney(slotMachine.getApuesta());
			for (Spin spin : spinners) {
				spin.toStart();
			}
		}
	}
	@Override
	public void onOpen() {
		slotMachine.paintThread();
		slotMachine.lightsThread();
	}
	@Override
	public void onClose() {
		
	}
}
