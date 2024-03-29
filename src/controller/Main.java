package controller;

import java.util.Scanner;

public class Main {
	private static Scanner scan;
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	public static void main(String[] args) {

		System.out.println(BLUE + "Bienvenue dans notre application Administration restaurant");
		scan = new Scanner(System.in);

		System.out.println("Veuillez choisir l'action à réaliser"); 
		int choix;
		do {
			choix = afficherMenu();
			switch (choix) {
			case 1:
				System.out.println(BLUE+"Ajouter un restaurant");
				System.out.println("    a. Saisie manuelle");
				System.out.println("    b. Saisie automatique");
				String choixSaisie = scan.next();
				scan.nextLine();
				switch (choixSaisie) {
				case "a":
					TestTable.creerRestaurantavecTable();
					break;
				case "b":
					TestLecteur.listeRestaurants();
				break;
				default:
					System.out.println("Saisie non valide");
					break;
				}
				break;
			case 2:
				TestRestaurant.modifierRestaurant();
				break;
			case 3:
				TestRestaurant.supprimerRestaurant();
				break;
			case 4:{
				System.out.println("Créer une carte");
				TestCarte.creerCarte(); 
				break;}
			case 5:
				System.out.println("Modifier une carte");
				TestCarte.modifierCarte(); 
				break;
			case 6:
				System.out.println("Byebye"); 
				break;

			default:
				System.out.println("Saisie non valide");
				break;
			}
		} while (choix != 6);
		scan.close();

	}

	private static int afficherMenu() {
		System.out.println(PURPLE + "1." + RESET + CYAN + "Ajouter un restaurant" + RESET);
		System.out.println(PURPLE + "2." + RESET + CYAN + "Modifier un restaurant existant" + RESET);
		System.out.println(PURPLE + "3." + RESET + RED + "Supprimer un restaurant existant" + RESET);
		System.out.println(PURPLE + "4." + RESET + CYAN + "Créer une carte");
		System.out.println(PURPLE + "5." + RESET + CYAN + "Modifier une carte" + RESET);
		System.out.println(PURPLE + "6." + RESET + CYAN + "Quitter" + RESET);
		int choix = scan.nextInt();
		scan.nextLine();
		return choix;
	}

}
