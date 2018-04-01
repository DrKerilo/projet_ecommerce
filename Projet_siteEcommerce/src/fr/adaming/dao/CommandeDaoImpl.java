package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao{

	@PersistenceContext(unitName="PU_commerce")
	private EntityManager em;
	
	@Override
	public List<LigneCommande> getAllLignesCommandes(Commande commande) {
		// requête JPQL
		String req = "SELECT lc FROM LigneCommande lc WHERE lc.commande.idCommande=:pIdCommande";
		
		// objet Query
		Query query = em.createQuery(req);
		
		// params requête
		query.setParameter("pIdCommande", commande.getIdCommande());
		
		// envoyer requête et récup résultat
		return query.getResultList();
	}

}
