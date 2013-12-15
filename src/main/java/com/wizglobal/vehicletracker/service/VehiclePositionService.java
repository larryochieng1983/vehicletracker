/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehiclePosition;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehiclePositionService extends DataAccessService<VehiclePosition> implements Serializable {

	public VehiclePositionService() {
		super( VehiclePosition.class );
	}

}
