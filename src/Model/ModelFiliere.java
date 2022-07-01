package Model;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;

import Vue.EspaceFiliere;
import metier.Filiere;
import metier.Matiere;
import metier.MetierFiliere;
import metier.MetierMatiere;

public class ModelFiliere {

	EspaceFiliere espaceFiliere;
	DefaultListModel<String> list_1 = new DefaultListModel();

	public DefaultListModel<String> matiere() {
		MetierMatiere metierMatiere = new MetierMatiere();
		List<Matiere> m = metierMatiere.getAllMatiere();

		for (Matiere matiere : m) {

			this.list_1.addElement(matiere.getType_matiere());
		}

		return list_1;
	}

	public void filiere(JComboBox cmbFiliereModif) {
		List<Filiere> et = new MetierFiliere().getAllFiliere();
		// Vider le JComboBox
		cmbFiliereModif.removeAllItems();

		cmbFiliereModif.addItem("choisir filiere");
		for (Filiere filier : et) {
			cmbFiliereModif.addItem(filier.getNom_filiere());
		}

	}
}