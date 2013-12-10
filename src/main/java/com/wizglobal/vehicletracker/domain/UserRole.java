/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = UserRole.ALL, query = "SELECT r FROM UserRole r") })
public class UserRole extends BaseEntity implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String ALL = "Role.populateRoles";
	 
	private String roledesc;
	private String rolename;

	public UserRole() {
	}

	public UserRole( Integer roleid, String rolename ) {
		this.rolename = rolename;
	}
	
	public String getRoledesc() {
		return this.roledesc;
	}

	public void setRoledesc( String roledesc ) {
		this.roledesc = roledesc;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename( String rolename ) {
		this.rolename = rolename;
	}	
}
