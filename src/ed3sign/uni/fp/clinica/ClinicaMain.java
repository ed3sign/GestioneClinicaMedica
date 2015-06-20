package ed3sign.uni.fp.clinica;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ed3sign.uni.fp.utility.MyFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class ClinicaMain {
	
	final static String UTENTI_FILENAME = "utenti.txt";
	final static String MEDICI_FILENAME = "medici.txt";
	final static String VISITE_FILENAME = "visite.txt";
	final static String SUCCESS_LOAD = "Caricamento Avvenuto con successo!";
	final static String MSG_NO_FILE = "Nessun file trovato.";
	final static String CAST_ERROR = "Errore di casting!";
	protected static HashMap<Boolean, String> loggedin = new HashMap<Boolean, String >();
	
	private static JMenu mn_visualizza = null;
	private static JMenu menu_aggiungi = null;
	private static JMenu menu_orari = null;
	private static JMenuItem mntmLogout = null;
	private static JMenuItem mnLogin = null;
	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Inizializzazione File
		File f_users = new File(UTENTI_FILENAME);
		File f_medici = new File(MEDICI_FILENAME);
		boolean successfulLoad = false;
		ElencoUtenti utenti = null;
		ElencoMedici medici = null;
		
		// Caricamento File, se gi� esistente
		if(f_users.exists()){
			try{
				utenti = (ElencoUtenti) MyFile.loadObject(f_users, UTENTI_FILENAME);
				medici = (ElencoMedici) MyFile.loadObject(f_medici, MEDICI_FILENAME);
			} catch (ClassCastException e){ e.printStackTrace(); System.out.println(CAST_ERROR); }
			finally{
				if((utenti != null && medici != null)){
					System.out.println(SUCCESS_LOAD);
					successfulLoad = true;
				}
			}
		}

		// File non esistente
		if (!successfulLoad)
			System.out.println(MSG_NO_FILE);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClinicaMain window = new ClinicaMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClinicaMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setBounds(0, 0, 1000, 600);
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnUtente = new JMenu("Utente");
		menuBar.add(mnUtente);
		
		mnLogin = new JMenuItem("Login");
		mnUtente.add(mnLogin);
		
		mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loggedin.remove(true);
				JOptionPane.showMessageDialog(frame, "Torna Presto!", "Logout", JOptionPane.INFORMATION_MESSAGE);
				checkMenuLogin();
			}
		});
		mnUtente.add(mntmLogout);
		mnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login newLogin = null;
				newLogin = new Login();
				newLogin.setVisible(true);
			}
		});
		
		menu_aggiungi = new JMenu("Aggiungi");
		menuBar.add(menu_aggiungi);
		
		/**
		 * Aggiungi Nuovo Utente
		 */
		JMenuItem mntmNuovoUtente = new JMenuItem("Nuovo Utente");
		mntmNuovoUtente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuovoUtente nuovoUtente = null;
				try {
					nuovoUtente = new NuovoUtente();
				} catch (IOException ioe) {ioe.printStackTrace();}
				nuovoUtente.setVisible(true);
			}
		});
		menu_aggiungi.add(mntmNuovoUtente);
		
		
		JMenuItem mntmNuovoMedico = new JMenuItem("Nuovo Medico");
		mntmNuovoMedico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NuovoMedico nuovoMedico = null;
				try {
					nuovoMedico = new NuovoMedico();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
				nuovoMedico.setVisible(true);
			}
		});
		menu_aggiungi.add(mntmNuovoMedico);
		
		JMenuItem mntmNuovaVisita = new JMenuItem("Nuova Visita");
		menu_aggiungi.add(mntmNuovaVisita);
		
		mn_visualizza = new JMenu("Visualizza");
		menuBar.add(mn_visualizza);
		
		JMenuItem mntmElencoUtenti = new JMenuItem("Elenco Utenti");
		mntmElencoUtenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizzaUtenti newView = null;
				newView = new VisualizzaUtenti();
				newView.setVisible(true);
			}
		});
		mn_visualizza.add(mntmElencoUtenti);
		
		JMenuItem mntmElencoMedici = new JMenuItem("Elenco Medici");
		mntmElencoMedici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisualizzaMedici newVisualizzaMedici = new VisualizzaMedici();
				newVisualizzaMedici.setVisible(true);
			}
		});
		mn_visualizza.add(mntmElencoMedici);
		
		JMenuItem mntmElencoVisite = new JMenuItem("Elenco Visite");
		mn_visualizza.add(mntmElencoVisite);
		
		menu_orari = new JMenu("Orari & Visite");
		menuBar.add(menu_orari);
		
		JMenu menu_help = new JMenu("Help");
		menuBar.add(menu_help);
		
		// Testing: already logged in
		loggedin.put(true, "gianni");
		checkMenuLogin();
	}
	
	public static void checkMenuLogin(){
		if(loggedin.isEmpty()){
			menu_aggiungi.setVisible(false);
			mn_visualizza.setVisible(false);
			menu_orari.setVisible(false);
			mntmLogout.setVisible(false);
			mnLogin.setVisible(true);
		}
		else{
			mntmLogout.setVisible(true);
			menu_aggiungi.setVisible(true);
			mn_visualizza.setVisible(true);
			menu_orari.setVisible(true);
			mnLogin.setVisible(false);
		}
	}
}