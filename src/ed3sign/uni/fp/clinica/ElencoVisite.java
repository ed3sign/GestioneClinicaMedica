package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import ed3sign.uni.fp.utility.MyFile;

/**
 * Classe Elenco Visite
 * Gestione della lista contenente tutte le liste in archivio
 * Salvataggio su file
 * @author Seba
 *
 */
public class ElencoVisite implements Serializable{
		private static final long serialVersionUID = 1L;
		ArrayList<Visita> elencoVisite;
		File f_visite = new File(ClinicaMain.VISITE_FILENAME);
		
		/**
		 * Metodo Costruttore Elenco Visite
		 */
		public ElencoVisite(){
			elencoVisite = new ArrayList<Visita>();
		}
		
		/**
		 * Salva File Visite
		 */
		public void saveVisite(ElencoVisite elencoVisite) {	
			MyFile.saveObject(f_visite, elencoVisite, ClinicaMain.VISITE_FILENAME);
		}

		/**
		 * Get Lista
		 * @return lista elenco delle visite
		 */
		public ArrayList<Visita> getList() {
		       return elencoVisite;
		}
		
}

