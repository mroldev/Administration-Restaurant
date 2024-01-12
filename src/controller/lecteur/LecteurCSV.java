package controller.lecteur;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import bo.Restaurant;

public class LecteurCSV {
//	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter HEURE_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
	
	public List<Restaurant> lireCSV(String chemin, boolean aEntete) throws LecteurCSVException {
		LecteurCSVException csvexception = new LecteurCSVException();
		List<Restaurant> listeRestaurants = new ArrayList<>();
		File fichier = new File(chemin);
		int cpt = 1;
		try (Scanner scan = new Scanner(fichier)) {
			if (aEntete) {
				scan.nextLine(); // On saute la ligne d'entetes
				cpt++;
			}
			while (scan.hasNext()) {
				ligneVersRestaurant(csvexception, listeRestaurants, scan, cpt);
				cpt++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if (csvexception.getErreurs().size() > 0) {
			throw csvexception;
		}
		
		return listeRestaurants;
	}

	private void ligneVersRestaurant(LecteurCSVException csvexception, List<Restaurant> listeRestaurants, Scanner scan, int cpt) {
		String ligne = scan.nextLine();
		String[] donnees = ligne.split(",");
		if (donnees.length != 4) {
			// System.err.println(ligne);
			csvexception.ajouterErreur("[" + cpt + "]" + ligne + " (la ligne doit contenir 4 informations)");
		} else {
			String nom = donnees[0];
			String adresse = donnees[1];
			String heureOuvertureStrg = donnees[2];
			String heureFermetureStrg = donnees[3];
			
			LocalTime heureOuverture = null;
			LocalTime heureFermeture = null;
			
			try {
				heureOuverture = LocalTime.parse(heureOuvertureStrg, HEURE_FORMAT);
				heureFermeture = LocalTime.parse(heureFermetureStrg, HEURE_FORMAT);
			} catch (DateTimeParseException e) {
				// System.err.println(ligne);
				csvexception.ajouterErreur("[" + cpt + "]" + ligne + "(l'heure ne respecte pas le format HH:mm)");
			}
			
			Restaurant restaurant = new Restaurant();
			restaurant.setNom(nom);
			restaurant.setAdresse(adresse);
			restaurant.setHeureOuverture(heureOuverture);
			restaurant.setHeureFermeture(heureOuverture);
			
			listeRestaurants.add(restaurant);
		}
	}
}
