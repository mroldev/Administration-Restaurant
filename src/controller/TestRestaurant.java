package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;

public class TestRestaurant {
	private static Scanner scan;
	private static RestaurantBLL bll;

	public static void main(String[] args) {
		try {
			bll = new RestaurantBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		scan = new Scanner(System.in);
//		listerRestaurant();
		creerRestaurant();

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
	
	private static void creerRestaurant() {
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
		LocalTime heureOuverture = LocalTime.parse(heureOuvertureStr, dtf);
		LocalTime heureFermeture = LocalTime.parse(heureFermetureStr, dtf);
		
		try {
			bll.insert(nom, adresse, heureOuverture, heureFermeture);
			System.out.println("Restaurant ajouté");
		} catch (BLLException e) {
			System.out.println("Une erreur est survenue :");
			for (String erreur : e.getErreurs()) {
				System.out.println("\t" + erreur);
			}
		}
		
	}
	
}
