/*
 * Integrantes:
 * 	- Manuel Lepe Fa�ndez.
 * 	- Gonzalo Miranda Cabrera.
*/

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JFileChooser;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class InterfaceUno {

	public JFrame frmMquinaDeTuring;
	private JTable table_1;

	public static ArrayList<Estado> mt= new ArrayList<Estado>();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceUno window = new InterfaceUno();
					window.frmMquinaDeTuring.setVisible(true);
					window.frmMquinaDeTuring.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfaceUno() {
		initialize();
	}
	
	private void initialize() {
		frmMquinaDeTuring = new JFrame();
		frmMquinaDeTuring.setResizable(false);
		frmMquinaDeTuring.setTitle("M\u00E1quina de Turing - Proyecto FCC");
		frmMquinaDeTuring.setBounds(100, 100, 450, 300);
		frmMquinaDeTuring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*CREA BARRA DE MEN� Y LA AGREGA AL JFRAME*/
		JMenuBar menuBar = new JMenuBar();
		frmMquinaDeTuring.setJMenuBar(menuBar);
		
		/*A�ADE UN MEN� LLAMADO OPERACIONES*/
		JMenu mnFile = new JMenu("Operaciones");
		menuBar.add(mnFile);
		
		/*A�ADE OPCION DE CARGAR TRANSICIONES*/
		JMenuItem mntmCargarTransiciones = new JMenuItem("Cargar transiciones");
		mnFile.add(mntmCargarTransiciones);
		

		/*BOTON RECONOCIMIENTO INDIVIDUAL*/
		JMenuItem mntmNewMenuItem = new JMenuItem("Reconocimiento individual");
		mnFile.add(mntmNewMenuItem);
		mntmNewMenuItem.setEnabled(false);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* 
				 * EVENTO DE CLICK EN RECONOCIMIENTO IDIVIDUAL
				 * CREA UNA NUEVA VENTANA DE TRANSICION Y CAMBIA LA VISIBILIDAD
				 * DE LA INTERFAZ PRINCIPAL
				 */
				iTrInd Indiv = new iTrInd();
				Indiv.frmTransicionReconocimientoIndividual.setVisible(true);
				Indiv.frmTransicionReconocimientoIndividual.setLocationRelativeTo(null);
				frmMquinaDeTuring.setVisible(false);
			}
		});
		
		/*CREA OPCION DE RECONOCIMIENTO POR LOTES*/
		JMenuItem mntmReconocimientoPorLotes = new JMenuItem("Reconocimiento por lotes");
		mnFile.add(mntmReconocimientoPorLotes);
		mntmReconocimientoPorLotes.setEnabled(false);
		frmMquinaDeTuring.getContentPane().setLayout(null);
		
		/*A�ADE LABEL DE TRANSICIONES CARGADAS*/
		JLabel lblTransicionesCargadas = new JLabel("TRANSICIONES CARGADAS");
		lblTransicionesCargadas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTransicionesCargadas.setBounds(76, 11, 287, 40);
		frmMquinaDeTuring.getContentPane().add(lblTransicionesCargadas);
		lblTransicionesCargadas.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 414, 167);
		frmMquinaDeTuring.getContentPane().add(scrollPane);

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
		
		/*EVENTO DE CLICK EN CARGAR TRANSICIONES*/
		mntmCargarTransiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("user.home"));
				fc.setDialogTitle("FILE CHOOSER");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.showOpenDialog(null);
				mt = Metodos.Leerxml(fc.getSelectedFile().getAbsolutePath());

				//llena la tabla
				for(Estado o: mt){
					try{
						for (Transicion t: o.todasTransiciones) {
							model.insertRow(model.getRowCount(),new Object[] {"("+o.qi +", "+ t.si+")","("+t.qj +", "+ t.sj +", "+ t.movimiento+")"});
						}
					}catch(Exception spe){
						//estado sin transicion
					}

				}

				table_1.setModel(model);
				lblTransicionesCargadas.setVisible(true);
				mntmReconocimientoPorLotes.setEnabled(true);
				mntmNewMenuItem.setEnabled(true); //Reconocimiento Individual
				scrollPane.setVisible(true); // tabla

			}
		});
		
		/*EVENTO DE CLICK EN RECONOCIMIENTO POR LOTES*/
		mntmReconocimientoPorLotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iTrLot window = new iTrLot();
				window.frmTransicionLotes.setVisible(true);
				window.frmTransicionLotes.setLocationRelativeTo(null);
				frmMquinaDeTuring.setVisible(false);
			}
		});
	}
}
