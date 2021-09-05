package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la scelta della tipologia di ricerca che il cittadino può effettuare 
 * (per nome oppure per comune e tipologia).
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class tipricerca {

	private JFrame frame;
	private ServerDBMSInterface db;
	private String userName;
	private boolean accesso;

	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(final String args, final ServerDBMSInterface db, final boolean accesso) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tipricerca window = new tipricerca(args, accesso);
					window.setDB(db);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tipricerca(String userName, boolean accesso) {
		this.userName = userName;
		this.accesso = accesso;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 278);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame1
		JLabel lblNewLabel = new JLabel("Quale tipologia di ricerca desideri effettuare?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 20, 416, 25);
		frame.getContentPane().add(lblNewLabel);
		
		//bottone che permette di aprire una schermata per effettuare una ricerca per nome del centro vaccinale 
		JButton btnNewButton = new JButton("Ricerca centro vaccinale in base al nome");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				
				// ricerca senza login
				if(!accesso) {
					frame.setVisible(false);
					rnome.main(null, db);
				}
				
				// ricerca con login
				else {
					frame.setVisible(false);
					rnomeavv.main(userName, db);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 66, 416, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ricerca centro vaccinale in base al comune e alla tipologia");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// ricerca senza login
				if(!accesso) {
					frame.setVisible(false);
					rcomune.main(null, db);
				}
				
				// ricerca con login
				else {
					frame.setVisible(false);
					rcomuneavv.main(userName, db);
				}
			}
		});
		
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 136, 416, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		//bottone che permette di tornare alla schermata precedente, il login viene annullato 
		JButton btnNewButton_2 = new JButton("\uD83D\uDD19");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			frame.setVisible(false);
			cittadino.main(null, db);
			}
		});
		btnNewButton_2.setBounds(10, 210, 52, 21);
		frame.getContentPane().add(btnNewButton_2);
		//bottone che permettte di tornare alla home di cittadino, il login viene annullato 
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//se torna alla home esce dal login
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(369, 210, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		
		if (accesso) {
			JLabel lblNewLabel_11 = new JLabel("Accesso effettuato come " + userName);
			lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_11.setBounds(10, 193, 416, 17);
			frame.getContentPane().add(lblNewLabel_11);
			lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_11.setVisible(true);
		}
	}
}