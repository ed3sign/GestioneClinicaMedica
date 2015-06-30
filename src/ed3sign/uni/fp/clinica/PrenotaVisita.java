package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

public class PrenotaVisita extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	protected JTextArea ta_motivo;
	protected static PrenotaVisita frame;
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new PrenotaVisita(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param giorno_visita 
	 * @param m 
	 */
	public PrenotaVisita(Medico m, Date data_visita) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 660, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 102, 252, 178, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPrenotaVisita = new JLabel("Prenota Visita");
		lblPrenotaVisita.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPrenotaVisita = new GridBagConstraints();
		gbc_lblPrenotaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblPrenotaVisita.gridwidth = 2;
		gbc_lblPrenotaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenotaVisita.gridx = 1;
		gbc_lblPrenotaVisita.gridy = 1;
		contentPane.add(lblPrenotaVisita, gbc_lblPrenotaVisita);
		
		JLabel lblMedico = new JLabel("Medico");
		GridBagConstraints gbc_lblMedico = new GridBagConstraints();
		gbc_lblMedico.anchor = GridBagConstraints.WEST;
		gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedico.gridx = 1;
		gbc_lblMedico.gridy = 3;
		contentPane.add(lblMedico, gbc_lblMedico);
		
		JLabel lbl_nomemedico = new JLabel("[nome_medico]");
		GridBagConstraints gbc_lbl_nomemedico = new GridBagConstraints();
		gbc_lbl_nomemedico.anchor = GridBagConstraints.WEST;
		gbc_lbl_nomemedico.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_nomemedico.gridx = 2;
		gbc_lbl_nomemedico.gridy = 3;
		contentPane.add(lbl_nomemedico, gbc_lbl_nomemedico);
		
		JLabel lblPaziente = new JLabel("Paziente");
		GridBagConstraints gbc_lblPaziente = new GridBagConstraints();
		gbc_lblPaziente.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaziente.anchor = GridBagConstraints.WEST;
		gbc_lblPaziente.gridx = 1;
		gbc_lblPaziente.gridy = 4;
		contentPane.add(lblPaziente, gbc_lblPaziente);
		
		JComboBox<String> cb_pazienti = new JComboBox<String>();
		GridBagConstraints gbc_cb_pazienti = new GridBagConstraints();
		gbc_cb_pazienti.insets = new Insets(0, 0, 5, 5);
		gbc_cb_pazienti.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_pazienti.gridx = 2;
		gbc_cb_pazienti.gridy = 4;
		contentPane.add(cb_pazienti, gbc_cb_pazienti);
		
		// Caricamento Pazienti (Utenti)
		if(f_utenti.exists()){
			ElencoUtenti utenti = null;
			utenti = (ElencoUtenti) MyFile.loadObject(f_utenti, ClinicaMain.UTENTI_FILENAME);
			
			for(Utente u : utenti.elencoUtenti)
				cb_pazienti.addItem(u.getNome() + " " +u.getCognome() + " - " + u.getCodFiscale());
		}
		
		JLabel lblData = new JLabel("Data");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.WEST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 1;
		gbc_lblData.gridy = 5;
		contentPane.add(lblData, gbc_lblData);
		
		JLabel lbl_giorno_visita = new JLabel("[giorno_visita]");
		GridBagConstraints gbc_lbl_giorno_visita = new GridBagConstraints();
		gbc_lbl_giorno_visita.anchor = GridBagConstraints.WEST;
		gbc_lbl_giorno_visita.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_giorno_visita.gridx = 2;
		gbc_lbl_giorno_visita.gridy = 5;
		contentPane.add(lbl_giorno_visita, gbc_lbl_giorno_visita);
		
		JLabel lbl_Orario = new JLabel("Orario");
		GridBagConstraints gbc_lblOrario = new GridBagConstraints();
		gbc_lblOrario.anchor = GridBagConstraints.WEST;
		gbc_lblOrario.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrario.gridx = 1;
		gbc_lblOrario.gridy = 6;
		contentPane.add(lbl_Orario, gbc_lblOrario);
		
		JLabel lbl_orario = new JLabel("[orario_visita]");
		GridBagConstraints gbc_lbl_orario = new GridBagConstraints();
		gbc_lbl_orario.anchor = GridBagConstraints.WEST;
		gbc_lbl_orario.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_orario.gridx = 2;
		gbc_lbl_orario.gridy = 6;
		contentPane.add(lbl_orario, gbc_lbl_orario);
		
		JLabel lblTipo = new JLabel("Tipo");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.WEST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 1;
		gbc_lblTipo.gridy = 7;
		contentPane.add(lblTipo, gbc_lblTipo);
		
		JComboBox<String> cb_tipo = new JComboBox<String>();
		if(m.getTipologia().equals(ClinicaMain.SPECIALISTA))
			cb_tipo.addItem(ClinicaMain.SPECIALISTICA);
		cb_tipo.addItem(ClinicaMain.GENERICA);
		GridBagConstraints gbc_cb_tipo = new GridBagConstraints();
		gbc_cb_tipo.insets = new Insets(0, 0, 5, 5);
		gbc_cb_tipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_tipo.gridx = 2;
		gbc_cb_tipo.gridy = 7;
		contentPane.add(cb_tipo, gbc_cb_tipo);
		
		JLabel lblMotivoDellaVisita = new JLabel("Motivo della visita (note)");
		GridBagConstraints gbc_lblMotivoDellaVisita = new GridBagConstraints();
		gbc_lblMotivoDellaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblMotivoDellaVisita.gridwidth = 2;
		gbc_lblMotivoDellaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivoDellaVisita.gridx = 1;
		gbc_lblMotivoDellaVisita.gridy = 9;
		contentPane.add(lblMotivoDellaVisita, gbc_lblMotivoDellaVisita);
		
		ta_motivo = new JTextArea();
		GridBagConstraints gbc_ta_motivo = new GridBagConstraints();
		gbc_ta_motivo.gridwidth = 2;
		gbc_ta_motivo.insets = new Insets(0, 0, 5, 5);
		gbc_ta_motivo.fill = GridBagConstraints.BOTH;
		gbc_ta_motivo.gridx = 1;
		gbc_ta_motivo.gridy = 10;
		contentPane.add(ta_motivo, gbc_ta_motivo);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tipo = cb_tipo.getSelectedItem().toString();
				String motivo = ta_motivo.getText();
				Utente u = getPaziente(cb_pazienti);
				Visita newVisita = new Visita(m, u, data_visita, ClinicaMain.PRENOTATA, tipo, motivo);
				if(!newVisita.aggiungiVisita(newVisita))
					JOptionPane.showMessageDialog(contentPane, "Visita già prenotata per l'orario selezionato!", "Attenzione!", JOptionPane.WARNING_MESSAGE);
				else{
					JOptionPane.showMessageDialog(contentPane, "Visita prenotata per l'orario selezionato!", "Operazione Effettuata", JOptionPane.INFORMATION_MESSAGE);
					((Window) contentPane.getParent()).dispose();
				}
			}
		});
		GridBagConstraints gbc_btnConferma = new GridBagConstraints();
		gbc_btnConferma.anchor = GridBagConstraints.SOUTH;
		gbc_btnConferma.insets = new Insets(0, 0, 5, 0);
		gbc_btnConferma.gridx = 3;
		gbc_btnConferma.gridy = 10;
		contentPane.add(btnConferma, gbc_btnConferma);
		
		// Set Frame Data
		if(m!=null && data_visita!=null){
			lbl_nomemedico.setText(m.getNome()+ " " + m.getCognome() + " ("+m.getTipologia()+")");
			lbl_giorno_visita.setText(MyUtil.timeDateFormat(data_visita));
			lbl_orario.setText(MyUtil.timeIntervalFormat(data_visita));
		}
		else{
			JOptionPane.showMessageDialog(contentPane, "Dati della prenotazione non disponibili!", "Attenzione!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
			
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
