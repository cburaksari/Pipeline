package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import DTO.ShopUser;

@Repository
public class UserDAO {

	private ShopUser user;
	
	public List<ShopUser> findUserByUserName(String username) {
		List<ShopUser> list2 = new ArrayList<ShopUser>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("rawtypes")
			Query query = session.createQuery("from ShopUser where userName =: userName");
			query.setParameter("userName", username);
			list2 = query.list();

			
			session.getTransaction().commit();
			session.close();
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list2;
	}
	
}
