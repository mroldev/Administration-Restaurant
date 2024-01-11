package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Carte;

public class CarteDAO {
	private static final String TABLE_NAME = "cartes";

	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

	private Connection cnx;

	public CarteDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Carte> selectAll() throws DALException {
		List<Carte> cartes = new ArrayList<Carte>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carte carte = new Carte();
				carte.setId(rs.getInt("id"));
				carte.setNom(rs.getString("nom"));
				carte.setIdRestaurant(rs.getInt("id"));
				cartes.add(carte);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations", e);
		}
		return cartes;
	}

	public Carte selectById(int id) throws DALException {
		Carte carte = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); // Remplace le '?' numero 1 par la valeur de l'id
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				carte = new Carte();
				carte.setId(rs.getInt("id"));
				carte.setNom(rs.getString("nom"));
				carte.setIdRestaurant(rs.getInt("id"));
			}
			if (carte == null)
				throw new DALException("Aucun carte ne porte cet ID", null);
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id " + id, e);
		}
		return carte;
	}
}
