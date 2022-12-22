package we.josemipepeedu.casisi.Screen;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.Screen;
import we.josemipepeedu.casisi.Utils.Utils;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Banco extends Screen {
	private JTextField textField;
	
	private static ImagePanel volver;
	private JLabel lbl_creditos;
	
	public Banco(Casisi casisi) throws IOException {
		super(Utils.selectedFondo);
		setBackgroundType(BackgroundType.FILL);
		setBounds(new Rectangle(0, 0, 1200, 800));
		setLayout(null);
		
		try {
			volver = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
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
		
		lbl_creditos = new JLabel();
		lbl_creditos.setText(casisi.getBankSystem().getMoney() + " creditos");
		lbl_creditos.setBounds(836, 446, 128, 26);
		add(lbl_creditos);
		
		JButton btnNewButton = new JButton("Convertir");
		btnNewButton.setBounds(335, 486, 100, 23);
		add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(192, 192, 192, 225));
		panel_1.setBounds(227, 87, 825, 477);
		add(panel_1);
		
		btnNewButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					casisi.getBankSystem().addMoney(Integer.parseInt(textField.getText()));
					lbl_creditos.setText(casisi.getBankSystem().getMoney() + " créditos");				
					lbl_creditos.repaint();
				} catch (NumberFormatException ex) {}
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
	@Override
	public void onOpen() {
		setTexture(Utils.selectedFondo);
		lbl_creditos.setText(Casisi.getInstance().getBankSystem().getMoney() + " creditos");
	}
	@Override
	public void onClose() {
		
	}
}
