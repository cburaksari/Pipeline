package SERVICE;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import DAO.*;
import DTO.*;

@ManagedBean
@SessionScoped
public class CategoryService {
	
	private static int count = 1;

	@ManagedProperty(value = "#{category}")
	private Category category;
	
	@ManagedProperty(value = "#{addDAO}")
	private AddDAO addDAO;
	
	public AddDAO getAddDAO() {
		return addDAO;
	}

	public void setAddDAO(AddDAO addDAO) {
		this.addDAO = addDAO;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public String addCategory() {
		category.setCatid(count);
		category.setCatName(category.getCatName());
		category.setCatDesc(category.getCatDesc());
		
		boolean bo = addDAO.addCategory(category);
		
		if(bo) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage mes = new FacesMessage(FacesMessage.SEVERITY_INFO,"Saved","It saved");
			fc.addMessage(null, mes);
			count++;
		}
		else {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage mes = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Not saved");
			fc.addMessage(null, mes);
		}
		
		return null;
	}
	
	
}
