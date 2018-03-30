package fr.adaming.Dao;

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
		// requête JPQL pour modifier un produit
		String req = "UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.selectionne=:pSelectionne, p.photo=:pPhoto WHERE p.idProduit=:pIdProduit";
		return 0;
	}

	@Override
	public List<Produit> getAll() {
		// requête JPQL pour récupérer les produits
		String req = "SELECT p FROM Produit p";
		
		// créer un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);

		// envoyer la requête et retourner le résultat
		return query.getResultList();
	}

	@Override
	public List<Produit> getAll(Categorie cat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit get(Produit p) {
		// TODO Auto-generated method stub
		return null;
	}

}
