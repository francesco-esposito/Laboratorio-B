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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di ricerca di una struttura vaccinale 
 * per comune e tipologia per l'inserimento di eventi avversi.
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class rcomuneavv {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private ServerDBMSInterface db;
	private JTable table;
	private String userName;
	private HashMap<Integer, CentroVacc> centri;

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
					rcomuneavv window = new rcomuneavv(args);
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
	public rcomuneavv(String userName) {
		this.userName = userName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 492);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel_1 = new JLabel("Inserisci comune e tipologia centro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 42, 705, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("RICERCA PER COMUNE E TIPOLOGIA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 10, 736, 25);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 74, 158, 19);
		frame.getContentPane().add(textField);
		
		final JLabel lblNewLabel_2 = new JLabel("Tabella con i centri vaccinali trovati");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBounds(403, 111, 343, 39);
		frame.getContentPane().add(lblNewLabel_2);
		
		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 150, 736, 154);
		frame.getContentPane().add(scrollPane);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(10, 368, 52, 21);
		frame.getContentPane().add(textField_1);
		
		//tabella dove dovranno essere stampati i dati provenienti dal db
		final DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("KEY"); 
		model.addColumn("NOME");
		scrollPane.setViewportView(table);
		
		final JLabel lblNewLabel_1_1 = new JLabel("Specifica il centro vaccinale per cui vuoi segnalare eventi avversi utilizzando l'apposita KEY mostrata nella tabella. \r\n");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setEnabled(false);
		lblNewLabel_1_1.setBounds(10, 302, 736, 21);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		final JLabel lblNewLabel_3 = new JLabel("Il sistema \u00E8 in grado di annullare segnalazioni di utenti che non si sono vaccinati in quella struttura.");
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 314, 720, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ospedaliero", "Aziendale", "Hub"}));
		comboBox.setBounds(10, 103, 158, 21);
		frame.getContentPane().add(comboBox);
		
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
		btnNewButton_1.setBounds(72, 368, 96, 21);
		frame.getContentPane().add(btnNewButton_1);
		
		final JButton btnNewButton = new JButton("Avvia la ricerca");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
						
						lblNewLabel_1.setEnabled(true);
						lblNewLabel_3.setEnabled(true);
						lblNewLabel_1_1.setEnabled(true);
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
						comboBox.setEnabled(false);

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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(188, 103, 158, 21);
		frame.getContentPane().add(btnNewButton);
		
		
		
		JButton btnNewButton_4 = new JButton("\uD83D\uDD19");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.setVisible(false);
				tipricerca.main(null, db, true);
			}
		});
		btnNewButton_4.setBounds(10, 424, 52, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(689, 424, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		
		
	}
}
