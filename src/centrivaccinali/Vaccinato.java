package centrivaccinali;
import java.util.Scanner;

/**Classe ausiliaria che permette la creazione di oggetti che consentono di descrivere
 * un vaccinato regolare memorizzandone le informazioni richieste
 * ATTENZIONE: una volta creato l'oggetto che descrive un dato indirizzo, i suoi dati NON possono
 * essere modificati per motivi di sicurezza (quindi non vi sono metodi "setter")
 * @author Alessandro Alonzi
 * @author Daniel Pedrotti
 * @author Francesco Esposito 
 */

public class Vaccinato {
	private final static char separatore = ','; 
	private String nomeCentro;
	private String nomeVaccinato;
	private String cognomeVaccinato;
	private String cf;
	private String vaccino;
	private String data; 
	private String VID;
	
	/**@return Costruzione dell'oggetto solo con tutte le info necessarie
	 * inizializzazione variabili di stato costruttore con visibilità di package.*/
	Vaccinato(String nomeCentro, String nomeVaccinato, String cognome, String cf,
			String vaccino, String data){
		this.nomeCentro = nomeCentro;
		this.nomeVaccinato = nomeVaccinato;
		this.cognomeVaccinato = cognome;
		this.cf = cf;
		this.vaccino = vaccino;
		this.data = data;
	}
	
	/**@return Il metodo InfoVaccinato() permette la creazione di un oggetto Centro vaccinale inserendo le info necessarie.
	 * restituisce pertanto un'istanza delle classe stessa.*/
	public static Vaccinato InfoVaccinato() {
		Scanner in = new Scanner(System.in);
		
		System.out.println("\n***ATTENZIONE: avvio procedura REGISTRAZIONE NUOVO VACCINATO***");
		System.out.println("LEGGERE ATTENTAMENTE LE ISTRUZIONI RIPORTATE.");
		System.out.println("Inserire SOLO i dati RICHIESTI.\n");
		
		System.out.print("Nome: "); String nome = in.nextLine();
		
		System.out.print("Cognome: "); String cognome = in.nextLine();
		
		System.out.print("Nome centro di vaccinazione: "); String nomeCentro = in.nextLine();
		
		System.out.print("Codice fiscale: "); String cf = in.nextLine();
		
		System.out.print("Nome vaccino somministrato [Pfizer/AstraZeneca/Moderna/J&J]: "); String vaccino = in.nextLine();
		
		System.out.print("Data vaccinazione --> Giorno [gg]: "); String day = in.nextLine();
		System.out.print("Data vaccinazione --> Mese [mm]: "); String mese = in.nextLine();
		System.out.print("Data vaccinazione --> Anno [aaaa]: "); String anno = in.nextLine();
		
		// costruzione di un nuovo oggetto tramite costruttore
		return new Vaccinato(nomeCentro, nome, cognome, cf,vaccino, day + "/" + mese + "/" + anno);
	}
	
	// metodi getter
	public String getNomeVaccinato() {return this.nomeVaccinato;} public String getCognome() {return this.cognomeVaccinato;}
	public String getNomeCentro() {return this.nomeCentro;} public String getCF() {return this.cf.toUpperCase();}
	public String getVaccino() {return this.vaccino;} public String getVID() {return this.VID;} 
	public String getData() {return this.data;} public void setVID(String VID) {this.VID = VID;}
}