package bll;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import bo.Carte;
import bo.Plat;
import dal.DALException;
import dal.PlatDAO;

public class PlatBLL {
	private PlatDAO dao;

	public PlatBLL() throws BLLException {
		try {
			dao = new PlatDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	// SELECT ALL
	public List<Plat> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des composants", e);

		}
	}

	// SELECT BY ID
	public Plat selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
		}
	}

	// INSERT
	public void insert(String nom, int prix, String description, String categorie, String image_plat_url, Carte id_carte)
			throws BLLException {

		Plat plat = new Plat(nom, prix, description, categorie, image_plat_url, id_carte);
		try {
			dao.insert(plat);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
	}

}