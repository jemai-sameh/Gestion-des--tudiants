package Vue;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import Controleur.EcouteurRechercherEtudiant;
import Model.Model;
import metier.Filiere;
import metier.MetierFiliere;
import metier.MetiereEtudiant;

public class RechercheEtudiant extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField enteNom;
	private JTextField entercin;
	private JComboBox cmbFiliere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechercheEtudiant frame = new RechercheEtudiant();
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
	public RechercheEtudiant() {
		setOpaque(true);
		setBorder(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 876, 516);
		getContentPane().setLayout(null);

		/***************************/

		JPanel panel2 = new JPanel();

		enteNom = new JTextField();
		enteNom.setHorizontalAlignment(SwingConstants.CENTER);
		enteNom.setBounds(44, 11, 165, 20);
		enteNom.setText("ent\u00E9e  nom");
		enteNom.setToolTipText("Nom exemple :sami");
		enteNom.setColumns(10);

		entercin = new JTextField();
		entercin.setHorizontalAlignment(SwingConstants.CENTER);
		entercin.setBounds(538, 11, 179, 20);
		entercin.setToolTipText("Carte D'identit\u00E9 National");
		entercin.setText("ent\u00E9e  Cin");
		entercin.setColumns(8);

		JButton btnRechercheFiliere = new JButton("Rechercher");
		btnRechercheFiliere.addActionListener(new EcouteurRechercherEtudiant(this, "RechercheEtudiantFiliere"));

		btnRechercheFiliere.setBounds(307, 50, 137, 20);
		btnRechercheFiliere.setIcon(
				new ImageIcon(RechercheEtudiant.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		btnRechercheFiliere.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// appeler la methode liste dans la classe model pour afficher liste des
		// etudiant
		new Model().liste(table, new MetiereEtudiant().getAllEtudiantResultSet());

		JPanel panel2_partie1 = new JPanel();
		panel2_partie1.setLayout(null);
		panel2_partie1.add(enteNom);
		panel2_partie1.add(entercin);
		panel2_partie1.add(btnRechercheFiliere);
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setPreferredSize(new Dimension(700, 150));
		tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Liste etudiants", TitledBorder.CENTER, TitledBorder.TOP));
		tableScrollPane.setViewportView(table);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel2_partie1, tableScrollPane);

		JButton btnRechercheNom = new JButton("Rechercher");
		btnRechercheNom.addActionListener(new EcouteurRechercherEtudiant(this, "RechercheEtudiantNom"));

		btnRechercheNom.setIcon(
				new ImageIcon(RechercheEtudiant.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		btnRechercheNom.setBounds(70, 50, 125, 20);
		panel2_partie1.add(btnRechercheNom);

		JButton btnRechercheCin = new JButton("Rechercher");
		btnRechercheCin.setIcon(
				new ImageIcon(RechercheEtudiant.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		btnRechercheCin.setBounds(570, 50, 125, 20);
		panel2_partie1.add(btnRechercheCin);
		btnRechercheCin.addActionListener(new EcouteurRechercherEtudiant(this, "RechercheEtudiantCin"));

		cmbFiliere = new JComboBox();
		cmbFiliere.setModel(new DefaultComboBoxModel(new String[] { "Choisir Filiere" }));
		cmbFiliere.setSelectedIndex(0);
		MetierFiliere mf = new MetierFiliere();
		List<Filiere> f = mf.getAllFiliere();
		for (Filiere filiere : f) {
			cmbFiliere.addItem(filiere.getAbriviation());
		}

		cmbFiliere.setBounds(282, 11, 195, 20);
		panel2_partie1.add(cmbFiliere);

		Label label_2 = new Label("");
		label_2.setEnabled(false);
		label_2.setBackground(SystemColor.controlHighlight);
		label_2.setBounds(500, 0, 6, 89);
		panel2_partie1.add(label_2);

		Label label_1 = new Label("");
		label_1.setEnabled(false);
		label_1.setBackground(SystemColor.controlHighlight);
		label_1.setBounds(512, 0, 6, 89);
		panel2_partie1.add(label_1);

		Label label_2_1 = new Label("");
		label_2_1.setEnabled(false);
		label_2_1.setBackground(SystemColor.controlHighlight);
		label_2_1.setBounds(237, 0, 6, 89);
		panel2_partie1.add(label_2_1);

		Label label_1_1 = new Label("");
		label_1_1.setEnabled(false);
		label_1_1.setBackground(SystemColor.controlHighlight);
		label_1_1.setBounds(249, 0, 6, 89);
		panel2_partie1.add(label_1_1);

		splitPane.setBounds(71, 46, 774, 421);
		getContentPane().add(splitPane);
		splitPane.setDividerLocation(90);
		splitPane.setEnabled(false);

		// image background

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel
				.setIcon(new ImageIcon(RechercheEtudiant.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel.setBounds(0, 0, 886, 489);
		getContentPane().add(lblNewLabel);
	}

	public JTable getTable() {
		return table;
	}

	public JTextField getEnteNom() {
		return enteNom;
	}

	public JTextField getEntercin() {
		return entercin;
	}

	public JComboBox getCmbFiliere() {
		return cmbFiliere;
	}
}
