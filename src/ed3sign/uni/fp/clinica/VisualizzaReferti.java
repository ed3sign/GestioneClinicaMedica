package ed3sign.uni.fp.clinica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Date;

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

public class VisualizzaReferti extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected int selected_index;
	File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	File f_utenti = new File(ClinicaMain.UTENTI_FILENAME);
	File f_referti = new File(ClinicaMain.REFERTI_FILENAME);
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private JLabel lblElencoRapporti;
	private JComboBox<String> cb_medico;
	private JLabel lblMedico;
	private JLabel lblPaziente;
	private JLabel lblData;
	private JDateChooser dateChooser;
	private JSeparator separator;
	private JComboBox<String> cb_paziente;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaReferti frame = new VisualizzaReferti();
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
	public VisualizzaReferti() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 830, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{67, 203, 70, 135, 47, 213, 0};
		gbl_contentPane.rowHeights = new int[]{21, 0, 32, 0, 474, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblElencoRapporti = new JLabel("Elenco Referti");
		lblElencoRapporti.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblElencoRapporti.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblElencoRapporti = new GridBagConstraints();
		gbc_lblElencoRapporti.gridwidth = 6;
		gbc_lblElencoRapporti.anchor = GridBagConstraints.NORTH;
		gbc_lblElencoRapporti.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblElencoRapporti.insets = new Insets(0, 0, 5, 0);
		gbc_lblElencoRapporti.gridx = 0;
		gbc_lblElencoRapporti.gridy = 0;
		contentPane.add(lblElencoRapporti, gbc_lblElencoRapporti);
		
		// Header Tabella
		String col[] = {"Data/Ora","Medico","Paziente","Rapporto","Prescrizione"};
		model = new DefaultTableModel(col, 0);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 6;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		contentPane.add(separator, gbc_separator);
		
		lblMedico = new JLabel("Medico");
		lblMedico.setHorizontalAlignment(SwingConstants.TRAILING);
		GridBagConstraints gbc_lblMedico = new GridBagConstraints();
		gbc_lblMedico.anchor = GridBagConstraints.EAST;
		gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedico.gridx = 0;
		gbc_lblMedico.gridy = 2;
		contentPane.add(lblMedico, gbc_lblMedico);
		
		cb_medico = new JComboBox<String>();
		GridBagConstraints gbc_cb_medico = new GridBagConstraints();
		gbc_cb_medico.insets = new Insets(0, 0, 5, 5);
		gbc_cb_medico.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_medico.gridx = 1;
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
		
		// Filtro Medico
		cb_medico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(table != null){
					loadTable();
				}
			}
		});
		
		lblPaziente = new JLabel("Paziente");
		GridBagConstraints gbc_lblPaziente = new GridBagConstraints();
		gbc_lblPaziente.anchor = GridBagConstraints.EAST;
		gbc_lblPaziente.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaziente.gridx = 2;
		gbc_lblPaziente.gridy = 2;
		contentPane.add(lblPaziente, gbc_lblPaziente);
		
		cb_paziente = new JComboBox<String>();
		GridBagConstraints gbc_cb_paziente = new GridBagConstraints();
		gbc_cb_paziente.insets = new Insets(0, 0, 5, 5);
		gbc_cb_paziente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cb_paziente.gridx = 3;
		gbc_cb_paziente.gridy = 2;
		contentPane.add(cb_paziente, gbc_cb_paziente);
		
		lblData = new JLabel("Data");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.EAST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 4;
		gbc_lblData.gridy = 2;
		contentPane.add(lblData, gbc_lblData);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 5;
		gbc_dateChooser.gridy = 2;
		contentPane.add(dateChooser, gbc_dateChooser);
		
		// Detect Date Change
		dateChooser.getDateEditor().addPropertyChangeListener(
	    new PropertyChangeListener() {
	        @Override
	        public void propertyChange(PropertyChangeEvent e) {
	            if ("date".equals(e.getPropertyName())) {
	                loadTable();
	            }
	        }
	    });
		// Caricamento Pazienti
		if(f_utenti.exists()){
			ElencoUtenti utenti = null;
			utenti = (ElencoUtenti) MyFile.loadObject(f_medici, ClinicaMain.UTENTI_FILENAME);
			for(Utente u : utenti.elencoUtenti)
				cb_paziente.addItem(u.getNome() + " " +u.getCognome()+ " "+ " - "+u.getCodFiscale());
		}
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 4;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		// Nessuna Visita in Archivio
		ElencoReferti referti = null;
		referti = (ElencoReferti) MyFile.loadObject(f_referti, ClinicaMain.REFERTI_FILENAME);
		if(referti == null || referti.elencoReferti.size() == 0){
			JOptionPane.showMessageDialog(contentPane, "Nessun referto presente nei nostri archivi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
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
		
		if(f_referti.exists()){
			ElencoReferti referti = null;
			referti = (ElencoReferti) MyFile.loadObject(f_visite, ClinicaMain.REFERTI_FILENAME);
			
			String codAlbo  = "";
			String codFiscale = "";
			
			for(Referto r : referti.elencoReferti){
				String data_visita = null;
				
				
				// Annulla filtro medico
				if(cb_medico.getSelectedIndex() == 0 ){
					for(int i=0; i<cb_medico.getItemCount(); i++){
						// Get Codice Albo From Combobox
						String med = cb_medico.getItemAt(i);
						codAlbo= med.substring(0, med.indexOf(" ")); 
						if(codAlbo.equals(r.getVisita().getMedico().getAlbo()))
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
						
						if(codFiscale.equals(r.getVisita().getPaziente().getCodFiscale()))
							cb_paziente.setSelectedIndex(i);
					}
				}
				
				
				// Annulla filtro data
				if(dateChooser.getDate() == null)
					dateChooser.setDate(r.getVisita().getData());
				
				// Get Selected Codice Albo From Combobox
				String med1 = (String) cb_medico.getSelectedItem();
				codAlbo = med1.substring(0, med1.indexOf(" "));
				
				// Get Selected Codice Fiscale From Combobox
				int indexOfDash = cb_paziente.getSelectedItem().toString().indexOf('-');
				codFiscale = cb_paziente.getSelectedItem().toString().substring(indexOfDash +1);
				codFiscale = codFiscale.replaceAll("\\s+","");

				// Filter
				if(r.getVisita().getMedico().getAlbo().equals(codAlbo)
						&& r.getVisita().getPaziente().getCodFiscale().equals(codFiscale)
						&& r.getVisita().getData().equals(dateChooser.getDate())){
						
						data_visita = MyUtil.timeDateFormat(r.getVisita().getData()) + " "+ MyUtil.timeIntervalFormat(r.getVisita().getData());
						String medico = r.getVisita().getMedico().getCognome();
						String paziente = r.getVisita().getPaziente().getCognome();
						String rapporto = r.getRapporto();
						String prescrizione = r.getPrescrizione();
						Object data[] = {data_visita, medico, paziente, rapporto, prescrizione};
						model.addRow(data);
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
				if(v.getData().before(current_date))
					v.setStato(ClinicaMain.CONCLUSA);
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
