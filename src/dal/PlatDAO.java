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
	private static final String INSERT = "INSERT INTO" + TABLE_NAME
			+ "( nom, prix, description, categorie, image_plat_url, id_carte) VALUES (?,?,?,?,?,?)";
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
				plat.setPrix(rs.getString("prix"));
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
		Plat plat = null;

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
				plat.setPrix(rs.getString("prix"));
				plat.setDescription(rs.getString("description"));
				plat.setCategorie(rs.getString("categorie"));
				plat.setImage_plat_url(rs.getString("image_plat_url"));
				carte.setId(rs.getInt("id_carte"));

			}
			if (plat == null)
				throw new DALException("Aucun plat avec l", null);

		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id " + id, e);
		}
		return plat;
	}

	// Insert
	public void insert(Plat plat) throws DALException {
		try {
			// L'ajout de RETURN_GENERATED_KEYS permet de récupérer l'id généré par la base

			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, plat.getNom());
			ps.setString(2, plat.getPrix());
			ps.setString(3, plat.getDescription());
			ps.setString(4, plat.getCategorie());
			ps.setString(5, plat.getImage_plat_url());
			ps.setInt(6, plat.getCarte().getId());
			ps.executeUpdate();

			// Le bloc suivant permet de faire la récupération de l'id
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { // Va chercher dans le resultat, la première ligne
				int id = rs.getInt(1); // plus précisément, le int à la première colonne
				plat.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);
		}

	}

}
