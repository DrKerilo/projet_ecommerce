package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService{
// association uml en java
@EJB
ICommandeDao commandeDao;
		
		@Override
		public Commande getCommande(Commande c, Client cl) {
			c.getClient();
			return commandeDao.getCommande(c, cl);
		}
		
		@Override
		public int deleteCommande(Commande c, Client cl) {
			c.getClient();
			return commandeDao.deleteCommande(c, cl);
		}
		
		@Override
		public int updateCommande(Commande c, Client cl) {
			c.getClient();
			return commandeDao.updateCommande(c, cl);
		}

}
