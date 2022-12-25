package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import we.josemipepeedu.casisi.Screen.Banco;
import we.josemipepeedu.casisi.Screen.Inicio;
import we.josemipepeedu.casisi.Screen.Blackjack.Blackjack;
import we.josemipepeedu.casisi.Screen.Ruleta.Ruleta;
import we.josemipepeedu.casisi.Screen.Tragaperras.Tragaperras;
import we.josemipepeedu.casisi.Utils.Screen;
import we.josemipepeedu.casisi.Utils.Utils;
import we.josemipepeedu.casisi.system.BankSystem;

public class Casisi extends JFrame {
	private static Casisi instance;
	private HashMap<String, Screen> screens = new HashMap<String, Screen>();
	private static BankSystem bankSystem = new BankSystem();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Casisi frame = new Casisi();
					frame.setLocation(0, 0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Casisi() throws IOException {
		instance = this;
		Utils.fondoClaro = ImageIO.read(getClass().getClassLoader().getResource("fondo.jpg"));
		Utils.fondoOscuro = ImageIO.read(getClass().getClassLoader().getResource("fondo2.jpg"));
		Utils.selectedFondo = Utils.fondoClaro;
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
		setTitle("Casisi - Online");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		try {
			screens.put("game-tragaperras", new Tragaperras());
			screens.put("game-ruleta", new Ruleta(this));
			screens.put("game-blackjack", new Blackjack(this));
			screens.put("game-inicio", new Inicio(this));
			screens.put("game-banco", new Banco(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentPane(screens.get("game-inicio"));
	}
	public void openScreen(String id) {
		Screen alreadyOpenned = (Screen) getContentPane();
		alreadyOpenned.close();
		screens.get(id).open();
		setContentPane(screens.get(id));
		repaint();
	}
	public static Casisi getInstance() {
		return instance;
	}
	public static BankSystem getBankSystem() {
		return bankSystem;
	}
}