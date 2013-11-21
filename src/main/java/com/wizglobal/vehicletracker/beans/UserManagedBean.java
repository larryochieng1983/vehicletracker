/**
 * 
 */
package com.wizglobal.vehicletracker.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.wizglobal.vehicletracker.domain.User;

/**
 * @author Otieno Lawrence
 * 
 */
@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 152717293232892353L;
	private static final String HOME_PAGE = "home?faces-redirect=true";
	private User user;
	private String userLogin;
	private String password;

	public String login() {
		if( userLogin != null && password != null ) {
			return someLoginMethod();
		}
		return "login?faces-redirect=true";
	}

	private String someLoginMethod() {
		if( userLogin.equals( "tom" ) && password.equals( "tom" ) ) {
			user = new User( userLogin, password, "Tom", "Hanks" );
			return HOME_PAGE;
		} else if( userLogin.equals( "forrest" ) && password.equals( "forrest" ) ) {
			user = new User( userLogin, password, "Forrest", "Gump" );
			return HOME_PAGE;
		}
		return "login?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser( User user ) {
		this.user = user;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin( String userLogin ) {
		this.userLogin = userLogin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}
}
