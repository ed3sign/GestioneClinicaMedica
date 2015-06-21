package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import ed3sign.uni.fp.utility.MyFile;

public class Utente implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean loggedin = false;
	private String nome;
	private String cognome;
	private Date dataN;
	private String luogoNascita;
	private String telefono;
	private String sesso;
	private String codFiscale;
	File f_users = new File(ClinicaMain.UTENTI_FILENAME);
	
	
	/**
	 * Metodo Costruttore Utente
	 * @param nome nome di battesimoi
	 * @param cognome cognome dell'utente
	 * @param dataN data di nascita
	 * @param luogoNascita luogo di nascina
	 * @param sesso sessso (M/F)
	 * @param telefono numero di telefono
	 * @param codFiscale codice fiscale
	 */
	public Utente(String nome, String cognome, Date dataN, String luogoNascita, String sesso, String telefono, String codFiscale) {
		this.nome=nome;
		this.cognome = cognome;
		this.dataN = dataN;
		this.luogoNascita = luogoNascita;
		this.sesso = sesso;
		this.telefono = telefono;
		this.codFiscale = codFiscale;
	}
	
	/**
	 * Aggiungi Nuovo Utente alla Lista
	 */
	public void aggiungiUtente(Utente newUtente) {
		ElencoUtenti utenti = null;
		
		// Existing File
		if(f_users.exists())
			utenti = (ElencoUtenti) MyFile.loadObject(f_users, ClinicaMain.UTENTI_FILENAME);
		// New File
		else
			utenti = new ElencoUtenti();
			
		utenti.elencoUtenti.add(newUtente);
		utenti.saveUsers(utenti);
	}

	/**
	 * Setters and Getters
	 * @return item
	 */
	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
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

	public Date getDataN() {
		return dataN;
	}

	public void setDataN(Date dataN) {
		this.dataN = dataN;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}
}
