package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao {
	@PersistenceContext(unitName = "PU_commerce") // pour injecter
													// l'entityManager
	private EntityManager em;

	@Override
	public Produit add(Produit p) {
		em.persist(p);
		return p;
	}

	@Override
	public int delete(Produit p) {

		try {
			em.remove(em.find(Produit.class, p.getIdProduit()));
			return 1;
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(Produit p) {
		// requ�te JPQL pour modifier un produit
		String req = "UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.selectionne=:pSelectionne, p.photo=:pPhoto, p.categorie=:pCategorie WHERE p.idProduit=:pIdProduit";
		
		Query query = em.createQuery(req);

		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pSelectionne", p.isSelectionne());
		query.setParameter("pPhoto", p.getPhoto());
		query.setParameter("pCategorie", p.getCategorie().getIdCategorie());
		query.setParameter("pIdProduit", p.getIdProduit());

		return query.executeUpdate();
	}

	@Override
	public List<Produit> getAll() {
		// requ�te JPQL pour r�cup�rer les produits
		String req = "SELECT p FROM Produit p";
		
		// cr�er un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);

		// envoyer la requ�te et retourner le r�sultat
		return query.getResultList();
	}

	@Override
	public List<Produit> getAll(Categorie categorie) {
		// requ�te JPQL pour r�cup�rer la liste de produits par cat�gorie
		String req = "SELECT p FROM Produit p WHERE p.categorie.idCategorie=:pIdCategorie";
		
		// cr�ation d'un objet de type Query pour envoyer la requ�te JPQL
		Query query = em.createQuery(req);
		
		// passage des param�tres
		query.setParameter("pIdCategorie", categorie.getIdCategorie());
		
		// envoyer la requ�te et retourner le r�sultat
		return query.getResultList();
	}

	@Override
	public Produit get(Produit p) {
		Produit pOut = em.find(Produit.class, p.getIdProduit());
		return pOut;
	}

}
