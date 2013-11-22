/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.Vehicle;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleService extends DataAccessService<Vehicle> {

	public VehicleService() {
		super( Vehicle.class );
	}
}
