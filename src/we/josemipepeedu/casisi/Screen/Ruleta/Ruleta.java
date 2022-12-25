package we.josemipepeedu.casisi.Screen.Ruleta;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.Screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Clase de la Ruleta
 */

public class Ruleta extends Screen {
	private static final long serialVersionUID = 8910088409767064275L;
	
	private static JPanel topPanel = new JPanel(); // Panel superior que contiene el botón de volver, el tiempo y el título
	private static JPanel gamePanel = new JPanel(); // Panel que contendrá el juego en sí
	private static JPanel bottomPanel = new JPanel(); // Panel que contendrá la introducción del saldo y la retirada del mismo
	
	private static JLabel titulo = new JLabel(); // Label que contiene el título
	private static JLabel saldo = new JLabel(); // Label que contiene el saldo que te queda
	
	private static JTextField saldoIntro = new JTextField(10);
	
	private static ImagePanel volver; // Panel que se usa como boton para volver a la pantalla principal
	
	private static JButton tirar = new JButton(); // Boton que realiza las tiradas de la ruleta
	
	private static Color transparente = new Color(0, 0, 0, 0); // Color transparente para los fondos de los paneles
	
	private static Game game; // Canvas que contiene el juego
	
	/*
	 * URL página donde explica las reglas:
	 * https://www.casasdeapuestas.com/ruleta/europea/
	 * 
	 * Tutorial para detectar las apuestas:
	 * https://www.youtube.com/watch?v=wZHhSyulMsM
	 * 
	 * Video que explica los giros de la ruleta:
	 * https://www.youtube.com/watch?v=WIIf3WaO5x4
	 */

	public Ruleta(Casisi casisi) {
		super(null);
		setLayout(null);
		setBackground(new Color(3, 76, 3));
		
		// topPanel
		topPanel.setBackground(transparente);
		topPanel.setLayout(null);
		//topPanel.setBorder(new LineBowrder(Color.red));
		topPanel.setBounds(0, 0, (casisi.getWidth() - 16), (int) (casisi.getHeight() * 0.1));
		//System.out.println("Diferencia de anchura: " + (ventana.getWidth() - topPanel.getWidth()));
		
		// gamePanel
		gamePanel.setBackground(transparente);
		gamePanel.setLayout(null);
		//gamePanel.setBorder(new LineBorder(Color.blue));
		gamePanel.setBounds(0, (int) (casisi.getHeight() * 0.1), (int) casisi.getWidth() - 16, (int) (casisi.getHeight() * 0.8) - 39);
		
		// bottomPanel
		bottomPanel.setBackground(transparente);
		bottomPanel.setLayout(null); 
		//bottomPanel.setBorder(new LineBorder(Color.red));
		bottomPanel.setBounds(0, (int) (casisi.getHeight() * 0.9) - 39, (int) casisi.getWidth() - 16, (int) (casisi.getHeight() * 0.1));
				
		// Boton volver
		try {
			volver = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("volver.png")));
			volver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.openScreen("game-inicio");
				}
			});
			volver.setBackgroundType(BackgroundType.PANEL);
			volver.setBackground(new Color(0, 0, 0, 0));
			volver.setBounds(0, 0, 80, 80);
			//volver.setBorder(new LineBorder(Color.BLUE));
			topPanel.add(volver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Boton iniciarRuleta
		tirar.setText("Finalizar Apuesta");
		//tirar.setBorder(new LineBorder(Color.pink));
		tirar.setBounds(topPanel.getHeight() + 50, 0, topPanel.getHeight() + 60, topPanel.getHeight());
		
		tirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Casisi.getInstance().getBankSystem().getMoney() != 0) {
					game.setRotate(true);
					game.setAscend(true);
				} else {
					JOptionPane.showMessageDialog(null, "No tienes fichas suficientes para hacer una apuesta, la apuesta mínima son 1$ y la mázima son 500$.");
				}
			}
		});

		// Titulo
		titulo.setText("Ruleta Europea");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment(SwingConstants.CENTER);
		//titulo.setBorder(new LineBorder(Color.red));
		titulo.setBounds(500, 0, 700, topPanel.getHeight());
		titulo.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 80));
		titulo.setForeground(Color.white);
		
		topPanel.add(volver);
		topPanel.add(tirar);
		topPanel.add(titulo);
		
		// Canvas del juego
		game = new Game(this, gamePanel.getWidth(), gamePanel.getHeight());
		
		gamePanel.add(game);
		
		// Elementos de bottomPanel
		// Saldo
		saldo.setHorizontalAlignment(SwingConstants.LEFT);
		saldo.setVerticalAlignment(SwingConstants.CENTER);
		//saldo.setBorder(new LineBorder(Color.red));
		saldo.setBounds(5, 5, 350, 50);
		saldo.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 35));
		saldo.setForeground(Color.white);
		
		bottomPanel.add(saldo);
		bottomPanel.add(saldoIntro);
		
		// Agregando los paneles a la ventana
		add(topPanel);
		add(gamePanel);
		add(bottomPanel);
		setVisible(true);
	}	
	public static JPanel getGamePanel() {
		return gamePanel;
	}
	@Override
	public void onOpen() {
		saldo.setText("Saldo actual: " + Casisi.getInstance().getBankSystem().getMoney() + "$");
		game.paintThread();
		repaint();
	}
	@Override
	public void onClose() {
		
	}
}
