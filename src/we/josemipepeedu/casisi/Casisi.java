<<<<<<< HEAD
package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Screen.Ruleta.Ruleta;
import we.josemipepeedu.casisi.Screen.Tragaperras.Tragaperras;


public class Casisi extends JFrame {
	private HashMap<String, JPanel> screens = new HashMap<String, JPanel>();
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
		try {
			screens.put("game-tragaperras", new Tragaperras());
			screens.put("game-ruleta", new Ruleta(this));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentPane(screens.get("game-ruleta"));
	}
}
=======
package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import we.josemipepeedu.casisi.Blackjack.Blackjack;

import java.awt.Color;

public class Casisi extends JFrame {
	private Blackjack Panel;
	/**
	 * Launch the application.
	 */
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		setLocationRelativeTo(null);
		try {
			Panel = new Blackjack();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Panel.setForeground(new Color(255, 255, 255));
		Panel.setBackground(new Color(255, 255, 255));
		setContentPane(Panel);
	}
}
>>>>>>> origin/josemi
