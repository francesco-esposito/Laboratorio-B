/* Alonzi Alessandro 741411 VA (Project Manager)
Esposito Francesco 739888 VA */

package cittadini;
import java.util.Scanner;

/**Classe ausiliaria che permette la creazione di oggetti che consentono di descrivere
 * un un cittadino memorizzandone le informazioni richieste
 * ATTENZIONE: una volta creato l'oggetto che descrive un dato indirizzo, i suoi dati NON possono
 * essere modificati per motivi di sicurezza (quindi non vi sono metodi "setter")
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class Cittadino {
	private String nome;
	private String cognome;
	private String cf;
	private String mail;
	private String UID;
	private String password;
	private String VID;
	

	/**@return Costruzione dell'oggetto solo con tutte le info necessarie
	 * inizializzazione variabili di stato costruttore con visibilità di package.*/
	Cittadino(String nome, String cognome, String cf,
			String UID, String password, String VID, String mail){
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.mail = mail;
		this.UID = UID;
		this.password = password;
		this.VID = VID;
	}
	

	/**@return Il metodo InfoVaccinato() permette la creazione di un oggetto Centro vaccinale inserendo le info necessarie.
	 * restituisce pertanto un'istanza delle classe stessa.*/
	public static Cittadino InfoCittadino() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n***ATTENZIONE: avvio procedura REGISTRAZIONE NUOVO CITTADINO***");
		System.out.println("LEGGERE ATTENTAMENTE LE ISTRUZIONI RIPORTATE");
		System.out.println("Inserire SOLO i dati RICHIESTI");
		
		System.out.print("Nome: "); String nome = in.nextLine();
		
		System.out.print("Cognome: "); String cognome = in.nextLine();
		
		System.out.print("Codice fiscale: "); String cf = in.nextLine();
		
		System.out.print("E-mail personale: "); String mail = in.nextLine();
		
		System.out.print("Inserisci il tuo nome utente: "); String UID = in.nextLine();
		
		System.out.print("Inserisci la tua password: "); String password = in.nextLine();
		
		System.out.print("Inserisci identificativo vaccinazione (fornito al momento della vaccinazione): ");
		String VID = in.nextLine();
		
		// costruzione di un nuovo oggetto tramite costruttore
		return new Cittadino(nome, cognome, cf, UID, password, VID, mail);
	}
	
	// metodi getter
	public String getNome() {return this.nome;}
	public String getCognome() {return this.cognome;}
	public String getCF() {return this.cf.toUpperCase();}
	public String getUID() {return this.UID;}
	public String getPassword() {return this.password;}
	public String getVID() {return this.VID;}
	public String getMail() {return this.mail;}
	
}