package metier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.SingletonConnection;

public class MetiereEtudiant {
	Connection connection = SingletonConnection.getConnexion();
	ResultSet rs;

	public void Add(Identifiant identifiant, String niveau, Filiere filiere) {

		try {

			PreparedStatement p = connection.prepareStatement("select * from etudiant where cin =? or login =?");

			p.setInt(1, identifiant.getCin());
			p.setString(2, identifiant.getLogin());

			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				JOptionPane.showConfirmDialog(null, " existe déjà", "information", JOptionPane.ERROR_MESSAGE);

			} else {
				PreparedStatement ps = connection.prepareStatement(
						"insert into etudiant ( cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,motpasse,niveau,id_filiere) value (?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
				ps.setString(12, niveau);
				ps.setInt(13, filiere.getId_filiere());
				ps.executeUpdate();
				ps.close();
				JOptionPane.showConfirmDialog(null, "ajouter l'etudiant avec succés  ", "Information",
						JOptionPane.CLOSED_OPTION);
			}

			p.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR D'AJOUT  " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

	public void Update(Identifiant identifiant, String niveau, Filiere filiere, Integer num_etudiant) {

		try {

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE etudiant set  cin=?, nom=?, prenom=?,telephone=?,adresse=?, pays=?,gouvernourat=?,date_naissance=?,sexe=?,login=?, motpasse=? ,niveau=?,id_filiere=? where num_etudiant=?");
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
			ps.setString(12, niveau);
			ps.setInt(13, filiere.getId_filiere());
			ps.setInt(14, num_etudiant);

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

	public void Update(Double moyenne, String etat,int crédit, int num_etudiant) {
		// TODO Auto-generated method stub
		try {

			PreparedStatement ps = connection
					.prepareStatement("UPDATE etudiant set moyenne=?, etat=? ,somme_credit=?  where num_etudiant=?");
			ps.setDouble(1, moyenne);
			ps.setString(2, etat);
			ps.setInt(3, crédit);
			ps.setInt(4, num_etudiant);
			ps.executeUpdate();
			
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR D'AJOUT  " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);

		}

	}

	public void Delete(int condition, String type) {
		PreparedStatement ps1;

		try {
			if (type == "cin") {
				ps1 = connection.prepareStatement("DELETE From etudiant where cin =?");
			} else {
				ps1 = connection.prepareStatement("DELETE FROM etudiant where num_etudiant =?");
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

	public ResultSet FindById(int numET) {

		try {

			PreparedStatement ps1 = connection.prepareStatement("Select * from etudiant where num_etudiant=?");
			ps1.setInt(1, numET);
			ResultSet rs = ps1.executeQuery();
			if (rs.next()) {
				return rs;
			}

			ps1.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}

	public ResultSet EtudiantByCin(int cin) {

		try {

			PreparedStatement ps = connection.prepareStatement("select * from etudiant where cin=?");
			ps.setInt(1, cin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs;
			}
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;

	}


	public ResultSet find(String rq) {
		try {

			PreparedStatement ps = connection.prepareStatement(rq);
			rs = ps.executeQuery();
			return rs;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;

	}

	public List<Etudiant> getAllEtudiant(String rq) {
		List<Etudiant> liste = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(rq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setNum_etudiant(rs.getInt("num_etudiant"));
				etudiant.setFiliere(new MetierFiliere().getFiliere(rs.getInt("id_filiere")));
				etudiant.setMoyenne(rs.getFloat("moyenne"));
				etudiant.setCrédit(rs.getInt("somme_credit"));
				etudiant.setNiveau(rs.getString("niveau"));
				etudiant.setEtat(rs.getString("etat"));
				etudiant.setCin(rs.getInt("cin"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setPrenom(rs.getString("prenom"));
				etudiant.setTelephone(rs.getInt("telephone"));
				etudiant.setAdresse(rs.getString("adresse"));
				etudiant.setPays(rs.getString("pays"));
				etudiant.setGouvernourat(rs.getString("gouvernourat"));
				etudiant.setDate_naissance(rs.getString(String.valueOf("date_naissance")));
				etudiant.setSexe(rs.getString("sexe"));
				etudiant.setLogin(rs.getString("login"));
				liste.add(etudiant);
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return liste;

	}

	public ResultSet getAllEtudiantResultSet() {
		try {

			PreparedStatement ps = connection.prepareStatement(
					"select  num_etudiant 'Numero',cin,nom,prenom ,telephone,adresse,pays,gouvernourat,date_naissance 'Date naissance',sexe,login 'Email',abriviation 'filiere',niveau,moyenne,etat,somme_credit from etudiant join filiere using (id_filiere)   ");

			return ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;
	}

	


	public Etudiant getEtudiant(String rq) {
		List<Etudiant> liste = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(rq);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Etudiant etudiant = new Etudiant();

				etudiant.setNum_etudiant(rs.getInt("num_etudiant"));
				etudiant.setFiliere(new MetierFiliere().getFiliere(rs.getInt("id_filiere")));
				etudiant.setMoyenne(rs.getFloat("moyenne"));
				etudiant.setCrédit(rs.getInt("somme_credit"));
				etudiant.setNiveau(rs.getString("niveau"));
				etudiant.setEtat(rs.getString("etat"));
				etudiant.setCin(rs.getInt("cin"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setPrenom(rs.getString("prenom"));
				etudiant.setTelephone(rs.getInt("telephone"));
				etudiant.setAdresse(rs.getString("adresse"));
				etudiant.setPays(rs.getString("pays"));
				etudiant.setGouvernourat(rs.getString("gouvernourat"));
				etudiant.setDate_naissance(rs.getString(String.valueOf("date_naissance")));
				etudiant.setSexe(rs.getString("sexe"));
				etudiant.setLogin(rs.getString("login"));
				return etudiant;

			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;
	}

	public ResultSet getEtudiantResultSet(String rq) {
		try {
			PreparedStatement ps = connection.prepareStatement(rq);
			return ps.executeQuery();
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
		return null;

	}

}
