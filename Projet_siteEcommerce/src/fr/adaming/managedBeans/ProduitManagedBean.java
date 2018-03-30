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

import fr.adaming.Service.IProduitService;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@ManagedBean(name="produitMB")
@RequestScoped
public class ProduitManagedBean implements Serializable{
	
	// transformer l'association uml en java
	@EJB
	IProduitService produitService;
	
	// déclaration des attributs du ManagedBean
	private Produit produit;
	private List<Produit> listeProduits;
	HttpSession session;

	// constructeur vide
	public ProduitManagedBean() {
		// instancier un produit
		this.produit  = new Produit();	
	}
	
	@PostConstruct
	public void init() {
		// pour que la méthode s'exécute après l'instanciation du ProduitManagedBean
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
	
	// méthodes du managedBean
	
	public String consulterTout(){
		listeProduits = produitService.getAll();
		if(listeProduits != null){
			this.session.setAttribute("listeProduits", listeProduits);
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, liste introuvable"));
			return "testMethodes.xhtml";
		}
	}
	public String ajouter(){
		Produit pAjoute = produitService.add(this.produit);
		if(pAjoute != null){
			this.listeProduits = produitService.getAll();
			this.session.setAttribute("listeProduits", listeProduits);
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
			this.session.setAttribute("listeProduits", listeProduits);
			return "testMethodes.xhtml";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Une erreur est survenue, produit non ajouté"));
			return "testMethodes.xhtml";
		}
	}

	

	

}
