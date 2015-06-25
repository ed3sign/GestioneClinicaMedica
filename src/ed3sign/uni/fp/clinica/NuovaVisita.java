package ed3sign.uni.fp.clinica;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.toedter.calendar.JDateChooser;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NuovaVisita extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Calendar cal = Calendar.getInstance();
	private JTable table;
	private File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	private HashMap<Integer, ArrayList<Date>> orariSettimanali;
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
		
		JDateChooser dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 3;
		contentPane.add(dateChooser, gbc_dateChooser);
		
		JButton btnNewButton = new JButton("Filtra");
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
					clearRowContent(table);
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
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
		});
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setRowSelectionAllowed(false);
		table.setCellSelectionEnabled(true);
		table.setCellEditor(null);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Header Tabella
		String col[] = {"","Lun","Mar","Mer", "Gio", "Ven"};
		model = new DefaultTableModel(col, 0);
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
	}
	
	/**
	 * Print Week Days
	 * @param model table model
	 */
	public void printDayHours(DefaultTableModel model){
		for(int i=0; i<=OrariSettimanali.TIME_SLOTS; i++){
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
			// Medico Selezionato
			String med = (String) cb_medico.getSelectedItem();
			String codAlbo= med.substring(0, med.indexOf(" ")); 
			ElencoMedici medici = null;
			medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
			
			for(Medico m : medici.elencoMedici)
				if(m.getAlbo().equals(codAlbo))
					orariSettimanali = m.getOrariSettimanali();
			
			if(orariSettimanali != null){
				for (Entry <Integer, ArrayList<Date>> entry : orariSettimanali.entrySet()) {
					ArrayList<Date> dates = entry.getValue();
					for(int i=0; i<dates.size(); i++){
						int col = entry.getKey();
						int row = MyUtil.getHourRows(cal, dates.get(i));
						
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
	}
	
	/**
	 * Remove all Rows from table
	 * @param model table model
	 */
	public void clearRowContent(JTable table){
		int rows = table.getRowCount();
		int cols = table.getColumnCount();
		System.out.println(rows + cols);
		for(int i = 0; i<rows; i++)
			for(int j=1; j<cols; j++)
				table.setValueAt("", i, j);
	}
}
