package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;

@Local
public interface IPanierDao {
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande);

}
