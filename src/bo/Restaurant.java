package bo;

import java.time.LocalTime;

public class Restaurant {
	private int id;
	private String nom;
	private String adresse;
	private LocalTime heureOuverture;
	private LocalTime heureFermeture;
	private String imageRestaurantUrl;

	public Restaurant() {
	}

	public Restaurant(String nom, String adresse, LocalTime heureOuverture, LocalTime heureFermeture) {
		this.nom = nom;
		this.adresse = adresse;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}

	public Restaurant(int id, String nom, String adresse, LocalTime heureOuverture, LocalTime heureFermeture) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}

	public Restaurant(int id, String nom, String adresse, LocalTime heureOuverture, LocalTime heureFermeture,
			String imageRestaurantUrl) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
		this.imageRestaurantUrl = imageRestaurantUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public LocalTime getHeureOuverture() {
		return heureOuverture;
	}

	public void setHeureOuverture(LocalTime heureOuverture) {
		this.heureOuverture = heureOuverture;
	}

	public LocalTime getHeureFermeture() {
		return heureFermeture;
	}

	public void setHeureFermeture(LocalTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}

	public String getImageRestaurantUrl() {
		return imageRestaurantUrl;
	}

	public void setImageRestaurantUrl(String imageRestaurantUrl) {
		this.imageRestaurantUrl = imageRestaurantUrl;
	}

	@Override
	public String toString() {

		return "ID :" + this.id + " - " + this.nom + " - " + this.adresse + " - " + "ouvert de : " + this.heureOuverture
				+ " " + "jusqu'à " + this.heureFermeture;
	}

}
