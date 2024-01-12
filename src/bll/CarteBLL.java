package bll;

import java.util.List;

import bo.Carte;
import bo.Restaurant;
import dal.CarteDAO;
import dal.DALException;

public class CarteBLL {
	private CarteDAO dao;

	public CarteBLL() throws BLLException {
		try {
			dao = new CarteDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	public List<Carte> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des Restaurants", e);
		}
	}

	public Carte selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
		}
	}

}
