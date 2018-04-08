package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao{
@PersistenceContext(unitName = "PU_commerce")
private EntityManager em;

	@Override
	public List<LigneCommande> getAllLignesCommandes(Commande commande) {
		// requ�te JPQL
		String req = "SELECT lc FROM LigneCommande lc WHERE lc.commande.idCommande=:pIdCommande";
		
		// objet Query
		Query query = em.createQuery(req);
		
		// params requ�te
		query.setParameter("pIdCommande", commande.getIdCommande());
		
		// envoyer requ�te et r�cup r�sultat
		return query.getResultList();
	}
	
	@Override
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande) {
		em.persist(ligneCommande);
		return ligneCommande;
	}

	@Override
	public int suppressionLigneCommande(LigneCommande ligneCommande) {
		// requ�te JPQL
		String req = "DELETE lc FROM LigneCommande lc WHERE lc.idLigneCommande=:pId";

		// cr�er un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);

		// passage des param�tres
		query.setParameter("pId", ligneCommande.getIdLigneCommande());

		// envoyer la requ�te et retourner le r�sultat
		return query.executeUpdate();
		
	}

	@Override
	public int modificationLigneCommande(LigneCommande ligneCommande) {
		// req�te JPQL
		String req = "UPDATE LigneCommande lc SET lc.quantite=:pQuantite, lc.prix=:pPrix, lc.produit.idProduit=:pIdP, lc.commande.idCommande=:pIdC WHERE lc.idLigneCommande=:pIdL";
		
		// cr�er un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);
	
		// passage des param�tres
		query.setParameter("pQuantite", ligneCommande.getQuantite());
		query.setParameter("pPrix", ligneCommande.getPrix());
		query.setParameter("pIdP", ligneCommande.getProduit().getIdProduit());
		query.setParameter("pIdC", ligneCommande.getCommande().getIdCommande());
		query.setParameter("pIdL", ligneCommande.getIdLigneCommande());
	
		// envoyer la requ�te et retourner le r�sultat		
		return query.executeUpdate();
	}


}
