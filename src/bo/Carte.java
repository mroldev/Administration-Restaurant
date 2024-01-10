package bo;

public class Carte {
	private int id;
	private String nom;
	private int idRestaurant;
	
	public Carte() {
		
	}
	public Carte(String nom, int idRestaurant) {
		super();
		this.nom = nom;
		this.idRestaurant = idRestaurant;
	}
	
	public Carte(int id, String nom, int idRestaurant) {
		this.id = id;
		this.nom = nom;
		this.idRestaurant = idRestaurant;
	}
	
	
}
