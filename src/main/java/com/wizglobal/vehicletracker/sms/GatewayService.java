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
public class GatewayService  {

	private static Logger log = Logger.getLogger( GatewayService.class );

	/** SMS Gateway Properties */
	private Properties gatewayProperties = new Properties();
	private Service service;
	private SerialModemGateway gateway;

	public Service getService() {
		if( service == null ) {
			service = Service.getInstance();
			service.setInboundMessageNotification( new InboundNotification() );
			service.setOutboundMessageNotification( new OutboundNotification() );
		}
		return service;
	}

	private  SerialModemGateway getGateway() {
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
	public void stopService() throws TimeoutException, GatewayException, SMSLibException,
			IOException, InterruptedException {
		getService().stopService();
	}

	/**
         * Starts the SMS service from a different thread to prevent the app from hanging when this method is invoked.
         *
         */
        public void startService() {
            Runnable runnable = new Runnable() {

                                    @Override
                                    public void run() {
                                        try {
                                            getService().addGateway( getGateway() );
                                            log.info( "Starting SMS service" );
                                            if( getService().getServiceStatus() != Service.ServiceStatus.STARTED ) {
                                                    getService().startService();
                                                    log.info( "SMS service successfully Started!" );
                                            } else {
                                                log.info( "SMS service already Started!" );
                                            }
                                        } catch (GatewayException ex) {
                                           log.error("The SMS gateway failed to initialize. " + ex.getMessage(), ex);
                                        } catch (SMSLibException ex) {
                                            log.error("SMSLib internal error prevented the SMS service from initializing properly. " + ex.getMessage(), ex);
                                        }catch ( IOException | InterruptedException ex) {
                                            log.error("Error starting the SMS service. " + ex.getMessage(), ex);
                                        }
                                    }
                                };
            Thread newThread = new Thread(runnable);
            newThread.start();
        }

        private GatewayService() {
        }
        
        
        
        public static GatewayService getGatewayService(){
            return GatewayServiceHolder.GATEWAY_SERVICE;
        }
        
        private static final class GatewayServiceHolder {
            private static final GatewayService GATEWAY_SERVICE = new GatewayService();
        }
}
