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
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

public class VisualizzaVisite extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected int selected_index;
	File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblElencoVisite;
	private JComboBox<String> cb_medico;
	private JLabel lblMedico;
	private JLabel lblPaziente;
	private JComboBox<String> cb_paziente;
	private JLabel lblData;
	private JDateChooser dateChooser;
	private JLabel lblTipo;
	private JComboBox<String> cb_tipo;
	private JSeparator separator;
	private JSeparator separator_1;
	private JButton btnNewButton;
	private JButton btnFiltra;
	private JButton btnAzzeraFiltri;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaVisite frame = new VisualizzaVisite();
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
	public VisualizzaVisite() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 830, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{20, 49, 306, 39, 88, 212, 31, 46, 153, 20, 0};
		gbl_contentPane.rowHeights = new int[]{21, 0, 32, 0, 0, 474, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblElencoVisite = new JLabel("Elenco Visite");
		lblElencoVisite.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblElencoVisite.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblElencoVisite = new GridBagConstraints();
		gbc_lblElencoVisite.gridwidth = 7;
		gbc_lblElencoVisite.anchor = GridBagConstraints.NORTH;
		gbc_lblElencoVisite.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblElencoVisite.insets = new Insets(0, 0, 5, 5);
		gbc_lblElencoVisite.gridx = 2;
		gbc_lblElencoVisite.gridy = 0;
		contentPane.add(lblElencoVisite, gbc_lblElencoVisite);
		
		// Header Tabella
		String col[] = {"Data/Ora","Medico","Paziente","Stato","Tipo","Motivo"};
		model = new DefaultTableModel(col, 0);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 8;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		lblMedico = new JLabel("Medico");
		GridBagConstraints gbc_lblMedico = new GridBagConstraints();
		gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedico.anchor = GridBagConstraints.EAST;
		gbc_lblMedico.gridx = 1;
		gbc_lblMedico.gridy = 2;
		contentPane.add(lblMedico, gbc_lblMedico);
		
		cb_medico = new JComboBox<String>();
		GridBagConstraints gbc_cb_medico = new GridBagConstraints();
		gbc_cb_medico.insets = new Insets(0, 0, 5, 5);
		gbc_cb_medico.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_medico.gridx = 2;
		gbc_cb_medico.gridy = 2;
		contentPane.add(cb_medico, gbc_cb_medico);
		
		cb_medico.addItem("- Seleziona Medico");
		// Caricamento Medici
		if(f_medici.exists()){
			ElencoMedici medici = null;
			medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
			
			for(Medico m : medici.elencoMedici)
				cb_medico.addItem(m.getAlbo() + " - " +m.getCognome()+ " ("+m.getTipologia()+")");
		}
		
		lblData = new JLabel("Data");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.EAST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 4;
		gbc_lblData.gridy = 2;
		contentPane.add(lblData, gbc_lblData);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 5;
		gbc_dateChooser.gridy = 2;
		contentPane.add(dateChooser, gbc_dateChooser);
		
		btnFiltra = new JButton("Filtra");
		btnFiltra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadTable();
			}
		});
		GridBagConstraints gbc_btnFiltra = new GridBagConstraints();
		gbc_btnFiltra.insets = new Insets(0, 0, 5, 5);
		gbc_btnFiltra.gridx = 8;
		gbc_btnFiltra.gridy = 2;
		contentPane.add(btnFiltra, gbc_btnFiltra);
		
		lblPaziente = new JLabel("Paziente");
		GridBagConstraints gbc_lblPaziente = new GridBagConstraints();
		gbc_lblPaziente.anchor = GridBagConstraints.EAST;
		gbc_lblPaziente.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaziente.gridx = 1;
		gbc_lblPaziente.gridy = 3;
		contentPane.add(lblPaziente, gbc_lblPaziente);
		
		cb_paziente = new JComboBox<String>();
		GridBagConstraints gbc_cb_paziente = new GridBagConstraints();
		gbc_cb_paziente.insets = new Insets(0, 0, 5, 5);
		gbc_cb_paziente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_paziente.gridx = 2;
		gbc_cb_paziente.gridy = 3;
		contentPane.add(cb_paziente, gbc_cb_paziente);
		
		cb_paziente.addItem("- Seleziona Paziente");
		// Caricamento Pazienti
		if(f_utenti.exists()){
			ElencoUtenti utenti = null;
			utenti = (ElencoUtenti) MyFile.loadObject(f_medici, ClinicaMain.UTENTI_FILENAME);
			for(Utente u : utenti.elencoUtenti)
				cb_paziente.addItem(u.getNome() + " " +u.getCognome()+ " "+ " - "+u.getCodFiscale());
		}
		
		lblTipo = new JLabel("Tipo");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 4;
		gbc_lblTipo.gridy = 3;
		contentPane.add(lblTipo, gbc_lblTipo);
		
		cb_tipo = new JComboBox<String>();
		GridBagConstraints gbc_cb_tipo = new GridBagConstraints();
		gbc_cb_tipo.insets = new Insets(0, 0, 5, 5);
		gbc_cb_tipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_tipo.gridx = 5;
		gbc_cb_tipo.gridy = 3;
		contentPane.add(cb_tipo, gbc_cb_tipo);
		
		// Aggiungi Tipologie
		cb_tipo.addItem("- Seleziona Tipo");
		cb_tipo.addItem(ClinicaMain.GENERICA);
		cb_tipo.addItem(ClinicaMain.SPECIALISTICA);
		
		btnAzzeraFiltri = new JButton("Azzera Filtri");
		btnAzzeraFiltri.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_medico.setSelectedIndex(0);
				cb_paziente.setSelectedIndex(0);
				dateChooser.setDate(null);
				cb_tipo.setSelectedIndex(0);
				loadTable();
			}
		});
		GridBagConstraints gbc_btnAzzeraFiltri = new GridBagConstraints();
		gbc_btnAzzeraFiltri.insets = new Insets(0, 0, 5, 5);
		gbc_btnAzzeraFiltri.gridx = 8;
		gbc_btnAzzeraFiltri.gridy = 3;
		contentPane.add(btnAzzeraFiltri, gbc_btnAzzeraFiltri);
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 4;
		contentPane.add(separator_1, gbc_separator_1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		btnNewButton = new JButton("Inserisci Referto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				
				// Riga Selezionata
				if(row == -1)
					JOptionPane.showMessageDialog(contentPane, "Nessuna visita selezionata!", "Errore", JOptionPane.WARNING_MESSAGE);
				else if(table.getValueAt(row, col) == null || table.getValueAt(row, 3).equals(ClinicaMain.PRENOTATA))
					JOptionPane.showMessageDialog(contentPane, "Visita ancora in attesa!", "Errore", JOptionPane.WARNING_MESSAGE);
				else if(table.getValueAt(row, col) == null || table.getValueAt(row, 3).equals(ClinicaMain.ARCHIVIATA))
					JOptionPane.showMessageDialog(contentPane, "Visita gi� archiviata!", "Errore", JOptionPane.WARNING_MESSAGE);
				else{
					ElencoVisite visite = null;
					visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
					
					Date selected_date = MyUtil.splitDateTime(table.getValueAt(row, 0).toString());
					
					NuovoReferto referto = null;
					for(Visita v : visite.elencoVisite){	
						if(v.getData().equals(selected_date)){
							referto = new NuovoReferto(v);
							referto.setVisible(true);
						}
					}
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.gridwidth = 7;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		// Nessuna Visita in Archivio
		ElencoVisite visite = null;
		visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
		if(visite == null || visite.elencoVisite.size() == 0){
			JOptionPane.showMessageDialog(contentPane, "Nessuna visita presente nei nostri archivi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
		}
		else{
			loadTable();
		}
		
		// Table Refresh
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				loadTable();
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}
	
	/**
	 * Load Table
	 */
	public void loadTable(){
		checkVisite();
		removeAllRows(model);
		int cb_medico_index = cb_medico.getSelectedIndex();
		int cb_paziente_index = cb_paziente.getSelectedIndex();
		int cb_tipo_index = cb_tipo.getSelectedIndex();
		Date date_initial = dateChooser.getDate();
		
		if(f_visite.exists()){
			ElencoVisite visite = null;
			visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
			
			String codAlbo  = "";
			String codFiscale = "";
			
			System.out.println("Combobox: "+cb_medico_index + cb_paziente_index + cb_tipo_index);
			System.out.println("Dimensione Lista: "+visite.elencoVisite.size());
			
			for(Visita v : visite.elencoVisite){
				int index = 0;
				System.out.println(index + " Medico: "+v.getMedico().getAlbo() + " Paziente: "+v.getPaziente().getCodFiscale()+ "  - "+v.getData() + " "+v.getStato());
				index++;
			}
			
			for(Visita v : visite.elencoVisite){
				String data_visita = null;
					
				// Annulla filtro medico
				if(cb_medico.getSelectedIndex() == 0 ){
					for(int i=0; i<cb_medico.getItemCount(); i++){
						// Get Codice Albo From Combobox
						String med = (String) cb_medico.getItemAt(i);
						codAlbo= med.substring(0, med.indexOf(" ")); 
						if(codAlbo.equals(v.getMedico().getAlbo()))
							cb_medico.setSelectedIndex(i);
					}
				}
				
				// Annulla filtro paziente
				if(cb_paziente.getSelectedIndex() == 0){
					for(int i=0; i<cb_paziente.getItemCount(); i++){ 
						// Get Codice Fiscale From Combobox
						int indexOfDash = cb_paziente.getItemAt(i).toString().indexOf('-');
						codFiscale = cb_paziente.getItemAt(i).toString().substring(indexOfDash +1);
						codFiscale = codFiscale.replaceAll("\\s+","");
						
						if(codFiscale.equals(v.getPaziente().getCodFiscale()))
							cb_paziente.setSelectedIndex(i);
					}
				}
				
				// Annulla filtro tipo
				if(cb_tipo.getSelectedIndex() == 0){
					for(int i=0; i<cb_tipo.getItemCount(); i++){
						if(cb_tipo.getItemAt(i).toString().equals(v.getTipo()))
							cb_tipo.setSelectedIndex(i);
					}
				}
				
				// Annulla filtro data
				if(dateChooser.getDate() == null){
					dateChooser.setDate(v.getData());
				}
				
				// Get Selected Codice Albo From Combobox
				String med1 = (String) cb_medico.getSelectedItem();
				codAlbo = med1.substring(0, med1.indexOf(" "));
				
				// Get Selected Codice Fiscale From Combobox
				int indexOfDash = cb_paziente.getSelectedItem().toString().indexOf('-');
				codFiscale = cb_paziente.getSelectedItem().toString().substring(indexOfDash +1);
				codFiscale = codFiscale.replaceAll("\\s+","");

				// Filter
				if(v.getMedico().getAlbo().equals(codAlbo)
						&& v.getPaziente().getCodFiscale().equals(codFiscale)
						&& MyUtil.compareDateOnly(dateChooser.getDate(), v.getData())
						&& v.getTipo().equals(cb_tipo.getSelectedItem().toString())){
						
						data_visita = MyUtil.timeDateFormat(v.getData()) + " "+ MyUtil.timeIntervalFormat(v.getData());
						String medico = v.getMedico().getCognome();
						String paziente = v.getPaziente().getCognome();
						String stato = v.getStato();
						String tipo = v.getTipo();
						String motivo = v.getDescrizione();
						Object data[] = {data_visita, medico, paziente, stato, tipo, motivo};
						model.addRow(data);
				}
				
				if(cb_medico_index == 0 && cb_paziente_index == 0 && cb_tipo_index == 0 && date_initial == null){
					cb_medico.setSelectedIndex(0);
					cb_paziente.setSelectedIndex(0);
					cb_tipo.setSelectedIndex(0);
					dateChooser.setDate(null);
				}
				else{
					cb_medico.setSelectedIndex(cb_medico_index);
					cb_paziente.setSelectedIndex(cb_paziente_index);
					cb_tipo.setSelectedIndex(cb_tipo_index);
					dateChooser.setDate(date_initial);
				}
			}
			
		}
		
	}
	
	/**
	 * Controllo Completamento Visite
	 */
	public void checkVisite(){
		Date current_date = MyUtil.getCurrentTime();
		ElencoVisite visite = null;
		if(f_visite.exists()){
			visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
			for(Visita v : visite.elencoVisite){
				if(v.getData().before(current_date)){
					v.setStato(ClinicaMain.CONCLUSA);
					MyFile.saveObject(f_visite, visite, ClinicaMain.VISITE_FILENAME);
				}
			}
		}
	}
	
	/**
	 * Remove all Rows from table
	 * @param model table model
	 */
	public void removeAllRows(DefaultTableModel model){
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		   model.removeRow(i); 
	}

}
