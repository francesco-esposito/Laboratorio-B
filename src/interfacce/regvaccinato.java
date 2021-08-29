package interfacce;
//schermata che permette di inserire i vari dati della persona che ha effetttuato il vaccino
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class regvaccinato {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private static ServerDBMSInterface db;

	/**
	 * Create the application.
	 */
	public regvaccinato() {
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
					
					regvaccinato window = new regvaccinato();
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Inserisci i dati del vaccinato");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 21, 416, 25);
		frame.getContentPane().add(lblNewLabel);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_1 = new JLabel("Nome centro vaccinale");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 56, 189, 13);
		frame.getContentPane().add(lblNewLabel_1);
		//permette di inseire del testo 
		textField = new JTextField();
		textField.setBounds(10, 79, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_2 = new JLabel("Nome e cognome del cittadino");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 118, 220, 13);
		frame.getContentPane().add(lblNewLabel_2);
		//permette di inseire del testo
		textField_1 = new JTextField();
		textField_1.setBounds(10, 141, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		//permette di inseire del testo
		textField_2 = new JTextField();
		textField_2.setBounds(116, 141, 96, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_3 = new JLabel("Codice fiscale");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(225, 120, 166, 13);
		frame.getContentPane().add(lblNewLabel_3);
		//permette di inseire del testo
		textField_3 = new JTextField();
		textField_3.setBounds(222, 141, 166, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		//permette di scegliere uno tra gli elementi presenti
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Pfizer", "Astrazeneca", "Moderna", "J&J"}));
		comboBox.setBounds(169, 205, 107, 21);
		frame.getContentPane().add(comboBox);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_4 = new JLabel("Data somministrazione");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 183, 166, 13);
		frame.getContentPane().add(lblNewLabel_4);
		//permette di inserire del testo
		textField_4 = new JTextField();
		textField_4.setBounds(10, 206, 29, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_5 = new JLabel("Nome vaccino");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(169, 185, 96, 13);
		frame.getContentPane().add(lblNewLabel_5);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_6 = new JLabel("ID vaccino viene assegnato dinamicamente ");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 235, 283, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		//bottone che permette di confermare l'inserimento dei dati  
		JButton btnNewButton = new JButton("INSERISCI");
		
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				String cf= textField_3.getText().toUpperCase();
				String gg= textField_4.getText();
				String mm= textField_4.getText();
				String aa= textField_4.getText();
				String nome= textField_1.getText().toLowerCase();
				String cognome=textField_2.getText().toLowerCase();
				String nomecentro= textField.getText().toLowerCase();
				String data = gg + "/" + mm + "/" + aa;
				String vaccino = comboBox.getSelectedItem().toString().toLowerCase();

				//verifica della validita dei dati tramire controlli preliminari
				//se e tutto ok allora chiamiamo il metodo per inserire i dati nel db, se va tutto a buon fine avremo un messaggio di conferma 
			
				
				if ((cf.length() == 16 && cf.substring(0,6).matches("[a-zA-Z]*") && cf.substring(6,8).matches("[0-9]+") &&  
						cf.substring(8,9).matches("[a-zA-Z]*")&& cf.substring(9,11).matches("[0-9]+")&&cf.substring(11,12).matches("[a-zA-Z]*")
						&& cf.substring(12,15).matches("[0-9]+")&&
						cf.substring(15,16).matches("[a-zA-Z]*")&&(gg.length()>0&&gg.length()<31)&&(mm.length()>0&&mm.length()<32)&&
						(aa.length()>2019&&aa.length()<2022)&&(cognome.length()>1)&&(nomecentro.length()>1)&&(nome.length()>1)))
				{
					
					// richiamo metodo del server per la registrazione di un vaccinato nel DB - Insuccesso
					try {
						
						String vid = (db.insertVaccinato(cf, nome, cognome, nomecentro, data, vaccino));
						
						
						if (vid.equals("0"))
							JOptionPane.showMessageDialog(null, "Operazione di registrazione fallita",
								"Inane error", JOptionPane.ERROR_MESSAGE);
						
						// richiamo metodo del server per la registrazione di un vaccinato nel DB - Successo
						else {
							JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo. \n Il tuo VID è : " + vid);
						}
					
					} catch (HeadlessException | RemoteException | SQLException | InterruptedException e) {
						System.out.println("Errore");
					}
				
				}
				
				else {
					
					JOptionPane.showMessageDialog(null, "I dati inseriti non sono corretti. Riprovare",
					"Inane error", JOptionPane.ERROR_MESSAGE);
				}
			
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(303, 235, 123, 21);
		frame.getContentPane().add(btnNewButton);
		//permette di inserire del testo
		textField_6 = new JTextField();
		textField_6.setBounds(49, 206, 29, 19);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		//permette di inseire del testo 
		textField_7 = new JTextField();
		textField_7.setBounds(88, 206, 67, 19);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_7 = new JLabel("gg");
		lblNewLabel_7.setBounds(10, 227, 45, 13);
		frame.getContentPane().add(lblNewLabel_7);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_8 = new JLabel("mm");
		lblNewLabel_8.setBounds(47, 227, 45, 13);
		frame.getContentPane().add(lblNewLabel_8);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_9 = new JLabel("aaaa");
		lblNewLabel_9.setBounds(88, 227, 45, 13);
		frame.getContentPane().add(lblNewLabel_9);
		
		//bottone che permette di tornare alla schermata precedente 
		JButton btnNewButton_1 = new JButton("\uD83D\uDD19");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				opvaccinale.main(null, db);
			}
		});
		
		btnNewButton_1.setBounds(308, 0, 52, 21);
		frame.getContentPane().add(btnNewButton_1);
		//bottone che permette di tornare alla home di operatore vaccinale
		JButton btnNewButton_2 = new JButton("\u2302");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				opvaccinale.main(null, null);
			}
		});
		btnNewButton_2.setBounds(357, 0, 57, 21);
		frame.getContentPane().add(btnNewButton_2);
	}
}
