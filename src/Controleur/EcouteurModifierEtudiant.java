package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.ModelMois;
import Model.ModelValidateur;
import Vue.EspaceEtudiant;
import metier.Filiere;
import metier.Identifiant;
import metier.MetierFiliere;
import metier.MetiereEtudiant;

public class EcouteurModifierEtudiant implements ActionListener {
	private EspaceEtudiant espaceEtudiant;
	MetiereEtudiant metiereEtudiant = new MetiereEtudiant();
	ModelValidateur modelValidateur = new ModelValidateur();

	public EcouteurModifierEtudiant(EspaceEtudiant espaceEtudiant) {
		this.espaceEtudiant = espaceEtudiant;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Filiere filiere;
		Integer cin = 0, telephone = 0, numEtudiant = 0;
		String date_naissance, niveau, nom, prenom, adresse, sexe = "", login, password = "", pays, gouvernourat, jour,
				mois = "", annee;
		try {
			espaceEtudiant.getCinModif().setEnabled(false);
			if (espaceEtudiant.getCBoxCin().isSelected()) {
				cin = Integer.parseInt(espaceEtudiant.getCinModif().getText());
				ResultSet rs = metiereEtudiant.find(
						"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',abriviation 'filiere',niveau,moyenne,etat,somme_credit from etudiant join filiere using (id_filiere) where cin="
								+ cin);
				if (rs.next()) {
					numEtudiant = rs.getInt("Numero");

				}

			} else {
				numEtudiant = (Integer) espaceEtudiant.getNumEt().getSelectedItem();
				ResultSet rs = metiereEtudiant.FindById(numEtudiant);
				if (rs != null) {
					cin = rs.getInt("cin");
				}
			}

			if (espaceEtudiant.getPrenomModif().getText().isEmpty() || espaceEtudiant.getNomModif().getText().isEmpty()
					|| espaceEtudiant.getTelephoneModif().getText().isEmpty()
					|| espaceEtudiant.getAdresseModif().getText().isEmpty()
					|| espaceEtudiant.getLoginModif().getText().isEmpty()
					|| espaceEtudiant.getPasswordModif().getPassword().length == 0) {
				JOptionPane.showConfirmDialog(null, "Remplir tous les champs ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (!(espaceEtudiant.getFemmeModif().isSelected() || espaceEtudiant.getHommeModif().isSelected())) {
				JOptionPane.showConfirmDialog(null, "selection sexe", "ERREUR", JOptionPane.WARNING_MESSAGE);

			} else if (espaceEtudiant.getCmbNiveauModif().getSelectedItem().toString().equals("choisir niveau")) {
				JOptionPane.showConfirmDialog(null, "choisir niveau", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (espaceEtudiant.getCmbFiliereModif().getSelectedItem().toString().equals("choisir filiere")) {
				JOptionPane.showConfirmDialog(null, "choisir filiere", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (espaceEtudiant.getPaysModif().getSelectedItem().toString().equals("choisir Pays ")) {
				JOptionPane.showConfirmDialog(null, "choisir Pays", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else if (espaceEtudiant.getGovModif().getSelectedItem().toString().equals("choisir Gouvernourat")) {
				JOptionPane.showConfirmDialog(null, "choisir Gouvernourat", "ERREUR", JOptionPane.CLOSED_OPTION);

			} else if (!modelValidateur.isValideTelephoneOrCin(espaceEtudiant.getTelephoneModif().getText())) {
				JOptionPane.showConfirmDialog(null, "numero telephone non valider ", "ERREUR",
						JOptionPane.CLOSED_OPTION);
			} else if (!modelValidateur.isValidEmailAddress(espaceEtudiant.getLoginModif().getText())) {
				JOptionPane.showConfirmDialog(null, "login in valide", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				telephone = Integer.parseInt(espaceEtudiant.getTelephoneModif().getText());

				nom = espaceEtudiant.getNomModif().getText();
				prenom = espaceEtudiant.getPrenomModif().getText();

				if (espaceEtudiant.getFemmeModif().isSelected()) {
					sexe = "Femme";
				}
				if (espaceEtudiant.getHommeModif().isSelected()) {
					sexe = "Homme";
				}

				if (espaceEtudiant.getJourModif().getValue().toString().length() == 1) {
					jour = "0" + espaceEtudiant.getJourModif().getValue().toString();
				} else {
					jour = espaceEtudiant.getJourModif().getValue().toString();
				}

				mois = new ModelMois().NumberMois(espaceEtudiant.getMoisModif().getValue().toString());

				annee = espaceEtudiant.getAnneeModif().getValue().toString();

				date_naissance = annee + "-" + mois + "-" + jour;
				adresse = espaceEtudiant.getAdresseModif().getText();

				pays = espaceEtudiant.getPaysModif().getSelectedItem().toString();

				gouvernourat = espaceEtudiant.getGovModif().getSelectedItem().toString();

				login = espaceEtudiant.getLoginModif().getText();

				char[] pass = espaceEtudiant.getPasswordModif().getPassword();
				for (char c : pass) {
					password += c;
				}
				niveau = espaceEtudiant.getCmbNiveauModif().getSelectedItem().toString();
				filiere = new MetierFiliere()
						.getFiliereByNom(espaceEtudiant.getCmbFiliereModif().getSelectedItem().toString());
				Identifiant identifiant = new Identifiant(cin, nom, prenom, telephone, adresse, date_naissance, sexe,
						login, password, pays, gouvernourat);
				metiereEtudiant.Update(identifiant, niveau, filiere, numEtudiant);
			}
		} catch (NumberFormatException e2) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e2.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		} catch (SQLException e1) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e1.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

}
