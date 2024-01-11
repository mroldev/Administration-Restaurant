package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Classe utilitaire pour mutualiser la connexion à la base de données

public abstract class ConnectionProvider {
	public static Connection getConnection() throws DALException {
		String url = "jdbc:sqlserver://localhost;databasename=PROJET_FIL_ROUGE;trustservercertificate=true";
		try {
			return DriverManager.getConnection(url, System.getenv("USER_SQLSERVER"),
					System.getenv("PASSWORD_SQLSERVER"));
		} catch (SQLException e) {
			throw new DALException("Erreur de connexion a la base de donnees", e);
		}
	}			

}
