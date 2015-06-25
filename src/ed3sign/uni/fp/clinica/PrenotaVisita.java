package ed3sign.uni.fp.clinica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JTextArea;

public class PrenotaVisita extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrenotaVisita frame = new PrenotaVisita();
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
	public PrenotaVisita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 252, 262, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPrenotaVisita = new JLabel("Prenota Visita");
		lblPrenotaVisita.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		GridBagConstraints gbc_lblPrenotaVisita = new GridBagConstraints();
		gbc_lblPrenotaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblPrenotaVisita.gridwidth = 2;
		gbc_lblPrenotaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrenotaVisita.gridx = 1;
		gbc_lblPrenotaVisita.gridy = 1;
		contentPane.add(lblPrenotaVisita, gbc_lblPrenotaVisita);
		
		JLabel lblMedico = new JLabel("Medico");
		GridBagConstraints gbc_lblMedico = new GridBagConstraints();
		gbc_lblMedico.anchor = GridBagConstraints.WEST;
		gbc_lblMedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedico.gridx = 1;
		gbc_lblMedico.gridy = 3;
		contentPane.add(lblMedico, gbc_lblMedico);
		
		JLabel lblnomemedico = new JLabel("[nome_medico]");
		GridBagConstraints gbc_lblnomemedico = new GridBagConstraints();
		gbc_lblnomemedico.anchor = GridBagConstraints.WEST;
		gbc_lblnomemedico.insets = new Insets(0, 0, 5, 5);
		gbc_lblnomemedico.gridx = 2;
		gbc_lblnomemedico.gridy = 3;
		contentPane.add(lblnomemedico, gbc_lblnomemedico);
		
		JLabel lblPaziente = new JLabel("Paziente");
		GridBagConstraints gbc_lblPaziente = new GridBagConstraints();
		gbc_lblPaziente.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaziente.anchor = GridBagConstraints.WEST;
		gbc_lblPaziente.gridx = 1;
		gbc_lblPaziente.gridy = 4;
		contentPane.add(lblPaziente, gbc_lblPaziente);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 4;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblData = new JLabel("Data");
		GridBagConstraints gbc_lblData = new GridBagConstraints();
		gbc_lblData.anchor = GridBagConstraints.WEST;
		gbc_lblData.insets = new Insets(0, 0, 5, 5);
		gbc_lblData.gridx = 1;
		gbc_lblData.gridy = 5;
		contentPane.add(lblData, gbc_lblData);
		
		JLabel lblgiornovisita = new JLabel("[giorno_visita]");
		GridBagConstraints gbc_lblgiornovisita = new GridBagConstraints();
		gbc_lblgiornovisita.anchor = GridBagConstraints.WEST;
		gbc_lblgiornovisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblgiornovisita.gridx = 2;
		gbc_lblgiornovisita.gridy = 5;
		contentPane.add(lblgiornovisita, gbc_lblgiornovisita);
		
		JLabel lblOrario = new JLabel("Orario");
		GridBagConstraints gbc_lblOrario = new GridBagConstraints();
		gbc_lblOrario.anchor = GridBagConstraints.WEST;
		gbc_lblOrario.insets = new Insets(0, 0, 5, 5);
		gbc_lblOrario.gridx = 1;
		gbc_lblOrario.gridy = 6;
		contentPane.add(lblOrario, gbc_lblOrario);
		
		JLabel lblorariovisita = new JLabel("[orario_visita]");
		GridBagConstraints gbc_lblorariovisita = new GridBagConstraints();
		gbc_lblorariovisita.anchor = GridBagConstraints.WEST;
		gbc_lblorariovisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblorariovisita.gridx = 2;
		gbc_lblorariovisita.gridy = 6;
		contentPane.add(lblorariovisita, gbc_lblorariovisita);
		
		JLabel lblMotivoDellaVisita = new JLabel("Motivo della visita (note)");
		GridBagConstraints gbc_lblMotivoDellaVisita = new GridBagConstraints();
		gbc_lblMotivoDellaVisita.anchor = GridBagConstraints.WEST;
		gbc_lblMotivoDellaVisita.gridwidth = 2;
		gbc_lblMotivoDellaVisita.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivoDellaVisita.gridx = 1;
		gbc_lblMotivoDellaVisita.gridy = 8;
		contentPane.add(lblMotivoDellaVisita, gbc_lblMotivoDellaVisita);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 9;
		contentPane.add(textArea, gbc_textArea);
	}

}
