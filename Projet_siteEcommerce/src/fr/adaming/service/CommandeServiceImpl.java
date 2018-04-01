package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateful
public class CommandeServiceImpl implements ICommandeService{
// association uml en java
@EJB
ICommandeDao commandeDao;
	
	@Override
	public List<LigneCommande> getAllLignesCommandes(Commande commande) {
		return commandeDao.getAllLignesCommandes(commande);
	}

}
