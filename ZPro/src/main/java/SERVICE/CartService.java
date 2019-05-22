package SERVICE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import DTO.CartDetails;

@ManagedBean
@SessionScoped
public class CartService {

	
	List<CartDetails> list = new ArrayList<CartDetails>();
	
	private int quantity;
	
	private int product_id;
	
	private String productName;
	
	private double price;
	
	private double total;
	
	private int cartSize;
	
	private String item = "item";
	
	private String selectedZone;

	Map<Integer, CartDetails> map = new HashMap<Integer, CartDetails>();
	
	
	
	public String getSelectedZone() {
		return selectedZone;
	}

	public void setSelectedZone(String selectedZone) {
		this.selectedZone = selectedZone;
	}

	public String getItem() {
		if(cartSize>1)
			item="items";
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public int getCartSize() {
		return cartSize;
	}

	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Map<Integer, CartDetails> getMap() {
		return map;
	}

	public void setMap(Map<Integer, CartDetails> map) {
		this.map = map;
	}

	public List<CartDetails> getList() {
		return list;
	}

	public void setList(List<CartDetails> list) {
		this.list = list;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public String processCart(int qty) {
		CartDetails c = new CartDetails();
		quantity = qty;
		c.setProductName(productName);
		c.setQuantity(quantity);
		c.setPrice(price);
		c.setTotal(quantity*price);
		list.add(c);
		map.put(product_id, c);
		cartSize = list.size();
		
		if(cartSize>1) {
			item = "items";
		}
		
		return null;
	}
	
	
	
	
	
}
