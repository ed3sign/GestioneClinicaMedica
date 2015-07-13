package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import ed3sign.uni.fp.utility.CodiceFiscale;
import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.toedter.calendar.JDateChooser;

/**
 * Classe NuovoMedico
 * Classe per la creazione di un nuovo medico. 
 * Form sostanzialmente identico a quello per l'inserimento
 * di un nuovo utente semplice, con la differenza di alcuni
 * nuovi campi relativi al tipo di medico, alla specializzazione
 * e ai dettagli di accesso quindi username e password.
 * @author ed3sign
 *
 */
public class NuovoMedico extends JFrame{
	
	private static final long serialVersionUID = 1L;
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	private JTextField tf_user;
	private JPasswordField tf_pass;
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
	protected JLabel lbl_title;
	protected JSeparator separator;
	protected JButton btnCalcola;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuovoMedico frame = new NuovoMedico();
					System.out.println("Swag");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Metodo Costruttore nuovo Frame
	 * @throws IOException
	 */
	public NuovoMedico() throws IOException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 640, 510);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{36, 125, 359, 160, 34, 0};
		gbl_contentPane.rowHeights = new int[]{45, 0, 0, 0, 0, 28, 28, 28, 28, 28, 27, 28, 0, 0, 60, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
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
		
		
		// Sesso
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
		
		// Sesso
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
		
		setBounds(100, 100, 700, 510);
		
		GridBagLayout gridBagLayout = (GridBagLayout) getContentPane().getLayout();
		gridBagLayout.columnWidths = new int[]{20, 124, 386, 50, 20};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 2.0, 0.0};
		
		lbl_title.setText("Nuovo Medico");
		
		// Lista delle Competenze
		ArrayList<JCheckBox> allCheckBoxes = new ArrayList<JCheckBox>();
		
		JLabel lblTipologia = new JLabel("Tipologia");
		GridBagConstraints gbc_lblTipologia = new GridBagConstraints();
		gbc_lblTipologia.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipologia.anchor = GridBagConstraints.WEST;
		gbc_lblTipologia.gridx = 1;
		gbc_lblTipologia.gridy = 10;
		getContentPane().add(lblTipologia, gbc_lblTipologia);
		
		JComboBox<String> cb_tipologia = new JComboBox<String>();
		cb_tipologia.addItem(ClinicaMain.GENERICO);
		cb_tipologia.addItem(ClinicaMain.SPECIALISTA);
		GridBagConstraints gbc_cb_tipologia = new GridBagConstraints();
		gbc_cb_tipologia.insets = new Insets(0, 0, 5, 5);
		gbc_cb_tipologia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_tipologia.gridx = 2;
		gbc_cb_tipologia.gridy = 10;
		getContentPane().add(cb_tipologia, gbc_cb_tipologia);
		
		// Aree di Competenza
		JLabel lblAreaDiCompetenza = new JLabel("Area di Competenza");
		GridBagConstraints gbc_lblAreaDiCompetenza = new GridBagConstraints();
		gbc_lblAreaDiCompetenza.anchor = GridBagConstraints.WEST;
		gbc_lblAreaDiCompetenza.insets = new Insets(0, 0, 5, 5);
		gbc_lblAreaDiCompetenza.gridx = 1;
		gbc_lblAreaDiCompetenza.gridy = 11;
		getContentPane().add(lblAreaDiCompetenza, gbc_lblAreaDiCompetenza);
		
		JPanel tipo_panel = new JPanel();
		GridBagConstraints gbc_tipo_panel = new GridBagConstraints();
		gbc_tipo_panel.anchor = GridBagConstraints.WEST;
		gbc_tipo_panel.gridheight = 2;
		gbc_tipo_panel.insets = new Insets(0, 0, 5, 5);
		gbc_tipo_panel.gridx = 2;
		gbc_tipo_panel.gridy = 11;
		getContentPane().add(tipo_panel, gbc_tipo_panel);
		
		// Specializzazioni
		JCheckBox cb_cardiologia = new JCheckBox("Cardiologia");
		JCheckBox cb_chirurgia = new JCheckBox("Chirurgia");
		JCheckBox cb_gastroenterologia = new JCheckBox("Gastroenterologia");
		allCheckBoxes.add(cb_cardiologia);
		allCheckBoxes.add(cb_chirurgia);
		allCheckBoxes.add(cb_gastroenterologia);
		JCheckBox cb_nefrologia = new JCheckBox("Nefrologia");
		allCheckBoxes.add(cb_nefrologia);
		JCheckBox cb_ortopedia = new JCheckBox("Ortopedia");
		allCheckBoxes.add(cb_ortopedia);
		GroupLayout gl_tipo_panel = new GroupLayout(tipo_panel);
		gl_tipo_panel.setHorizontalGroup(
			gl_tipo_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tipo_panel.createSequentialGroup()
					.addGap(19)
					.addGroup(gl_tipo_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(cb_cardiologia)
						.addComponent(cb_nefrologia))
					.addGap(5)
					.addGroup(gl_tipo_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_tipo_panel.createSequentialGroup()
							.addComponent(cb_chirurgia)
							.addGap(5)
							.addComponent(cb_gastroenterologia))
						.addComponent(cb_ortopedia))
					.addGap(80))
		);
		gl_tipo_panel.setVerticalGroup(
			gl_tipo_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tipo_panel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_tipo_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(cb_cardiologia)
						.addComponent(cb_chirurgia)
						.addComponent(cb_gastroenterologia))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_tipo_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cb_nefrologia)
						.addComponent(cb_ortopedia))
					.addContainerGap())
		);
		tipo_panel.setLayout(gl_tipo_panel);
		
		// Username
		JLabel lblUser = new JLabel("User");
		GridBagConstraints gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.WEST;
		gbc_lblUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblUser.gridx = 1;
		gbc_lblUser.gridy = 13;
		getContentPane().add(lblUser, gbc_lblUser);
		
		tf_user = new JTextField();
		GridBagConstraints gbc_tf_user = new GridBagConstraints();
		gbc_tf_user.gridwidth = 2;
		gbc_tf_user.insets = new Insets(0, 0, 5, 5);
		gbc_tf_user.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_user.gridx = 2;
		gbc_tf_user.gridy = 13;
		getContentPane().add(tf_user, gbc_tf_user);
		tf_user.setColumns(10);
		
		// Password
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 14;
		getContentPane().add(lblPassword, gbc_lblPassword);
		
		tf_pass = new JPasswordField();
		GridBagConstraints gbc_tf_pass = new GridBagConstraints();
		gbc_tf_pass.gridwidth = 2;
		gbc_tf_pass.insets = new Insets(0, 0, 5, 5);
		gbc_tf_pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_pass.gridx = 2;
		gbc_tf_pass.gridy = 14;
		getContentPane().add(tf_pass, gbc_tf_pass);
		
		/**
		 * Aggiungi Medico (evento click su pulsante)
		 */
		button = new JButton("Aggiungi");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verifica Inserimento Dati
				if(tf_user.getText().equals("") || tf_pass.getPassword().equals("") || tf_nome.getText().equals("") || tf_cognome.getText().equals("") || dateChooser.getDate() == null || tf_luogo_nascita.getText().equals("") 
						|| tf_telefono.getText().equals("") || tf_codiceFiscale.getText().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Attenzione! Inserisci tutti i dati richiesti!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
				else{
					String user=tf_user.getText();
					String pass=new String(tf_pass.getPassword());
					String nome=tf_nome.getText();
					String cognome=tf_cognome.getText();
					Date date = dateChooser.getDate();
					String luogo = tf_luogo_nascita.getText();
					String sesso = cb_sesso.getSelectedItem().toString();
					String tel = tf_telefono.getText();
					String cod = tf_codiceFiscale.getText();
					String tipo = cb_tipologia.getSelectedItem().toString();
					ArrayList<String> competenze = new ArrayList<String>();
					
					// Selezione Competenze
					for(Component component : tipo_panel.getComponents()) {
					  if(component instanceof JCheckBox) {
					    if(((JCheckBox)component).isSelected()){
					    	competenze.add(((JCheckBox) component).getText());
					    }
					  }
					}
					
					// Validazione Nome Utente
					ElencoMedici medici = null;
					if(f_medici.exists() && f_medici.length()>0)
						medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
					else
						medici = new ElencoMedici();
						
					// Controllo Utente gi� esistente
					boolean existing = false;
					if(medici.elencoMedici.size()>0){
						for(Medico m : medici.elencoMedici){
							if(user.equals(m.getUser())){
								System.out.println("Medico gi� esistente");
								JOptionPane.showMessageDialog(contentPane, "Medico gi� presente nei nostri archivi!", "Errore", JOptionPane.WARNING_MESSAGE);
								existing=true;
							}
						}
					}
						
					// Validazione Codice Fiscale
					if(!MyUtil.checkCodFisc(cod))
						JOptionPane.showMessageDialog(contentPane, "Formato del Codice Fiscale non valido!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
					else if(!existing){
						String albo = MyUtil.generaCodAlbo(cognome);
						Medico newMedico = new Medico(user, pass, nome, cognome, date, luogo, sesso, tel, cod, albo, tipo, competenze);
						newMedico.aggiungiMedico(newMedico);
						JOptionPane.showMessageDialog(contentPane, "Medico Inserito!", "Operazione Riuscita", JOptionPane.DEFAULT_OPTION);
						
						// FIELD RESET
						InterfaceHelpers.cls(contentPane);
						InterfaceHelpers.cleanCheckboxes(contentPane);
						dateChooser.setDate(null);
					}
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 3;
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 16;
		contentPane.add(button, gbc_button);
		
	}
	

}
