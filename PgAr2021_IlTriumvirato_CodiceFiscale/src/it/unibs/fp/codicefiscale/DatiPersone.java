package it.unibs.fp.codicefiscale;

import java.util.ArrayList;

public class DatiPersone {
	ArrayList<Persona> arrayPersone;
	
	public DatiPersone() {
		
	}
	
	public DatiPersone(ArrayList<Persona> arrayPersone) {
		this.arrayPersone=arrayPersone;
	}

	public ArrayList<Persona> getArrayPersone() {
		return arrayPersone;
	}

	public void setArrayPersone(ArrayList<Persona> arrayPersone) {
		this.arrayPersone = arrayPersone;
	}
	
	
}
