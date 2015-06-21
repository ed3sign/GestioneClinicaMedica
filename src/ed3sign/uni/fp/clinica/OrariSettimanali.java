package ed3sign.uni.fp.clinica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
import java.util.Calendar;
import java.util.Date;

public class OrariSettimanali extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private static final int TIME_SLOTS = 20;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{774, 0};
		gbl_contentPane.rowHeights = new int[]{47, 509, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
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
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setBackground(Color.MAGENTA);
		scrollPane.setViewportView(table);
		
		// Header Tabella
		String col[] = {"","Lun","Mar","Mer", "Gio", "Ven"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table.setModel(model);
		printDayHours(model);
		
	}
	
	// Print Table
	public void printTable(DefaultTableModel model){
			//removeAllRows(model);
			
	}
	
	/**
	 * Print Week Days
	 * @param model table model
	 */
	public void printDayHours(DefaultTableModel model){
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.HOUR_OF_DAY, 8);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    
		for(int i=0; i<=TIME_SLOTS; i++){
			Object data[] = {cal.getTime()};
			model.addRow(data);
			cal.add(Calendar.MINUTE, 30); // adds one hour			
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
