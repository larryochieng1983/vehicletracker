/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity(name = "App_User")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3571343460175211199L;

	public final static String ALL = "User.populateUsers";
	public final static String TOTAL = "User.countUsersTotal";

	@Column(nullable = false, length = 50)
	private String userName;

	@Column(length = 64)
	private String password;

	@Column(length = 50)
	private String firstName;

	@Column(length = 50)
	private String lastName;
	@ManyToMany
	@JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "User_userid") }, inverseJoinColumns = { @JoinColumn(name = "UserRole_roleid") })
	private List<UserRole> userRoles = new ArrayList<UserRole>();;

	public User( String userName, String password, String firstName, String lastName,
			List<UserRole> userRoles ) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRoles = userRoles;
	}

	public User() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
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

	/**
	 * @return the userRoles
	 */
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles( List<UserRole> userRoles ) {
		this.userRoles = userRoles;
	}

}
