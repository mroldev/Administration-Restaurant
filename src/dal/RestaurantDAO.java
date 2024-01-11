package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import bo.Restaurant;

public class RestaurantDAO {
	private static final String TABLE_NAME = " restaurants ";

	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
	private static final String INSERT = "INSERT INTO " + TABLE_NAME
			+ " (nom, adresse, heure_ouverture, heure_fermeture) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
	private static final String UPDATE = "UPDATE " + TABLE_NAME
			+ " SET nom = ?, adresse = ?, heure_ouverture = ?, heure_fermeture = ? WHERE id = ?";
	private static final String DELETE = "DELETE FROM" + TABLE_NAME + " WHERE id = ?";

	private Connection cnx;

	public RestaurantDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Restaurant> selectAll() throws DALException {
		List<Restaurant> restaurants = new ArrayList<>();
		// alt + shift + r pour renommer partout

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setId(rs.getInt("id"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setHeureOuverture(rs.getTime("heure_ouverture").toLocalTime());
				restaurant.setHeureFermeture(rs.getTime("heure_fermeture").toLocalTime());
				restaurant.setImageRestaurantUrl(rs.getString("image_restaurant_url"));

				restaurants.add(restaurant);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations", e);
		}
		return restaurants;
	}

	public void insert(Restaurant restaurant) throws DALException {
		try {
			// L'ajout de RETURN_GENERATED_KEYS permet de récupérer l'id généré par la base
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setTime(3, Time.valueOf(restaurant.getHeureOuverture()));
			ps.setTime(4, Time.valueOf(restaurant.getHeureFermeture()));
			ps.executeUpdate();

			// Le bloc suivant permet de faire la récupération de l'id
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { // Va chercher dans le resultat, la première ligne
				int id = rs.getInt(1); // plus précisément, le int à la première colonne
				restaurant.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);
		}
	}

	public Restaurant selectById(int id) throws DALException {
		Restaurant restaurant = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); // Remplace le '?' numero 1 par la valeur de l'id
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				restaurant = new Restaurant();
				restaurant.setId(rs.getInt("id"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setHeureOuverture(rs.getTime("heure_ouverture").toLocalTime());
				restaurant.setHeureFermeture(rs.getTime("heure_fermeture").toLocalTime());
				restaurant.setImageRestaurantUrl(rs.getString("image_restaurant_url"));
			}
			if (restaurant == null)
				throw new DALException("Aucun restaurant ne porte cet ID", null);
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id " + id, e);
		}
		return restaurant;
	}

	public void update(Restaurant restaurant) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setTime(3, Time.valueOf(restaurant.getHeureOuverture()));
			ps.setTime(4, Time.valueOf(restaurant.getHeureFermeture()));
			ps.setInt(5, restaurant.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id " + restaurant.getId(), e);
		}
	}

	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression du restaurant d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer le restaurant d'id " + id, e);
		}
	}
}
