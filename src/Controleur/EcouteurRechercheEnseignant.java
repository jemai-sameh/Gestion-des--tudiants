package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import Model.Model;
import Model.ModelMois;
import Model.ModelValidateur;
import Vue.EspaceEnseignant;
import Vue.RechercheEnseignant;
import metier.MetierEnseignant;

public class EcouteurRechercheEnseignant implements ActionListener {

	private RechercheEnseignant rechercheEnseignant;
	private EspaceEnseignant espaceEnseignant;
	private String rechercher;
	ModelValidateur modelValidateur = new ModelValidateur();
	MetierEnseignant metierEnseignant = new MetierEnseignant();
	ResultSet rs;
	private JTable table;

	public EcouteurRechercheEnseignant(EspaceEnseignant espaceEnseignant, String rechercher) {
		this.espaceEnseignant = espaceEnseignant;
		this.rechercher = rechercher;
	}

	public EcouteurRechercheEnseignant(RechercheEnseignant rechercheEnseignant, String rechercher, JTable table) {
		this.rechercheEnseignant = rechercheEnseignant;
		this.rechercher = rechercher;
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (this.rechercher) {
		case "RechercheEnseignantCin":
			try {
				if (rechercheEnseignant.getEntercin().getText().equals("entée  Cin")) {
					JOptionPane.showConfirmDialog(null, "doit etre non vide", "information", JOptionPane.ERROR_MESSAGE);
					break;
				}
				Integer cin = Integer.parseInt(rechercheEnseignant.getEntercin().getText());
				rs = metierEnseignant.FindEnseignant(
						"select num_enseignant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email' from enseignant where cin="
								+ cin);
				if (rs != null) {
					new Model().liste(table, rs);
				} else {
					JOptionPane.showConfirmDialog(null, " l'enseignant n'existe pas", "information",
							JOptionPane.ERROR_MESSAGE);

				}

			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
						JOptionPane.CLOSED_OPTION);
			}

			break;
		case "RechercheEnseignantNom":
			if (rechercheEnseignant.getEnteNom().getText().equals("entée  nom")) {
				JOptionPane.showConfirmDialog(null, "doit etre non vide", "information", JOptionPane.ERROR_MESSAGE);
				break;
			}
			String nom = rechercheEnseignant.getEnteNom().getText();

			rs = metierEnseignant.FindEnseignant(
					"select  num_enseignant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email' from enseignant where nom ='"
							+ nom + "'");
			if (rs != null) {
				new Model().liste(table, rs);
			} else {
				JOptionPane.showConfirmDialog(null, " l'enseignant n'existe pas", "information",
						JOptionPane.ERROR_MESSAGE);

			}

			break;

		case "rechercheEspaceEnseignant":
			Integer numEnseignant = 0;
			Integer cinEnsegnant = 0;
			try {
				if (espaceEnseignant.getCBoxCin().isSelected()) {
					if (espaceEnseignant.getCinModif().getText().isEmpty()) {
						JOptionPane.showConfirmDialog(null, "entrer Numéro Carte Cin d'etudiant", "Information",
								JOptionPane.CLOSED_OPTION);
					} else {
						cinEnsegnant = Integer.parseInt(espaceEnseignant.getCinModif().getText());
						ResultSet rs = metierEnseignant
								.FindEnseignant("select * from enseignant where cin=" + cinEnsegnant);
						if (rs != null) {
							espaceEnseignant.getNumEn().setSelectedItem(rs.getInt("num_enseignant"));
							recupère(cinEnsegnant, "cin");
						} else {
							JOptionPane.showConfirmDialog(null, "l'enseignant n'existe pas", "Information",
									JOptionPane.CLOSED_OPTION);
						}

					}
				} else {
					if (espaceEnseignant.getNumEn().getSelectedIndex() == -1) {
						JOptionPane.showConfirmDialog(null, "selectionner un numero d'etudiant", "Information",
								JOptionPane.CLOSED_OPTION);
					} else {
						numEnseignant = (Integer) espaceEnseignant.getNumEn().getSelectedItem();
						ResultSet rs = metierEnseignant.FindById(numEnseignant);
						if (rs != null) {
							espaceEnseignant.getCinModif().setText(String.valueOf(rs.getInt("cin")));
							recupère(numEnseignant, "numEnseignant");

						} else {
							JOptionPane.showConfirmDialog(null, "l'enseignant n'existe pas", "Information",
									JOptionPane.CLOSED_OPTION);
						}

					}
				}

			} catch (NumberFormatException e1) {
				JOptionPane.showConfirmDialog(null, "le champs doit etre un entier ", "Information",
						JOptionPane.CLOSED_OPTION);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;
		}

	}

	private void recupère(Integer number, String type) {
		String date;
		Integer cin = 0, numEnseignant = 0;
		ResultSet rsNumEn, rsCin;
		try {

			if (type == "cin") {
				cin = number;

			} else {
				numEnseignant = number;

			}

			rsNumEn = metierEnseignant.FindById(numEnseignant);
			rsCin = metierEnseignant.FindEnseignant("select * from enseignant where cin=" + cin);
			if (rsNumEn != null) {
				espaceEnseignant.getNomModif().setText(rsNumEn.getString("nom"));
				espaceEnseignant.getPrenomModif().setText(rsNumEn.getString("prenom"));
				if (rsNumEn.getString("sexe").equals("Femme")) {
					espaceEnseignant.getFemmeModif().setSelected(true);
				} else {
					espaceEnseignant.getHommeModif().setSelected(true);
				}
				espaceEnseignant.getTelephoneModif().setText(rsNumEn.getString("telephone"));

				date = String.valueOf(rsNumEn.getDate("date_naissance"));

				// date :1998-12-20:annee ,mois, jour

				espaceEnseignant.getAnneeModif().getModel().setValue(Integer.parseInt(date.substring(0, 4)));
				espaceEnseignant.getJourModif().getModel().setValue(Integer.parseInt(date.substring(8)));
				espaceEnseignant.getMoisModif().getModel().setValue(new ModelMois().StringMois(date.substring(5, 7)));

				espaceEnseignant.getAdresseModif().setText(rsNumEn.getString("adresse"));

				espaceEnseignant.getPaysModif().setSelectedItem(rsNumEn.getString("pays"));
				espaceEnseignant.getGovModif().setSelectedItem(rsNumEn.getString("gouvernourat"));
				espaceEnseignant.getLoginModif().setText(rsNumEn.getString("login"));
				espaceEnseignant.getPasswordModif().setText(rsNumEn.getString("motpasse"));

				espaceEnseignant.enableComponent(true);
			} else if (rsCin != null) {

				espaceEnseignant.getNomModif().setText(rsCin.getString("nom"));
				espaceEnseignant.getPrenomModif().setText(rsCin.getString("prenom"));
				if (rsCin.getString("sexe").equals("Femme")) {
					espaceEnseignant.getFemmeModif().setSelected(true);
				} else {
					espaceEnseignant.getHommeModif().setSelected(true);
				}
				espaceEnseignant.getTelephoneModif().setText(rsCin.getString("telephone"));

				date = String.valueOf(rsCin.getDate("date_naissance"));

				// date :1998-12-20:annee ,mois, jour

				espaceEnseignant.getAnneeModif().getModel().setValue(Integer.parseInt(date.substring(0, 4)));
				espaceEnseignant.getJourModif().getModel().setValue(Integer.parseInt(date.substring(8)));
				espaceEnseignant.getMoisModif().getModel().setValue(new ModelMois().StringMois(date.substring(5, 7)));
				espaceEnseignant.getAdresseModif().setText(rsCin.getString("adresse"));

				espaceEnseignant.getPaysModif().setSelectedItem(rsCin.getString("pays"));
				espaceEnseignant.getGovModif().setSelectedItem(rsCin.getString("gouvernourat"));
				espaceEnseignant.getLoginModif().setText(rsCin.getString("login"));
				espaceEnseignant.getPasswordModif().setText(rsCin.getString("motpasse"));

				espaceEnseignant.enableComponent(true);

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
