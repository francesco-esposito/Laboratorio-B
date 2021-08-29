package interfacce;

//permette di scegliere se effettuare una ricerca per comune e tipo o per nome viene aperta quando si accede o quando si
//decide di procedere senza accedere o effettuando l'accesso cambiano le politiche di ricerca e inserimento
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tipricerca {

	private JFrame frame;
	private ServerDBMSInterface db;

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
					tipricerca window = new tipricerca();
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
	public tipricerca() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame1
		JLabel lblNewLabel = new JLabel("Quale operazione vuoi effettuare?");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 20, 416, 25);
		frame.getContentPane().add(lblNewLabel);
		//bottone che permette di aprire una schermata per effettuare una ricerca per nome del centro vaccinale 
		JButton btnNewButton = new JButton("Ricerca centro vaccinale per nome");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				rnome.main(null, db);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 84, 416, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ricerca centro vaccinale per comune e tipo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rcomune.main(null, db);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(10, 154, 416, 38);
		frame.getContentPane().add(btnNewButton_1);
		//label che viene visualizzata solo nel caso in cui l'accesso é stato effettuato-
		JLabel lblNewLabel_1 = new JLabel("ACCESSO ESEGUITO");
		lblNewLabel_1.setEnabled(true);
		lblNewLabel_1.setVisible(false);
		if(accedi.accesso)
			lblNewLabel_1.setVisible(true);
		lblNewLabel_1.setBounds(10, 214, 134, 13);
		frame.getContentPane().add(lblNewLabel_1);
		//bottone che permette di tornare alla schermata precedente, il login viene annullato 
		JButton btnNewButton_2 = new JButton("\uD83D\uDD19");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//se torna indietro esce dal login
			accedi.accesso=false;
			frame.setVisible(false);
			cittadino.main(null, db);
			}
		});
		btnNewButton_2.setBounds(309, 0, 52, 21);
		frame.getContentPane().add(btnNewButton_2);
		//bottone che permettte di tornare alla home di cittadino, il login viene annullato 
		JButton btnNewButton_2_1 = new JButton("\u2302");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//se torna alla home esce dal login
				accedi.accesso=false;
				frame.setVisible(false);
				client.main(null, null);
			}
		});
		btnNewButton_2_1.setBounds(358, 0, 57, 21);
		frame.getContentPane().add(btnNewButton_2_1);
		//scritta che viene stampata informa che nel caso in cui si ha effettuato il login e si torna indietro 
		//bisognera effettuarlo nuovamente
		JLabel lblNewLabel_10 = new JLabel("tornando alla home o indietro dovrai rieffettuare l'accesso");
		lblNewLabel_10.setBounds(37, 4, 272, 13);
		frame.getContentPane().add(lblNewLabel_10);
	}
}