package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.IProduitService;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitManagedBean implements Serializable{
	
	// transformer l'association uml en java
	@EJB
	IProduitService produitService;
	
	// déclaration des attributs du ManagedBean
	private Produit produit;
	private List<Produit> listeProduits;
	private Categorie categorie;
	//HttpSession session;

	// constructeur vide
	public ProduitManagedBean() {
		// instancier un produit
		this.produit  = new Produit();
		this.categorie = new Categorie();
	}
	
	@PostConstruct
	public void init() {
		// pour que la méthode s'exécute après l'instanciation du ProduitManagedBean
		//this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
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

	
	// méthodes du managedBean

	public String consulterTout(){
		listeProduits = produitService.getAll();
		if(listeProduits != null){
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, liste introuvable"));
			return "testMethodes.xhtml";
		}
	}
	public String ajouter(){
		Produit pAjoute = produitService.add(this.produit, this.produit.getCategorie());
		if(pAjoute != null){
			this.listeProduits = produitService.getAll();
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, produit non ajouté"));
			return "testMethodes.xhtml";
		}
	}
	
	public String supprimer(){
		int verif = produitService.delete(this.produit);
		if(verif != 0){
			this.listeProduits = produitService.getAll();
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, produit non supprimé"));
			return "testMethodes.xhtml";
		}
	}
	
	public String modifier(){
		int verif = produitService.update(this.produit, this.produit.getCategorie());
		if(verif !=0){
			this.listeProduits = produitService.getAll();
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, produit non modifié"));
			return "testMethodes.xhtml";
		}
	}
	
	public void getByIdEvent(){
		
		this.produit=produitService.get(this.produit);
	}
	
	public String consulter(){
		produit = produitService.get(this.produit);
		if(produit != null){
			return "formulaireTest.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, produit introuvable"));
			return "testMethodes.xhtml";
		}
	}
	
	public String consulterToutByCategorie(){
		listeProduits = produitService.getAll(this.categorie);
		if(listeProduits != null){
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, produit introuvable"));
			return "testMethodes.xhtml";
		}
	}

	

	

}
