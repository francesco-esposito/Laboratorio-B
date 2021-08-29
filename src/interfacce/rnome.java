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
//permette di effettuare la ricerca per nome e successivamente la selezione per vedere le info aggiuntive 
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
	public static void main(String[] args, final ServerDBMSInterface db) {
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
		frame.setBounds(100, 100, 770, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Ricerca per nome ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 416, 25);
		frame.getContentPane().add(lblNewLabel);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_1 = new JLabel("Inserisci il nome del centro per il quale vuoi effettuare la ricerca");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 35, 416, 25);
		frame.getContentPane().add(lblNewLabel_1);
		//permette di inseire del testo
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 70, 96, 19);
		frame.getContentPane().add(textField);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_2 = new JLabel("Tabella con i centri vaccinali trovati");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(403, 70, 343, 21);
		frame.getContentPane().add(lblNewLabel_2);
		
		//permette di scorrere gli elementi all'interno della tabella
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 101, 736, 120);
		frame.getContentPane().add(scrollPane);
		
		//tabella dove dovranno essere stampati i dati provenienti dal db
		final DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("KEY"); 
		model.addColumn("NOME");
		scrollPane.setViewportView(table);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_1_1 = new JLabel("Inserire la key del centro per informazioni aggiuntive");
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 265, 416, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		//permette di inserire del testo
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(10, 288, 96, 19);
		frame.getContentPane().add(textField_1);
		
		//permette di scorrere gli elementi all'interno della tabella
		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(10, 317, 736, 94);
		frame.getContentPane().add(scrollPane_1);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_3 = new JLabel("Tabella con info agguntive");
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(409, 274, 325, 39);
		frame.getContentPane().add(lblNewLabel_3);
		
		//tabella riepilogo sintomi
		final DefaultTableModel EAmodel = new DefaultTableModel();
		tableEA = new JTable(EAmodel);
		EAmodel.addColumn("SINTOMO"); 
		EAmodel.addColumn("SEVERITA' MEDIA"); 
		EAmodel.addColumn("NUMERO CASI");
		scrollPane_1.setViewportView(tableEA);
		
		//bottone che permette di avere info aggiuntive viene reso visibile solo se prima si ha effettuato la ricerca
		//ed inserendo la key della tabella, il metodo richiamato deve avere restituito anceh qualcosa altrimenti non avrebbe senso renderlo visibile
		final JButton btnNewButton_1 = new JButton("Visualizza informazioni aggiuntive");
		
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				// controllo validità key
				if( textField_1.getText().length() > 0 ) {
				
					lblNewLabel_3.setEnabled(true);
					scrollPane_1.setEnabled(true);
					
					CentroVacc centro = centri.get(Integer.parseInt(textField_1.getText()));
					
					// INFO DA STAMPARE - CREAZIONE LABEL
					centro.tostring();
					
					try {
						
						// richiamo metodo server per il prelevamento delle info riguardanti le sev. medie
						String[] sev = db.infoAVGCentro(centro.getNome());
						
						if(sev == null) {
							
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
		btnNewButton_1.setBounds(116, 288, 231, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		
		//bottone che permette di andare avanti e ricercare informazioni successive
		final JButton btnNewButton_2 = new JButton("Visualizza informazioni informazioni aggiuntive sul centro ricercato");
		
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel_1_1.setEnabled(true);
				textField_1.setEnabled(true);
				btnNewButton_1.setEnabled(true);
				
			}
		});
		
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 234, 337, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_4 = new JLabel("Per inserire effetti avversi accedi");
		lblNewLabel_4.setEnabled(true);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(363, 421, 371, 13);
		frame.getContentPane().add(lblNewLabel_4);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_6 = new JLabel("Inserire la key del centro per inserire eff avversi");
		lblNewLabel_6.setEnabled(false);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 421, 325, 13);
		frame.getContentPane().add(lblNewLabel_6);
		//permette di inserire del testo
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(10, 443, 96, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		final JLabel lblNewLabel_5 = new JLabel("Ricerca e seleziona il C.V. per inserire effetti avversi");
		lblNewLabel_5.setEnabled(false);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(363, 444, 371, 13);
		frame.getContentPane().add(lblNewLabel_5);
		
		//bottone che permette di inseire effetti avversi diventa disponible solo se si ha effettuato l'accesso e si ha gia effettuato la ricerca di un centro 
		final JButton btnNewButton_3 = new JButton("Inserisci segnalazioni per eventi avversi");
		btnNewButton_3.setEnabled(false);	
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//inseffavv iea = new inseffavv();
				//iea.main(null);
			}
		});
		
		btnNewButton_3.setBounds(10, 464, 337, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		
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
						
						centri = db.searchCentro(textField.getText(), "0");
						
						// se la ricerca non ha prodotto risultati --> quindi hashmap non vuota
						if(!centri.isEmpty()) {
							
							//se l'accesso é stato effettuato 
							//setta visibile le parti riservate solo a chi accede cioe l'inserimento di effetti avversi
							if(accedi.accesso){
								btnNewButton_3.setEnabled(true);
								lblNewLabel_5.setEnabled(true);
								textField_2.setEnabled(true);
								lblNewLabel_6.setEnabled(true);
								lblNewLabel_4.setEnabled(false);
								
							} else {
								lblNewLabel_4.setEnabled(true);
							}
							
							lblNewLabel_2.setEnabled(true);
							btnNewButton_2.setEnabled(true);
							scrollPane.setEnabled(true);
							
							// per ogni centro memorizzato nella tabella hash
							for(CentroVacc c : centri.values()) {
								
								// inseriscilo nella JTable
								model.insertRow(model.getRowCount(), new Object[] { c.getId() , 
										c.getNome().toUpperCase() });
							}
							
							JOptionPane.showMessageDialog(null, "Ricerca conclusa con successo.");
							btnNewButton.setVisible(false);

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
		btnNewButton.setBounds(126, 67, 155, 21);
		frame.getContentPane().add(btnNewButton);
		
		//bottone che permette di tornare alla schermata precedente 
		JButton btnNewButton_4 = new JButton("\uD83D\uDD19");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				tipricerca.main(null, db);
			}
		});
		btnNewButton_4.setBounds(628, 0, 52, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		//bottone che permette di tornare alla home il login viene annullato
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				accedi.accesso=false;
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(677, 0, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_10 = new JLabel("tornando alla home dovrai effetttuare nuovamente l'accesso");
		lblNewLabel_10.setBounds(341, 4, 288, 13);
		frame.getContentPane().add(lblNewLabel_10);
	}
}

