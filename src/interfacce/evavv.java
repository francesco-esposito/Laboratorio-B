package interfacce;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import backenddb.ServerDBMSInterface;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**Interfaccia che implementa la funzionalità di registrazione di eventi avversi 
 * nel sistema (per un cittadino dopo login).
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class evavv {

	public JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private ServerDBMSInterface db;
	private String userName;
	private String nomeCentro;

	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(final String args, final ServerDBMSInterface db, final String nomeCentro) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					evavv window = new evavv(args, nomeCentro);
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
	public evavv(String userName, String nomeCentro) {
		this.nomeCentro = nomeCentro;
		this.userName = userName;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel = new JLabel("Inserimento eventi avversi per il centro");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 21, 416, 25);
		frame.getContentPane().add(lblNewLabel);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_1 = new JLabel("Mal di testa");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 132, 83, 13);
		frame.getContentPane().add(lblNewLabel_1);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_2 = new JLabel("Febbre");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(20, 155, 83, 13);
		frame.getContentPane().add(lblNewLabel_2);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_3 = new JLabel("Dolori muscolari e articolari");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(20, 178, 184, 13);
		frame.getContentPane().add(lblNewLabel_3);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_4 = new JLabel("Linfoadenopatia");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(20, 201, 129, 13);
		frame.getContentPane().add(lblNewLabel_4);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_5 = new JLabel("Tachicardia");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(20, 224, 83, 13);
		frame.getContentPane().add(lblNewLabel_5);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_6 = new JLabel("Crisi ipertensiva");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(20, 247, 146, 13);
		frame.getContentPane().add(lblNewLabel_6);
		//combobox permette di scegliere uno degli elementi 
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(199, 128, 56, 21);
		frame.getContentPane().add(comboBox);
		//combobox permette di scegliere uno degli elementi 
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_1.setSelectedIndex(0);
		comboBox_1.setBounds(199, 151, 56, 21);
		frame.getContentPane().add(comboBox_1);
		//combobox permette di scegliere uno degli elementi 
		final JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_2.setSelectedIndex(0);
		comboBox_2.setBounds(199, 174, 56, 21);
		frame.getContentPane().add(comboBox_2);
		//combobox permette di scegliere uno degli elementi 
		final JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_3.setSelectedIndex(0);
		comboBox_3.setBounds(199, 197, 56, 21);
		frame.getContentPane().add(comboBox_3);
		//combobox permette di scegliere uno degli elementi 
		final JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_4.setSelectedIndex(0);
		comboBox_4.setBounds(199, 220, 56, 21);
		frame.getContentPane().add(comboBox_4);
		//combobox permette di scegliere uno degli elementi 
		final JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		comboBox_5.setSelectedIndex(0);
		comboBox_5.setBounds(199, 243, 56, 21);
		frame.getContentPane().add(comboBox_5);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_7 = new JLabel("Evento");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(20, 87, 83, 25);
		frame.getContentPane().add(lblNewLabel_7);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_8 = new JLabel("Severit\u00E0");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_8.setBounds(199, 87, 111, 25);
		frame.getContentPane().add(lblNewLabel_8);
		//creazione di una label e imposta i vari campi (posizione, font) inoltre lo aggiunge al frame
		JLabel lblNewLabel_9 = new JLabel("Note aggiuntive");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_9.setBounds(265, 87, 161, 25);
		frame.getContentPane().add(lblNewLabel_9);
		//permette di inserire del testo 
		textField = new JTextField();
		textField.setBounds(265, 131, 161, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		//permette di inserire del testo 
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setBounds(265, 154, 161, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		//permette di inserire del testo 
		textField_2 = new JTextField();
		textField_2.setBounds(265, 177, 161, 19);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		//permette di inserire del testo 
		textField_3 = new JTextField();
		textField_3.setBounds(265, 200, 161, 19);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		//permette di inserire del testo 
		textField_4 = new JTextField();
		textField_4.setBounds(265, 223, 161, 19);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		//permette di inserire del testo 
		textField_5 = new JTextField();
		textField_5.setBounds(265, 246, 161, 19);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		//bottone che permette di confermare l'inserimento dei dati e di inserirli dentro il db
		JButton btnNewButton = new JButton("Conferma segnalazioni");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String note_mdt= textField.getText();
				String note_f= textField_1.getText();
				String note_dma= textField_2.getText();
				String note_l= textField_3.getText();
				String note_t= textField_4.getText();
				String note_ci= textField_5.getText();
				final String separatore = "@@"; 
				
				// unione note per memorizzazione nel DB con utilizzo di una stringa "separatrice"
				String noteDB = note_mdt + separatore + note_f + separatore + note_dma + separatore
						+ note_l + separatore + note_t + separatore + note_ci;
				
				String[] sev = { 
								 comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(),
								 comboBox_2.getSelectedItem().toString(), comboBox_3.getSelectedItem().toString(),
								 comboBox_4.getSelectedItem().toString(), comboBox_5.getSelectedItem().toString()
							   };
				
				//verifica che i commenti inserii abbiano senso e rispettino la lunghezza
				if((note_mdt.length()<=256)&&(note_f.length()<=256)&&(note_dma.length()<=256)
						&&(note_l.length()<=256)&&(note_ci.length()<=256)&&((note_t.length()<=256)))
				{
					
					// richiamo metodo server per l'inserimento di eventi avversi
					try {
						
						if(db.inserisciEventiAvversi(userName, "ospedale di varese", sev, noteDB)) {
							JOptionPane.showMessageDialog(null, "Inserimento dati avvenuto con successo");
							frame.setVisible(false);
							cittadino.main(null, db);
						}
						else {
							JOptionPane.showMessageDialog(null, "Errore. Riprovare",
								    "Inane error",
								    JOptionPane.ERROR_MESSAGE);
						}
					
					
					} catch (RemoteException | SQLException e) {
						e.printStackTrace();
						System.out.println("Errore");
					} 
					
				}
				else {
					JOptionPane.showMessageDialog(null, "I dati inseriti non sono validi",
						    "Inane error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(109, 281, 227, 21);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_10 = new JLabel(nomeCentro.toUpperCase());
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(10, 50, 416, 25);
		frame.getContentPane().add(lblNewLabel_10);
	}
}
