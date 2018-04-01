package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.LigneCommande;

@Stateless
public class PanierDaoImpl implements IPanierDao{
@PersistenceContext(unitName = "PU_commerce")
private EntityManager em;
	
	@Override
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande) {
		em.persist(ligneCommande);
		return ligneCommande;
	}

}
