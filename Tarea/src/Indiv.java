import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class Indiv {

	private JFrame frmReconocimientoIndividual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Indiv window = new Indiv();
					window.frmReconocimientoIndividual.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Indiv() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReconocimientoIndividual = new JFrame();
		frmReconocimientoIndividual.setTitle("Reconocimiento Individual");
		frmReconocimientoIndividual.setBounds(100, 100, 450, 300);
		frmReconocimientoIndividual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReconocimientoIndividual.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(51, 42, 332, 165);
		frmReconocimientoIndividual.getContentPane().add(textPane);
	}
}
