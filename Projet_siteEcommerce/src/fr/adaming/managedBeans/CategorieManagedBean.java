package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.model.Categorie;
import fr.adaming.service.ICategorieService;

@ManagedBean(name = "categorieMB")
@RequestScoped
public class CategorieManagedBean implements Serializable {

	// Attributs
	private Categorie categorie;
	private List<Categorie> listeCategories;
	private boolean rendered; // pour le rendered de la vue
	private HttpSession session;
	private UploadedFile uf;

	// Transformation de l'association UML en Java
	@EJB
	ICategorieService categorieService;

	// Constructeur vide
	public CategorieManagedBean() {
		this.categorie = new Categorie();
		this.rendered = false;
		this.uf=new UploadedFileWrapper();
	}

	@PostConstruct
	public void init() {
		this.session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	public List<Categorie> getListeCategories() {
		return listeCategories;
	}
	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}
	public boolean isRendered() {
		return rendered;
	}
	public void setRendered(boolean rendered) {
		this.rendered = rendered;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	public UploadedFile getUf() {
		return uf;
	}
	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}
	

	// Méthodes métiers	

	public String consulterTout() {
		this.listeCategories = categorieService.getAll();
		this.session.setAttribute("listeCategories", listeCategories);
		return "categories";
	}

	public String ajouter() {
		// Ajouter la photo dans la catégorie
		categorie.setPhoto(uf.getContents());
		
		Categorie catAjout = categorieService.add(categorie);
		if (catAjout.getIdCategorie() != 0) {
			// Récupérer la liste des catégories mise à jour
			listeCategories = categorieService.getAll();
			this.session.setAttribute("listeCategories", listeCategories);
			return "espaceAdmin";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur dans l'ajout."));
			return "ajoutadmin";
		}
	}

	// Non utilisée : la modification se fait via le RowEdit
	public String modifier() {
		int verif = categorieService.update(categorie);
		if (verif != 0) {
			// Récupérer la liste des catégories mise à jour
			listeCategories = categorieService.getAll();
			return "testVal";
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie indiquée n'existe pas."));
			return "testVal";
		}
	}

	public void onRowEdit(RowEditEvent event) {
		categorieService.update((Categorie) event.getObject());
		listeCategories = categorieService.getAll();
		session.setAttribute("listeCategories", listeCategories);
		FacesMessage msg = new FacesMessage("Catégorie modifiée", ((Categorie) event.getObject()).getNomCategorie());
        FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Modification annulée", ((Categorie) event.getObject()).getNomCategorie());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void supprimer() {
		int verif = categorieService.delete(categorie);
		if (verif != 0) {
			// Récupérer la liste des catégories mise à jour
			listeCategories = categorieService.getAll();
			// L'ajouter à la session
			session.setAttribute("listeCategories", listeCategories);
		} else {
			// Si erreur
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie indiquée n'existe pas."));
		}
	}

	public String consulter() {
		try {
			this.categorie = categorieService.get(categorie);
			this.rendered = true;
		} catch (EJBTransactionRolledbackException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("La catégorie recherchée n'existe pas."));
			this.rendered = false;
		}
		return "testVal";
	}
}
