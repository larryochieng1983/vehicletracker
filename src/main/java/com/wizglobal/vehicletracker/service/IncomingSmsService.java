/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class IncomingSmsService extends DataAccessService<IncomingSms> implements Serializable {

	public IncomingSmsService() {
		super( IncomingSms.class );
	}
}
