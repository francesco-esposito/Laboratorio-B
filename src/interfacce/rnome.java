package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import backenddb.ServerDBMSInterface;
import centrivaccinali.CentroVacc;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di ricerca di una struttura vaccinale 
 * per nome.
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */
public class rnome {

	public JFrame frame;
	private JTextField textField;
	private JTable table;
	private JTable tableEA;
	private JTextField textField_1;
	private JTextField textField_2;
	private ServerDBMSInterface db;
	private HashMap<Integer, CentroVacc> centri;

	
	/**
	 * Create the application.
	 */
	public rnome() {
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
					rnome window = new rnome();
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
		frame.setBounds(100, 100, 770, 617);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("RICERCA PER NOME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 724, 25);
		frame.getContentPane().add(lblNewLabel);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_1 = new JLabel("Inserire il nome del centro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 35, 416, 25);
		frame.getContentPane().add(lblNewLabel_1);
		//permette di inseire del testo
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 70, 155, 19);
		frame.getContentPane().add(textField);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_2 = new JLabel("Tabella con i centri vaccinali trovati");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(409, 70, 337, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		//permette di scorrere gli elementi all'interno della tabella
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 101, 736, 94);
		frame.getContentPane().add(scrollPane);
		
		//tabella dove dovranno essere stampati i dati provenienti dal db
		final DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("KEY"); 
		model.addColumn("NOME");
		scrollPane.setViewportView(table);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_1_1 = new JLabel("Inserire la key del centro indicata nella tabella da");
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 254, 658, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		final JLabel lblNewLabel_8 = new JLabel("visualizzare informazioni aggiuntive");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setEnabled(false);
		lblNewLabel_8.setBounds(10, 272, 464, 19);
		frame.getContentPane().add(lblNewLabel_8);
		
		
		//permette di inserire del testo
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(10, 305, 52, 19);
		frame.getContentPane().add(textField_1);
		
		//permette di scorrere gli elementi all'interno della tabella
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(10, 334, 736, 120);
		frame.getContentPane().add(scrollPane_1);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_3 = new JLabel("Tabella con informazioni aggiuntive");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(409, 301, 337, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		//tabella riepilogo sintomi
		final DefaultTableModel EAmodel = new DefaultTableModel();
		tableEA = new JTable(EAmodel);
		EAmodel.addColumn("SINTOMO"); 
		EAmodel.addColumn("SEVERITA' MEDIA"); 
		EAmodel.addColumn("NUMERO CASI");
		scrollPane_1.setViewportView(tableEA);
		
		final JLabel lblNewLabel_4 = new JLabel("Informazioni aggiuntive");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(10, 215, 736, 29);
		frame.getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setEnabled(false);
		
		final JLabel lblNewLabel_7 = new JLabel("Non sono ancora state rilasciate segnalazioni di eventi avversi per questo centro");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(10, 394, 736, 25);
		lblNewLabel_7.setVisible(false);
		frame.getContentPane().add(lblNewLabel_7);
		
		final JLabel lblNewLabel_6 = new JLabel("label vuota");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setVisible(false);
		lblNewLabel_6.setBounds(10, 468, 736, 19);
		frame.getContentPane().add(lblNewLabel_6);
		
		final JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 491, 736, 19);
		lblNewLabel_5.setVisible(false);
		frame.getContentPane().add(lblNewLabel_5);
		
		final JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(10, 514, 736, 19);
		lblNewLabel_9.setVisible(false);
		frame.getContentPane().add(lblNewLabel_9);
		
		//bottone che permette di avere info aggiuntive viene reso visibile solo se prima si ha effettuato la ricerca
		//ed inserendo la key della tabella, il metodo richiamato deve avere restituito anceh qualcosa altrimenti non avrebbe senso renderlo visibile
		final JButton btnNewButton_1 = new JButton("Conferma");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				// controllo validità key
				if( textField_1.getText().length() > 0 ) {
				
					lblNewLabel_3.setEnabled(true);
					lblNewLabel_6.setVisible(true);
					lblNewLabel_9.setVisible(true);
					lblNewLabel_5.setVisible(true);
					scrollPane_1.setEnabled(true);
					lblNewLabel_3.setVisible(true);
					
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
							// no recensioni
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
		btnNewButton_1.setBounds(82, 303, 119, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		
	//bottone che permette di effettuare la ricerca in base al nome inserito
	//se va tutto  buon fine stampa un messaggio di conferma in caso contrario di errore lo fa con tutti i bottoni
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
						
						centri = db.cercaCentroVaccinale(textField.getText(), "0");
						
						// se la ricerca non ha prodotto risultati --> quindi hashmap non vuota
						if(!centri.isEmpty()) {
							
							lblNewLabel_1_1.setEnabled(true);
							lblNewLabel_8.setEnabled(true);
							textField_1.setEnabled(true);
							btnNewButton_1.setEnabled(true);
							
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
		btnNewButton.setBounds(186, 70, 155, 21);
		frame.getContentPane().add(btnNewButton);
		
		//bottone che permette di tornare alla schermata precedente 
		JButton btnNewButton_4 = new JButton("\uD83D\uDD19");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				tipricerca.main(null, db, false);
			}
		});
		btnNewButton_4.setBounds(10, 549, 52, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		//bottone che permette di tornare alla home il login viene annullato
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(689, 549, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		
		
		
	
	}
}

