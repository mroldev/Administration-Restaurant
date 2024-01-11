package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Restaurant;

public class RestaurantDAO {
	private static final String TABLE_NAME = " restaurants ";

	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;

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
}
