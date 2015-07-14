package ed3sign.uni.fp.clinica;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Classe Login
 * Form di login utente.
 * @author Seba
 *
 */
public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField tf_pass;
	private JButton btnLogin;
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	private JTextField tf_utente;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 344, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 10, 0, 173, 20, 0};
		gbl_contentPane.rowHeights = new int[]{20, 0, 10, 0, 15, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblUtente = new JLabel("Nome Utente");
		GridBagConstraints gbc_lblUtente = new GridBagConstraints();
		gbc_lblUtente.anchor = GridBagConstraints.WEST;
		gbc_lblUtente.insets = new Insets(0, 0, 5, 5);
		gbc_lblUtente.gridx = 1;
		gbc_lblUtente.gridy = 1;
		contentPane.add(lblUtente, gbc_lblUtente);
		
		tf_utente = new JTextField();
		tf_utente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLogin.doClick();
			}
		});
		GridBagConstraints gbc_tf_utente = new GridBagConstraints();
		gbc_tf_utente.insets = new Insets(0, 0, 5, 5);
		gbc_tf_utente.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_utente.gridx = 3;
		gbc_tf_utente.gridy = 1;
		contentPane.add(tf_utente, gbc_tf_utente);
		tf_utente.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 3;
		contentPane.add(lblPassword, gbc_lblPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tf_utente.getText().equals("") || tf_pass.getPassword().equals(""))
					JOptionPane.showMessageDialog(contentPane, "Attenzione! Compilare tutti i dati richiesti!", "Errore di Validazione", JOptionPane.WARNING_MESSAGE);
				
				else{
					// Preleva Dati dal Form
					String utente=tf_utente.getText();
					String pass = new String(tf_pass.getPassword());

					// Ottieni Lista Utenti
					if(f_medici.exists()){
						ElencoMedici medici = null;
						medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
					
						// Login Check
						checkLogin(medici.elencoMedici, utente, pass);
					}
					else
						JOptionPane.showMessageDialog(contentPane, "Nessun utente presente nei nostri archivi!", "Errore", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		tf_pass = new JPasswordField();
		tf_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					btnLogin.doClick();
			}
		});
		GridBagConstraints gbc_tf_pass = new GridBagConstraints();
		gbc_tf_pass.insets = new Insets(0, 0, 5, 5);
		gbc_tf_pass.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_pass.gridx = 3;
		gbc_tf_pass.gridy = 3;
		contentPane.add(tf_pass, gbc_tf_pass);
		
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.gridwidth = 3;
		gbc_btnLogin.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogin.gridx = 1;
		gbc_btnLogin.gridy = 5;
		contentPane.add(btnLogin, gbc_btnLogin);
	}
	
	/**
	 * Login Check
	 * @param list lista degli utenti
	 * @param user nome utente inserito
	 * @param pass password inserita
	 */
	public void checkLogin(ArrayList<Medico> list, String user, String pass) {
		boolean found = false;
		
		// Testing
		for(Medico m : list){
			if(m.getUser().equals(user) && m.getPass().equals(pass)){
				found = true;
				ClinicaMain.loggedin.put(true, m);
				InterfaceHelpers.closeParent(contentPane);
				JOptionPane.showMessageDialog(contentPane, "Benvenuto, "+m.getNome()+"!", "Login Confermato", JOptionPane.INFORMATION_MESSAGE);
				ClinicaMain.checkMenuLogin();
			}
		}
		if(!found)
			JOptionPane.showMessageDialog(contentPane, "Attenzione! Utente non presente nei nostri archivi!", "Utente non Riconosciuto", JOptionPane.WARNING_MESSAGE);
	}

}
