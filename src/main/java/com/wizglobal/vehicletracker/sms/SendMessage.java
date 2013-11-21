/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

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
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws SMSLibException
	 * @throws TimeoutException
	 */
	public void send( String receiver, String message ) throws TimeoutException, SMSLibException,
			IOException, InterruptedException {
		OutboundNotification outboundNotification = new OutboundNotification();
		SerialModemGateway gateway = new SerialModemGateway( "modem.com1", "COM4", 115200, "Huawei",
				"" );
		gateway.setInbound( true );
		gateway.setOutbound( true );
		gateway.setSimPin( "0000" );
		// Explicit SMSC address set is required for some modems.
		gateway.setSmscNumber( "+306942190000" );
		Service.getInstance().setOutboundMessageNotification( outboundNotification );
		Service.getInstance().addGateway( gateway );
		Service.getInstance().startService();
		// Send a message synchronously.
		OutboundMessage msg = new OutboundMessage( "306974000000", "Hello from SMSLib!" );
		Service.getInstance().sendMessage( msg );
		Service.getInstance().stopService();
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process( AGateway gateway, OutboundMessage msg ) {
		}
	}
}
