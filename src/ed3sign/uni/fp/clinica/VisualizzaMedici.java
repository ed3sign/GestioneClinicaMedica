package ed3sign.uni.fp.clinica;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.toedter.calendar.JDateChooser;

import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

/**
 * Classe VisualizzaMedici
 * Classe di visualizzazione dell'elenco medici, responsabile
 * anche dell'aggiornamento (modifica del dati) e eliminazione.
 * 
 * @author ed3sign
 *
 */
public class VisualizzaMedici extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	protected JPanel contentPane;
	protected JTextField tf_nome;
	protected JTextField tf_cognome;
	protected JDateChooser dateChooser;
	protected JSplitPane splitPane;
	File f_medici = new File(ClinicaMain.MEDICI_FILENAME);
	File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaMedici frame = new VisualizzaMedici();
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
	public VisualizzaMedici() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{806, 0};
		gbl_contentPane.rowHeights = new int[]{45, 205, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		/**
		 * Titolo Principale
		 */
		JLabel lblElencoUtenti = new JLabel("Elenco Medici");
		lblElencoUtenti.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblElencoUtenti = new GridBagConstraints();
		gbc_lblElencoUtenti.insets = new Insets(0, 0, 5, 0);
		gbc_lblElencoUtenti.gridx = 0;
		gbc_lblElencoUtenti.gridy = 0;
		contentPane.add(lblElencoUtenti, gbc_lblElencoUtenti);
		splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		contentPane.add(splitPane, gbc_splitPane);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 15, 10, 15));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("1px"),
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("97px"),
				ColumnSpec.decode("35px"),
				ColumnSpec.decode("157px"),},
			new RowSpec[] {
				RowSpec.decode("1px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.UNRELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("14px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("33px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("46px"),
				RowSpec.decode("218px"),
				RowSpec.decode("23px"),}));
		
		JPanel container_AggiungiUtenti = new JPanel();
		panel.add(container_AggiungiUtenti, "1, 1, center, center");
		
		// Aggiungi Utenti
		JButton btnAggiungiUtenti = new JButton("Aggiungi Medico");
		container_AggiungiUtenti.add(btnAggiungiUtenti);
		btnAggiungiUtenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuovoMedico nuovoMedico = null;
				try {
					nuovoMedico = new NuovoMedico();
				} catch (IOException e) {
					e.printStackTrace();
				}
				nuovoMedico.setVisible(true);
			}
		});
		JLabel label_2 = new JLabel("Nome");
		panel.add(label_2, "3, 3, left, center");
		
		tf_nome = new JTextField();
		tf_nome.setColumns(20);
		panel.add(tf_nome, "5, 3, fill, center");
		
		JLabel label_3 = new JLabel("Cognome");
		panel.add(label_3, "3, 5, left, center");
		
		tf_cognome = new JTextField();
		tf_cognome.setColumns(20);
		panel.add(tf_cognome, "5, 5, fill, center");
		
		JLabel lblDataDiNascita = new JLabel("Telefono");
		panel.add(lblDataDiNascita, "3, 7, left, center");
		
		JFormattedTextField tf_telefono = new JFormattedTextField();
		panel.add(tf_telefono, "5, 7, fill, center");
		
		JLabel lblLuogoDiNascita = new JLabel("Tipologia");
		panel.add(lblLuogoDiNascita, "3, 9, left, center");
				
		JComboBox<String> cb_tipo = new JComboBox<String>();
		cb_tipo.addItem("Generico");
		cb_tipo.addItem("Specialista");
		panel.add(cb_tipo, "5, 9, fill, center");
		
		JLabel lblAreaDiCompetenza = new JLabel("Area di Competenza");
		panel.add(lblAreaDiCompetenza, "3, 11, left, center");
		
		JPanel tipo_panel = new JPanel();
		panel.add(tipo_panel, "3, 13, 3, 3");
		tipo_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCheckBox checkBox_1 = new JCheckBox("Chirurgia");
		tipo_panel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Gastroenterologia");
		tipo_panel.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Ortopedia");
		tipo_panel.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("Nefrologia");
		tipo_panel.add(checkBox_4);
		
		JCheckBox checkBox = new JCheckBox("Cardiologia");
		tipo_panel.add(checkBox);

		
		// Modifica Record
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() >= 0){
					// Ottieni Lista Utenti
					ElencoMedici medici = null;
					medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
			
					int selected_index =  (int) ((table.getValueAt(table.getSelectedRow(), 0)));
					selected_index--;
					medici.elencoMedici.get(selected_index).setNome(tf_nome.getText());
					medici.elencoMedici.get(selected_index).setCognome(tf_cognome.getText());
					medici.elencoMedici.get(selected_index).setTelefono(tf_telefono.getText());
					medici.elencoMedici.get(selected_index).setTipologia(cb_tipo.getSelectedItem().toString());
					
					ArrayList<String> competenze = new ArrayList<String>();
					// Selezione Competenze
					for(Component component : tipo_panel.getComponents()) {
					  if(component instanceof JCheckBox) {
					    if(((JCheckBox)component).isSelected()){
					    	competenze.add(((JCheckBox) component).getText());
					    }
					  }
					}
					medici.elencoMedici.get(selected_index).setCompetenze(competenze);
					
					// Salvataggio Modifica
					MyFile.saveObject(f_medici, medici, ClinicaMain.MEDICI_FILENAME);
					
					// Refresh Tabella
					table.setValueAt(tf_nome.getText(), selected_index , 1);
					table.setValueAt(tf_cognome.getText(), selected_index , 2);
					table.setValueAt(tf_telefono.getText(), selected_index, 3);
					table.setValueAt(cb_tipo.getSelectedItem(), selected_index, 4);
					table.setValueAt(MyUtil.arrayListToString(competenze), selected_index, 5);
				}
			}
		});
		panel.add(btnModifica, "5, 17, right, center");
		
		
		// Elimina Records
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow() >= 0){
					// Ottieni Lista Utenti
					ElencoMedici medici = null;
					medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
					
					// Get Selected Index
					int selected_index =  (int) ((table.getValueAt(table.getSelectedRow(), 0)));
					selected_index--;
					table.getSelectionModel().clearSelection();
					
					// Rimuovi Visite Associate all'utente
					ElencoVisite visite = null;
					visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
					for(int i=0; i<visite.elencoVisite.size(); i++){
						if(visite.elencoVisite.get(i).getPaziente().equals(medici.elencoMedici.get(selected_index)))
								visite.elencoVisite.remove(i);
					}
					
					// Rimuovi Utente			
					System.out.println("Eliminato "+medici.elencoMedici.get(selected_index).getNome()+" a riga: "+selected_index);
					medici.elencoMedici.remove(selected_index);
					((DefaultTableModel)table.getModel()).removeRow(selected_index);
					
					// Azzera Campi di Modifica
					InterfaceHelpers.cls(panel);
					
					// Disabilita Pulsanti Modifica e Annulla
					if(table.getRowCount()== 0){
						btnModifica.setEnabled(false);
						btnElimina.setEnabled(false);
					}
					// Salvataggio Modifica
					MyFile.saveObject(f_medici, medici, ClinicaMain.MEDICI_FILENAME);
				}
			}
		});
		panel.add(btnElimina, "3, 17, center, center");
		
		/**
		 * Right Components
		 */
		JScrollPane scrollpane = new JScrollPane();
		splitPane.setRightComponent(scrollpane);
	
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollpane.setSize(getMaximumSize());
		scrollpane.setViewportView(table);
		
		// Header Tabella
		String col[] = {"#","Nome","Cognome","Telefono", "Tipo", "Area di Competenza"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		
		
		// Nessun Utente in Archivio
		ElencoMedici medici = null;
		medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
		if(medici == null || medici.elencoMedici.size() == 0){
			JOptionPane.showMessageDialog(contentPane, "Nessun Medico presente nei nostri archivi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
			btnModifica.setEnabled(false);
			btnElimina.setEnabled(false);
		}
		else{
			printTable(model);
		}
		
		// Table Selected Row Event
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if(table.getSelectedRowCount() > 0){
	        		int index = table.getSelectedRow();
		        	tf_nome.setText((table.getValueAt(table.getSelectedRow(), 1).toString()));
			        tf_cognome.setText((table.getValueAt(table.getSelectedRow(), 2).toString()));
			        tf_telefono.setText((table.getValueAt(table.getSelectedRow(), 3).toString()));
			        cb_tipo.setSelectedItem((table.getValueAt(table.getSelectedRow(), 4).toString()));
			        
			        ElencoMedici medici = null;
					medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
			        ArrayList<String>competenze = null;
			        competenze = medici.elencoMedici.get(index).getCompetenze();
			        
			        // Selezione Competenze
					for(Component component : tipo_panel.getComponents()) {
					  if(component instanceof JCheckBox) {
				    	for(int i=0; i<competenze.size(); i++){
				        	if(competenze.get(i).equals(((JCheckBox) component).getText()))
				        		((JCheckBox) component).setSelected(true);
				        }
					  }
					}
	        	}
	        }
	    });
		
		// Table Refresh
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				printTable(model);
				
				// Abilita Pulsanti Modifica e Annulla
				if(table.getRowCount() > 0){
					btnModifica.setEnabled(true);
					btnElimina.setEnabled(true);
				}
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}
	
	/**
	 * Print Table
	 * @param model DefaultTableModel
	 */
	public void printTable(DefaultTableModel model){
		if(f_medici.exists()){
			ElencoMedici medici = null;
			medici = (ElencoMedici) MyFile.loadObject(f_medici, ClinicaMain.MEDICI_FILENAME);
		
			removeAllRows(model);
		
			int index = 1;
			for(Medico m : medici.elencoMedici){
				String nome = m.getNome();
				String cognome = m.getCognome();
				String tel = m.getTelefono();
				String tipo = m.getTipologia();
				String area = MyUtil.arrayListToString(m.getCompetenze());
	
				Object data[] = {index, nome, cognome, tel, tipo, area};
				model.addRow(data);
				index++;
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
	
	public void SetData(Object obj, int row_index, int col_index){
		table.getModel().setValueAt(obj,row_index,col_index);
		System.out.println("Value is added");
	}

}
