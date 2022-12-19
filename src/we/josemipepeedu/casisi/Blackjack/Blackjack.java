package we.josemipepeedu.casisi.Blackjack;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Blackjack extends ImagePanel {
	private boolean playing = true;
	private boolean puedeApostar = true;
	private HashMap<Cartas, List<Integer>> baraja = new HashMap<Cartas, List<Integer>>();
	private HashMap<Cartas, List<Integer>> mano_jugador = new HashMap<Cartas, List<Integer>>();
	private HashMap<Cartas, List<Integer>> mano_crupier = new HashMap<Cartas, List<Integer>>();
	private final JTextField apuesta = new JTextField();



	/**
	 * Create the panel.
	 * 
	 * @throws IOException
	 */
	public Blackjack() throws IOException {
		super(null);
		setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo2.jpg")));
		setBackgroundType(BackgroundType.FILL);
		setSize(new Dimension(1200, 800));
		setBounds(new Rectangle(0, 0, 1200, 800));
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setLayout(null);

		JButton pedircard = new JButton("Pedir");

		pedircard.setBounds(447, 682, 89, 23);
		add(pedircard);

		JButton stopcard = new JButton("Parar");

		stopcard.setBounds(587, 682, 89, 23);
		add(stopcard);

		JButton apostar = new JButton("Apostar");
		apostar.setBounds(728, 682, 89, 23);
		add(apostar);


		JButton Barajar = new JButton("Barajar");

		Barajar.setBounds(868, 682, 89, 23);
		add(Barajar);
		apuesta.setBounds(645, 736, 115, 23);
		apuesta.setVisible(false);
		add(apuesta);
		apuesta.setColumns(10);

		final JButton btnNewButton_4 = new JButton("Confirmar");

		btnNewButton_4.setBounds(773, 736, 103, 23);
		btnNewButton_4.setVisible(false);
		add(btnNewButton_4);

		ImagePanel tablero = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("tablero2.jpg")));
		tablero.setBackgroundType(BackgroundType.PANEL);
		tablero.setBounds(10, 11, 1180, 762);
		add(tablero);

		ImagePanel carta1 = new ImagePanel(null, 50);
		carta1.setBackgroundType(BackgroundType.PANEL);
		carta1.setBackground(new Color(0, 0, 0, 0));
		carta1.setBounds(443, 503, 73, 93);
		tablero.add(carta1);

		ImagePanel carta2 = new ImagePanel(null, 50);
		carta2.setBackgroundType(BackgroundType.PANEL);
		carta2.setBackground(new Color(0, 0, 0, 0));
		carta2.setBounds(527, 503, 73, 93);
		tablero.add(carta2);

		ImagePanel carta3 = new ImagePanel(null, 50);
		carta3.setBounds(610, 503, 73, 93);
		carta3.setBackgroundType(BackgroundType.PANEL);
		carta3.setBackground(new Color(0, 0, 0, 0));
		tablero.add(carta3);

		ImagePanel carta4 = new ImagePanel(null, 50);
		carta4.setBounds(693, 503, 73, 93);
		carta4.setBackgroundType(BackgroundType.PANEL);
		carta4.setBackground(new Color(0, 0, 0, 0));
		tablero.add(carta4);

		ImagePanel carta1cup = new ImagePanel(null, 50);
		carta1cup.setBounds(443, 126, 73, 93);
		carta1cup.setBackgroundType(BackgroundType.PANEL);
		tablero.add(carta1cup);

		ImagePanel carta2cup = new ImagePanel(null, 50);
		carta2cup.setBounds(526, 126, 73, 93);
		carta2cup.setBackgroundType(BackgroundType.PANEL);
		tablero.add(carta2cup);

		ImagePanel carta3cup = new ImagePanel(null, 50);
		carta3cup.setBackgroundType(BackgroundType.PANEL);
		carta3cup.setBounds(609, 126, 73, 93);
		tablero.add(carta3cup);

		ImagePanel carta4cup = new ImagePanel(null, 50);
		carta4cup.setBackgroundType(BackgroundType.PANEL);
		carta4cup.setBounds(692, 126, 73, 93);
		tablero.add(carta4cup);

		ImagePanel carta5cup = new ImagePanel(null, 50);
		carta5cup.setBackgroundType(BackgroundType.PANEL);
		carta5cup.setBounds(775, 126, 73, 93);
		tablero.add(carta5cup);

		ImagePanel carta5 = new ImagePanel(null, 50);
		carta5.setBounds(776, 503, 73, 93);
		carta5.setBackgroundType(BackgroundType.PANEL);
		carta5.setBackground(new Color(0, 0, 0, 0));
		tablero.add(carta5);
		carta1.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta2.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta3.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta4.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta5.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		generarCarta(carta1cup, false);
		carta2cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta3cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta4cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
		carta5cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));



		pedircard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playing) {
					puedeApostar = false;
					if (!pasarde21(calcularValorJugador())) {
						int contador = contarCartasJugador();
						if (contador == 0) {
							generarCarta(carta1, true); // llama al metodo generar carta
						} else if (contador == 1) {
							generarCarta(carta2, true);
						} else if (contador == 2) {
							generarCarta(carta3, true);
						} else if (contador == 3) {
							generarCarta(carta4, true);
						} else if (contador == 4) {
							generarCarta(carta5, true);
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Te has pasado de 21",
								"Has perdido",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		});




		apostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (puedeApostar) {
					apuesta.setVisible(true);
					btnNewButton_4.setVisible(true);
				}
			}
		});

		Barajar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playing = true;
				puedeApostar = true;
				baraja.clear();
				mano_jugador.clear();
				mano_crupier.clear();
				try {
					carta1.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta2.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta3.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta4.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta5.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					generarCarta(carta1cup, false);
					carta2cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta3cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta4cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					carta5cup.setTexture(ImageIO.read(getClass().getClassLoader().getResource("reversocarta.png")));
					paintComponents(getGraphics());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		stopcard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (playing) {
					playing = false;
					new Thread() {
						@Override
						public void run() {
							boolean finished = false;
							while (!finished) {
								try {
									sleep(1000);
									if (calcularValorCrupier() > 21) {
										finished = true;
									} else {
										if (calcularValorJugador() > 21) {
											finished = true;
										} else if (calcularValorCrupier() < calcularValorJugador()) {
											int contador = contarCartasCrupier();
											if (contador == 0) {
												generarCarta(carta1cup, false); // llama al metodo generar carta
											} else if (contador == 1) {
												generarCarta(carta2cup, false);
											} else if (contador == 2) {
												generarCarta(carta3cup, false);
											} else if (contador == 3) {
												generarCarta(carta4cup, false);
											} else if (contador == 4) {
												generarCarta(carta5cup, false);
											}
										} else {
											finished = true;
										}
									}
								} catch (InterruptedException e) {
									e.printStackTrace();
								}							
							}
							if(ganadorcupier()) {
								JOptionPane.showMessageDialog(null,
										"HAS PERDIDO!!" ,
										"Has perdido",
										JOptionPane.WARNING_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(null,
										"HAS GANADO!!" ,
										"HAS GANADO",
										JOptionPane.WARNING_MESSAGE);
							}
							System.out.println("HE PARADO " + ganadorcupier());
						}
					}.start();
				}
			}
		});

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apuesta.setVisible(false);
				btnNewButton_4.setVisible(false);
			}
		});

	}

	/*
	 * funcion recursiva para generar una carta aleatoria que no esté en la baraja.
	 */
	private void generarCarta(ImagePanel panel, boolean esjugador) {
		try {
			Carta randomCarta = Cartas.getRandomCarta();
			if (!baraja.containsKey(randomCarta.getType())) {
				baraja.put(randomCarta.getType(), new ArrayList<Integer>());
			}
			if (baraja.get(randomCarta.getType()).contains(randomCarta.getId())) {
				generarCarta(panel, esjugador);
			} else {
				// System.out.println("carta generada: " + randomCarta.getType() + " - " +
				// randomCarta.getId());
				baraja.get(randomCarta.getType()).add(randomCarta.getId());
				panel.setTexture(randomCarta.getTexture());
				añadirMano(randomCarta, esjugador);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void añadirMano(Carta carta, boolean esjugador) {
		if (esjugador) {
			if (!mano_jugador.containsKey(carta.getType())) {
				mano_jugador.put(carta.getType(), new ArrayList<Integer>());
			}
			mano_jugador.get(carta.getType()).add(carta.getId());
		} else { // es crupier
			if (!mano_crupier.containsKey(carta.getType())) {
				mano_crupier.put(carta.getType(), new ArrayList<Integer>());
			}
			mano_crupier.get(carta.getType()).add(carta.getId());
		}
	}
	public int contarCartasJugador() {
		int result = 0;
		for (Entry<Cartas, List<Integer>> entry : mano_jugador.entrySet()) {
			result += entry.getValue().size();
		}
		return result;
	}
	public int calcularValorJugador() {
		int result = 0;
		for (Entry<Cartas, List<Integer>> entry : mano_jugador.entrySet()) {
			for (Integer valor : entry.getValue()) {
				result += valor;
			}
		}
		return result;
	}
	public int contarCartasCrupier() {
		int result = 0;
		for (Entry<Cartas, List<Integer>> entry : mano_crupier.entrySet()) {
			result += entry.getValue().size();
		}
		return result;
	}
	public int calcularValorCrupier() {
		int result = 0;
		for (Entry<Cartas, List<Integer>> entry : mano_crupier.entrySet()) {
			for (Integer valor : entry.getValue()) {
				result += valor;
			}
		}
		return result;
	}

	public boolean ganadorcupier() {
		int mano = calcularValorJugador();
		int manocupier = calcularValorCrupier();
		if(manocupier>mano && manocupier<21 || mano>21) {
			return true;
		}else {
			return false;
		}
	}


	public boolean pasarde21(int mano) {
		if (mano > 21) {
			return true;
		} else {
			return false;
		}
	}
}