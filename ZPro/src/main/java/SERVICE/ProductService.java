package SERVICE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.UploadedFile;

import DAO.AddDAO;
import DAO.ListDAO;
import DTO.Category;
import DTO.Product;
import DTO.SubCategory;

@ManagedBean
@SessionScoped
public class ProductService {

	private static int count = 6;

	@ManagedProperty(value = "#{product}")
	private Product product;

	@ManagedProperty(value = "#{subCategory}")
	private SubCategory subCategory;
	
	@ManagedProperty(value="#{listDAO}")
	private ListDAO listDAO;
	
	@ManagedProperty(value = "#{addDAO}")
	private AddDAO addDAO;

	private String subCatName,catName;

	private List<SubCategory> list;
	
	private UploadedFile file;
	
	
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public AddDAO getAddDAO() {
		return addDAO;
	}

	public void setAddDAO(AddDAO addDAO) {
		this.addDAO = addDAO;
	}

	public ListDAO getListDAO() {
		return listDAO;
	}

	public void setListDAO(ListDAO listDAO) {
		this.listDAO = listDAO;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public List<SubCategory> getList() {
		return list;
	}

	public void setList(List<SubCategory> list) {
		this.list = list;
	}

	public String addProduct() {
        upload();
		list = listDAO.catSubListByName(subCatName);
		
		subCategory.setSubCatid(list.get(0).getSubCatid());
		product.setProid(count);
		product.setSubCategory(subCategory);
		product.setProName(product.getProName());
		product.setProQty(product.getProQty());
		product.setProPrice(product.getProPrice());
		product.setProUrl(product.getProUrl());
		product.setProDesc(product.getProDesc());

		boolean status = addDAO.addProduct(product);

		if (status) {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage mes = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved", "It saved");
			fc.addMessage(null, mes);
			count++;
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			FacesMessage mes = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Not saved");
			fc.addMessage(null, mes);
		}

		return null;
	}
	
	public void upload() {
		if(file != null) {
			try {
			FacesContext context = FacesContext.getCurrentInstance();
			ServletContext serv_con =(ServletContext)context.getExternalContext().getContext();
			
			String dbPath = serv_con.getRealPath("/");
			String webcut = dbPath.substring(0,dbPath.lastIndexOf("\\"));
			String buildCut = webcut.substring(0, webcut.lastIndexOf("\\"));
			String mainURL = buildCut.substring(0, buildCut.lastIndexOf("\\"));
			
			
			InputStream stream = file.getInputstream();
			String path = mainURL + "\\ZPro\\WebContent\\imgs\\" + file.getFileName();
			
			File destFile = new File(path);
			
			if(!destFile.exists()) {
				System.out.print("Im in!!!!!!!");
				FileUtils.copyInputStreamToFile(stream, destFile);
				}
			
			product.setProUrl(file.getFileName());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<SelectItem> getSubCategoryName(){
		List<SelectItem> items = listDAO.subCatList(catName);
		return items;
	}
	

}
