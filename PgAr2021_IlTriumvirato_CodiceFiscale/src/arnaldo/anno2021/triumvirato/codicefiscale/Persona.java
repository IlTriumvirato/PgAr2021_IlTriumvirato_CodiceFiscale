package arnaldo.anno2021.triumvirato.codicefiscale;

import java.time.LocalDate;

public class Persona {
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
	
	
}
