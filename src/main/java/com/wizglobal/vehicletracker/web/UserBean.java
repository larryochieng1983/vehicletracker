/**
 *
 */
package com.wizglobal.vehicletracker.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.wizglobal.vehicletracker.domain.User;
import com.wizglobal.vehicletracker.domain.UserRole;
import com.wizglobal.vehicletracker.exception.DataAccessException;
import com.wizglobal.vehicletracker.service.UserService;

/**
 * @author Otieno Lawrence
 * 
 */
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean extends BasePage implements Serializable {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger( UserBean.class );

	@Inject
	private UserService userService;

	// Lazy loading user list
	private LazyUsersDataModel lazyUsersDataModel;

	// Creating new user
	private User newUser;

	// Selected user that will be updated
	private User currentUser;

	// Available role list
	private List<String> roleList;

	// Confirm password
	private String confirmPassword;

	/**
	 * Default constructor
	 */
	public UserBean() {
	}

	/**
	 * Initializing Data Access Service for LazyUserDataModel class role list for UserBean class
	 */
	@PostConstruct
	@Override
	public void init() {
		logger.info( "UserBean is initializing" );
		lazyUsersDataModel = new LazyUsersDataModel( userService );
		roleList = new ArrayList<>();
		for( UserRole userRole : UserRole.values() ) {
			roleList.add( userRole.name() );
		}
	}

	/**
	 * Create, Update and Delete operations
	 */
	public void doCreateUser() {
		userService.create( newUser );
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void doUpdateUser( ActionEvent actionEvent ) {
		try {
			userService.update( currentUser );
		} catch( DataAccessException ex ) {
			addErrorgMessage( "User not update due to a data access problem. Please try again.", null );
		}
	}

	/**
	 * Sets a new instance for {@link #newUser} to be used as new user.
	 * 
	 * 
	 */
	public void preRenderNewUser() {
		if( !isAjaxRequest() && newUser == null ) {
			newUser = new User();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String showUserList() {
		// refresh customer list first.
		return appendFacesRedirectTrue( "/users/list.jsf" );
	}

	/**
	 * 
	 * @return
	 */
	public String createNewUser() {
		newUser = null;
		return appendFacesRedirectTrue( "/users/new.jsf" );
	}

	/**
	 * Getters, Setters
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * 
	 * @param currentUser
	 */
	public void setCurrentUser( User currentUser ) {
		this.currentUser = currentUser;
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
	public void setNewUser( User newUser ) {
		this.newUser = newUser;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword( String confirmPassword ) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * 
	 * @return List<Role>
	 */
	public List<String> getRoleList() {
		return roleList;
	}

	/**
     *
     */
	public void setRoleList( List<String> roleList ) {
		this.roleList = roleList;
	}

	/**
	 * 
	 * @return Lazy loaded Model for users.
	 */
	public LazyDataModel<User> getLazyUsersDataModel() {
		if( lazyUsersDataModel == null ) {
			lazyUsersDataModel = new LazyUsersDataModel( userService );
		}
		return lazyUsersDataModel;
	}

	/**
	 * 
	 * @return
	 */
	public String selectAndDeletCurrentUser() {
		setCurrentUser( getLazyUsersDataModel().getRowData() );
		return deleteCurrentUser();
	}

	/**
	 * 
	 * @return
	 */
	public String deleteCurrentUser() {
		try {
			userService.delete( currentUser );
			addInfoMessage( "User " + currentUser.getFirstName() + " " + currentUser.getLastName()
					+ " removed.", null );
			return showUserList();
		} catch( Exception e ) {
			addErrorgMessage( "Failed to delete user. " + e.getMessage(), null );
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String selectAndViewCurrentUser() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String saveCurrentUserChanges() {
		if( currentUser == null ) {
			addWarningMessage( "Please Select a user to edit and try again.",
					"No User Selected for update" );
		} else {
			try {
				userService.update( currentUser );
				addInfoMessage( "User updated", null );
			} catch( DataAccessException ex ) {
				addErrorgMessage( "User changes not save due to data access error. Please try again.",
						null );
			}
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String editUser() {
		return currentUser == null ? null : appendFacesRedirectTrue( "/users/edit.jsf" );
	}

	/**
	 * 
	 * @param actionEvent
	 */
	public void comparePasswords( ActionEvent actionEvent ) {

	}

	/**
	 * 
	 * @author Otieno Lawrence
	 * 
	 */
	public static class LazyUsersDataModel extends LazyDataModel<User> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private UserService userDataSource;

		public LazyUsersDataModel( UserService userDataSource ) {
			this.userDataSource = userDataSource;
		}

		@Override
		public Object getRowKey( User user ) {
			return user.getId();
		}

		@Override
		public void setRowIndex( int rowIndex ) {
			if( rowIndex == -1 || getPageSize() == 0 ) {
				super.setRowIndex( -1 );
			} else {
				super.setRowIndex( rowIndex % getPageSize() );
			}
		}

		@Override
		public List<User> load( int first, int pageSize, String sortField, SortOrder sortOrder,
				Map<String, String> filters ) {
			List<User> results = userDataSource.findWithNamedQuery( "User.findAll" );
			setRowCount( results.size() );
			// setWrappedData(results);
			// setPageSize(pageSize);
			super.setPageSize( pageSize );
			if( results.size() > pageSize ) {
				try {
					return results.subList( first, first + pageSize );
				} catch( Exception e ) {
					return results.subList( first, (first + (results.size() % pageSize)) );
				}
			}
			return results;
		}

	}

}
