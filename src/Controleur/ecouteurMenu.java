package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Apropos;
import Vue.Bienvenu;
import Vue.Contact;
import Vue.EspaceEtudiant;
import Vue.MenuPrincipal;

public class ecouteurMenu implements ActionListener {
			MenuPrincipal menuPrincipal;
			String type;
	public ecouteurMenu(MenuPrincipal menuPrincipal ,String type) {
		this.menuPrincipal=menuPrincipal;
		this.type=type;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch (type) {
		case "ItmEtudiant":

			EspaceEtudiant etudiant = new EspaceEtudiant();
			etudiant.setLocation(menuPrincipal.getLblImage().getLocation());
			menuPrincipal.getDesktopPane().add(etudiant);
			etudiant.show();

			
			break;

		case "ItmAccueil":
			menuPrincipal.dispose();
			new MenuPrincipal();

			break;
		case "ItmDeconnecter":
			menuPrincipal.setVisible(false);
			new Bienvenu().setVisible(true);
			break;
		case "ItmPropos":
			Apropos a=new Apropos();
			a.setLocation(650, 180);
			break;
		case "ItmContact":
			Contact c=new Contact();
			c.setLocation(650, 180);

			break;
		
	}}

}
