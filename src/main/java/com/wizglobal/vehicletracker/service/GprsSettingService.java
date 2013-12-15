/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.GprsSetting;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class GprsSettingService extends DataAccessService<GprsSetting> {

	public GprsSettingService() {
		super( GprsSetting.class );
	}

	public GprsSetting findGprsSettingByServiceProviderName( String serviceProviderName ) {
		GprsSetting gprsSetting = null;
		return gprsSetting;
	}
}
