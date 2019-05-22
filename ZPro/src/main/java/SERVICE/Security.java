package SERVICE;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@ManagedBean
@RequestScoped
public class Security {

	public String doLogin() throws ServletException, IOException {
		ExternalContext con= FacesContext.getCurrentInstance().getExternalContext();
		RequestDispatcher dis = ((ServletRequest)con.getRequest()).getRequestDispatcher("/j_spring_security_check");
		dis.forward(((ServletRequest)con.getRequest()), ((ServletResponse)con.getResponse()));
		FacesContext.getCurrentInstance().responseComplete();
		return null;
	}
	
	
}
