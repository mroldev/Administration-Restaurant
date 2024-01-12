package controller.lecteur;

import java.util.ArrayList;
import java.util.List;

public class LecteurCSVException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private List<String> erreurs = new ArrayList<>();
	
	public void ajouterErreur(String erreur) {
		erreurs.add(erreur);
	}
	
	public List<String> getErreurs() {
		return erreurs;
	}
}
