/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

import com.wizglobal.vehicletracker.domain.OutgoingSms;
import com.wizglobal.vehicletracker.service.OutgoingSmsService;

/**
 * @author Otieno Lawrence
 * 
 */
public class OutboundNotification implements IOutboundMessageNotification {

	private static Logger log = Logger.getLogger( SendMessage.class );
	
	@Inject
	private OutgoingSmsService outgoingSmsService;

	public void process( AGateway gateway, OutboundMessage msg ) {
		log.info( "Outbound handler called from Gateway: " + gateway.getGatewayId() );
		OutgoingSms outgoingSms = new OutgoingSms();
		outgoingSms.setCreateDate( msg.getDate() );
		outgoingSms.setRecipient( msg.getRecipient() );
		outgoingSms.setText( msg.getText() );
		outgoingSmsService.create( outgoingSms );
	}

}
