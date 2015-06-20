package ed3sign.uni.fp.clinica;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import ed3sign.uni.fp.utility.MyFile;

public class Medico extends Utente{
	private static final long serialVersionUID = 1L;
	private String user;
	private String pass;
	private String albo;
	private String tipologia;
	private ArrayList<String> competenze;
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	
	/**
	 * Medico Costruttore
	 * @param user nome utente
	 * @param pass password
	 * @param nome nome di battesimo
	 * @param cognome cognome
	 * @param dataN data di nascita
	 * @param luogoNascita luogo di nascita
	 * @param sesso sesso (M/F)
	 * @param telefono numero di telefono
	 * @param codFiscale codice fiscale
	 * @param albo numero di albo
	 * @param tipologia tipo di medico
	 * @param competenze area di competenza
	 */
	public Medico(String user, String pass, String nome, String cognome,
			Date dataN, String luogoNascita, String sesso, String telefono,
			String codFiscale, String albo, String tipologia,
			ArrayList<String> competenze) {
		super(nome, cognome, dataN, luogoNascita, sesso, telefono,
				codFiscale);
		this.user = user;
		this.pass = pass;
		this.albo = albo;
		this.tipologia = tipologia;
		this.competenze = competenze;
	}
	
	public void aggiungiMedico(Medico newMedico) {
		ElencoMedici elencoMedici = null;
		
		// Existing File
		if(f_users.exists())
			elencoMedici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
		// New File
		else
			elencoMedici = new ElencoMedici();
			
		elencoMedici.elencoMedici.add(newMedico);
		elencoMedici.saveMedici(elencoMedici);
	}

	/**
	 * Setters and Getters
	 */
	public String getAlbo() {
		return albo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public File getF_medici() {
		return f_medici;
	}

	public void setF_medici(File f_medici) {
		this.f_medici = f_medici;
	}

	public void setAlbo(String albo) {
		this.albo = albo;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public ArrayList<String> getCompetenze() {
		return competenze;
	}

	public void setCompetenze(ArrayList<String> area) {
		this.competenze = area;
	}
	
}
