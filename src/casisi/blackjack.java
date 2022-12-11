package casisi;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class blackjack extends JPanel {
	private final JTextField apuesta = new JTextField();

	public boolean pasarde21 (int mano, int proximacarta ) {
		if(mano+proximacarta>21) {
			return false;
		}else {
			return true;
		}
		
	}
	/**
	 * Create the panel.
	 */
	public blackjack() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		setLayout(null);
		
		final Random random=new Random();
		int cartamano=0;
		final int numerorand=0;
		ArrayList<ImageIcon> listaImagenes= new ArrayList<ImageIcon>();
		listaImagenes.add(new ImageIcon(blackjack.class.getResource("/resource/2trebol.png")));
		
		JButton pedircard = new JButton("Pedir");
		
		pedircard.setBounds(138, 416, 89, 23);
		add(pedircard);
		
		JLabel carta2 = new JLabel("New label");
		carta2.setBounds(403, 310, 49, 14);
		add(carta2);
		
		final JLabel carta1 = new JLabel("New label");
		carta1.setVisible(false);
		carta1.setIcon(listaImagenes.get(0));
		carta1.setBounds(313, 276, 54, 82);
		add(carta1);
		
		JLabel tablero = new JLabel("");
		tablero.setBounds(10, 11, 785, 400);
		tablero.setIcon(new ImageIcon(blackjack.class.getResource("/resource/tablero2red.jpg")));
		add(tablero);
		
		JButton stopcard = new JButton("Parar");
		stopcard.setBounds(278, 416, 89, 23);
		add(stopcard);
		
		JButton apostar = new JButton("Apostar");
		apostar.setBounds(419, 416, 89, 23);
		add(apostar);
		
		JButton Barajar = new JButton("Barajar");
		Barajar.setBounds(559, 416, 89, 23);
		add(Barajar);
		apuesta.setBounds(336, 470, 115, 23);
		apuesta.setVisible(false);
		add(apuesta);
		apuesta.setColumns(10);
		
		final JButton btnNewButton_4 = new JButton("Confirmar");
		
		btnNewButton_4.setBounds(464, 470, 103, 23);
		btnNewButton_4.setVisible(false);
		add(btnNewButton_4);

		pedircard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//	numerorand=random.nextInt(12);
			//	if(pasarde21(cartamano, numerorand))
			//	mano
				carta1.setVisible(true);
			}
			
		});
		
		
		apostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apuesta.setVisible(true);
				btnNewButton_4.setVisible(true);
			}
		});
		
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apuesta.setVisible(false);
				btnNewButton_4.setVisible(false);
			}
		});
	}
}
