package we.josemipepeedu.casisi;

import java.awt.EventQueue;
import java.awt.Toolkit;
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

	/**
	 * Create the frame.
	 */
	public Casisi() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		screens.put("game-tragaperras", new Tragaperras());
		setContentPane(screens.get("game-tragaperras"));
	}

}
