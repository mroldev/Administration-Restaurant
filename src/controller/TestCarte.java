package controller;

import java.util.List;
import java.util.Scanner;
import bll.BLLException;
import bll.CarteBLL;
import bo.Carte;

public class TestCarte {
	private static CarteBLL bll;
	private static Scanner scan;

	public static void main(String[] args) {
		try {
			bll = new CarteBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		scan = new Scanner(System.in);
		// listerCartes();
		trouverCarteParID();
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
		System.out.println("Vous avez choisi de chercher un restaurant par son ID");

		System.out.println("Veuillez saisir son ID");
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

}
