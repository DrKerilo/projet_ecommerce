package fr.adaming.dao;

import java.util.List;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {

	@PersistenceContext(unitName="PU_commerce")
	private EntityManager em;
	
	@Override
	public List<Categorie> getAll() {
		// Requ�te JPQL
		String req="SELECT cat FROM Categorie cat";
		// Query
		Query q=em.createQuery(req);
		// Envoi de la requ�te et r�cup�ration de la liste des cat�gories
		return q.getResultList();
	}

	@Override
	public Categorie add(Categorie c) {
		// Appel de la m�thode persist de EM
		em.persist(c);
		return c;	// La cat�gorie correctement ajout�e aura un id
	}

	@Override
	public int update(Categorie c) {
		// Requ�te JPQL
		String req="UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDesc WHERE c.idCategorie=:pId";
		// Cr�er Query
		Query q=em.createQuery(req);
		// Passage des param�tres
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDesc", c.getDescription());
		q.setParameter("pId", c.getIdCategorie());
		// Envoi de la requ�te et r�cup�ration du nombre de lignes modifi�es
		return q.executeUpdate();
	}

	@Override
	public int delete(Categorie c) {
		// Requ�te JPQL
		String req="DELETE FROM Categorie c WHERE c.idCategorie=:pId";
		// Query
		Query q=em.createQuery(req);
		// Passage des param�tres
		q.setParameter("pId", c.getIdCategorie());
		// Envoi requ�te et r�cup nombre de lignes modifi�es
		return q.executeUpdate();
	}

	@Override
	public Categorie get(Categorie c) throws EJBTransactionRolledbackException {
		// Requ�te JPQL
		String req="SELECT c FROM Categorie c WHERE c.idCategorie=:pId";
		// Query
		Query q=em.createQuery(req);
		// Passage des param�tres
		q.setParameter("pId", c.getIdCategorie());
		// Envoi et r�cup�ration du r�sultat
		return (Categorie) q.getSingleResult();
	}

}
