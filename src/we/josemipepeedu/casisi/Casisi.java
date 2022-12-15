package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import we.josemipepeedu.casisi.Tragaperras.Tragaperras;


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
		setBounds(100, 100, 1200, 800);
		try {
			screens.put("game-tragaperras", new Tragaperras());
		} catch (IOException e) {
			e.printStackTrace();
		}
		setContentPane(screens.get("game-tragaperras"));
	}
}