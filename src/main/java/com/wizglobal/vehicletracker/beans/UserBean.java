/**
 *
 */
package com.wizglobal.vehicletracker.beans;

import com.wizglobal.vehicletracker.domain.User;
import com.wizglobal.vehicletracker.domain.UserRole;
import com.wizglobal.vehicletracker.exception.DataAccessException;
import com.wizglobal.vehicletracker.service.UserService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.management.relation.Role;
import org.primefaces.model.LazyDataModel;

/**
 * @author Otieno Lawrence
 * 
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private   transient Logger logger;
    @Inject
    private  UserService das;
    // Selected users that will be removed 
    private User[] selectedUsers;
    // Lazy loading user list
    private LazyDataModel<User> lazyModel;
    // Creating new user
    private User newUser;
    // Selected user that will be updated
    private User selectedUser;
    // Available role list
    private List<Role> roleList;

    /**
     * Default constructor
     */
    public UserBean() {
    }

    /**
     * Initializing Data Access Service for LazyUserDataModel class role list
     * for UserContoller class
     */
    @PostConstruct
    public void init() {
	logger.log(Level.INFO, "UserController is initializing");
	lazyModel = new LazyUserDataModel(das);
	roleList = das.findWithNamedQuery(UserRole.ALL);
    }

    /**
     * Create, Update and Delete operations
     */
    public void doCreateUser() {
	try {
	    das.create(newUser);
	} catch (Exception ex) {
	    Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     *
     * @param actionEvent
     */
    public void doUpdateUser(ActionEvent actionEvent) {
	das.update(selectedUser);
    }

    /**
     *
     * @param actionEvent
     */
    public void doDeleteUsers(ActionEvent actionEvent) {
	das.deleteItems(selectedUsers);
    }

    /**
     * Getters, Setters
     *
     * @return
     */
    public User getSelectedUser() {
	return selectedUser;
    }

    /**
     *
     * @param selectedUser
     */
    public void setSelectedUser(User selectedUser) {
	this.selectedUser = selectedUser;
    }

    /**
     *
     * @return
     */
    public User[] getSelectedUsers() {
	return selectedUsers;
    }

    /**
     *
     * @param selectedUsers
     */
    public void setSelectedUsers(User[] selectedUsers) {
	this.selectedUsers = selectedUsers;
    }

    /**
     *
     * @return
     */
    public User getNewUser() {
	return newUser;
    }

    /**
     *
     * @param newUser
     */
    public void setNewUser(User newUser) {
	this.newUser = newUser;
    }

    /**
     *
     * @return LazyDataModel
     */
    public LazyDataModel<User> getLazyModel() {
	return lazyModel;
    }

    /**
     *
     * @return List<Role>
     */
    public List<Role> getRoleList() {
	return roleList;
    }

    /**
     *
     */
    public void setRoleList(List<Role> roleList) {
	this.roleList = roleList;
    }

}
