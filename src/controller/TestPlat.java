package controller;

import java.util.List;
import java.util.Scanner;

import bll.BLLException;
import bll.PlatBLL;
import bo.Plat;

public class TestPlat {
	private static Scanner scan;
	private static PlatBLL bll;

	public static void main(String[] args) {

		System.out.println("Bienvenu dans la partie plats");
		scan = new Scanner(System.in);
		try {
			bll = new PlatBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		// aFFICHER PLAT
		listerPlat();
	}

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

}
