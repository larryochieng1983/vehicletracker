/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 3571343460175211199L;

	private String login;
	private String password;
	private String firstName;
	private String lastName;

	public User( String login, String password, String firstName, String lastName ) {
		super();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin( String login ) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

}
