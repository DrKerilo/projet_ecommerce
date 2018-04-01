package fr.adaming.model;

public class Panier {
	
	// association uml en java
	private LigneCommande ligneCommande;

	// déclaration des constructeurs
	public Panier() {
		super();
	}

	// getters et setters
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	
	
	
	

}
