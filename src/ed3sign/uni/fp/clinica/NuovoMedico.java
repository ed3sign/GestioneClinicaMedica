package ed3sign.uni.fp.clinica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class NuovoMedico extends NuovoUtente {
	
	private static final long serialVersionUID = 1L;
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	private JTextField tf_user;
	private JPasswordField tf_pass;

	public NuovoMedico() throws IOException {
		super();
		
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
		 * Aggiungi Medico
		 */
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
		
	}
	

}
