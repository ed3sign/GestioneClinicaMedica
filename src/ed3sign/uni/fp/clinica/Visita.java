package ed3sign.uni.fp.clinica;

import java.io.File;
import java.util.Date;

import ed3sign.uni.fp.utility.MyFile;

public class Visita {
	private Medico medico;
	private Utente paziente;
	private Date data;
	private String stato;
	private String descrizione;
	private File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	
	/**
	 * Metodo Costruttore Visita
	 * @param medico Medico curante
	 * @param paziente Paziente da visitare
	 * @param data Data e Ora della visita
	 * @param stato Stato della visita (Prenotazione, Referto in Attesa, Archiviata) 
	 * @param descrizione Motivo della visita
	 */
	public Visita(Medico medico, Utente paziente, Date data, String stato, String descrizione) {
		super();
		this.medico = medico;
		this.paziente = paziente;
		this.data = data;
		this.stato = stato;
		this.descrizione = descrizione;
	}
	
	/**
	 * Aggiungi Visita
	 * @param newMedico
	 */
	public void aggiungiVisita(Visita newVisita) {
		ElencoVisite elencoVisite = null;
		
		// Existing File
		if(f_visite .exists())
			elencoVisite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
		// New File
		else
			elencoVisite = new ElencoVisite();
			
		elencoVisite.elencoVisite.add(newVisita);
		elencoVisite.saveVisite(elencoVisite);
	}

	
	/**
	 * Getters and Setters
	 * @return
	 */
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Utente getPaziente() {
		return paziente;
	}

	public void setPaziente(Utente paziente) {
		this.paziente = paziente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public File getF_visite() {
		return f_visite;
	}

	public void setF_visite(File f_visite) {
		this.f_visite = f_visite;
	}
	
	
}
