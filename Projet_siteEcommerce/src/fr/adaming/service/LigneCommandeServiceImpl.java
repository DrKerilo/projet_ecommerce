package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{
// transformation uml en java
@EJB
ILigneCommandeDao ligneCommandeDao;

	@Override
	public List<LigneCommande> getAllLignesCommandes(Commande commande) {
		return ligneCommandeDao.getAllLignesCommandes(commande);
	}
	
	@Override
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande) {
		ligneCommande.setProduit(produit);
		ligneCommande.setCommande(commande);
		ligneCommande.setPrix((int)produit.getPrix()*ligneCommande.getQuantite());		
		return ligneCommandeDao.ajoutLigneCommande(ligneCommande);
	}

	@Override
	public int suppressionLigneCommande(LigneCommande ligneCommande) {
		//ligneCommande.setCommande(commande);
		return ligneCommandeDao.suppressionLigneCommande(ligneCommande);
		
	}

	@Override
	public int modificationLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande) {
		ligneCommande.setProduit(produit);
		ligneCommande.setCommande(commande);
		ligneCommande.setPrix((int)produit.getPrix()*ligneCommande.getQuantite());
		return ligneCommandeDao.modificationLigneCommande(ligneCommande);
	}

}
