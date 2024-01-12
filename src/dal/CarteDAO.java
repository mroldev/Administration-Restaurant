package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Plat;
import bo.Restaurant;

public class CarteDAO {
	private static final String TABLE_NAME = "cartes";

	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ? ";
	private static final String INSERT = "INSERT INTO " + TABLE_NAME + "(nom ,id_restaurant) VALUES (?,?)";
	private static final String UPDATE = "UPDATE " + TABLE_NAME + " SET nom = ?,  id_restaurant = ?  WHERE id = ?";

	private Connection cnx;

	public CarteDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	// Select all
	public List<Carte> selectAll() throws DALException {
		List<Carte> cartes = new ArrayList<Carte>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carte carte = new Carte();
				Restaurant restaurant = new Restaurant();

				carte.setId(rs.getInt("id"));
				carte.setNom(rs.getString("nom"));
				restaurant.setId(rs.getInt("id_restaurant"));
				cartes.add(carte);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations", e);
		}
		return cartes;
	}

	// Select by id
	public Carte selectById(int id) throws DALException {
		Carte carte = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); // Remplace le '?' numero 1 par la valeur de l'id
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				carte = new Carte();
				Restaurant restaurant = new Restaurant();

				carte.setId(rs.getInt("id"));
				carte.setNom(rs.getString("nom"));
				restaurant.setId(rs.getInt("id_restaurant"));
			}
			if (carte == null)
				throw new DALException("Aucun carte ne porte cet ID", null);
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id " + id, e);
		}
		return carte;
	}

	// insert
	public void insert(Carte carte) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setString(1, carte.getNom());
			ps.setInt(2, carte.getRestaurant().getId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				carte.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);
		}

	}

	// Modification
	public void update(Carte carte) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, carte.getNom());
			ps.setInt(2, carte.getRestaurant().getId());
			ps.setInt(3, carte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id " + carte.getId(), e);
		}
	}

}
