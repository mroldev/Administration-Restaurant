package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;

public class TestRestaurant {
	private static Scanner scan;
	private static RestaurantBLL bll;
	//Mettre en static ci-dessous pour pouvoir l'utiliser
	static {
		try {
			bll = new RestaurantBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		scan = new Scanner(System.in);
	}



	public static void listerRestaurant() {
		try {
			List<Restaurant> restaurants = bll.selectAll();
			for (Restaurant current : restaurants) {
				System.out.println("\t" + current.getId() + ". " + current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	public static void creerRestaurant() {
		System.out.println("Vous avez choisi d'ajouter un restaurant");
		
		System.out.println("Veuillez saisir son nom");
		String nom = scan.nextLine();

		System.out.println("Veuillez saisir son adresse");
		String adresse = scan.nextLine();

		System.out.println("Veuillez saisir l'heure d'ouverture (hh:mm)");
		String heureOuvertureStr = scan.nextLine();

		System.out.println("Veuillez saisir l'heure de fermeture (hh:mm)");
		String heureFermetureStr = scan.nextLine();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime heureOuverture = null;
		LocalTime heureFermeture = null;
		try {
			heureOuverture = LocalTime.parse(heureOuvertureStr, dtf);
			heureFermeture = LocalTime.parse(heureFermetureStr, dtf);
			try {
			bll.insert(nom, adresse, heureOuverture, heureFermeture);
			System.out.println("Restaurant ajouté");
		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
		}
		} catch (DateTimeParseException e) {
			System.out.println("La date n'est pas au bon format hh:mm");
		}

		

	}

	public static void trouverRestaurantParID() {
		System.out.println("Vous avez choisi de chercher un restaurant par son ID");

		System.out.println("Veuillez saisir son ID");
		int id = scan.nextInt();
		scan.nextLine();
		try {
			Restaurant rest = bll.selectById(id);
			System.out.println(rest);
		} catch (BLLException e) {
			System.out.println(e.getMessage());
			;
		}
	}

	public static void modifierRestaurant() {
		System.out.println("Vous avez choisi de modifier un restaurant");

		System.out.println("Veuillez saisir son ID");
		int id = scan.nextInt();
		scan.nextLine();

		System.out.println("Veuillez saisir son nouveau nom");
		String nom = scan.nextLine();

		System.out.println("Veuillez saisir sa nouvelle adresse");
		String adresse = scan.nextLine();

		System.out.println("Veuillez saisir la nouvelle heure d'ouverture (hh:mm)");
		String heureOuvertureStr = scan.nextLine();

		System.out.println("Veuillez saisir la nouvelle heure de fermeture (hh:mm)");
		String heureFermetureStr = scan.nextLine();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime heureOuverture = null;
		LocalTime heureFermeture = null;
		try {
			heureOuverture = LocalTime.parse(heureOuvertureStr, dtf);
			heureFermeture = LocalTime.parse(heureFermetureStr, dtf);

			try {
				Restaurant rest = bll.selectById(id);
				rest.setNom(nom);
				rest.setAdresse(adresse);
				rest.setHeureOuverture(heureOuverture);
				rest.setHeureFermeture(heureFermeture);
				bll.update(rest);
				System.out.println("Restaurant modifié");
			} catch (BLLException e) {
				System.out.println("Une erreur est survenue :");
				for (String erreur : e.getErreurs()) {
					System.out.println("\t" + erreur);
				}
			}
		} catch (DateTimeParseException e) {
			System.out.println("La date n'est pas au bon format hh:mm");
		}
	}

	public static void supprimerRestaurant() {
		System.out.println("Vous avez choisi de supprimer un restaurant");
		System.out.println("Veuillez saisir l'id du restaurant à supprimer");
		int id = scan.nextInt();
		scan.nextLine();

		try {
			bll.delete(id);
			System.out.println("Le restaurant a bien été supprimé");
		} catch (BLLException e) {
			System.out.println("Il est interdit de supprimer ce restaurant ou ce restaurant n'existe pas.\n"
					+ "Pour être supprimer un restaurant ne dois plus avoir ni tables, ni carte, ni réservations, ni employés");
		}
	}

	public static void creerRestaurantavecTable() {
		// TODO Auto-generated method stub
		
	}
}
