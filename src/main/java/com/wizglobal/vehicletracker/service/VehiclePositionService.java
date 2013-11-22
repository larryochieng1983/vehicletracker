/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehiclePosition;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehiclePositionService extends DataAccessService<VehiclePosition> {

	public VehiclePositionService() {
		super( VehiclePosition.class );
	}

}
