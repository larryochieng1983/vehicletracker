/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class GpsDeviceService extends DataAccessService<GpsDevice> {

	public GpsDeviceService() {
		super( GpsDevice.class );
	}
}
