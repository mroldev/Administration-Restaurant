package bll;

import java.time.LocalTime;
import java.util.List;

import bo.Restaurant;
import dal.DALException;
import dal.RestaurantDAO;

public class RestaurantBLL {
	private RestaurantDAO dao;

	public RestaurantBLL() throws BLLException {
		try {
			dao = new RestaurantDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	public List<Restaurant> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des Restaurants", e);
		}
	}

	public Restaurant insert(String nom, String adresse, LocalTime heureOuverture, LocalTime heureFermeture)
			throws BLLException {

		BLLException blleException = new BLLException();

		if (nom.length() < 2) {
			blleException.ajouterErreur("Le nom du restaurant doit faire au moins 2 caractères");
		}

		if (nom.length() > 100) {
			blleException.ajouterErreur("Le nom doit faire maximum 100 caractères");
		}

		if (adresse.length() < 10) {
			blleException.ajouterErreur("L'adresse du restaurant doit faire au moins 10 caractères");
		}

		if (adresse.length() > 150) {
			blleException.ajouterErreur("L'adresse doit faire maximum 150 caractères");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		Restaurant restaurant = new Restaurant(nom, adresse, heureOuverture, heureFermeture);
		try {
			dao.insert(restaurant);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return restaurant;
	}

	public Restaurant selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
		}
	}

	public void update(Restaurant restaurant) throws BLLException {
		BLLException blleException = new BLLException();

		if (restaurant.getNom().length() < 2) {
			blleException.ajouterErreur("Le nom du restaurant doit faire au moins 2 caractères");
		}

		if (restaurant.getNom().length() > 100) {
			blleException.ajouterErreur("Le nom doit faire maximum 100 caractères");
		}

		if (restaurant.getAdresse().length() < 10) {
			blleException.ajouterErreur("L'adresse du restaurant doit faire au moins 10 caractères");
		}

		if (restaurant.getAdresse().length() > 150) {
			blleException.ajouterErreur("L'adresse doit faire maximum 150 caractères");
		}

		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		try {
			dao.update(restaurant);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise a jour", e);
		}
	}

	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
}
