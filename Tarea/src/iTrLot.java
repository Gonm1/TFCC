import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
		//InterfaceUno.mt
		
		JComboBox comboBox = new JComboBox(Metodos.todos_estados(InterfaceUno.mt));
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

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String e_final=comboBox.getSelectedItem().toString();
				for(Estado o: InterfaceUno.mt){
					if(o.qi.equals(e_final)) o.estadoFinal=true;
				}
		}
		});

		btnRunMt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] aux2=textArea.getText().split(",");
				for(int i=0;i<aux2.length;i++){
					cadenas_ingresadas.add(aux2[i]);
				}

			for(String o: cadenas_ingresadas){
				Cinta cadena=Metodos.CrearCinta(o);
				resultados.add(Metodos.verificarCadena(InterfaceUno.mt,cadena,"0",cadena.inicio));//se asume que el estado inicial es 0;
			}

			for (Boolean o:resultados) {
			 	System.out.println(o);
			 } 

			 Lotes winda=new Lotes();
			 frmTransicionLotes.setVisible(false);
			 winda.frmReconocimientoPorLotes.setVisible(true);

		}
		});

	}
}
