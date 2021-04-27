package it.unibs.fp.codicefiscale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final int DIVISORE_CARATTERE_CONTROLLO=26;
	public static int[] rangeMinoreMesi;
	public static int[] rangeMaggioreMesi;
	public static ArrayList<String> codiceCitta;
	
	
	
	public static Map<Character, Integer> getMappaPari() {
		Map <Character, Integer> mappaPari = new HashMap <Character, Integer>( );
		
		mappaPari.put('0', 0);
		mappaPari.put('1', 1);
		mappaPari.put('2', 2);
		mappaPari.put('3', 3);
		mappaPari.put('4', 4);
		mappaPari.put('5', 5);
		mappaPari.put('6', 6);
		mappaPari.put('7', 7);
		mappaPari.put('8', 8);
		mappaPari.put('9', 9);
		mappaPari.put('A', 0);
		mappaPari.put('B', 1);
		mappaPari.put('C', 2);
		mappaPari.put('D', 3);
		mappaPari.put('E', 4);
		mappaPari.put('F', 5);
		mappaPari.put('G', 6);
		mappaPari.put('H', 7);
		mappaPari.put('I', 8);
		mappaPari.put('J', 9);
		mappaPari.put('K', 10);
		mappaPari.put('L', 11);
		mappaPari.put('M', 12);
		mappaPari.put('N', 13);
		mappaPari.put('O', 14);
		mappaPari.put('P', 15);
		mappaPari.put('Q', 16);
		mappaPari.put('R', 17);
		mappaPari.put('S', 18);
		mappaPari.put('T', 19);
		mappaPari.put('U', 20);
		mappaPari.put('V', 21);
		mappaPari.put('W', 22);
		mappaPari.put('X', 23);
		mappaPari.put('Y', 24);
		mappaPari.put('Z', 25);
		
		return mappaPari;
	}
	
	public static Map<Character, Integer> getMappaDispari() {
		Map <Character, Integer> mappaDispari = new HashMap <Character, Integer>( );
		
		mappaDispari.put('0', 1);
		mappaDispari.put('1', 0);
		mappaDispari.put('2', 5);
		mappaDispari.put('3', 7);
		mappaDispari.put('4', 9);
		mappaDispari.put('5', 13);
		mappaDispari.put('6', 15);
		mappaDispari.put('7', 17);
		mappaDispari.put('8', 19);
		mappaDispari.put('9', 21);
		mappaDispari.put('A', 1);
		mappaDispari.put('B', 0);
		mappaDispari.put('C', 5);
		mappaDispari.put('D', 7);
		mappaDispari.put('E', 9);
		mappaDispari.put('F', 13);
		mappaDispari.put('G', 15);
		mappaDispari.put('H', 17);
		mappaDispari.put('I', 19);
		mappaDispari.put('J', 21);
		mappaDispari.put('K', 2);
		mappaDispari.put('L', 4);
		mappaDispari.put('M', 18);
		mappaDispari.put('N', 20);
		mappaDispari.put('O', 11);
		mappaDispari.put('P', 3);
		mappaDispari.put('Q', 6);
		mappaDispari.put('R', 8);
		mappaDispari.put('S', 12);
		mappaDispari.put('T', 14);
		mappaDispari.put('U', 16);
		mappaDispari.put('V', 10);
		mappaDispari.put('W', 22);
		mappaDispari.put('X', 25);
		mappaDispari.put('Y', 24);
		mappaDispari.put('Z', 23);
		
		return mappaDispari;
	}
	
	
	public static String getCodiceComuneByNome(ArrayList<Comune> dc,String nome) {
		String r="non trovato";
		boolean trovato=false;
		int i=0;
		while(!trovato&&i<dc.size()) {
			if(dc.get(i).getNome().equals(nome)) {
				r=dc.get(i).getCodice();
				trovato=true;
			}else {
				i++;
			}
		}
		
		return r;
	}
	
	public static void controllaPresenze(ArrayList<Persona> dp, ArrayList<String> dcf) {
		for(int i=0; i<dp.size(); i++) {
			for(int j=0; j<dcf.size(); j++) {
				if(dp.get(i).getCodiceFiscale().equals(dcf.get(j))){
					dp.get(i).setCodiceAssente(true);
					dcf.remove(j);
					j--;
				}
			}
		}

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
		
		ArrayList<Boolean> b=new ArrayList<Boolean>();
		boolean r=true;
		
		char[] basi=new char[16];
		char[] ceils=new char[16];
		
		basi[0]='A';
		ceils[0]='Z';
		basi[1]='A';
		ceils[1]='Z';
		basi[2]='A';
		ceils[2]='Z';
		basi[3]='A';
		ceils[3]='Z';
		basi[4]='A';
		ceils[4]='Z';
		basi[5]='A';
		ceils[5]='Z';
		basi[6]='0';
		ceils[6]='9';
		basi[7]='0';
		ceils[7]='9';
		basi[8]='A';
		ceils[8]='T';
		basi[9]='0';
		ceils[9]='9';
		basi[10]='0';
		ceils[10]='9';
		basi[11]='A';
		ceils[11]='Z';
		basi[12]='0';
		ceils[12]='9';
		basi[13]='0';
		ceils[13]='9';
		basi[14]='0';
		ceils[14]='9';
		basi[15]='A';
		ceils[15]='Z';

		
		
		for(int i=0;i<16;i++) {
			boolean appoggio;
			appoggio = controlloRange(codice.charAt(0),basi[i],ceils[i]);
			r=r&&appoggio;
		}
		

		
		/*	
		codice.substring(0,6)   tutte lettere  //nome+cognome
		codice.substring(6,8)   tutti numeri
		codice.substring(8,9)   lettera nell'array
		//giorno
		codice.substring(9,11)     devono essere numeri,   in totale deve essere nel range del mese*/
		 
		/*codice.substring(11,12)    lettera  
		codice.substring(12,15)    numero

		
		codice.substring(15,16)    E E */
		
		/*int numeroGiorno=Integer.parseInt(codice.substring(9,11));
		if(numeroGiorno>rangeMaggioreMesi[codice.charAt(8)-'A']||numeroGiorno>rangeMinoreMesi[codice.charAt(8)-'A']) {
			r=false;
		}
			
			
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
	
	
	
