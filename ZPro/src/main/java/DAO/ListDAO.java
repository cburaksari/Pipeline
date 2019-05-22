package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Session;
import org.hibernate.query.Query;

import DTO.Category;
import DTO.Product;
import DTO.SubCategory;

@ManagedBean(name = "listDAO")
@SessionScoped
public class ListDAO {

	private List<Category> list;
	
	private List<SubCategory> list3;
	
	private List<SubCategory> list4;
	
	public List allProductList() {
		List<Product> list2 = new ArrayList<Product>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from Product");
			list2 = query.list();

			
			session.getTransaction().commit();
			session.close();
		}

		catch (Exception e) {
			return null;
		}

		return list2;
	}
	

	public List catList() {
		List<String> list2 = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from Category");
			list = query.list();

			for (Category category : list) {
				list2.add(category.getCatName());
			}
			
			session.getTransaction().commit();
			session.close();
		}

		catch (Exception e) {
			return null;
		}

		return list2;
	}

	public List<Category> catListByName(String name) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from Category where catName =:catName");
			query.setParameter("catName", name);
			list = query.list();

			session.getTransaction().commit();
			session.close();
		}

		catch (Exception e) {
			return null;
		}

		return list;
	}
	
	public List<SubCategory> catSubListByName(String name) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from SubCategory where catName =:catName");
			query.setParameter("catName", name);
			list3 = query.list();

			session.getTransaction().commit();
			session.close();
		}

		catch (Exception e) {
			return null;
		}

		return list3;
	}

	public List subCatList(String name) {
		List<String> list2 = new ArrayList<String>();
		List<SubCategory> list5 = new ArrayList<SubCategory>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from Category where catName =:catName");
			query.setParameter("catName", name);
			list = query.list();
			
			list4 = subCatList();
			
			for (Category category : list) {
				for (SubCategory subcategory : list4) {
					if(subcategory.getCategory().getCatid() == category.getCatid())
						list5.add(subcategory);
				}
				
			}
			
			for (SubCategory subCategory : list5) {
				list2.add(subCategory.getCatName());
			}
			
			session.getTransaction().commit();
			session.close();
		}

		catch (Exception e) {
			return null;
		}

		return list2;
	}
	
	public List subCatList() {
		List<String> list5 = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from SubCategory");
			list5 = query.list();
			
			session.getTransaction().commit();
			session.close();
		}

		catch (Exception e) {
			return null;
		}

		return list5;
	}
	
}
