/**
 * 
 */
package com.wizglobal.vehicletracker.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import com.wizglobal.vehicletracker.service.VehiclePositionService;
import com.wizglobal.vehicletracker.service.VehicleService;

/**
 * @author Otieno Lawrence
 * 
 */
@ManagedBean(name = "vehiclePositionBean")
@SessionScoped
public class VehiclePositionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient Logger logger;

	@Inject
	private VehiclePositionService vehiclePositionService;
	
	@Inject
	private VehicleService vehicleService;

	public VehiclePositionBean() {

	}

}
