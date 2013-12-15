/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleType;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VehicleTypeService extends DataAccessService<VehicleType> implements Serializable {

	public VehicleTypeService() {
		super( VehicleType.class );
	}

}
