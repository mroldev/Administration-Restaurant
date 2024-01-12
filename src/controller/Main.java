package controller;

import java.util.Scanner;

import bo.Restaurant;
import controller.TestRestaurant;

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
				TestRestaurant.creerRestaurant();
				// Rajouter le Do While s'il y a des erreures dans la saisie pour que l'utilisateur puisse refaire sa saisie
				break;
			case 2:
				System.out.println("2. Modifier un restaurant existant");
				break;
			case 3:
				System.out.println("3. Supprimer un restaurant existant");
				break;
			case 4:
				System.out.println("4. Créer une carte");
				break;
			case 5:
				System.out.println("5. Modifier une carte");
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
