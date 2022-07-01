package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import Model.ModelFiliere;
import Vue.EspaceFiliere;
import Vue.ajoutMatiereFiliere;
import metier.Filiere;
import metier.Matiere;
import metier.MetierFiliere;
import metier.MetierMatiere;

public class ecouteurFiliere implements ActionListener {
	EspaceFiliere espaceFiliere;
	MetierFiliere metierfiliere = new MetierFiliere();
	String nom_filiere, abriviation;
	String type;

	public ecouteurFiliere(EspaceFiliere espaceFiliere, String type) {
		super();
		this.espaceFiliere = espaceFiliere;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (type) {
		case "Ajout":

			if (espaceFiliere.getNom_filiere().getText().isEmpty() || espaceFiliere.getAbriviation().getText().isEmpty()
					|| espaceFiliere.getListMat().getSelectedIndex() == -1) {
				JOptionPane.showConfirmDialog(null, "remplir tous les champs", "ERREUR", JOptionPane.CLOSED_OPTION);
				break;
			}
			nom_filiere = espaceFiliere.getNom_filiere().getText();
			abriviation = espaceFiliere.getAbriviation().getText();
			List<String> indices = espaceFiliere.getListMat().getSelectedValuesList();
			List<Matiere> listeMatiere = new ArrayList();
			for (String string : indices) {
				listeMatiere.add(new MetierMatiere().getMatiereByNom(string));
			}
			new MetierFiliere().add(new Filiere(listeMatiere, abriviation, nom_filiere));
			break;

		case "Modifier":
			if (espaceFiliere.getCmbFiliereModif().getSelectedIndex() == 0) {
				JOptionPane.showConfirmDialog(null, "choisir filiere", "ERREUR", JOptionPane.CLOSED_OPTION);
				break;
			}
			nom_filiere = String.valueOf(espaceFiliere.getCmbFiliereModif().getSelectedItem());
			espaceFiliere.getPanel_1().setVisible(true);
			Filiere filier = metierfiliere.getFiliereByNom(nom_filiere);
			espaceFiliere.getAbriviationModif().setText(filier.getAbriviation());

			DefaultListModel<String> list_1 = new DefaultListModel();
			List<Matiere> m = filier.getMatiere();
			for (Matiere matiere : m) {
				list_1.addElement(matiere.getType_matiere());
			}
			espaceFiliere.getListMatModif().setModel(list_1);

			break;
		case "ajoutMatiere":
			ajoutMatiereFiliere a = new ajoutMatiereFiliere(espaceFiliere);
			break;
		case "Valider Modification":
			if (espaceFiliere.getCmbFiliereModif().getSelectedIndex() == 0
					|| espaceFiliere.getAbriviationModif().getText().isEmpty()) {
				JOptionPane.showConfirmDialog(null, "remplir tous les champs", "ERREUR", JOptionPane.CLOSED_OPTION);
				break;
			}
			nom_filiere = String.valueOf(espaceFiliere.getCmbFiliereModif().getSelectedItem());
			abriviation = espaceFiliere.getAbriviationModif().getText();
			ListModel<String> indice = espaceFiliere.getListMatModif().getModel();
			List<Matiere> listeMatier = new ArrayList();

			for (int i = 0; i < indice.getSize(); i++) {
				listeMatier.add(new MetierMatiere().getMatiereByNom(indice.getElementAt(i)));

			}

			new MetierFiliere().Update(new Filiere(listeMatier, abriviation, nom_filiere));
			espaceFiliere.getPanel_1().setVisible(false);
			break;
		case "supprimer":
			if (espaceFiliere.getCmbFiliereModif().getSelectedIndex() == 0) {
				JOptionPane.showConfirmDialog(null, "remplir tous les champs", "ERREUR", JOptionPane.CLOSED_OPTION);
				break;
			}
			new MetierFiliere().Delete(String.valueOf(espaceFiliere.getCmbFiliereModif().getSelectedItem()));
			new ModelFiliere().filiere(espaceFiliere.getCmbFiliereModif());

			break;

		}

	}

}
