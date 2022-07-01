package Vue;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Controleur.EcouteurNote;
import Model.Model;
import metier.Filiere;
import metier.Matiere;
import metier.MetierFiliere;
import metier.MetierMatiere;
import metier.MetierNote;
import metier.MetiereEtudiant;
import metier.Note;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class EspaceNote extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBox cmbFiliere;
	private JComboBox cmbMatiere;
	private JTextField cin;
	private JTextField examen;
	private JTextField TP;
	private JTextField Orale;
	private JPanel panel;
	private JButton btnAnnuler;
	private JButton btnModif;
	private JButton btnAjout;
	private JLabel lblTP;
	private JTextField Ds2;
	private JTextField DS1;
	private JLabel lblDs2;
	private JLabel lblDs1;
	private JLabel lblOrale;
	private JLabel lblExamen;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EspaceNote frame = new EspaceNote();
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
	public EspaceNote() {
		setBorder(null);
		setBounds(100, 100, 876, 516);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 96, 482, 349);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Note note;
				MetierNote metierNote = new MetierNote();

				if (cmbMatiere.getSelectedIndex() == 0) {
					JOptionPane.showConfirmDialog(null, "selectionner matiere", "Message", JOptionPane.OK_OPTION);

				} else {

					String matier = String.valueOf(cmbMatiere.getSelectedItem());

					int ligneSelectionne = table.getSelectedRow();
					int id_etudiant = (Integer) table.getValueAt(ligneSelectionne, 0);

					int id_matiere = new MetierMatiere().getIdMatiere(matier);
					Matiere mat = new MetierMatiere().getMatiereById(id_matiere);

					if (metierNote.checkExist("select * FROM note  where id_matiere='" + id_matiere
							+ "' and id_etudiant='" + id_etudiant + "'")) {

						panel.setVisible(true);
						emty();
						note = metierNote.getNoteMatiereByEtudiant(id_matiere, id_etudiant);
						Orale.setText(String.valueOf(note.getNote_Orale()));
						if (mat.getDS()) {
							isVisible(true, false);
							DS1.setText(String.valueOf(note.getNote_Ds1()));
							Ds2.setText(String.valueOf(note.getNote_Ds2()));

						} else {
							isVisible(false, true);

							examen.setText(String.valueOf(note.getNote_examen()));

							TP.setText(String.valueOf(note.getNote_tp()));
						}
					} else {
						panel.setVisible(true);
						emty();
						if (mat.getDS()) {

							isVisible(true, false);

						} else {

							isVisible(false, true);

						}
					}
				}

			}
		});

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		new Model().liste(table, new MetiereEtudiant().getAllEtudiantResultSet());
		scrollPane.setViewportView(table);

		cmbFiliere = new JComboBox();
		cmbFiliere.addActionListener(new EcouteurNote(this, "cmbFiliere"));
		cmbFiliere.setBounds(32, 94, 148, 20);

		cmbFiliere.setModel(new DefaultComboBoxModel(new String[] { "Choisir Filiere" }));
		MetierFiliere mf = new MetierFiliere();
		List<Filiere> f = mf.getAllFiliere();
		for (Filiere filiere : f) {
			cmbFiliere.addItem(filiere.getAbriviation());
		}

		getContentPane().add(cmbFiliere);

		cmbMatiere = new JComboBox();
		cmbMatiere.setEnabled(false);
		cmbMatiere.addActionListener(new EcouteurNote(this, "cmbMatiere"));
		cmbMatiere.setModel(new DefaultComboBoxModel(new String[] { "Choisir matiere" }));
		cmbMatiere.setBounds(190, 94, 164, 20);
		getContentPane().add(cmbMatiere);

		cin = new JTextField();
		cin.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (!cin.getText().equals("")) {
				try {
					ResultSet rs = new MetiereEtudiant().find(
								"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',abriviation 'filiere',niveau,moyenne,etat from etudiant join filiere using (id_filiere) where cin like '%"
										+ Integer.parseInt(cin.getText()) + "%'");
						if (rs != null) {
							new Model().liste(table, rs);
						}
					

				} catch (NumberFormatException e1) {
					JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
							JOptionPane.CLOSED_OPTION);
				
				}
				}
			}
		});
		
		
		cin.setHorizontalAlignment(SwingConstants.CENTER);

		cin.setBounds(592, 52, 180, 20);
		getContentPane().add(cin);
		cin.setColumns(10);

		panel = new JPanel();
		panel.setVisible(false);
		panel.setBounds(32, 125, 342, 298);
		getContentPane().add(panel);
		panel.setLayout(null);

		examen = new JTextField();
		examen.setColumns(10);
		examen.setBounds(168, 45, 164, 20);
		panel.add(examen);

		TP = new JTextField();
		TP.setColumns(10);
		TP.setBounds(168, 87, 164, 20);
		panel.add(TP);

		lblExamen = new JLabel("Examen");
		lblExamen.setForeground(Color.BLACK);
		lblExamen.setFont(new Font("Dialog", Font.BOLD, 14));
		lblExamen.setBounds(26, 46, 142, 14);
		panel.add(lblExamen);

		lblTP = new JLabel("TP");
		lblTP.setForeground(Color.BLACK);
		lblTP.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTP.setBounds(26, 88, 142, 14);
		panel.add(lblTP);

		btnAjout = new JButton("Ajouter");
		btnAjout.addActionListener(new EcouteurNote(this, "Ajout"));

		btnAjout.setBounds(108, 180, 89, 23);
		panel.add(btnAjout);

		btnModif = new JButton("Modifier");
		btnModif.addActionListener(new EcouteurNote(this, "Modifier"));
		btnModif.setBounds(108, 225, 89, 23);
		panel.add(btnModif);

		lblOrale = new JLabel("Orale");
		lblOrale.setForeground(Color.BLACK);
		lblOrale.setFont(new Font("Dialog", Font.BOLD, 14));
		lblOrale.setBounds(26, 132, 142, 14);
		panel.add(lblOrale);

		Orale = new JTextField();
		Orale.setColumns(10);
		Orale.setBounds(168, 131, 164, 20);
		panel.add(Orale);

		btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				cmbFiliere.setSelectedIndex(0);
				cmbMatiere.setSelectedIndex(0);
				emty();
			}
		});
		btnAnnuler.setBounds(108, 264, 89, 23);
		panel.add(btnAnnuler);

		Ds2 = new JTextField();
		Ds2.setColumns(10);
		Ds2.setBounds(168, 87, 164, 20);
		panel.add(Ds2);

		lblDs2 = new JLabel("DS2");
		lblDs2.setForeground(Color.BLACK);
		lblDs2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDs2.setBounds(26, 88, 142, 14);
		panel.add(lblDs2);

		lblDs1 = new JLabel("Ds1");
		lblDs1.setForeground(Color.BLACK);
		lblDs1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDs1.setBounds(26, 45, 142, 14);
		panel.add(lblDs1);

		DS1 = new JTextField();
		DS1.setColumns(10);
		DS1.setBounds(168, 44, 164, 20);
		panel.add(DS1);
		
		lblNewLabel_1 = new JLabel("Cin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(510, 52, 72, 17);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(EspaceNote.class.getResource("/Image/Annotation 2020-05-27 110214.png")));
		lblNewLabel.setBounds(0, 0, 876, 489);
		getContentPane().add(lblNewLabel);

	}

	public void emty() {
		examen.setText("");
		DS1.setText("");
		Ds2.setText("");
		Orale.setText("");
		TP.setText("");

	}

	public void isVisible(Boolean enbled1, Boolean enbled2) {

		lblDs1.setVisible(enbled1);
		lblDs2.setVisible(enbled1);
		lblExamen.setVisible(enbled2);
		examen.setVisible(enbled2);
		TP.setVisible(enbled2);
		lblTP.setVisible(enbled2);
		DS1.setVisible(enbled1);
		Ds2.setVisible(enbled1);

	}

	public JComboBox getCmbFiliere() {
		return cmbFiliere;
	}

	public JButton getBtnAjout() {
		return btnAjout;
	}

	public JButton getBtnModif() {
		return btnModif;
	}

	public JComboBox getCmbMatiere() {
		return cmbMatiere;
	}

	public JTextField getCin() {
		return cin;
	}

	

	public JTable getTable() {
		return table;
	}

	public JTextField getExamen() {
		return examen;
	}

	public JTextField getTP() {
		return TP;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public JLabel getLblTP() {
		return lblTP;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JTextField getOrale() {
		return Orale;
	}

	public JTextField getDs2() {
		return Ds2;
	}

	public JTextField getDS1() {
		return DS1;
	}

}
