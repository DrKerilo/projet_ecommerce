package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IPanierService;

@ManagedBean(name = "panierMB")
@RequestScoped
public class PanierManagedBean implements Serializable{

	// transformer l'association uml en java
	@EJB
	IPanierService panierService;
	
	// attributs du managedBean
	private LigneCommande ligneCommande;
	private Produit produit;
	private Commande commande;

	// constructeur vide
	public PanierManagedBean() {
		ligneCommande = new LigneCommande();
		produit = new Produit();
		commande = new Commande();
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


	// méthodes du managedBean

	public String ajoutProduitPanier(){
		panierService.ajoutLigneCommande(this.ligneCommande, this.produit, this.commande);
		return "panier";
	}
	
	public String validerPanier(){
		return "succes";
	}
	
	public String supprimerPanier(){
		return "panier";
	}
	
	

}
