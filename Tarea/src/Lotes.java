import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class Lotes {

	public JFrame frmReconocimientoPorLotes;
	private JTable table_1;

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
		frmReconocimientoPorLotes.setBounds(100, 100, 650, 500);
		frmReconocimientoPorLotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReconocimientoPorLotes.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 197);
		frmReconocimientoPorLotes.getContentPane().add(scrollPane);

		table_1 = new JTable();
		DefaultTableModel model;
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"(qi, si)", "(qj, sj, movimiento)"
			}
			) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			});
		model = (DefaultTableModel) table_1.getModel();
		scrollPane.setVisible(false);

		
		for (int j=0;j<iTrLot.resultados.size();j++ ) {
			System.out.println("hola");
			model.insertRow(model.getRowCount(),new Object[] {"("+iTrLot.cadenas_ingresadas.get(j)+")","("+iTrLot.resultados.get(j)+")"});
		}
		table_1.setModel(model);
		

		scrollPane.setVisible(true); 
		JButton btnRunMt = new JButton("Run MT");
		btnRunMt.setBounds(10, 227, 89, 23);
		frmReconocimientoPorLotes.getContentPane().add(btnRunMt);
	}
}
