/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class IncomingSmsService extends DataAccessService<IncomingSms> {

	public IncomingSmsService() {
		super( IncomingSms.class );
	}
}
