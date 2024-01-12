package controller;

import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;
import controller.lecteur.LecteurCSV;
import controller.lecteur.LecteurCSVException;

public class TestLecteur {
	private static RestaurantBLL bll;

	public static void main(String[] args) {

		LecteurCSV lecteur = new LecteurCSV();
		List<Restaurant> listeRestaurants;
		try {
			try {
				bll = new RestaurantBLL();
			} catch (BLLException e) {
				e.printStackTrace();
			}
			listeRestaurants = lecteur.lireCSV("data.csv", true);
			for (Restaurant current : listeRestaurants) {
				try {
					bll.insert(current.getNom(), current.getAdresse(), current.getHeureOuverture(),
							current.getHeureFermeture());
				} catch (BLLException e) {
					System.out.println("Une erreur est survenue :");
					for (String erreur : e.getErreurs()) {
						System.out.println("\t" + erreur);
					}
				}
				System.out.println(current);
			}
		} catch (LecteurCSVException e) {
			for (String erreur : e.getErreurs()) {
				System.err.println(erreur);
			}
		}
	}
}
