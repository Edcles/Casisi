package we.josemipepeedu.casisi.Screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import we.josemipepeedu.casisi.Casisi;
import we.josemipepeedu.casisi.Utils.BackgroundType;
import we.josemipepeedu.casisi.Utils.ImagePanel;
import we.josemipepeedu.casisi.Utils.Screen;
import we.josemipepeedu.casisi.Utils.Utils;
import javax.swing.JPanel;

public class Inicio extends Screen {
	private static final long serialVersionUID = -3969425414245940631L;
	
	private JLabel ruletaName;
	private JLabel blackjackName;
	private JLabel slotmachineName;
	
	private ImagePanel luz = null;
	
	private int contluz=0;
	private int contbandera=0;
	
	private Color c= new Color(255, 255, 255, 0);
	
	private boolean juegosSelected = false;
	
	private static JLabel saldo;
	private JPanel panel_games;
	private ImagePanel tragaperras;
	private ImagePanel ruleta;
	private ImagePanel blackjack;
	
	public Inicio(Casisi casisi) {
		super(Utils.selectedFondo);
		setBackgroundType(BackgroundType.FILL);
		setBounds(new Rectangle(0, 0, 1200, 800));
		setLayout(null);
		try {				
			luz  = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("modoclaro.png")));
			luz.setBackgroundType(BackgroundType.PANEL);
			luz.setBackground(c);
			luz.setBounds(111, 11, 59, 66);
			add(luz);


			ImagePanel logo = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("logo.png")));
			logo.setOpaque(false);
			logo.setBackground(null);
			logo.setBackgroundType(BackgroundType.PANEL);
			logo.setBounds(600 - 150, 50, 300, 300);
			logo.setBackground(c);
			add(logo);

			ImagePanel bandera = new ImagePanel(null);

			bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("lang/españa.png")));
			bandera.setBounds(32, 26, 59, 40);
			add(bandera);

			ImagePanel Paneljuegos = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("juegos.png")));
			Paneljuegos.setBackgroundType(BackgroundType.PANEL);
			Paneljuegos.setBounds(276, 300, 227, 161);
			Paneljuegos.setBackground(c);
			add(Paneljuegos);
			tragaperras = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("slotmachineIcon.png")));
			tragaperras.setBounds(220, 548, 200, 200);
			tragaperras.setBackground(c);
			ruleta = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("ruletaIcon.png")));
			ruleta.setBounds(470, 548, 200, 200);
			ruleta.setBackground(c);
			blackjack = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("blackjackIcon.png")));
			blackjack.setBounds(720, 548, 200, 200);
			blackjack.setBackground(c);
			tragaperras.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.openScreen("game-tragaperras");
				}
			});
			ruleta.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.openScreen("game-ruleta");
				}
			});
			blackjack.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.openScreen("game-blackjack");
				}
			});
			Paneljuegos.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (juegosSelected) {
						juegosSelected = false;
					} else {
						juegosSelected = true;
					}
					tragaperras.setVisible(juegosSelected);
					ruleta.setVisible(juegosSelected);
					blackjack.setVisible(juegosSelected);
					panel_games.setVisible(juegosSelected);
					repaint();
				}
			});

			tragaperras.setVisible(false);
			ruleta.setVisible(false);
			blackjack.setVisible(false);
			add(tragaperras);
			add(ruleta);
			add(blackjack);
			ImagePanel banco = new ImagePanel(ImageIO.read(getClass().getClassLoader().getResource("dinero.png")));
			banco.setBackground(c);
			banco.setBackgroundType(BackgroundType.PANEL);
			banco.setBounds(693, 300, 227, 161);
			add(banco);
			banco.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					casisi.openScreen("game-banco");
				}
			});

			saldo = new JLabel();
			saldo.setOpaque(true);
			saldo.setForeground(Color.black);
			saldo.setVerticalAlignment(SwingConstants.CENTER);
			saldo.setHorizontalAlignment(SwingConstants.CENTER);
			saldo.setFont(new Font("Times New Roman", Font.BOLD, 40));
			saldo.setText("Saldo: " + casisi.getBankSystem().getMoney() + "$");
			saldo.setHorizontalAlignment(SwingConstants.TRAILING);
			saldo.setBounds(950, 26, 225, 28);
			saldo.setBackground(new Color(192, 192, 192, 225));
			add(saldo);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(192, 192, 192, 225));
			panel.setBounds(204, 50, 736, 444);
			add(panel);
			
			panel_games = new JPanel();
			panel_games.setBackground(new Color(192, 192, 192, 225));
			panel_games.setBounds(220, 548, 700, 200);
			panel_games.setVisible(false);
			add(panel_games);

			luz.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					contluz++;
					try {
						if(contluz%2==1) {
							luz.setTexture(ImageIO.read(getClass().getClassLoader().getResource("modooscuro.png")));
							luz.setBackgroundType(BackgroundType.PANEL);
							Utils.selectedFondo = Utils.fondoOscuro;
						} else {
							luz.setTexture(ImageIO.read(getClass().getClassLoader().getResource("modoclaro.png")));
							luz.setBackgroundType(BackgroundType.PANEL);
							Utils.selectedFondo = Utils.fondoClaro;
						}
						setTexture(Utils.selectedFondo);
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
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("lang/españa.png")));
							bandera.setBackgroundType(BackgroundType.PANEL);
						}else if(contbandera%3==1) {
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("lang/aleman.png")));
							bandera.setBackgroundType(BackgroundType.PANEL);
						}else if(contbandera%3==2) {
							bandera.setTexture(ImageIO.read(getClass().getClassLoader().getResource("lang/inglaterra.jpg")));
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
	}
	@Override
	public void onOpen() {
		setTexture(Utils.selectedFondo);
		saldo.setText("Saldo: " + Casisi.getInstance().getBankSystem().getMoney() + "$");
	}
	@Override
	public void onClose() {
		juegosSelected = false;
		tragaperras.setVisible(juegosSelected);
		ruleta.setVisible(juegosSelected);
		blackjack.setVisible(juegosSelected);
		panel_games.setVisible(juegosSelected);
	}
}
