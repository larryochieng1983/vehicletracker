/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import java.io.Serializable;

import com.wizglobal.vehicletracker.domain.VoiceCall;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VoiceCallService extends DataAccessService<VoiceCall> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VoiceCallService() {
		super( VoiceCall.class );
	}
}
