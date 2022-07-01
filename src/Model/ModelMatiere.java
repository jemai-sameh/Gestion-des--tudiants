package Model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import metier.Enseignant;
import metier.Matiere;

public class ModelMatiere {

	JComboBox componant;

	public ModelMatiere(JComboBox componant) {
		// TODO Auto-generated constructor stub
		this.componant = componant;
	}

	public void remplirMatiere(List<Matiere> M) {
		componant.setModel(new DefaultComboBoxModel(new String[] { "Choisir Matiere" }));
		componant.setSelectedIndex(0);
		// List<Matiere> M = new MetierMatiere().getAllMatiere()new
		// MetierEnseignant().getAllEnseignant();
		for (Matiere mat : M) {
			componant.addItem(mat.getType_matiere());
		}
	}

	public void remplirEnseignant(List<Enseignant> en) {
		componant.setModel(new DefaultComboBoxModel(new String[] { "Choisir enseignant" }));
		componant.setSelectedIndex(0);
//		List<Enseignant> en = new MetierEnseignant().getAllEnseignant();
		for (Enseignant ens : en) {
			componant.addItem(ens.getNom());
		}
	}

}
