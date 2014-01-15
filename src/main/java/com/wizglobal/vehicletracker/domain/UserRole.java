/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Otieno Lawrence
 * 
 */
@Entity
@Table(name = "USER_ROLE")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQueries({ @NamedQuery(name = UserRole.ALL, query = "SELECT r FROM UserRole r") })
public class UserRole extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String ALL = "Role.populateRoles";

	@Column(name = "ROLE_DESC")
	@Size(max = 40)
	@NotNull
	private String roleDesc;

	@Column(name = "ROLE_NAME")
	@Size(max = 100)
	@NotNull
	private String roleName;

	/**
	 * @return the roleDesc
	 */
	public String getRoleDesc() {
		return roleDesc;
	}

	/**
	 * @param roleDesc the roleDesc to set
	 */
	public void setRoleDesc( String roleDesc ) {
		this.roleDesc = roleDesc;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName( String roleName ) {
		this.roleName = roleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserRole [roleDesc=" + roleDesc + ", roleName=" + roleName + "]";
	}

}
