import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class iTrInd {

	public JFrame frmTransicionReconocimientoIndividual;
	private JTextField textFieldInd;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					iTrInd window = new iTrInd();
					window.frmTransicionReconocimientoIndividual.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public iTrInd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTransicionReconocimientoIndividual = new JFrame();
		frmTransicionReconocimientoIndividual.setTitle("Transicion Reconocimiento Individual");
		frmTransicionReconocimientoIndividual.setBounds(100, 100, 450, 300);
		frmTransicionReconocimientoIndividual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransicionReconocimientoIndividual.getContentPane().setLayout(null);
		
		JLabel lblReconocimientoIndividual = new JLabel("Reconocimiento Individual");
		lblReconocimientoIndividual.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReconocimientoIndividual.setBounds(94, 11, 280, 45);
		frmTransicionReconocimientoIndividual.getContentPane().add(lblReconocimientoIndividual);
		
		textFieldInd = new JTextField();
		textFieldInd.setBounds(112, 67, 289, 20);
		frmTransicionReconocimientoIndividual.getContentPane().add(textFieldInd);
		textFieldInd.setColumns(10);
		
		JLabel lblCadena = new JLabel("Cadena:");
		lblCadena.setBounds(56, 70, 46, 14);
		frmTransicionReconocimientoIndividual.getContentPane().add(lblCadena);
		
		
		String[] opciones=Metodos.todos_estados(InterfaceUno.mt);
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(122, 103, 161, 20);
		frmTransicionReconocimientoIndividual.getContentPane().add(comboBox);
		
		
		
		
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.setBounds(335, 227, 89, 23);
		frmTransicionReconocimientoIndividual.getContentPane().add(btnContinuar);
		
		JLabel lblEstadoFinal = new JLabel("Estado Final:");
		lblEstadoFinal.setBounds(56, 106, 77, 14);
		frmTransicionReconocimientoIndividual.getContentPane().add(lblEstadoFinal);
	}
}
