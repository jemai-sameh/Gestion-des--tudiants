package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.Model;
import Model.ModelMatiere;
import Model.Moyenne;
import Vue.EspaceNote;
import metier.Etudiant;
import metier.Matiere;
import metier.MetierFiliere;
import metier.MetierMatiere;
import metier.MetierNote;
import metier.MetiereEtudiant;
import metier.Note;

public class EcouteurNote implements ActionListener {
	private EspaceNote espaceNote;
	private String type;
	private Note note;

	private MetierNote metierNote = new MetierNote();
	Double examen, DS1, DS2, tp, Orale;

	public EcouteurNote(EspaceNote espaceNote, String type) {
		super();
		this.espaceNote = espaceNote;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (type) {
		case "cmbMatiere":
			if (espaceNote.getCmbFiliere().getSelectedIndex() == 0) {
				JOptionPane.showConfirmDialog(espaceNote, "Choisir Filiere", "Message", JOptionPane.OK_OPTION);
				break;

			}
			if (espaceNote.getCmbMatiere().getSelectedIndex() != 0) {
				espaceNote.getPanel().setVisible(false);
				JOptionPane.showConfirmDialog(espaceNote, "Selectionner etudiant", "Message", JOptionPane.OK_OPTION);

			} else {
				espaceNote.getPanel().setVisible(false);
			}

			break;
		case "cmbFiliere":
			if (espaceNote.getCmbFiliere().getSelectedIndex() != 0) {
				espaceNote.getCmbMatiere().setEnabled(true);
				// remplir combobox
				int id = new MetierFiliere()
						.getIdByAbriviationFiliere(String.valueOf(espaceNote.getCmbFiliere().getSelectedItem()));
				new ModelMatiere(espaceNote.getCmbMatiere())
						.remplirMatiere(new MetierMatiere().getAllMatiereByFiliere(id));

				new Model().liste(espaceNote.getTable(), new MetiereEtudiant().getEtudiantResultSet(
						"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',abriviation 'filiere',niveau,moyenne,etat,somme_credit from etudiant join filiere using (id_filiere)   where etudiant.id_filiere="
								+ id));

			}

			break;
		case "Ajout":
			String matier = String.valueOf(espaceNote.getCmbMatiere().getSelectedItem());
			int id_matiere = new MetierMatiere().getIdMatiere(matier);
			Matiere mat = new MetierMatiere().getMatiereById(id_matiere);
			if (mat.getDS() == true) {
				if (espaceNote.getDS1().getText().isEmpty() || espaceNote.getDs2().getText().isEmpty()
						|| espaceNote.getOrale().getText().isEmpty()) {
					JOptionPane.showConfirmDialog(espaceNote, "remplir tous les champs", "Message",
							JOptionPane.OK_OPTION);
					break;
				}

			} else {
				if (espaceNote.getExamen().getText().isEmpty() || espaceNote.getTP().getText().isEmpty()
						|| espaceNote.getOrale().getText().isEmpty()) {
					JOptionPane.showConfirmDialog(espaceNote, "remplir tous les champs", "Message",
							JOptionPane.OK_OPTION);
					break;
				}

			}

			int ligneSelectionne = espaceNote.getTable().getSelectedRow();
			int id_etudiant = (Integer) espaceNote.getTable().getValueAt(ligneSelectionne, 0);

			try {
				Etudiant x = new MetiereEtudiant().getEtudiant(
						"select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,somme_credit,id_filiere from etudiant where num_etudiant="
								+ id_etudiant);

				if (!metierNote.checkExist(
						"select * FROM note  where id_matiere=" + id_matiere + " and id_etudiant=" + id_etudiant)) {
					if (Double.parseDouble(espaceNote.getOrale().getText()) < 0
							|| Double.parseDouble(espaceNote.getOrale().getText()) > 20) {
						JOptionPane.showConfirmDialog(espaceNote, "note doit être entre [0,20]", "Message",
								JOptionPane.OK_OPTION);
						break;
					}
					Orale = Double.parseDouble(espaceNote.getOrale().getText());

					if (mat.getDS()) {
						if (Double.parseDouble(espaceNote.getDS1().getText()) < 0
								|| Double.parseDouble(espaceNote.getDS1().getText()) > 20
								|| Double.parseDouble(espaceNote.getDs2().getText()) < 0
								|| Double.parseDouble(espaceNote.getDs2().getText()) > 20) {
							JOptionPane.showConfirmDialog(espaceNote, "note doit être entre [0,20]", "Message",
									JOptionPane.OK_OPTION);
							break;
						}
						DS1 = Double.parseDouble(espaceNote.getDS1().getText());
						DS2 = Double.parseDouble(espaceNote.getDs2().getText());

						note = metierNote.getNoteMatiereByEtudiant(id_matiere, id_etudiant);
						metierNote.Add(new Note(mat, x, Orale, DS1, DS2, true));
					} else {
						if (Double.parseDouble(espaceNote.getExamen().getText()) < 0
								|| Double.parseDouble(espaceNote.getExamen().getText()) > 20
								|| Double.parseDouble(espaceNote.getTP().getText()) < 0
								|| Double.parseDouble(espaceNote.getTP().getText()) > 20) {
							JOptionPane.showConfirmDialog(espaceNote, "note doit être entre [0,20]", "Message",
									JOptionPane.OK_OPTION);
							break;
						}
						examen = Double.parseDouble(espaceNote.getExamen().getText());
						tp = Double.parseDouble(espaceNote.getTP().getText());

						note = metierNote.getNoteMatiereByEtudiant(id_matiere, id_etudiant);
						metierNote.Add(new Note(mat, x, examen, tp, Orale));

					}

				} else {
					JOptionPane.showConfirmDialog(espaceNote, "note est déja ajouter", "Message",
							JOptionPane.OK_OPTION);

				}

				new Moyenne().load();

			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
						JOptionPane.CLOSED_OPTION);
			}
			break;
		case "Modifier":

			String matie = String.valueOf(espaceNote.getCmbMatiere().getSelectedItem());
			int id_matier = new MetierMatiere().getIdMatiere(matie);
			Matiere mati = new MetierMatiere().getMatiereById(id_matier);

			if (mati.getDS()) {
				if (espaceNote.getDS1().getText().isEmpty() || espaceNote.getDs2().getText().isEmpty()
						|| espaceNote.getOrale().getText().isEmpty()) {
					JOptionPane.showConfirmDialog(espaceNote, "remplir tous les champs", "Message",
							JOptionPane.OK_OPTION);
					break;
				}

			} else {
				if (espaceNote.getExamen().getText().isEmpty() || espaceNote.getTP().getText().isEmpty()
						|| espaceNote.getOrale().getText().isEmpty()) {
					JOptionPane.showConfirmDialog(espaceNote, "remplir tous les champs", "Message",
							JOptionPane.OK_OPTION);
					break;
				}

			}

			int ligneSelectionn = espaceNote.getTable().getSelectedRow();
			int id_etudian = Integer.parseInt(String.valueOf(espaceNote.getTable().getValueAt(ligneSelectionn, 0)));

			try {
				Etudiant etu = new MetiereEtudiant().getEtudiant(
						"select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,somme_credit,id_filiere from etudiant where num_etudiant="
								+ id_etudian );
				if (metierNote.checkExist(
						"select * FROM note  where id_matiere='" + id_matier + "' and id_etudiant='" + id_etudian+"'")) {
					if (Double.parseDouble(espaceNote.getOrale().getText()) < 0
							|| Double.parseDouble(espaceNote.getOrale().getText()) > 20) {
						JOptionPane.showConfirmDialog(espaceNote, "note doit être entre [0,20]", "Message",
								JOptionPane.OK_OPTION);
						break;
					}
					Orale = Double.parseDouble(espaceNote.getOrale().getText());

					if (mati.getDS()) {
						if (Double.parseDouble(espaceNote.getDS1().getText()) < 0
								|| Double.parseDouble(espaceNote.getDS1().getText()) > 20
								|| Double.parseDouble(espaceNote.getDs2().getText()) < 0
								|| Double.parseDouble(espaceNote.getDs2().getText()) > 20) {
							JOptionPane.showConfirmDialog(espaceNote, "note doit être entre [0,20]", "Message",
									JOptionPane.OK_OPTION);
							break;
						}
						DS1 = Double.parseDouble(espaceNote.getDS1().getText());
						DS2 = Double.parseDouble(espaceNote.getDs2().getText());

						note = metierNote.getNoteMatiereByEtudiant(id_matier, id_etudian);
						metierNote.Update(new Note(mati, etu, Orale, DS1, DS2, true));

					} else {
						if (Double.parseDouble(espaceNote.getExamen().getText()) < 0
								|| Double.parseDouble(espaceNote.getExamen().getText()) > 20
								|| Double.parseDouble(espaceNote.getTP().getText()) < 0
								|| Double.parseDouble(espaceNote.getTP().getText()) > 20) {
							JOptionPane.showConfirmDialog(espaceNote, "note doit être entre [0,20]", "Message",
									JOptionPane.OK_OPTION);
							break;
						}
						examen = Double.parseDouble(espaceNote.getExamen().getText());
						tp = Double.parseDouble(espaceNote.getTP().getText());

						note = metierNote.getNoteMatiereByEtudiant(id_matier, id_etudian);
						metierNote.Update(new Note(mati, etu, examen, tp, Orale));

					}

				} else {
					JOptionPane.showConfirmDialog(espaceNote, "note n'est pas ajouter ", "Message",
							JOptionPane.OK_OPTION);

				}
				new Moyenne().load();
			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
						JOptionPane.CLOSED_OPTION);
			}
			break;

		}

	}

}
