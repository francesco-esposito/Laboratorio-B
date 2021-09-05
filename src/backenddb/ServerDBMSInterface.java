package backenddb;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.HashMap;

import centrivaccinali.CentroVacc;

/**Classe che codifica la componente remota del server del sistema, cioè, quella che espone tutti i
 * servizi che vengono offerti ai client. estendendo la classe "Remote". 
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public interface ServerDBMSInterface extends Remote {

   /**@return metodo che consente l'inserimento di un centro nel DB
	* restituisce TRUE se l'operazione di registrazione di un nuovo centro va a buon fine, FALSE altrimenti
	* nel secondo caso, oltre ad eventuali eccezioni, si può verificare il caso in cui il centro sia già
	* stato registrato
	*/
	public boolean registraCentroVaccinale(String nome, String tipologia, String qualificatore, String nomeIndirizzo, String comune,
			String provincia, int CAP, String civico) throws SQLException, RemoteException;

   /**@return metodo che consente l'inserimento di un centro nel DB
	* restituisce TRUE se l'operazione di registrazione di un nuovo vaccinato va a buon fine, FALSE altrimenti
	* nel secondo caso, oltre ad eventuali eccezioni, si può verificare il caso in cui il vaccinato sia già
	* stato registrato
    */
	public String registraVaccinato(String CF, String nome, String cognome, String nomeCentro,
			String data, String vaccino) throws SQLException, RemoteException, InterruptedException;

   /**@return metodo che consente la ricerca di un dato centro nel DB
	* restituisce una hashmap contenente una lista di centri se la ricerca va a buon fine, altrimenti
	* restituirà una hashmap vuota
	*/
	public HashMap<Integer, CentroVacc> cercaCentroVaccinale(String par1, String par2) 
			throws SQLException, RemoteException;

	/**@return metodo che consente la visualizzazione di informazioni (media) legate ad un determinato centro estrapolate dal DB */
	public String[] infoAVGCentro(String nomecentro) throws SQLException, RemoteException;

	/**@return metodo che consente la visualizzazione di informazioni (numero segnalazioni) legate ad un determinato centro estrapolate dal DB */
	public double[] infoCOUNTCentro(String nomecentro) throws SQLException, RemoteException;

   /**@return metodo che consente l'inserimento di un cittadino nel DB
	* restituisce TRUE se l'operazione di registrazione di un nuovo cittadino va a buon fine, FALSE altrimenti
	* nel secondo caso, oltre ad eventuali eccezioni, si può verificare il caso in cui il cittadino sia già
	* stato registrato
	*/
	public boolean registraCittadino(String CF, String nome, String cognome, String email, String UID, String password,
			String VID) throws SQLException, RemoteException;

   /**@return metodo che consente all'utente registrato di accedere al sistema e restituisce una stringa contenente il suo UID se
	* se l'operazione va a buon fine oppure una stringa vuota
	*/
	public String login(String name, String password) throws SQLException, RemoteException;

   /**@return metodo che consente di registrare nel DB eventuali eventi avversi avvenuti dopo la vaccinazione per COVID19.
	* restituisce TRUE se l'operazione va a buon fine, FALSE altrimenti
	*/
	public boolean inserisciEventiAvversi(String uid, String nomecentro, String[] sev, String note) 
			throws SQLException, RemoteException;

}