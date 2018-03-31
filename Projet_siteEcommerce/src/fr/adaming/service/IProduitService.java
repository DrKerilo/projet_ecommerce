package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {

	public Produit add(Produit p, Categorie categorie);

	public int delete(Produit p);

	public int update(Produit p,  Categorie categorie);
	
	public List<Produit> getAll(); 

	public List<Produit> getAll(Categorie categorie);

	public Produit get(Produit p);

}
