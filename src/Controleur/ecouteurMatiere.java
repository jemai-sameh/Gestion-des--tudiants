package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.ModelMatiere;
import Vue.EspaceMatiere;
import metier.Enseignant;
import metier.Matiere;
import metier.MetierEnseignant;
import metier.MetierMatiere;

public class ecouteurMatiere implements ActionListener {
	EspaceMatiere espaceMatiere;
	String type;

	public ecouteurMatiere(EspaceMatiere espaceMatiere, String type) {
		super();
		this.espaceMatiere = espaceMatiere;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String matier, enseignant;
		Boolean comporteTP;
		Float coefficient;
		Integer credit;
		switch (type) {
		case "Ajout":

			if (espaceMatiere.getMatiere().getText().isEmpty()
					|| espaceMatiere.getCmbEnseignantNouveau().getSelectedIndex() == 0
					|| espaceMatiere.getCmbCreditnouveau().getSelectedIndex() == 0
					|| espaceMatiere.getCmbCoefficientNouveau().getSelectedIndex() == 0) {
				JOptionPane.showConfirmDialog(null, "remlir tous les champs");
			} else {
				matier = espaceMatiere.getMatiere().getText();
				credit = Integer.parseInt(String.valueOf(espaceMatiere.getCmbCreditnouveau().getSelectedItem()));
				coefficient = Float
						.parseFloat(String.valueOf(espaceMatiere.getCmbCoefficientNouveau().getSelectedItem()));
				if (espaceMatiere.getBtnrdDSNouveau().isSelected()) {
					comporteTP = true;
				} else {
					comporteTP = false;
				}
				enseignant = String.valueOf(espaceMatiere.getCmbEnseignantNouveau().getSelectedItem());

				Enseignant et = new MetierEnseignant().getEnseignant(enseignant);
				new MetierMatiere().Add(new Matiere(matier, coefficient, credit, comporteTP, et));
				espaceMatiere.emptyComponent("ajout");

			}
			break;
		case "Modifier":

			if (espaceMatiere.getCmbMatiereModif().getSelectedIndex() == 0
					|| espaceMatiere.getCmbEnseignantModif().getSelectedIndex() == 0
					|| espaceMatiere.getCmbCreditModif().getSelectedIndex() == 0
					|| espaceMatiere.getCmbCoefficientModif().getSelectedIndex() == 0) {
				JOptionPane.showConfirmDialog(null, "remlir tous les champs");
			} else {
				matier = String.valueOf(espaceMatiere.getCmbMatiereModif().getSelectedItem());
				credit = Integer.parseInt(String.valueOf(espaceMatiere.getCmbCreditModif().getSelectedItem()));
				coefficient = Float
						.parseFloat(String.valueOf(espaceMatiere.getCmbCoefficientModif().getSelectedItem()));
				if (espaceMatiere.getBtnrdDSMdif().isSelected()) {
					comporteTP = true;
				} else {
					comporteTP = false;
				}
				enseignant = String.valueOf(espaceMatiere.getCmbEnseignantModif().getSelectedItem());

				Enseignant et = new MetierEnseignant().getEnseignant(enseignant);
				new MetierMatiere().update(new Matiere(matier, coefficient, credit, comporteTP, et));
				espaceMatiere.emptyComponent("Modif");

			}
			break;
		case "supprimer":
			if (espaceMatiere.getCmbMatiereModif().getSelectedItem().equals("Choisir Matiere")) {
				JOptionPane.showConfirmDialog(null, "choisir matiere", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				Integer x = JOptionPane.showConfirmDialog(null, "Veuillez vous supprimer Matiere", "ERREUR",
						JOptionPane.YES_NO_OPTION);
				if (x == 0) {
					new MetierMatiere().delete(String.valueOf(espaceMatiere.getCmbMatiereModif().getSelectedItem()));
					new ModelMatiere(espaceMatiere.getCmbMatiereModif())
							.remplirMatiere(new MetierMatiere().getAllMatiere());
				}

			}
			break;
		case "Modif":
			if (espaceMatiere.getCmbMatiereModif().getSelectedItem().equals("Choisir Matiere")) {
				JOptionPane.showConfirmDialog(null, "choisir matiere", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				Matiere mt = new MetierMatiere()
						.getMatiereByNom(String.valueOf(espaceMatiere.getCmbMatiereModif().getSelectedItem()));
				espaceMatiere.getCmbEnseignantModif().setSelectedItem(mt.getEnseignant().getNom());
				espaceMatiere.getCmbCreditModif().setSelectedItem(String.valueOf(mt.getCredit()));
				String coef = String.valueOf(mt.getCoefficient());
				if (coef.equals("1.5")) {
					espaceMatiere.getCmbCoefficientModif().setSelectedItem(coef);
				} else {
					espaceMatiere.getCmbCoefficientModif().setSelectedItem(coef.substring(0, coef.indexOf(".")));
				}
				if (mt.getDS()) {
					espaceMatiere.getBtnrdDSMdif().setSelected(true);
				}
				espaceMatiere.getPanelModif().setVisible(true);
			}
			break;

		}
	}

}
