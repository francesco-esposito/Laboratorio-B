package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di registrazione di una nuova struttura
 * vaccinale nel sistema.
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class regcentro {

	public JFrame frame;
	private JTextField txtNome;
	private JTextField txtViapiazza;
	private JTextField txtCivico;
	private JTextField txtComune;
	private JTextField txtProvincia;
	private JTextField txtCap;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private ServerDBMSInterface db;

	/**
	 * Create the application.
	 */
	public regcentro() {
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
					regcentro window = new regcentro();
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
		frame.setBounds(100, 100, 450, 303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel = new JLabel("Inserisci i dati del centro vaccinale ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 23, 436, 25);
		frame.getContentPane().add(lblNewLabel);
		//permette di inserire del testo
		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNome.setBounds(10, 82, 163, 19);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_1 = new JLabel("Nome centro");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 58, 96, 25);
		frame.getContentPane().add(lblNewLabel_1);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_2 = new JLabel("Indirizzo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(10, 111, 96, 25);
		frame.getContentPane().add(lblNewLabel_2);
		//permette di inseire del testo
		txtViapiazza = new JTextField();
		txtViapiazza.setBounds(113, 138, 180, 19);
		frame.getContentPane().add(txtViapiazza);
		txtViapiazza.setColumns(10);
		//permette di inserire del testo
		txtCivico = new JTextField();
		txtCivico.setBounds(303, 138, 64, 19);
		frame.getContentPane().add(txtCivico);
		txtCivico.setColumns(10);
		
		txtComune = new JTextField();
		txtComune.setBounds(10, 181, 180, 19);
		frame.getContentPane().add(txtComune);
		txtComune.setColumns(10);
		
		txtProvincia = new JTextField();
		txtProvincia.setBounds(200, 181, 64, 19);
		frame.getContentPane().add(txtProvincia);
		txtProvincia.setColumns(10);
		
		txtCap = new JTextField();
		txtCap.setBounds(271, 181, 96, 19);
		frame.getContentPane().add(txtCap);
		txtCap.setColumns(10);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_3 = new JLabel("Tipologia");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(197, 58, 96, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ospedaliero", "Aziendale", "Hub"}));
		comboBox.setBounds(197, 82, 96, 21);
		frame.getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Via", "V.le", "P.zza"}));
		comboBox_1.setBounds(10, 137, 80, 21);
		frame.getContentPane().add(comboBox_1);
		
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JButton btnNewButton = new JButton("REGISTRA");
		
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String cap = txtCap.getText().toLowerCase();
				String provincia = txtProvincia.getText().toUpperCase();
				String nomecentro = txtNome.getText().toLowerCase();
				String civico = txtCivico.getText().toLowerCase();
				String comune = txtComune.getText().toLowerCase();
				String via = txtViapiazza.getText().toLowerCase();
				String qualificatore = comboBox_1.getSelectedItem().toString().toLowerCase();
				String tipologia = comboBox.getSelectedItem().toString().toLowerCase();
			
				
				//verifica se il cap é di 5 cifre e se é numerico come indica lo standard
				//verifica se la provincia é di 2 lettere e non numeri
				//verifica anche se tutti gli altri campi hanno una lunghezza maggiore di 1 cioe se hanno senso 
				if ((cap.matches("[0-9]+") && cap.length() == 5)&&((provincia.matches("[a-zA-Z]*")&& provincia.length() == 2)&&(nomecentro.length()>1)
						&&(civico.length()>0)&&(comune.length()>1)&&(via.length()>1)))
				{
				
					try {
						
						// metodo del server per l'aggiunta di un nuovo centro nel DB - Successo
						if (db.registraCentroVaccinale(nomecentro, tipologia, qualificatore, via, comune, provincia, Integer.parseInt(cap), civico))
							JOptionPane.showMessageDialog(null, "Registrazione avvenuta con successo");
						
						// metodo del server per l'aggiunta di un nuovo centro nel DB - Insuccesso
						else
							JOptionPane.showMessageDialog(null, "Errore. Centro già registrato nel sistema.",
								    "Inane error",
								    JOptionPane.ERROR_MESSAGE);
					
					} catch (RemoteException | SQLException e) {
						//e.printStackTrace();
						System.out.println("Errore");
					}
					
				}
				else 
					JOptionPane.showMessageDialog(null, "I dati inseriti non sono corretti. Riprovare",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(151, 233, 123, 21);
		frame.getContentPane().add(btnNewButton);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_4 = new JLabel("Nome");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(113, 111, 123, 25);
		frame.getContentPane().add(lblNewLabel_4);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_5 = new JLabel("Civico");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(303, 113, 45, 23);
		frame.getContentPane().add(lblNewLabel_5);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_6 = new JLabel("Comune");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(10, 157, 128, 25);
		frame.getContentPane().add(lblNewLabel_6);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_7 = new JLabel("Provincia");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(200, 157, 74, 25);
		frame.getContentPane().add(lblNewLabel_7);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame 
		JLabel lblNewLabel_8 = new JLabel("Cap");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(271, 157, 45, 25);
		frame.getContentPane().add(lblNewLabel_8);
		//bottone che permette di tornare indietro 
		JButton btnNewButton_1 = new JButton("\uD83D\uDD19");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				opvaccinale.main(null, db);
			}
		});
		btnNewButton_1.setBounds(10, 235, 52, 21);
		frame.getContentPane().add(btnNewButton_1);
		//bottone che permette di tornare alla home
		JButton btnNewButton_2 = new JButton("\u2302");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				client.main(null, db);
			}
		});
		btnNewButton_2.setBounds(369, 235, 57, 21);
		frame.getContentPane().add(btnNewButton_2);
	}
}

