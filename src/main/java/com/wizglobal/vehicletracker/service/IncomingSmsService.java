/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.IncomingSms;

/**
 * @author Otieno Lawrence
 * 
 */
public class IncomingSmsService extends DataAccessService<IncomingSms> {

	public IncomingSmsService() {
		super( IncomingSms.class );
	}
}
