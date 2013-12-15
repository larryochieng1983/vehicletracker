/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.SimCard;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class SimCardService extends DataAccessService<SimCard> {

	public SimCardService() {
		super( SimCard.class );
	}
}
