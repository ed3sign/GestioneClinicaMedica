package ed3sign.uni.fp.clinica;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;

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

public class VisualizzaVisite extends JFrame {
	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected int selected_index;
	File f_visite = new File(ClinicaMain.VISITE_FILENAME);
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblElencoVisite;

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
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		// Header Tabella
		String col[] = {"Data/Ora","Medico","Paziente","Stato","Motivo"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table.setModel(model);
		
		lblElencoVisite = new JLabel("Elenco Visite");
		lblElencoVisite.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblElencoVisite.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblElencoVisite, BorderLayout.NORTH);
		
		// Nessuna Visita in Archivio
		ElencoVisite visite = null;
		visite = (ElencoVisite) MyFile.loadObject(f_visite, ClinicaMain.VISITE_FILENAME);
		if(visite == null || visite.elencoVisite.size() == 0){
			JOptionPane.showMessageDialog(contentPane, "Nessuna visita presente nei nostri archivi!", "Attenzione", JOptionPane.WARNING_MESSAGE);
		}
		else{
			printTable(model);
		}
		
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
			
			for(Visita v : visite.elencoVisite){
				String data_visita = MyUtil.timeDateFormat(v.getData()) + " "+ MyUtil.timeIntervalFormat(v.getData());
				String medico = v.getMedico().getCognome();
				String paziente = v.getPaziente().getCognome();
				String stato = v.getStato();
				String motivo = v.getDescrizione();
				Object data[] = {data_visita, medico, paziente, stato, motivo};
				model.addRow(data);
			}
		}
	}
	
	public void removeAllRows(DefaultTableModel model){
		int rows = model.getRowCount(); 
		for(int i = rows - 1; i >=0; i--)
		   model.removeRow(i); 
	}
}
