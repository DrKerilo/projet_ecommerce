package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitDao {
	
	public Produit add(Produit p);
	
	public int delete(Produit p);
	
	public int update(Produit p);
	
	public List<Produit> getAll(Categorie cat);
	
	public List<Produit> getAll();
	
	public Produit get(Produit p);
	
}
