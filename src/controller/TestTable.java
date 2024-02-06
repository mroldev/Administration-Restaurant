package controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.TableBLL;
import bo.Restaurant;
import bo.Table;

public class TestTable {


	private static Scanner scan = new Scanner(System.in);
	private static RestaurantBLL bll;
	private static TableBLL tablebll;

	static {	
		try {
			bll = new RestaurantBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		try {
			tablebll = new TableBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}

		scan = new Scanner(System.in);
	}
		// creerRestaurantavecTable();
		//supprimerTable();


	public static void creerRestaurantavecTable() {
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
				Restaurant restaurantCreer = bll.insert(nom, adresse, heureOuverture, heureFermeture);
				int idRestaurantCreer = restaurantCreer.getId();
				System.out.println("Restaurant ajouté - ID : " + idRestaurantCreer);

				ajouterTableARestaurant(idRestaurantCreer);

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

	public static void ajouterTableARestaurant(int idRestaurantCreer) throws BLLException {
		System.out.println("Veuillez saisir le nombre de tables à créer");
		int nombreTablesACreer = 0;
		try {
			nombreTablesACreer = scan.nextInt();
			scan.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Veuillez saisir un nombre entier pour le nombre de tables.");
		}

		for (int i = 0; i < nombreTablesACreer; i++) {
			String etat = "LIBRE";
			System.out.println("Veuillez saisir le numéro de la table");
			int numeroTable = 0;
			try {
				numeroTable = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Veuillez saisir un nombre entier pour le numéro de la table");
			}

			System.out.println("Veuillez saisir le nombre de place de la table");
			int nombrePlacesTable = 0;
			try {
				nombrePlacesTable = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("Veuillez saisir un nombre entier pour le nombre de place la table");
			}

			Table nouvelleTable = tablebll.insert(numeroTable, nombrePlacesTable, etat, idRestaurantCreer);
			System.out.println("La table d'ID :" + nouvelleTable.getId() + " vient d'être ajoutée au Restaurant d'ID : "
					+ idRestaurantCreer);
		}
	}

	public static void supprimerTable() {

		System.out.println("Vous avez choisi de supprimer une table");
		System.out.println("Veuillez saisir l'id de la table à supprimer");
		int id = scan.nextInt();
		scan.nextLine();

		try {
			tablebll.delete(id);
			System.out.println("La table a bien été supprimé");
		} catch (BLLException e) {
			System.out.println("Il est interdit de supprimer cette table ou cette table n'existe pas.\n"
					+ "Pour être supprimer une table ne dois plus avoir ni commandes, ni reservations");
		}
	}
}
