package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "produitMB")
@SessionScoped
public class ProduitManagedBean implements Serializable {

	// transformer l'association uml en java
	@EJB
	IProduitService produitService;

	@EJB
	ICategorieService catService;

	// déclaration des attributs du ManagedBean
	private Produit produit;
	private List<Produit> listeProduits;
	private Categorie categorie;
	HttpSession session;
	private UploadedFile uf;

	// constructeur vide
	public ProduitManagedBean() {
		// instancier un produit
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.uf=new UploadedFileWrapper();
	}

	@PostConstruct
	public void init() {
		this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// guetters et setters
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public List<Produit> getListeProduits() {
		return listeProduits;
	}
	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public UploadedFile getUf() {
		return uf;
	}
	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	// méthodes du managedBean

	public String consulterTout() {
		listeProduits = produitService.getAll();
		if (listeProduits != null) {
			return "recherche.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, liste introuvable."));
			return "testMethodes.xhtml";
		}
	}

	public String ajouter() {
		// Ajouter la photo du produit
		produit.setPhoto(uf.getContents());
		this.categorie = catService.get(categorie);
		Produit pAjoute = produitService.add(this.produit, categorie);
		if (pAjoute != null) {
			this.listeProduits = produitService.getAll();
			return "espaceAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, produit non ajouté."));
			return "ajoutadmin";
		}
	}

	public String supprimer() {
		int verif = produitService.delete(this.produit);
		if (verif != 0) {
			this.listeProduits = produitService.getAll();
			return "produitsadmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, produit non supprimé."));
			return "produitsadmin";
		}
	}

	// Non utilisée : la modification se fait via RowEdit
	public String modifier() {
		int verif = produitService.update(this.produit, this.categorie);
		if (verif != 0) {
			this.listeProduits = produitService.getAll();
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, produit non modifié."));
			return "testMethodes.xhtml";
		}
	}

	public void onRowEdit(RowEditEvent event) {
		produitService.update((Produit) event.getObject(),this.categorie);
		listeProduits = produitService.getAll();
		session.setAttribute("listeProduits", listeProduits);
		FacesMessage msg = new FacesMessage("Produit modifié", ((Produit) event.getObject()).getDesignation());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annulée", ((Produit) event.getObject()).getDesignation());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void getByIdEvent() {

		this.produit = produitService.get(this.produit);
	}

	public String consulter() {
		produit = produitService.get(this.produit);
		if (produit != null) {
			return "formulaireTest.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, produit(s) introuvable(s)."));
			return "testMethodes.xhtml";
		}
	}

	public String consulterToutByCategorie() {
		this.categorie = catService.get(categorie);
		listeProduits = produitService.getAll(this.categorie);
		if (listeProduits != null) {
			return "produitsadmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Une erreur est survenue, produit(s) introuvable(s)."));
			return "espaceAdmin";
		}
	}

}
