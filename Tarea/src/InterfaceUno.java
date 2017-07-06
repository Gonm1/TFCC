import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;

public class InterfaceUno {

	private JFrame frmMquinaDeTuring;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceUno window = new InterfaceUno();
					window.frmMquinaDeTuring.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceUno() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMquinaDeTuring = new JFrame();
		frmMquinaDeTuring.setTitle("M\u00E1quina de Turing - Proyecto FCC");
		frmMquinaDeTuring.setBounds(100, 100, 450, 300);
		frmMquinaDeTuring.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuItem mntmCargarTransiciones = new JMenuItem("Cargar transiciones");
		
		JMenuBar menuBar = new JMenuBar();
		frmMquinaDeTuring.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Operaciones");
		menuBar.add(mnFile);
		
		mnFile.add(mntmCargarTransiciones);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Reconocimiento individual");
		mnFile.add(mntmNewMenuItem);
		mntmNewMenuItem.setEnabled(false);
		JMenuItem mntmReconocimientoPorLotes = new JMenuItem("Reconocimiento por lotes");
		mnFile.add(mntmReconocimientoPorLotes);
		mntmReconocimientoPorLotes.setEnabled(false);
		frmMquinaDeTuring.getContentPane().setLayout(null);
		
		JLabel lblTransicionesCargadas = new JLabel("TRANSICIONES CARGADAS");
		lblTransicionesCargadas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTransicionesCargadas.setBounds(76, 11, 287, 40);
		frmMquinaDeTuring.getContentPane().add(lblTransicionesCargadas);
		lblTransicionesCargadas.setVisible(false);
		
		
		
		
		mntmCargarTransiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("user.home"));
				fc.setDialogTitle("This is a JFileChooser");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.showOpenDialog(null);
				//if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				ArrayList<Estado> mt = Metodos.Leerxml(fc.getSelectedFile().getAbsolutePath());
				//}
				
				table = new JTable();
				table.setToolTipText("");
				table.setBounds(62, 62, 303, 125);
				frmMquinaDeTuring.getContentPane().add(table);
				
				lblTransicionesCargadas.setVisible(true);
				mntmReconocimientoPorLotes.setEnabled(true);
				mntmNewMenuItem.setEnabled(true); //Reconocimiento Individual
			}
		});
		

	}
}
