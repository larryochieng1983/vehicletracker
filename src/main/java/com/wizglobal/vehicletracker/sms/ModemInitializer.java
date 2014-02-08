/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;

/**
 * @author Otieno Lawrence
 * 
 */
public class ModemInitializer extends HttpServlet {

	private static Logger log = Logger.getLogger( ModemInitializer.class );
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		try {
			GatewayService.startService();
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

	public void service( HttpServletRequest request, HttpServletResponse response )
			throws ServletException, IOException {

	}
}
