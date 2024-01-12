package controller;

import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.CarteBLL;
import bll.PlatBLL;
import bo.Carte;
import bo.Plat;

public class TestPlat {
	private static Scanner scan;
	private static PlatBLL bll;
	private static CarteBLL carteBll;

	public static void main(String[] args) {

		System.out.println("Bienvenu dans la partie plats");
		scan = new Scanner(System.in);
		try {
			bll = new PlatBLL();
			carteBll = new CarteBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// listerPlat();
		// selectionDeUnPlat();
		// creerPlat();
	}

	// listerPlat();
	private static void listerPlat() {
		try {
			System.out.println("Afficher les plats");
			List<Plat> plats = bll.selectAll();
			for (Plat current : plats) {
				System.out.println("\t" + current.getId() + ". " + current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	// selectionDeUnPlat();
	private static void selectionDeUnPlat() {
		System.out.println("Indiquer un id");
		int id = scan.nextInt();
		scan.nextLine();

		try {
			Plat platSelectionne = bll.selectById(id);

			System.out.println("Plat Selectionne" + platSelectionne);
		} catch (BLLException e) {
			System.out.println(e.getMessage());
		}
	}

	// creerPlat();
	private static void creerPlat() {
		System.out.println("Vous avez choisi d'ajouter un plat");

		System.out.println("Veuillez saisir son nom");
		String nom = scan.nextLine();

		System.out.println("Veuillez saisir son prix (ex :  12,2 )");
		String prix = scan.nextLine();

		System.out.println("Veuillez saisir sa description");
		String description = scan.nextLine();

		System.out.println("Veuillez saisir sa categorie(ENTREE, PLAT, DESSERT ou DESSERT)");
		String categorie = scan.nextLine();

		System.out.println("Veuillez saisir son image ");
		String image_plat_url = scan.nextLine();

		System.out.println("Veuillez saisir son id_carte");
		int id_carte = 0;
		try {
			id_carte = scan.nextInt();
		} catch (java.util.InputMismatchException e) {
			System.out.println("Erreur : Veuillez saisir un nombre entier pour l'id_carte.");
		}

		try {
			Carte carteid = carteBll.selectById(id_carte);
			Plat platAjoute = bll.insert(nom, prix, description, categorie, image_plat_url, carteid);
			System.out.println("Plat ajouté" + platAjoute);

		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
			System.out.println(e.getMessage());
		}
	}

}
