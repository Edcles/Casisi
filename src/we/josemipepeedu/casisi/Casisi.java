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