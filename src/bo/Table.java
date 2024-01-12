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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getNombrePlace() {
		return nombrePlace;
	}

	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	@Override
	public String toString() {
		return "Table id : " + id + ", numero table " + numero + ", nombre de places : " + nombrePlace + ", etat : " + etat
				+ ", appartenant au Restaurant ayant l'id : " + idRestaurant;
	}
	
	
}
