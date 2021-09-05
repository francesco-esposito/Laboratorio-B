package centrivaccinali;

import java.awt.EventQueue;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import backenddb.ServerDBMSInterface;
import interfacce.client;


import java.sql.*;

/**Classe principale per la gestione della parte del sistema che implementa le funzionalità usate da un operatore vaccinale.
 * Si parla quindi dell'inserimento e memorizzazione nel DB di un nuovo centro e di un nuovo vaccinato.
 * Sono anche implementati metodi che effettuano un controllo sui duplicati e sull'inconsistenza dei dati.
 * Questa è la classe che contie il "main" e il menu dell'intera applicazione del client
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class CentriVaccinali {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException{
		
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
		}
	}
}