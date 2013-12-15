/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VehicleColor;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VehicleColorService extends DataAccessService<VehicleColor> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Dba dba;

	public VehicleColorService() {
	}
	
	

	public VehicleColorService(Dba dba) {
		this.dba = dba;
	}

}
