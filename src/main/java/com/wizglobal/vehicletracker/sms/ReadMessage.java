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
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
import com.wizglobal.vehicletracker.util.IncomingMessageObserver;

/**
 * @author Otieno Lawrence
 * 
 */
public class ReadMessage implements Job {

	private static Logger log = Logger.getLogger( ReadMessage.class );

	/** SMS Gateway Properties */
	private Properties gatewayProperties = new Properties();
	private List<IncomingSms> incomingMessages = new ArrayList<IncomingSms>();
	private List<IncomingMessageObserver> observers = new ArrayList<IncomingMessageObserver>();

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
			List<IncomingSms> list = new ArrayList<IncomingSms>();
			msgList = new ArrayList<InboundMessage>();
			Service.getInstance().readMessages( msgList, MessageClasses.ALL );
			for( InboundMessage msg : msgList ) {
				IncomingSms incomingSms = new IncomingSms( msg.getType(), msg.getOriginator(),
						msg.getDate(), msg.getDate(), msg.getText() );
				list.add( incomingSms );
				// Delete after reading'
				Service.getInstance().deleteMessage( msg );
			}
			setIncomingMessages( list );
		} catch( Exception e ) {
			log.error( e );
		} finally {
			Service.getInstance().stopService();
		}
	}

	/**
	 * @return the incomingmessages
	 */
	public List<IncomingSms> getIncomingMessages() {
		return incomingMessages;
	}

	/**
	 * @param incomingmessages the incomingmessages to set
	 */
	public void setIncomingMessages( List<IncomingSms> incomingMessages ) {
		this.incomingMessages = incomingMessages;
		notifyAllObservers();
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

	public void attach( IncomingMessageObserver observer ) {
		observers.add( observer );
	}

	public void notifyAllObservers() {
		for( IncomingMessageObserver observer : observers ) {
			observer.saveMessage();
		}
	}

	@Override
	public void execute( JobExecutionContext context ) throws JobExecutionException {
		try {
			receive();
		} catch( TimeoutException e ) {
			log.error( e );
		} catch( GatewayException e ) {
			log.error( e );
		} catch( SMSLibException e ) {
			log.error( e );
		} catch( IOException e ) {
			log.error( e );
		} catch( InterruptedException e ) {
			log.error( e );
		}
	}
}
