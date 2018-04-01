package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Local
public interface ICommandeService{
	public List<LigneCommande> getAllLignesCommandes(Commande commande);

}
