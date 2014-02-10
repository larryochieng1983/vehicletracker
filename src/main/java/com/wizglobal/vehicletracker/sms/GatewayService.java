/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.smslib.AGateway.Protocols;
import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 * @author Otieno Lawrence
 * 
 */
public class GatewayService {

	private static Logger log = Logger.getLogger( GatewayService.class );

	/** SMS Gateway Properties */
	private static Properties gatewayProperties = new Properties();
	private static Service service;
	private static SerialModemGateway gateway;

	public static Service getService() {
		if( service == null ) {
			service = Service.getInstance();
			service.setInboundMessageNotification( new InboundNotification() );
			service.setOutboundMessageNotification( new OutboundNotification() );
		}
		return service;
	}

	private static SerialModemGateway getGateway() {
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream( "smslib/modem.properties" );
			if( inputStream == null ) {
				log.error( "Failed To Load SMS Server Settings" );
				throw new IllegalStateException( "Failed To Load SMS Server Settings" );
			}
			gatewayProperties.load( inputStream );
		} catch( IOException e ) {
			log.error( "Failed To Load SMS Server Settings" );
			throw new IllegalStateException( "Failed To Load SMS Server Settings" );
		}
		if( gateway == null ) {
			gateway = new SerialModemGateway( gatewayProperties.getProperty( "gateway.0" ),
					gatewayProperties.getProperty( "modem1.port" ), Integer.parseInt( gatewayProperties
							.getProperty( "modem1.baudrate" ) ),
					gatewayProperties.getProperty( "modem1.manufacturer" ),
					gatewayProperties.getProperty( "modem1.model" ) );
		}
		gateway.setProtocol( Protocols.PDU );
		gateway.setInbound( true );
		gateway.setOutbound( true );
		gateway.setSimPin( gatewayProperties.getProperty( "modem1.pin" ) );
		return gateway;
	}

	/**
	 * 
	 * @throws TimeoutException
	 * @throws GatewayException
	 * @throws SMSLibException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void stopService() throws TimeoutException, GatewayException, SMSLibException,
			IOException, InterruptedException {
		getService().stopService();
	}

	public static void startService() throws TimeoutException, GatewayException, SMSLibException,
			IOException, InterruptedException {
		getService().addGateway( getGateway() );
		log.info( "Starting SMS service" );
		getService().startService();
		log.info( "SMS service successfully Started!" );
	}
}
