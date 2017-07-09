import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Indiv {

	public JFrame frmReconocimientoIndividual;
	//Variables staticas que se usan:
	public static int z=0;
	public static ArrayList<Timer> temporizadores= new ArrayList<Timer>();
	
	public Indiv() {
		initialize();
	}

	private void initialize(){
		//se define el Frame y sus carcateristicas.
		frmReconocimientoIndividual = new JFrame();
		frmReconocimientoIndividual.setTitle("Reconocimiento Individual");
		frmReconocimientoIndividual.setResizable(false);
		frmReconocimientoIndividual.setBounds(100, 100, 600, 544);
		frmReconocimientoIndividual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReconocimientoIndividual.getContentPane().setLayout(null);
		frmReconocimientoIndividual.setLocationRelativeTo(null);
		//titulo cinta:
		JLabel lblCinta = new JLabel("Cinta:");
		lblCinta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCinta.setBounds(152, 43, 60, 20);
		frmReconocimientoIndividual.getContentPane().add(lblCinta);
		//titulo estado actual
		JLabel lblEstadoActual = new JLabel("Estado actual:");
		lblEstadoActual.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstadoActual.setBounds(241, 115, 103, 20);
		frmReconocimientoIndividual.getContentPane().add(lblEstadoActual);
		//titulo proximo movimiento
		JLabel lblPosiblesMovimientos = new JLabel("Pr\u00F3ximo Movimiento:");
		lblPosiblesMovimientos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPosiblesMovimientos.setBounds(222, 185, 153, 20);
		frmReconocimientoIndividual.getContentPane().add(lblPosiblesMovimientos);
		//titulo principal:
		JLabel titulo = new JLabel("Para la Cadena "+iTrInd.cadena_ingresada);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		titulo.setBounds(144, 11, 299, 28);
		frmReconocimientoIndividual.getContentPane().add(titulo);
		//Se muestra el resultado despues de terminar los movimientos 
		JLabel lblLaCadena;
		if(iTrInd.resultado) lblLaCadena = new JLabel("La cadena es aceptada por el lenguaje.");
		else lblLaCadena = new JLabel("La cadena NO es aceptada por el lenguaje.");
		lblLaCadena.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLaCadena.setBounds(150, 400, 333, 28);
		lblLaCadena.setVisible(false);
		frmReconocimientoIndividual.getContentPane().add(lblLaCadena);
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
		//donde se mostrara el estado actual:
		JTextPane TextEstado = new JTextPane();
		TextEstado.setEditable(false);
		TextEstado.setFont(new Font("Tahoma", Font.PLAIN, 20));
		TextEstado.setBounds(269, 146, 30, 28);
		frmReconocimientoIndividual.getContentPane().add(TextEstado);
		//Style de la cinta:
		StyleContext sc = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(sc);
		JTextPane TextCinta = new JTextPane(doc);
		frmReconocimientoIndividual.getContentPane().add(TextCinta);
		TextCinta.setBounds(150, 64, 293, 47);
		TextCinta.setEditable(false);
		TextCinta.setFont(new Font("Tahoma", Font.PLAIN, 34));
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++///
		//la barrita
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(222, 215, 146, 157);
		frmReconocimientoIndividual.getContentPane().add(scrollPane);
		//Tabla que muestra el contenido.
		JTable table_1 = new JTable();
		DefaultTableModel model;
		scrollPane.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"(qj, sj, movimiento)"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		//Boton pausa:
		JButton btnPausa = new JButton("Pausa");
		btnPausa.setBounds(22, 446, 73, 23);
		frmReconocimientoIndividual.getContentPane().add(btnPausa);
		model = (DefaultTableModel) table_1.getModel();
		//Boton play
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(111, 446, 89, 23);
		frmReconocimientoIndividual.getContentPane().add(btnPlay);

		//Timer para que se vea dinamico:
		Timer times;
		TimerTask tarea;
		times = new Timer();
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++
		tarea= new TimerTask(){
			public void run(){
				if(z<Metodos.movimiento_cinta.size()){
					String s = Metodos.movimiento_cinta.get(z);
					TextCinta.setText("");
					try{
						doc.insertString(0, s, null);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}

					// Define el estilo
					Style rojo = sc.addStyle("ConstantWidth", null);
					StyleConstants.setForeground(rojo, Color.red);
					// Aplica el estilo
					doc.setCharacterAttributes(Metodos.numero_cabezal.get(z), 1, rojo, false);	
			
					TextEstado.setText(Metodos.estado_actual.get(z));
					model.insertRow(model.getRowCount(),new Object[] {Metodos.proximo_movimiento.get(z)});
					z++;
				}else{
					lblLaCadena.setVisible(true);
					times.cancel();
				}
			}
		};
		times.scheduleAtFixedRate(tarea,0,2000);
		//*++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//Botn replay;
		JButton btnNewButton = new JButton("Replay Run MT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				times.cancel();
				if (temporizadores.size()!=0)temporizadores.get(temporizadores.size()-1).cancel();
				btnPlay.setEnabled(false);
				btnPausa.setEnabled(true);
				z=0;
				for(int g=0;g<table_1.getRowCount();g++){
					model.removeRow(g);
					g-=1;
				}
				Timer times2 = new Timer();
				temporizadores.add(times2);
				TimerTask tarea2;
				tarea2= new TimerTask(){
					public void run(){
						if(z<Metodos.movimiento_cinta.size()){
							
							String s = Metodos.movimiento_cinta.get(z);
							TextCinta.setText("");
							try{
								doc.insertString(0, s, null);
							}catch(Exception e){
								System.out.println(e.getMessage());
							}

							// Define el estilo
							Style rojo = sc.addStyle("ConstantWidth", null);
							StyleConstants.setForeground(rojo, Color.red);
							// Aplica el estilo
							doc.setCharacterAttributes(Metodos.numero_cabezal.get(z), 1, rojo, false);	

							TextEstado.setText(Metodos.estado_actual.get(z));
							model.insertRow(model.getRowCount(),new Object[] {Metodos.proximo_movimiento.get(z)});
							z++;
						}else{
							lblLaCadena.setVisible(true);
							temporizadores.get(temporizadores.size()-1).cancel();
						}
					}
				};
				temporizadores.get(temporizadores.size()-1).scheduleAtFixedRate(tarea2,0,2000);

			}
		});
		btnNewButton.setBounds(290, 443, 130, 28);
		frmReconocimientoIndividual.getContentPane().add(btnNewButton);

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		btnPlay.setEnabled(false);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnPlay.setEnabled(false);
				btnPausa.setEnabled(true);
				Timer times2 = new Timer();
				temporizadores.add(times2);
				TimerTask tarea2;
				tarea2= new TimerTask(){
					public void run(){
						if(z<Metodos.movimiento_cinta.size()){
							String s = Metodos.movimiento_cinta.get(z);
							TextCinta.setText("");
							try{
								doc.insertString(0, s, null);
							}catch(Exception e){
								System.out.println(e.getMessage());
							}

							// Define el estilo
							Style rojo = sc.addStyle("ConstantWidth", null);
							StyleConstants.setForeground(rojo, Color.red);
							// Aplica el estilo
							doc.setCharacterAttributes(Metodos.numero_cabezal.get(z), 1, rojo, false);	

							TextEstado.setText(Metodos.estado_actual.get(z));
							model.insertRow(model.getRowCount(),new Object[] {Metodos.proximo_movimiento.get(z)});
							z++;
						}else{
							lblLaCadena.setVisible(true);
							temporizadores.get(temporizadores.size()-1).cancel();
						}
					}
				};
				temporizadores.get(temporizadores.size()-1).scheduleAtFixedRate(tarea2,0,2000);
			}
		});
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++///
		//boton pausa:
		btnPausa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				times.cancel();
				if (temporizadores.size()!=0)temporizadores.get(temporizadores.size()-1).cancel();
				btnPausa.setEnabled(false);
				btnPlay.setEnabled(true);
			}
		});
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++///
	}
}
