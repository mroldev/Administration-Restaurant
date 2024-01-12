package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.Table;

public class TableDAO {
	private static final String TABLE_NAME = " tables ";

//	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
	private static final String INSERT = "INSERT INTO " + TABLE_NAME
			+ " (numero, nombre_place, etat, id_restaurant) VALUES (?,?,?,?)";
//	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME
			+ " SET etat = ? WHERE id = ?"; //Il n'y a que l'état de la table 
	private static final String DELETE = "DELETE FROM" + TABLE_NAME + " WHERE id = ?";

	private Connection cnx;

	public TableDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}
	
	public void insert(Table table) throws DALException {
		try {
			// L'ajout de RETURN_GENERATED_KEYS permet de récupérer l'id généré par la base
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, table.getNumero());
			ps.setInt(2, table.getNombrePlace());
			ps.setString(3, table.getEtat());
			ps.setInt(4, table.getIdRestaurant());
		
			ps.executeUpdate();

			// Le bloc suivant permet de faire la récupération de l'id
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { // Va chercher dans le resultat, la première ligne
				int id = rs.getInt(1); // plus précisément, le int à la première colonne
				table.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);
		}
	}
}
