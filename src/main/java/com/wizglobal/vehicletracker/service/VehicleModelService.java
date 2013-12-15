/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleModel;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VehicleModelService extends DataAccessService<VehicleModel> implements Serializable {

	public VehicleModelService() {
		super( VehicleModel.class );
	}

}
