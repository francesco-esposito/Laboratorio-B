package centrivaccinali;
import java.io.Serializable;
import java.util.Scanner;

/** classe ausiliaria che permette la creazione di oggetti che consentono di descrivere
  * qualsiasi centro vaccinale memorizzandone le informazioni richieste
  * ATTENZIONE: una volta creato l'oggetto che descrive una struttura vaccinale, i suoi dati NON possono
  * essere modificati per motivi di sicurezza (quindi non vi sono metodi "setter")
  * @author Alessandro Alonzi
  * @author Francesco Esposito */

public class CentroVacc implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String tipologia;
	private Indirizzo indirizzo;
	
	/**@return Costruzione dell'oggetto solo con tutte le info necessarie
	 * inizializzazione variabili di stato costruttore con visibilità di package.*/
	public CentroVacc(int id, String name, String tipologia, Indirizzo indirizzo){
		this.id = id;
		this.name = name;
		this.tipologia = tipologia;
		this.indirizzo = indirizzo;
	}
	
	/**@return Il metodo InfoCentro() permette la creazione di un oggetto Centro vaccinale inserendo le info necessarie.
	 * restituisce pertanto un'istanza delle classe stessa.*/
	public static CentroVacc InfoCentro() {
		Scanner tastiera = new Scanner(System.in);
		System.out.println("\n***ATTENZIONE: avvio procedura REGISTRAZIONE NUOVO CENTRO***");
		System.out.println("LEGGERE ATTENTAMENTE LE ISTRUZIONI RIPORTATE.");
		System.out.println("Inserire SOLO i dati RICHIESTI.\n");
		
		System.out.print("Nome struttura: "); String qualificatore = tastiera.nextLine();
		
		System.out.print("Tipologia [ospedaliero/hub/aziendale]: "); String tipologia = tastiera.nextLine(); System.out.println("");
		
		int id = 6;
		
		// costruzione di un nuovo oggetto tramite costruttore
		return new CentroVacc(id,qualificatore, tipologia, Indirizzo.inserisciInfo());
	}
	
	// metodi getter
	public String getNome() {return this.name;}
	public String getTipologia() {return this.tipologia;}
	public Indirizzo getIndirizzo() {return this.indirizzo;}
	public int getId() {return this.id;}
	
	
	public String tostring() {
		return "***INFORMAZIONI STRUTTURA " + getNome().toUpperCase() + "***\n" + 
			   "Tipologia centro: " + getTipologia() + "\n" +
		       "Indirizzo centro: " + indirizzo.toString();
	}
}

	

	
