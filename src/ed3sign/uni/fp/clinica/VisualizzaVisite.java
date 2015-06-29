package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.Color;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;

import com.toedter.calendar.JDateChooser;

import javax.swing.JSeparator;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	private Button button;
	
	

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
		gbl_contentPane.columnWidths = new int[]{20, 49, 306, 39, 76, 202, 127, 46, 153, 0};
		gbl_contentPane.rowHeights = new int[]{21, 0, 32, 0, 0, 474, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblElencoVisite = new JLabel("Elenco Visite");
		lblElencoVisite.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblElencoVisite.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblElencoVisite = new GridBagConstraints();
		gbc_lblElencoVisite.gridwidth = 7;
		gbc_lblElencoVisite.anchor = GridBagConstraints.NORTH;
		gbc_lblElencoVisite.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblElencoVisite.insets = new Insets(0, 0, 5, 0);
		gbc_lblElencoVisite.gridx = 2;
		gbc_lblElencoVisite.gridy = 0;
		contentPane.add(lblElencoVisite, gbc_lblElencoVisite);
		
		// Header Tabella
		String col[] = {"Data/Ora","Medico","Paziente","Stato","Tipo","Motivo"};
		model = new DefaultTableModel(col, 0);
		
		separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 8;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
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
		
		// Filtro Medico
		cb_medico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(table != null){
					loadTable();
				}
			}
		});
		
		
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
		
		button = new Button("Azzera Filtri");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cb_medico.setSelectedIndex(0);
				cb_paziente.setSelectedIndex(0);
				dateChooser.setDate(null);
				cb_tipo.setSelectedIndex(0);
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridheight = 2;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 8;
		gbc_button.gridy = 2;
		contentPane.add(button, gbc_button);
		
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
				cb_paziente.addItem(u.getNome() + " " +u.getCognome()+ " "+ " ("+u.getCodFiscale());
		}
		
		// Filtro Pazienti
		cb_paziente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(table != null){
					loadTable();
				}
			}
		});
		
		
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
		
		// Filtro Tipo
		cb_tipo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(table != null){
					loadTable();
				}
			}
		});
		
		separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 3;
		gbc_separator_1.gridy = 4;
		contentPane.add(separator_1, gbc_separator_1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 8;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		table.setModel(model);
		
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
		removeAllRows(model);
		if(f_visite.exists()){
			ElencoVisite visite = null;
			visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
			
			for(Visita v : visite.elencoVisite){
				String data_visita = null;
				
				if(cb_medico.getSelectedIndex() == 0 )
					cb_medico.setSelectedItem(v.getMedico());
			
				if(cb_paziente.getSelectedIndex() == 0)
					cb_paziente.setSelectedItem(v.getPaziente());
				
				if(cb_tipo.getSelectedIndex() == 0)
					cb_tipo.setSelectedItem(v.getTipo());
				
				if(dateChooser.getDate() == null)
					dateChooser.setDate(v.getData());
				
				cb_medico.setSelectedItem(v.getMedico().getCognome());
				System.out.println("Combobox: "+cb_medico.getSelectedItem());
				System.out.println("Medico: "+v.getMedico());
				
				if(v.getMedico().equals(cb_medico.getSelectedItem()) && 
						v.getPaziente().equals(cb_paziente.getSelectedItem()) && 
						v.getData().equals(dateChooser.getDate()) && 
						v.getTipo().equals(cb_tipo.getSelectedItem())){
					
							data_visita = MyUtil.timeDateFormat(v.getData()) + " "+ MyUtil.timeIntervalFormat(v.getData());
							String medico = v.getMedico().getCognome();
							String paziente = v.getPaziente().getCognome();
							String stato = v.getStato();
							String tipo = v.getTipo();
							String motivo = v.getDescrizione();
							Object data[] = {data_visita, medico, paziente, stato, tipo, motivo};
							model.addRow(data);
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
