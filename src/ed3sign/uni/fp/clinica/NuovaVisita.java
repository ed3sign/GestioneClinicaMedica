package ed3sign.uni.fp.clinica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

/**
 * Classe NuovaVisita
 * 
 * Classe per la prenotazione di una nuova visita.
 * Visualizzazione simile a quella per l'impostazione degli
 * orari settimanali. 
 * L'utente visualizza una tabella di orari di disponibilità,
 * seleziona uno slot di tempo e cliccando sul pulsante prenota
 * - se la selezione risulta valida - viene indirizzato al frame 
 * per l'aggiunta di una nuova visita e finalizzare l'operazione.
 * 
 * @author ed3sign
 *
 */
public class NuovaVisita extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Calendar cal = Calendar.getInstance();
	private JTable table;
	private File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	private File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	private HashMap<Integer, ArrayList<Date>> orariSettimanali;
	private JDateChooser dateChooser;
	private JComboBox<String> cb_medico;
	private DefaultTableModel model;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NuovaVisita frame = new NuovaVisita();
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
	public NuovaVisita() {
		// Window Refresh
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				loadTable();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		cal.set(Calendar.HOUR_OF_DAY, OrariSettimanali.STARTING_HOUR);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 790, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 178, 346, 202, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 3, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblAggiungiNuovaVisita = new JLabel("Aggiungi Nuova Visita");
		lblAggiungiNuovaVisita.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GridBagConstraints gbc_lblAggiungiNuovaVisita = new GridBagConstraints();
		gbc_lblAggiungiNuovaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblAggiungiNuovaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblAggiungiNuovaVisita.gridx = 1;
		gbc_lblAggiungiNuovaVisita.gridy = 1;
		contentPane.add(lblAggiungiNuovaVisita, gbc_lblAggiungiNuovaVisita);
		
		JLabel lblNewLabel = new JLabel("Filtra per Data");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 3;
		contentPane.add(dateChooser, gbc_dateChooser);
		
		dateChooser.setDate(cal.getTime());
		
		// Filter Button
		JButton btnNewButton = new JButton("Filtra");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable();
				headerRefresh();
				printVisite();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridheight = 2;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Filtra per Medico");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		cb_medico = new JComboBox<String>();
		cb_medico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(table != null){
					loadTable();
				}
			}
		});
		GridBagConstraints gbc_cb_medico = new GridBagConstraints();
		gbc_cb_medico.insets = new Insets(0, 0, 5, 5);
		gbc_cb_medico.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_medico.gridx = 2;
		gbc_cb_medico.gridy = 4;
		contentPane.add(cb_medico, gbc_cb_medico);
		
		// Caricamento Medici
		if(f_medici.exists()){
			ElencoMedici medici = null;
			medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
			
			for(Medico m : medici.elencoMedici)
				cb_medico.addItem(m.getAlbo() + " - " +m.getCognome()+ " "+m.getNome()+ " ("+m.getTipologia()+")");
		}
		
		// Selezione Medico Loggato
		for(int i=0; i<cb_medico.getItemCount(); i++){
			String med = (String) cb_medico.getItemAt(i);
			String codAlbo= med.substring(0, med.indexOf(" ")); 
			if(ClinicaMain.loggedin.get(true) != null && ClinicaMain.loggedin.get(true).getAlbo().equals(codAlbo))
				cb_medico.setSelectedIndex(i);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setCellEditor(null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		model = new DefaultTableModel(getCurrentWeek(dateChooser), 0);
		table.setModel(model);
		
		/**
		 * Pulsante di conferma (evento click sul pulsante Prenota)
		 * Prima di richiamare il frame per l'effettiva conferma di prenotazione,
		 * viene controllata la validità dell'orario e l'effettiva disponibilità
		 * del medico.
		 */
		JButton btnPrenota = new JButton("Prenota");
		btnPrenota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				// Riga Selezionata
				if(row == -1)
					JOptionPane.showMessageDialog(contentPane, "Nessun orario selezionato!", "Errore", JOptionPane.WARNING_MESSAGE);
				else if(table.getValueAt(row, col) == null || !table.getValueAt(row, col).equals(OrariSettimanali.DISPONIBILE))
					JOptionPane.showMessageDialog(contentPane, "Medico non disponibile per l'orario selezionato!", "Errore", JOptionPane.WARNING_MESSAGE);
				else{
					// Get Giorno Visita
					Date giorno_visita = null;
					try {giorno_visita =  MyUtil.revertDateFormatter((String) (table.getTableHeader().getColumnModel().getColumn(col).getHeaderValue()));
					} catch (ParseException e1) {e1.printStackTrace();}
					
					// Get Current Date
					Date current_date = MyUtil.getCurrentTime(); 
					
					// Get Orario Visita
					Date orario_visita = MyUtil.getHours(cal, row, col);
					
					// Merge Date
					Date data_visita = MyUtil.mergeDateTime(giorno_visita, orario_visita);
					
					// Get Medico
					Medico m = getMedico(cb_medico);
					
					// Controllo Data
					if(data_visita.before(current_date))
						JOptionPane.showMessageDialog(contentPane, "Data non valida!", "Errore", JOptionPane.WARNING_MESSAGE);
					else{
						PrenotaVisita newPrenotazione = new PrenotaVisita(m, data_visita);
						newPrenotazione.setVisible(true);
					}
				}
			}
		});
		GridBagConstraints gbc_btnPrenota = new GridBagConstraints();
		gbc_btnPrenota.anchor = GridBagConstraints.EAST;
		gbc_btnPrenota.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrenota.gridx = 3;
		gbc_btnPrenota.gridy = 7;
		contentPane.add(btnPrenota, gbc_btnPrenota);
				
		// Load Table Data
		printDayHours(model);
		loadTable();
		 
		// Table Selected Row Event
		ListSelectionListener newSelection = new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	// Disabilita selezione Prima colonna
	        	if(table.getSelectedColumn() == 0){
	        		table.getSelectionModel().clearSelection();
	        	}
	        
	        	if(table.getSelectedRowCount() > 0){
	        		if(table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).equals(OrariSettimanali.PRENOTATA))
	        			table.setSelectionBackground(Color.red);
	        		else
	        			table.setSelectionBackground(Color.green);
	        	}
	        }
	    };
	    table.getColumnModel().getSelectionModel().addListSelectionListener(newSelection);
		table.getSelectionModel().addListSelectionListener(newSelection);
	}
	
	/**
	 * Print Day Hours
	 * Stampa gli orari settimali delle visite, ad intervalli di 30 minuti.
	 * @param model table model
	 */
	public void printDayHours(DefaultTableModel model){
	    // Stampa Orari Settimali
		for(int i=0; i<=OrariSettimanali.TIME_SLOTS; i++){
			String starting_time = MyUtil.timeHourFormat(cal.getTime());
			cal.add(Calendar.MINUTE, OrariSettimanali.TIME_SLOT_DURATION);
			String ending_time = MyUtil.timeHourFormat(cal.getTime());
			Object data[] = {starting_time +" - "+ ending_time};
			model.addRow(data);
		}
	}

	
	/**
	 * Load Table
	 * Metodo di stampa della tabella.
	 * Per prima cosa viene controllato il login utente, per consentire l'accesso alla sezione di inserimento.
	 * Se la verifica avviene con successo, vengono visualizzati gli orari relativi al medico. 
	 * Gli orari sono tradotti in righe e colonne della tabella tramite dei metodi realizzati nella classe MyUtil.
	 */
	public void loadTable(){
		clearRowContent(table);
		
		// Accesso Richiesto
		if(ClinicaMain.loggedin.isEmpty()){
			JOptionPane.showMessageDialog(contentPane, "Effettuare il login prima di proseguire!", "Attenzione!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		
		else if(f_medici.exists()){
			// Orari Settimanali del Medico Selezionato
			orariSettimanali = getMedico(cb_medico).getOrariSettimanali();
			if(orariSettimanali != null){
				for (Entry <Integer, ArrayList<Date>> entry : orariSettimanali.entrySet()) {
					ArrayList<Date> dates = entry.getValue();
					for(int i=0; i<dates.size(); i++){
						int col = entry.getKey();
						int row = MyUtil.getHourRow(cal, dates.get(i));
						if(!dates.isEmpty()){
							table.setValueAt(OrariSettimanali.DISPONIBILE, row, col);
						}
					}
				}
			}
			else{
				orariSettimanali = new HashMap<Integer, ArrayList<Date>>();
			}
		}
		// Overwrite della disponibilità del medico con gli orari di visita.
		printVisite();
	}
	
	
	
	/**
	 * Get Medico From Combobox
	 * Il medico viene identificato univocamente con il codice albo.
	 * Prelevando il codice albo dal Combobox, è possibile controllare
	 * nell'archivio file l'esistenza di un medico che abbia lo stesso
	 * codice albo.
	 * @param cb_medico JComboBox con elenco dei medici
	 * @return Medico medico selezionato, o null se non esiste
	 */
	public Medico getMedico(JComboBox<String> cb_medico){
		String med = (String) cb_medico.getSelectedItem();
		String codAlbo= med.substring(0, med.indexOf(" ")); 
		ElencoMedici medici = null;
		medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
		
		for(Medico m : medici.elencoMedici)
			if(m.getAlbo().equals(codAlbo))
				return m;
		return null;
	}
	
	/**
	 * Print Visite
	 * Stampa nella tabella gli orari di visita già prenotati.
	 * Le disponibilità della tabella originale vengono quindi sostituite
	 * nel caso vi sia una visita in quel determinato slot di tempo.
	 * @param table
	 */
	public void printVisite(){
		if(f_visite.exists()){
			ElencoVisite visite = null;
			visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
			for(Visita v : visite.elencoVisite){
				
				// Get Current Date
				Calendar now = new GregorianCalendar();
				Date current_date = new Date();
				current_date = now.getTime();
				
				// Archiviazione Visite Completate
				if(v.getData().before(current_date))
					v.setStato(ClinicaMain.CONCLUSA);
				
				// Get Selected Codice Albo From Combobox
				String med = (String) cb_medico.getSelectedItem();
				String codAlbo = med.substring(0, med.indexOf(" "));
				
				// Stampa Visite relative al medico selezionato
				if(v.getMedico().getAlbo().equals(codAlbo)){
					Date dataVisita = v.getData();
					int row = MyUtil.getHourRow(cal, dataVisita);
					int col = MyUtil.getDayCol(cal, dataVisita)-1;
					
					JTableHeader th = table.getTableHeader();
					TableColumnModel tcm = th.getColumnModel();
					
					// Get Date from Table Header
					Date col_date = null;
					try{col_date = MyUtil.revertDayFormatter(tcm.getColumn(col).getHeaderValue().toString());}
					catch(Exception e){e.printStackTrace();}
					
					if(MyUtil.dateFormatter(col_date).equals(MyUtil.dateFormatter(v.getData())))
						table.setValueAt(OrariSettimanali.PRENOTATA, row, col);
				}
			}
		}
	}
	
	
	/**
	 * Header Tabella Settimana Corrente
	 * Stampa settimana corrente, a partire da Lunedì.
	 * La settimana lavorativa del medico parte da Lunedì fino a Venerdì.
	 */
	public Object[] getCurrentWeek(JDateChooser date){
		Calendar now = date.getCalendar();
		Object[] days = new String[ClinicaMain.WORKING_DAYS];
	    int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; // Week start on Monday
	    now.add(Calendar.DAY_OF_MONTH, delta);
	    days[0] = "";
	    for (int i = 1; i < days.length; i++){
	        days[i] = MyUtil.dayFormatter(now.getTime());
	        now.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    return days;
	}
	
	/**
	 * Header Refresh
	 * Aggiorna gli header della tabella tramite il TableColumnModel,
	 * con i giorni e le date della settimana.
	 */
	public void headerRefresh(){
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		for(int i=0; i<ClinicaMain.WORKING_DAYS; i++){
			Object days[] = getCurrentWeek(dateChooser);
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(days[i]);
		}
		th.repaint();
	}
	
	/**
	 * Clear table
	 * Azzera il contenuto di tutte le celle della tabella
	 * @param model table model
	 */
	public void clearRowContent(JTable table){
		int rows = table.getRowCount();
		int cols = table.getColumnCount();
		for(int i = 0; i<rows; i++)
			for(int j=1; j<cols; j++)
				table.setValueAt("", i, j);
	}
}
