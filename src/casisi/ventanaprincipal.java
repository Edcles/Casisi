package casisi;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class ventanaprincipal extends JFrame {
blackjack Panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaprincipal frame = new ventanaprincipal();
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
	public ventanaprincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 860, 537);
		Panel = new blackjack();
		Panel.setForeground(new Color(255, 255, 255));
		Panel.setBackground(new Color(255, 255, 255));
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(Panel);
	}

}
