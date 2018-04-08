package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="categories")
public class Categorie implements Serializable {
	
	// Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_categorie")
	private Long idCategorie;
	private String nomCategorie;
	@Lob
	private byte[] photo;
	@Transient
	private String image;
	private String description;
	
	// Transformation de l'association UML en Java 
	// OneToMany avec Produit (si on supprime une catégorie, on supprime les produits à l'intérieur)
	@OneToMany(mappedBy="categorie",cascade=CascadeType.REMOVE)
	private List<Produit> listeProduits;
	
	// Constructeurs
	public Categorie() {
		super();
	}
	public Categorie(String nomCategorie, byte[] photo, String description) {
		super();
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}
	public Categorie(Long idCategorie, String nomCategorie, byte[] photo, String description) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.photo = photo;
		this.description = description;
	}
	
	// Getters et setters
	public Long getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(Long idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Produit> getListeProduits() {
		return listeProduits;
	}
	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	

}
