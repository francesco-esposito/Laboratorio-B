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
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di ricerca di una struttura vaccinale 
 * per nome per l'inserimento di eventi avversi.
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class rnomeavv {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private ServerDBMSInterface db;
	private HashMap<Integer, CentroVacc> centri;
	private JTable table;
	private String userName;

	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(final String args, final ServerDBMSInterface db) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					rnomeavv window = new rnomeavv(args);
					window.frame.setVisible(true);
					window.setDB(db);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public rnomeavv(String userName) {
		this.userName = userName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RICERCA PER NOME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 724, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Inserire il nome del centro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 35, 416, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("Tabella con i centri vaccinali trovati");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBounds(403, 70, 343, 21);
		frame.getContentPane().add(lblNewLabel_2);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 101, 736, 160);
		frame.getContentPane().add(scrollPane);
		
		//tabella dove dovranno essere stampati i dati provenienti dal db
		final DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("KEY"); 
		model.addColumn("NOME");
		scrollPane.setViewportView(table);
		
		final JLabel lblNewLabel_1_1 = new JLabel("Specifica il centro vaccinale per cui vuoi segnalare eventi avversi utilizzando l'apposita KEY mostrata nella tabella. ");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setBounds(10, 294, 736, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(10, 347, 52, 22);
		frame.getContentPane().add(textField_1);
		
		final JButton btnNewButton_1 = new JButton("Conferma");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// controllo validità key
				if( textField_1.getText().length() > 0 ) {
					CentroVacc centro = centri.get(Integer.parseInt(textField_1.getText()));
					frame.setVisible(false);
					evavv.main(userName, db, centro.getNome());
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.setBounds(72, 347, 96, 22);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("\uD83D\uDD19");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				tipricerca.main(null, db, true);
			}
		});
		btnNewButton_4.setBounds(10, 401, 52, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(689, 401, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 67, 169, 22);
		frame.getContentPane().add(textField);
		

		final JLabel lblNewLabel_3 = new JLabel("Il sistema \u00E8 in grado di annullare segnalazioni di utenti che non si sono vaccinati in quella struttura.");
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 313, 672, 21);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		final JButton btnNewButton_2 = new JButton("Avvia la ricerca");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//verific che il testo inserito abbia un minimo di senso
				if(textField.getText().length()>1) {
					
					// richiamo metodo server per la ricerca in base al nome inserito dall'utente
					// secondo parametro stringa contenente 0 per indicare al server la tipologia di ricerca
					// da effettuare
					// memorizzazione dati in una HashMap
					try {
						
						centri = db.cercaCentroVaccinale(textField.getText().toLowerCase(), "0");
					
						// se la ricerca non ha prodotto risultati --> quindi hashmap non vuota
						if(!centri.isEmpty()) {
							
							lblNewLabel_1_1.setEnabled(true);
							lblNewLabel_3.setEnabled(true);
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
							btnNewButton_2.setEnabled(false);

					}
					else 
						//messaggio di errore bisogna reinserire i dati perche qualcosa non è andato a buon fine
						JOptionPane.showMessageDialog(null, "La ricerca non ha prodotto risultati utili.",
							    "Inane error",
							    JOptionPane.ERROR_MESSAGE);
					
					} catch (RemoteException | SQLException e1) {
						System.out.println("Errore");
					}
					
			} else
				JOptionPane.showMessageDialog(null, "I dati inseriti non sono corretti. Riprovare",
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(200, 66, 169, 25);
		frame.getContentPane().add(btnNewButton_2);
		
	}
}
