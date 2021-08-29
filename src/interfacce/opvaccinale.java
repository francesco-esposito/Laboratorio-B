package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//schermata principale se scegli di procedere come operatore vaccinale,
//permette di scegliere se registrare un nuovo vaccinato o inserire un nuovo centro vaccinale 
public class opvaccinale {

	JFrame frame;
	private ServerDBMSInterface db;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final ServerDBMSInterface db) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					opvaccinale window = new opvaccinale();
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
	public opvaccinale() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Quale operazione desideri effettuare?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 29, 416, 30);
		frame.getContentPane().add(lblNewLabel);
		//bottone che permette di aprire la schermata per inserire un nuovo centro vaccinale 
		JButton btnNewButton = new JButton("Registra nuovo centro vaccinale ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//regcentro rc= new regcentro(db);
				regcentro.main(null, db);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(84, 89, 273, 30);
		frame.getContentPane().add(btnNewButton);
		//bottone che apre la schermata per inserire un nuovo vaccinato
		JButton btnNewButton_1 = new JButton("Registra nuovo vaccinato");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//regvaccinato rv= new regvaccinato(db);
				regvaccinato.main(null, db);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(84, 155, 273, 30);
		frame.getContentPane().add(btnNewButton_1);
		//bottone che permette di tornare alla schermata principale
		JButton btnNewButton_2 = new JButton("\uD83D\uDD19");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//client c1 = new client();
				client.main(null, db);
			}
		});
		btnNewButton_2.setBounds(304, 0, 52, 21);
		frame.getContentPane().add(btnNewButton_2);
		//bottone che permette di tornare alla home 
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//client c1= new client(null);
				client.main(null, db);
			}
		});
		btnNewButton_2_1.setBounds(353, 0, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
	}

}