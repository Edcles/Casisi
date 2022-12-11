package we.josemipepeedu.casisi.Blackjack;

import javax.swing.JPanel;
import javax.swing.JLabel;
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
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import java.awt.Rectangle;
import java.awt.Dimension;

public class Blackjack extends JPanel {
	private HashMap<Cartas, List<Integer>> baraja = new HashMap<Cartas, List<Integer>>();
	private final JTextField apuesta = new JTextField();

	public boolean pasarde21 (int mano, int proximacarta ) {
		if(mano+proximacarta>21) {
			return false;
		}else {
			return true;
		}
		
	}
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Blackjack() throws IOException {
		setSize(new Dimension(1200, 800));
		setBounds(new Rectangle(0, 0, 1200, 800));
		setBackground(new Color(0, 0, 0));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		
		int cartamano=0;
		int numerorand=0;
		JButton pedircard = new JButton("Pedir");
		
		pedircard.setBounds(447, 682, 89, 23);
		add(pedircard);
		
		ImagePanel tablero = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("tablero2red.jpg")));
		tablero.setBackgroundType(BackgroundType.PANEL);
		tablero.setBounds(10, 11, 1180, 640);
		add(tablero);
		
		ImagePanel carta1 = new ImagePanel(null, 50);
		carta1.setBackgroundType(BackgroundType.PANEL);
		carta1.setBackground(new Color(0, 0, 0 ,0));
		carta1.setBounds(564, 414, 73, 93);
		tablero.add(carta1);
		carta1.setVisible(true);
		
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

		pedircard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generarCarta(carta1); // llama al metodo generar carta
			}			
		});
		
		
		apostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apuesta.setVisible(true);
				btnNewButton_4.setVisible(true);
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apuesta.setVisible(false);
				btnNewButton_4.setVisible(false);
			}
		});
		// baraja.clear();
	}
	/*
	 * funcion recursiva para generar una carta aleatoria que no esté en la baraja.
	 */
	private void generarCarta(ImagePanel panel) {
		try {
			Carta randomCarta = Cartas.getRandomCarta();
			if (!baraja.containsKey(randomCarta.getType())) {
				baraja.put(randomCarta.getType(), new ArrayList<Integer>());
			}
			if (baraja.get(randomCarta.getType()).contains(randomCarta.getId())) {
				generarCarta(panel);
			} else {
				baraja.get(randomCarta.getType()).add(randomCarta.getId());
				panel.setTexture(Cartas.getRandomCarta().getTexture());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
