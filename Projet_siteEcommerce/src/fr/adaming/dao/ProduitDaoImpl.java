package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

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
		String req = "UPDATE Produit p SET p.designation=:pDesignation, p.description=:pDescription, p.prix=:pPrix, p.quantite=:pQuantite, p.selectionne=:pSelectionne, p.photo=:pPhoto, p.categorie.idCategorie=:pIdCategorie WHERE p.idProduit=:pIdProduit";
		
		Query query = em.createQuery(req);

		query.setParameter("pDesignation", p.getDesignation());
		query.setParameter("pDescription", p.getDescription());
		query.setParameter("pPrix", p.getPrix());
		query.setParameter("pQuantite", p.getQuantite());
		query.setParameter("pSelectionne", p.isSelectionne());
		query.setParameter("pPhoto", p.getPhoto());
		query.setParameter("pIdCategorie", p.getCategorie().getIdCategorie());
		query.setParameter("pIdProduit", p.getIdProduit());

		return query.executeUpdate();
	}

	@Override
	public List<Produit> getAll() {
		// requête JPQL pour récupérer les produits
		String req = "SELECT p FROM Produit p";
		// créer un objet de type Query pour envoyer la requete JPQL
		Query query = em.createQuery(req);
		// Récupération du résultat
		List<Produit> listeOut=query.getResultList();
		// Chargement des images
		for(Produit prod: listeOut){
			prod.setImage("data:image/png;base64,"+Base64.encodeBase64String(prod.getPhoto()));
		}
		// envoyer la requête et retourner le résultat
		return listeOut;
	}

	@Override
	public List<Produit> getAll(Categorie categorie) {
		// requête JPQL pour récupérer la liste de produits par catégorie
		String req = "SELECT p FROM Produit p WHERE p.categorie.idCategorie=:pIdCategorie";
		// création d'un objet de type Query pour envoyer la requête JPQL
		Query query = em.createQuery(req);
		// passage des paramètres
		query.setParameter("pIdCategorie", categorie.getIdCategorie());
		// Récupération du résultat
		List<Produit> listeOut=query.getResultList();
		// Chargement des images
		for(Produit prod: listeOut){
			prod.setImage("data:image/png;base64,"+Base64.encodeBase64String(prod.getPhoto()));
		}
		// retourner le résultat
		return listeOut;
	}

	@Override
	public Produit get(Produit p) {
		Produit pOut = em.find(Produit.class, p.getIdProduit());
		pOut.setImage("data:image/png;base64,"+Base64.encodeBase64String(pOut.getPhoto()));
		return pOut;
	}

}
