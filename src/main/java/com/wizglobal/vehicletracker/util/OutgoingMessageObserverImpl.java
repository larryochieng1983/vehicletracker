/**
 * 
 */
package com.wizglobal.vehicletracker.util;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.wizglobal.vehicletracker.service.OutgoingSmsService;
import com.wizglobal.vehicletracker.sms.SendMessage;

/**
 * @author Otieno Lawrence
 * 
 */
public class OutgoingMessageObserverImpl extends OutgoingMessageObserver {
	
	private static Logger log = Logger.getLogger( OutgoingMessageObserverImpl.class );

	private SendMessage sendMessage;

	@Inject
	private OutgoingSmsService outgoingSmsService;

	public OutgoingMessageObserverImpl( SendMessage sendMessage ) {
		this.sendMessage = sendMessage;
		this.sendMessage.attach( this );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.wizglobal.vehicletracker.util.OutgoingMessageObserver#saveMessage()
	 */
	@Override
	public void saveMessage() {
		log.info( "Informing Guys!>>>>>>>>>" );
		outgoingSmsService.create( sendMessage.getOutgoingSms() );
	}

}
