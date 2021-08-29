/* Alonzi Alessandro 741411 VA (Project Manager)
Esposito Francesco 739888 VA */

package cittadini;

import java.rmi.RemoteException;
import java.sql.*;

/** Classe principale per la gestione della parte del sistema che implementa le funzionalità usate da un cittadino.
 * Si parla quindi della ricerca e visualizzazione delle informazioni legate ad una determinata struttura,
 * registrazione di un cittadino nel sistema e inserimento di eventi avversi post-vaccino da COVID-19
 * Sono anche implementati metodi che effettuano un controlllo sui duplicati.
 * @author Alessandro Alonzi
 * @author Francesco Esposito
 * @author Daniel Pedrotti
 */
public class Cittadini {
	
	/** 
	 * @return Il metodo cercaCentroVaccinale() permette di ricercare una data struttura vaccinale se è già
	 * presente nel sistema
	 * @throws SQLException 
	*/
	public void cercaCentroVaccinale() 
			throws SQLException, RemoteException {	
	}
	
	/** 
	 * @return Il metodo visualizzaInfoCentroVaccinale() permette di ricercare e visualizzare le info di una 
	 * data struttura vaccinale
	 * presente nel sistema
	 * @throws SQLException 
	*/
	public void visualizzaInfoCentroVaccinale() throws SQLException, RemoteException {
		
	}
	
	/** 
	 * @return Il metodo registraCittadino() permette di inserire informazioni riguardanti un cittadino se non è già
	 * presente nel sistema
	 * @throws SQLException 
	*/
	public void registraCittadino() throws SQLException, RemoteException {

	}
	
	/** 
	 * @return Il metodo loginCittadino() permette di effettuare il login al cittadino
	 * @throws SQLException 
	*/
	public void loginCittadino() throws SQLException, RemoteException {
		
	}
	
	/** 
	 * @return Il metodo inserisciEventiAvversi() permette di inserire segnalazioni su eventuali eventi avversi dovuti
	 * al vaccino 
	 * @throws SQLException 
	*/
	public static void inserisciEventiAvversi() 
			throws SQLException, RemoteException {
	}
}