package ed3sign.uni.fp.clinica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ed3sign.uni.fp.utility.MyFile;
import ed3sign.uni.fp.utility.MyUtil;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import org.joda.time.DateTime;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrariSettimanali extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private static final int TIME_SLOTS = 20;
	private JButton btnSalva;
	private static final int STARTING_HOUR = 8;
	private static final String DISPONIBILE = "Disponibile";
	protected Calendar cal = Calendar.getInstance();
	ArrayList<Date> orariMedico = new ArrayList<Date>();
	HashMap<ArrayList, Integer> orariSettimali = new HashMap<ArrayList, Integer>();
	

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
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    if(table.getSelectedColumnCount() > 0){
                    	int selected_rows[] = table.getSelectedRows();
                    	int selected_cols[] = table.getSelectedColumns();
                    	for(int i=0; i<selected_rows.length; i++){
                    		for(int j=0; j<selected_cols.length; j++)
                    				table.setValueAt("Disponibile", selected_rows[i], selected_cols[j]);
                    	}
                    }
                }
				
				if(e.getKeyCode() == KeyEvent.VK_SHIFT){
					if(table.getSelectedColumnCount() > 0){
                    	int selected_rows[] = table.getSelectedRows();
                    	int selected_cols[] = table.getSelectedColumns();
                    	for(int i=0; i<=selected_rows.length; i++){
                    		for(int j=0; j<=selected_cols.length; j++)
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
		
		// Header Tabella
		String col[] = {"","Lun","Mar","Mer", "Gio", "Ven"};
		DefaultTableModel model = new DefaultTableModel(col, 0);
		table.setModel(model);
		printDayHours(model);
		
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
		
		btnSalva = new JButton("Salva");
		btnSalva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected_rows[] = table.getSelectedRows();
            	int selected_cols[] = table.getSelectedColumns();
            	for(int i=0; i<selected_rows.length; i++){
            		for(int j=0; j<selected_cols.length; j++){
            			if(table.getValueAt(i, j) != null && table.getValueAt(i, j).equals(DISPONIBILE)){
            				Date intervallo = MyUtil.getHours(cal, i, j);
            				System.out.println(intervallo);
            				orariMedico.add(intervallo);
            			}
            		}	
            	}
				JOptionPane.showMessageDialog(getParent(), "Orari Salvati!");
			}
		});
		contentPane.add(btnSalva, gbc_btnSalva);
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
		for(int i=0; i<=TIME_SLOTS; i++){
			String starting_time = MyUtil.timeHourFormat(cal.getTime());
			cal.add(Calendar.MINUTE, 30);
			String ending_time = MyUtil.timeHourFormat(cal.getTime());
			Object data[] = {starting_time +" - "+ ending_time};
			model.addRow(data);
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
