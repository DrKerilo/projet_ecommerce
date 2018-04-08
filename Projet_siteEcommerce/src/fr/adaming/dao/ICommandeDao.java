package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeDao {
	public Commande getCommande(Commande c, Client cl);
	
	public int deleteCommande(Commande c, Client cl);
	
	public int updateCommande(Commande c, Client cl);

}
