package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bo.Carte;
import bo.Plat;

public class PlatDAO {
	private static final String TABLE_NAME = "plats";
	private static final String SELECT = "SELECT * FROM " + TABLE_NAME;
	private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";

	private Connection cnx;

	// Connection
	public PlatDAO() throws DALException {
		cnx = ConnectionProvider.getConnection();
	}

	// Select All
	public List<Plat> selectAll() throws DALException {
		List<Plat> plats = new ArrayList<>();

		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Plat plat = new Plat();
				Carte carte = new Carte();
				plat.setId(rs.getInt("id"));
				plat.setNom(rs.getString("nom"));
				plat.setPrix(rs.getInt("prix"));
				plat.setDescription(rs.getString("description"));
				plat.setCategorie(rs.getString("categorie"));
				plat.setImage_plat_url(rs.getString("image_plat_url"));
				carte.setId(rs.getInt("id_carte"));

				plats.add(plat);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations", e);
		}

		return plats;
	}

	// Select ById
	public Plat selectById(int id) throws DALException {
		Plat plat = new Plat();

		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				plat = new Plat();
				Carte carte = new Carte();
				plat.setId(rs.getInt("id"));
				plat.setNom(rs.getString("nom"));
				plat.setPrix(rs.getInt("prix"));
				plat.setDescription(rs.getString("description"));
				plat.setCategorie(rs.getString("categorie"));
				plat.setImage_plat_url(rs.getString("image_plat_url"));
				carte.setId(rs.getInt("id_carte"));
				
				
				

			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id " + id, e);
		}
		return plat;
	}

}
