package fr.adaming.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="commandes")
public class Commande {
	
	// déclaration des attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_commande")
	private Long idCommande;
	private Date dateCommande;
	
	// transformation uml en java
	@ManyToOne
	@JoinColumn(name="client_id", referencedColumnName="id_client")
	private Client client;
	
	@OneToMany(mappedBy="commande", cascade=CascadeType.REMOVE)
	private LigneCommande ligneCommande;
	
	// déclaration des constructeurs
	public Commande() {
		super();
	}
	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}
	public Commande(Long idCommande, Date dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}
	
	// getters et setters
	public Long getIdCommande() {
		return idCommande;
	}
	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}
	public Date getDateCommande() {
		return dateCommande;
	}
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	
	

}
