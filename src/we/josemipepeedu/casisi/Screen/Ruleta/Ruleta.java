package we.josemipepeedu.casisi.Screen.Ruleta;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Screen.Tragaperras.Spin;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*
 * Clase de la Ruleta
 */

public class Ruleta extends JPanel {
	private static final long serialVersionUID = 8910088409767064275L;
	
	private static JPanel topPanel = new JPanel(); // Panel superior que contiene el botón de volver, el tiempo y el título
	private static JPanel gamePanel = new JPanel(); // Panel que contendrá el juego en sí
	private static JPanel narratorPanel = new JPanel(); // Panel que contiene un narrador que va a ir mostrando mensajes de lo que vas haciendo
	
	private static JLabel titulo = new JLabel(); // Label que contiene el título
	
	private static ImagePanel volver; // Panel que se usa como boton para volver a la pantalla principal
	private static JButton tirar = new JButton(); // Boton que realiza las tiradas de la ruleta
	
	private static Color transparente = new Color(0, 0, 0, 0);
	
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

	public Ruleta(Casisi ventana) {
		setLayout(null);
		setBackground(new Color(59, 140, 45));
		
		// topPanel
		topPanel.setBackground(transparente);
		topPanel.setLayout(null);
		//topPanel.setBorder(new LineBorder(Color.red));
		topPanel.setBounds(0, 0, (ventana.getWidth() - 16), (int) (ventana.getHeight() * 0.1));
		//System.out.println("Diferencia de anchura: " + (ventana.getWidth() - topPanel.getWidth()));
		
		// gamePanel
		gamePanel.setBackground(transparente);
		gamePanel.setLayout(null);
		//gamePanel.setBorder(new LineBorder(Color.blue));
		gamePanel.setBounds(0, (int) (ventana.getHeight() * 0.1), (int) (ventana.getWidth() * 0.8), (int) (ventana.getHeight() * 0.9) - 39);
		
		// narratorPanel
		narratorPanel.setBackground(transparente);
		//narratorPanel.setBorder(new LineBorder(Color.black));
		narratorPanel.setBounds((int) (ventana.getWidth() * 0.8), (int) (ventana.getHeight() * 0.1), (int) (ventana.getWidth() * 0.2) - 16, (int) (ventana.getHeight() * 0.9) - 39);
		
		// Boton volver
		try {
			volver = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
			volver.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("AAA");
					ventana.setContentPane(Casisi.getScreens().get("principal"));
					ventana.repaint();
				}
			});
			volver.setBackgroundType(BackgroundType.PANEL);
			volver.setBackground(new Color(0, 0, 0, 0));
			volver.setBounds(0, 0, 80, 80);
			volver.setBorder(new LineBorder(Color.BLUE));
			topPanel.add(volver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Boton iniciarRuleta
		tirar.setText("Finalizar Apuesta");
		//tirar.setBorder(new LineBorder(Color.pink));
		tirar.setBounds(topPanel.getHeight() + 50, 0, topPanel.getHeight() + 50, topPanel.getHeight());
		
		tirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random random = new Random();
				int res = random.nextInt(37);
				System.out.println(res);
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
		Game game = new Game(gamePanel.getWidth(), gamePanel.getHeight());
		
		gamePanel.add(game);
		
		add(topPanel);
		add(gamePanel);
		add(narratorPanel);
	}
	
	public static JPanel getGamePanel() {
		return gamePanel;
	}
}
