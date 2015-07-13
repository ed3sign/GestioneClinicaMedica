package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import ed3sign.uni.fp.utility.CodiceFiscale;
import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

/**
 * Classe NuovoUtente
 * Classe per l'aggiunta di un nuovo utente normale, o paziente.
 * I dati richiesti sono esclusivamente di tipo anagrafico, dato
 * che l'utente generico non possiede le credenziali per accedere
 * alle sezioni riservate al medico.
 * Per garantire l'univocità degli utenti in archivio, viene effettuato
 * un controllo sul codice fiscale.
 * @author ed3sign
 *
 */
public class NuovoUtente extends JFrame{

	private static final long serialVersionUID = 1L;
	protected static final String CodiceFiscale = null;
	protected JPanel contentPane;
	protected JTextField tf_nome;
	protected JLabel lblCognome;
	protected JTextField tf_cognome;
	protected JLabel lblDatan;
	protected JLabel lblLuogoDiNascita;
	protected JTextField tf_luogo_nascita;
	protected JLabel lblTelefono;
	protected JDateChooser dateChooser;
	protected JFormattedTextField tf_telefono;
	protected JLabel lblCodiceFiscale;
	protected JLabel lblSesso;
	protected JFormattedTextField tf_codiceFiscale;
	protected JComboBox<String> cb_sesso;
	protected JButton button;
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	protected JLabel lbl_title;
	protected JSeparator separator;
	private JButton btnCalcola;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuovoUtente frame = new NuovoUtente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public NuovoUtente() throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 510);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{36, 125, 359, 160, 34, 0};
		gbl_contentPane.rowHeights = new int[]{45, 0, 0, 0, 0, 28, 28, 28, 28, 28, 27, 28, 0, 0, 60, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lbl_title = new JLabel("Nuovo Utente");
		lbl_title.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lbl_title = new GridBagConstraints();
		gbc_lbl_title.anchor = GridBagConstraints.WEST;
		gbc_lbl_title.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_title.gridx = 1;
		gbc_lbl_title.gridy = 1;
		contentPane.add(lbl_title, gbc_lbl_title);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		contentPane.add(separator, gbc_separator);
		
		// Nome
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 1;
		gbc_lblNome.gridy = 3;
		contentPane.add(lblNome, gbc_lblNome);
		
		tf_nome = new JTextField();
		GridBagConstraints gbc_tf_nome = new GridBagConstraints();
		gbc_tf_nome.gridwidth = 2;
		gbc_tf_nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_nome.insets = new Insets(0, 0, 5, 5);
		gbc_tf_nome.gridx = 2;
		gbc_tf_nome.gridy = 3;
		contentPane.add(tf_nome, gbc_tf_nome);
		tf_nome.setColumns(20);
		tf_nome.setText("Sebastiano");
		
		// Cognome
		lblCognome = new JLabel("Cognome");
		GridBagConstraints gbc_lblCognome = new GridBagConstraints();
		gbc_lblCognome.anchor = GridBagConstraints.WEST;
		gbc_lblCognome.insets = new Insets(0, 0, 5, 5);
		gbc_lblCognome.gridx = 1;
		gbc_lblCognome.gridy = 4;
		contentPane.add(lblCognome, gbc_lblCognome);
		
		tf_cognome = new JTextField();
		tf_cognome.setColumns(20);
		GridBagConstraints gbc_tf_cognome = new GridBagConstraints();
		gbc_tf_cognome.gridwidth = 2;
		gbc_tf_cognome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cognome.insets = new Insets(0, 0, 5, 5);
		gbc_tf_cognome.gridx = 2;
		gbc_tf_cognome.gridy = 4;
		contentPane.add(tf_cognome, gbc_tf_cognome);
		tf_cognome.setText("Gaggiano");
		
		// Data di Nascita
		lblDatan = new JLabel("Data di Nascita");
		GridBagConstraints gbc_lblDatan = new GridBagConstraints();
		gbc_lblDatan.anchor = GridBagConstraints.WEST;
		gbc_lblDatan.insets = new Insets(0, 0, 5, 5);
		gbc_lblDatan.gridx = 1;
		gbc_lblDatan.gridy = 5;
		contentPane.add(lblDatan, gbc_lblDatan);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.anchor = GridBagConstraints.NORTH;
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 5;
		contentPane.add(dateChooser, gbc_dateChooser);
		
		// Luogo di Nascita
		lblLuogoDiNascita = new JLabel("Luogo di Nascita");
		GridBagConstraints gbc_lblLuogoDiNascita = new GridBagConstraints();
		gbc_lblLuogoDiNascita.anchor = GridBagConstraints.WEST;
		gbc_lblLuogoDiNascita.insets = new Insets(0, 0, 5, 5);
		gbc_lblLuogoDiNascita.gridx = 1;
		gbc_lblLuogoDiNascita.gridy = 6;
		contentPane.add(lblLuogoDiNascita, gbc_lblLuogoDiNascita);
		
		tf_luogo_nascita = new JTextField();
		tf_luogo_nascita.setColumns(10);
		GridBagConstraints gbc_tf_luogo_nascita = new GridBagConstraints();
		gbc_tf_luogo_nascita.gridwidth = 2;
		gbc_tf_luogo_nascita.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_luogo_nascita.insets = new Insets(0, 0, 5, 5);
		gbc_tf_luogo_nascita.gridx = 2;
		gbc_tf_luogo_nascita.gridy = 6;
		contentPane.add(tf_luogo_nascita, gbc_tf_luogo_nascita);
		tf_luogo_nascita.setText("SAN GIOVANNI ROTONDO");
		
		// Telefono
		lblTelefono = new JLabel("Telefono");
		GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
		gbc_lblTelefono.anchor = GridBagConstraints.WEST;
		gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefono.gridx = 1;
		gbc_lblTelefono.gridy = 7;
		contentPane.add(lblTelefono, gbc_lblTelefono);
		
		tf_telefono = new JFormattedTextField();
		tf_telefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)
					e.consume();
			}
		});
		GridBagConstraints gbc_tf_telefono = new GridBagConstraints();
		gbc_tf_telefono.gridwidth = 2;
		gbc_tf_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_telefono.insets = new Insets(0, 0, 5, 5);
		gbc_tf_telefono.gridx = 2;
		gbc_tf_telefono.gridy = 7;
		contentPane.add(tf_telefono, gbc_tf_telefono);
		tf_telefono.setText("3334565789");
		
		
		lblCodiceFiscale = new JLabel("Cod. Fiscale");
		GridBagConstraints gbc_lblCodiceFiscale = new GridBagConstraints();
		gbc_lblCodiceFiscale.anchor = GridBagConstraints.WEST;
		gbc_lblCodiceFiscale.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodiceFiscale.gridx = 1;
		gbc_lblCodiceFiscale.gridy = 8;
		contentPane.add(lblCodiceFiscale, gbc_lblCodiceFiscale);
		
		tf_codiceFiscale = new JFormattedTextField();
		GridBagConstraints gbc_tf_codiceFiscale = new GridBagConstraints();
		gbc_tf_codiceFiscale.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_codiceFiscale.insets = new Insets(0, 0, 5, 5);
		gbc_tf_codiceFiscale.gridx = 2;
		gbc_tf_codiceFiscale.gridy = 8;
		contentPane.add(tf_codiceFiscale, gbc_tf_codiceFiscale);
		tf_codiceFiscale.setText("GGGSST95D26H916Y");
		
		/**
		 * Calcolo Automatico del CF (evento sul pulsante Calcola)
		 * Se l'utente ha già inserito i dati sufficienti, quali Nome, Cognome, Data di Nascita, Sesso e Comune di Nascita, 
		 * è possibile calcolare automaticamente il codice fiscale, tramite una libreria nel package di utilità (trovata sul web ed integrata nell'applicativo).
		 */
		btnCalcola = new JButton("Calcola");
		btnCalcola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_nome.getText().equals("") || tf_cognome.getText().equals("") || dateChooser.getDate() == null || tf_luogo_nascita.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Attenzione! Dati insufficienti per il calcolo del CF!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
				else{
					String nome=tf_nome.getText();
					String cognome=tf_cognome.getText();
					
					Date date = (dateChooser.getDate());
					Calendar cal = new GregorianCalendar();
					cal.setTime(date);

					String month = String.valueOf(cal.get(Calendar.MONTH) + 1);
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int year = cal.get(Calendar.YEAR);
					String luogo = tf_luogo_nascita.getText();
					String sesso = cb_sesso.getSelectedItem().toString();

					CodiceFiscale codFisc = new CodiceFiscale(nome, cognome, luogo, year, month, day, sesso);
					tf_codiceFiscale.setText(codFisc.getCodiceFiscale());
				}
			}
		});
		GridBagConstraints gbc_btnCalcola = new GridBagConstraints();
		gbc_btnCalcola.anchor = GridBagConstraints.EAST;
		gbc_btnCalcola.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalcola.gridx = 3;
		gbc_btnCalcola.gridy = 8;
		contentPane.add(btnCalcola, gbc_btnCalcola);
		
		// Codice Fiscale
		lblSesso = new JLabel("Sesso");
		GridBagConstraints gbc_lblSesso = new GridBagConstraints();
		gbc_lblSesso.anchor = GridBagConstraints.WEST;
		gbc_lblSesso.insets = new Insets(0, 0, 5, 5);
		gbc_lblSesso.gridx = 1;
		gbc_lblSesso.gridy = 9;
		contentPane.add(lblSesso, gbc_lblSesso);
		
		cb_sesso = new JComboBox<String>();
		cb_sesso.addItem("Maschio");
		cb_sesso.addItem("Femmina");
		GridBagConstraints gbc_cb_sesso = new GridBagConstraints();
		gbc_cb_sesso.gridwidth = 2;
		gbc_cb_sesso.insets = new Insets(0, 0, 5, 5);
		gbc_cb_sesso.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_sesso.gridx = 2;
		gbc_cb_sesso.gridy = 9;
		contentPane.add(cb_sesso, gbc_cb_sesso);
		
		
		/**
		 * Aggiungi Nuovo Utente (evento click sul pulsante Aggiungi)
		 * Prima di procedere all'inserimento di un utente, vengono effettuati una serie di controlli:
		 * - Inserimento di tutti i dati richiesti
		 * - Controllo sul formato del Codice Fiscale
		 * - Controllo duplicazione utente sulla base del CF
		 */
		button = new JButton("Aggiungi");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica Inserimento Dati
				if(tf_nome.getText().equals("") || tf_cognome.getText().equals("") || dateChooser.getDate() == null || tf_luogo_nascita.getText().equals("") 
						|| tf_telefono.getText().equals("") || tf_codiceFiscale.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Attenzione! Compilare tutti i dati richiesti!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
				else{
					String nome=tf_nome.getText();
					String cognome=tf_cognome.getText();
					Date date = dateChooser.getDate();
					String luogo = tf_luogo_nascita.getText();
					String sesso = cb_sesso.getSelectedItem().toString();
					String tel = tf_telefono.getText();
					String cod = tf_codiceFiscale.getText();
					boolean existing = false;
						
					// Validazione Codice Fiscale
					if(!MyUtil.checkCodFisc(cod))
						JOptionPane.showMessageDialog(contentPane, "Formato del Codice Fiscale non valido!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
					
					// Controllo Duplicazione Utente
					else{
						if(f_utenti.exists()){
							ElencoUtenti utenti = null;
							utenti = (ElencoUtenti) MyFile.loadObject(f_utenti, ClinicaMain.UTENTI_FILENAME);
							
							for(Utente u : utenti.elencoUtenti)
								if(u.getCodFiscale().equals(cod)){
									existing = true;
									JOptionPane.showMessageDialog(contentPane, "Utente già presente nei nostri archivi!", "Errore", JOptionPane.WARNING_MESSAGE);	
								}
							}
						}
						
						// Inserimento Utente
						if(!existing){
							Utente newUtente = new Utente(nome, cognome, date, luogo, sesso, tel, cod);
							newUtente.aggiungiUtente(newUtente);
							JOptionPane.showMessageDialog(contentPane, "Utente Inserito!", "Operazione Riuscita", JOptionPane.DEFAULT_OPTION);
							
							// FIELD RESET
							InterfaceHelpers.cls(contentPane);
							dateChooser.setDate(null);
						}
					}
				}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 5;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 15;
		contentPane.add(button, gbc_button);
	}

}
