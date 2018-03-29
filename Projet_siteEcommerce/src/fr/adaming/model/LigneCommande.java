package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignesCommandes")
public class LigneCommande {
	
	// déclaration des attributs
	private int quantite;
	private int prix;
	
	// association uml en java
	@ManyToOne
	@JoinColumn(name="panier_id", referencedColumnName="id_panier")
	private Panier p;
	
	// déclaration des constructeurs
	public LigneCommande() {
		super();
	}
	public LigneCommande(int quantite, int prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}
	
	// getters et setters
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	
	
	

}
