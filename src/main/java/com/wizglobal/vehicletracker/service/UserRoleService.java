/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.UserRole;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class UserRoleService extends DataAccessService<UserRole> implements Serializable {

	public UserRoleService() {
		super( UserRole.class );
	}
}
