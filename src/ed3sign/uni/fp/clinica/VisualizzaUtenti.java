package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.JTable;

import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JSplitPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ListSelectionModel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;

public class VisualizzaUtenti extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel panel;
	protected JPanel contentPane;
	protected JTextField tf_nome;
	protected JTextField tf_cognome;
	protected JDateChooser dateChooser;
	protected JTextField tf_luogo;
	protected JFormattedTextField tf_telefono;
	protected JSplitPane splitPane;
	protected int selected_index;
	File f_users = new File(ClinicaMain.UTENTI_FILENAME);
	JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizzaUtenti frame = new VisualizzaUtenti();
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
	public VisualizzaUtenti() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 830, 599);
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
		JLabel lblElencoUtenti = new JLabel("Elenco Utenti");
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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{78, 16, 144, 0};
		gbl_panel.rowHeights = new int[]{45, 16, 0, 35, 0, 16, 0, 0, 0, 77, 28, 28, 28, 28, 27, 28, 0, 0, 35, 0, 0, 0, 29, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel container_AggiungiUtenti = new JPanel();
		GridBagConstraints gbc_container_AggiungiUtenti = new GridBagConstraints();
		gbc_container_AggiungiUtenti.gridwidth = 3;
		gbc_container_AggiungiUtenti.insets = new Insets(0, 0, 5, 0);
		gbc_container_AggiungiUtenti.gridx = 0;
		gbc_container_AggiungiUtenti.gridy = 2;
		panel.add(container_AggiungiUtenti, gbc_container_AggiungiUtenti);
		
		// Aggiungi Utenti
		JButton btnAggiungiUtenti = new JButton("Aggiungi Utente");
		container_AggiungiUtenti.add(btnAggiungiUtenti);
		btnAggiungiUtenti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NuovoUtente nuovoUtente = null;
				try {
					nuovoUtente = new NuovoUtente();
				} catch (IOException e) {
					e.printStackTrace();
				}
				nuovoUtente.setVisible(true);
			}
		});
		JLabel label_2 = new JLabel("Nome");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 3;
		panel.add(label_2, gbc_label_2);
		
		tf_nome = new JTextField();
		tf_nome.setColumns(20);
		GridBagConstraints gbc_tf_nome = new GridBagConstraints();
		gbc_tf_nome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_nome.insets = new Insets(0, 0, 5, 0);
		gbc_tf_nome.gridx = 2;
		gbc_tf_nome.gridy = 3;
		panel.add(tf_nome, gbc_tf_nome);
		
		JLabel label_3 = new JLabel("Cognome");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 4;
		panel.add(label_3, gbc_label_3);
		
		tf_cognome = new JTextField();
		tf_cognome.setColumns(20);
		GridBagConstraints gbc_tf_cognome = new GridBagConstraints();
		gbc_tf_cognome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_cognome.insets = new Insets(0, 0, 5, 0);
		gbc_tf_cognome.gridx = 2;
		gbc_tf_cognome.gridy = 4;
		panel.add(tf_cognome, gbc_tf_cognome);
		
		JLabel lblDataDiNascita = new JLabel("Data di Nascita");
		GridBagConstraints gbc_lblDataDiNascita = new GridBagConstraints();
		gbc_lblDataDiNascita.anchor = GridBagConstraints.WEST;
		gbc_lblDataDiNascita.insets = new Insets(0, 0, 5, 5);
		gbc_lblDataDiNascita.gridx = 0;
		gbc_lblDataDiNascita.gridy = 5;
		panel.add(lblDataDiNascita, gbc_lblDataDiNascita);
		
		dateChooser = new JDateChooser();
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 0);
		gbc_dateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 5;
		panel.add(dateChooser, gbc_dateChooser);
		
		JLabel lblLuogoDiNascita = new JLabel("Luogo di Nascita");
		GridBagConstraints gbc_lblLuogoDiNascita = new GridBagConstraints();
		gbc_lblLuogoDiNascita.anchor = GridBagConstraints.WEST;
		gbc_lblLuogoDiNascita.insets = new Insets(0, 0, 5, 5);
		gbc_lblLuogoDiNascita.gridx = 0;
		gbc_lblLuogoDiNascita.gridy = 6;
		panel.add(lblLuogoDiNascita, gbc_lblLuogoDiNascita);
		
		tf_luogo = new JTextField();
		tf_luogo.setColumns(20);
		GridBagConstraints gbc_tf_luogo = new GridBagConstraints();
		gbc_tf_luogo.insets = new Insets(0, 0, 5, 0);
		gbc_tf_luogo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_luogo.gridx = 2;
		gbc_tf_luogo.gridy = 6;
		panel.add(tf_luogo, gbc_tf_luogo);
		
		JLabel label_6 = new JLabel("Telefono");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 7;
		panel.add(label_6, gbc_label_6);
		
		tf_telefono = new JFormattedTextField();
		GridBagConstraints gbc_tf_telefono = new GridBagConstraints();
		gbc_tf_telefono.fill = GridBagConstraints.HORIZONTAL;
		gbc_tf_telefono.insets = new Insets(0, 0, 5, 0);
		gbc_tf_telefono.gridx = 2;
		gbc_tf_telefono.gridy = 7;
		panel.add(tf_telefono, gbc_tf_telefono);
		
		// Modifica Record
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selected_index >= 0){
					// Ottieni Lista Utenti
					ElencoUtenti utenti = null;
					utenti = (ElencoUtenti) MyFile.loadObject(f_users, ClinicaMain.UTENTI_FILENAME);
			
					utenti.elencoUtenti.get(selected_index).setNome(tf_nome.getText());
					utenti.elencoUtenti.get(selected_index).setCognome(tf_cognome.getText());
					utenti.elencoUtenti.get(selected_index).setDataN(dateChooser.getDate());
					utenti.elencoUtenti.get(selected_index).setLuogoNascita(tf_luogo.getText());
					utenti.elencoUtenti.get(selected_index).setTelefono(tf_telefono.getText());
					
					// Salvataggio Modifica
					MyFile.saveObject(f_users, utenti, ClinicaMain.UTENTI_FILENAME);
					
					// Refresh Tabella
					table.setValueAt(tf_nome.getText(), selected_index , 1);
					table.setValueAt(tf_cognome.getText(), selected_index , 2);
					table.setValueAt(MyUtil.dateFormatter(dateChooser.getDate()), selected_index, 3);
					table.setValueAt(tf_luogo.getText(), selected_index, 4);
					table.setValueAt(tf_telefono.getText(), selected_index , 5);
				}
			}
		});

		GridBagConstraints gbc_btnModifica = new GridBagConstraints();
		gbc_btnModifica.anchor = GridBagConstraints.EAST;
		gbc_btnModifica.insets = new Insets(0, 0, 5, 0);
		gbc_btnModifica.gridx = 2;
		gbc_btnModifica.gridy = 18;
		panel.add(btnModifica, gbc_btnModifica);
		
		
		// Elimina Records
		JButton btnElimina = new JButton("Elimina");
		btnElimina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Ottieni Lista Utenti
				ElencoUtenti utenti = null;
				utenti = (ElencoUtenti) MyFile.loadObject(f_users, ClinicaMain.UTENTI_FILENAME);
				
				// Get Selected Index
				int selected_index =  (int) ((table.getValueAt(table.getSelectedRow(), 0)));
				selected_index--;
				table.getSelectionModel().clearSelection();
				
				// Rimuovi Utente
				System.out.println("Eliminato "+utenti.elencoUtenti.get(selected_index).getNome()+" a riga: "+selected_index);
				utenti.elencoUtenti.remove(selected_index);
				((DefaultTableModel)table.getModel()).removeRow(selected_index);
				
				// Azzera Campi di Modifica
				InterfaceHelpers.cls(panel);
				dateChooser.setDate(null);

				// Disabilita Pulsanti Modifica e Annulla
				if(table.getRowCount() > 0){
					btnModifica.setEnabled(false);
					btnElimina.setEnabled(false);
				}

				// Salvataggio Modifica
				MyFile.saveObject(f_users, utenti, ClinicaMain.UTENTI_FILENAME);
			}
		});
		GridBagConstraints gbc_btnElimina = new GridBagConstraints();
		gbc_btnElimina.insets = new Insets(0, 0, 5, 5);
		gbc_btnElimina.gridx = 0;
		gbc_btnElimina.gridy = 18;
		panel.add(btnElimina, gbc_btnElimina);
		
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
		String col[] = {"#","Nome","Cognome","Data di Nascita", "Luogo di Nascita", "Telefono"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		
		
		// Nessun Utente in Archivio
		ElencoUtenti utenti = null;
		utenti = (ElencoUtenti) MyFile.loadObject(f_users, ClinicaMain.UTENTI_FILENAME);
		if(utenti == null || utenti.elencoUtenti.size() == 0){
			JOptionPane.showMessageDialog(contentPane, "Nessun Utente presente nei nostri archivi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
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
	        		
	        		// Get Selected Index
	        		selected_index =  (int) ((table.getValueAt(table.getSelectedRow(), 0)));
					selected_index--;
					System.out.println("Row Selected: "+selected_index);
					
		        	tf_nome.setText((table.getValueAt(table.getSelectedRow(), 1).toString()));
			        tf_cognome.setText((table.getValueAt(table.getSelectedRow(), 2).toString()));
			         
			        // Get Date Object
			        try {
						dateChooser.setDate((Date) (MyUtil.revertDateFormatter(table.getValueAt(table.getSelectedRow(), 3).toString())));
			        } catch (ParseException e) { e.printStackTrace(); }
			         
			        tf_luogo.setText((table.getValueAt(table.getSelectedRow(), 4).toString()));
			        tf_telefono.setText((table.getValueAt(table.getSelectedRow(), 5).toString()));
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
	
	// Print Table
	public void printTable(DefaultTableModel model){
		if(f_users.exists()){
			ElencoUtenti utenti = null;
			utenti = (ElencoUtenti) MyFile.loadObject(f_users, ClinicaMain.UTENTI_FILENAME);
			
			removeAllRows(model);
			
			int index = 1;
			for(Utente u : utenti.elencoUtenti){
				String nome = u.getNome();
				String cognome = u.getCognome();
				Date dataN = u.getDataN();
				String dataNf = MyUtil.dateFormatter(dataN);
				String luogoN = u.getLuogoNascita();
				String tel = u.getTelefono();
				Object data[] = {index, nome, cognome, dataNf, luogoN, tel};
				model.addRow(data);
				index++;
			}
		}
	}
	
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
