package DAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;

import DTO.Category;
import DTO.Product;
import DTO.SubCategory;

@ManagedBean(name="addDAO")
@SessionScoped
public class AddDAO {

	public boolean addCategory(Category category) {
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(category);
		
		session.getTransaction().commit();
		session.close();}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean addSubCategory(SubCategory subCategory) {
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(subCategory);
		
		session.getTransaction().commit();
		session.close();}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean addProduct(Product product) {
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(product);
		
		session.getTransaction().commit();
		session.close();}
		catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
