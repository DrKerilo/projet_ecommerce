package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
@Local
public interface IPanierService {
	
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande);

}

