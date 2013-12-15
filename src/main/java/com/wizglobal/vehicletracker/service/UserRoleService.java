/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.UserRole;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class UserRoleService extends DataAccessService<UserRole> implements Serializable {

	public UserRoleService() {
		super( UserRole.class );
	}
}
