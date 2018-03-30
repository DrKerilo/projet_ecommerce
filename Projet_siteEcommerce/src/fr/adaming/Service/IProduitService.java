package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.Dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {

	public Produit add(Produit p);

	public int delete(Produit p);

	public int update(Produit p);
	
	public List<Produit> getAll(); 

	public List<Produit> getAll(Categorie cat);

	public Produit get(Produit p);

}
