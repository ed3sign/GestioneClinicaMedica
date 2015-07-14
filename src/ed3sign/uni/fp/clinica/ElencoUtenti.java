package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import ed3sign.uni.fp.utility.MyFile;

/**
 * Classe Elenco Utenti
 * Gestione della lista contenente tutti gli utenti/pazienti in archivio
 * Salvataggio su file
 * @author Seba
 *
 */
public class ElencoUtenti implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList<Utente> elencoUtenti;
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	
	/**
	 * Metodo Costruttore Elenco Utenti
	 */
	public ElencoUtenti(){
		elencoUtenti = new ArrayList<Utente>();
	}
	
	/**
	 * Salva File Utenti
	 */
	public void saveUsers(ElencoUtenti toSave){
		MyFile.saveObject(f_utenti, toSave, ClinicaMain.UTENTI_FILENAME);
	}

	/**
	 * Get Lista
	 * @return lista elenco degli utenti
	 */
	public ArrayList<Utente> getList() {
	       return elencoUtenti;
	}
}
