/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleManufacturer;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleManufacturerService extends DataAccessService<VehicleManufacturer> implements Serializable {

	public VehicleManufacturerService() {
		super( VehicleManufacturer.class );
	}

}
