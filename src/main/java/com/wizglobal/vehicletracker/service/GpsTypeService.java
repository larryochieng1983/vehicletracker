/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.GpsType;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class GpsTypeService extends DataAccessService<GpsType> {

	public GpsTypeService() {
		super( GpsType.class );
	}

}
