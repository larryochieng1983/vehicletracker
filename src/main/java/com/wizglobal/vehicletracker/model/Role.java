package com.wizglobal.vehicletracker.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * 
 * @author Otieno Lawrence
 */
@Entity
@NamedQueries({ @NamedQuery(name = Role.ALL, query = "SELECT r FROM Role r") })
public class Role extends BaseEntity implements Serializable {

	public final static String ALL = "Role.populateRoles";

	private String roledesc;
	private String rolename;

	public Role() {
	}

	public Role( Integer roleid, String rolename ) {
		this.rolename = rolename;
	}

	@ManyToMany(mappedBy = "roles")
	private List<User> users;

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers( List<User> users ) {
		this.users = users;
	}
}