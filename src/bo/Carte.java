package bo;

public class Carte {
	private int id;
	private String nom;
	private int idRestaurant;

	public Carte() {

	}

	public Carte(String nom, int idRestaurant) {
		this.nom = nom;
		this.idRestaurant = idRestaurant;
	}

	public Carte(int id, String nom, int idRestaurant) {
		this.id = id;
		this.nom = nom;
		this.idRestaurant = idRestaurant;
	}

	public Carte(String nom) {
		this.nom = nom;
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

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	@Override
	public String toString() {
		return "Carte [id=" + id + ", nom=" + nom + ", idRestaurant=" + idRestaurant + "]";
	}

}
