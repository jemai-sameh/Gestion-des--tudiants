package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.SingletonConnection;

public class MetierMatiere {
	Connection connection = SingletonConnection.getConnexion();
	PreparedStatement ps, ps2;
	ResultSet rs, rs1;

	public void Add(Matiere matiere) {

		try {
			ps = connection.prepareStatement("select type_matiere FROM matiere where type_matiere=?");
			ps.setString(1, matiere.getType_matiere());
			rs = ps.executeQuery();
			if (rs.next()) {
				JOptionPane.showConfirmDialog(null, "la matiere existe déja ", "ERREUR", JOptionPane.ERROR_MESSAGE);
			} else {
				ps = connection.prepareStatement(
						"INSERT INTO matiere (type_matiere,crédit,coefficient,	DS,num_enseignant) VALUE(?,?,?,?,?) ");
				ps.setString(1, matiere.getType_matiere());
				ps.setInt(2, matiere.getCredit());
				ps.setFloat(3, matiere.getCoefficient());
				ps.setBoolean(4, matiere.getDS());
				ps.setInt(5, matiere.getEnseignant().getNum_enseignant());

				int rsi = ps.executeUpdate();
				if (rsi == 1) {
					JOptionPane.showConfirmDialog(null, "Insertion efectuée avec succés ", "ERREUR",
							JOptionPane.ERROR_MESSAGE);

				} else {
					JOptionPane.showConfirmDialog(null, " erreur d'insertion", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void update(Matiere matiere) {
		try {
			ps = connection.prepareStatement(
					"update matiere set crédit=?,coefficient=?,DS=?,num_enseignant=? where type_matiere=? ");

			ps.setInt(1, matiere.getCredit());
			System.out.println("c" + matiere.getCredit());
			ps.setFloat(2, matiere.getCoefficient());
			ps.setBoolean(3, matiere.getDS());
			ps.setInt(4, matiere.getEnseignant().getNum_enseignant());
			ps.setString(5, matiere.getType_matiere());

			int rsi = ps.executeUpdate();
			if (rsi == 1) {
				JOptionPane.showConfirmDialog(null, "Modification efectuée avec succés ", "ERREUR",
						JOptionPane.ERROR_MESSAGE);

			} else {
				JOptionPane.showConfirmDialog(null, " erreur de modification", "ERREUR", JOptionPane.ERROR_MESSAGE);
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void delete(String nom) {
		try {
			ps = connection.prepareStatement("DELETE  FROM matiere where type_matiere='" + nom + "'");
			int rs = ps.executeUpdate();
			if (rs != 0) {
				JOptionPane.showConfirmDialog(null, "supprission effectuée avec succés", "ERREUR",
						JOptionPane.ERROR_MESSAGE);

			} else {
				JOptionPane.showConfirmDialog(null, "ERREUR de supprission", "ERREUR", JOptionPane.ERROR_MESSAGE);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

	}
	
	public int getIdMatiere(String nom_matiere) {
		Integer id = -1;

		try {
			ps = connection.prepareStatement("select id_matiere FROM matiere where type_matiere=?");
			ps.setString(1, nom_matiere);

			rs = ps.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id_matiere");
			}
			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("metier 1");
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

		return id;
	}

	public List<Matiere> getAllMatiere() {
		List<Matiere> m = new ArrayList();
		try {
			ps = connection.prepareStatement("select * FROM matiere");
			rs = ps.executeQuery();
			while (rs.next()) {
				Matiere matiere = new Matiere();
				matiere.setId_matiere(rs.getInt("id_matiere"));
				matiere.setType_matiere(rs.getString("type_matiere"));
				matiere.setCredit(rs.getInt("crédit"));
				matiere.setCoefficient(rs.getFloat("Coefficient"));
				matiere.setEnseignant(new MetierEnseignant().getEnseignant(rs.getInt("num_enseignant")));
				matiere.setDS(rs.getBoolean("DS"));
				m.add(matiere);
			}

			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return m;
	}

	public List<Matiere> getAllMatiereByFiliere(Integer id_filiere) {
		List<Matiere> m = new ArrayList<>();

		try {
			ps = connection.prepareStatement(
					"select m.id_matiere,type_matiere,crédit,Coefficient,DS,num_enseignant FROM filiere_matiere f join matiere AS m using (id_matiere) where id_filiere=?");

			ps.setInt(1, id_filiere);
			rs = ps.executeQuery();
			while (rs.next()) {
				Matiere matiere = new Matiere();
				matiere.setId_matiere(rs.getInt("id_matiere"));
				matiere.setType_matiere(rs.getString("type_matiere"));
				matiere.setCredit(rs.getInt("crédit"));
				matiere.setCoefficient(rs.getFloat("Coefficient"));
				matiere.setEnseignant(new MetierEnseignant().getEnseignant(rs.getInt("num_enseignant")));
				matiere.setDS(rs.getBoolean("DS"));
				m.add(matiere);
			}

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return m;
	}
//	public ResultSet getAllMatiereByFilier(Integer id_filiere) {
//		try {
//			ps = connection.prepareStatement("select m.id_matiere 'numéro',type_matiere 'Nom',crédit,Coefficient,comporte_TP, e.nom 'Nom' FROM filiere_matiere AS f , matiere AS m ,enseignant AS e where id_filiere=?");
//			ps.setInt(1, id_filiere);
//			rs = ps.executeQuery();
//
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
//		}
//		return rs;
//	}

	public Matiere getMatiereByNom(String type_matiere) {

		try {
			ps = connection.prepareStatement("select * FROM matiere where type_matiere=? ");
			ps.setString(1, type_matiere);
			rs = ps.executeQuery();
			if (rs.next()) {
				Matiere matiere = new Matiere();
				matiere.setId_matiere(rs.getInt("id_matiere"));
				matiere.setType_matiere(rs.getString("type_matiere"));
				matiere.setCredit(rs.getInt("crédit"));
				matiere.setCoefficient(rs.getFloat("Coefficient"));
				matiere.setEnseignant(new MetierEnseignant().getEnseignant(rs.getInt("num_enseignant")));
				matiere.setDS(rs.getBoolean("DS"));
				return matiere;
			}

			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public Matiere getMatiereById(Integer id_matiere) {
		try {
			ps = connection.prepareStatement("select * FROM matiere where id_matiere=? ");
			ps.setInt(1, id_matiere);
			rs = ps.executeQuery();
			if (rs.next()) {
				Matiere matiere = new Matiere();
				matiere.setId_matiere(rs.getInt("id_matiere"));
				matiere.setType_matiere(rs.getString("type_matiere"));
				matiere.setCredit(rs.getInt("crédit"));
				matiere.setCoefficient(rs.getFloat("Coefficient"));
				matiere.setEnseignant(new MetierEnseignant().getEnseignant(rs.getInt("num_enseignant")));
				matiere.setDS(rs.getBoolean("DS"));
				return matiere;
			}

			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

}
