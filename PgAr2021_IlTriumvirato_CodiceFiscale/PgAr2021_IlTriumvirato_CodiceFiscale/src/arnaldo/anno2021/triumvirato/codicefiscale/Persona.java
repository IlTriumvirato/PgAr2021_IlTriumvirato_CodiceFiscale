package arnaldo.anno2021.triumvirato.codicefiscale;

import java.time.LocalDate;

public class Persona {
	private final static char FILLER_CHAR='X';
	private final static int GENDER_NUMBER_TO_ADD=40;
	private final static char[] MONTH_CHARS= {'A','B','C','D','E','H','L','M','P','R','S','T'};
	private int id;
	private String nome;
	private String cognome;
	private char sesso;
	private String comune_nascita;
	private LocalDate data_nascita;
	private String codiceFiscale;
	
	public Persona(String nome, String cognome, char sesso, String comune_nascita, LocalDate data_nascita) {
		this.nome=nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comune_nascita = comune_nascita;
		this.data_nascita = data_nascita;
	}
	
	public Persona(int id) {
		this.id=id;
	}
	
	public void stampaNomeCognome() {
		System.out.println("nome="+nome+" cognome="+cognome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public char getSesso() {
		return sesso;
	}

	public void setSesso(char sesso) {
		this.sesso = sesso;
	}

	public String getComune_nascita() {
		return comune_nascita;
	}

	public void setComune_nascita(String comune_nascita) {
		this.comune_nascita = comune_nascita;
	}

	public LocalDate getData_nascita() {
		return data_nascita;
	}

	public void setData_nascita(LocalDate data_nascita) {
		this.data_nascita = data_nascita;
	}
	
	
	private static boolean isVowel(char letter) {
		return letter=='A'||letter=='E'||letter=='I'||letter=='O'||letter=='U';
	}
	
	private static String getVowellessString(String s) {
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<s.length();i++) {
			if(!isVowel(s.charAt(i))) {
				sb.append(s.charAt(i));
			}
		}
		return new String(sb);
	}
	
	
	private String getThreeLetterName() {
		StringBuffer tln=new StringBuffer("");
		
		String vowelLessName=getVowellessString(nome);
		
		if(vowelLessName.length()==3) {
			
			tln.append(vowelLessName);
			
		}else if(vowelLessName.length()>3) {
			
			tln.append(vowelLessName.charAt(0)+vowelLessName.charAt(2)+vowelLessName.charAt(3));
			
		}else{ //se il nome ha meno di tre consonanti, i casi sono due: se ci sono poi vocali per completarlo, mette prima le consonanti poi le vocali necessarie in ordine; se invece non ci sono neanche quelle, aggiunge delle X
			
			
				tln.append(vowelLessName);
				int i=0;
				int counter=vowelLessName.length();
				while(counter<3) {
					
					if(i<nome.length()){
						if(isVowel(nome.charAt(i))) {
							tln.append(nome.charAt(i));
							counter++;
						}
						
						i++;
					}else {
						tln.append(FILLER_CHAR);
						counter++;
					}		
					
				}
				
			
			
		}
		
		return new String(tln);
	}
		
	private String getThreeLetterSurname() {
		
		StringBuffer tls=new StringBuffer("");
		
		String vowelLessSurname=getVowellessString(cognome);
		
			
		if(vowelLessSurname.length()>=3){
			
			tls.append(vowelLessSurname.substring(0,3));
			
		}else{ //se il nome ha meno di tre consonanti, i casi sono due: se ci sono poi vocali per completarlo, mette prima le consonanti poi le vocali necessarie in ordine; se invece non ci sono neanche quelle, aggiunge delle X
			
			
			tls.append(vowelLessSurname);
			int i=0;
			int counter=vowelLessSurname.length();
			while(counter<3) {
				
				if(i<cognome.length()){
					if(isVowel(cognome.charAt(i))) {
						tls.append(cognome.charAt(i));
						counter++;
					}
					
					i++;
				}else {
					tls.append(FILLER_CHAR);
					counter++;
				}		
				
			}
			
		
		
	}
		
		return new String(tls);
	}
	
	public void generaCodiceFiscale() {
		this.codiceFiscale="";
		StringBuffer cf=new StringBuffer("");
		
		//genera nome
		cf.append(getThreeLetterName());
		
		//genera cognome
		cf.append(getThreeLetterSurname());

		//anno di nascita
		String stringaAnno=""+data_nascita.getYear();
		while(stringaAnno.length()<2) {  //caso estremo
			stringaAnno="0"+stringaAnno;
		}
		stringaAnno=stringaAnno.substring(stringaAnno.length()-2,stringaAnno.length());
		cf.append(stringaAnno);
		
		//mese di nascita
		cf.append(MONTH_CHARS[data_nascita.getMonthValue()-1]);
		
		//dato composito costituito dal giorno di nascita, al quale viene aggiunto 40 in caso di sesso femminile, in modo da utilizzare un carattere 
		int giornoNascita=data_nascita.getDayOfMonth();
		if(sesso=='F')giornoNascita+=GENDER_NUMBER_TO_ADD;
		cf.append(giornoNascita);
		
		cf.append(calcolaCarattereControllo(new String(cf)));
		
		this.codiceFiscale=new String(cf);
	}
	
	//abstract for now
	private char calcolaCarattereControllo(String cfIncompleto) {
		if(cfIncompleto.length()==15) {
			return 'A';
		}else {
			return ' ';//errore
		}
		
	}
}