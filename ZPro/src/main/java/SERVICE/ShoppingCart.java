package SERVICE;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import DTO.Item;
import DTO.Product;

@ManagedBean
@SessionScoped
public class ShoppingCart {

	private List<Item> cart = new ArrayList<Item>();
	private double total;
	private int cartSize;
	private String item = "item";
	public List<Item> getCart() {
		return cart;
	}
	public void setCart(List<Item> cart) {
		this.cart = cart;
	}
	public double getTotal() {
		total = 0.0;
		for(Item item :cart) {
			total = total + (item.getQuantity() * item.getP().getProPrice());
		}
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getCartSize() {
		return cartSize;
	}
	public void setCartSize(int cartSize) {
		this.cartSize = cartSize;
	}
	public String getItem() {
		if(cartSize>1)
			item = "items";
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	public String addToCart(Product p) {
		if(cart.size()>0) {
			for (Item item : cart) {
				if(item.getP().getProid() == p.getProid()) {
					item.setQuantity(item.getQuantity()+1);
					return "cart";
				}
			}
			
		}
		
		Item i = new Item();
		i.setQuantity(1);
		i.setP(p);
		cart.add(i);
		return "cart";
	}
	
	
	public void UpdateCart() {
		
	}
	
	public void RemoveCart(Item i) {
		for (Item item : cart) {
			if(item.equals(i)) {
				cart.remove(i);
				break;
			}
		}
	}
	
}
