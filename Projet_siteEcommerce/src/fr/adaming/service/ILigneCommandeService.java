package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
@Local
public interface ILigneCommandeService {

	public List<LigneCommande> getAllLignesCommandes(Commande commande);
		
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande);
	
	public int suppressionLigneCommande(LigneCommande ligneCommande);
	
	public int modificationLigneCommande(LigneCommande ligneCommande, Produit produit, Commande commande);

}

