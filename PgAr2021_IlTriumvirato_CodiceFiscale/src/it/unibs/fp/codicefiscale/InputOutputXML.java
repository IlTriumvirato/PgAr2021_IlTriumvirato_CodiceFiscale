package it.unibs.fp.codicefiscale;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputOutputXML {
	
	private static XMLStreamReader getXmlReader(String filename) {
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			
		} catch (Exception e){
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
			
		}
		
		return xmlr;
	}
	
	public static ArrayList<Persona> prendiInInputPersone(String filename) {
		
		ArrayList<Persona> dp=new ArrayList<Persona>();
		
		XMLStreamReader xmlr=getXmlReader(filename);
		
		try {
			String selezione="";
			
			while (xmlr.hasNext()) {
				
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_ELEMENT: 
						 selezione=xmlr.getLocalName();
						 if(selezione.equals("persona")) {
							 dp.add(new Persona(Integer.parseInt(xmlr.getAttributeValue(0))));
						 }
						 
					 break;
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					 
						 if (xmlr.getText().trim().length() > 0) {// controlla se il testo non contiene solo spazi
							 int n_persona=dp.size()-1;
							 
							 if(selezione.equals("nome")) {
								 dp.get(n_persona).setNome(xmlr.getText());
							 }else if(selezione.equals("cognome")) {
								 dp.get(n_persona).setCognome(xmlr.getText());
							 }else if(selezione.equals("sesso")) {
								 dp.get(n_persona).setSesso(Character.toUpperCase(xmlr.getText().charAt(0)));
							 }else if(selezione.equals("comune_nascita")) {
								 dp.get(n_persona).setComune_nascita(xmlr.getText());
							 }else if(selezione.equals("data_nascita")) {
								 dp.get(n_persona).setData_nascita(LocalDate.parse(xmlr.getText()));

							 }
						 }

					 break;
				 }
				 
				 
				 xmlr.next();
				
				 
			}
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return dp;
		
	}
	
	
	public static ArrayList<String> prendiInInputCodiciFiscali(String filename) {
		
		ArrayList<String> dcf=new ArrayList<String>();
		XMLStreamReader xmlr=getXmlReader(filename);
		
		try {
			while (xmlr.hasNext()) {
				
				switch (xmlr.getEventType()) { 
					case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: prende il codice
							 
						if (xmlr.getText().trim().length() > 0) {// controlla se il testo non contiene solo spazi
							
							dcf.add(new String(xmlr.getText()));
						}
		
					break;
				}
				 
				xmlr.next();
				 
			}
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return dcf;
		
	}
	
	
	
	
	
	public static ArrayList<Comune> prendiInInputComuni(String filename) {
		filename="xmlInputFiles/comuni.xml";
		
		ArrayList<Comune> dc=new ArrayList<Comune>();
		
		XMLStreamReader xmlr=getXmlReader(filename);
		
		try {
			String selezione="";
			
			while (xmlr.hasNext()) {
				
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_ELEMENT: 
						 selezione=xmlr.getLocalName();
						 if(selezione.equals("comune")) {
							 dc.add(new Comune());
						 }
						 
					 break;
					 case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo
					 
						 if (xmlr.getText().trim().length() > 0) {// controlla se il testo non contiene solo spazi
							 int n_comune=dc.size()-1;
							 
							 if(selezione.equals("nome")) {
								 dc.get(n_comune).setNome(xmlr.getText());
							 }else if(selezione.equals("codice")) {
								 dc.get(n_comune).setCodice(xmlr.getText());
							 }						 }

					 break;
				 }
				 
				 
				 xmlr.next();
				
				 
			}
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
		return dc;
		
	}
	
	
	
	
	
}