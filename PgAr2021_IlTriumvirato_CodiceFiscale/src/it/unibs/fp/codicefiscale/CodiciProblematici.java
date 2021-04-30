package it.unibs.fp.codicefiscale;

import java.util.ArrayList;

public class CodiciProblematici {
	ArrayList<String> codiciInvalidi;
	ArrayList<String> codiciSpaiati;
	
	CodiciProblematici(){
		codiciInvalidi=new ArrayList<String>();
		codiciSpaiati=new ArrayList<String>();
	}
	

	public CodiciProblematici(ArrayList<String> codiciInvalidi, ArrayList<String> codiciSpaiati) {
		this.codiciInvalidi = codiciInvalidi;
		this.codiciSpaiati = codiciSpaiati;
	}

	public ArrayList<String> getCodiciInvalidi() {
		return codiciInvalidi;
	}

	public ArrayList<String> getCodiciSpaiati() {
		return codiciSpaiati;
	}

	public void setCodiciInvalidi(ArrayList<String> codiciInvalidi) {
		this.codiciInvalidi=codiciInvalidi;
	}
	
	public void setCodiciSpaiati(ArrayList<String> codiciSpaiati) {
		this.codiciSpaiati=codiciSpaiati;
	}
}
