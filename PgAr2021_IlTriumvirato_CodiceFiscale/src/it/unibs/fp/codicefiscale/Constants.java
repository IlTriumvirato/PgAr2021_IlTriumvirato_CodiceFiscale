package it.unibs.fp.codicefiscale;

public class Constants {
	public static final char FILLER_CHAR='X';
	public static final int DIVISORE_CARATTERE_CONTROLLO=26;
	public static final int GENDER_NUMBER_TO_ADD=40;
	public static final char[] MONTH_CHARS= {'A','B','C','D','E','H','L','M','P','R','S','T'};
	public static final int[] GIORNI_PER_MESE= {31,29,31,30,31,30,31,31,30,31,30,31};
	public static final String INFORMATION_MESSAGE="Benvenuto nel nostro programma, per ottenere le informazioni sui codici fiscali di un gruppo di persone, inserire le seguenti informazioni nei file indicati, i quali si trovano tutti nella cartella xmlInputFiles all'interno della directory principale:\n1-dati delle persone in inputPersone.xml\n2-dati dei comuni in comuni.xml\n3-codici fiscali di partenza(che verranno verificati) in codiciFiscali.xml\n\n\n I dati da lei forniti verranno elaborati e restituiti in formato xml nel file output.xml presente nella cartella xmlOutputFiles che si trova nella directory principale del programma.\nIl file di output conterrà i dati delle persone con i relativi codici fiscali da lei forniti in codiciFiscali.xml, sarà effettuato però un controllo correttezza e scritto \"ASSENTE\" per i codici fiscali errati o assenti.\nSaranno inoltre riportati di seguito i codici fiscali errati o non corrispondenti a nessuna persona";
	public static final String ERROR_MESSAGE_FILE_ASSENTI="";
	public static final String ERROR_MESSAGE_FILE_INVALIDI="";
}
