package it.unibs.fp.codicefiscale;

import java.util.ArrayList;

import javax.xml.stream.XMLStreamException;


public class CodiceFiscaleMain {
	
	public static ArrayList<Persona> dp;
	public static ArrayList<String>  dcf;
	public static ArrayList<Comune>  dc;
	public static CodiciProblematici listaErrati;
	
	public static void provideProgramInformation() {
		System.out.println(Constants.INFORMATION_MESSAGE);
	}
	
	public static void main(String[] args) throws XMLStreamException {
		
		//inizializzazione
		//give program's inner workings information
		//dataInput()
		//dataProcessing()
		//dataOutput()
		dp=InputOutputXML.prendiInInputPersone("xmlInputFiles/inputPersone.xml");
		dcf=InputOutputXML.prendiInInputCodiciFiscali("xmlInputFiles/codiciFiscali.xml");
		dc=InputOutputXML.prendiInInputComuni("xmlInputFiles/comuni.xml");
		
		Maps.inizializzaMappe(dc);
		//Maps.inizializzaMappaComuniDalCodice(dc);
		
		for(int i=0; i<dp.size(); i++) {
			dp.get(i).generaCodiceFiscale();
		}
		
		//se CodiceAssente=false allora c'è il codice 
		// contiene cf delle persone non presenti in dp
		ArrayList<String> invalidiESpaiati=ElaboraCF.controllaPresenze(dp, dcf);
		//controlla quali codici sono errati
		listaErrati=ElaboraCF.selezioneAnomalie(invalidiESpaiati);
		
		/*
		//System.out.println(listaErrati.getCodiciSpaiati().get(17));
		for(int i=0;i<15;i++) {
			//come mai viene fatto i<15 invece di puntare direttamente al size?
			//perché questo for era solo un piccolo test
			if(i<invalidiESpaiati.size()) {
				//System.out.println(listaErrati.getCodiciInvalidi().get(i));
			}
		}*/
		
		
		//metodoDiOutputDiApu(dp,listaErrati.getCodiciInvalidi(),listaErrati.getCodiciSpaiati());
		try {
			InputOutputXML.OutputXML(dp, listaErrati.getCodiciInvalidi(),listaErrati.getCodiciSpaiati());
		}catch (Exception e) {
			// TODO: handle exception
		}

	}

}
