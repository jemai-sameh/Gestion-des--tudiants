package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.SingletonConnection;

public class MetierFiliereMetiere {
	Connection connection = SingletonConnection.getConnexion();
	PreparedStatement ps, ps2;
	ResultSet rs, rs1;

	public void add(FiliereMetiere filiereMetiere) {
		try {
			ps = connection.prepareStatement("insert into filiere_matiere(id_matiere,id_Filiere) value(?,?)");
			ps.setInt(1, filiereMetiere.getMatiere().getId_matiere());
			ps.setInt(2, new MetierFiliere().getIdFiliere(filiereMetiere.getFiliere().getNom_filiere()));
			ps.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void Delete(Integer id_filiere) {
		try {
			ps = connection.prepareStatement("Delete from filiere_matiere WHERE id_Filiere=?");
			ps.setInt(1, id_filiere);
			ps.executeUpdate();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

	}
}
