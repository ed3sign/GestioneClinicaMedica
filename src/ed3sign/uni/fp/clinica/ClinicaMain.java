package ed3sign.uni.fp.clinica;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;

import ed3sign.uni.fp.utility.MyFile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Classe Principale ClinicaMain
 * @author Sebastiano Gaggiano
 * @version 1.0.0
 */
public class ClinicaMain {
	final static String UTENTI_FILENAME = "utenti.txt";
	final static String MEDICI_FILENAME = "medici.txt";
	final static String VISITE_FILENAME = "visite.txt";
	final static String REFERTI_FILENAME = "referti.txt";
	final static String SUCCESS_LOAD = "Caricamento Avvenuto con successo!";
	final static String MSG_NO_FILE = "Nessun file trovato.";
	final static String CAST_ERROR = "Errore di casting!";
	protected static final int WORKING_DAYS = 6;
	protected static final String PRENOTATA = "Prenotata";
	protected final static String CONCLUSA = "Conclusa";
	protected final static String ARCHIVIATA = "Archiviata";
	protected static final String GENERICA = "Generica";
	protected static final String SPECIALISTICA = "Specialistica";
	protected static final String SPECIALISTA = "Specialista";
	protected static final String GENERICO = "Generico";
	protected static HashMap<Boolean, Medico> loggedin = new HashMap<Boolean, Medico >();
	
	private static JMenu mn_visualizza = null;
	private static JMenu menu_aggiungi = null;
	private static JMenu menu_orari = null;
	private static JMenuItem mntmLogout = null;
	private static JMenuItem mnLogin = null;
	private static JMenuItem mntmNuovaVisita = null;
	private JFrame frame;
	
	/**
	 * Metodo Principale
	 * Lancio dell'applicazione
	 * @param args
	 */
	public static void main(String[] args) {
		// Inizializzazione File Utente e Medico
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
		
		// Creazione ed Apertura della finestra principale
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
		
		
		// Creazione del Men� di navigazione con relativi sottomen� e voci
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnUtente = new JMenu("Accesso");
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
		
		mntmNuovaVisita = new JMenuItem("Nuova Visita");
		mntmNuovaVisita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuovaVisita newVisita = new NuovaVisita();
				newVisita.setVisible(true);
			}
		});
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
		mntmElencoVisite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VisualizzaVisite newVisualizzaVisite = new  VisualizzaVisite();
				newVisualizzaVisite.setVisible(true);
			}
		});
		mn_visualizza.add(mntmElencoVisite);
		
		JMenuItem mntmElencoReferti = new JMenuItem("Elenco Referti");
		mntmElencoReferti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisualizzaReferti newVisualizzaReferti = new VisualizzaReferti();
				newVisualizzaReferti.setVisible(true);
			}
		});
		mn_visualizza.add(mntmElencoReferti);
		
		menu_orari = new JMenu("Orari & Visite");
		menuBar.add(menu_orari);
		
		JMenuItem mntmImpostaOrariDi = new JMenuItem("Imposta Orari di Visita");
		mntmImpostaOrariDi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OrariSettimanali orari = new OrariSettimanali();
				orari.setVisible(true);
			}
		});
		menu_orari.add(mntmImpostaOrariDi);
		
		JMenu menu_help = new JMenu("Help");
		menuBar.add(menu_help);
		
		checkMenuLogin();
	}
	
	/**
	 * Metodo di Controllo del Login Utente
	 * A seconda che l'utente sia loggato o meno,
	 * alcune funzioni dell'applicativo risultano ad accesso riservato ai medici.
	 */
	public static void checkMenuLogin(){
		if(loggedin.isEmpty()){
			menu_orari.setVisible(false);
			mntmLogout.setVisible(false);
			mntmNuovaVisita.setVisible(false);
			mnLogin.setVisible(true);
			
		}
		else{
			mntmLogout.setVisible(true);
			menu_aggiungi.setVisible(true);
			menu_orari.setVisible(true);
			mntmNuovaVisita.setVisible(true);
			mnLogin.setVisible(false);
		}
	}
}
