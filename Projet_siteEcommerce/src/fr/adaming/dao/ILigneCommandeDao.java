package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeDao {
	
	public List<LigneCommande> getAllLignesCommandes(Commande commande);
	
	public LigneCommande ajoutLigneCommande(LigneCommande ligneCommande);
	
	public int suppressionLigneCommande(LigneCommande ligneCommande);
	
	public int modificationLigneCommande(LigneCommande ligneCommande);

}
