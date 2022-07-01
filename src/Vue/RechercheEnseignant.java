package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Controleur.EcouteurRechercheEnseignant;
import Model.Model;
import metier.MetierEnseignant;

/**
 * @author aa
 *
 */
public class RechercheEnseignant extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField enteNom;
	private JTextField entercin;

	public JTextField getEnteNom() {
		return enteNom;
	}

	public JTextField getEntercin() {
		return entercin;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechercheEnseignant frame = new RechercheEnseignant();
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
	public RechercheEnseignant() {
		setBorder(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 876, 516);
		getContentPane().setLayout(null);

		/***************************/


		enteNom = new JTextField();
		enteNom.setHorizontalAlignment(SwingConstants.CENTER);
		enteNom.setBounds(22, 30, 165, 31);
		enteNom.setText("ent\u00E9e  nom");
		enteNom.setToolTipText("Nom exemple :sami");
		enteNom.setColumns(10);

		entercin = new JTextField();
		entercin.setHorizontalAlignment(SwingConstants.CENTER);
		entercin.setBounds(425, 30, 179, 31);
		entercin.setToolTipText("Carte D'identit\u00E9 National");
		entercin.setText("ent\u00E9e  Cin");
		entercin.setColumns(8);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
// appeler methode liste de la class model pour remplir jtable
		new Model().liste(table, new MetierEnseignant().getAllEnseignantResultSet(
				"select  num_enseignant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email' from enseignant"));
		JPanel panel2_partie1 = new JPanel();
		panel2_partie1.setLayout(null);
		panel2_partie1.add(enteNom);
		panel2_partie1.add(entercin);
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setFont(new Font("Tahoma", Font.BOLD, 15));
		tableScrollPane.setPreferredSize(new Dimension(700, 150));
		tableScrollPane.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Les Enseignant",
				TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));
		tableScrollPane.setViewportView(table);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panel2_partie1, tableScrollPane);

		JButton btnRechercheNom = new JButton("Rechercher");
		// ecouteur recherche by nom
		btnRechercheNom.addActionListener(new EcouteurRechercheEnseignant(this, "RechercheEnseignantNom", table));

		btnRechercheNom.setIcon(new ImageIcon(
				RechercheEnseignant.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		btnRechercheNom.setBounds(208, 20, 125, 50);
		panel2_partie1.add(btnRechercheNom);

		JButton btnRechercheCin = new JButton("Rechercher");
		// ecouteur recherche by cin
		btnRechercheCin.addActionListener(new EcouteurRechercheEnseignant(this, "RechercheEnseignantCin", table));

		btnRechercheCin.setIcon(new ImageIcon(
				RechercheEnseignant.class.getResource("/Image/icons8-trouver-l'utilisateur-homme-16.png")));
		btnRechercheCin.setBounds(637, 20, 125, 50);
		panel2_partie1.add(btnRechercheCin);

		Label label_2 = new Label("");
		label_2.setEnabled(false);
		label_2.setBackground(SystemColor.controlHighlight);
		label_2.setBounds(382, 0, 6, 89);
		panel2_partie1.add(label_2);

		Label label_1 = new Label("");
		label_1.setEnabled(false);
		label_1.setBackground(SystemColor.controlHighlight);
		label_1.setBounds(394, 0, 6, 89);
		panel2_partie1.add(label_1);

		Label label_2_1 = new Label("");
		label_2_1.setEnabled(false);
		label_2_1.setBackground(SystemColor.controlHighlight);
		label_2_1.setBounds(358, 0, 6, 89);
		panel2_partie1.add(label_2_1);

		Label label_1_1 = new Label("");
		label_1_1.setEnabled(false);
		label_1_1.setBackground(SystemColor.controlHighlight);
		label_1_1.setBounds(370, 0, 6, 89);
		panel2_partie1.add(label_1_1);
		splitPane.setBounds(63, 31, 774, 421);
		getContentPane().add(splitPane);
		splitPane.setDividerLocation(90);
		splitPane.setEnabled(false);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(
				new ImageIcon(RechercheEnseignant.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel.setBounds(0, 0, 886, 489);
		getContentPane().add(lblNewLabel);
	}

}
