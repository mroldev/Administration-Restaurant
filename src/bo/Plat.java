package bo;

public class Plat {
	private int id;
	private String nom;
	private int prix;
	private String description;
	private String categorie;
	private String image_plat_url;
	// id_carte

//constructor

	public Plat() {
	}

	public Plat(String nom, int prix, String description, String categorie, String image_plat_url) {
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
		this.image_plat_url = image_plat_url;
	}
	public Plat(String nom, int prix, String description, String categorie) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
	}
	public Plat(int id, String nom, int prix, String description, String categorie, String image_plat_url) {
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.description = description;
		this.categorie = categorie;
		this.image_plat_url = image_plat_url;
	}




	//data
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

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
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

//toString

}
