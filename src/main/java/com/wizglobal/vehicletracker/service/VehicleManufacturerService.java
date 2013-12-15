/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleManufacturer;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VehicleManufacturerService extends DataAccessService<VehicleManufacturer> implements Serializable {

	public VehicleManufacturerService() {
		super( VehicleManufacturer.class );
	}

}
