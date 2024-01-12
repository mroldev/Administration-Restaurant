package bo;

public class Table {
	private int id;
	private int numero;
	private int nombrePlace;
	private String etat;
	private int idRestaurant;

	public Table() {
	}

	public Table(int numero, int nombrePlace, String etat, int idRestaurant) {
		this.numero = numero;
		this.nombrePlace = nombrePlace;
		this.etat = etat;
		this.idRestaurant = idRestaurant;
	}
	
	public Table(int id, int numero, int nombrePlace, String etat, int idRestaurant) {
		this.id = id;
		this.numero = numero;
		this.nombrePlace = nombrePlace;
		this.etat = etat;
		this.idRestaurant = idRestaurant;
	}
}
