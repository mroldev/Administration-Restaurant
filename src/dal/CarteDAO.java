package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;

public class CarteDAO {
	private static final String TABLE_NAME = "cartes";

	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;

	private Connection cnx;

	public CarteDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	public List<Carte> selectAll() throws  DALException {
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
}
