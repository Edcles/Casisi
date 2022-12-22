package we.josemipepeedu.casisi.Screen;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Banco extends ImagePanel {
	private JTextField textField;
	
	private static int fichasCant = 0;
	
	private static ImagePanel volver;
	
	public Banco(Casisi casisi) throws IOException {
		super(null);
		setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg")));
		setBackgroundType(BackgroundType.FILL);
		setBounds(new Rectangle(0, 0, 1200, 800));
		setLayout(null);
		
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
			add(volver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImagePanel panel = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("billete.jpg")));
		panel.setBounds(244, 254, 295, 144);
		panel.setBackgroundType(BackgroundType.PANEL);
		add(panel);
		
		ImagePanel fichas = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("dinero.png")));
		fichas.setBackgroundType(BackgroundType.PANEL);
		fichas.setBackground(new Color(0, 0, 0, 0));
		fichas.setBounds(745, 254, 295, 144);
		add(fichas);
		
		JLabel lblNewLabel = new JLabel("Intercambio a fichas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(528, 87, 249, 40);
		add(lblNewLabel);
		
		ImagePanel Imaflecha = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("flecha.png")));
		Imaflecha.setBackgroundType(BackgroundType.PANEL);
		Imaflecha.setBackground(new Color(0, 0, 0, 0));
		Imaflecha.setBounds(596, 306, 75, 53);
		add(Imaflecha);
		
		textField = new JTextField();
		textField.setBounds(323, 446, 128, 26);
		add(textField);
		textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char caracter = e.getKeyChar();
				
				// Verificar si la tecla pulsada no es un dígito
				if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) { // '\b' es un espacio
					e.consume(); // Ignora el evento de un teclado
				}
				if (textField.getText().length() >= 9) {
					e.consume();
				}
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setText(fichasCant + " creditos");
		lblNewLabel_1.setBounds(836, 446, 128, 26);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Convertir");
		btnNewButton.setBounds(335, 486, 100, 23);
		add(btnNewButton);
		
		btnNewButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("AAA");
				fichasCant += Integer.parseInt(textField.getText());
				Inicio.getSaldo().setText("Saldo: " + fichasCant);
				lblNewLabel_1.setText(fichasCant + " créditos");				
				lblNewLabel_1.repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
	}
	
	public static int getCant() {
		return fichasCant;
	}
}
