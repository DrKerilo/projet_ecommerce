package fr.adaming.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="adminMB")
@RequestScoped
public class AdminManagedBean {
	
	// transformation de l'association uml en java
	@EJB
	private IAdminService adminService;
	
	@EJB
	private ICategorieService categorieService;
	
	// attributs du managedBean
	private Admin admin;
	private List<Categorie> listeCategories;
	
	// constructeur vide
	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// getters et setters
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}
	
	// Méthode du managedBean
	// Utilisation de règles de navigation car possibilité de connexion depuis plusieurs pages
	public String seConnecter(){
		try {
			Admin aOut = adminService.isExist(this.admin);
			listeCategories = categorieService.getAll();
			
			// ajouter dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", aOut);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCategories", listeCategories);
			return "espaceAdmin";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admin introuvable"));
		}
		return "accueil";
	}
	
	public String seDeconnecter(){
		// fermer la session Http courante
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "accueil";
	}
	
}
	
	
