package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Admin;

@Stateless
public class AdminDaoImpl implements IAdminDao{
	@PersistenceContext(unitName="PU_commerce") // pour injecter l'entityManager
	private EntityManager em;

	@Override
	public Admin isExist(Admin admin) {
		// la requête JPQL pour récupérer l'admin
		String req ="SELECT a FROM Admin a WHERE a.mail=:pMail AND a.mdp=:pMdp";
		
		// créer un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);
		
		// passage des paramètres
		query.setParameter("pMail", admin.getMail());
		query.setParameter("pMdp", admin.getMdp());
		
		// envoyer la requête et retourner le résultat
		return (Admin) query.getSingleResult();
	}

}
