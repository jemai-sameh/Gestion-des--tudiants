package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Model;
import Model.ModelMois;
import Model.ModelValidateur;
import Model.Moyenne;
import Vue.EspaceEtudiant;
import Vue.RechercheEtudiant;
import metier.MetierFiliere;
import metier.MetiereEtudiant;

public class EcouteurRechercherEtudiant implements ActionListener {
	private EspaceEtudiant espaceEtudiant;
	private RechercheEtudiant rechercheEtudiant;
	private String rechercher;
	MetiereEtudiant metiereEtudiant = new MetiereEtudiant();
	ModelValidateur modelValidateur = new ModelValidateur();
	MetierFiliere metierFiliere = new MetierFiliere();
	ResultSet rs;

	public EcouteurRechercherEtudiant(EspaceEtudiant espaceEtudiant, String rechercher) {
		this.espaceEtudiant = espaceEtudiant;
		this.rechercher = rechercher;

	}

	public EcouteurRechercherEtudiant(RechercheEtudiant rechercheEtudiant, String rechercher) {
		this.rechercheEtudiant = rechercheEtudiant;
		this.rechercher = rechercher;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		new Moyenne().load();

		switch (this.rechercher) {
		case "rechercheEspaceEtudiant":
			Integer numEtudiant = 0;
			Integer cin = 0;
			try {
				if (espaceEtudiant.getCBoxCin().isSelected()) {
					if (espaceEtudiant.getCinModif().getText().isEmpty()) {
						JOptionPane.showConfirmDialog(null, "entrer Numéro Carte Cin d'etudiant", "Information",
								JOptionPane.CLOSED_OPTION);
					} else {
						cin = Integer.parseInt(espaceEtudiant.getCinModif().getText());
						ResultSet rs = metiereEtudiant.EtudiantByCin(cin);
						if (rs != null) {
							espaceEtudiant.getNumEt().setSelectedItem(rs.getInt("num_etudiant"));
							recupère(cin, "cin");
						} else {
							JOptionPane.showConfirmDialog(null, "l'etudiant n'existe pas", "Information",
									JOptionPane.CLOSED_OPTION);
						}

					}
				} else {
					if (espaceEtudiant.getNumEt().getSelectedIndex() == -1) {
						JOptionPane.showConfirmDialog(null, "selectionner un numero d'etudiant", "Information",
								JOptionPane.CLOSED_OPTION);
					} else {
						numEtudiant = (Integer) espaceEtudiant.getNumEt().getSelectedItem();
						ResultSet rs = metiereEtudiant.FindById(numEtudiant);
						if (rs != null) {
							espaceEtudiant.getCinModif().setText(String.valueOf(rs.getInt("cin")));
							recupère(numEtudiant, "numEtudiant");

						} else {
							JOptionPane.showConfirmDialog(null, "l'etudiant n'existe pas", "Information",
									JOptionPane.CLOSED_OPTION);
						}

					}
				}

			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
						JOptionPane.CLOSED_OPTION);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			break;

		case "RechercheEtudiantCin":
			try {
				rs = metiereEtudiant.find(
						"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',abriviation 'filiere',niveau,moyenne,etat,somme_credit 'crédit' from etudiant join filiere using (id_filiere) where cin="
								+ Integer.parseInt(rechercheEtudiant.getEntercin().getText()));
				if (rs != null) {
					new Model().liste(rechercheEtudiant.getTable(), rs);
				} else {
					JOptionPane.showConfirmDialog(null, " l'etudiant n'existe pas", "information",
							JOptionPane.ERROR_MESSAGE);

				}

			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
						JOptionPane.CLOSED_OPTION);
			}

			break;
		case "RechercheEtudiantNom":

			rs = metiereEtudiant.find(
					"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',niveau,moyenne,etat,somme_credit 'crédit',abriviation 'filiere' from etudiant join filiere using (id_filiere) where nom='"
							+ rechercheEtudiant.getEnteNom().getText() + "'");

			if (rs != null) {
				new Model().liste(rechercheEtudiant.getTable(), rs);
			} else {
				JOptionPane.showConfirmDialog(null, " l'etudiant n'existe pas", "information",
						JOptionPane.ERROR_MESSAGE);

			}

			break;
		case "RechercheEtudiantFiliere":

			int id = new MetierFiliere()
					.getIdByAbriviationFiliere(rechercheEtudiant.getCmbFiliere().getSelectedItem().toString());
			new Model().liste(rechercheEtudiant.getTable(), new MetiereEtudiant().getEtudiantResultSet(
					"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',abriviation 'filiere',niveau,moyenne,etat,somme_credit from etudiant join filiere using (id_filiere)   where etudiant.id_filiere="
							+ id));

			break;

		}

	}

	private void recupère(Integer number, String type) {
		String date;
		Integer cin = 0, numEtudiant = 0;
		ResultSet rsNumE, rsCin;
		try {

			if (type == "cin") {
				cin = number;

			} else {
				numEtudiant = number;

			}

			rsNumE = metiereEtudiant.FindById(numEtudiant);
			rsCin = metiereEtudiant.EtudiantByCin(cin);
			if (rsNumE != null) {

				espaceEtudiant.getCmbNiveauModif().setSelectedItem(rsNumE.getString("niveau"));
				espaceEtudiant.getCmbFiliereModif()
						.setSelectedItem(metierFiliere.getNomFiliere(rsNumE.getInt("id_filiere")));
				espaceEtudiant.getNomModif().setText(rsNumE.getString("nom"));
				espaceEtudiant.getPrenomModif().setText(rsNumE.getString("prenom"));
				if (rsNumE.getString("sexe").equals("Femme")) {
					espaceEtudiant.getFemmeModif().setSelected(true);
				} else {
					espaceEtudiant.getHommeModif().setSelected(true);
				}
				espaceEtudiant.getTelephoneModif().setText(rsNumE.getString("telephone"));

				date = String.valueOf(rsNumE.getDate("date_naissance"));

				// date :1998-12-20:annee ,mois, jour

				espaceEtudiant.getAnneeModif().getModel().setValue(Integer.parseInt(date.substring(0, 4)));
				espaceEtudiant.getJourModif().getModel().setValue(Integer.parseInt(date.substring(8)));
				espaceEtudiant.getMoisModif().getModel().setValue(new ModelMois().StringMois(date.substring(5, 7)));

				espaceEtudiant.getAdresseModif().setText(rsNumE.getString("adresse"));

				espaceEtudiant.getPaysModif().setSelectedItem(rsNumE.getString("pays"));
				espaceEtudiant.getGovModif().setSelectedItem(rsNumE.getString("gouvernourat"));
				espaceEtudiant.getLoginModif().setText(rsNumE.getString("login"));
				espaceEtudiant.getPasswordModif().setText(rsNumE.getString("motpasse"));

				espaceEtudiant.enableComponent(true);
			} else if (rsCin != null) {

				espaceEtudiant.getNomModif().setText(rsCin.getString("nom"));
				espaceEtudiant.getPrenomModif().setText(rsCin.getString("prenom"));
				if (rsCin.getString("sexe").equals("Femme")) {
					espaceEtudiant.getFemmeModif().setSelected(true);
				} else {
					espaceEtudiant.getHommeModif().setSelected(true);
				}
				espaceEtudiant.getTelephoneModif().setText(rsCin.getString("telephone"));

				date = String.valueOf(rsCin.getDate("date_naissance"));

				// date :1998-12-20:annee ,mois, jour

				espaceEtudiant.getAnneeModif().getModel().setValue(Integer.parseInt(date.substring(0, 4)));
				espaceEtudiant.getJourModif().getModel().setValue(Integer.parseInt(date.substring(8)));
				espaceEtudiant.getMoisModif().getModel().setValue(new ModelMois().StringMois(date.substring(5, 7)));
				espaceEtudiant.getAdresseModif().setText(rsCin.getString("adresse"));

				espaceEtudiant.getPaysModif().setSelectedItem(rsCin.getString("pays"));
				espaceEtudiant.getGovModif().setSelectedItem(rsCin.getString("gouvernourat"));
				espaceEtudiant.getLoginModif().setText(rsCin.getString("login"));
				espaceEtudiant.getPasswordModif().setText(rsCin.getString("motpasse"));
				espaceEtudiant.getCmbNiveauModif().setSelectedItem(rsCin.getString("niveau"));
				espaceEtudiant.getCmbFiliereModif()
						.setSelectedItem(metierFiliere.getNomFiliere(rsCin.getInt("id_filiere")));

				espaceEtudiant.enableComponent(true);

			} else {
				JOptionPane.showConfirmDialog(null, "l'etudiant n'existe pas", "Information",
						JOptionPane.CLOSED_OPTION);
			}
		} catch (NumberFormatException e1) {
			JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
					JOptionPane.CLOSED_OPTION);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
