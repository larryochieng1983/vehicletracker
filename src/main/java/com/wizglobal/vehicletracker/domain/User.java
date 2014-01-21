/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "APP_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.userName") })
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	@Enumerated
	private UserRole userRole;

	public User( String userName, String password, String firstName, String lastName,
			UserRole userRole ) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userRole = userRole;
	}

	/**
	 * 
	 */
	public User() {

	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName( String userName ) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword( String password ) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole( UserRole userRole ) {
		this.userRole = userRole;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
				+ "]";
	}

}
