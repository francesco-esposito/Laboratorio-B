package backenddb;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

import centrivaccinali.CentroVacc;
import centrivaccinali.Indirizzo;

public class ServerDBMS extends UnicastRemoteObject implements ServerDBMSInterface {
	
	private static final long serialVersionUID = 1L;
	private final String DRIVER = "org.postgresql.Driver";
	private final String DBURL = "jdbc:postgresql:progettolab";
	private final String DBUSERNAME = "postgres";
	private final String DBPASSWORD = "Alessandro1900";
	private Connection con;
	private Statement stmt;
	// formattatore di date
	private DateTimeFormatter dtf;
	
	// inizializzazione parametri connessione
	public ServerDBMS() throws RemoteException {
		super();
		try {
			con = initDB();
			stmt = con.createStatement();
			System.out.println("Inizializzazione PostGreeSQL database eseguita correttamente.");
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss:SS");
		} catch (ClassNotFoundException | SQLException e) {e.printStackTrace();}
	}
	
	public Connection initDB() throws SQLException, ClassNotFoundException, RemoteException {
		
		// collegamento al driver
		Class.forName(DRIVER);
		
		// stabilimento connessione tramite driver manager
		return DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);
	}
	
	// metodo che consente di identificare duplicati (e non solo) determinando se una data row è presente in una determinata tabella
	// (evita anche eccezioni dovute alla violazione di vincoli che agiscono sull'univocità delle info)
	// restituisce true se la row è già presente nella tabella, false altrimenti
	public synchronized boolean rowChecking(String table, String condizione) throws SQLException {
		
		// query per la ricerca di tutti i dati da un tabella data una certa condizione
		String query = "SELECT * FROM " + table + " WHERE " + condizione;
		
		// esecuzione comando tramite DBMS
		ResultSet rs = stmt.executeQuery(query);
					
		// se la query non ha prodotto risultati significa che la row non è memorizzata nella tabella data
		if (!rs.isBeforeFirst() ) return false;
		// altrimenti il contrario
		else return true;
	}
	
	// metodo che consente l'inserimento di un centro nel DB
	// restituisce TRUE se l'operazione di registrazione di un nuovo centro va a buon fine, FALSE altrimenti
	// nel secondo caso, oltre ad eventuali eccezioni, si può verificare il caso in cui il centro sia già
	// stato registrato
	public synchronized boolean insertCentro(String nome, String tipologia, String qualificatore, String nomeIndirizzo, String comune,
			String provincia, int CAP, String civico) throws SQLException, RemoteException {
		
		// stringa contenente clausola where comando SQL per la ricerca di centri duplicati
		String where =
				"nomecentro = '" + nome + "' AND "
				+ "tipologia = '" + tipologia + "' AND "
				+ "qualificatore = '" + qualificatore + "' AND "
				+ "nomeindirizzo = '" + nomeIndirizzo + "' AND "
				+ "comune = '" + comune + "' AND "
				+ "provincia = '" + provincia + "' AND "
				+ "cap = '" + CAP + "' AND "
				+ "civico = '" + civico + "'";
		
		// centro già presente - duplicato
		if( rowChecking("centrivaccinali", where) ) { 
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Errore nell'inserimento del nuovo centro nel sistema. Il centro vaccinale \""
					+ nome.toUpperCase() + "\" è già stato memorizzato precedentemente!");
			return false; 
		}
		
		// centro non presente nel DB
		else {
			
			// comando SQL per l'aggiunta del nuovo centro
			String cmd = "INSERT INTO CentriVaccinali (nomecentro,tipologia,qualificatore,nomeindirizzo,"
					+ "comune,provincia,cap,civico)"
					+ "VALUES ("
					+ "'" + nome + "',"
					+ "'" + tipologia + "',"
					 + "'" + qualificatore + "'," 
					 + "'" + nomeIndirizzo + "',"  
					 + "'" + comune + "',"  
					 + "'" + provincia + "',"  
					 + CAP + ","
					 + "'" + civico + "')";
			
			// esecuzione comando
			stmt.executeUpdate(cmd);
			
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Inserimento nuovo centro \"" + 
			nome.toUpperCase() + "\" nel sistema avvenuto correttamente.");
			
			return true;
			
		}
	}
	
	// metodo che consente l'inserimento di un centro nel DB
	// restituisce TRUE se l'operazione di registrazione di un nuovo vaccinato va a buon fine, FALSE altrimenti
	// nel secondo caso, oltre ad eventuali eccezioni, si può verificare il caso in cui il vaccinato sia già
	// stato registrato
	public synchronized String insertVaccinato(String CF, String nome, String cognome, String nomeCentro,
			String data, String vaccino) throws SQLException, RemoteException, InterruptedException {	
		
		String where =
				"nomecentro = '" + nomeCentro + "'";
		
		// se il centro specificato è registrato nel sistema
		if( rowChecking("centrivaccinali", where)) {
			
			// generazione ID di vaccinazione univoco a 16 caratteri
			Random random = new Random(); Long tmp = System.currentTimeMillis();
			String vid = tmp.toString() + Integer.toString(random.nextInt(900) + 100);
			Thread.sleep(100);
			
			// eliminazione di eventuali spazi
			String centro = "";
			if(nomeCentro.contains(" "))
				centro = nomeCentro.replace(" ", "_");
			else
				centro = nomeCentro;
			
			// evita bug convenzioni postgres
			centro = centro.toLowerCase();
			
			// comando SQL per l'inserimento di un nuovo vaccinato nell'apposita tabella
			String cmd = "INSERT INTO vaccinati_" + centro + " VALUES ("
					 + "'" + CF.toUpperCase() + "',"  
					 + "'" + vid + "'," 
					 + "'" + nome.toLowerCase() + "',"  
					 + "'" + cognome.toLowerCase() + "',"  
					 + "'" + nomeCentro.toLowerCase() + "',"
					 + "'" + data + "',"
					 + "'" + vaccino.toLowerCase() + "')";
			
			
			// clausolo where per la ricerca nel DB
			where = "TABLE_NAME = 'vaccinati_" + centro + "'";
			
			// verifica esistenza di una data tabella nel DB
			if (rowChecking("INFORMATION_SCHEMA.TABLES", where)) {
				
				// controllo vaccinati duplicati (solo) per quel centro 
				if(rowChecking("vaccinati_" + centro, "cf = '" + CF + "'")) { 
					System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Errore nell'inserimento del nuovo vaccinato \""+ nome.toUpperCase() + " "
							+ cognome.toUpperCase() + "\" nel sistema. E' già stato memorizzato nel sistema precedentemente!");
					return "0"; 
				}
				
				// utente non precedentemente registrato
				else {
					
					// esecuzione query per l'aggiunta di dati (in questo caso non possono esserci duplicati)
					stmt.executeUpdate(cmd);
					System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Inserimento nuovo vaccinato \"" + nome.toUpperCase() + " " + 
							cognome.toUpperCase() + "\" nel sistema avvenuto correttamente.");
					
					return vid;
				}
				
			}
			
			// la tabella non esiste, deve essere creata e poi deve essere inserita la row contenente le info
			// relative al vaccinato
			else {
				
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Creazione di una nuova tabella nel sistema: " + centro);
				
				// creazione tabella nel db
				String table = "CREATE TABLE public.vaccinati_" + centro + "("
						+ "    cf text COLLATE pg_catalog.default NOT NULL,"
						+ "    vid bigint NOT NULL,"
						+ "    nome text COLLATE pg_catalog.default NOT NULL,"
						+ "    cognome text COLLATE pg_catalog.default NOT NULL,"
						+ "    centro text COLLATE pg_catalog.default NOT NULL,"
						+ "    dataVacc text NOT NULL,"
						+ "    vaccino text COLLATE pg_catalog.default NOT NULL,"
						+ "    CONSTRAINT vaccinati_" + centro + "_pkey PRIMARY KEY (cf),"
						+ "	   CONSTRAINT " + centro + "_VIDunique UNIQUE (vid))";
				
				// esecuzioni comando SQL di creazione della relazione
				stmt.executeUpdate(table);
				
				// esecuzione query per l'aggiunta di dati (in questo caso non possono esserci duplicati)
				stmt.executeUpdate(cmd);
				
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Inserimento nuovo vaccinato \""+ nome.toUpperCase() + " " + 
						cognome.toUpperCase() + "\" nel sistema avvenuto correttamente");
				
				return vid;
			}
			
		}
		else {
			
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Il centro \"" + nomeCentro.toUpperCase() 
			+ "\"non esiste nel sistema. Nome specificato errato.");
			
			return "0";
		}
	}

	// metodo che consente la ricerca di un dato centro nel DB
	// restituisce una hashmap contenente una lista di centri se la ricerca va a buon fine, altrimenti
	// restituirà una hashmap vuota
	public synchronized HashMap<Integer, CentroVacc> searchCentro( String par1, String par2 ) 
			throws SQLException, RemoteException {
		
		boolean trovato = false;
		HashMap<Integer,CentroVacc> centri = new HashMap<Integer, CentroVacc>();
		
		// ricerca per nome
		if ( par2.equals("0") ) {
			
			// stringa contenente comando in sintassi SQL
			String query = "SELECT * FROM centrivaccinali WHERE nomecentro LIKE '%" + par1.toLowerCase() + "%'";
			
			// esecuzione comando tramite DBMS
			ResultSet rs = stmt.executeQuery(query);
			
			// se la ricerca non ha prodotto risultati restituisci hashmap vuota
			if (!rs.isBeforeFirst() ) {
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - La ricerca non ha prodotto risultati utili.");
				return centri;
			}
			// se la ricerca ha prodotto dei risultati
			else {
				
				// recupero dati della query
				while (rs.next()) {
					
					// converti riga del result-set in un oggetto centro vaccinale
					Indirizzo ind = new Indirizzo(
							rs.getString ("qualificatore"),
							rs.getString ("nomeindirizzo"),
							rs.getString ("civico"),
							rs.getString ("comune"),
							rs.getString ("provincia"),
							rs.getInt("cap"));

					CentroVacc centro = new CentroVacc(
							rs.getInt ("id"),
							rs.getString ("nomecentro"),
							rs.getString("tipologia"),
							ind);
					
					// inserimento dato nella tabella hash
					centri.put(rs.getInt ("id"), centro);
					
				}
				
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - La ricerca ha prodotto risultati utili.");
				
				// restituisci tabella hash contente risultati della ricerca
				return centri;	
			}
		}
		
		// ricerca per comune e tipologia
		else {
			
			// stringa contenente comando in sintassi SQL
			String query = "SELECT * FROM centrivaccinali WHERE comune = '" + par1.toLowerCase() + "' AND"
					+ " tipologia = '" + par2.toLowerCase() + "'";
			
			// esecuzione comando tramite DBMS
			ResultSet rs = stmt.executeQuery(query);
			
			// se la ricerca non ha prodotto risultati restituisci hashmap vuota
			if (!rs.isBeforeFirst() ) {
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - La ricerca non ha prodotto risultati utili.");
				return centri;
			}
			
			// se la ricerca ha prodotto risultati
			else {
				
				// recupero dati della query
				while (rs.next()) {
				
					// converti riga del result-set in un oggetto centro vaccinale
					Indirizzo ind = new Indirizzo(
							rs.getString ("qualificatore"),
							rs.getString ("nomeindirizzo"),
							rs.getString ("civico"),
							rs.getString ("comune"),
							rs.getString ("provincia"),
							rs.getInt("cap"));

					CentroVacc centro = new CentroVacc(
							rs.getInt ("id"),
							rs.getString ("nomecentro"),
							rs.getString("tipologia"),
							ind);
					
					// inserimento dato nella tabella hash
					centri.put(rs.getInt ("id"), centro);
					
				}
				
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - La ricerca ha prodotto risultati utili.");
				
				// restituzione lista centri risultato della ricerca
				return centri;
			
			}
		}		
		
	}

	// metodo che consente la visualizzazione di informazioni (media) legate ad un determinato centro estrapolate dal DB
	public synchronized String[] infoAVGCentro(String nomecentro) throws SQLException, RemoteException {
		
		String[] avg = new String[6];
		
		// condizione clausola where
		String where = "centro = '" + nomecentro + "'";
		
		// verifica informazioni associate al centro già registrate nel sistema
		if (rowChecking("eventi_avversi", where)) {
			
			// stringa contenente comando in sintassi SQL per determinare la media dei valori di severità legati ai vari sintomi
			// lasciati dagli utenti
			String query = "SELECT AVG(maltesta) as avgmaltesta,"
					+ " AVG(febbre) as avgfebbre,"
					+ " AVG(dolmuar) as avgdolmuar,"
					+ " AVG(linfo) as avglinfo,"
					+ " AVG(tac) as avgtact,"
					+ " AVG(criper) as avgcriper"
					+ " FROM eventi_avversi WHERE centro = '" + nomecentro + "'";
			
			// esecuzione comando tramite DBMS
			ResultSet rs = stmt.executeQuery(query);
			
			// recupero dati della query e memorizzazione nell'array
			while (rs.next()) {
				avg[0] = rs.getString ("avgmaltesta");
				avg[1] = rs.getString ("avgfebbre");
				avg[2] = rs.getString ("avgdolmuar");
				avg[3] = rs.getString ("avglinfo");
				avg[4] = rs.getString ("avgtact");
				avg[5] = rs.getString ("avgcriper");
			}
			
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Estrapolazione e calcolo dati legati alle segnalazioni di eventi avversi per il centro \""
					+ nomecentro.toUpperCase() + "\" avvenuta correttamente.");
			
			return avg;
		}
		
		// restituisci vettore vuoto per indicare il fatto che non vi sono dati legati a questo centro	
		else {
			
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Non sono ancora state rilasciate segnalazioni di eventi avversi per il centro \"" + nomecentro.toUpperCase() + "\".");
			return null;
		}
	}
	
	// metodo che consente la visualizzazione di informazioni (numero segnalazioni) legate ad un determinato centro estrapolate dal DB
	public synchronized double[] infoCOUNTCentro(String nomecentro) throws SQLException, RemoteException {
		
		double[] count = new double[6];
		
		// condizione clausola where
		String where = "centro = '" + nomecentro + "'";
		
		// verifica informazioni associate al centro già registrate nel sistema
		if (rowChecking("eventi_avversi", where)) {
			
			// memorizzazione dei valori nel vettore
			count[0] = count("maltesta","cmaltesta",nomecentro);
			count[1] = count("febbre","cfebbre",nomecentro);
			count[2] = count("dolmuar","cdolmuar",nomecentro);
			count[3] = count("linfo","clinfo",nomecentro);
			count[4] = count("tac","ctac",nomecentro);
			count[5] = count("criper","ccriper",nomecentro);
			
			return count;
		}
		
		// restituisci vettore vuoto per indicare il fatto che non vi sono dati legati a questo centro
		else return count;
	}
	
	// metodo che consente di prelevare il valore relativo al numero di segnalazioni sintomi post vaccinazioni 
	// calcolandolo in base ai dati presenti in un certo momento nel DB
	public double count(String nomeColDB, String newNomeCol, String nomecentro) throws SQLException {
		
		// valore conteggio
		double count = 0;
		
		// interrogazione parametrizzata al DB
		String query = "SELECT COUNT (" + nomeColDB + ") AS " + (newNomeCol) + " FROM eventi_avversi " +
					   " WHERE centro = '" + nomecentro + "' AND " + nomeColDB + "<> 0";
		
		// esecuzione comando tramite DBMS
		ResultSet rs = stmt.executeQuery(query);
		
		// memorizzazione valore calcolato
		while (rs.next()) {
			count = rs.getDouble(newNomeCol);
		}
		
		return count;
	}

	// metodo che consente l'inserimento di un cittadino nel DB
	// restituisce TRUE se l'operazione di registrazione di un nuovo cittadino va a buon fine, FALSE altrimenti
	// nel secondo caso, oltre ad eventuali eccezioni, si può verificare il caso in cui il cittadino sia già
	// stato registrato
	public synchronized boolean insertCittadino (String CF, String nome, String cognome, String email, 
		String UID, String password, String VID) throws SQLException, RemoteException {
		
		// condizione clausola where
		String where = "uid = '" + UID + "'";
		
		// nome utente non disponibile
		if(rowChecking("cittadini_registrati", where)) {
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Operazione fallita. Nome utente \"" + 
					UID + "\" non disponibile perchè già utilizzato nel sistema.");
			return false;
		}
		
		// nome utente non ancora utilizzato
		else {
			
			// condizione clausola where
			where = "cf = '" + CF + "'";
			
			// cittadino già registrato nel sistema con username differente
			if(rowChecking("cittadini_registrati", where)) {
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Errore nell'inserimento del nuovo cittadino nel sistema. " + " \"" + nome.toUpperCase() + " " +
						cognome.toUpperCase() + "\" è già stato memorizzato precedentemente!");
				return false;
			}
			
			// la registrazione può avvenire correttamente
			else {
				
				// stringa contenente comando in sintassi SQL
				String cmd = "INSERT INTO cittadini_registrati VALUES ("
							 + VID + "," 
							 + "'" + CF.toUpperCase() + "'," 
							 + "'" + nome.toLowerCase() + "',"  
							 + "'" + cognome.toLowerCase() + "'," 
							 + "'" + email.toLowerCase() + "',"  
							 + "'" + UID + "',"  
							 + "'" + password + "')"; 
		
				// esecuzione comando tramite DBMS
				stmt.executeUpdate(cmd);
				
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Inserimento nuovo cittadino \""+ nome.toUpperCase() + " " + cognome.toUpperCase() 
				+ "\" nel sistema avvenuto correttamente.");
				
				return true;
			}
		}
		
	}

	// metodo che consente all'utente registrato di accedere al sistema e restituisce una stringa contenente il suo UID se
	// se l'operazione va a buon fine oppure una stringa vuota
	public synchronized String login(String name, String password) 
			throws SQLException, RemoteException {
		
		boolean esito = false;
		
		// stringa contenente comando in sintassi SQL
		String query = "SELECT EXISTS (SELECT * FROM cittadini_registrati WHERE uid = '" + name + "' AND "
				+ "password = '" + password + "') AS EXISTS";
		
	    // esecuzione comando tramite DBMS
		ResultSet rs = stmt.executeQuery(query);
		
		// controllo risultato query
		while(rs.next())
			esito = rs.getBoolean("exists");
		
		// esito positivo
		if (esito) {
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Credenziali valide. Accesso al sistema avvenuto correttamente (" + 
					name + ").");
			return name;
		}
		
		// esito negativo
		else {
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Credenziali non corrette. Accesso al sistema negato (" + 
					name + ").");
			return "";
		}
		
	}

	// metodo che consente di registrare nel DB eventuali eventi avversi avvenuti dopo la vaccinazione per COVID19.
	// restituisce TRUE se l'operazione va a buon fine, FALSE altrimenti
	public synchronized boolean insertEventiAvversi(String uid, 
			String nomecentro, String[] sev, String note) throws SQLException, RemoteException {
		
		String where = "uid = '" + uid + "'";
		
		// verifica se l'utente ha già inserito segnalazioni precedentemente
		if(rowChecking("eventi_avversi", where)) {
			System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Operazione fallita. \"" + uid + "\" ha già inserito delle segnalazioni precedentemente "
					+ "e non puo' modificarle.");
			return false;
		}
		
		
		// prima volta
		else {
			
			// eliminazione di eventuali spazi da nome centro
			String struttura = "";
			if(nomecentro.contains(" "))
				struttura = nomecentro.replace(" ", "_");
			else
				struttura = nomecentro;
			
			// evita bug convenzioni postgres
			struttura = struttura.toLowerCase();
			
			// insieme di tutti i cf di coloro che si sono vaccinati in quel centro
			String subquery = "SELECT cf"
				+   " FROM vaccinati_" + struttura + " v";
			
			// cf di coloro che hanno lasciato una recensione eventi avversi
			String query = "SELECT c.cf AS po"
					+ "     FROM cittadini_registrati c"
					+ "		WHERE c.uid = '" + uid + "' AND c.cf IN (" + subquery + ")";
			
			// esecuzione query
			ResultSet rs = stmt.executeQuery(query);
			
			// se la query non ha prodotto risultati significa che l'utente non si è vaccinato in tale centro
			if (!rs.isBeforeFirst() ) {
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Operazione fallita. \"" + uid + "\" non è stato vaccinato nel "
						+ "centro \""+ nomecentro.toUpperCase() + "\".");
				return false;
			}
			
			// l'operazione di inserimento di segnalazioni nel DB può svolgersi correttamente
			else {
				
				// stringa contenente comando di inserimento dati in sintassi SQL
				String cmd = "INSERT INTO eventi_avversi VALUES ("
						 	 + "'" + uid + "',"
						 	 + "'" + nomecentro.toLowerCase() + "',"
						 	 + "'" + sev[0] + "',"  
							 + "'" + sev[1] + "',"  
							 + "'" + sev[2] + "',"  
							 + "'" + sev[3] + "',"
							 + "'" + sev[4] + "',"
							 + "'" + sev[5] + "',"
							 + "'" + note.toLowerCase() + "')";
				
				// esecuzione comando di inserimento nuova row
				stmt.executeUpdate(cmd);
				System.out.println("\n" + dtf.format(LocalDateTime.now()) + " - Registrazione segnalazioni per eventi avversi nel sistema avvenuta correttamente (" 
						+ uid + ").");
				return true;
			}
			
		}
		
	}

	// server main
	public static void main (String args[]) throws RemoteException {
		
		// Pubblicazione server nel registro per ottenere metodi da remoto
		try{  
		    
			// creazione oggetto server
			ServerDBMS obj = new ServerDBMS();
		    
			// creazione registro per la memorizzazione del riferimento remoto al server
			Registry registro = LocateRegistry.createRegistry(1099);
		    
			// memorizzazione riferimento remoto server per permettere l'accesso ai client
			registro.rebind("SERVER", obj);
		    
			System.out.println(obj.dtf.format(LocalDateTime.now()) + 
					" - Collegamento con il server avvenuto correttamente. Ora e' utilizzabile!");
			
		} catch (Exception e){
		    e.printStackTrace();  
			System.out.println("Errore nel caricamento del server. Riprovare."); }
	}
}

