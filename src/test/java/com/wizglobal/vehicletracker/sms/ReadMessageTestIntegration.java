/**
 * 
 */
package com.wizglobal.vehicletracker.sms;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.smslib.GatewayException;
import org.smslib.SMSLibException;
import org.smslib.TimeoutException;

import junit.framework.TestCase;

/**
 * @author Otieno Lawrence
 * 
 */
public class ReadMessageTestIntegration extends TestCase {

	private ReadMessage readMessage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		readMessage = new ReadMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test method for {@link com.wizglobal.vehicletracker.sms.ReadMessage#receive()}.
	 */
	@Test
	public void testReceive() {
		try {
			readMessage.receive();
		} catch( TimeoutException e ) {
			fail( "Exception Not Expected! " + e );
		} catch( GatewayException e ) {
			fail( "Exception Not Expected! " + e );
		} catch( SMSLibException e ) {
			fail( "Exception Not Expected! " + e );
		} catch( IOException e ) {
			fail( "Exception Not Expected! " + e );
		} catch( InterruptedException e ) {
			fail( "Exception Not Expected! " + e );
		}

	}

}
