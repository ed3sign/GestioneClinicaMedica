package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe OrariSettimanali
 * Classe per la gestione degli orari settimanali del medico.
 * @author ed3sign
 *
 */
public class OrariSettimanali extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnSalva;
	public static final int STARTING_HOUR = 8;
	public static final int TIME_SLOT_DURATION = 30;
	public static final int TIME_SLOTS = 20;
	public static final String DISPONIBILE = "Disponibile";
	public static final String PRENOTATA = "Prenotata";
	protected Calendar cal = Calendar.getInstance();
	protected File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	protected File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	protected HashMap<Integer, ArrayList<Date>> orariSettimanali;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrariSettimanali frame = new OrariSettimanali();
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
	public OrariSettimanali() {
		cal.set(Calendar.HOUR_OF_DAY, STARTING_HOUR);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{774, 0};
		gbl_contentPane.rowHeights = new int[]{47, 509, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblOrariSettimanali = new JLabel("Orari Settimanali");
		lblOrariSettimanali.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOrariSettimanali.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblOrariSettimanali = new GridBagConstraints();
		gbc_lblOrariSettimanali.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblOrariSettimanali.insets = new Insets(0, 0, 5, 0);
		gbc_lblOrariSettimanali.gridx = 0;
		gbc_lblOrariSettimanali.gridy = 0;
		contentPane.add(lblOrariSettimanali, gbc_lblOrariSettimanali);
		
		scrollPane = new JScrollPane();
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			/**
			 * Pressione tasto ENTER
			 * Selezionando gli orari desiderati e premendo il tasto ENTER viene
			 * impostata la disponibilità del medico per quegli orari.
			 */
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(table.getSelectedColumnCount() > 0){
                    	int selected_rows[] = table.getSelectedRows();
                    	int selected_cols[] = table.getSelectedColumns();
                    	for(int i=0; i<selected_rows.length; i++){
                    		for(int j=0; j<selected_cols.length; j++)
                    				table.setValueAt(DISPONIBILE, selected_rows[i], selected_cols[j]);
                    	}
                    }
                }
				
				/**
				 * Premendo invece il tasto SHIFT, si annulla la disponibilità 
				 * per la selezione corrente.
				 */
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					if(table.getSelectedColumnCount() > 0){
                    	int selected_rows[] = table.getSelectedRows();
                    	int selected_cols[] = table.getSelectedColumns();
                    	for(int i=0; i<selected_rows.length; i++){
                    		for(int j=0; j<selected_cols.length; j++)
                    			if(table.getValueAt(selected_rows[i], selected_cols[j]) != null && table.getValueAt(selected_rows[i], selected_cols[j]).equals(DISPONIBILE))
                    				table.setValueAt("", selected_rows[i], selected_cols[j]);
                    	}
                    }
				}
			}
		});
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setCellEditor(null);
		scrollPane.setViewportView(table);
		
		// Header Tabella con i giorni della settimana
		String col[] = {"","Lun","Mar","Mer", "Gio", "Ven"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table.setModel(model);
		printDayHours(model);
		loadTable();
		
		// Table Selected Row Event
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	// Disabilita selezione Prima colonna
	        	if(table.getSelectedColumn() == 0){
	        		table.getSelectionModel().clearSelection();
	        	}
	        	
	        	if(table.getSelectedRowCount() > 0){
	        		table.setSelectionBackground(Color.green);
	        	}
	        }
	    });
		
		// Pulsante di Salvataggio
		GridBagConstraints gbc_btnSalva = new GridBagConstraints();
		gbc_btnSalva.gridx = 0;
		gbc_btnSalva.gridy = 2;
		
		/**
		 * Salva Orari (evento sul pulsante Salva)
		 * L'aggiornamento degli orari di visita comporterà un annullamento
		 * di tutte le visite in stato di prenotazione.
		 */
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = 0;
				boolean existing = false;
				Medico m = ClinicaMain.loggedin.get(true);
				if(!m.getOrariSettimanali().isEmpty()){
					choice = JOptionPane.showConfirmDialog(getParent(), "L'aggiornamento degli orari settimanali comporterà la rimozione di tutte le visite prenotate");
					existing = true;
				}
				
				if(choice == JOptionPane.YES_OPTION || !existing){
					for(int j=0; j<table.getColumnCount(); j++){
						ArrayList<Date> orariMedico = new ArrayList<Date>();
	            		for(int i=0; i<table.getRowCount(); i++){
	            			if(table.getValueAt(i, j) != null && table.getValueAt(i, j).equals(DISPONIBILE)){
	            				Date intervallo = MyUtil.getHours(cal, i, j);
	            				orariMedico.add(intervallo);
	            			}
	            		}
	            		// Inserimento Hashmap
	            		orariSettimanali.put(j, orariMedico);
	            	}
					
					// Stampa Hashmap
					for (Entry <Integer, ArrayList<Date>> entry : orariSettimanali.entrySet()) {
						  Integer key = entry.getKey();
						  ArrayList<Date> value = entry.getValue();
						  System.out.println("Chiave: "+key+ " Contenuto: "+value);
					}
					
					// Salvataggio Orari Settimanali
					ElencoMedici medici = null;
					medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
					
					// Reset delle Visite
					if(f_visite.exists()){
						ElencoVisite visite = null;
						visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
						for(int i=0; i<visite.elencoVisite.size(); i++){
							if(visite.elencoVisite.get(i).getStato().equals(ClinicaMain.PRENOTATA) && visite.elencoVisite.get(i).getMedico().equals(m))
								visite.elencoVisite.remove(i);
						}
					}
					
					// Aggiornamento Medico
					for(Medico m1 : medici.elencoMedici){
						if(m1.getUser().equals(m.getUser()))
							m1.setOrariSettimanali(orariSettimanali);
					}
					
					// Salvataggio su File
					MyFile.saveObject(f_medici, medici, ClinicaMain.MEDICI_FILENAME);
					JOptionPane.showMessageDialog(getParent(), "Orari Salvati!");
				}
			}
		});
		contentPane.add(btnSalva, gbc_btnSalva);
	}
	
	/**
	 * Print Week Days
	 * @param model table model
	 */
	public void printDayHours(DefaultTableModel model){
		for(int i=0; i<=TIME_SLOTS; i++){
			String starting_time = MyUtil.timeHourFormat(cal.getTime());
			cal.add(Calendar.MINUTE, 30);
			String ending_time = MyUtil.timeHourFormat(cal.getTime());
			Object data[] = {starting_time +" - "+ ending_time};
			model.addRow(data);
		}
	}
	
	/**
	 * Load Table
	 */
	public void loadTable(){
		// Accesso Richiesto
		if(ClinicaMain.loggedin.isEmpty()){
			JOptionPane.showMessageDialog(contentPane, "Effettuare il login prima di proseguire!", "Attenzione!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		else if(f_medici.exists()){
			orariSettimanali = ClinicaMain.loggedin.get(true).getOrariSettimanali();
			System.out.println(orariSettimanali);
			if(orariSettimanali != null){
				for (Entry <Integer, ArrayList<Date>> entry : orariSettimanali.entrySet()) {
					ArrayList<Date> dates = entry.getValue();
					for(int i=0; i<dates.size(); i++){
						int col = entry.getKey();
						int row = MyUtil.getHourRow(cal, dates.get(i));
						
						if(!dates.isEmpty()){
							table.setValueAt(DISPONIBILE, row, col);
						}
					}
				}
			}
			else{
				orariSettimanali = new HashMap<Integer, ArrayList<Date>>();
			}
		}
	}
}
