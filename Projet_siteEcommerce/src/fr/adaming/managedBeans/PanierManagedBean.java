package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "panierMB")
@RequestScoped
public class PanierManagedBean implements Serializable{

	// transformer l'association uml en java
	@EJB
	ILigneCommandeService ligneCommandeService;
	
	@EJB
	ICommandeService commandeService;
	
	// attributs du managedBean
	private LigneCommande ligneCommande;
	private Produit produit;
	private Commande commande;
	HttpSession session;
	private List<LigneCommande> listeLignesCommandes;
	private Client client;

	// constructeur vide
	public PanierManagedBean() {
		ligneCommande = new LigneCommande();
		produit = new Produit();		
	}
	
	@PostConstruct
	public void init() {
		// pour que la méthode s'exécute après l'instanciation du ManagedBean
		this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	
	

	// méthodes du managedBean

	public String ajoutProduitPanier(){
		this.ligneCommande = ligneCommandeService.ajoutLigneCommande(this.ligneCommande, this.produit, this.commande);
		listeLignesCommandes = ligneCommandeService.getAllLignesCommandes(commande);
		session.setAttribute("LignesCommandesSession", listeLignesCommandes);
		return "recherche";
	}
	
	public String validerPanier(){
		return "engisterClient";
	}
	
	public String supprimerPanier(){
		commandeService.deleteCommande(this.commande, this.client);
		return "panier";
	}
	
	public void onRowEdit(RowEditEvent event){
		//ligneCommandeService.updateLigneCommande((LigneCommande) event.getObject(), commande);
		List<LigneCommande> listeLignesCommandes = ligneCommandeService.getAllLignesCommandes(commande);
		session.setAttribute("LignesCommandesSession", listeLignesCommandes);
	}
	

}
