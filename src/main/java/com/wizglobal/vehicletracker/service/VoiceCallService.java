/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VoiceCall;
import javax.enterprise.context.ApplicationScoped;

/**
 * @author Otieno Lawrence
 * 
 */
@ApplicationScoped
public class VoiceCallService extends DataAccessService<VoiceCall> {

	public VoiceCallService() {
		super( VoiceCall.class );
	}
}
