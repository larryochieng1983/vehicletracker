/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

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
import com.wizglobal.vehicletracker.util.IncomingMessageObserverImpl;

/**
 * @author Otieno Lawrence
 * 
 */
public class ReadMessage implements Job {

	private static Logger log = Logger.getLogger( ReadMessage.class );

	/** SMS Gateway Properties */
	private Properties gatewayProperties = new Properties();
	private Service service;
	private IncomingMessage incomingMessage = new IncomingMessage();

	public ReadMessage() {
		try {
			gatewayProperties.load( getClass().getResourceAsStream( "/smslib/modem.properties" ) );
			// gatewayProperties.load( new FileInputStream( "smslib/modem.properties" ) );
		} catch( IOException e ) {
			log.error( "Failed To Load SMS Server Settings" );
			throw new IllegalStateException( "Failed To Load SMS Server Settings" );
		}
	}

	private synchronized Service getService() {
		if( this.service == null ) {
			this.service = Service.getInstance();
			InboundNotification inboundNotification = new InboundNotification();
			CallNotification callNotification = new CallNotification();
			GatewayStatusNotification statusNotification = new GatewayStatusNotification();
			OrphanedMessageNotification orphanedMessageNotification = new OrphanedMessageNotification();

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
			this.service.setInboundMessageNotification( inboundNotification );
			this.service.setCallNotification( callNotification );
			this.service.setGatewayStatusNotification( statusNotification );
			this.service.setOrphanedMessageNotification( orphanedMessageNotification );
			try {
				if( this.service.getServiceStatus() == Service.ServiceStatus.STOPPED ) {
					this.service.addGateway( gateway );
					this.service.startService();
				}
			} catch( TimeoutException e ) {
				log.error( e );
			} catch( SMSLibException e ) {
				log.error( e );
			} catch( IOException e ) {
				log.error( e );
			} catch( InterruptedException e ) {
				log.error( e );
			}
		}
		return this.service;

	}

	public void receive() throws TimeoutException, GatewayException, SMSLibException, IOException,
			InterruptedException {
		log.info( "Reading Available Messages" );
		List<InboundMessage> msgList;

		incomingMessage = new IncomingMessage();

		List<IncomingSms> list = new ArrayList<IncomingSms>();
		msgList = new ArrayList<InboundMessage>();
		getService().readMessages( msgList, MessageClasses.ALL );

		for( InboundMessage msg : msgList ) {
			IncomingSms incomingSms = new IncomingSms( msg.getType(), msg.getOriginator(),
					msg.getDate(), msg.getDate(), msg.getText() );
			list.add( incomingSms );
			// Delete after reading'
			if( !getService().deleteMessage( msg ) ) {
				log.warn( "Message Read but could not be deleted" );
			}
		}
		new IncomingMessageObserverImpl( incomingMessage );
		incomingMessage.setIncomingMessages( list );
		log.info( "Finished Reading Available Messages " + list.size() );

	}

	public void stopService() throws TimeoutException, GatewayException, SMSLibException,
			IOException, InterruptedException {
		getService().stopService();
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
