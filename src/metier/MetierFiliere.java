package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.SingletonConnection;

public class MetierFiliere {
	Connection connection = SingletonConnection.getConnexion();
	PreparedStatement ps, ps1;
	ResultSet rs, rs1;

	public void add(Filiere filiere) {

		try {
			ps = connection.prepareStatement("select * from  filiere where nom_filiere=? ");
			ps.setString(1, filiere.getNom_filiere());
			rs = ps.executeQuery();
			if (rs.next()) {
				JOptionPane.showConfirmDialog(null, " existe déjà", "information", JOptionPane.ERROR_MESSAGE);

			} else {
				ps.close();
				ps1 = connection.prepareStatement("insert into filiere(abriviation,nom_filiere) value(?,?)");
				ps1.setString(1, filiere.getAbriviation());
				ps1.setString(2, filiere.getNom_filiere());
				int rsi = ps1.executeUpdate();
				ps1.close();

				for (Matiere matiere : filiere.getMatiere()) {
					new MetierFiliereMetiere().add(new FiliereMetiere(filiere, matiere));
				}
				JOptionPane.showConfirmDialog(null, " insertion effectuée avec succée", "information",
						JOptionPane.ERROR_MESSAGE);

			}
			

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);

		}
	}

	public void Delete(String nom_filiere) {

		try {
			ps = connection.prepareStatement("DELETE FROM filiere where nom_filiere =?");
			ps.setString(1, nom_filiere);
			int rsd = ps.executeUpdate();
			if (rsd == 1) {
				JOptionPane.showConfirmDialog(null, "La supprission éffectuée ", "ERREUR", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showConfirmDialog(null, "ERREUR de supprission", "ERREUR", JOptionPane.CLOSED_OPTION);

			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.CLOSED_OPTION);
		}
	}



	public void Update(Filiere filiere) {

		try {
			ps = connection.prepareStatement("update filiere set abriviation=? where nom_filiere=? ");
			ps.setString(1, filiere.getAbriviation());
			ps.setString(2, filiere.getNom_filiere());
			int rsu = ps.executeUpdate();
			if (rsu == 1) {
				ps.close();
				ps = connection.prepareStatement("select id_filiere FROM filiere where nom_filiere=?");
				ps.setString(1, filiere.getNom_filiere());
				rs = ps.executeQuery();
				if (rs.next()) {
					new MetierFiliereMetiere().Delete(getIdFiliere(filiere.getNom_filiere()));
					for (Matiere matiere : filiere.getMatiere()) {
						
						new MetierFiliereMetiere().add(new FiliereMetiere(filiere, matiere));
					}
					JOptionPane.showConfirmDialog(null, " Modification effectuée avec succée", "information",
							JOptionPane.CLOSED_OPTION);

				}
			}

			ps.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);

		}
	}

	public int getIdFiliere(String nom_filiere) {

		try {
			ps = connection.prepareStatement("select id_filiere FROM filiere where nom_filiere=?");

			ps.setString(1, nom_filiere);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_filiere");
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

		return -1;
	}

	public String getNomFiliere(int id_filiere) {

		try {
			ps = connection.prepareStatement("select nom_filiere FROM filiere where id_filiere=?");
			ps.setInt(1, id_filiere);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("nom_filiere");
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public Filiere getFiliere(Integer id_filiere) {
		List<Matiere> listeMatiere = new ArrayList<>();

		try {
			ps = connection
					.prepareStatement("select id_filiere,abriviation,nom_filiere FROM filiere where id_filiere=?");
			ps.setInt(1, id_filiere);
			rs = ps.executeQuery();
			if (rs.next()) {
				Filiere filiere = new Filiere();
				filiere.setNom_filiere(rs.getString("nom_filiere"));
				filiere.setId_filiere(rs.getInt("id_filiere"));
				filiere.setAbriviation(rs.getString("abriviation"));
				ps1 = connection.prepareStatement(
						"select * FROM matiere join filiere_matiere using(id_matiere) where filiere_matiere.id_Filiere=?  ");
				ps1.setInt(1, rs.getInt("id_filiere"));
				rs1 = ps1.executeQuery();

				while (rs1.next()) {
					Matiere matiere = new Matiere();
					
					matiere.setId_matiere(rs1.getInt("id_matiere"));
					matiere.setType_matiere(rs1.getString("type_matiere"));
					matiere.setCredit(rs1.getInt("crédit"));
					matiere.setCoefficient(rs1.getFloat("Coefficient"));
					matiere.setEnseignant(new MetierEnseignant().getEnseignant(rs1.getInt("num_enseignant")));
					matiere.setDS(rs1.getBoolean("DS"));
					listeMatiere.add(matiere);
				}

				filiere.setMatiere(listeMatiere);
				return filiere;
				
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public Filiere getFiliereByNom(String nom_filiere) {
		List<Matiere> listeMatiere = new ArrayList<>();

		try {
			ps = connection
					.prepareStatement("select id_filiere,abriviation,nom_filiere FROM filiere where nom_filiere=?");
			ps.setString(1, nom_filiere);
			rs = ps.executeQuery();
			if (rs.next()) {

				Filiere filiere = new Filiere();
				filiere.setNom_filiere(rs.getString("nom_filiere"));
				filiere.setId_filiere(rs.getInt("id_filiere"));
				filiere.setAbriviation(rs.getString("abriviation"));
				ps1 = connection.prepareStatement(
						"select * FROM matiere join filiere_matiere using(id_matiere) where filiere_matiere.id_Filiere=?  ");
				ps1.setInt(1, rs.getInt("id_filiere"));
				rs1 = ps1.executeQuery();

				while (rs1.next()) {

					System.out.println(1);
					Matiere matiere = new Matiere();
					matiere.setId_matiere(rs1.getInt("id_matiere"));
					matiere.setType_matiere(rs1.getString("type_matiere"));
					matiere.setCredit(rs1.getInt("crédit"));
					matiere.setCoefficient(rs1.getFloat("Coefficient"));
					matiere.setEnseignant(new MetierEnseignant().getEnseignant(rs1.getInt("num_enseignant")));
					matiere.setDS(rs1.getBoolean("DS"));
					listeMatiere.add(matiere);

				}
				filiere.setMatiere(listeMatiere);
				return filiere;

			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public List<Filiere> getAllFiliere() {
		List<Filiere> f = new ArrayList();
		try {
			ps = connection.prepareStatement("select id_filiere,abriviation,nom_filiere FROM filiere");
			rs = ps.executeQuery();
			while (rs.next()) {
				Filiere filiere = new Filiere();
				filiere.setId_filiere(rs.getInt("id_filiere"));
				filiere.setAbriviation(rs.getString("abriviation"));
				filiere.setNom_filiere(rs.getString("nom_filiere"));
				f.add(filiere);
			}
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return f;
	}


	public Integer getIdByAbriviationFiliere(String abriviation) {
		try {
			ps = connection.prepareStatement("select id_filiere FROM filiere where abriviation=?");

			ps.setString(1, abriviation);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt("id_filiere");
			}
		} catch (SQLException e) {

			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}

		return -1;
	}


}
