package interfacce;

//interfaccia grafica iniziale che ti permette di scegliere se proseguire come operatore vaccinale o cliente
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import backenddb.ServerDBMSInterface;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class client {

	public JFrame frame;
	private final Action action = new SwingAction();
	private ServerDBMSInterface db;

	/**
	 * Create the application.
	 */
	public client() {
		initialize();
	}
	
	
	public void setDB(ServerDBMSInterface db){
		this.db = db;
	}
	
	public static void main(String[] args, final ServerDBMSInterface db) {
		// richiamo menu' principale tramite interfaccia grafica
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client window = new client();
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
		
		JLabel lblNewLabel = new JLabel("Accedi al sistema come:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 37, 416, 21);
		frame.getContentPane().add(lblNewLabel);
		
		//bottone che permette di aprire la schermata per proseguire come operatore vaccinale
		JButton OPvaccinale = new JButton("Operatore \nVaccinale");
		OPvaccinale.setFont(new Font("Tahoma", Font.PLAIN, 14));
		OPvaccinale.setBounds(60, 119, 122, 43);
		
		OPvaccinale.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				frame.setVisible(false);
				opvaccinale opv = new opvaccinale();
				opvaccinale.main(null, db);
			}
		});
	
		frame.getContentPane().add(OPvaccinale);
		//bottone che permette di aprire la schermata per proseguire come cittadino
		JButton btnNewButton_1 = new JButton("Cittadino");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//rende non visibile la schermata attuale 
				frame.setVisible(false);
				//apre una nuova schermata in questo caso quella relativa a proseguire come cittadino
				//cittadino c = new cittadino();
				cittadino.main(null, db);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(254, 119, 122, 43);
		frame.getContentPane().add(btnNewButton_1);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			//opvaccinale opv= new opvaccinale(db);
			opvaccinale.main(null, db);
		}
	}
}

