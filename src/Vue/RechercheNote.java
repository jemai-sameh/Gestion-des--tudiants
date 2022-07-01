package Vue;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import Model.Model;
import metier.Etudiant;
import metier.Filiere;
import metier.MetierFiliere;
import metier.MetierNote;
import metier.MetiereEtudiant;

public class RechercheNote extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox cmbEtudiant;
	private JButton btnNewButton;
	private JComboBox cmbFiliere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechercheNote frame = new RechercheNote();
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
	public RechercheNote() {
		setBorder(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 876, 516);
		getContentPane().setLayout(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setBounds(125, 31, 669, 421);
		getContentPane().add(splitPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),
				"Liste Matiere Avec Note", TitledBorder.CENTER, TitledBorder.TOP));
		splitPane.setRightComponent(scrollPane);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		new Model().liste(table, new MetierNote().getAllNoteResultSet(
				"select cin ,type_matiere 'matiere',crédit,coefficient,note_examen 'Note Examen',note_Ds1 'Note Ds1',note_Ds2 'Note Ds2',note_Orale 'Note Orale',note_tp 'Note TP' FROM matiere NATURAL JOIN note ,etudiant AS e where e.num_etudiant=note.id_etudiant"));

		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		splitPane.setLeftComponent(panel);

		JLabel lblEt = new JLabel("choisir Etudiant");
		lblEt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEt.setBounds(320, 0, 154, 29);
		panel.add(lblEt);

		cmbFiliere = new JComboBox();
		cmbFiliere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbFiliere.getSelectedIndex() != 0) {

					cmbEtudiant.setEnabled(true);
					// remplir combobox
					int id = new MetierFiliere()
							.getIdByAbriviationFiliere(String.valueOf(cmbFiliere.getSelectedItem()));
					cmbEtudiant.setModel(new DefaultComboBoxModel(new String[] { "Choisir Etudiant" }));
					cmbEtudiant.setSelectedIndex(0);
					MetiereEtudiant mf = new MetiereEtudiant();
					List<Etudiant> f = mf.getAllEtudiant("select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,	somme_credit ,id_filiere from etudiant  where id_filiere="+id);
					for (Etudiant etudiant : f) {
						cmbEtudiant.addItem(etudiant.getNom() + " " + etudiant.getPrenom() + " " + etudiant.getCin());
					}

					new Model().liste(table, new MetierNote().getAllNoteResultSet(
							"select cin ,type_matiere 'matiere',crédit,coefficient,note_examen 'Note Examen',note_Ds1 'Note Ds1',note_Ds2 'Note Ds2',note_Orale 'Note Orale',note_tp 'Note TP' FROM matiere NATURAL JOIN note ,etudiant AS e where e.num_etudiant=note.id_etudiant AND e.id_filiere='"
									+ id + "'"));

				}
			}
		});
		cmbFiliere.setModel(new DefaultComboBoxModel(new String[] { "Choisir Filiere" }));
		cmbFiliere.setSelectedIndex(0);
		MetierFiliere mf = new MetierFiliere();
		List<Filiere> f = mf.getAllFiliere();
		for (Filiere filiere : f) {
			cmbFiliere.addItem(filiere.getAbriviation());
		}

		cmbFiliere.setBounds(69, 34, 181, 29);
		panel.add(cmbFiliere);

		btnNewButton = new JButton("Recherche");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cmbFiliere.getSelectedIndex() == 0 || cmbEtudiant.getSelectedIndex() == 0) {
					JOptionPane.showConfirmDialog(null, "choisir filiere ou matiere", "ERREUR",
							JOptionPane.CLOSED_OPTION);
				} else {
					// na3ml liste 7aseb filiere w7aseb etudiant
					Integer id = new MetierFiliere()
							.getIdByAbriviationFiliere(String.valueOf(cmbFiliere.getSelectedItem()));
					String etudiant = String.valueOf(cmbEtudiant.getSelectedItem());
					String[] s = etudiant.split(" ");

					String nom = s[0];
					String prenom = s[1];
					String cin = s[2];
					new Model().liste(table, new MetierNote().getAllNoteResultSet(
							"select cin,type_matiere 'matiere',crédit,coefficient,note_examen 'Note Examen',note_Ds1 'Note Ds1',note_Ds2 'Note Ds2',note_Orale 'Note Orale',note_tp 'Note TP' FROM matiere NATURAL JOIN note ,etudiant AS e where e.num_etudiant=note.id_etudiant AND e.nom='"
									+ nom + "' AND e.prenom='" + prenom + "' AND e.cin='" + cin + "' AND e.id_filiere='"
									+ id + "'"));
				}

			}
		});
		btnNewButton.setBounds(544, 35, 103, 27);
		panel.add(btnNewButton);

		cmbEtudiant = new JComboBox();
		cmbEtudiant.setBounds(308, 36, 166, 24);
		panel.add(cmbEtudiant);

		JLabel lblNewLabel = new JLabel("Choisir filiere");
		lblNewLabel.setBounds(108, 8, 89, 14);

		panel.add(lblNewLabel);
		splitPane.setDividerLocation(90);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1
				.setIcon(new ImageIcon(RechercheNote.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel_1.setBounds(0, 0, 876, 489);
		getContentPane().add(lblNewLabel_1);
	}
}
