/**
 * 
 */
package com.wizglobal.vehicletracker.scheduler;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.wizglobal.vehicletracker.domain.OutgoingSms;
import com.wizglobal.vehicletracker.service.OutgoingSmsService;
import com.wizglobal.vehicletracker.sms.SendMessage;

/**
 * @author Otieno Lawrence
 * 
 */
public class OutgoingMessageObserver implements MessageObserver<OutgoingSms> {
	
	private static Logger log = Logger.getLogger( OutgoingMessageObserver.class );

	private SendMessage sendMessage;

	@Inject
	private OutgoingSmsService outgoingSmsService;

	public OutgoingMessageObserver( SendMessage sendMessage ) {
		this.sendMessage = sendMessage;
		this.sendMessage.attach( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wizglobal.vehicletracker.util.OutgoingMessageObserver#saveMessage()
	 */
	public void saveMessage(OutgoingSms outgoingSms) {
		outgoingSmsService.create( sendMessage.getOutgoingSms() );
	}

}
