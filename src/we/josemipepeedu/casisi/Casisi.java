package we.josemipepeedu.casisi;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Casisi extends JFrame {
	
	public Casisi() {
		setTitle("Casisi");
		setSize(new Dimension(1200, 800));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(new Ruleta(this));
		setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Casisi();
	}
}
