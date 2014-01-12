/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;

import com.wizglobal.vehicletracker.domain.OutgoingSms;

/**
 * @author Otieno Lawrence
 * 
 */
public class OutgoingSmsService extends DataAccessService<OutgoingSms> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutgoingSmsService() {
		super( OutgoingSms.class );
	}
}
