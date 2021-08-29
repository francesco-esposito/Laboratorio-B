package centrivaccinali;

import java.awt.EventQueue;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import backenddb.ServerDBMSInterface;
import cittadini.Cittadini;
import interfacce.client;
import interfacce.regcentro;
import interfacce.regvaccinato;

import java.sql.*;

/** Classe principale per la gestione della parte del sistema che implementa le funzionalità usate da un operatore vaccinale.
 * Si parla quindi dell'inserimento e memorizzazione nel DB di un nuovo centro e di un nuovo vaccinato.
 * Sono anche implementati metodi che effettuano un controllo sui duplicati e sull'inconsistenza dei dati.
 * Questa è la classe che contie il "main" e il menu dell'intera applicazione del client
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito */
public class CentriVaccinali {
	
	/** 
	 * @return Il metodo registraCentroVaccinale() permette di inserire informazioni riguardanti una struttura nel DB 
	 * se e solo se non è già presente nel sistema
	 * @throws SQLException 
	*/
	/*public void registraCentroVaccinale(final ServerDBMSInterface db) throws IOException, SQLException, RemoteException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					regcentro window = new regcentro(db);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	/** 
	 * @return Il metodo registraVaccinato() permette di inserire informazioni riguardanti un vaccinato se e solo se non è già
	 * presente nel sistema 
	 * @throws InterruptedException 
	 * @throws RemoteException 
	*/
	/*public void registraVaccinato(final ServerDBMSInterface db) throws RemoteException, InterruptedException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					regvaccinato window = new regvaccinato(db);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
		
	/** 
	 * @return Il metodo initDB() permette di inizializzare ed ottenere la connesione al DB tramite driver manager,
	 * essenziale per compiere una qualsiasi interazione con esso. Viene restituito pertanto un oggetto della classe
	 * "Connection".
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException{
		
		CentriVaccinali app = new CentriVaccinali();
		Cittadini cittadini = new Cittadini();
		
		// ottenimento oggetto remoto server dal registry sulla porta prei-mpostata
		Registry reg = LocateRegistry.getRegistry(1099);
			
		// ottenimento riferimento remoto server
		try {
			
			// conversione oggetto che permette di sfruttare i servii offerti dal server
			ServerDBMSInterface server = (ServerDBMSInterface) reg.lookup("SERVER");
			System.out.println("Collegamento con il server stabilito correttamente.");
			
			client.main(null, server);
			
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Il server non è stato avviato oppure la sue esecuzione è stata interrotta.");
			//e.printStackTrace(); 
		}
	}
}

