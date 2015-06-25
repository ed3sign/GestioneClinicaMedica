package ed3sign.uni.fp.clinica;

import java.util.Date;

public class Visita {
	private Medico medico;
	private Utente paziente;
	private Date data;
	private String stato;
	private String descrizione;
	
	public Visita(Medico medico, Utente paziente, Date data, String stato, String descrizione) {
		super();
		this.medico = medico;
		this.paziente = paziente;
		this.data = data;
		this.stato = stato;
		this.descrizione = descrizione;
	}
}
