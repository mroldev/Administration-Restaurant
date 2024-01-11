package controller;

import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;

public class TestRestaurant {
	private static RestaurantBLL bll;

	public static void main(String[] args) {
		try {
			bll = new RestaurantBLL();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listerRestaurant();

	}

	private static void listerRestaurant() {
		try {
			List<Restaurant> restaurants = bll.selectAll();
			for (Restaurant current : restaurants) {
				System.out.println("\t" + current.getId() + ". " + current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
