package Model;

import java.math.BigDecimal;
import java.util.List;

import metier.Etudiant;
import metier.Matiere;
import metier.MetierMatiere;
import metier.MetierNote;
import metier.MetiereEtudiant;
import metier.Note;

public class Moyenne {

	public Moyenne() {
		// TODO Auto-generated constructor stub
	}

	public void load() {
		List<Etudiant> e = new MetiereEtudiant().getAllEtudiant("select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,	somme_credit ,id_filiere from etudiant ");
		for (Etudiant etudiant : e) {
			if (new MetierNote()
					.checkExist("select * FROM note  where id_etudiant=" + etudiant.getNum_etudiant() )) {
				List<Matiere> mat = new MetierMatiere().getAllMatiereByFiliere(etudiant.getFiliere().getId_filiere());
				double somme = 0;
				Integer crédit=0;
				String etat;
				for (Matiere matiere : mat) {
					Double note = calcul(etudiant, matiere);
					if (note == null) {
						somme = 0;
					} else {
						if (note>=10) {
							crédit+=matiere.getCredit();
						}
						somme += note * matiere.getCoefficient();
					}
				}
				double sommeCoef = 0;
				for (Matiere matiere : mat) {
					sommeCoef += matiere.getCoefficient();

				}

				Double moyenne = somme / sommeCoef;
				BigDecimal bd = new BigDecimal(moyenne);
				bd = bd.setScale(3, BigDecimal.ROUND_DOWN);
				moyenne = bd.doubleValue();
				if (moyenne >= 10) {
					etat = "admis";
				} else if(crédit>45){
						etat= "Admis avec crédit";
				}else {
					etat = "Contrôle";
				}
				new MetiereEtudiant().Update(moyenne, etat,crédit, etudiant.getNum_etudiant());
			}
		}
	}

	public Double calcul(Etudiant etudiant, Matiere matiere) {
	
		Note notes = new MetierNote().getNoteMatiereByEtudiant(matiere.getId_matiere(), etudiant.getNum_etudiant());
		double s = 0;
		if (notes != null) {
			if (matiere.getDS()) {
				s += (notes.getNote_Ds1() * 0.4) + (notes.getNote_Ds2() * 0.4) + (notes.getNote_Orale() * 0.2);
			} else {
				s += (notes.getNote_examen() * 0.7) + (notes.getNote_tp() * 0.2) + (notes.getNote_Orale() * 0.1);
			}

		}
		return s;
	}

}
