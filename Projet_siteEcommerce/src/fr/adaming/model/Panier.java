package fr.adaming.model;

import javax.persistence.OneToMany;

public class Panier {
	
	// association uml en java
	@OneToMany(mappedBy="p")
	private LigneCommande lc;

	// getters et setters
	public LigneCommande getLc() {
		return lc;
	}

	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}
	
	

}
