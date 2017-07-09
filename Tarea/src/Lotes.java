/*
 * Integrantes:
 * 	- Manuel Lepe Faúndez.
 * 	- Gonzalo Miranda Cabrera.
*/
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
		frmReconocimientoPorLotes.setResizable(false);
		frmReconocimientoPorLotes.setTitle("Reconocimiento por lotes");
		frmReconocimientoPorLotes.setBounds(100, 100, 450, 300);
		frmReconocimientoPorLotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReconocimientoPorLotes.getContentPane().setLayout(null);
		frmReconocimientoPorLotes.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 239);
		frmReconocimientoPorLotes.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		DefaultTableModel model;
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Cadena", "Aceptada/Rechazada)"
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
		/*
		 * SE LLENA LA TABLA CON LAS CADENAS INGRESADAS Y SUS RESPECTIVOS RESULTADOS
		 */
		for (int j=0;j<iTrLot.resultados.size();j++ ) {
			model.insertRow(model.getRowCount(),new Object[] {"("+iTrLot.cadenas_ingresadas.get(j)+")","("+iTrLot.resultados.get(j)+")"});
		}
		iTrLot.cadenas_ingresadas.clear();
		table_1.setModel(model);
		scrollPane.setVisible(true);
	}
}
