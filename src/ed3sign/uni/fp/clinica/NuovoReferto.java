package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ed3sign.uni.fp.utility.MyFile;

/**
 * Classe NuovoReferto
 * Classe per la creazione di un nuovo referto.
 * Form accessibile solo dall'elenco visite, dove
 * è possibile selezionare una visita conclusa e
 * decidere di aggiungere il relativo referto con
 * un'eventuale prescrizione per il paziente.
 * @author ed3sign
 *
 */
public class NuovoReferto extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextArea ta_prescrizione;
	protected static NuovoReferto frame;
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new NuovoReferto(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param giorno_visita giorno della visita (dal frame ElencoVisite)
	 * @param m medico responsabile della visita, quindi del referto
	 */
	public NuovoReferto(Visita v) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 102, 252, 178, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 10, 0, 15, 0, 50, 0, 0, 20, 0, 0, 0, 0, 0, 20, 0, 20, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPrenotaVisita = new JLabel("Nuovo Referto Medico");
		lblPrenotaVisita.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPrenotaVisita = new GridBagConstraints();
		gbc_lblPrenotaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblPrenotaVisita.gridwidth = 2;
		gbc_lblPrenotaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenotaVisita.gridx = 1;
		gbc_lblPrenotaVisita.gridy = 1;
		contentPane.add(lblPrenotaVisita, gbc_lblPrenotaVisita);
		
		JLabel lblMedico = new JLabel("Visita");
		GridBagConstraints gbc_lblMedico = new GridBagConstraints();
		gbc_lblMedico.anchor = GridBagConstraints.WEST;
		gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedico.gridx = 1;
		gbc_lblMedico.gridy = 3;
		contentPane.add(lblMedico, gbc_lblMedico);
		
		JLabel lbl_infovisita = new JLabel("[info_visita]");
		GridBagConstraints gbc_lbl_infovisita = new GridBagConstraints();
		gbc_lbl_infovisita.anchor = GridBagConstraints.WEST;
		gbc_lbl_infovisita.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_infovisita.gridx = 2;
		gbc_lbl_infovisita.gridy = 3;
		contentPane.add(lbl_infovisita, gbc_lbl_infovisita);
		
		// Set Frame Data
		if(v!=null){
			lbl_infovisita.setText(v.getData().toString());
		}
		else{
			JOptionPane.showMessageDialog(contentPane, "Dati del referto non disponibili!", "Attenzione!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		
		
		JLabel lblData = new JLabel("Rapporto");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.WEST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 1;
		gbc_lblData.gridy = 5;
		contentPane.add(lblData, gbc_lblData);
		
		JTextArea ta_rapporto = new JTextArea();
		GridBagConstraints gbc_ta_rapporto = new GridBagConstraints();
		gbc_ta_rapporto.gridheight = 3;
		gbc_ta_rapporto.gridwidth = 3;
		gbc_ta_rapporto.insets = new Insets(0, 0, 5, 5);
		gbc_ta_rapporto.fill = GridBagConstraints.BOTH;
		gbc_ta_rapporto.gridx = 1;
		gbc_ta_rapporto.gridy = 6;
		contentPane.add(ta_rapporto, gbc_ta_rapporto);
		
		JLabel lblMotivoDellaVisita = new JLabel("Prescrizione");
		GridBagConstraints gbc_lblMotivoDellaVisita = new GridBagConstraints();
		gbc_lblMotivoDellaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblMotivoDellaVisita.gridwidth = 2;
		gbc_lblMotivoDellaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivoDellaVisita.gridx = 1;
		gbc_lblMotivoDellaVisita.gridy = 10;
		contentPane.add(lblMotivoDellaVisita, gbc_lblMotivoDellaVisita);
		
		ta_prescrizione = new JTextArea();
		GridBagConstraints gbc_ta_prescrizione = new GridBagConstraints();
		gbc_ta_prescrizione.gridheight = 4;
		gbc_ta_prescrizione.gridwidth = 3;
		gbc_ta_prescrizione.insets = new Insets(0, 0, 5, 5);
		gbc_ta_prescrizione.fill = GridBagConstraints.BOTH;
		gbc_ta_prescrizione.gridx = 1;
		gbc_ta_prescrizione.gridy = 11;
		contentPane.add(ta_prescrizione, gbc_ta_prescrizione);
		
		/**
		 * Aggiungi Referto (evento click sul tasto conferma)
		 */
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int choice = 0;
				Referto newReferto;
				
				// Caricamento Visite
				ElencoVisite visite = null;
				if(f_visite.exists()){
					visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
				}
				
				if(ta_rapporto.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Compilare il rapporto prima di continuare.", "Errore", JOptionPane.WARNING_MESSAGE);
				else if(ta_prescrizione.getText().equals("")){
					choice = JOptionPane.showConfirmDialog(getParent(), "Nessuna prescrizione inserita. Sicuro di voler continuare?");
					if(choice == JOptionPane.YES_OPTION){
						newReferto = new Referto(v, ta_rapporto.getText(), ta_prescrizione.getText());
						newReferto.aggiungiReferto(newReferto);
						
						// Aggiornamento Stato Visita
						for(Visita va : visite.elencoVisite){
							if(va.getData().equals(v.getData())){
								va.setStato(ClinicaMain.ARCHIVIATA);
							}
						}
						MyFile.saveObject(f_visite, visite, ClinicaMain.VISITE_FILENAME);
						
						JOptionPane.showMessageDialog(contentPane, "Referto aggiunto con successo. Visita Archiviata", "Operazione Completata", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else{
					newReferto = new Referto(v, ta_rapporto.getText(), ta_prescrizione.getText());
					newReferto.aggiungiReferto(newReferto);
					
					// Aggiornamento Stato Visita
					for(Visita va : visite.elencoVisite){
						if(va.getData().equals(v.getData())){
							va.setStato(ClinicaMain.ARCHIVIATA);
						}
					}
					MyFile.saveObject(f_visite, visite, ClinicaMain.VISITE_FILENAME);
					JOptionPane.showMessageDialog(contentPane, "Referto aggiunto con successo. Visita Archiviata", "Operazione Completata", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnConferma = new GridBagConstraints();
		gbc_btnConferma.anchor = GridBagConstraints.EAST;
		gbc_btnConferma.insets = new Insets(0, 0, 5, 5);
		gbc_btnConferma.gridx = 3;
		gbc_btnConferma.gridy = 16;
		contentPane.add(btnConferma, gbc_btnConferma);
			
	}
	
	/**
	 * Get Utente From Combobox
	 * @param cb_medico JComboBox con elenco degli utenti (pazienti)
	 * @return Utente utente selezionato
	 */
	public Utente getPaziente(JComboBox<String> cb_pazienti){
		String user = (String) cb_pazienti.getSelectedItem();
		String codFisc = user.substring(user.lastIndexOf(" ")+1);
		ElencoUtenti utenti = null;
		utenti = (ElencoUtenti) MyFile.loadObject(f_utenti, ClinicaMain.UTENTI_FILENAME);
		
		for(Utente u : utenti.elencoUtenti)
			if(u.getCodFiscale().equals(codFisc))
				return u;
		return null;
	}

}
