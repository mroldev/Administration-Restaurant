package bll;

import java.util.List;

import bo.Restaurant;
import dal.RestaurantDAO;
import dal.DALException;

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
	
}
