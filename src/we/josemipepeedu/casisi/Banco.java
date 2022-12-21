package we.josemipepeedu.casisi;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Banco extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Banco() throws IOException {
		setBounds(new Rectangle(0, 0, 1200, 800));
		setLayout(null);
		
		ImagePanel panel = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("billete.jpg")));
		panel.setBounds(244, 254, 295, 144);
		panel.setBackgroundType(BackgroundType.PANEL);
		add(panel);
		
		ImagePanel fichas = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("dinero.png")));
		fichas.setBackgroundType(BackgroundType.PANEL);
		fichas.setBounds(745, 254, 295, 144);
		add(fichas);
		
		JLabel lblNewLabel = new JLabel("Intercambio a fichas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(528, 87, 249, 40);
		add(lblNewLabel);
		
		ImagePanel Imaflecha = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("flecha.png")));
		Imaflecha.setBackgroundType(BackgroundType.PANEL);
		Imaflecha.setBounds(596, 306, 75, 53);
		add(Imaflecha);
		
		textField = new JTextField();
		textField.setBounds(323, 446, 128, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(836, 446, 128, 26);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Convertir");
		btnNewButton.setBounds(350, 486, 77, 23);
		add(btnNewButton);
		
		
	}
}
