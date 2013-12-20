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
import org.smslib.modem.SerialModemGateway;

import com.wizglobal.vehicletracker.domain.IncomingSms;
import com.wizglobal.vehicletracker.service.IncomingSmsService;

/**
 * @author Otieno Lawrence
 * 
 */
public class ReadMessage {

	private static Logger log = Logger.getLogger( ReadMessage.class );

	/** SMS Gateway Properties */
	private Properties gatewayProperties = new Properties();
	private IncomingSmsService incomingSmsService;

	public ReadMessage() {
		try {
			gatewayProperties.load( new FileInputStream( "smslib/SMSServer.conf" ) );
		} catch( IOException e ) {
			log.error( "Failed To Load SMS Server Settings" );
			throw new IllegalStateException( "Failed To Load SMS Server Settings" );
		}
	}

	public void receive() throws TimeoutException, GatewayException, SMSLibException, IOException,
			InterruptedException {
		List<InboundMessage> msgList;
		InboundNotification inboundNotification = new InboundNotification();
		CallNotification callNotification = new CallNotification();
		GatewayStatusNotification statusNotification = new GatewayStatusNotification();
		OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();
		try {
			SerialModemGateway gateway = new SerialModemGateway(
					gatewayProperties.getProperty( "gateway.0" ),
					gatewayProperties.getProperty( "modem1.port" ), Integer.parseInt( gatewayProperties
							.getProperty( "modem1.baudrate" ) ),
					gatewayProperties.getProperty( "modem1.manufacturer" ),
					gatewayProperties.getProperty( "modem1.model" ) );
			gateway.setProtocol( Protocols.PDU );
			gateway.setInbound( true );
			gateway.setOutbound( true );
			gateway.setSimPin( gatewayProperties.getProperty( "modem1.pin" ) );
			Service.getInstance().setInboundMessageNotification( inboundNotification );
			Service.getInstance().setCallNotification( callNotification );
			Service.getInstance().setGatewayStatusNotification( statusNotification );
			Service.getInstance().setOrphanedMessageNotification( orphanedMessageNotification );
			Service.getInstance().addGateway( gateway );
			Service.getInstance().startService();
			msgList = new ArrayList<InboundMessage>();
			List<IncomingSms> smsList = new ArrayList<IncomingSms>();
			Service.getInstance().readMessages( msgList, MessageClasses.ALL );
			for( InboundMessage msg : msgList ) {
				IncomingSms sms = new IncomingSms( msg.getType(), msg.getOriginator(), msg.getDate(),
						msg.getDate(), msg.getText() );
				smsList.add( sms );
				// Delete after reading
				Service.getInstance().deleteMessage( msg );
			}
			incomingSmsService.create( smsList );

		} catch( Exception e ) {
			log.error( e );
		} finally {
			Service.getInstance().stopService();
		}
	}

	public class InboundNotification implements IInboundMessageNotification {
		public void process( AGateway gateway, MessageTypes msgType, InboundMessage msg ) {
			if( msgType == MessageTypes.INBOUND ) {
				log.info( ">>> New Inbound message detected from Gateway: " + gateway.getGatewayId() );
			} else if( msgType == MessageTypes.STATUSREPORT ) {
				log.info( ">>> New Inbound Status Report message detected from Gateway: "
						+ gateway.getGatewayId() );
				log.info( msg );
			}

		}
	}

	public class CallNotification implements ICallNotification {
		public void process( AGateway gateway, String callerId ) {
			log.info( ">>> New call detected from Gateway: " + gateway.getGatewayId() + " : "
					+ callerId );
		}
	}

	public class GatewayStatusNotification implements IGatewayStatusNotification {
		public void process( AGateway gateway, GatewayStatuses oldStatus, GatewayStatuses newStatus ) {
			log.info( ">>> Gateway Status change for " + gateway.getGatewayId() + ", OLD: "
					+ oldStatus + " -> NEW: " + newStatus );
		}
	}

	public class OrphanedMessageNotification implements IOrphanedMessageNotification {
		public boolean process( AGateway gateway, InboundMessage msg ) {
			log.info( ">>> Orphaned message part detected from: " + gateway.getGatewayId() );
			log.info( msg );
			return false;
		}
	}
}
