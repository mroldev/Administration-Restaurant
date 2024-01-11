package bll;

import java.util.List;

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

}