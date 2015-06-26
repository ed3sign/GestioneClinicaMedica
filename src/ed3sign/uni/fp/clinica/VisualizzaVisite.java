package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;
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
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;

import ed3sign.uni.fp.utility.InterfaceHelpers;
import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

public class VisualizzaVisite extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected int selected_index;
	File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	private JScrollPane scrollPane;
	private JTable table;

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
	public VisualizzaVisite() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 830, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{804, 0};
		gbl_contentPane.rowHeights = new int[]{190, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		// Header Tabella
		String col[] = {"#","Data/Ora","Medico","Paziente", "Motivo"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		printTable(model);
		
		// Table Refresh
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				printTable(model);
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
	}
	
	// Print Table
	public void printTable(DefaultTableModel model){
		if(f_visite.exists()){
			ElencoVisite visite = null;
			visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
			
			removeAllRows(model);
			
			int index = 1;
			for(Visita v : visite.elencoVisite){
				String data_visita = MyUtil.timeDateFormat(v.getData()) + MyUtil.timeIntervalFormat(v.getData());
				String medico = v.getMedico().getCognome();
				String paziente = v.getPaziente().getCognome();
				String motivo = v.getDescrizione();
				Object data[] = {index, data_visita, medico, paziente, motivo};
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
}
