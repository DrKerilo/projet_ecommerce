package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeService{
	
	public Commande getCommande(Commande c, Client cl);
	
	public int deleteCommande(Commande c, Client cl);
	
	public int updateCommande(Commande c, Client cl);

}
