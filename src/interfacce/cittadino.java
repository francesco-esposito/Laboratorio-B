package interfacce;

//intefaccia principale per le operazioni effettuate dal cittadino

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Quale operazione desideri effettuare?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 20, 416, 32);
		frame.getContentPane().add(lblNewLabel);
		//bottone che permette di aprire la schermata se vuoi procedere senza accedere
		JButton btnNewButton = new JButton("Procedi senza accedere");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				tipricerca.main(null, db);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 81, 416, 32);
		frame.getContentPane().add(btnNewButton);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_1 = new JLabel("Per inserire eventi avversi ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 139, 416, 25);
		frame.getContentPane().add(lblNewLabel_1);
		//bottone che permette di aprire la schermata per effettuare l'accesso
		JButton btnNewButton_1 = new JButton("Accedi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				accedi.main(null, db);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 174, 154, 21);
		frame.getContentPane().add(btnNewButton_1);
		//bottone che permette di effettuare la registrazione 
		JButton btnNewButton_2 = new JButton("Registrati");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//regcittadino rc= new regcittadino();
				regcittadino.main(null, db);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_2.setBounds(10, 205, 154, 21);
		frame.getContentPane().add(btnNewButton_2);
		
		//bottone che permette di tornare alla schermata precedente 
		JButton btnNewButton_3 = new JButton("\uD83E\uDC14");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//client c1= new client();
				client.main(null, db);
			}
		});
		btnNewButton_3.setBounds(264, 0, 59, 21);
		frame.getContentPane().add(btnNewButton_3);
		//bottone che permette di tornare alla home
		JButton btnNewButton_4 = new JButton("\u2302");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				//client c1= new client();
				client.main(null, db);
			}
		});
		btnNewButton_4.setBounds(321, 0, 59, 21);
		frame.getContentPane().add(btnNewButton_4);
	}
}
