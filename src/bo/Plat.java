package bo;

public class Plat {
	private int id;
	private String nom;
	private String prix;
	private String description;
	private String categorie;
	private String image_plat_url;
	private Carte carte; // id_carte

	// constructor
	public Plat() {
	}

	public Plat(String nom, String prix, String description, String categorie, String image_plat_url, Carte carte) {
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
		this.image_plat_url = image_plat_url;
		this.carte = carte;
	}

	public Plat(int id, String nom, String prix, String description, String categorie, String image_plat_url,
			Carte carte) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
		this.image_plat_url = image_plat_url;
		this.carte = carte;
	}

	// data
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

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getImage_plat_url() {
		return image_plat_url;
	}

	public void setImage_plat_url(String image_plat_url) {
		this.image_plat_url = image_plat_url;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	// toString
	@Override
	public String toString() {
		return "Plat [id=" + id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", categorie="
				+ categorie + ", image_plat_url=" + image_plat_url + ", carte=" + carte + "]";
	}

}
