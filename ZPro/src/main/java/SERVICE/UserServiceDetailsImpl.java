package SERVICE;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import DAO.*;
import DTO.*;

@Service
public class UserServiceDetailsImpl implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<ShopUser> user = userDAO.findUserByUserName(username);
		ShopUser user2 = user.get(0);
		
		if (user2 == null)
	        throw new UsernameNotFoundException("User not found: " + username);
		else {
			GrantedAuthority ga = new SimpleGrantedAuthority(user2.getUserRole().getRoleName());
			List<GrantedAuthority> list = new ArrayList<>();
			list.add(ga);
			
			return new org.springframework.security.core.userdetails.User(user2.getUserName(),user2.getUserPassword(),list);
		}
		
		
	}

}
