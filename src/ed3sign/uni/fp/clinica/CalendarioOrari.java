package ed3sign.uni.fp.clinica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Font;

public class CalendarioOrari extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalendarioOrari frame = new CalendarioOrari();
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
	public CalendarioOrari() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JCalendar calendar = new JCalendar();
		calendar.setTodayButtonVisible(true);
		calendar.setTodayButtonText("Today");
		calendar.getDayChooser().setAlwaysFireDayProperty(true);
		contentPane.add(calendar, BorderLayout.CENTER);
		calendar.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{calendar.getMonthChooser().getSpinner(), calendar.getMonthChooser(), calendar.getMonthChooser().getComboBox(), calendar.getYearChooser(), calendar.getYearChooser().getSpinner(), calendar.getDayChooser(), calendar.getDayChooser().getDayPanel()}));
		
		JLabel lblSelezionaGiorno = new JLabel("Seleziona Giorno");
		lblSelezionaGiorno.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelezionaGiorno.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(lblSelezionaGiorno, BorderLayout.NORTH);
	}

}
