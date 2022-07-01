package Vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controleur.ecouteurMatiere;
import Model.ModelMatiere;
import metier.MetierEnseignant;
import metier.MetierMatiere;

public class EspaceMatiere extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JTextField matiere;
	private JComboBox cmbEnseignantNouveau;
	private JComboBox cmbCreditnouveau;
	private JRadioButton btnrdDSNouveau;
	private JComboBox cmbCoefficientNouveau;
	private JComboBox cmbMatiereModif;
	private JComboBox cmbCoefficientModif;
	private JComboBox cmbCreditModif;
	private JRadioButton btnrdDSMdif;
	private JComboBox cmbEnseignantModif;
	private JPanel panelModif;
	private JButton btnAnnuler;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspaceMatiere frame = new EspaceMatiere();
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
	public EspaceMatiere() {
		setBorder(null);
		setBounds(100, 100, 876, 516);
		getContentPane().setLayout(null);

		// panel card layout
		JPanel panelCardLayout = new JPanel();
		panelCardLayout.setBounds(213, 39, 628, 421);
		getContentPane().add(panelCardLayout);
		panelCardLayout.setLayout(new CardLayout(0, 0));

		JPanel pnlNouveauMatiere = new JPanel();
		pnlNouveauMatiere.setLayout(null);
		panelCardLayout.add(pnlNouveauMatiere, "nouveauMatiere");

		JLabel lblMatiere_1 = new JLabel("Matiere");
		lblMatiere_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMatiere_1.setBounds(117, 115, 46, 14);
		pnlNouveauMatiere.add(lblMatiere_1);

		JLabel lblNewLabel_2_1 = new JLabel("Enseignant");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(117, 171, 89, 14);
		pnlNouveauMatiere.add(lblNewLabel_2_1);

		JLabel lblNewLabel_3_1 = new JLabel("Cr\u00E9dits");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3_1.setBounds(117, 256, 46, 14);
		pnlNouveauMatiere.add(lblNewLabel_3_1);

		btnrdDSNouveau = new JRadioButton("DS");
		btnrdDSNouveau.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnrdDSNouveau.setBounds(285, 311, 109, 23);
		pnlNouveauMatiere.add(btnrdDSNouveau);

		JButton btnAjout = new JButton("Ajouter");
		btnAjout.addActionListener(new ecouteurMatiere(this, "Ajout"));

		btnAjout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjout.setBounds(479, 131, 89, 41);
		pnlNouveauMatiere.add(btnAjout);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyComponent("ajout");

			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnnuler.setBounds(479, 188, 89, 33);
		pnlNouveauMatiere.add(btnAnnuler);

		matiere = new JTextField();
		matiere.setColumns(10);
		matiere.setBounds(231, 109, 163, 23);
		pnlNouveauMatiere.add(matiere);

		cmbCreditnouveau = new JComboBox();
		cmbCreditnouveau.setBounds(231, 249, 89, 24);
		cmbCreditnouveau.setModel(new DefaultComboBoxModel(new String[] { "", "2", "3", "4", "6", "8" }));
		cmbCreditnouveau.setSelectedIndex(0);

		pnlNouveauMatiere.add(cmbCreditnouveau);

		cmbEnseignantNouveau = new JComboBox();
		// remplir combobox les nom d'eseignant
		new ModelMatiere(cmbEnseignantNouveau).remplirEnseignant(new MetierEnseignant().getAllEnseignant());

		cmbEnseignantNouveau.setBounds(231, 162, 163, 33);
		pnlNouveauMatiere.add(cmbEnseignantNouveau);

		JLabel lblcoefficient = new JLabel("Coefficient");
		lblcoefficient.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblcoefficient.setBounds(117, 218, 69, 27);
		pnlNouveauMatiere.add(lblcoefficient);

		cmbCoefficientNouveau = new JComboBox();
		cmbCoefficientNouveau.setModel(new DefaultComboBoxModel(new String[] { "", "1", "1.5", "2", "3", "4" }));
		cmbCoefficientNouveau.setSelectedIndex(0);

		cmbCoefficientNouveau.setBounds(231, 213, 89, 23);
		pnlNouveauMatiere.add(cmbCoefficientNouveau);

		// ************************ nouveau
		// matiere**************************************
		JPanel pnlSuppModif = new JPanel();
		pnlSuppModif.setLayout(null);
		panelCardLayout.add(pnlSuppModif, "suppMatiere");

		panelModif = new JPanel();
		panelModif.setVisible(false);
		panelModif.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panelModif.setBounds(101, 157, 392, 242);
		pnlSuppModif.add(panelModif);
		panelModif.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Enseignant");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(79, 32, 89, 14);
		panelModif.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Cr\u00E9dits");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(79, 108, 46, 14);
		panelModif.add(lblNewLabel_3);

		cmbCreditModif = new JComboBox();
		cmbCreditModif.setModel(new DefaultComboBoxModel(new String[] { "", "2", "3", "4", "6", "8" }));
		cmbCreditModif.setSelectedIndex(0);
		cmbCreditModif.setBounds(191, 105, 109, 20);
		panelModif.add(cmbCreditModif);

		cmbEnseignantModif = new JComboBox();
		// remplir combobox les nom d'eseignant
		new ModelMatiere(cmbEnseignantModif).remplirEnseignant(new MetierEnseignant().getAllEnseignant());

		cmbEnseignantModif.setBounds(191, 26, 161, 23);
		panelModif.add(cmbEnseignantModif);

		JButton btnAjoutModif = new JButton("Valider Modification");
		btnAjoutModif.addActionListener(new ecouteurMatiere(this, "Modifier"));

		btnAjoutModif.setBounds(79, 205, 161, 23);
		panelModif.add(btnAjoutModif);
		btnAjoutModif.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnAnnulerModif = new JButton("Annuler");
		btnAnnulerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyComponent("supp_modif");

			}
		});
		btnAnnulerModif.setBounds(277, 205, 89, 23);
		panelModif.add(btnAnnulerModif);
		btnAnnulerModif.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnrdDSMdif = new JRadioButton("DS");
		btnrdDSMdif.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnrdDSMdif.setBounds(191, 151, 109, 23);
		panelModif.add(btnrdDSMdif);

		JLabel lblcoefficient_1 = new JLabel("Coefficient");
		lblcoefficient_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblcoefficient_1.setBounds(79, 69, 69, 27);
		panelModif.add(lblcoefficient_1);

		cmbCoefficientModif = new JComboBox();
		cmbCoefficientModif.setModel(new DefaultComboBoxModel(new String[] { "", "1", "1.5", "2", "3", "4" }));
		cmbCoefficientModif.setSelectedIndex(0);

		cmbCoefficientModif.setBounds(190, 71, 110, 23);
		panelModif.add(cmbCoefficientModif);

		JLabel lblNewLabel = new JLabel("Choisir matiere");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(116, 85, 111, 20);
		pnlSuppModif.add(lblNewLabel);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ecouteurMatiere(this, "supprimer"));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSupprimer.setForeground(new Color(0, 0, 0));
		btnSupprimer.setBounds(463, 62, 111, 26);
		pnlSuppModif.add(btnSupprimer);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ecouteurMatiere(this, "Modif"));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifier.setForeground(new Color(0, 0, 0));
		btnModifier.setBounds(463, 111, 111, 26);
		pnlSuppModif.add(btnModifier);

		cmbMatiereModif = new JComboBox();
		// remplir combobox
		new ModelMatiere(cmbMatiereModif).remplirMatiere(new MetierMatiere().getAllMatiere());

		cmbMatiereModif.setBounds(253, 79, 133, 26);
		pnlSuppModif.add(cmbMatiereModif);

		JPanel panel = new JPanel();
		panel.setBounds(10, 39, 183, 421);
		getContentPane().add(panel);
		panel.setLayout(null);

		// button d'action
		JButton btnNouveauMatiere = new JButton("Nouveau Matiere");
		btnNouveauMatiere.setBounds(10, 99, 163, 69);
		btnNouveauMatiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				((CardLayout) panelCardLayout.getLayout()).show(panelCardLayout, "nouveauMatiere");
				new ModelMatiere(cmbEnseignantNouveau).remplirEnseignant(new MetierEnseignant().getAllEnseignant());

				emptyComponent("ajout");
			}
		});
		btnNouveauMatiere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNouveauMatiere.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnNouveauMatiere);

		JButton btnSupp_ModifMatiere = new JButton("Supprimer/Modifier");
		btnSupp_ModifMatiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				((CardLayout) panelCardLayout.getLayout()).show(panelCardLayout, "suppMatiere");
				new ModelMatiere(cmbMatiereModif).remplirMatiere(new MetierMatiere().getAllMatiere());

				emptyComponent("Modif");
			}
		});
		btnSupp_ModifMatiere.setBounds(10, 219, 163, 69);
		btnSupp_ModifMatiere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSupp_ModifMatiere.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnSupp_ModifMatiere);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5
				.setIcon(new ImageIcon(EspaceMatiere.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel_5.setBounds(0, 0, 876, 486);
		getContentPane().add(lblNewLabel_5);
	}

	public void emptyComponent(String empty) {
		// TODO Auto-generated method stub
		if (empty == "ajout") {
			matiere.setText("");
			cmbEnseignantNouveau.setSelectedIndex(0);
			cmbCreditnouveau.setSelectedIndex(0);
			cmbCoefficientNouveau.setSelectedIndex(0);
			btnrdDSNouveau.setSelected(false);
		} else {
			cmbMatiereModif.setSelectedIndex(0);
			cmbEnseignantModif.setSelectedIndex(0);
			cmbCreditModif.setSelectedIndex(0);
			cmbCoefficientModif.setSelectedIndex(0);
			btnrdDSMdif.setSelected(false);
			panelModif.setVisible(false);
		}

	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public JTextField getMatiere() {
		return matiere;
	}

	public JComboBox getCmbEnseignantNouveau() {
		return cmbEnseignantNouveau;
	}

	public JComboBox getCmbCreditnouveau() {
		return cmbCreditnouveau;
	}

	public JRadioButton getBtnrdDSNouveau() {
		return btnrdDSNouveau;
	}

	public JComboBox getCmbCoefficientNouveau() {
		return cmbCoefficientNouveau;
	}

	public JComboBox getCmbMatiereModif() {
		return cmbMatiereModif;
	}

	public JComboBox getCmbCoefficientModif() {
		return cmbCoefficientModif;
	}

	public JComboBox getCmbCreditModif() {
		return cmbCreditModif;
	}

	public JRadioButton getBtnrdDSMdif() {
		return btnrdDSMdif;
	}

	public JComboBox getCmbEnseignantModif() {
		return cmbEnseignantModif;
	}

	public JPanel getPanelModif() {
		return panelModif;
	}
}
