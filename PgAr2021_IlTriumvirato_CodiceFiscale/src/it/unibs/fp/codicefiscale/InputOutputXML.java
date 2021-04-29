package it.unibs.fp.codicefiscale;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.sound.midi.Synthesizer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


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
		//filename="xmlInputFiles/comuni.xml";
		
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
	
	
	public static void OutputXML(ArrayList<Persona> dp,ArrayList<String> listaInvlidi,ArrayList<String> listaSpaiati) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db=null;
		db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();

		//elemento radice <output>
		Element output = doc.createElement("output");
		
		//CREAZIONE ELEMENTI DI PERSONE
		
		//elemento <persone>
		Element persone= doc.createElement("persone");
		//creo attributo numero
		Attr numero_persone=doc.createAttribute("numero");
		numero_persone.setValue(String.valueOf(dp.size()));
		//aggiungo numero a persone
		persone.setAttributeNode(numero_persone);
		//INIZIO FOR
		for(int i=0;i<dp.size();i++) {
			//creo persona
			Element persona=doc.createElement("persona");
			//creazione tutto per persona
			Attr id_persona=doc.createAttribute("id");
			Element nome=doc.createElement("nome");
			Element cognome=doc.createElement("cognome");
			Element sesso=doc.createElement("sesso");
			Element comune_nascita=doc.createElement("comune_nascita");
			Element data_nascita=doc.createElement("data_nascita");
			Element codice_fiscale=doc.createElement("codice_fiscale");
			
			//settaggio values
			id_persona.setValue(String.valueOf(i));
			nome.setTextContent(dp.get(i).getNome());
			cognome.setTextContent(dp.get(i).getCognome());
			sesso.setTextContent(String.valueOf(dp.get(i).getSesso()));
			comune_nascita.setTextContent(dp.get(i).getComune_nascita());
			data_nascita.setTextContent((dp.get(i).getData_nascita()).toString());
			
			if(dp.get(i).getCodiceAssente()) {
				codice_fiscale.setTextContent("ASSENTE");
			}else {
				codice_fiscale.setTextContent(dp.get(i).getCodiceFiscale());
			}
			//attaccare tutto a persona
			persona.setAttributeNode(id_persona);
			persona.appendChild(nome);
			persona.appendChild(cognome);
			persona.appendChild(sesso);
			persona.appendChild(comune_nascita);
			persona.appendChild(data_nascita);
			persona.appendChild(codice_fiscale);
			//attaccare persona a persone
			persone.appendChild(persona);
		}
		
		//elemento <codici>
		Element codici= doc.createElement("codici");
		//invalidi
		Element invalidi= doc.createElement("invalidi");
		Attr numero_invalidi=doc.createAttribute("numero");
		numero_invalidi.setValue(String.valueOf(listaInvlidi.size()));
		invalidi.setAttributeNode(numero_invalidi);
		//inizio for codice invalido
		for(int i=0;i<listaInvlidi.size();i++) {
			Element codice_invalido= doc.createElement("codice");
			//settaggio values
			codice_invalido.setTextContent(listaInvlidi.get(i));
			//attaccare un codice a codici invalidi
			invalidi.appendChild(codice_invalido);
			//fine for
		}
			
		//codici spaiati
		Element spaiati= doc.createElement("spaiati");
		Attr numero_spaiati=doc.createAttribute("numero");
		numero_spaiati.setValue(String.valueOf(listaSpaiati.size()));
		spaiati.setAttributeNode(numero_spaiati);
		//un codice spaiato, inizio for
		for(int i=0;i<listaSpaiati.size();i++) {
			Element codice_spaiato= doc.createElement("codice");
			//settaggio value
			codice_spaiato.setTextContent(listaSpaiati.get(i));
			//attaccare un codice ai codici spaiati
			spaiati.appendChild(codice_spaiato);
			//fine for
		}
		
			
		//attaccare tutto a codici
		codici.appendChild(invalidi);
		codici.appendChild(spaiati);
		
		//attaccare tutto ad output
		output.appendChild(persone);
		output.appendChild(codici);
		//attaccare output al documento principale
		doc.appendChild(output);
	

		// write the content into xml file
	    Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.INDENT,"yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(Constants.PATH_RELATIVO_OUTPUT));

		// Output to console for testing
		//StreamResult result = new StreamResult(System.out);

		tf.transform(source, result);

	}
	
	

}