/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import com.wizglobal.vehicletracker.service.DataAccessService;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleColorService extends DataAccessService<VehicleColor> {

	public VehicleColorService() {
		super( VehicleColor.class );
	}
}
