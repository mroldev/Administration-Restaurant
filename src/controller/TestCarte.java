package controller;

import java.util.List;
import bll.BLLException;
import bll.CarteBLL;
import bo.Carte;

public class TestCarte {
	private static CarteBLL bll;

	public static void main(String[] args) {
		try {
			bll = new CarteBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		listerCartes();

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

}
