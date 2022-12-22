package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Screen.Tragaperras.Tragaperras;
import we.josemipepeedu.casisi.Utils.Utils;
import we.josemipepeedu.casisi.system.BankSystem;


public class Casisi extends JFrame {
	private static Casisi instance;
	private HashMap<String, JPanel> screens = new HashMap<String, JPanel>();
	private BankSystem bankSystem = new BankSystem();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Casisi frame = new Casisi();
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		try {
			screens.put("game-tragaperras", new Tragaperras());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentPane(screens.get("game-tragaperras"));
	}
	public void openScreen(String id) {
		setContentPane(screens.get(id));
	}
	public static Casisi getInstance() {
		return instance;
	}
	public BankSystem getBankSystem() {
		return bankSystem;
	}
}