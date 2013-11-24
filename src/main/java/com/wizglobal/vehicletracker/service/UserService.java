/**
 *
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.User;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 *
 */
@ApplicationScoped
public class UserService extends DataAccessService<User> implements Serializable {

    public UserService() {
	super(User.class);
    }
}
