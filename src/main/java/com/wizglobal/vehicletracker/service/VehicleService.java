/**
 *
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.Vehicle;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 *
 */
@ApplicationScoped
public class VehicleService extends DataAccessService<Vehicle> implements Serializable {

    public VehicleService() {
	super(Vehicle.class);
    }
}
