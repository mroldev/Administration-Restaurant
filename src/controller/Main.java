package controller;

import java.util.Scanner;


public class Main {
	private static Scanner scan;


	public static void main(String[] args) {
		System.out.println("Bienvenue dans notre application Administration restaurant");
		scan = new Scanner(System.in);
		
		System.out.println("Veuillez choisir l'action à réaliser");
		int choix;
		do {
			choix = afficherMenu();
			switch (choix) {
			case 1:
				TestTable.creerRestaurantavecTable(); // OK
				break;
			case 2:
				TestRestaurant.modifierRestaurant();
				break;
			case 3:
				System.out.println("3. Supprimer un restaurant existant");
				break;
			case 4:{
				System.out.println("4. Créer une carte");
				TestCarte.creerCarte(); // VOIR SI ON PEUT AUSSI AJOUTER LES PLATS
				break;}
			case 5:
				System.out.println("5. Modifier une carte");
				TestCarte.modifierCarte(); // ON MODIFIE UNIQUEMENT LE NOM DE LA CARTE ET SON RESTAUTANT.
				// VOIR SI ON PEUT MODIFIER LES PLATS SI POSSIBLE.
				break;
			case 6:
				System.out.println("Byebye"); //Ajouter err si lettre
				break;
				
			default:
				System.out.println("Saisie non valide");
				break;
			}
		} while (choix != 6);
		scan.close();

	}

	private static int afficherMenu() {
		System.out.println("1. Ajouter un restaurant");
		System.out.println("2. Modifier un restaurant existant");
		System.out.println("3. Supprimer un restaurant existant");
		System.out.println("4. Créer une carte");
		System.out.println("5. Modifier une carte");
		System.out.println("6. Quitter");
		int choix = scan.nextInt();
		scan.nextLine();
		return choix;
	}

}
