package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import ed3sign.uni.fp.utility.MyFile;

public class ElencoReferti implements Serializable{
		private static final long serialVersionUID = 1L;
		ArrayList<Referto> elencoReferti;
		File f_referti = new File(ClinicaMain.REFERTI_FILENAME);
		
		/**
		 * Metodo Costruttore Elenco Visite
		 */
		public ElencoReferti(){
			elencoReferti = new ArrayList<Referto>();
		}
		
		/**
		 * Salva File Visite
		 */
		public void saveReferti(ElencoReferti elencoReferti) {	
			MyFile.saveObject(f_referti, elencoReferti, ClinicaMain.REFERTI_FILENAME);
		}

		/**
		 * Get Lista
		 * @return lista elenco dei referti
		 */
		public ArrayList<Referto> getList() {
		       return elencoReferti;
		}
		
}

