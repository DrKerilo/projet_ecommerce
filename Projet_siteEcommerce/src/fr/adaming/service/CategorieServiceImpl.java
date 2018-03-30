package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService {
	
	// Transformation de l'association UML en Java
	@EJB
	private ICategorieDao categorieDao;

	@Override
	public List<Categorie> getAll() {
		return categorieDao.getAll();
	}

	@Override
	public Categorie add(Categorie c) {
		return categorieDao.add(c);
	}

	@Override
	public int update(Categorie c) {
		return categorieDao.update(c);
	}

	@Override
	public int delete(Categorie c) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Categorie get(Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

}
