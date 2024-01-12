package bll;

import java.util.Arrays;
import java.util.List;

import bo.Carte;
import bo.Plat;
import dal.DALException;
import dal.PlatDAO;

public class PlatBLL {
	private PlatDAO dao;

	public PlatBLL() throws BLLException {
		try {
			dao = new PlatDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}

	// SELECT ALL
	public List<Plat> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des composants", e);

		}
	}

	// SELECT BY ID
	public Plat selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation du composant d'id " + id, e);
		}
	}

	// INSERT
	public Plat insert(String nom, String prix, String description, String categorie, String image_plat_url,
			Carte id_carte) throws BLLException {
		// ERREUR
		BLLException blleException = new BLLException();
		if (nom.length() > 30) {
			blleException.ajouterErreur("Le nom doit faire au maximum 30 caractères");
		}
		if (description.length() > 255) {
			blleException.ajouterErreur("Le nom doit faire au maximum 255 caractères");
		}

		List<String> valeursValides = Arrays.asList("ENTREE", "PLAT", "DESSERT", "DESSERT");
		if (!valeursValides.contains(categorie)) {
			blleException.ajouterErreur("La categorie du plat doit valoir ENTREE, PLAT, DESSERT ou DESSERT");
		}
		if (image_plat_url.length() > 255) {
			blleException.ajouterErreur("L'image doit faire au maximum 255 caractères");
		}
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		Plat plat = new Plat();

		plat = new Plat(nom, prix, description, categorie, image_plat_url, id_carte);
		try {
			dao.insert(plat);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Echec de l'insertion", e);
		}
		return plat;
	}

	// UPDATE
	public void update(Plat plat) throws BLLException {
		BLLException blleException = new BLLException();

		if (plat.getNom().length() > 30) {
			blleException.ajouterErreur("Le nom doit faire au maximum 30 caractères");
		}
		if (plat.getDescription().length() > 255) {
			blleException.ajouterErreur("Le nom doit faire au maximum 255 caractères");
		}

		List<String> valeursValides = Arrays.asList("ENTREE", "PLAT", "DESSERT", "DESSERT");
		if (!valeursValides.contains(plat.getCategorie())) {
			blleException.ajouterErreur("La categorie du plat doit valoir ENTREE, PLAT, DESSERT ou DESSERT");
		}
		if (plat.getImage_plat_url().length() > 255) {
			blleException.ajouterErreur("L'image doit faire au maximum 255 caractères");
		}
		if (blleException.getErreurs().size() > 0) {
			throw blleException;
		}

		try {
			dao.update(plat);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise a jour du plat", e);
		}
	}
	// DELETE

	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression du plat", e);
		}
	}

}