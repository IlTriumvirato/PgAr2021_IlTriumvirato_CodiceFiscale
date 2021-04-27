package it.unibs.fp.codicefiscale;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;


public class CodiceFiscaleMain {
	
	public static ArrayList<Persona> dp;
	public static ArrayList<String>  dcf;
	public static ArrayList<Comune>  dc;
	public static ArrayList<String>  invalidi;
	
	public static void main(String[] args) throws XMLStreamException {
		
		dp=new ArrayList<Persona>();
		dcf=new ArrayList<String>();
		dc=new ArrayList<Comune>();
		
		
		
		dp=InputOutputXML.prendiInInputPersone("xmlInputFiles/inputPersone.xml");
		dcf=InputOutputXML.prendiInInputCodiciFiscali("xmlInputFiles/codiciFiscali.xml");
		dc=InputOutputXML.prendiInInputComuni("xmlInputFiles/comuni.xml");
		
		dp.get(1).stampaNomeCognome();
		System.out.println(dp.get(1).getSesso());
		System.out.println(dp.get(1).getComune_nascita());
		System.out.println(dp.get(1).getData_nascita());

		
		
		
		dp.get(1).generaCodiceFiscale();
		//quiSystem.out.println(dp.get(1).getCodiceFiscale());
		
		for(int i=0; i<dp.size(); i++) {
			dp.get(i).generaCodiceFiscale();
		}
		
		
		/*quifor(int i=0;i<4;i++) {
			System.out.println("Codice nuovo: "+dcf.get(i));			
		}

		for(int i=0;i<4;i++) {
			System.out.println("Comune di nome: "+dc.get(i).getNome()+"\n e codice "+dc.get(i).getCodice());			
		}
		
		//System.out.println("TROVATO: "+dcf.getCodici().get(0));
		
		/*
			dobbiamo prendere i dati da persone.xml, comuni.xml, 
			
			
			input le persone, input i comuni e generiamo i codici
			
			poi
			
			verificare la validità di quelli
			
			
			+ eventuali javadoc        un sacco di controlli a prova di idiota
			
			
			
		*/
		
		
		
		Constants.controllaPresenze(dp, dcf);
		
		
		/*for(int i=0; i<dp.size(); i++) {
			System.out.println(dp.get(i).isCodiceAssente());
		}*/
		
		boolean r;
		for(int i=0; i<dcf.size(); i++) {
			r= Constants.controllaCodice(dcf.get(i));
			if(r==false) {
				//metto il codice in invalidi
			}
		}
		
		
	}

}
