package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao{

	@PersistenceContext(unitName="PU_commerce")
	private EntityManager em;

	@Override
	public Commande getCommande(Commande c, Client cl) {
		// requête JPQL
		String req = "SELECT c FROM Commande c WHERE c.idCommande=:pIdC AND c.client.idClient=:pIdCl";
		
		// objet Query
		Query query = em.createQuery(req);
		
		// params requête
		query.setParameter("pIdC", c.getIdCommande());
		query.setParameter("pIdCl", cl.getIdClient());
		
		// envoyer requête et récup résultat
		return (Commande) query.getSingleResult();
	}

	@Override
	public int deleteCommande(Commande c, Client cl) {
		// requête JPQL
		String req = "DELETE c FROM Commande c WHERE c.idCommande=:pIdC AND c.client.idClient=:pIdCl";

		// créer un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);

		// passage des paramètres
		query.setParameter("pIdC", c.getIdCommande());
		query.setParameter("pIdCl", cl.getIdClient());

		// envoyer la requête et retourner le résultat
		return query.executeUpdate();
	}

	@Override
	public int updateCommande(Commande c, Client cl) {
		// requête JPQL
		String req = "UPDATE Commande c SET c.dateCommande=:pDate, c.listeLignesCommandes=:pIdL WHERE c.idCommande=:pIdC AND c.client.idClient=:pIdCl";
		
		// créer un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);
		
		// passage des paramètres
		query.setParameter("pDate", c.getDateCommande());
		query.setParameter("pIdL", c.getListeLignesCommandes());
		query.setParameter("pIdC", c.getIdCommande());
		query.setParameter("pIdCl", cl.getIdClient());
		
		// envoyer la requête et retourner le résultat
		return query.executeUpdate();
	}


}
