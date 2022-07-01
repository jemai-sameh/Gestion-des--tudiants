package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;

import Vue.EspaceFiliere;
import Vue.ajoutMatiereFiliere;

public class ecouteurAjoutMatForFiliere implements ActionListener {
	ajoutMatiereFiliere a;
	EspaceFiliere espaceFiliere;

	public ecouteurAjoutMatForFiliere(ajoutMatiereFiliere a, EspaceFiliere espaceFiliere) {
		super();
		this.a = a;
		this.espaceFiliere = espaceFiliere;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		List<String> indices = a.list.getSelectedValuesList();
		DefaultListModel<String> listeMatiere = new DefaultListModel();
		for (String string : indices) {
			listeMatiere.addElement(string);
		}
		espaceFiliere.getListMatModif().setModel(listeMatiere);
		a.dispose();
	
	}

}
