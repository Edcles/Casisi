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
import javax.swing.border.LineBorder;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * Clase de la Ruleta
 */

public class Ruleta extends JPanel {
	private static final long serialVersionUID = 8910088409767064275L;
	
	private static JPanel topPanel = new JPanel(); // Panel superior que contiene el botón de volver, el tiempo y el título
	private static JPanel gamePanel = new JPanel(); // Panel que contendrá el juego en sí
	private static JPanel bottomPanel = new JPanel(); // Panel que contendrá la introducción del saldo y la retirada del mismo
	
	private static JLabel titulo = new JLabel(); // Label que contiene el título
	private static JLabel saldo = new JLabel(); // Label que contiene el saldo que te queda
	private static JLabel saldoIntroLabel = new JLabel(); // Label que contiene el texto para indicar que hay que introducir texto
	
	private static JTextField saldoIntro = new JTextField(10);
	
	private static ImagePanel volver; // Panel que se usa como boton para volver a la pantalla principal
	
	private static JButton tirar = new JButton(); // Boton que realiza las tiradas de la ruleta
	private static JButton agregarSaldo = new JButton(); // Boton que introduce el saldo
	private static JButton retirarSaldo = new JButton(); // Boton que retira el saldo
	
	private static Color transparente = new Color(0, 0, 0, 0); // Color transparente para los fondos de los paneles
	
	private static Game game; // Canvas que contiene el juego
	
	private static int saldoCant = 0; // Cantidad de saldo
	
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
		casisi.repaint();
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
			volver = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
			volver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.setContentPane(casisi.getScreens().get("game-inicio"));
					casisi.repaint();
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
				Random random = new Random();
				int res = random.nextInt(37);
				game.setNumWin(res);
				game.setRotate(true);
				game.setAscend(true);
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
		game = new Game(gamePanel.getWidth(), gamePanel.getHeight());
		
		gamePanel.add(game);
		
		// Elementos de bottomPanel
		// Saldo
		saldo.setText("Saldo actual: " + saldoCant + "$");
		saldo.setHorizontalAlignment(SwingConstants.LEFT);
		saldo.setVerticalAlignment(SwingConstants.CENTER);
		//saldo.setBorder(new LineBorder(Color.red));
		saldo.setBounds(5, 5, 350, 50);
		saldo.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 35));
		saldo.setForeground(Color.white);
		
		// Retirar
		retirarSaldo.setText("RETIRAR SALDO");
		retirarSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		retirarSaldo.setVerticalAlignment(SwingConstants.CENTER);
		retirarSaldo.setBounds(365, 5, 260, 50);
		retirarSaldo.setBackground(Color.blue);
		retirarSaldo.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
		retirarSaldo.setForeground(Color.white);
		
		// Saldo Introductor
		saldoIntroLabel.setText("Introduce Saldo: ");
		saldoIntroLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saldoIntroLabel.setVerticalAlignment(SwingConstants.CENTER);
		saldoIntroLabel.setBounds(720, 5, 170, 20);
		saldoIntroLabel.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 20));
		saldoIntroLabel.setForeground(Color.white);
		
		saldoIntro.setBounds(720, 30, 170, 20);
		saldoIntro.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 20));
		saldoIntro.setHorizontalAlignment(JTextField.RIGHT);
		saldoIntro.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
				// Verificar si la tecla pulsada no es un dígito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) { // '\b' es un espacio
					e.consume(); // Ignora el evento de un teclado
				}
				if (saldoIntro.getText().length() >= 9) {
					e.consume();
				}
			}
		});
		
		// Boton agregarSaldo
		agregarSaldo.setText("AGREGAR SALDO");
		agregarSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		agregarSaldo.setVerticalAlignment(SwingConstants.CENTER);
		agregarSaldo.setBounds(900, 5, 280, 50);
		agregarSaldo.setBackground(Color.blue);
		agregarSaldo.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 30));
		agregarSaldo.setForeground(Color.white);
		agregarSaldo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (saldoCant + Integer.parseInt(saldoIntro.getText()) <= 9999) {
					saldoCant += Integer.parseInt(saldoIntro.getText());
					saldo.setText("Saldo actual: " + saldoCant + "$");
					repaint();
					paintComponent(getGraphics());
				} else {
					JOptionPane.showMessageDialog(null, "El saldo no puede superar los 9999", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});	
		
		bottomPanel.add(saldo);
		bottomPanel.add(retirarSaldo);
		bottomPanel.add(saldoIntroLabel);
		bottomPanel.add(saldoIntro);
		bottomPanel.add(agregarSaldo);
		
		// Agregando los paneles a la ventana
		add(topPanel);
		add(gamePanel);
		add(bottomPanel);
		setVisible(true);
	}
	
	public static JPanel getGamePanel() {
		return gamePanel;
	}
}
