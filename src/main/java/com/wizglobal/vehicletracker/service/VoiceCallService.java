/**
 * 
 */
package com.wizglobal.vehicletracker.service;

import com.wizglobal.vehicletracker.domain.VoiceCall;
import java.io.Serializable;

/**
 * @author Otieno Lawrence
 * 
 */
public class VoiceCallService extends DataAccessService<VoiceCall> implements Serializable {

	public VoiceCallService() {
		super( VoiceCall.class );
	}
}
