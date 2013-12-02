/**
 * 
 */
package com.wizglobal.vehicletracker.domain;

import com.wizglobal.vehicletracker.service.DataAccessService;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleColorService extends DataAccessService<VehicleColor> implements Serializable {

	public VehicleColorService() {
		super( VehicleColor.class );
	}
}
