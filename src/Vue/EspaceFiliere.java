package Vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controleur.ecouteurFiliere;
import Model.ModelFiliere;

public class EspaceFiliere extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField abriviationModif;
	private JTextField nom_filiere;
	private JList<String> listMat;
	private JTextField abriviation;
	private JComboBox cmbFiliereModif;
	private JPanel panalModif;
	private JList<String> listMatModif;
	private JScrollPane scrollPane;
	private JButton btnAnnuler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspaceFiliere frame = new EspaceFiliere();
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
	public EspaceFiliere() {
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
		panelCardLayout.add(pnlNouveauMatiere, "nouveau matiere");

		JLabel lblFiliere = new JLabel("Nom Filiere");
		lblFiliere.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFiliere.setBounds(117, 115, 89, 14);
		pnlNouveauMatiere.add(lblFiliere);

		JLabel lblAbriviation = new JLabel("Abriviation ");
		lblAbriviation.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAbriviation.setBounds(117, 178, 89, 14);
		pnlNouveauMatiere.add(lblAbriviation);

		JLabel lblmatiere = new JLabel("Les matiere");
		lblmatiere.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblmatiere.setBounds(117, 256, 89, 14);
		pnlNouveauMatiere.add(lblmatiere);

		JButton btnAjout = new JButton("Ajouter");
		btnAjout.addActionListener(new ecouteurFiliere(this, "Ajout"));
		btnAjout.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAjout.setBounds(479, 149, 89, 23);
		pnlNouveauMatiere.add(btnAjout);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyComponent("ajout");
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAnnuler.setBounds(479, 198, 89, 23);
		pnlNouveauMatiere.add(btnAnnuler);

		nom_filiere = new JTextField();
		nom_filiere.setColumns(10);
		nom_filiere.setBounds(231, 112, 129, 20);
		pnlNouveauMatiere.add(nom_filiere);

		abriviation = new JTextField();
		abriviation.setText("");
		abriviation.setBounds(231, 176, 129, 20);
		pnlNouveauMatiere.add(abriviation);
		abriviation.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(231, 237, 129, 75);
		pnlNouveauMatiere.add(scrollPane_1);

		listMat = new JList<>(new ModelFiliere().matiere());

		scrollPane_1.setViewportView(listMat);

		// ************************ nouveau
		// matiere**************************************
		JPanel pnlSuppModif = new JPanel();
		pnlSuppModif.setLayout(null);
		panelCardLayout.add(pnlSuppModif, "supp/modif matiere");

		panalModif = new JPanel();
		panalModif.setVisible(false);
		panalModif.setBorder(new LineBorder(new Color(0, 0, 128), 1, true));
		panalModif.setBounds(27, 148, 547, 262);
		pnlSuppModif.add(panalModif);
		panalModif.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Abriviation");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(70, 46, 89, 23);
		panalModif.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Matiere");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(69, 109, 46, 14);
		panalModif.add(lblNewLabel_3);

		JButton btnAjoutModif = new JButton("Valider Modification");
		btnAjoutModif.addActionListener(new ecouteurFiliere(this, "Valider Modification"));
		btnAjoutModif.setBounds(79, 208, 161, 31);
		panalModif.add(btnAjoutModif);
		btnAjoutModif.setFont(new Font("Tahoma", Font.BOLD, 12));

		JButton btnAnnulerModif = new JButton("Annuler");
		btnAnnulerModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyComponent("Modif");
			}
		});
		btnAnnulerModif.setBounds(273, 208, 89, 31);
		panalModif.add(btnAnnulerModif);
		btnAnnulerModif.setFont(new Font("Tahoma", Font.BOLD, 12));

		abriviationModif = new JTextField();
		abriviationModif.setBounds(193, 48, 129, 20);
		panalModif.add(abriviationModif);
		abriviationModif.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(193, 109, 129, 74);
		panalModif.add(scrollPane);

		listMatModif = new JList();
		listMatModif.setEnabled(false);
		scrollPane.setViewportView(listMatModif);

		JButton btnAjoutMatiere = new JButton("Modifier matiere ");
		btnAjoutMatiere.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnAjoutMatiere.setBounds(367, 109, 132, 39);
		panalModif.add(btnAjoutMatiere);
		//////////////////////////////////////

		JLabel lblNewLabel = new JLabel("Choisir filiere");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel.setBounds(116, 85, 111, 20);
		pnlSuppModif.add(lblNewLabel);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ecouteurFiliere(this, "supprimer"));
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSupprimer.setForeground(new Color(0, 0, 0));
		btnSupprimer.setBounds(463, 65, 111, 23);
		pnlSuppModif.add(btnSupprimer);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ecouteurFiliere(this, "Modifier"));
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifier.setForeground(new Color(0, 0, 0));
		btnModifier.setBounds(463, 114, 111, 23);
		pnlSuppModif.add(btnModifier);

		cmbFiliereModif = new JComboBox();
		new ModelFiliere().filiere(cmbFiliereModif);
		cmbFiliereModif.setBounds(237, 85, 180, 20);
		cmbFiliereModif.setSelectedIndex(0);
		pnlSuppModif.add(cmbFiliereModif);

		JPanel panel = new JPanel();
		panel.setBounds(10, 39, 183, 421);
		getContentPane().add(panel);
		panel.setLayout(null);

		// button d'action
		JButton btnNouveauFiliere = new JButton("Nouveau Filiere");
		btnNouveauFiliere.setBounds(10, 99, 163, 69);
		btnNouveauFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				((CardLayout) panelCardLayout.getLayout()).show(panelCardLayout, "nouveau matiere");
				emptyComponent("ajout");
			}
		});
		btnNouveauFiliere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNouveauFiliere.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnNouveauFiliere);

		JButton btnSupp_ModifMatiere = new JButton("Supprimer/Modifier");
		btnSupp_ModifMatiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				((CardLayout) panelCardLayout.getLayout()).show(panelCardLayout, "supp/modif matiere");
				emptyComponent("Modif");
			}
		});
		btnSupp_ModifMatiere.setBounds(10, 219, 163, 69);
		btnSupp_ModifMatiere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSupp_ModifMatiere.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(btnSupp_ModifMatiere);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5
				.setIcon(new ImageIcon(EspaceFiliere.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel_5.setBounds(0, 0, 876, 486);
		getContentPane().add(lblNewLabel_5);

		btnAjoutMatiere.addActionListener(new ecouteurFiliere(this, "ajoutMatiere"));
	}

	void emptyComponent(String type) {
		if (type == "ajout") {
			nom_filiere.setText("");
			abriviation.setText("");
		} else {
			panalModif.setVisible(false);
			cmbFiliereModif.setSelectedIndex(0);
			abriviationModif.setText("");

		}

	}

	public JPanel getPanel_1() {
		return panalModif;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JList getListMatModif() {
		return listMatModif;
	}

	public JTextField getAbriviationModif() {
		return abriviationModif;
	}

	public JTextField getNom_filiere() {
		return nom_filiere;
	}

	public JList<String> getListMat() {
		return listMat;
	}

	public JTextField getAbriviation() {
		return abriviation;
	}

	public JComboBox getCmbFiliereModif() {
		return cmbFiliereModif;
	}

}
