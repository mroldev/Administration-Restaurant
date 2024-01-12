package bll;

import bo.Table;
import dal.DALException;
import dal.TableDAO;

public class TableBLL {
	private TableDAO dao;

	public TableBLL() throws BLLException {
		try {
			dao = new TableDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	public Table insert(int numero, int nombrePlace, String etat, int idRestaurant) throws BLLException {

		BLLException blleException = new BLLException();

		if (numero == 0) {
			blleException.ajouterErreur("Le numéro de la table doit être supérieur à zéro");
		}

		if (numero < 0) {
			blleException.ajouterErreur("Le numéro de la table doit être supérieur à zéro");
		}

		if (nombrePlace == 0) {
			blleException.ajouterErreur("Le nombre de place doit être supérieur à zéro");
		}

		if (nombrePlace < 0) {
			blleException.ajouterErreur("Le nombre de place doit être supérieur à zéro");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		Table table = new Table(numero, nombrePlace, etat, idRestaurant);
		try {
			dao.insert(table);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return table;
	}

	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
}
