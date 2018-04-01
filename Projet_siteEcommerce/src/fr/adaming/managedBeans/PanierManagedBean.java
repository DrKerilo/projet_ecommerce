package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.IPanierService;

@ManagedBean(name = "panierMB")
@RequestScoped
public class PanierManagedBean implements Serializable{

	// transformer l'association uml en java
	@EJB
	IPanierService panierService;
	
	@EJB
	ICommandeService commandeService;
	
	// attributs du managedBean
	private LigneCommande ligneCommande;
	private Produit produit;
	private Commande commande;
	HttpSession session;
	private List<LigneCommande> listeLignesCommandes;

	// constructeur vide
	public PanierManagedBean() {
		ligneCommande = new LigneCommande();
		produit = new Produit();
		commande = new Commande();
	}
	
	@PostConstruct
	public void init() {
		// pour que la méthode s'exécute après l'instanciation du ManagedBean
		this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// guetters et seters
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}
		
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}	
	
	public List<LigneCommande> getListeLignesCommandes() {
		return listeLignesCommandes;
	}

	public void setListeLignesCommandes(List<LigneCommande> listeLignesCommandes) {
		this.listeLignesCommandes = listeLignesCommandes;
	}

	// méthodes du managedBean

	public String ajoutProduitPanier(){
		this.ligneCommande = panierService.ajoutLigneCommande(this.ligneCommande, this.produit, this.commande);
		listeLignesCommandes = commandeService.getAllLignesCommandes(commande);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LignesCommandesSession", listeLignesCommandes);
		return "panier";
	}
	
	public String validerPanier(){
		return "engisterClient";
	}
	
	public String supprimerPanier(){
		return "panier";
	}
	
	

}
