package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import we.josemipepeedu.casisi.Utils.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JPanel luz = new JPanel();
		luz.setBounds(99, 11, 59, 40);
		contentPane.add(luz);
		
		textField = new JTextField();
		textField.setBounds(973, 25, 157, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		try {
			ImagePanel logo = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
			logo.setBounds(393, 94, 490, 443);
			contentPane.add(logo);
			
			ImagePanel bandera = new ImagePanel(null);
			bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("banderas/españa.png")));
			bandera.setBounds(30, 11, 59, 40);
			contentPane.add(bandera);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
