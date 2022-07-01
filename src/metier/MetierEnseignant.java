package metier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import DAO.SingletonConnection;

public class MetierEnseignant {
	Connection connection = SingletonConnection.getConnexion();

	public void Add(Identifiant identifiant) {
		// TODO Auto-generated method stub

		try {

			PreparedStatement p = connection.prepareStatement("select * from enseignant where cin =? or login =?");

			p.setInt(1, identifiant.getCin());
			p.setString(2, identifiant.getLogin());

			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				JOptionPane.showConfirmDialog(null, " existe déjà", "information", JOptionPane.ERROR_MESSAGE);

			} else {
				PreparedStatement ps = connection.prepareStatement(
						"insert into enseignant ( cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,motpasse) value (?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, identifiant.getCin());
				ps.setString(2, identifiant.getNom());
				ps.setString(3, identifiant.getPrenom());
				ps.setInt(4, identifiant.getTelephone());
				ps.setString(5, identifiant.getAdresse());
				ps.setString(6, identifiant.getPays());
				ps.setString(7, identifiant.getGouvernourat());
				ps.setDate(8, Date.valueOf(identifiant.getDate_naissance()));
				ps.setString(9, identifiant.getSexe());
				ps.setString(10, identifiant.getLogin());
				ps.setString(11, identifiant.getPassword());
				ps.executeUpdate();
				ps.close();
				JOptionPane.showConfirmDialog(null, "ajouter l'enseignant avec succés  ", "Information",
						JOptionPane.CLOSED_OPTION);
			}

			p.close();

		} catch (SQLException e) {
			System.out.println(e.toString());
			JOptionPane.showConfirmDialog(null, "ERREUR D'AJOUT  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void Update(Identifiant identifiant, Integer num_enseignant) {

		try {

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE enseignant set  cin=?, nom=?, prenom=?,telephone=?,adresse=?, pays=?,gouvernourat=?,date_naissance=?,sexe=?,login=?, motpasse=?  where num_enseignant=?");
			ps.setInt(1, identifiant.getCin());
			ps.setString(2, identifiant.getNom());
			ps.setString(3, identifiant.getPrenom());
			ps.setInt(4, identifiant.getTelephone());
			ps.setString(5, identifiant.getAdresse());
			ps.setString(6, identifiant.getPays());
			ps.setString(7, identifiant.getGouvernourat());
			ps.setDate(8, Date.valueOf(identifiant.getDate_naissance()));
			ps.setString(9, identifiant.getSexe());
			ps.setString(10, identifiant.getLogin());
			ps.setString(11, identifiant.getPassword());
			ps.setInt(12, num_enseignant);

			int rs = ps.executeUpdate();
			if (rs != 0) {
				JOptionPane.showConfirmDialog(null, "modification  effectuée  ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showConfirmDialog(null, "modification n'est pas effectuée  ", "ERREUR",
						JOptionPane.CLOSED_OPTION);
			}
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR D'AJOUT  " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

	public void Delete(int condition, String type) {
		// TODO Auto-generated method stub
		PreparedStatement ps1;

		try {
			if (type == "cin") {
				ps1 = connection.prepareStatement("DELETE From enseignant where cin =?");
			} else {
				ps1 = connection.prepareStatement("DELETE FROM enseignant where num_enseignant =?");
			}
			ps1.setInt(1, condition);
			int rs = ps1.executeUpdate();
			if (rs != 0) {
				JOptionPane.showConfirmDialog(null, "La supprission éffectuée ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showConfirmDialog(null, "ERREUR de supprission", "ERREUR", JOptionPane.CLOSED_OPTION);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}

	}

	public ResultSet FindById(Integer num_enseignant) {

		try {

			PreparedStatement ps1 = connection.prepareStatement("Select * from enseignant where num_enseignant=?");
			ps1.setInt(1, num_enseignant);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				return rs;
			}
//					else {
//					JOptionPane.showConfirmDialog(null, " existe déjà", "information", JOptionPane.ERROR_MESSAGE);
//
//				}
			ps1.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}

	public ResultSet FindEnseignant(String rq) {

		try {

			PreparedStatement ps = connection.prepareStatement(rq);
			ResultSet rs = ps.executeQuery();
			
				return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}



	public List<Enseignant> getAllEnseignant() {
		List<Enseignant> liste = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  num_enseignant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login from enseignant ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Enseignant enseignant = new Enseignant();
				enseignant.setNum_enseignant(rs.getInt("num_enseignant"));
				enseignant.setCin(rs.getInt("cin"));
				enseignant.setNom(rs.getString("nom"));
				enseignant.setPrenom(rs.getString("prenom"));
				enseignant.setTelephone(rs.getInt("telephone"));
				enseignant.setAdresse(rs.getString("adresse"));
				enseignant.setPays(rs.getString("pays"));
				enseignant.setGouvernourat(rs.getString("gouvernourat"));
				enseignant.setDate_naissance(rs.getString(String.valueOf("date_naissance")));
				enseignant.setSexe(rs.getString("sexe"));
				enseignant.setLogin(rs.getString("login"));
				liste.add(enseignant);
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return liste;

	}

	public Enseignant getEnseignant(Integer num_enseignant) {
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  	num_enseignant ,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login from enseignant where num_enseignant=?");
			ps.setInt(1, num_enseignant);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Enseignant enseignant = new Enseignant();
				enseignant.setNum_enseignant(rs.getInt("num_enseignant"));
				enseignant.setCin(rs.getInt("cin"));
				enseignant.setNom(rs.getString("nom"));
				enseignant.setPrenom(rs.getString("prenom"));
				enseignant.setTelephone(rs.getInt("telephone"));
				enseignant.setAdresse(rs.getString("adresse"));
				enseignant.setPays(rs.getString("pays"));
				enseignant.setGouvernourat(rs.getString("gouvernourat"));
				enseignant.setDate_naissance(rs.getString(String.valueOf("date_naissance")));
				enseignant.setSexe(rs.getString("sexe"));
				enseignant.setLogin(rs.getString("login"));
				return enseignant;
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;

	}

	public Enseignant getEnseignant(String Nom_enseignant) {
		List<Enseignant> liste = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"select  num_enseignant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login from enseignant where nom=?");
			ps.setString(1, Nom_enseignant);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Enseignant enseignant = new Enseignant();
				enseignant.setNum_enseignant(rs.getInt("num_enseignant"));
				enseignant.setCin(rs.getInt("cin"));
				enseignant.setNom(rs.getString("nom"));
				enseignant.setPrenom(rs.getString("prenom"));
				enseignant.setTelephone(rs.getInt("telephone"));
				enseignant.setAdresse(rs.getString("adresse"));
				enseignant.setPays(rs.getString("pays"));
				enseignant.setGouvernourat(rs.getString("gouvernourat"));
				enseignant.setDate_naissance(rs.getString(String.valueOf("date_naissance")));
				enseignant.setSexe(rs.getString("sexe"));
				enseignant.setLogin(rs.getString("login"));
				return enseignant;
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;

	}

	public ResultSet getAllEnseignantResultSet(String rq) {
		try {

			PreparedStatement ps = connection.prepareStatement(rq);
			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;
	}



}
