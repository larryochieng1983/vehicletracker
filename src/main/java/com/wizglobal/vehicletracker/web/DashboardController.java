/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wizglobal.vehicletracker.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SlideEndEvent;

import com.wizglobal.vehicletracker.domain.User;
import com.wizglobal.vehicletracker.domain.Vehicle;
import com.wizglobal.vehicletracker.service.UserService;
import com.wizglobal.vehicletracker.service.VehicleService;

/**
 * Events at homepage are handled here mainly.
 * 
 * @author kenny
 */
@Named(value = "dashboardController")
@SessionScoped
public class DashboardController extends BasePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private VehicleService vehicleService;

	@Inject
	private UserService userService;

	private final int defaultReshRate = 2; // 2 seconds
	private int refreshRate;

	/** The vehicles owned by this user/customer */
	private List<Vehicle> vehicles;

	/** Currently logged in user */
	private User currentUser;

	/**
	 * Creates a new instance of DashboardController
	 */
	public DashboardController() {
	}

	@Override
	@PostConstruct
	public void init() {
		refreshRate = defaultReshRate;
		getCurrentUser();
		getVehicles();
	}

	public int getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate( int refreshRate ) {
		this.refreshRate = refreshRate;
	}

	public void slideEndEvent( SlideEndEvent slideEndEvent ) {
		setRefreshRate( slideEndEvent.getValue() );
	}

	/**
	 * @return the vehicles
	 */
	public List<Vehicle> getVehicles() {
		Map<String, Object> vehicleQueryParams = new HashMap<String, Object>();
		vehicleQueryParams.put( "user", currentUser );
		vehicles = vehicleService
				.findWithNamedQuery( "vehicle.findVehicleByUser", vehicleQueryParams );
		return vehicles;
	}

	/**
	 * @param vehicles the vehicles to set
	 */
	public void setVehicles( List<Vehicle> vehicles ) {
		this.vehicles = vehicles;
	}

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		Map<Object, Object> attr = getContext().getAttributes();
		String userName = (String) attr.get( "user" );
		Map<String, String> userQueryParams = new HashMap<String, String>();
		userQueryParams.put( "userName", userName );
		List<User> list = userService.findWithNamedQuery( "User.findByUserName", userQueryParams );
		if( !list.isEmpty() ) {
			currentUser = list.get( 0 );
		}
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser( User currentUser ) {
		this.currentUser = currentUser;
	}

	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

}
