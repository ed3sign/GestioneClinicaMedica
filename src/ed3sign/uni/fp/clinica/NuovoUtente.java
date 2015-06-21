package ed3sign.uni.fp.clinica;

import java.awt.BorderLayout;
import java.io.Serializable;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JComboBox;
import javax.swing.JButton;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

import org.joda.time.*;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class NuovoUtente extends JFrame{

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
	protected JLabel lblSesso;
	protected JLabel lblCodFiscale;
	protected JFormattedTextField tf_codiceFiscale;
	protected JComboBox<String> cb_sesso;
	protected JButton button;
	File f_users = new File(ClinicaMain.UTENTI_FILENAME);
	protected JLabel lbl_title;
	protected JSeparator separator;
	

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
		setBounds(100, 100, 641, 507);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{36, 125, 6, 34, 0};
		gbl_contentPane.rowHeights = new int[]{45, 0, 0, 0, 0, 28, 28, 28, 28, 28, 27, 28, 0, 0, 60, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		gbc_tf_nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_nome.insets = new Insets(0, 0, 5, 5);
		gbc_tf_nome.gridx = 2;
		gbc_tf_nome.gridy = 3;
		contentPane.add(tf_nome, gbc_tf_nome);
		tf_nome.setColumns(20);
		tf_nome.setText("Gianni");
		
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
		gbc_tf_cognome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cognome.insets = new Insets(0, 0, 5, 5);
		gbc_tf_cognome.gridx = 2;
		gbc_tf_cognome.gridy = 4;
		contentPane.add(tf_cognome, gbc_tf_cognome);
		tf_cognome.setText("Morandi");
		
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
		gbc_tf_luogo_nascita.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_luogo_nascita.insets = new Insets(0, 0, 5, 5);
		gbc_tf_luogo_nascita.gridx = 2;
		gbc_tf_luogo_nascita.gridy = 6;
		contentPane.add(tf_luogo_nascita, gbc_tf_luogo_nascita);
		tf_luogo_nascita.setText("Monghidoro");
		
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
		gbc_tf_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_telefono.insets = new Insets(0, 0, 5, 5);
		gbc_tf_telefono.gridx = 2;
		gbc_tf_telefono.gridy = 7;
		contentPane.add(tf_telefono, gbc_tf_telefono);
		tf_telefono.setText("3334565789");
		
		
		// Sesso
		lblSesso = new JLabel("Sesso");
		GridBagConstraints gbc_lblSesso = new GridBagConstraints();
		gbc_lblSesso.anchor = GridBagConstraints.WEST;
		gbc_lblSesso.insets = new Insets(0, 0, 5, 5);
		gbc_lblSesso.gridx = 1;
		gbc_lblSesso.gridy = 8;
		contentPane.add(lblSesso, gbc_lblSesso);
		
		tf_codiceFiscale = new JFormattedTextField();
		GridBagConstraints gbc_tf_codiceFiscale = new GridBagConstraints();
		gbc_tf_codiceFiscale.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_codiceFiscale.insets = new Insets(0, 0, 5, 5);
		gbc_tf_codiceFiscale.gridx = 2;
		gbc_tf_codiceFiscale.gridy = 8;
		contentPane.add(tf_codiceFiscale, gbc_tf_codiceFiscale);
		tf_codiceFiscale.setText("GGGSST95D26H916Y");
		
		// Codice Fiscale
		lblCodFiscale = new JLabel("Cod. Fiscale");
		GridBagConstraints gbc_lblCodFiscale = new GridBagConstraints();
		gbc_lblCodFiscale.anchor = GridBagConstraints.WEST;
		gbc_lblCodFiscale.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodFiscale.gridx = 1;
		gbc_lblCodFiscale.gridy = 9;
		contentPane.add(lblCodFiscale, gbc_lblCodFiscale);
		
		
		/**
		 * Pulsate Aggiungi
		 * Evento: Click
		 */
		
		cb_sesso = new JComboBox<String>();
		cb_sesso.addItem("Maschio");
		cb_sesso.addItem("Femmina");
		GridBagConstraints gbc_cb_sesso = new GridBagConstraints();
		gbc_cb_sesso.insets = new Insets(0, 0, 5, 5);
		gbc_cb_sesso.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_sesso.gridx = 2;
		gbc_cb_sesso.gridy = 9;
		contentPane.add(cb_sesso, gbc_cb_sesso);
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
						
					// Validazione Codice Fiscale
					if(!MyUtil.checkCodFisc(cod))
						JOptionPane.showMessageDialog(contentPane, "Formato del Codice Fiscale non valido!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
					else{
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
		gbc_button.gridwidth = 2;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 1;
		gbc_button.gridy = 15;
		contentPane.add(button, gbc_button);
	}

}
