import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class iTrInd {

	public JFrame frmTransicionReconocimientoIndividual;
	private JTextField textFieldInd;
	private JComboBox comboBox;
	public static String cadena_ingresada;
	public static Boolean resultado;

	/**
	 * Launch the application.
	 */

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

		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadena_ingresada=textFieldInd.getText();
				String e_final=comboBox.getSelectedItem().toString();
				for(Estado o: InterfaceUno.mt){
					if(o.qi.equals(e_final)) o.estadoFinal=true;
				}
				Cinta cadena=new Cinta();
				cadena=Metodos.CrearCinta(cadena_ingresada);
				resultado=Metodos.verificarCadena(InterfaceUno.mt,cadena,"0",cadena.inicio);//se asume que el estado inicial es 0;
				Indiv window = new Indiv();
				window.frmReconocimientoIndividual.setVisible(true);
				frmTransicionReconocimientoIndividual.setVisible(false);

			}
		});
	}
}
