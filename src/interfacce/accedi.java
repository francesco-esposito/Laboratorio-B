package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;
import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di login nel sistema per un cittadino. 
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class accedi {
	public static boolean accesso=false;
	//creazione del frame che poi conterra tutti gli elementi
	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private String userName;
	private ServerDBMSInterface db;

	/**
	 * Create the application.
	 */
	public accedi() {
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
					accedi window = new accedi();
					window.setDB(db);
					window.frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Per utilizzare questa funzionalità è necessario \n"
							+ "essere registrati nel sistema e aver effettuato \nl'accesso con le proprie credenziali.",
						    "Inane error",
						    JOptionPane.INFORMATION_MESSAGE);
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
		frame.setBounds(100, 100, 410, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Inserisci le credenziali");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 20, 376, 24);
		frame.getContentPane().add(lblNewLabel);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_2 = new JLabel("Nome utente ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 69, 124, 13);
		frame.getContentPane().add(lblNewLabel_2);
		//permette di scrivere all'interno del testo
		textField = new JTextField();
		textField.setBounds(130, 68, 124, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(10, 108, 135, 13);
		frame.getContentPane().add(lblNewLabel_3);
		//permette di scrivere all'interno del testo di tipo password cioe non si vede quello che é stato scritto
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 107, 124, 19);
		frame.getContentPane().add(passwordField);
		
		//bottone che serve per dare la conferma dei dati inseriti e procedere con l'accesso 
		JButton btnNewButton = new JButton("ACCEDI");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
		
				try {
					
					userName = textField.getText();
					
					// se i dati inseriti sono validi
					if(userName.length()> 0) {
						if(db.login(userName, passwordField.getText()).equals(""))
						JOptionPane.showMessageDialog(null, "Autenticazione fallita. Riprovare",
							    "Inane error",
							    JOptionPane.ERROR_MESSAGE);
						
						else { 
							
							JOptionPane.showMessageDialog(null, "Operazione avvenuta con successo. \nTornando "
									+ "alla home del sistema, le credenziali\ninserite non saranno più valide.");
							frame.setVisible(false);
							tipricerca.main(userName, db, true);
							
						}
					
					// inserimento dati non valido
					} else {
						JOptionPane.showMessageDialog(null, "I dati inseriti non sono corretti. Riprovare",
							    "Inane error",
							    JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (HeadlessException | RemoteException | SQLException e) {
					System.out.println("Errore aa");
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(130, 153, 124, 21);
		frame.getContentPane().add(btnNewButton);
		
		
		//bottone per tornare indietro
		JButton btnNewButton_1 = new JButton("\uD83D\uDD19");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				cittadino.main(null, db);
			}
		});
		btnNewButton_1.setBounds(10, 246, 52, 21);
		frame.getContentPane().add(btnNewButton_1);
		//bottone per tornare alla home
		JButton btnNewButton_2 = new JButton("\u2302");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2.setBounds(329, 246, 57, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("oppure, se non possiedi ancora un account");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 204, 376, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_3 = new JButton("REGISTRATI");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				regcittadino.main(null, db);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_3.setBounds(130, 233, 124, 21);
		frame.getContentPane().add(btnNewButton_3);
	}


	public String getUserName() { return userName; }
}
