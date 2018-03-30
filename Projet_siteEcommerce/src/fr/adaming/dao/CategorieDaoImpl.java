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
		// Requête JPQL
		String req="SELECT cat FROM Categorie cat";
		// Query
		Query q=em.createQuery(req);
		// Envoi de la requête et récupération de la liste des catégories
		return q.getResultList();
	}

	@Override
	public Categorie add(Categorie c) {
		// Appel de la méthode persist de EM
		em.persist(c);
		return c;	// La catégorie correctement ajoutée aura un id
	}

	@Override
	public int update(Categorie c) {
		// Requête JPQL
		String req="UPDATE Categorie c SET c.nomCategorie=:pNom, c.photo=:pPhoto, c.description=:pDesc WHERE c.idCategorie=:pId";
		// Créer Query
		Query q=em.createQuery(req);
		// Passage des paramètres
		q.setParameter("pNom", c.getNomCategorie());
		q.setParameter("pPhoto", c.getPhoto());
		q.setParameter("pDesc", c.getDescription());
		q.setParameter("pId", c.getIdCategorie());
		// Envoi de la requête et récupération du nombre de lignes modifiées
		return q.executeUpdate();
	}

	@Override
	public int delete(Categorie c) {
		// Requête JPQL
		String req="DELETE FROM Categorie c WHERE c.idCategorie=:pId";
		// Query
		Query q=em.createQuery(req);
		// Passage des paramètres
		q.setParameter("pId", c.getIdCategorie());
		// Envoi requête et récup nombre de lignes modifiées
		return q.executeUpdate();
	}

	@Override
	public Categorie get(Categorie c) throws EJBTransactionRolledbackException {
		// Requête JPQL
		String req="SELECT c FROM Categorie c WHERE c.idCategorie=:pId";
		// Query
		Query q=em.createQuery(req);
		// Passage des paramètres
		q.setParameter("pId", c.getIdCategorie());
		// Envoi et récupération du résultat
		return (Categorie) q.getSingleResult();
	}

}
