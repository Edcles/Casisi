package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Screen.Principal;
import we.josemipepeedu.casisi.Screen.Ruleta.Ruleta;


public class Casisi extends JFrame {
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

	/**
	 * Create the frame.
	 */
	public Casisi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1200, 800);
		screens.put("game-ruleta", new Ruleta(this));
		screens.put("principal", new Principal());
		setContentPane(screens.get("game-ruleta"));
	}
	
	public static HashMap<String, JPanel> getScreens() {
		return screens;
	}
}
