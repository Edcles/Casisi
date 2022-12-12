package we.josemipepeedu.casisi;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/*
 * Clase de la Ruleta
 */

public class Ruleta extends JPanel {
	private static final long serialVersionUID = 8910088409767064275L;
	
	private static JPanel topPanel = new JPanel(); // Panel superior que contiene el botón de volver, el tiempo y el título
	private static JPanel gamePanel = new JPanel(); // Panel que contendrá el juego en sí
	private static JPanel narratorPanel = new JPanel(); // Panel que contiene un narrador que va a ir mostrando mensajes de lo que vas haciendo
	
	private static JLabel titulo = new JLabel(); // Label que contiene el título
	private static JLabel tiempo = new JLabel(); // Label que contiene el tiempo restante para que la ruleta gire
	private static JLabel ruletaImg = new JLabel(); // Label que contiene la ruleta
	
	private static JButton volver = new JButton(); // Boton para volver a la pantalla principal
	
	/*
	 * URL página donde explica las reglas:
	 * https://www.casasdeapuestas.com/ruleta/europea/
	 */

	public Ruleta(Casisi ventana) {
		setLayout(null);
		setBackground(new Color(59, 140, 45));
		
		// Titulo
		titulo.setText("Ruleta Europea");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setVerticalAlignment(SwingConstants.CENTER);
		titulo.setBorder(new LineBorder(Color.red));
		titulo.setBounds(0, 0, (ventana.getWidth() - 16), (int) (ventana.getHeight() * 0.13));
		titulo.setFont(new Font("Franklin Gothic Demi", Font.BOLD, 90));
		titulo.setForeground(Color.white);
		
		
		// topPanel
		topPanel.setBackground(Color.black);
		topPanel.setBorder(new LineBorder(Color.red));
		topPanel.setBounds(0, 0, (ventana.getWidth() - 16), (int) (ventana.getHeight() * 0.1));
		//System.out.println("Diferencia de anchura: " + (ventana.getWidth() - topPanel.getWidth()));
		
		// gamePanel
		gamePanel.setBackground(Color.yellow);
		gamePanel.setLayout(null);
		gamePanel.setBorder(new LineBorder(Color.blue));
		gamePanel.setBounds(0, (int) (ventana.getHeight() * 0.1), (int) (ventana.getWidth() * 0.8), (int) (ventana.getHeight() * 0.9) - 39);
		//System.out.println("Diferencia de altura: " + (ventana.getHeight() - (gamePanel.getHeight() + topPanel.getHeight())));
		
		// narratorPanel
		narratorPanel.setBackground(Color.orange);
		narratorPanel.setBorder(new LineBorder(Color.black));
		narratorPanel.setBounds((int) (ventana.getWidth() * 0.8), (int) (ventana.getHeight() * 0.1), (int) (ventana.getWidth() * 0.2) - 16, (int) (ventana.getHeight() * 0.9) - 39);
		
		// Ruleta
		ruletaImg.setIcon(new ImageIcon(Casisi.class.getResource("/img/ruleta.png")));
		ruletaImg.setBorder(new LineBorder(Color.black));
		ruletaImg.setBounds(0, 0, 100, 100);
		
		gamePanel.add(ruletaImg);
		
		add(topPanel);
		add(gamePanel);
		add(narratorPanel);
	}
}
