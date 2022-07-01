package Vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Apropos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Apropos frame = new Apropos();
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
	public Apropos() {
		setTitle("A propos");
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 511, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Universit\u00E9 De Carthage");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setBounds(90, 11, 255, 23);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("FACULTE DES SCIENCES ECONOMIQUES ET DE GESTION DE NABEUL");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(37, 52, 376, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Licence Appliquer  2 Informatique De gestion ");
		lblNewLabel_2.setForeground(new Color(0, 0, 128));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(90, 88, 281, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cours de: PROGRAMMATION ORIENT2 OBJET 2");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 10));
		lblNewLabel_3.setBounds(37, 128, 334, 14);
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Par l'Etudiant : JEMAI SAMEH");
		lblNewLabel_4.setForeground(new Color(0, 0, 128));
		lblNewLabel_4.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 12));
		lblNewLabel_4.setBounds(90, 178, 177, 14);
		getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Enseignant :Mr ELKOSTANTINI SABEUR ");
		lblNewLabel_5.setForeground(new Color(0, 0, 128));
		lblNewLabel_5.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.ITALIC, 12));
		lblNewLabel_5.setBounds(89, 153, 224, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("GESTION DES TACHES,CETTE APPLICATION SERT A:");
		lblNewLabel_6.setFont(new Font("Stencil", Font.BOLD, 14));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(23, 220, 415, 14);
		getContentPane().add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("* G\u00E9rer les etudiant");
		lblNewLabel_7.setBounds(55, 245, 196, 14);
		getContentPane().add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("* Acc\u00E9der au contenu d'une base de donn\u00E9e ,afin de");
		lblNewLabel_8.setBounds(55, 298, 316, 14);
		getContentPane().add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("*  G\u00E9rer les activit\u00E9 des enseignant");
		lblNewLabel_9.setBounds(55, 270, 212, 14);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("- Ajouter ou modifier ou Supprimer un Etudiant ou un enseignant");
		lblNewLabel_10.setBounds(81, 323, 315, 14);
		getContentPane().add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("- Ajouter ou modifier ou Supprimer des matiere ou filiere");
		lblNewLabel_11.setBounds(78, 348, 318, 14);
		getContentPane().add(lblNewLabel_11);

		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnOK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOK.setBounds(421, 333, 63, 34);
		contentPane.add(btnOK);
		
		JLabel lblNewLabel_11_1 = new JLabel("- ajouter ou modifier note d'etudiant ");
		lblNewLabel_11_1.setBounds(78, 373, 318, 14);
		contentPane.add(lblNewLabel_11_1);

	}
}
