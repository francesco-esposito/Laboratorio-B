package interfacce;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import backenddb.ServerDBMSInterface;
import centrivaccinali.CentroVacc;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/**Interfaccia che implementa la funzionalità di ricerca di una struttura vaccinale 
 * per comune e tipologia.
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class rcomune {

	public JFrame frame;
	private JTable table;
	private JTable tableEA;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private ServerDBMSInterface db;
	private HashMap<Integer, CentroVacc> centri;

	/**
	 * Create the application.
	 */
	public rcomune() {
		initialize();
	}
	
	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String args, final ServerDBMSInterface db) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rcomune window = new rcomune();
					window.setDB(db);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 638);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel_7 = new JLabel("Non sono ancora state rilasciate informazioni per questo centro");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(10, 415, 736, 25);
		lblNewLabel_7.setVisible(false);
		frame.getContentPane().add(lblNewLabel_7);
		
		//pannello all'interno della tabella che permette di scorrere gli elementi
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 736, 95);
		frame.getContentPane().add(scrollPane);
		
		//tabella dove dovranno essere stampati i dati provenienti dal db
		final DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("KEY"); 
		model.addColumn("NOME");
		scrollPane.setViewportView(table);
		
		//pannello all'interno della tabella che permette di scorrere gli elementi
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 364, 736, 120);
		frame.getContentPane().add(scrollPane_1);
		
		//tabella riepilogo sintomi
		final DefaultTableModel EAmodel = new DefaultTableModel();
		tableEA = new JTable(EAmodel);
		EAmodel.addColumn("SINTOMO"); 
		EAmodel.addColumn("SEVERITA' MEDIA"); 
		EAmodel.addColumn("NUMERO CASI");
		scrollPane_1.setViewportView(tableEA);
		
		//permette di scrivere del testo all'interno
		textField = new JTextField();
		textField.setBounds(10, 74, 155, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		//permette di scrivere del testo all'interno
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(10, 336, 52, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("RICERCA PER COMUNE E TIPOLOGIA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 736, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_1 = new JLabel("Inserisci comune e tipologia centro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 42, 705, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("Tabella con i centri vaccinali trovati");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(419, 111, 327, 39);
		frame.getContentPane().add(lblNewLabel_2);
		
		final JLabel lblNewLabel_1_1 = new JLabel("Inserire la key del centro indicata nella tabella da");
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 290, 548, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		final JLabel lblNewLabel_8 = new JLabel("visualizzare informazioni aggiuntive");
		lblNewLabel_8.setEnabled(false);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 307, 464, 19);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		
		final JLabel lblNewLabel_3 = new JLabel("Tabella con informazioni agguntive");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(419, 329, 327, 39);
		frame.getContentPane().add(lblNewLabel_3);
		
		final JLabel lblNewLabel_4 = new JLabel("Informazioni aggiuntive");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setEnabled(false);
		lblNewLabel_4.setBounds(10, 255, 736, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		final JLabel lblNewLabel_5 = new JLabel("label vuota");
		lblNewLabel_5.setVisible(false);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 496, 736, 19);
		frame.getContentPane().add(lblNewLabel_5);
		
		final JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setVisible(false);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 519, 736, 19);
		frame.getContentPane().add(lblNewLabel_6);
		
		final JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setVisible(false);
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 541, 736, 19);
		frame.getContentPane().add(lblNewLabel_9);
		
		final JButton btnNewButton_1 = new JButton("Conferma");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// controllo validità key
				if( textField_1.getText().length() > 0 ) {
				
					lblNewLabel_3.setEnabled(true);
					scrollPane_1.setEnabled(true);
					lblNewLabel_5.setVisible(true);
					lblNewLabel_6.setVisible(true);
					lblNewLabel_9.setVisible(true);
					
					
					CentroVacc centro = centri.get(Integer.parseInt(textField_1.getText()));
					
					lblNewLabel_6.setText(centro.getNome().toUpperCase());
					
					lblNewLabel_5.setText(
							"Indirizzo struttura: "
							+  centro.getIndirizzo().getQualificatore()
							+ " " + centro.getIndirizzo().getNome().substring(0, 1).toUpperCase() + centro.getIndirizzo().getNome().substring(1)
							+ " " + centro.getIndirizzo().getCivico().toUpperCase()
							+ " " + centro.getIndirizzo().getComune().substring(0,1).toUpperCase() + centro.getIndirizzo().getComune().substring(1) + ","
							+ " (" + centro.getIndirizzo().getProvincia() + ")"
							+ " " + centro.getIndirizzo().getCAP());
					
					lblNewLabel_9.setText("Tipologia struttura: " + centro.getTipologia().toUpperCase());
					
					btnNewButton_1.setEnabled(false);
					textField_1.setEnabled(false);
					
					try {
						
						// richiamo metodo server per il prelevamento delle info riguardanti le sev. medie
						String[] sev = db.infoAVGCentro(centro.getNome());
						
						if(sev == null) {
							
							lblNewLabel_7.setVisible(true);
							scrollPane_1.setVisible(false);
							lblNewLabel_3.setVisible(false);
						}
						
						else {
							
							String[] sintomi = {"MAL DI TESTA", "FEBBRE", "DOLORI MUSCOLARI E ARTICOLARI"
									, "LINFOADENOPATIA", "TACHICARDIA", "CRISI IPERTENSIVA"};
							
							
							// richiamo metodo server per il prelevamento delle info aggiuntive
							double[] count = db.infoCOUNTCentro(centro.getNome());
							
							// stampa delle info aggiuntive nella tabella
							for(int i = 0; i < sintomi.length; i++) {
								
								EAmodel.insertRow(EAmodel.getRowCount(), 
										new Object[] { sintomi[i], 
												BigDecimal.valueOf(Double.parseDouble(sev[i])).setScale(1) , 
												BigDecimal.valueOf(count[i]).setScale(0) });
							}
							
						}
						
						
					} catch (RemoteException | SQLException e) {
						System.out.println("Errore");
					}
					
				}
				
				else 
					JOptionPane.showMessageDialog(null, "La KEY inserita non è valida",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(81, 336, 119, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ospedaliero", "Aziendale", "Hub"}));
		comboBox.setBounds(10, 103, 155, 21);
		frame.getContentPane().add(comboBox);
		
		final JButton btnNewButton = new JButton("Avvia la ricerca");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				//verific che il testo inserito abbia un minimo di senso
				if(textField.getText().length()>1) {
					
					// richiamo metodo server per la ricerca in base al nome inserito dall'utente
					// secondo parametro stringa contenente 0 per indicare al server la tipologia di ricerca
					// da effettuare
					// memorizzazione dati in una HashMap
					try {
						
						centri = db.cercaCentroVaccinale(textField.getText().toLowerCase(), 
								comboBox.getSelectedItem().toString().toLowerCase());
					
						// se la ricerca non ha prodotto risultati --> quindi hashmap non vuota
						if(!centri.isEmpty()) {
							
							lblNewLabel_1_1.setEnabled(true);
							lblNewLabel_8.setEnabled(true);
							textField_1.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							lblNewLabel_3.setEnabled(true);
							
							lblNewLabel_2.setEnabled(true);
							
							scrollPane.setEnabled(true);
							
							// per ogni centro memorizzato nella tabella hash
							for(CentroVacc c : centri.values()) {
								
								// inseriscilo nella JTable
								model.insertRow(model.getRowCount(), new Object[] { c.getId() , 
										c.getNome().toUpperCase() });
							}
							
							JOptionPane.showMessageDialog(null, "Ricerca conclusa con successo.");
							btnNewButton.setEnabled(false);
							textField.setEnabled(false);
							lblNewLabel_4.setEnabled(true);

					}
					else 
						//messaggio di errore bisogna reinserire i dati perche qualcosa non è andato a buon fine
						JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto risultati utili.",
							    "Inane error",
							    JOptionPane.ERROR_MESSAGE);
					
					} catch (RemoteException | SQLException e) {
						System.out.println("Errore");
					}
					
			} else
				JOptionPane.showMessageDialog(null, "I dati inseriti non sono corretti. Riprovare",
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
				
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(185, 103, 155, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("\uD83D\uDD19");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
				tipricerca.main(null, db, false);
			}
		});
		btnNewButton_4.setBounds(10, 570, 52, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accedi.accesso=false;
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(689, 570, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		

		
		
	}
}