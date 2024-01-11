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

	public void insert(String nom, String adresse, LocalTime heureOuverture, LocalTime heureFermeture)
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
	}
}
