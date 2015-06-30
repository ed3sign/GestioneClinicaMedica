package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.Serializable;

import ed3sign.uni.fp.utility.MyFile;

public class Referto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Visita visita;
	private String rapporto;
	private String prescrizione;
	private File f_referti = new File(ClinicaMain.REFERTI_FILENAME);
	
	
	/**
	 * Metodo Costruttore Referto
	 * @param visita Visita relativa al referto
	 * @param rapporto Rapporto del medico
	 * @param prescrizione Prescrizione del medico
	 */
	public Referto(Visita visita, String rapporto, String prescrizione) {
		super();
		this.visita = visita;
		this.rapporto = rapporto;
		this.prescrizione = prescrizione;
	}
	
	/**
	 * Aggiungi Referto
	 * @param newReferto
	 */
	public void aggiungiReferto(Referto newReferto) {
		ElencoReferti elencoReferti = null;
		
		// Existing File
		if(f_referti .exists())
			elencoReferti = (ElencoReferti) MyFile.loadObject(f_referti, ClinicaMain.REFERTI_FILENAME);
		// New File
		else
			elencoReferti = new ElencoReferti();
		
		elencoReferti.elencoReferti.add(newReferto);
		elencoReferti.saveReferti(elencoReferti);
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public Visita getVisita() {
		return visita;
	}
	public void setVisita(Visita visita) {
		this.visita = visita;
	}
	public String getRapporto() {
		return rapporto;
	}
	public void setRapporto(String rapporto) {
		this.rapporto = rapporto;
	}
	public String getPrescrizione() {
		return prescrizione;
	}
	public void setPrescrizione(String prescrizione) {
		this.prescrizione = prescrizione;
	}
	
	
	

}
