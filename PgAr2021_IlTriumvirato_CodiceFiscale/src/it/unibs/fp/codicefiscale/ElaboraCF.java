package it.unibs.fp.codicefiscale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ElaboraCF {

	public static String getCodiceComuneByNome(String nome) {
		return Maps.getMappaComuniDalCodice().get(nome);
	}
	
	public static ArrayList<String> controllaPresenze(ArrayList<Persona> dp, ArrayList<String> dcf) {
		
		for(int i=0; i<dp.size(); i++) {
			dp.get(i).setCodiceAssente(true); //conviene mettere direttamente nel costruttuore Cod.ass.=true
		}
		
		for(int i=0; i<dp.size(); i++) {
			for(int j=0; j<dcf.size(); j++) {
				if(dp.get(i).getCodiceFiscale().equals(dcf.get(j))){
					dp.get(i).setCodiceAssente(false);
					dcf.remove(j);
					j--;
				}
			}
		}
		
		//spaiati invalidi
		return dcf;
	}
	
	public static CodiciProblematici selezioneAnomalie(ArrayList<String> codiciAnomali) {
		ArrayList<String> invalidi=new ArrayList<String>();
		ArrayList<String> spaiati=new ArrayList<String>();
		
		for(String codice:codiciAnomali) {
			if(controllaCodice(codice)) {
				spaiati.add(new String(codice));
			}else {
				invalidi.add(new String(codice));
			}
		}
		
		return new CodiciProblematici(invalidi,spaiati);
	}
	
	public static boolean controlloRange(char x, char base, char ceil) {
		boolean prova;
		if(base<=x&&x<=ceil) {
			prova = true;
		} else {
			prova = false;
		}
		
		return prova;
	}
	
	
	public static boolean controllaCodice(String codice) {
		
		if(codice.length()!=16) {
			return false;
		
		}else{
			
			boolean r=true;
			
			char[] basi=new char[16];
			char[] ceils=new char[16];
			//CCCNNN01L23G009C
			
			basi[0]='A';ceils[0]='Z';
			basi[1]='A';ceils[1]='Z';
			basi[2]='A';ceils[2]='Z';
			
			basi[3]='A';ceils[3]='Z';
			basi[4]='A';ceils[4]='Z';
			basi[5]='A';ceils[5]='Z';
			
			basi[6]='0';ceils[6]='9';
			basi[7]='0';ceils[7]='9';
			
			//8th char is month, verification happens later
			
			basi[9]='0';ceils[9]='7';
			basi[10]='0';ceils[10]='9';
			
			basi[11]='A';ceils[11]='Z';
			basi[12]='0';ceils[12]='9';
			basi[13]='0';ceils[13]='9';
			basi[14]='0';ceils[14]='9';
			
			basi[15]='A';ceils[15]='Z';
	
			
			
			for(int i=0;i<16;i++) {
				
				if(i!=8) {
					boolean appoggio;
					appoggio = controlloRange(codice.charAt(i),basi[i],ceils[i]);
					r=r&&appoggio;
				}else {
					boolean appoggio=false;
					for(int j=0;j<12;j++) {
						if(Constants.MONTH_CHARS[j]==codice.charAt(i)) {
							appoggio=true;
							break;
						}
					}
					r=r&&appoggio;
				}
				
				if(!r)break;
			}
			
			if(r){
				int giornoNascita=Integer.parseInt(codice.substring(9,11));
				if(giornoNascita>31&&giornoNascita<41 || giornoNascita>71) {
					r=false;
				}
			}
			
			
			
			if(r){
				r=r&&codice.charAt(15)==calcolaCarattereControllo(codice.substring(0,15));
			}
			
			
			if(r) {
				Map<Character,Integer> mappaGiorniMesi=Maps.getMappaGiorniMesi();
				
				int numeroGiorno=Integer.parseInt(codice.substring(9,11));
				
				char carattereMese=codice.charAt(8);
				
				if(numeroGiorno>31) { //qui basta questa condizione perché c'è già un controllo sulla validità del numero prima, quindi se è >31, è semplicemente individuo femmina
					numeroGiorno-=40;
				}
				
				if(numeroGiorno>mappaGiorniMesi.get(carattereMese)) {
					r=false;
				}
			}
			
			
			if(r) {
				boolean vocaleTrovata=false;
				
				for(int i=0;i<3;i++) {
					if(isVowel(codice.charAt(i))){
						vocaleTrovata=true;
					}else {
						if(vocaleTrovata){
							if(codice.charAt(i)!='X') {
								r=false;
								break;
							}
						}
					}
				}
			}
			
			
			if(r){
				boolean vocaleTrovata=false;
				
				for(int i=3;i<6;i++) {
					if(isVowel(codice.charAt(i))){
						vocaleTrovata=true;
					}else {
						if(vocaleTrovata){
							if(codice.charAt(i)!='X') {
								r=false;
								break;
							}
						}
					}
				}
			}
			
         
			/*AAA AAA                              chiedere a Lange per i comuni
			codice.substring(0,6)   tutte lettere  //nome+cognome
			codice.substring(6,8)   tutti numeri
			codice.substring(8,9)   lettera nell'array
			//giorno
			codice.substring(9,11)     devono essere numeri,   in totale deve essere nel range del mese*/
			 
			/*codice.substring(11,12)    lettera  
			codice.substring(12,15)    numero
	
			
			codice.substring(15,16)    E E */
			
			/*
				
				
			boolean presente=false;
			for(String s:codiceCitta) {
				if(s.equals(codice.substring(11,15))) {
					presente=true;
					break;
				}
			}
			r=r&presente;*/
			
			return r;
		}
	}
	
	
	public static char calcolaCarattereControllo(String cfIncompleto) {
		if(cfIncompleto.length()==15) {
			int somma=0;
			for(int i=1;i<15;i+=2) {
				somma+=Maps.getMappaPari().get(cfIncompleto.charAt(i));
			}
			
			for(int i=0;i<15;i+=2) {
				somma+=Maps.getMappaDispari().get(cfIncompleto.charAt(i));
			}
			

			char codiceControllo=(char)('A'+somma%Constants.DIVISORE_CARATTERE_CONTROLLO);
			return codiceControllo;
			
		}else {
			return ' ';//errore
		}
		
	}

	
	/*
	 * ritorna true se il carattere dato è una vocale, altrimenti ritorna false, usa le proprietà booleane per farlo in maniera compatta
	 */
	public static boolean isVowel(char letter) {
		return letter=='A'||letter=='E'||letter=='I'||letter=='O'||letter=='U';
	}
}
	
	
	
