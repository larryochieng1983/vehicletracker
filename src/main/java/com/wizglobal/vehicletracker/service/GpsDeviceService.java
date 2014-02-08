
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.GpsDevice;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GpsDeviceService extends DataAccessService<GpsDevice> implements Serializable {

    public GpsDeviceService() {
        super(GpsDevice.class);
    }

    
}
