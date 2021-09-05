package interfacce;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di registrazione dell’utente nel sistema (per un cittadino).
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class regcittadino {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JTextField textField_5;
	private ServerDBMSInterface db;

	/**
	 * Create the application.
	 */
	public regcittadino() {
		initialize();
	}

	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	public static void main(String[] args, final ServerDBMSInterface db) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					regcittadino window = new regcittadino();
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
		frame.setBounds(100, 100, 450, 345);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Registra cittadino");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 20, 416, 25);
		frame.getContentPane().add(lblNewLabel);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_1 = new JLabel("Inserire i dati richiesti:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 65, 212, 13);
		frame.getContentPane().add(lblNewLabel_1);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_2 = new JLabel("Nome");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 91, 96, 22);
		frame.getContentPane().add(lblNewLabel_2);
		//permette di inserie del testo
		textField = new JTextField();
		textField.setBounds(10, 114, 96, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//permette di inseire del testo 
		textField_1 = new JTextField();
		textField_1.setBounds(116, 114, 96, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_3 = new JLabel("Cognome");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(116, 88, 96, 25);
		frame.getContentPane().add(lblNewLabel_3);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_4 = new JLabel("Codice fiscale");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(243, 91, 183, 22);
		frame.getContentPane().add(lblNewLabel_4);
		//permette di inseire del testo
		textField_2 = new JTextField();
		textField_2.setBounds(243, 114, 183, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_5 = new JLabel("E-mail");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(10, 155, 96, 25);
		frame.getContentPane().add(lblNewLabel_5);
		//permette di inserie del testo
		textField_3 = new JTextField();
		textField_3.setBounds(10, 178, 156, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_6 = new JLabel("Username");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(179, 155, 96, 25);
		frame.getContentPane().add(lblNewLabel_6);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_7 = new JLabel("Password");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(301, 155, 125, 25);
		frame.getContentPane().add(lblNewLabel_7);
		//permette di inserire del testo 
		textField_4 = new JTextField();
		textField_4.setBounds(177, 178, 116, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		//permette di inseire del testo di tipo password che risulta non visibile 
		passwordField = new JPasswordField();
		passwordField.setBounds(303, 178, 107, 19);
		frame.getContentPane().add(passwordField);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_8 = new JLabel("ID vaccinazione");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(10, 216, 131, 13);
		frame.getContentPane().add(lblNewLabel_8);
		//permette di inseire del testo
		textField_5 = new JTextField();
		textField_5.setBounds(10, 239, 156, 19);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		//conferma l'inserimento dei dati li controlla e chiama il metodo per inserirli nel db
		JButton btnNewButton = new JButton("INSERISCI");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String cf= textField_2.getText().toUpperCase();
				String id=textField_5.getText().toLowerCase();
				String mail= textField_3.getText().toLowerCase();
				String nome= textField.getText().toLowerCase();
				String cognome=textField_1.getText().toLowerCase();
				String psw= passwordField.getText();
				String username= textField_4.getText();
				
				//stringa da confrontare per verificare la validita della mail inserita 
				String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			      	
				//verifica se il CF é di 16 cifre  ed e scritto come nello standard
				//verifica se l'id è di 16 cifre e numerico
				//verifiche su tutti gli altri campi
				//se i dati hanno senso chiama il metodo che inserisce i dati nel db se va tutto a buon fine stampera un messaggio di successo altrimenti di errore
				if ((cf.length() == 16 && cf.substring(0,6).matches("[a-zA-Z]*") && cf.substring(6,8).matches("[0-9]+") &&  
						cf.substring(8,9).matches("[a-zA-Z]*")&& cf.substring(9,11).matches("[0-9]+")&&cf.substring(11,12).matches("[a-zA-Z]*")
						&& cf.substring(12,15).matches("[0-9]+")&&
						cf.substring(15,16).matches("[a-zA-Z]*")&&((id.length()==16)&&(id.matches("[0-9]+"))&&(mail.matches(regex)&&(nome.length()>1)
								&&(cognome.length()>1)&&(username.length()>1)&&(psw.length()>1)))))
				{					
				
				try {
					
					if (db.registraCittadino(cf, nome, cognome, mail, username, psw, id)) {
						JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo");
						frame.setVisible(false);
						accedi.main(null, db);
					}
					else
						JOptionPane.showMessageDialog(null, "Operazione fallita. Riprovare",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
				
				} catch (HeadlessException | RemoteException | SQLException e) {
					e.printStackTrace();
					System.out.println("Errore");
				}
				}else 
					{JOptionPane.showMessageDialog(null, "I dati inseriti non sono corretti",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					}
			
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(215, 236, 123, 21);
		frame.getContentPane().add(btnNewButton);
		
		//bottone che permette di tornare indietro 
		JButton btnNewButton_1 = new JButton("\uD83D\uDD19");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//cittadino c= new cittadino();
				cittadino.main(null, db);
			}
		});
		
		btnNewButton_1.setBounds(10, 277, 52, 21);
		frame.getContentPane().add(btnNewButton_1);
		//bottone che permette di tornare alla home 
		JButton btnNewButton_2 = new JButton("\u2302");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		
		btnNewButton_2.setBounds(369, 277, 57, 21);
		frame.getContentPane().add(btnNewButton_2);
	}
}
