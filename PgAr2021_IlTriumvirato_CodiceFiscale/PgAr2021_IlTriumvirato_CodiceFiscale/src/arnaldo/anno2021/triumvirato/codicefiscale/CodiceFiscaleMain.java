package arnaldo.anno2021.triumvirato.codicefiscale;

public class CodiceFiscaleMain {

	public static void main(String[] args) {
		
		DatiPersone dp=new DatiPersone();
		
		dp=InputOutputXML.prendiInInputPersone("xmlInputFiles/inputPersone.xml");
		
		dp.arrayPersone.get(0).stampaNomeCognome();
		System.out.println(dp.arrayPersone.get(0).getSesso());
		System.out.println(dp.arrayPersone.get(0).getComune_nascita());
		System.out.println(dp.arrayPersone.get(0).getData_nascita());

		/*
			dobbiamo prendere i dati da persone.xml, comuni.xml, 
			
			
			input le persone, input i comuni e generiamo i codici
			
			poi
			
			verificare la validità di quelli
			
			
			+ eventuali javadoc        un sacco di controlli a prova di idiota
			
			
			
		*/
	}

}
