package we.josemipepeedu.casisi.Screen.Tragaperras;

import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class Tragaperras extends JPanel {
	private HashMap<Integer, Spin> spinners = new HashMap<Integer, Spin>();
	public Tragaperras() {
		setSize(new Dimension(1200, 800));
		setLayout(null);
		
		JButton btnNewButton = new JButton("Spin!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spin();
			}
		});
		btnNewButton.setBounds(150, 721, 89, 23);
		add(btnNewButton);
		generateSpinners();
	}
	private void generateSpinners() {
		for (int x : new int[] {400, 500, 600}) {
			if (!spinners.containsKey(x)) {
				spinners.put(x, new Spin(this, x));
			}
		}
	}
	private void spin() {
		boolean oneActive = false;
		boolean oneToStop = false;
		for (Spin spin: spinners.values()) {
			if (spin.isSpinning()) {
				oneActive = true;
				if (!oneToStop && !spin.isToStop()) {
					spin.toStop();
					oneToStop = true;
				}
			}
		}
		if (!oneActive) {
			for (Spin spin : spinners.values()) {
				spin.toStart();
			}
		}
	}
}
