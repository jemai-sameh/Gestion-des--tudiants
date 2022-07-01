package Controleur;

/**
 * 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Model.ModelMois;
import Model.ModelValidateur;
import Vue.EspaceEtudiant;
import metier.Filiere;
import metier.Identifiant;
import metier.MetierFiliere;
import metier.MetiereEtudiant;

/**
 * @author sameh jemai
 *
 */
public class EcouteurAjoutEtudiant implements ActionListener {

	private EspaceEtudiant espaceEtudiant;
	MetiereEtudiant metiereEtudiant = new MetiereEtudiant();
	ModelValidateur modelValidateur = new ModelValidateur();
	MetierFiliere metierFiliere = new MetierFiliere();

	/**
	 * 
	 */
	public EcouteurAjoutEtudiant(EspaceEtudiant espaceEtudiant) {
		// TODO Auto-generated constructor stub
		this.espaceEtudiant = espaceEtudiant;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Filiere filiere;
		Integer cin, telephone;
		String date_naissance, niveau, nom, prenom, adresse, sexe = "", login, password = "", pays, gouvernourat, jour,
				mois = "", annee;
		try {

			if (espaceEtudiant.getCinajout().getText().isEmpty() || espaceEtudiant.getNomajout().getText().isEmpty()
					|| espaceEtudiant.getNomajout().getText().isEmpty()
					|| espaceEtudiant.getTelephoneajout().getText().isEmpty()
					|| espaceEtudiant.getTextAdresse().getText().isEmpty()
					|| espaceEtudiant.getLoginajout().getText().isEmpty()
					|| espaceEtudiant.getPasswordAjout().getPassword().length == 0) {
				JOptionPane.showConfirmDialog(null, "Remplir tous les champs ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (!(espaceEtudiant.getFemme().isSelected() || espaceEtudiant.getHomme().isSelected())) {
				JOptionPane.showConfirmDialog(null, "selection sexe", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (espaceEtudiant.getCinajout().getText().length() != 8
					&& espaceEtudiant.getTelephoneajout().getText().length() != 8) {
				JOptionPane.showConfirmDialog(null, "cin ou numero telephone non valider ", "ERREUR",
						JOptionPane.CLOSED_OPTION);

			} else if (espaceEtudiant.getPaysAjout().getSelectedItem().toString().equals("choisir Pays ")) {
				JOptionPane.showConfirmDialog(null, "choisir Pays", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (espaceEtudiant.getGovAjout().getSelectedItem().toString().equals("choisir Gouvernourat")) {
				JOptionPane.showConfirmDialog(null, "choisir Gouvernourat", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (espaceEtudiant.getCmbNiveau().getSelectedItem().toString().equals("choisir niveau")) {
				JOptionPane.showConfirmDialog(null, "choisir niveau", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (espaceEtudiant.getCmbFiliere().getSelectedItem().toString().equals("choisir filiere")) {
				JOptionPane.showConfirmDialog(null, "choisir filiere", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (!modelValidateur.isValidEmailAddress(espaceEtudiant.getLoginajout().getText())) {
				JOptionPane.showConfirmDialog(null, "login non valide", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (!(modelValidateur.isValideTelephoneOrCin(espaceEtudiant.getTelephoneajout().getText()))
					|| (!(modelValidateur.isValideTelephoneOrCin(espaceEtudiant.getCinajout().getText())))) {
				JOptionPane.showConfirmDialog(null, "numero telephone ou cin non valider ", "ERREUR",
						JOptionPane.CLOSED_OPTION);

			} else {
				cin = Integer.parseInt(espaceEtudiant.getCinajout().getText());
				telephone = Integer.parseInt(espaceEtudiant.getTelephoneajout().getText());

				nom = espaceEtudiant.getNomajout().getText();
				prenom = espaceEtudiant.getPrenomajout().getText();

				if (espaceEtudiant.getFemme().isSelected()) {
					sexe = "Femme";
				}
				if (espaceEtudiant.getHomme().isSelected()) {
					sexe = "Homme";
				}

				if (espaceEtudiant.getJour().getValue().toString().length() == 1) {
					jour = "0" + espaceEtudiant.getJour().getValue().toString();
				} else {
					jour = espaceEtudiant.getJour().getValue().toString();
				}
				mois = new ModelMois().NumberMois(espaceEtudiant.getMois().getValue().toString());
				annee = espaceEtudiant.getAnneeModif().getValue().toString();

				date_naissance = annee + "-" + mois + "-" + jour;
				adresse = espaceEtudiant.getTextAdresse().getText();

				pays = espaceEtudiant.getPaysAjout().getSelectedItem().toString();

				gouvernourat = espaceEtudiant.getGovAjout().getSelectedItem().toString();

				login = espaceEtudiant.getLoginajout().getText();

				char[] pass = espaceEtudiant.getPasswordAjout().getPassword();
				for (char c : pass) {
					password += c;
				}

				niveau = espaceEtudiant.getCmbNiveau().getSelectedItem().toString();
				filiere = new MetierFiliere()
						.getFiliereByNom(espaceEtudiant.getCmbFiliere().getSelectedItem().toString());

				Identifiant identifiant = new Identifiant(cin, nom, prenom, telephone, adresse, date_naissance, sexe,
						login, password, pays, gouvernourat);

				metiereEtudiant.Add(identifiant, niveau, filiere);
				espaceEtudiant.emptyComponent("Ajout");
			}

		} catch (NumberFormatException e2) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e2.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

}
