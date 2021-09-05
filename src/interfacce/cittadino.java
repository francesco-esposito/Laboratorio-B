package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**Interfaccia principale per le operazioni offerte dal sistema al cittadino.
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class cittadino {

	JFrame frame;
	private ServerDBMSInterface db;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final ServerDBMSInterface db) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cittadino window = new cittadino();
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
	public cittadino() {
		initialize();
	}

	
	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Quale operazione desideri effettuare?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 20, 416, 32);
		frame.getContentPane().add(lblNewLabel);
		
		//bottone che permette di aprire la schermata se vuoi procedere senza accedere
		JButton btnNewButton = new JButton("Ricerca e visualizzazioni dati centro vaccinale");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				tipricerca.main(null, db, false);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 81, 416, 32);
		frame.getContentPane().add(btnNewButton);
		
		
		//bottone che permette di tornare alla schermata precedente 
		JButton btnNewButton_3 = new JButton("\uD83D\uDD19");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		
		btnNewButton_3.setBounds(10, 207, 59, 21);
		frame.getContentPane().add(btnNewButton_3);
		
		//bottone che permette di tornare alla home
		JButton btnNewButton_4 = new JButton("\u2302");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_4.setBounds(367, 207, 59, 21);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("Inserimento eventi avversi post vaccinazione da COVID-19");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				accedi.main(null, db);
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 146, 416, 32);
		frame.getContentPane().add(btnNewButton_1);
	}
}
