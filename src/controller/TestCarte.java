package controller;

import java.util.List;
import java.util.Scanner;
import bll.BLLException;
import bll.CarteBLL;
import bll.RestaurantBLL;
import bo.Carte;
import bo.Restaurant;

public class TestCarte {
	private static CarteBLL bll;
	private static Scanner scan;
	private static RestaurantBLL restaurantBLL;


	
	static {
		try {
			bll = new CarteBLL();
			restaurantBLL = new RestaurantBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		scan = new Scanner(System.in);
	}

	private static void listerCartes() {
		try {
			List<Carte> cartes = bll.selectAll();
			for (Carte current : cartes) {
				System.out.println("\t" + current.getId() + ". " + current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	private static void trouverCarteParID() {
		System.out.println("Vous avez choisi de chercher une carte par son ID");

		System.out.println("Veuillez saisir  son ID");
		int id = scan.nextInt();
		scan.nextLine();
		try {
			Carte rest = bll.selectById(id);
			System.out.println(rest);
		} catch (BLLException e) {
			System.out.println(e.getMessage());
			;
		}
	}

	public static void creerCarte() {
		int id_restaurant;
		System.out.println("Vous avez choisi d'ajouter une carte");

		System.out.println("Veuillez saisir le nom");
		String nom = scan.nextLine();

		try {
			System.out.println("Veuillez saisir son id_restaurant");
			id_restaurant = Integer.parseInt(scan.nextLine());

			Restaurant restaurantId = restaurantBLL.selectById(id_restaurant);
			Carte carteAjoute = bll.insert(nom, restaurantId);
			System.out.println("Plat ajouté" + carteAjoute);
		} catch (NumberFormatException e) {
			System.out.println("Erreur : l'id saisi est est incorrect");
		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
			System.out.println(e.getMessage());
		}
	}

	private static void modifierCarte() {
		int id_restaurant;
		System.out.println("Vous avez choisi de modifier  une carte");
		System.out.println("Veuillez saisir son ID");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Veuillez saisir le nom");
		String nom = scan.nextLine();
		System.out.println("Veuillez saisir son nouveau id_restaurant");
		id_restaurant = scan.nextInt();
		scan.nextLine();

		try {
			Restaurant idrestauraunt = restaurantBLL.selectById(id_restaurant);

			Carte carte = bll.selectById(id);
			carte.setNom(nom);
			carte.setRestaurant(idrestauraunt);

			bll.update(carte);
			System.out.println("Carte ajouté");
		} catch (NumberFormatException e) {
			System.out.println("Erreur : l'id saisi est est incorrect");
		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
			System.out.println(e.getMessage());

		}
	}

}
