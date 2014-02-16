/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;

/**
 * @author Otieno Lawrence
 * 
 */
public class SendMessage {

	private static Logger log = Logger.getLogger( SendMessage.class );

	/**
	 * 
	 * @param receiver
	 * @param message
	 * @return sent or not
	 * @throws TimeoutException
	 * @throws SMSLibException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean send( String receiver, String message ) throws TimeoutException, SMSLibException,
			IOException, InterruptedException {
		log.info( "Sending message to: " + receiver );
		// Send a message synchronously.
		OutboundMessage msg = new OutboundMessage( receiver, message );
		msg.setStatusReport( true );
		return GatewayService.getGatewayService().getService().sendMessage( msg );
	}

}
