package we.josemipepeedu.casisi.Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;

public class Inicio extends JPanel {
	private static final long serialVersionUID = -3969425414245940631L;
	
	private JLabel ruletaName;
	private JLabel blackjackName;
	private JLabel slotmachineName;
	
	private ImagePanel panelentero;
	private ImagePanel luz = null;
	
	private int contluz=0;
	private int contbandera=0;
	
	private Color c= new Color(255, 255, 255, 0);
	
	private boolean juegosSelected = false;
	
	private static JLabel saldo;
	private static JLabel saldo2;
	
	public Inicio(Casisi casisi) {
		repaint();
		setLayout(null);
		try {	
			panelentero = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg")));	
			panelentero.setBounds(0, 0, casisi.getWidth(), casisi.getHeight());
			panelentero.setBackgroundType(BackgroundType.FILL);
			
			luz  = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("modoclaro.png")));
			luz.setBackgroundType(BackgroundType.PANEL);
			luz.setBackground(c);
			luz.setBounds(111, 11, 59, 66);
			panelentero.add(luz);


			ImagePanel logo = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
			logo.setOpaque(false);
			logo.setBackground(null);
			logo.setBackgroundType(BackgroundType.PANEL);
			logo.setBounds((int) (casisi.getWidth() / 2) - 150, 50, 300, 300);
			logo.setBackground(c);
			panelentero.add(logo);

			ImagePanel bandera = new ImagePanel(null);

			bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("españa.png")));
			bandera.setBounds(32, 26, 59, 40);
			panelentero.add(bandera);

			ImagePanel Paneljuegos = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("juegos.png")));
			Paneljuegos.setBackgroundType(BackgroundType.PANEL);
			Paneljuegos.setBounds(276, 300, 227, 161);
			Paneljuegos.setBackground(c);
			panelentero.add(Paneljuegos);
			ImagePanel tragaperras = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("slotmachineIcon.png")));
			tragaperras.setBounds(26, 450, 200, 200);
			tragaperras.setBackground(c);
			ImagePanel ruleta = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("ruletaIcon.png")));
			ruleta.setBounds(276, 450, 200, 200);
			ruleta.setBackground(c);
			ImagePanel blackjack = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("blackjackIcon.png")));
			blackjack.setBounds(526, 450, 200, 200);
			blackjack.setBackground(c);
			tragaperras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.setContentPane(casisi.getScreens().get("game-tragaperras"));
					casisi.repaint();
				}
			});
			ruleta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.setContentPane(casisi.getScreens().get("game-ruleta"));
					casisi.repaint();
				}
			});
			blackjack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.setContentPane(casisi.getScreens().get("game-blackjack"));
					casisi.repaint();
				}
			});
			Paneljuegos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (juegosSelected) {
						juegosSelected = false;
						panelentero.remove(tragaperras);
						panelentero.remove(ruleta);
						panelentero.remove(blackjack);
						repaint();
					} else {
						juegosSelected = true;
						panelentero.add(tragaperras);
						panelentero.add(ruleta);
						panelentero.add(blackjack);
						repaint();
					}
				}
			});

			ImagePanel banco = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("dinero.png")));
			banco.setBackground(c);
			banco.setBackgroundType(BackgroundType.PANEL);
			banco.setBounds(693, 300, 227, 161);
			panelentero.add(banco);
			banco.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.setContentPane(casisi.getScreens().get("game-banco"));
					repaint();
				}
			});

			saldo = new JLabel();
			saldo.setForeground(new Color(241, 196, 15));
			saldo.setFont(new Font("Times New Roman", getFont().BOLD, 40));
			saldo.setText("Saldo: 0");
			saldo.setBounds(950, 26, 250, 28);
			saldo.setBackground(Color.white);
			panelentero.add(saldo);

			saldo2 = new JLabel();
			saldo2.setForeground(Color.black);
			saldo2.setFont(new Font("Times New Roman", getFont().BOLD, 40));
			saldo2.setText("Saldo: 0");
			saldo2.setBackground(Color.white);
			saldo2.setBounds(949, 25, 251, 29);
			panelentero.add(saldo2);

			luz.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contluz++;
					try {
						if(contluz%2==1) {
							luz.setTexture(ImageIO.read(getClass().getClassLoader().getResource("modooscuro.png")));
							luz.setBackgroundType(BackgroundType.PANEL);

							((ImagePanel) panelentero).setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo2.jpg")));	
							((ImagePanel) panelentero).setBackgroundType(BackgroundType.FILL);
						}else {
							luz.setTexture(ImageIO.read(getClass().getClassLoader().getResource("modoclaro.png")));
							luz.setBackgroundType(BackgroundType.PANEL);

							((ImagePanel) panelentero).setTexture(ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg")));	
							((ImagePanel) panelentero).setBackgroundType(BackgroundType.FILL);
						}
					} catch (IOException e1) {
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
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("españa.png")));
							bandera.setBackgroundType(BackgroundType.PANEL);
						}else if(contbandera%3==1) {
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("aleman.png")));
							bandera.setBackgroundType(BackgroundType.PANEL);
						}else if(contbandera%3==2) {
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("inglaterra.jpg")));
							bandera.setBackgroundType(BackgroundType.PANEL);
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(panelentero);
	}
	
	public static JLabel getSaldo() {
		return saldo;
	}
}
