package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DAO.SingletonConnection;
import Model.Moyenne;

public class MetierNote {
	Connection connection = SingletonConnection.getConnexion();
	PreparedStatement ps, ps1;
	ResultSet rs, rs1;
	public void Add(Note note) {

		try {
			ps = connection.prepareStatement("select * FROM note where id_etudiant=? and id_matiere=?");
			ps.setInt(1, note.getEtudiant().getNum_etudiant());
			ps.setInt(2, note.getMatiere().getId_matiere());
			rs = ps.executeQuery();
			if (!rs.next()) {
				
				if (note.getMatiere().getDS()) {
					ps = connection.prepareStatement(
							"INSERT INTO note (id_matiere,id_etudiant,	note_Ds1,note_Ds2,note_Orale) VALUE(?,?,?,?,?) ");
					ps.setInt(1, note.getMatiere().getId_matiere());
					ps.setInt(2, note.getEtudiant().getNum_etudiant());
					ps.setDouble(3, note.getNote_Ds1());
					ps.setDouble(4, note.getNote_Ds2());
					ps.setDouble(5, note.getNote_Orale());

				} else {
					ps = connection.prepareStatement(
							"INSERT INTO note (id_matiere,id_etudiant,	note_examen,note_Orale,note_tp) VALUE(?,?,?,?,?) ");
					ps.setInt(1, note.getMatiere().getId_matiere());
					ps.setInt(2, note.getEtudiant().getNum_etudiant());
					ps.setDouble(3, note.getNote_examen());
					ps.setDouble(4, note.getNote_Orale());
					ps.setDouble(5, note.getNote_tp());

				}

				int rsi = ps.executeUpdate();
				if (rsi == 1) {
					JOptionPane.showConfirmDialog(null, "Insertion efectuée avec succés ", "ERREUR",
							JOptionPane.ERROR_MESSAGE);

				} else {
					JOptionPane.showConfirmDialog(null, " erreur d'insertion", "ERREUR", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("insert");
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void Update(Note note) {

		try {
			if (note.getMatiere().getDS()) {
				ps = connection.prepareStatement(
						"update note set note_Ds1=?,note_Ds2=?,note_Orale=? where id_etudiant=? and id_matiere=? ");
				ps.setDouble(1, note.getNote_Ds1());
				ps.setDouble(2, note.getNote_Ds2());
				ps.setDouble(3, note.getNote_Orale());

			} else {
				ps = connection.prepareStatement(
						"update note set note_examen=?,note_Orale=?,note_tp=? where id_etudiant=? and id_matiere=? ");
				ps.setDouble(1, note.getNote_examen());
				ps.setDouble(2, note.getNote_Orale());
				ps.setDouble(3, note.getNote_tp());

			}

			ps.setInt(4, note.getEtudiant().getNum_etudiant());
			ps.setInt(5, note.getMatiere().getId_matiere());

			int rsu = ps.executeUpdate();
			if (rsu == 1) {
				JOptionPane.showConfirmDialog(null, "modification efectuée avec succés ", "ERREUR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showConfirmDialog(null, "Erreur de modification ", "ERREUR", JOptionPane.ERROR_MESSAGE);
			}

			ps.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR  " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);

		}
	}

	public Note getNoteMatiereByEtudiant(Integer id_matiere, Integer id_etudiant) {
		try {
			ps = connection.prepareStatement("select * FROM note  where id_matiere=? and id_etudiant=?");
			ps.setInt(1, id_matiere);
			ps.setInt(2, id_etudiant);
			rs = ps.executeQuery();

			if (rs.next()) {
				Note note = new Note();
				note.setEtudiant(new MetiereEtudiant().getEtudiant(
						"select  num_etudiant,cin,nom,prenom,telephone,adresse,pays,gouvernourat,date_naissance,sexe,login,niveau,moyenne,etat,	somme_credit,id_filiere from etudiant where num_etudiant='"
								+ id_etudiant + "'"));
				note.setId_note(rs.getInt("id_note"));
				Matiere mat = new MetierMatiere().getMatiereById(id_matiere);
				note.setMatiere(mat);
				if (mat.getDS()) {
					note.setNote_Ds1(rs.getDouble("note_Ds1"));
					note.setNote_Ds2(rs.getDouble("note_Ds2"));

				} else {
					note.setNote_examen(rs.getDouble("note_examen"));
					note.setNote_tp(rs.getDouble("note_tp"));
				}
				note.setNote_Orale(rs.getDouble("note_Orale"));
				return note;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	/**
	 * verifier la matier contient une note ou non
	 */
	public Boolean checkExist(String rq) {
		try {
			ps = connection.prepareStatement(rq);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return false;

	}


	public ResultSet getAllNoteResultSet(String rq) {
		try {
			ps = connection.prepareStatement(rq);

			return ps.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "ERREUR " + e.toString(), "ERREUR", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}



}
