package arnaldo.anno2021.triumvirato.codicefiscale;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class InputOutputXML {
	
	public static DatiPersone prendiInInputPersone(String filename) {
		//String
		filename="xmlInputFiles/inputPersone.xml";
		//DatiPersone dp=new DatiPersone();
		ArrayList<Persona> dp=new ArrayList<Persona>();
		
		XMLInputFactory xmlif = null;XMLStreamReader xmlr = null;
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(filename, new FileInputStream(filename));
			
		} catch (Exception e){
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
			
		}
		
		try {
			String selezione="";
			String appoggio="";
			
			while (xmlr.hasNext()) {
				
				 switch (xmlr.getEventType()) { 
					 case XMLStreamConstants.START_DOCUMENT: 
						 
					 break;
					 case XMLStreamConstants.START_ELEMENT: 
						 selezione=xmlr.getLocalName();
						 if(selezione.equals("persona")) {
							 dp.add(new Persona(Integer.parseInt(xmlr.getAttributeValue(0))));
						 }
						 
						 /*for (int i = 0; i < xmlr.getAttributeCount(); i++)
							 System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i));
						 */
						 
						 
					 break;
					 case XMLStreamConstants.END_ELEMENT:
						 
				     break;
				     
					 case XMLStreamConstants.COMMENT:
						 
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
					
						     //System.out.println(selezione+": "+xmlr.getText());
						 
						 
						 }
						 //System.out.println("-> " + xmlr.getText());
					 break;
				 }
				 
				 
				 xmlr.next();
				
				 
			}
		
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new DatiPersone(dp);
		
	}
	
}
