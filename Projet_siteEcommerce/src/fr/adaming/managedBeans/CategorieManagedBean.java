package fr.adaming.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "categorieMB")
@RequestScoped
public class CategorieManagedBean {

	// Attributs
	private Categorie categorie;
	private List<Categorie> listeCategories;

	// Transformation de l'association UML en Java
	@EJB
	ICategorieService categorieService;

	// Constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
	}

	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	// M�thodes m�tiers
	// Consulter toutes les cat�gories
	public String consulterTout() {
		this.listeCategories = categorieService.getAll();
		return "testVal"; // A revoir !
	}

	public String ajouter() {
		Categorie catAjout = categorieService.add(categorie);
		if (catAjout.getIdCategorie() != 0) {
			// R�cup�rer la liste des cat�gories mise � jour
			listeCategories = categorieService.getAll();
			return "testVal";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur dans l'ajout."));
			return "testVal";
		}
	}

	public String modifier() {
		int verif = categorieService.update(categorie);
		if (verif != 0) {
			// R�cup�rer la liste des cat�gories mise � jour
			listeCategories = categorieService.getAll();
			return "testVal";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La cat�gorie indiqu�e n'existe pas."));
			return "testVal";
		}
	}

}
