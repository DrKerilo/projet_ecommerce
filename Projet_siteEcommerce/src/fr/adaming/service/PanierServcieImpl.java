package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IPanierDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class PanierServcieImpl implements IPanierService{
// transformation uml en java
@EJB
IPanierDao panierDao;

	@Override
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande) {
		ligneCommande.setProduit(produit);
		ligneCommande.setCommande(commande);
		ligneCommande.setPrix((int)produit.getPrix()*ligneCommande.getQuantite());
		panierDao.ajoutLigneCommande(ligneCommande);
		return ligneCommande;
	}

}
