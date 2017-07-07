import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Lotes {

	private JFrame frmReconocimientoPorLotes;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lotes window = new Lotes();
					window.frmReconocimientoPorLotes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lotes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReconocimientoPorLotes = new JFrame();
		frmReconocimientoPorLotes.setTitle("Reconocimiento por lotes");
		frmReconocimientoPorLotes.setBounds(100, 100, 450, 300);
		frmReconocimientoPorLotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReconocimientoPorLotes.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 197);
		frmReconocimientoPorLotes.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[/*AGREGAR EL NUMERO DE CADENAS INGRESADAS*/][/*AGREGAR EL NUMERO DE CADENAS INGRESADAS*/],
			new String[] {
				"Cadena", "Aceptada/Rechazada"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnRunMt = new JButton("Run MT");
		btnRunMt.setBounds(10, 227, 89, 23);
		frmReconocimientoPorLotes.getContentPane().add(btnRunMt);
	}
}
