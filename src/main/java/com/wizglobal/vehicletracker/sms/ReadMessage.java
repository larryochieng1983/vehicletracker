/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.AGateway.Protocols;
import org.smslib.GatewayException;
import org.smslib.ICallNotification;
import org.smslib.IGatewayStatusNotification;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.InboundMessage.MessageClasses;
import org.smslib.Message.MessageTypes;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.crypto.AESKey;
import org.smslib.modem.SerialModemGateway;

/**
 * @author Otieno Lawrence
 * 
 */
public class ReadMessage {

	private static Logger log = Logger.getLogger( ReadMessage.class );

	public void receive() throws TimeoutException, GatewayException, SMSLibException, IOException,
			InterruptedException {
		// Define a list which will hold the read messages.
		List<InboundMessage> msgList;
		// Create the notification callback method for inbound & status report
		// messages.
		InboundNotification inboundNotification = new InboundNotification();
		// Create the notification callback method for inbound voice calls.
		CallNotification callNotification = new CallNotification();
		// Create the notification callback method for gateway statuses.
		GatewayStatusNotification statusNotification = new GatewayStatusNotification();
		OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
		try {
			// Create the Gateway representing the serial GSM modem.
			SerialModemGateway gateway = new SerialModemGateway( "modem.com4", "COM4", 115200,
					"Huawei", "E160" );
			// Set the modem protocol to PDU (alternative is TEXT). PDU is the default, anyway...
			gateway.setProtocol( Protocols.PDU );
			// Do we want the Gateway to be used for Inbound messages?
			gateway.setInbound( true );
			// Do we want the Gateway to be used for Outbound messages?
			gateway.setOutbound( true );
			// Let SMSLib know which is the SIM PIN.
			gateway.setSimPin( "0000" );
			// Set up the notification methods.
			Service.getInstance().setInboundMessageNotification( inboundNotification );
			Service.getInstance().setCallNotification( callNotification );
			Service.getInstance().setGatewayStatusNotification( statusNotification );
			Service.getInstance().setOrphanedMessageNotification( orphanedMessageNotification );
			// Add the Gateway to the Service object.
			Service.getInstance().addGateway( gateway );
			// Similarly, you may define as many Gateway objects, representing
			// various GSM modems, add them in the Service object and control all of them.
			// Start! (i.e. connect to all defined Gateways)
			Service.getInstance().startService();

			// In case you work with encrypted messages, its a good time to declare your keys.
			// Create a new AES Key with a known key value.
			// Register it in KeyManager in order to keep it active. SMSLib will then automatically
			// encrypt / decrypt all messages send to / received from this number.
			Service
					.getInstance()
					.getKeyManager()
					.registerKey( "+306948494037",
							new AESKey( new SecretKeySpec( "0011223344556677".getBytes(), "AES" ) ) );
			// Read Messages. The reading is done via the Service object and
			// affects all Gateway objects defined. This can also be more directed to a specific
			// Gateway - look the JavaDocs for information on the Service method calls.
			msgList = new ArrayList<InboundMessage>();
			Service.getInstance().readMessages( msgList, MessageClasses.ALL );
			for( InboundMessage msg : msgList ) {

			}
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			Service.getInstance().stopService();
		}
	}

	public class InboundNotification implements IInboundMessageNotification {
		public void process( AGateway gateway, MessageTypes msgType, InboundMessage msg ) {
			if( msgType == MessageTypes.INBOUND ) {

			} else if( msgType == MessageTypes.STATUSREPORT ) {

			}

		}
	}

	public class CallNotification implements ICallNotification {
		public void process( AGateway gateway, String callerId ) {

		}
	}

	public class GatewayStatusNotification implements IGatewayStatusNotification {
		public void process( AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus ) {

		}
	}

	public class OrphanedMessageNotification implements IOrphanedMessageNotification {
		public boolean process( AGateway gateway, InboundMessage msg ) {
			return false;
		}
	}
}
