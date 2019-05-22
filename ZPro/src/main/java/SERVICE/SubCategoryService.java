package SERVICE;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import DAO.AddDAO;
import DAO.ListDAO;
import DTO.Category;
import DTO.SubCategory;

@ManagedBean
@SessionScoped
public class SubCategoryService {
	
	private static int count = 1;

	@ManagedProperty(value="#{subCategory}")
	private SubCategory subCategory;
	
	@ManagedProperty(value="#{listDAO}")
	private ListDAO listDAO;
	
	@ManagedProperty(value = "#{addDAO}")
	private AddDAO addDAO;
	
	private String catName;
	
	private List<Category> list;
	
	
	public AddDAO getAddDAO() {
		return addDAO;
	}


	public void setAddDAO(AddDAO addDAO) {
		this.addDAO = addDAO;
	}


	public List<Category> getList() {
		return list;
	}


	public void setList(List<Category> list) {
		this.list = list;
	}


	public SubCategory getSubCategory() {
		return subCategory;
	}


	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}


	public ListDAO getListDAO() {
		return listDAO;
	}


	public void setListDAO(ListDAO listDAO) {
		this.listDAO = listDAO;
	}


	public String getCatName() {
		return catName;
	}


	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public String addSubCategory() {
		
		list = listDAO.catListByName(catName);
		subCategory.setSubCatid(count);
		subCategory.setCatName(subCategory.getCatName());
		subCategory.setCatDesc(subCategory.getCatDesc());
		subCategory.setCategory(list.get(0));
		
		boolean status = addDAO.addSubCategory(subCategory);
		
		if(status) {
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
	
	public List<SelectItem> getCategories(){
		List<SelectItem> items = listDAO.catList();
		return items;
	}
	
	
}
