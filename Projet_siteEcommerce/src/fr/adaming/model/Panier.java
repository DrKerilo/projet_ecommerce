package fr.adaming.model;

public class Panier {
	
	// association uml en java
	private LigneCommande ligneCommande;

	// d�claration des constructeurs
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
