/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

import com.wizglobal.vehicletracker.domain.OutgoingSms;
import com.wizglobal.vehicletracker.util.OutgoingMessageObserver;

/**
 * @author Otieno Lawrence
 * 
 */
public class SendMessage {

	private static Logger log = Logger.getLogger( SendMessage.class );

	/** SMS Gateway Properties */
	private Properties gatewayProperties = new Properties();

	private OutgoingSms outgoingSms;
	private List<OutgoingMessageObserver> observers = new ArrayList<OutgoingMessageObserver>();

	/**
	 * 
	 */
	public SendMessage() {
		try {
			gatewayProperties.load( new FileInputStream( "smslib/SMSServer.conf" ) );
		} catch( IOException e ) {
			log.error( "Failed To Load SMS Server Settings" );
			throw new IllegalStateException( "Failed To Load SMS Server Settings" );
		}
	}

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
		boolean sent;
		OutboundNotification outboundNotification = new OutboundNotification();
		SerialModemGateway gateway = new SerialModemGateway(
				gatewayProperties.getProperty( "gateway.0" ),
				gatewayProperties.getProperty( "modem1.port" ), Integer.parseInt( gatewayProperties
						.getProperty( "modem1.baudrate" ) ),
				gatewayProperties.getProperty( "modem1.manufacturer" ),
				gatewayProperties.getProperty( "modem1.model" ) );
		gateway.setInbound( true );
		gateway.setOutbound( true );
		gateway.setSimPin( gatewayProperties.getProperty( "modem1.pin" ) );
		gateway.setSmscNumber( gatewayProperties.getProperty( "modem1.smsc_number" ) );
		Service.getInstance().setOutboundMessageNotification( outboundNotification );
		Service.getInstance().addGateway( gateway );
		Service.getInstance().startService();
		// Send a message synchronously.
		OutboundMessage msg = new OutboundMessage( receiver, message );
		msg.setStatusReport( true );
		if( Service.getInstance().sendMessage( msg ) ) {
			outgoingSms = new OutgoingSms( Message.MessageTypes.OUTBOUND, msg.getRecipient(),
					msg.getText(), msg.getDate() );
			sent = true;
		} else {
			sent = false;
		}
		Service.getInstance().stopService();
		return sent;
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process( AGateway gateway, OutboundMessage msg ) {
			log.info( "Outbound handler called from Gateway: " + gateway.getGatewayId() );
		}
	}

	/**
	 * @return the outgoingSms
	 */
	public OutgoingSms getOutgoingSms() {
		return outgoingSms;
	}

	/**
	 * @param outgoingSms the outgoingSms to set
	 */
	public void setOutgoingSms( OutgoingSms outgoingSms ) {
		this.outgoingSms = outgoingSms;
	}

	public void attach( OutgoingMessageObserver observer ) {
		observers.add( observer );
	}

	public void notifyAllObservers() {
		for( OutgoingMessageObserver observer : observers ) {
			observer.saveMessage();
		}
	}

}
