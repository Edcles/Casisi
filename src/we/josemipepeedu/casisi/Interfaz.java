package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JLabel;

public class Interfaz extends JFrame {

	private JPanel panelentero;
	private int contluz=0;
	private int contbandera=0;
	private Color c= new Color(255, 255, 255, 0);
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
		panelentero = new ImagePanel(null);
		panelentero.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelentero);
		panelentero.setLayout(null);
		
		try {	
		((ImagePanel) panelentero).setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg")));	
		((ImagePanel) panelentero).setBackgroundType(BackgroundType.FILL);
		
		ImagePanel luz = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("luz/modoclaro.png")));
		
		luz.setBackgroundType(BackgroundType.PANEL);
		luz.setBackground(c);
		luz.setBounds(111, 11, 59, 66);
		panelentero.add(luz);
		

			ImagePanel logo = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
			logo.setOpaque(false);
			logo.setBackground(null);
			logo.setBackgroundType(BackgroundType.PANEL);
			logo.setBounds(441, 132, 357, 302);
			logo.setBackground(c);
			panelentero.add(logo);
			
			ImagePanel bandera = new ImagePanel(null);
			
			bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("banderas/españa.png")));
			bandera.setBounds(32, 26, 59, 40);
			panelentero.add(bandera);
			
			ImagePanel Paneljuegos = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("juegos.png")));
			Paneljuegos.setBackgroundType(BackgroundType.PANEL);
			Paneljuegos.setBounds(276, 529, 227, 161);
			Paneljuegos.setBackground(c);
			panelentero.add(Paneljuegos);
			
			ImagePanel banco = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("dinero.png")));
			banco.setBackground(c);
			banco.setBackgroundType(BackgroundType.PANEL);
			banco.setBounds(693, 529, 227, 161);
			panelentero.add(banco);
			
			JLabel lblNewLabel = new JLabel("nombre: saldo");
			lblNewLabel.setBounds(994, 26, 127, 28);
			panelentero.add(lblNewLabel);
			
			luz.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contluz++;
					try {
					if(contluz%2==1) {
						luz.setTexture(ImageIO.read(getClass().getClassLoader().getResource("luz/modooscuro.png")));
						luz.setBackgroundType(BackgroundType.PANEL);
						
						((ImagePanel) panelentero).setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo2.jpg")));	
						((ImagePanel) panelentero).setBackgroundType(BackgroundType.FILL);
					}else {
						luz.setTexture(ImageIO.read(getClass().getClassLoader().getResource("luz/modoclaro.png")));
						luz.setBackgroundType(BackgroundType.PANEL);
						
						((ImagePanel) panelentero).setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg")));	
						((ImagePanel) panelentero).setBackgroundType(BackgroundType.FILL);
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			bandera.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
					contbandera++;
					
					if(contbandera%3==0) {
						
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("banderas/españa.png")));
							bandera.setBackgroundType(BackgroundType.PANEL);
					}else if(contbandera%3==1) {
						bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("banderas/aleman.png")));
						bandera.setBackgroundType(BackgroundType.PANEL);
					}else if(contbandera%3==2) {
						bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("banderas/inglaterra.jpg")));
						bandera.setBackgroundType(BackgroundType.PANEL);
					}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
}
