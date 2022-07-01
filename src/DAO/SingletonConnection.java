package DAO;

import java.sql.*;

public class SingletonConnection {

	private static Connection connexion;
	static {
		try {
			// charge le driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Charge ... ok!");

			// etablie la connection
			connexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/gestion_etudiant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			System.out.println("Connection etablie");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR    :" + e.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERREUR    :" + e.toString());
		}

	}

	public static Connection getConnexion() {
		if (connexion == null)
			new SingletonConnection();
		return connexion;
	}

	public static void main(String[] args) {
		//new SingletonConnection().getConnexion();
	}

}
