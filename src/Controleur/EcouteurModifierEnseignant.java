package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.ModelMois;
import Model.ModelValidateur;
import Vue.EspaceEnseignant;
import metier.Identifiant;
import metier.MetierEnseignant;

public class EcouteurModifierEnseignant implements ActionListener {
	private EspaceEnseignant espaceEnseignant;
	MetierEnseignant metiereEnseignant = new MetierEnseignant();
	ModelValidateur modelValidateur = new ModelValidateur();

	public EcouteurModifierEnseignant(EspaceEnseignant espaceEnseignant) {
		this.espaceEnseignant = espaceEnseignant;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Integer cin = 0, telephone = 0, numEnseignant = 0;
		String date_naissance, nom, prenom, adresse, sexe = "", login, password = "", pays, gouvernourat, jour,
				mois = "", annee;
		try {

			if (espaceEnseignant.getCBoxCin().isSelected()) {
				cin = Integer.parseInt(espaceEnseignant.getCinModif().getText());
				ResultSet rs = metiereEnseignant.FindEnseignant("select * from enseignant where cin=" + cin);
				if (rs != null) {
					numEnseignant = rs.getInt("num_enseignant");

				}

			} else {
				numEnseignant = (Integer) espaceEnseignant.getNumEn().getSelectedItem();
				ResultSet rs = metiereEnseignant.FindById(numEnseignant);
				if (rs != null) {
					cin = rs.getInt("cin");
				}
			}

			if (espaceEnseignant.getPrenomModif().getText().isEmpty()
					|| espaceEnseignant.getNomModif().getText().isEmpty()
					|| espaceEnseignant.getTelephoneModif().getText().isEmpty()
					|| espaceEnseignant.getAdresseModif().getText().isEmpty()
					|| espaceEnseignant.getLoginModif().getText().isEmpty()
					|| espaceEnseignant.getPasswordModif().getPassword().length == 0) {
				JOptionPane.showConfirmDialog(null, "Remplir tous les champs ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (!(espaceEnseignant.getFemmeModif().isSelected()
					|| espaceEnseignant.getHommeModif().isSelected())) {
				JOptionPane.showConfirmDialog(null, "selection sexe", "ERREUR", JOptionPane.WARNING_MESSAGE);

			} else if (espaceEnseignant.getPaysModif().getSelectedItem().toString().equals("choisir Pays ")) {
				JOptionPane.showConfirmDialog(null, "choisir Pays", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (espaceEnseignant.getGovModif().getSelectedItem().toString().equals("choisir Gouvernourat")) {
				JOptionPane.showConfirmDialog(null, "choisir Gouvernourat", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (!modelValidateur.isValideTelephoneOrCin(espaceEnseignant.getTelephoneModif().getText())) {
				JOptionPane.showConfirmDialog(null, "numero telephone non valider ", "ERREUR",
						JOptionPane.CLOSED_OPTION);
			} else if (!modelValidateur.isValidEmailAddress(espaceEnseignant.getLoginModif().getText())) {
				JOptionPane.showConfirmDialog(null, "login in valide", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				telephone = Integer.parseInt(espaceEnseignant.getTelephoneModif().getText());

				nom = espaceEnseignant.getNomModif().getText();
				prenom = espaceEnseignant.getPrenomModif().getText();

				if (espaceEnseignant.getFemmeModif().isSelected()) {
					sexe = "Femme";
				}
				if (espaceEnseignant.getHommeModif().isSelected()) {
					sexe = "Homme";
				}

				if (espaceEnseignant.getJourModif().getValue().toString().length() == 1) {
					jour = "0" + espaceEnseignant.getJourModif().getValue().toString();
				} else {
					jour = espaceEnseignant.getJourModif().getValue().toString();
				}

				mois = new ModelMois().NumberMois(espaceEnseignant.getMoisModif().getValue().toString());

				annee = espaceEnseignant.getAnneeModif().getValue().toString();

				date_naissance = annee + "-" + mois + "-" + jour;
				adresse = espaceEnseignant.getAdresseModif().getText();

				pays = espaceEnseignant.getPaysModif().getSelectedItem().toString();

				gouvernourat = espaceEnseignant.getGovModif().getSelectedItem().toString();

				login = espaceEnseignant.getLoginModif().getText();

				char[] pass = espaceEnseignant.getPasswordModif().getPassword();
				for (char c : pass) {
					password += c;
				}

				Identifiant identifiant = new Identifiant(cin, nom, prenom, telephone, adresse, date_naissance, sexe,
						login, password, pays, gouvernourat);
				metiereEnseignant.Update(identifiant, numEnseignant);
			}
		} catch (NumberFormatException e2) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e2.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		} catch (SQLException e1) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e1.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

}
