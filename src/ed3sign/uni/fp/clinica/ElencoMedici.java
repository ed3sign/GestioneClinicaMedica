package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import ed3sign.uni.fp.utility.MyFile;

public class ElencoMedici implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<Medico> elencoMedici;
	File f_medici= new File(ClinicaMain.MEDICI_FILENAME);
	
	/**
	 * Metodo Costruttore Elenco Utenti
	 */
	public ElencoMedici(){
		elencoMedici = new ArrayList<Medico>();
	}
	
	/**
	 * Salva File Medici
	 */
	public void saveMedici(ElencoMedici elencoMedici) {	
		MyFile.saveObject(f_medici, elencoMedici, ClinicaMain.MEDICI_FILENAME);
	}

	/**
	 * Get Lista
	 * @return lista elenco degli utenti
	 */
	public ArrayList<Medico> getList() {
	       return elencoMedici;
	}

	
}
