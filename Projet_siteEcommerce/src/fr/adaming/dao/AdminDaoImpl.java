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
		// la requ�te JPQL pour r�cup�rer l'admin
		String req ="SELECT a FROM Admin a WHERE a.mail=:pMail AND a.mdp=:pMdp";
		
		// cr�er un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);
		
		// passage des param�tres
		query.setParameter("pMail", admin.getMail());
		query.setParameter("pMdp", admin.getMdp());
		
		// envoyer la requ�te et retourner le r�sultat
		return (Admin) query.getSingleResult();
	}

}
