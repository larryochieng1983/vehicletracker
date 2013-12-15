/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehiclePosition;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VehiclePositionService extends DataAccessService<VehiclePosition> implements Serializable {

	public VehiclePositionService() {
		super( VehiclePosition.class );
	}

}
