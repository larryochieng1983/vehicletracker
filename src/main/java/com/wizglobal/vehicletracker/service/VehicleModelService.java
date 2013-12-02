/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleModel;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleModelService extends DataAccessService<VehicleModel> implements Serializable {

	public VehicleModelService() {
		super( VehicleModel.class );
	}

}
