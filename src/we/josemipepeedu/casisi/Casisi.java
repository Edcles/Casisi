package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Screen.Banco;
import we.josemipepeedu.casisi.Screen.Inicio;
import we.josemipepeedu.casisi.Screen.Blackjack.Blackjack;
import we.josemipepeedu.casisi.Screen.Ruleta.Ruleta;
import we.josemipepeedu.casisi.Screen.Tragaperras.Tragaperras;


public class Casisi extends JFrame {
	private static final long serialVersionUID = 4966135616136917821L;
	private static HashMap<String, JPanel> screens = new HashMap<String, JPanel>();
	
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
	
	public Casisi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1200, 800);
		setTitle("Casisi");
		try {
			screens.put("game-tragaperras", new Tragaperras(this));
			screens.put("game-ruleta", new Ruleta(this));
			screens.put("game-blackjack", new Blackjack(this));
			screens.put("game-inicio", new Inicio(this));
			screens.put("game-banco", new Banco(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentPane(screens.get("game-inicio"));
	}
	
	public static HashMap<String, JPanel> getScreens() {
		return screens;
	}
}
