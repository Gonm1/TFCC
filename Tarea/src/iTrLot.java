import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class iTrLot {

	public JFrame frmTransicionLotes;
	public static ArrayList<String> cadenas_ingresadas=new ArrayList<String>();
	public static ArrayList<Boolean> resultados=new ArrayList<Boolean>();
	
	public iTrLot() {
		initialize();
	}
	
	private void initialize() {
		frmTransicionLotes = new JFrame();
		frmTransicionLotes.setResizable(false);
		frmTransicionLotes.setTitle("Transicion Lotes");
		frmTransicionLotes.setBounds(100, 100, 450, 300);
		frmTransicionLotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTransicionLotes.getContentPane().setLayout(null);
		frmTransicionLotes.setLocationRelativeTo(null);
		
		/*CREA COMBO BOX PARA SELECCIONAR EL ESTADO FINAL
		 * Y LO LLENA CON TODOS LOS ESTADOS DISPONIBLES EN LA MAQUINA
		 */
		String[] opciones=Metodos.todos_estados(InterfaceUno.mt);
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		comboBox.setBounds(118, 191, 165, 20);
		frmTransicionLotes.getContentPane().add(comboBox);
		
		/*CREA LABEL ESTADO FINAL*/
		JLabel lblEstadoFinal = new JLabel("Estado Final:");
		lblEstadoFinal.setBounds(35, 194, 73, 14);
		frmTransicionLotes.getContentPane().add(lblEstadoFinal);

		/*CREA LABEL DE CADENAS*/
		JLabel lblCadena = new JLabel("Cadenas:");
		lblCadena.setBounds(35, 66, 52, 14);
		frmTransicionLotes.getContentPane().add(lblCadena);

		/*CREA LABEL DE RECONOCIMIENTO POR LOTES*/
		JLabel lblLotes = new JLabel("Reconocimiento por lotes");
		lblLotes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLotes.setBounds(95, 11, 280, 40);
		frmTransicionLotes.getContentPane().add(lblLotes);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 62, 271, 97);
		frmTransicionLotes.getContentPane().add(scrollPane);
		
		/*CREA TEXT AREA*/
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		/*CREA EL BOTON RUN MT*/
		JButton btnRunMt = new JButton("Run MT");
		btnRunMt.setBounds(335, 227, 89, 23);
		frmTransicionLotes.getContentPane().add(btnRunMt);
		
		/*EVENTO DE CLICK EN BOTON RUN MT*/
		btnRunMt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*GUARDA TODAS LAS CADENAS INGRESADAS EN UN ARRAY DE STRINGS, SEPARANDO POR COMAS*/
				String[] aux2=textArea.getText().split(",");
				for(int i=0 ; i<aux2.length ; i++){
					cadenas_ingresadas.add(aux2[i]);
				}
				
				/*GUARDA EN LA MAQUINA EL ESTADO FINAL SELECCIONADO EN EL COMBOBOX*/
				String e_final = comboBox.getSelectedItem().toString();
				for(Estado o: InterfaceUno.mt){
					if(o.qi.equals(e_final)) o.estadoFinal=true;
				}
				
				/*DECLARA LA CINTA DE LA MAQUINA A UTILIZAR*/
				Cinta cadena = new Cinta();
				for(String g: cadenas_ingresadas){
					/* RECORRE LAS CADENAS PREVIAMENTE INGRESADAS
					 * GUARDANDO EL RESULTADO QUE ENTREGA LA MAQUINA DE TURING
					 */
					cadena = Metodos.CrearCinta(g);
					Boolean auxiliar=Metodos.verificarCadena(InterfaceUno.mt,cadena,"0",cadena.inicio);//se asume que el estado inicial es 0;
					resultados.add(auxiliar);
					cadena=null;
				}
				
				/*CREA OBJETO DE INTERFAZ SIGUIENTE Y CAMBIA LAS VISIBILIDADES*/
				Lotes winda=new Lotes();
				frmTransicionLotes.setVisible(false);
				winda.frmReconocimientoPorLotes.setVisible(true);

			}
		});

	}
}
