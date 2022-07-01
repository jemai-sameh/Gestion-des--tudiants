package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.ModelMois;
import Model.ModelValidateur;
import Vue.EspaceEnseignant;
import metier.Identifiant;
import metier.MetierEnseignant;

public class EcouteurAjoutEnseignant implements ActionListener {

	private EspaceEnseignant espaceEnseignant;
	ModelValidateur modelValidateur = new ModelValidateur();
	MetierEnseignant metierEnseignant = new MetierEnseignant();

	/**
	 * 
	 */
	public EcouteurAjoutEnseignant(EspaceEnseignant espaceEnseignant) {
		this.espaceEnseignant = espaceEnseignant;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Integer cin, telephone;
		String date_naissance, nom, prenom, adresse, sexe = "", login, password = "", pays, gouvernourat, jour,
				mois = "", annee;
		try {

			if (espaceEnseignant.getCinajout().getText().isEmpty() || espaceEnseignant.getNomajout().getText().isEmpty()
					|| espaceEnseignant.getNomajout().getText().isEmpty()
					|| espaceEnseignant.getTelephoneajout().getText().isEmpty()
					|| espaceEnseignant.getTextAdresse().getText().isEmpty()
					|| espaceEnseignant.getLoginajout().getText().isEmpty()
					|| espaceEnseignant.getPasswordAjout().getPassword().length == 0) {
				JOptionPane.showConfirmDialog(null, "Remplir tous les champs ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (!(espaceEnseignant.getFemme().isSelected() || espaceEnseignant.getHomme().isSelected())) {
				JOptionPane.showConfirmDialog(null, "selection sexe", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (espaceEnseignant.getCinajout().getText().length() != 8
					&& espaceEnseignant.getTelephoneajout().getText().length() != 8) {
				JOptionPane.showConfirmDialog(null, "cin ou numero telephone non valider ", "ERREUR",
						JOptionPane.CLOSED_OPTION);

			} else if (!modelValidateur.isValidEmailAddress(espaceEnseignant.getLoginajout().getText())) {
				JOptionPane.showConfirmDialog(null, "login non valide", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (!(modelValidateur.isValideTelephoneOrCin(espaceEnseignant.getTelephoneajout().getText()))
					|| (!(modelValidateur.isValideTelephoneOrCin(espaceEnseignant.getCinajout().getText())))) {
				JOptionPane.showConfirmDialog(null, "numero telephone ou cin non valider ", "ERREUR",
						JOptionPane.CLOSED_OPTION);

			} else if (espaceEnseignant.getPaysAjout().getSelectedItem().toString().equals("choisir Pays ")) {
				JOptionPane.showConfirmDialog(null, "choisir Pays", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (espaceEnseignant.getGovAjout().getSelectedItem().toString().equals("choisir Gouvernourat")) {
				JOptionPane.showConfirmDialog(null, "choisir Gouvernourat", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else {
				cin = Integer.parseInt(espaceEnseignant.getCinajout().getText());
				telephone = Integer.parseInt(espaceEnseignant.getTelephoneajout().getText());

				nom = espaceEnseignant.getNomajout().getText();
				prenom = espaceEnseignant.getPrenomajout().getText();

				if (espaceEnseignant.getFemme().isSelected()) {
					sexe = "Femme";
				}
				if (espaceEnseignant.getHomme().isSelected()) {
					sexe = "Homme";
				}

				if (espaceEnseignant.getJour().getValue().toString().length() == 1) {
					jour = "0" + espaceEnseignant.getJour().getValue().toString();
				} else {
					jour = espaceEnseignant.getJour().getValue().toString();
				}
				mois = new ModelMois().NumberMois(espaceEnseignant.getMois().getValue().toString());
				annee = espaceEnseignant.getAnneeModif().getValue().toString();

				date_naissance = annee + "-" + mois + "-" + jour;
				adresse = espaceEnseignant.getTextAdresse().getText();

				pays = espaceEnseignant.getPaysAjout().getSelectedItem().toString();

				gouvernourat = espaceEnseignant.getGovAjout().getSelectedItem().toString();

				login = espaceEnseignant.getLoginajout().getText();

				char[] pass = espaceEnseignant.getPasswordAjout().getPassword();
				for (char c : pass) {
					password += c;
				}

				Identifiant identifiant = new Identifiant(cin, nom, prenom, telephone, adresse, date_naissance, sexe,
						login, password, pays, gouvernourat);

				metierEnseignant.Add(identifiant);
				espaceEnseignant.emptyComponent("Ajout");
			}

		} catch (NumberFormatException e2) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e2.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

}
