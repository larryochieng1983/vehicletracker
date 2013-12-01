/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleType;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleTypeService extends DataAccessService<VehicleType> implements Serializable {

	public VehicleTypeService() {
		super( VehicleType.class );
	}

}
