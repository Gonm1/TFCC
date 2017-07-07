import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class iTrLot {

	public JFrame frmTransicionLotes;

	

	/**
	 * Create the application.
	 */
	public iTrLot() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTransicionLotes = new JFrame();
		frmTransicionLotes.setTitle("Transicion Lotes");
		frmTransicionLotes.setBounds(100, 100, 450, 300);
		frmTransicionLotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransicionLotes.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(118, 191, 165, 20);
		frmTransicionLotes.getContentPane().add(comboBox);
		
		JLabel lblEstadoFinal = new JLabel("Estado Final:");
		lblEstadoFinal.setBounds(35, 194, 73, 14);
		frmTransicionLotes.getContentPane().add(lblEstadoFinal);
		
		JLabel lblCadena = new JLabel("Cadenas:");
		lblCadena.setBounds(35, 66, 52, 14);
		frmTransicionLotes.getContentPane().add(lblCadena);
		
		JLabel lblLotes = new JLabel("Reconocimiento por lotes");
		lblLotes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLotes.setBounds(95, 11, 280, 40);
		frmTransicionLotes.getContentPane().add(lblLotes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 62, 271, 97);
		frmTransicionLotes.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnRunMt = new JButton("Run MT");
		btnRunMt.setBounds(335, 227, 89, 23);
		frmTransicionLotes.getContentPane().add(btnRunMt);
	}
}
