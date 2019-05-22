package SERVICE;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import DAO.ListDAO;
import DTO.Product;

@ManagedBean
@SessionScoped
public class DisplayProduct {

	@ManagedProperty(value = "#{product}")
	private Product product;
	
	@ManagedProperty(value = "#{product}")
	private Product selectedProduct;

	@ManagedProperty(value = "#{listDAO}")
	private ListDAO listDAO;
	
	

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
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

	public List<Product> getAllProducts() {
		List<Product> list = listDAO.allProductList();
		return list;
	}
}
