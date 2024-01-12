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

	public Carte insert(String nom, Restaurant id_restaurant) throws BLLException {

		BLLException blleException = new BLLException();
		if (nom.length() < 2) {
			blleException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}

		if (nom.length() > 50) {
			blleException.ajouterErreur("Le nom doit faire maximum 50 caractères");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		Carte carte = new Carte();
		carte = new Carte(nom,id_restaurant);
		try {
			dao.insert(carte);
			
		} catch (DALException e) {

			throw new BLLException("Echec de l'insertion", e);
		}

		return carte;

	}
	
	 // UPDATE
	public void update(Carte carte) throws BLLException {
		BLLException blleException = new BLLException();

		if (carte.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom du restaurant doit faire au moins 2 caractères");
		}

		if (carte.getNom().length() > 100) {
			blleException.ajouterErreur("Le nom doit faire maximum 100 caractères");
		}		

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}
		try {
			dao.update(carte);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise a jour de carte", e);
		}
	}
}
