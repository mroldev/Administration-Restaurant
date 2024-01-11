package bll;

import java.util.List;

import bo.Carte;
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
	

}
