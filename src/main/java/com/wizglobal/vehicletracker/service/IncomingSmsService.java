/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class IncomingSmsService extends DataAccessService<IncomingSms> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncomingSmsService() {
		super( IncomingSms.class );
	}
}
