import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class iTrLot {

	public JFrame frmTransicionLotes;
	private JTextField textField;

	

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
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(335, 227, 89, 23);
		frmTransicionLotes.getContentPane().add(btnContinuar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(120, 115, 165, 20);
		frmTransicionLotes.getContentPane().add(comboBox);
		
		JLabel lblEstadoFinal = new JLabel("Estado Final:");
		lblEstadoFinal.setBounds(35, 118, 73, 14);
		frmTransicionLotes.getContentPane().add(lblEstadoFinal);
		
		JLabel lblCadena = new JLabel("Cadena:");
		lblCadena.setBounds(35, 85, 46, 14);
		frmTransicionLotes.getContentPane().add(lblCadena);
		
		textField = new JTextField();
		textField.setBounds(95, 81, 215, 23);
		frmTransicionLotes.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnEnviarCadena = new JButton("Enviar Cadena");
		btnEnviarCadena.setBounds(320, 81, 104, 23);
		frmTransicionLotes.getContentPane().add(btnEnviarCadena);
		
		JLabel lblLotes = new JLabel("Reconocimiento por lotes");
		lblLotes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLotes.setBounds(95, 11, 280, 40);
		frmTransicionLotes.getContentPane().add(lblLotes);
	}

}
