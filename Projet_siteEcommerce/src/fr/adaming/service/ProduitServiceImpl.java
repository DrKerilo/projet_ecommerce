package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{

	// transformation uml en java
	@EJB
	private IProduitDao produitDao;

	@Override
	public Produit add(Produit p, Categorie categorie) {
		p.setCategorie(categorie);
		return produitDao.add(p);
	}

	@Override
	public int delete(Produit p) {
		return produitDao.delete(p);
	}

	@Override
	public int update(Produit p, Categorie categorie) {
		p.setCategorie(categorie);
		return produitDao.update(p);
	}
	
	@Override
	public List<Produit> getAll() {
		return produitDao.getAll();
	}

	@Override
	public List<Produit> getAll(Categorie categorie) {
		return produitDao.getAll(categorie);
	}

	@Override
	public Produit get(Produit p) {
		return produitDao.get(p);
	}


}
