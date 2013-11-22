/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleType;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleTypeService extends DataAccessService<VehicleType> {

	public VehicleTypeService() {
		super( VehicleType.class );
	}

}
